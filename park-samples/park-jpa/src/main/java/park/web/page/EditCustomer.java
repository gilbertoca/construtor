package park.web.page;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.IntegerField;
import park.model.Customer;
import park.model.Person;
import park.model.Vehicle;
import park.model.dto.CustomerLookUp;
import park.service.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class EditCustomer extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    protected Table table = new Table("table");
    /**
     * Used to manage the data model state. On Get method we set it to false(update)
     * and when posting - OnClick method - we set i to true(insert)
     * isNew(false)=update, otherwise insert
     */
    protected HiddenField isNewField = new HiddenField("isNew", Boolean.class);
    /** The flow source track */
    protected HiddenField referrerField = new HiddenField("referrer", String.class);

    /**
     * The data model ID, used to get data from and to database.
     * The default option is declare it as HiddenField, but sometimes - when the user
     * enter the ID data - we use a normal TextField. For example:
     *  default --> protected HiddenField idField = new HiddenField("id", Long.class);
     *  sometimes-> protected TextField idField = new TextField("licensePlate", true);
     *  sometimes-> protected Select idField = new Select("id",true);
     * Don't forget to set i read only when on Update operation.
     */
    public Select idField = new Select("person.id", true);
    /** Bindable variables(ID, used on the Get method) can automatically have their value set by request parameters */
    public Long id;
    /** Bindable variables(used to track where the page was requested) can automatically have their value set by request parameters */
    public String referrer;
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditCustomer() {
        System.out.println("\n EditCustomer() method \n");

        getModel().put("title", getMessage("editCustomer.title"));
        getModel().put("heading", getMessage("editCustomer.heading"));

        addControl(form);
        form.add(referrerField);

        FieldSet personFieldSet = new FieldSet("personFieldSet");
        //personFieldSet.setStyle("background", "RED");

        FieldSet customerFieldSet = new FieldSet("customerFieldSet");
        //customerFieldSet.setStyle("background", "BLUE");

        //the idField is here - see below
        form.add(personFieldSet);
        form.add(customerFieldSet);
        form.add(isNewField);

        idField.setRequired(true);
        idField.setDefaultOption(Option.EMPTY_OPTION);

        personFieldSet.add(idField);
        IntegerField paymentDayField = new IntegerField("paymentDay", true);
        customerFieldSet.add(paymentDayField);

        table.setClass(Table.CLASS_SIMPLE);
        table.addColumn(new Column("licensePlate"));
        table.addColumn(new Column("color"));
        customerFieldSet.add(table);

        form.add(new Submit("okBt", this, "onOkClick"));
        form.add(new Submit("cancelBt", this, "onCancelClick"));
    }

    private List getVehiclesByCusotmerId(Long id) {
        System.out.println("\n getVehiclesByCusotmerId(Long id) method \n");
        Query queryObject = em.createNamedQuery("Vehicle.findByCustomerId");
        queryObject.setParameter("customerId", id);
        return queryObject.getResultList();
    }

    // Event Handlers ---------------------------------------------------------
    @Override
    public void onInit() {
        super.onInit();
        System.out.println("\n onInit() method \n");
        // Set the id on the table's controlLink. If you view the
        // output rendered by Table note that the id parameter
        // is rendered for each Paging and Sorting link.
        table.getControlLink().setParameter("id", id);
        // Set data provider to populate the table row list from
        table.setDataProvider(new DataProvider<Vehicle>() {

            public List<Vehicle> getData() {
                return (List<Vehicle>) getVehiclesByCusotmerId(id);
            }
        });
        idField.setDataProvider(new DataProvider() {

            String query = id == null ? "Customer.lookUpNameNotExists" : "Customer.lookUpName";

            public List getData() {
                List options = new ArrayList();
                List<CustomerLookUp> result =
                        em.createNamedQuery(query, CustomerLookUp.class).getResultList();
                for (CustomerLookUp customer : result) {
                    options.add(new Option(customer.getId(), customer.getCustomerName()));
                }
                return options;
            }
        });

    }

    /**
     * When page is first displayed on the GET request.
     *
     * @see Page#onGet()
     */
    @Override
    public void onGet() {
        System.out.println("\n onGet() method \n");
        if (id != null) {
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                // Copy customer data to form. The idField value will be set by
                // this call
                form.copyFrom(customer);
                //id parameter of the page is NOT null, then isNew=false
                isNewField.setValueObject(false);
                //it is the PK, here we can't change it, whick in this case idField
                //is not a HiddenField
                idField.setReadonly(true);
            }
        } else {
            //id parameter of the page is null, then isNew=true
            isNewField.setValueObject(true);
        }

        if (referrer != null) {
            // Set referrerField HiddenField to bound referrer field
            referrerField.setValue(referrer);
        }
    }

    public boolean onOkClick() throws Exception {
        System.out.println("\n onOkClick() method \n");

        if (form.isValid()) {
            Customer customer = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = Long.parseLong(idField.getValue());
            //isNew(false)=update, othewise insert
            Boolean _isNew = (Boolean) isNewField.getValueObject();

            if (_isNew) {
                customer = new Customer();
            } else {
                customer = em.find(Customer.class, _id);
            }

            form.copyTo(customer);
            //We need to bind Person(NP or LE) explicitly to Customer
            //LegalEntity lP
            //NaturaPerson nP
            //customer.setPerson(em.getReference(Person.class, Long.parseLong(idField.getValue())));
            em.createNamedQuery("Customer.findPersonById").setParameter("id", Long.parseLong(idField.getValue())).getSingleResult();

            //We need transation
            try {
                em.getTransaction().begin();
                if (_isNew) {
                    em.persist(customer);
                } else {
                    em.merge(customer);
                }
                em.getTransaction().commit();
            } catch (Exception ex) {
                try {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
                throw ex;
            }

            //The referrerField HiddenField was set on GET request (onGet() method)
            String _referrer = referrerField.getValue();
            if (_referrer != null) {
                setRedirect(_referrer);
            } else {
                setRedirect(ViewCustomer.class);
            }

            return true;

        } else {
            return true;
        }
    }

    public boolean onCancelClick() {
        System.out.println("\n onCancelClick() method \n");
        //The referrerField HiddenField was set on GET request (onGet() method)
        String _referrer = referrerField.getValue();
        if (_referrer != null) {
            setRedirect(_referrer);
        } else {
            setRedirect(ViewCustomer.class);
        }
        return true;
    }

    @Override
    public void onDestroy() {
        System.out.println("\n onDestroy() method \n");
        super.onDestroy();
        if (em.isOpen()) {
            em.close();
        }
    }
}

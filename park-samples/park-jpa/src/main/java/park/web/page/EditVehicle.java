package park.web.page;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import park.model.PriceTable;
import park.model.Vehicle;
import park.model.VehicleType;
import park.model.dto.CustomerLookUp;
import park.service.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class EditVehicle extends BorderPage {

    private static final long serialVersionUID = 1L;

    protected Form form = new Form("form");
    /**
     * Used to manage the data model state. On Get method we set it to false(update)
     * and when posting - OnClick method - we set i to true(insert)
     * isNew(false)=update, otherwise insert
     */
    protected HiddenField isNewField = new HiddenField("isNew", Boolean.class);
    /** The source track */
    protected HiddenField referrerField = new HiddenField("referrer", String.class);
    /**
     * The data model ID, used to get data from and to database.
     * The default option is declare it as HiddenField, but sometimes - when the user
     * enter the ID data - we use a normal TextField. For example:
     *  default --> protected HiddenField idField = new HiddenField("id", Long.class);
     *  sometimes-> protected TextField idField = new TextField("licensePlate", true);
     * Don't forget to set i read only when on Update operation.
     */
    protected TextField idField = new TextField("licensePlate", true);
    /** Bindable variables(ID, used on the Get method) can automatically have their value set by request parameters */
    public String licensePlate;
    /** Bindable variables(used to track where the page was requested) can automatically have their value set by request parameters */
    public String referrer;
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditVehicle() {
        System.out.println("\n EditVehicle() method \n");

        getModel().put("title", getMessage("editVehicle.title"));
        getModel().put("heading", getMessage("editVehicle.heading"));

        addControl(form);
        form.add(referrerField);

        idField.setMinLength(5);
        idField.setFocus(true);
        form.add(idField);
        form.add(isNewField);

        TextField colorField = new TextField("color", true);
        colorField.setFocus(true);
        form.add(colorField);

        Select vehicleTypeSelect = new Select("vType");
        vehicleTypeSelect.setRequired(true);
        vehicleTypeSelect.setDefaultOption(Option.EMPTY_OPTION);
        vehicleTypeSelect.setDataProvider(new DataProvider() {

            public List getData() {
                List options = new ArrayList();
                List<VehicleType> vehicleTypes = (List<VehicleType>) em.createNamedQuery("VehicleType.findAll").getResultList();
                for (VehicleType vehicleType : vehicleTypes) {
                    options.add(new Option(vehicleType.getVehicleType(), vehicleType.getVehicleType()));
                }
                return options;
            }
        });
        form.add(vehicleTypeSelect);

        Select priceTableSelect = new Select("priceTableId");
        priceTableSelect.setRequired(true);
        priceTableSelect.setDefaultOption(Option.EMPTY_OPTION);
        priceTableSelect.setDataProvider(new DataProvider() {

            public List getData() {
                List options = new ArrayList();
                List<PriceTable> priceTables = (List<PriceTable>) em.createNamedQuery("PriceTable.findAll").getResultList();
                for (PriceTable priceTable : priceTables) {
                    options.add(new Option(priceTable.getId(), priceTable.getItem() + "->" + priceTable.getPrice()));
                }
                return options;
            }
        });
        form.add(priceTableSelect);

        Select customerSelect = new Select("customerId");
        customerSelect.setRequired(true);
        customerSelect.setDefaultOption(Option.EMPTY_OPTION);
        customerSelect.setDataProvider(new DataProvider() {

            public List getData() {
                List options = new ArrayList();
                List<CustomerLookUp> result =
                        em.createQuery("SELECT new park.model.dto.CustomerLookUp(c.id, p.name) FROM Customer c JOIN c.person p", CustomerLookUp.class).getResultList();
                for (CustomerLookUp customer : result) {
                    options.add(new Option(customer.getId(), customer.getCustomerName()));
                }
                return options;
            }
        });
        form.add(customerSelect);
        form.add(new Submit("okBt", this, "onOkClick"));
        form.add(new Submit("cancelBt", this, "onCancelClick"));
    }

    // Event Handlers ---------------------------------------------------------
    /**
     * When page is first displayed on the GET request.
     *
     * @see Page#onGet()
     */
    @Override
    public void onGet() {
        System.out.println("\n onGet() method \n");
        if (licensePlate != null) {
            Vehicle vehicle = em.find(Vehicle.class, licensePlate);
            if (vehicle != null) {
                // Copy vehicle data to form. The idField value will be set by
                // this call
                form.copyFrom(vehicle);
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
            Vehicle vehicle = null;
            //local variable, don't confuse it with the public id parameter of the page
            String _licensePlate = idField.getValue();
            //isNew(false)=update, othewise insert
            Boolean _isNew = (Boolean) isNewField.getValueObject();

            if (_isNew) {
                vehicle = new Vehicle();
            } else {
                vehicle = em.find(Vehicle.class, _licensePlate);
            }

            form.copyTo(vehicle);
            //We need transation
            try {
                em.getTransaction().begin();
                if (_isNew) {
                    em.persist(vehicle);
                } else {
                    em.merge(vehicle);
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
                setRedirect(ViewVehicle.class);
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
            setRedirect(ViewVehicle.class);
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

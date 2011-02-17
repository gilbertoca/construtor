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
import park.model.orm.PriceTable;
import park.model.orm.Vehicle;
import park.model.orm.VehicleType;
import park.orm.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class EditVehicle extends BorderPage {

    private static final long serialVersionUID = 1L;

    private class CustomerLookUp {

        private Long id;
        private String customerName;

        public CustomerLookUp(Long id, String customerName) {
            this.id = id;
            this.customerName = customerName;
        }

        public Long getId() {
            return id;
        }

        public String getCustomerName() {
            return customerName;
        }
    }
    private Form form = new Form("form");
    private HiddenField referrerField = new HiddenField("referrer", String.class);
    private HiddenField idField = new HiddenField("id", String.class);
    // Bindable variables can automatically have their value set by request parameters
    public String id;
    public String referrer;
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditVehicle() {
        System.out.println("\n EditVehicle() method \n");

        getModel().put("title", getMessage("editVehicle.title"));
        getModel().put("heading", getMessage("editVehicle.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        form.add(referrerField);
        form.add(idField);

        TextField licensePlateField = new TextField("licensePlate", true);
        licensePlateField.setMinLength(5);
        licensePlateField.setFocus(true);
        form.add(licensePlateField);

        TextField colorField = new TextField("color", true);
        colorField.setMinLength(5);
        colorField.setFocus(true);
        form.add(colorField);

        Select vehicleTypeSelect = new Select("vehicleType");
        vehicleTypeSelect.setRequired(true);
        vehicleTypeSelect.add(Option.EMPTY_OPTION);
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

        Select priceTableSelect = new Select("priceTable");
        priceTableSelect.setRequired(true);
        priceTableSelect.add(Option.EMPTY_OPTION);
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

        Select customerSelect = new Select("customer");
        customerSelect.setRequired(true);
        customerSelect.add(Option.EMPTY_OPTION);
        customerSelect.setDataProvider(new DataProvider() {
            public List getData() {
                List options = new ArrayList();
                List<CustomerLookUp> result =
                        em.createQuery("SELECT new park.web.page.EditVehicle.CustomerLookUp(c.id, p.name) FROM Customer c JOIN c.person p", CustomerLookUp.class).getResultList();
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
        if (id != null) {
            Vehicle vehicle = em.find(Vehicle.class, id);
            if (vehicle != null) {
                // Copy vehicle data to form. The idField value will be set by
                // this call
                form.copyFrom(vehicle);
            }
        }

        if (referrer != null) {
            // Set referrerField HiddenField to bound referrer field
            referrerField.setValue(referrer);
        }
    }

    public boolean onOkClick() throws Exception {
        System.out.println("\n onOkClick() method \n");
        //isNew(false)=update, othewise insert
        boolean isNew = false;
        if (form.isValid()) {
            Vehicle vehicle = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = (Long) idField.getValueObject();
            if (_id != null) {
                vehicle = em.find(Vehicle.class, id);
            } else {
                isNew = true;
                vehicle = new Vehicle();
            }

            form.copyTo(vehicle);
            //We need transation
            try {
                em.getTransaction().begin();
                if (isNew) {
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

            //The referrerField HiddenField was set on GET request
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
        //The referrerField HiddenField was set on GET request
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

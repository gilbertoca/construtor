package park.web.page;

import javax.persistence.EntityManager;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.orm.Vehicle;
import park.orm.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class EditVehicle extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    private HiddenField referrerField = new HiddenField("referrer", String.class);
    private HiddenField idField = new HiddenField("id", Long.class);
    // Bindable variables can automatically have their value set by request parameters
    public Long id;
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

        TextField nameField = new TextField("name", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(nameField);

        TextField addressField = new TextField("address", true);
        addressField.setMinLength(5);
        addressField.setFocus(true);
        fieldSet.add(addressField);

        DateField dtFoundationField = new DateField("dtFoundation", true);
        dtFoundationField.setFormatPattern(getMessage("date.format"));
        dtFoundationField.setShowCalendar(false);
        fieldSet.add(dtFoundationField);

        TextField taxpayersField = new TextField("taxpayersId", true);
        taxpayersField.setMinLength(5);
        taxpayersField.setFocus(true);
        fieldSet.add(taxpayersField);

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

package park.web.page;

import javax.persistence.EntityManager;
import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import park.model.VehicleType;
import park.service.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class EditVehicleType extends BorderPage {

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
    /** The data model ID, used to get data from and to database */
    protected HiddenField idField = new HiddenField("vehicleType", String.class);
    /** Necessary here to access it on OnGet method. */
    protected TextField vehicleTypeField = new TextField("vehicleType", true);
    // Bindable variables can automatically have their value set by request parameters
    public String vehicleType;
    public String referrer;
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditVehicleType() {
        System.out.println("\n EditVehicleType() method \n");

        getModel().put("title", getMessage("editVehicleType.title"));
        getModel().put("heading", getMessage("editVehicleType.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        form.add(referrerField);
        form.add(idField);
        form.add(isNewField);

        
        vehicleTypeField.setFocus(true);
        form.add(vehicleTypeField);

        TextField modelField = new TextField("model", true);
        form.add(modelField);

        TextField manufacturerField = new TextField("manufacturer", true);
        form.add(manufacturerField);

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
        if (vehicleType != null) {
            VehicleType vehicleT = em.find(VehicleType.class, vehicleType);
            if (vehicleT != null) {
                // Copy vehicle data to form. The idField value will be set by
                // this call
                form.copyFrom(vehicleT);
                //vehicleType parameter of the page is NOT null, then isNew=false
                isNewField.setValueObject(false);
                //it is the PK, here we can't change it.
                vehicleTypeField.setReadonly(true);
            }
        } else {
            //vehicleType parameter of the page is null, then isNew=true
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
            VehicleType vehicleT = null;
            //local variable, don't confuse it with the public vehicleType parameter of the page
            String _vehicleType = idField.getValue();
            //isNew(false)=update, othewise insert
            Boolean _isNew = (Boolean) isNewField.getValueObject();

            if (_isNew) {
                vehicleT = new VehicleType();
            } else {
                vehicleT = em.find(VehicleType.class, _vehicleType);
            }

            form.copyTo(vehicleT);
            //We need transation
            try {
                em.getTransaction().begin();
                if (_isNew) {
                    em.persist(vehicleT);
                } else {
                    em.merge(vehicleT);
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

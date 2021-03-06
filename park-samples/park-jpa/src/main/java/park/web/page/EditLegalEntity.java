package park.web.page;

import javax.persistence.EntityManager;
import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.IntegerField;
import park.model.LegalEntity;
import park.service.util.EntityManagerContext;


/**
 *
 * @author gilberto
 */
public class EditLegalEntity extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    /**
     * Used to manage the data model state. On Get method we set it to false(update)
     * and when posting - OnClick method - we set i to true(insert)
     * isNew(false)=update, otherwise insert
     */
    protected HiddenField isNewField = new HiddenField("isNew", Boolean.class);
    /** The source track */
    protected HiddenField referrerField = new HiddenField("referrer", String.class);
    /** The data model ID, used to get data from and to database */
    protected HiddenField idField = new HiddenField("id", Long.class);
    /** Bindable variables(ID, used on the Get method) can automatically have their value set by request parameters */
    public Long id;
    /** Bindable variables(used to track where the page was requested) can automatically have their value set by request parameters */
    public String referrer;
    
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor -----------------------------------------------------------
    public EditLegalEntity() {
        System.out.println("\n EditLegalEntity() method \n");

        getModel().put("title", getMessage("editLegalEntity.title"));
        getModel().put("heading", getMessage("editLegalEntity.heading"));

        addControl(form);
        form.add(referrerField);
        form.add(idField);


        TextField nameField = new TextField("name", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        form.add(nameField);

        TextField addressField = new TextField("address", true);
        addressField.setMinLength(5);
        addressField.setFocus(true);
        form.add(addressField);
        
        //Grouping specific settings
        FieldSet fieldSet = new FieldSet("legalEntityFieldSet");
        fieldSet.setLabel(path);
        form.add(fieldSet);
        
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
            LegalEntity legalEntity = em.find(LegalEntity.class, id);
            if (legalEntity != null) {
                // Copy vehicle data to form. The idField value will be set by
                // this call
                form.copyFrom(legalEntity);
                //id parameter of the page is NOT null, then isNew=false
                isNewField.setValueObject(false);
                //it is the PK, here we can't change it.
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
        //isNew(false)=update, othewise insert
        boolean isNew = false;
        if (form.isValid()) {
            LegalEntity legalEntity = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = (Long) idField.getValueObject();
            if (_id != null) {
                legalEntity = em.find(LegalEntity.class, id);
            } else {
                isNew = true;
                legalEntity = new LegalEntity();
            }

            form.copyTo(legalEntity);
            //We need transation
            try {
                em.getTransaction().begin();
                if (isNew) {
                    em.persist(legalEntity);
                } else {
                    em.merge(legalEntity);
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
                setRedirect(ViewLegalEntity.class);
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
            setRedirect(ViewLegalEntity.class);
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

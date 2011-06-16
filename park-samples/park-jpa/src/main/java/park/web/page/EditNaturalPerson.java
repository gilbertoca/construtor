package park.web.page;

import javax.persistence.EntityManager;
import org.apache.click.Page;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.NaturalPerson;
import park.service.util.EntityManagerContext;

public class EditNaturalPerson extends BorderPage {

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
    public EditNaturalPerson() {
        System.out.println("\n EditNaturalPerson() method \n");

        getModel().put("title", getMessage("editNaturalPerson.title"));
        getModel().put("heading", getMessage("editNaturalPerson.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        form.add(referrerField);
        form.add(idField);

        FieldSet fieldSet = new FieldSet("addressFieldSet");
        form.add(fieldSet);

        TextField nameField = new TextField("name", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(nameField);

        TextField addressField = new TextField("address", true);
        addressField.setMinLength(5);
        addressField.setFocus(true);
        fieldSet.add(addressField);

        DateField dtBirthField = new DateField("dtBirth", true);
        dtBirthField.setFormatPattern(getMessage("date.format"));
        dtBirthField.setShowCalendar(false);
        fieldSet.add(dtBirthField);

        TextField legalDocumentField = new TextField("legalDocument", true);
        legalDocumentField.setMinLength(5);
        legalDocumentField.setFocus(true);
        fieldSet.add(legalDocumentField);

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
            NaturalPerson naturalPerson = em.find(NaturalPerson.class, id);
            if (naturalPerson != null) {
                // Copy naturalPerson data to form. The idField value will be set by
                // this call
                form.copyFrom(naturalPerson);
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
            NaturalPerson naturalPerson = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = (Long) idField.getValueObject();
            if (_id != null) {
                naturalPerson = em.find(NaturalPerson.class, id);
            } else {
                isNew = true;
                naturalPerson = new NaturalPerson();
            }

            form.copyTo(naturalPerson);
            //We need transation
            try {
                em.getTransaction().begin();
                if (isNew) {
                    em.persist(naturalPerson);
                } else {
                    em.merge(naturalPerson);
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
                setRedirect(ViewNaturalPerson.class);
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
            setRedirect(ViewNaturalPerson.class);
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

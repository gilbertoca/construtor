package park.web.page;

import com.google.constructor.cip.orm.jpa.BaseJPAService;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.orm.NaturalPerson;

public class EditNaturalPerson extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    private HiddenField referrerField = new HiddenField("referrer", String.class);
    private HiddenField idField = new HiddenField("id", Long.class);
    // Bindable variables can automatically have their value set by request parameters
    public Long id;
    public String referrer;
    private final BaseJPAService<NaturalPerson, Long> naturalPersonService;

    public EditNaturalPerson() {
        naturalPersonService = new BaseJPAService<NaturalPerson, Long>(NaturalPerson.class);

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
            NaturalPerson naturalPerson = naturalPersonService.find(id);
            System.out.println("\n naturalPersonService.find(id) was triggered \n");
            if (naturalPerson != null) {
                // Copy naturalPerson data to form. The idField value will be set by
                // this call
                form.copyFrom(naturalPerson);
            }
        }

        if (referrer != null) {
            // Set referrerField HiddenField to bound referrer field
            referrerField.setValue(referrer);
        }
    }

    public boolean onOkClick() throws Exception {
        //isNew(false)=update, othewise insert
        boolean isNew = false;
        if (form.isValid()) {
            NaturalPerson naturalPerson = null;
            //local variable, don't confuse it with the public id parameter of the page
            Long _id = (Long) idField.getValueObject();
            if (_id != null) {
                naturalPerson = naturalPersonService.find(_id);
            } else {
                isNew = true;
                naturalPerson = new NaturalPerson();
            }

            form.copyTo(naturalPerson);
            if (isNew) {
                naturalPersonService.insert(naturalPerson);
            } else {
                naturalPersonService.update(naturalPerson);
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
        //The referrerField HiddenField was set on GET request
        String _referrer = referrerField.getValue();
        if (_referrer != null) {
            setRedirect(_referrer);
        } else {
            setRedirect(ViewNaturalPerson.class);
        }
        return true;
    }
}

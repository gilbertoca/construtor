package park.web.page;

import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.LegalEntity;
import park.service.util.JpaHelper;

/**
 *
 * @author gilberto
 */
public class EditLegalEntity extends BorderPage {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    private HiddenField referrerField = new HiddenField("referrer", String.class);
    private HiddenField idField = new HiddenField("id", Long.class);
    // Bindable variables can automatically have their value set by request parameters
    public Long id;
    public String referrer;

    // Constructor -----------------------------------------------------------
    public EditLegalEntity() {
        addControl(form);
        form.add(referrerField);
        form.add(idField);

        FieldSet fieldSet = new FieldSet("Legal Entity");
        form.add(fieldSet);

        TextField nameField = new TextField("name", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(nameField);
        TextField addressField = new TextField("address", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(addressField);
        fieldSet.add(new DateField("dtFoundation", true));
        TextField taxpayersIdField = new TextField("taxpayersId", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(taxpayersIdField);

        form.add(new Submit("ok", "  OK  ", this, "onOkClick"));
        form.add(new Submit("cancel", this, "onCancelClick"));
    }

    // Event Handlers ---------------------------------------------------------
    /**
     * When page is first displayed on the GET request.
     *
     * @see Page#onGet()
     */
    @Override
    public void onGet() {
        if (id != null) {
            LegalEntity legalEntity = (LegalEntity) JpaHelper.retrieve(LegalEntity.class, id);

            if (legalEntity != null) {
                // Copy legalEntity data to form. The idField value will be set by
                // this call
                form.copyFrom(legalEntity);
            }
        }

        if (referrer != null) {
            // Set HiddenField to bound referrer field
            referrerField.setValue(referrer);
        }
    }

    public boolean onOkClick() throws Exception {
        boolean isNew = false;
        if (form.isValid()) {
            LegalEntity legalEntity = null;
            Long id = (Long) idField.getValueObject();
            if (id != null){
                legalEntity = (LegalEntity) JpaHelper.retrieve(LegalEntity.class, id);
            }else{
                isNew = true;
                legalEntity = new LegalEntity();
            }

            form.copyTo(legalEntity);
            if (isNew) {
                JpaHelper.create(legalEntity);
            } else {
                JpaHelper.update(legalEntity);
            }

            String referrer = referrerField.getValue();
            if (referrer != null) {
                setRedirect(referrer);
            } else {
                setRedirect(ViewLegalEntity.class);
            }

            return true;

        } else {
            return true;
        }
    }

 public boolean onCancelClick() {
        String referrer = referrerField.getValue();
        if (referrer != null) {
            setRedirect(referrer);
        } else {
            setRedirect(ViewLegalEntity.class);
        }
        return true;
    }
}


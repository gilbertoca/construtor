package park.web.page;

import com.google.constructor.cip.orm.jpa.BaseJPAService;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.LegalEntity;

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

    private BaseJPAService<LegalEntity, Long> legalEntityService;

    // Constructor -----------------------------------------------------------
    public EditLegalEntity() {
        legalEntityService = new BaseJPAService<LegalEntity, Long>(LegalEntity.class);
        
        getModel().put("title", getMessage("editLegalEntity.title"));
        getModel().put("heading", getMessage("editLegalEntity.heading"));
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
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(addressField);
        DateField dtFoundationField = new DateField("dtFoundation", true);
        dtFoundationField.setFormatPattern(getMessage("date.format"));
        fieldSet.add(dtFoundationField);
        TextField taxpayersField = new TextField("taxpayersId", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
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
        if (id != null) {
            LegalEntity legalEntity = legalEntityService.find(id);

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
        //isNew(false)=update, othewise insert
        boolean isNew = false;
        if (form.isValid()) {
            LegalEntity legalEntity = null;
            Long id = (Long) idField.getValueObject();
            if (id != null){
                legalEntity = legalEntityService.find(id);
            }else{
                isNew = true;
                legalEntity = new LegalEntity();
            }

            form.copyTo(legalEntity);
            if (isNew) {
                legalEntityService.insert(legalEntity);
            } else {
                legalEntityService.update(legalEntity);
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


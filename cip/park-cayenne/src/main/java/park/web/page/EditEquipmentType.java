package park.web.page;

import park.model.EquipmentType;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.commons.lang.StringUtils;

public class EditEquipmentType extends org.apache.click.Page {

    private Form form = new Form("form");
    private HiddenField referrerField = new HiddenField("referrer", String.class);
    private HiddenField idField = new HiddenField("id", Integer.class);
    // Bindable variables can automatically have their value set by request parameters
    public Long id;
    public String referrer;
    private ObjectContext context = DataContext.getThreadObjectContext();

    public EditEquipmentType() {
        System.out.println("\n EditEquipmentType() method \n");
        addControl(form);
        form.add(referrerField);
        form.add(idField);

        TextField descriptionField = new TextField("description", true);
        descriptionField.setMinLength(5);
        descriptionField.setFocus(true);
        form.add(descriptionField);
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
            EquipmentType equipmentType = Cayenne.objectForPK(context,EquipmentType.class, id);
            System.out.println("\n Cayenne.objectForPK(class,id) was triggered \n");
            if (equipmentType != null) {
                // Copy equipmentType data to form. The idField value will be set by
                // this call
                form.copyFrom(equipmentType);
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
            EquipmentType equipmentType = null;
            //local variable, don't confuse it with the public id parameter of the page
            Integer _id = (Integer) idField.getValueObject();
            if (_id != null) {
                equipmentType = Cayenne.objectForPK(context,EquipmentType.class, _id);
            } else {
                isNew = true;
                equipmentType = context.newObject(EquipmentType.class);
            }

            form.copyTo(equipmentType);
            //We need transation?
            context.commitChanges();
            System.out.println("\n context.commitChanges() was triggered \n");
            //The referrerField HiddenField was set on GET request
            String _referrer = referrerField.getValue();
            if (StringUtils.isNotBlank(_referrer)) {
                setRedirect(_referrer);
                System.out.println("\n setRedirect(_referrer)["+_referrer+"] was triggered \n");
            } else {
                setRedirect(ViewEquipmentType.class);
                System.out.println("\n setRedirect(ViewEquipmentType.class) was triggered \n");
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
        if (StringUtils.isNotBlank(_referrer)) {
            setRedirect(_referrer);
        } else {
            setRedirect(ViewEquipmentType.class);
        }
        return true;
    }
}

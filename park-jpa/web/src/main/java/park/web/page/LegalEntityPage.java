/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.web.page;

import org.apache.click.Page;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import org.apache.click.extras.control.EmailField;
import org.apache.click.util.Bindable;
import park.model.LegalEntity;

/**
 *
 * @author gilberto
 */
public class LegalEntityPage extends BorderPage{
    @Bindable protected Form form = new Form();
    @Bindable protected String msg;

    private Select investmentSelect = new Select("investment");

    // -------------------------------------------------------- Constructor

    public LegalEntityPage() {
        FieldSet fieldSet = new FieldSet("LegalEntity");
        form.add(fieldSet);

        TextField nameField = new TextField("name", true);
        nameField.setMinLength(5);
        nameField.setFocus(true);
        fieldSet.add(nameField);

        fieldSet.add(new EmailField("email", true));

        fieldSet.add(investmentSelect);

        fieldSet.add(new DateField("dateJoined", true));
        fieldSet.add(new Checkbox("active"));

        form.add(new Submit("ok", " OK ", this, "onOkClicked"));
        form.add(new Submit("cancel", this, "onCancelClicked"));
    }

    // ----------------------------------------------------- Event Handlers

    /**
     * @see Page#onInit()
     */
    @Override
    public void onInit() {
        investmentSelect.add(Option.EMPTY_OPTION);
        investmentSelect.addAll(em.createNamedQuery("LegalEntity.findAll").getResultList());
    }

    /**
     * Handle the OK button click event.
     *
     * @return true
     */
    public boolean onOkClicked() {
        if (form.isValid()) {
            LegalEntity legalEntity = new LegalEntity();
            form.copyTo(legalEntity);

            em.persist(legalEntity);

            form.clearValues();

            msg = "A new legalEntity record has been created.";
        }
        return true;
    }

    /**
     * Handle the Cancel button click event.
     *
     * @return false
     */
    public boolean onCancelClicked() {
        setRedirect(HomePage.class);
        return false;
    }
}
/*
 * To change this tgetEntityManager()plate, choose Tools | TgetEntityManager()plates
 * and open the tgetEntityManager()plate in the editor.
 */

package park.web.page;

import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.LegalEntity;

/**
 *
 * @author gilberto
 */
public class LegalEntityPage extends BorderPage{
    protected Form form = new Form();
    protected Table table = new Table();
    protected String msg;
    private Select investmentSelect = new Select("investment");

    // -------------------------------------------------------- Constructor

    public LegalEntityPage() {
        form.setName("form");
        table.setName("table");
        addControl(table);
        addControl(form);
        FieldSet fieldSet = new FieldSet("LegalEntity");
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

         table.setClass(Table.CLASS_ITS);
         table.setPageSize(4);
         table.setShowBanner(true);
         table.setSortable(true);

         table.addColumn(new Column("idPerson"));
         table.addColumn(new Column("name"));
         table.addColumn(new Column("address"));
         table.addColumn(new Column("dtFoundation"));

         //column = new Column("Action");
         //column.setDecorator(new LinkDecorator(table, deleteLink, "id"));
         //column.setSortable(false);
         //table.addColumn(column);



        //fieldSet.add(new Checkbox("active"));

        form.add(new Submit("ok", " OK ", this, "onOkClicked"));
        form.add(new Submit("cancel", this, "onCancelClicked"));
    }

    // ----------------------------------------------------- Event Handlers

    /**
     * @see Page#onInit()
     */
    @Override
    public void onInit() {
        //investmentSelect.add(Option.EMPTY_OPTION);
        //investmentSelect.addAll(getEntityManager().createNamedQuery("LegalEntity.findAll").getResultList());
    }

    @Override
    public void onRender() {
         table.setRowList(getEntityManager().createNamedQuery("LegalEntity.findAll").getResultList());
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

            getEntityManager().getTransaction().begin();
            getEntityManager().persist(legalEntity);
            getEntityManager().getTransaction().commit();
            
            form.clearValues();

            msg = "A new legalEntity record has been created";
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
package park.web.page;

import javax.persistence.EntityManager;
import org.apache.click.control.Column;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.DateField;
import park.model.LegalEntity;
import park.service.util.JpaHelper;

/**
 *
 * @author gilberto
 */
public class LegalEntityPage extends BorderPage {

    protected Form form = new Form();
    protected Table table = new Table();
    protected String msg;

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

        table.addColumn(new Column("id"));
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
    @Override
    public void onRender() {
        table.setRowList(JpaHelper.getEntityManager().createNamedQuery("LegalEntity.findAll").getResultList());
    }

    /**
     * Handle the OK button click event.
     *
     * @return true
     */
    public boolean onOkClicked() throws Exception {
        EntityManager em = null;
        if (form.isValid()) {
            LegalEntity legalEntity = new LegalEntity();
            form.copyTo(legalEntity);
            try {
                em = JpaHelper.getEntityManager();
                em.getTransaction().begin();
                em.persist(legalEntity);
                em.getTransaction().commit();
            } catch (Exception ex) {
                //I don't know what to do here right now!
                throw ex;
            } finally {
                if (em != null) {
                    em.close();
                }
            }

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
        setRedirect(LegalEntityPage.class);
        return false;
    }
}

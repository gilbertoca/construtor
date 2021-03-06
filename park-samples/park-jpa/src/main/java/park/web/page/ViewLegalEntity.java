package park.web.page;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.click.control.AbstractLink;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Form;
import org.apache.click.control.PageLink;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.extras.control.TableInlinePaginator;
import org.apache.commons.lang.NotImplementedException;
import park.model.LegalEntity;
import park.service.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class ViewLegalEntity extends BorderPage {

    private static final long serialVersionUID = 1L;
    protected Form form = new Form("form");
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("editLink", EditLegalEntity.class);
    protected ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    private TextField nameField = new TextField("nameField");
    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor ------------------------------------------------------------
    public ViewLegalEntity() {
        System.out.println("\n ViewLegalEntity() method \n");

        getModel().put("title", getMessage("viewLegalEntity.title"));
        getModel().put("heading", getMessage("viewLegalEntity.heading"));

        addControl(form);
        addControl(table);
        addControl(deleteLink);
        addControl(editLink);
        //setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(nameField);
        form.add(new Submit("searchBt"));
        form.add(new Submit("clearBt", this, "onClearClick"));
        form.add(new Submit("newBt", this, "onNewClick"));

        // Setup LegalEntitys table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        Column column = new Column("name");
        table.addColumn(column);
        column = new Column("taxpayersId");
        table.addColumn(column);
        column = new Column("dtFoundation");
        column.setFormat("{0,date,dd/MM/yyyy}");
        table.addColumn(column);

        //editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setParameter("referrer", "/view-legal-entity.htm");

        //deleteLink.setImageSrc("/assets/images/table-delete.png");
        //deleteLink.setAttribute("onclick", "return window.confirm('Are you sure you want to delete this record?');");
        deleteLink.setAttribute("onclick", getMessage("deleteLink.attribute.onclick"));

        column = new Column("action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);

        table.setDataProvider(new DataProvider<LegalEntity>() {

            public List<LegalEntity> getData() {
                return (List<LegalEntity>) findByName();
            }
        });
    }

    private List findByName() {
        System.out.println("\n findByName() method \n");
        Query queryObject = em.createNamedQuery("LegalEntity.findByName");
        queryObject.setParameter("name", "%" + nameField.getValue() + "%");
        return queryObject.getResultList();
    }
    // Event Handlers ---------------------------------------------------------

    /**
     * Handle the clear button click event.
     *
     * @return true
     */
    public boolean onClearClick() {
        System.out.println("\n onClearClick() method \n");
        form.clearErrors();
        form.clearValues();
        return true;
    }

    /**
     * Handle the new button click event.
     *
     * @return false
     */
    public boolean onNewClick() {
        System.out.println("\n onNewClick() method \n");
        String path = getContext().getPagePath(EditLegalEntity.class);
        path += "?referrer=/view-legal-entity.htm";
        setRedirect(path);
        return false;
    }

    /**
     * Handle the delete link click event.
     *
     * @return true
     */
    public boolean onDeleteClick() throws Exception {
        System.out.println("\n onDeleteClick() method \n");
        Long _id = deleteLink.getValueLong();
        if (_id != null) {
            delete(_id);
        } else {
            throw new NotImplementedException();
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

    private void delete(Long id) throws Exception {
        //We need transation
        try {
            em.getTransaction().begin();
            em.remove(em.find(LegalEntity.class, id));
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
    }
}

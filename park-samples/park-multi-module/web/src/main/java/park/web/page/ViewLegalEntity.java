package park.web.page;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import park.service.util.JpaHelper;

/**
 *
 * @author gilberto
 */
public class ViewLegalEntity extends BorderPage {

    private static final long serialVersionUID = 1L;
    protected Form form = new Form("form");
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("Edit", EditLegalEntity.class);
    protected ActionLink deleteLink = new ActionLink("Delete", this, "onDeleteClick");
    private TextField nameField = new TextField("name");

    // Constructor ------------------------------------------------------------
    public ViewLegalEntity() {
        getModel().put("title", getMessage("viewLegalEntity.title"));
        getModel().put("heading", getMessage("viewLegalEntity.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        addControl(table);
        setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(nameField);
        form.add(new Submit("Search"));
        form.add(new Submit("Clear", this, "onClearClick"));
        form.add(new Submit("New...", this, "onNewClick"));

        // Setup LegalEntitys table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        table.addColumn(new Column("id"));

        table.addColumn(new Column("name"));

        table.addColumn(new Column("dtFoundation"));

        editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setTitle("Edit LegalEntity details");
        editLink.setParameter("referrer", "/view-legal-entity.htm");

        deleteLink.setImageSrc("/assets/images/table-delete.png");
        deleteLink.setTitle("Delete LegalEntity record");
        deleteLink.setAttribute("onclick", "return window.confirm('Are you sure you want to delete this record?');");

        Column column = new Column("Action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);
        System.out.println("%" + nameField.getValue() + "%");
        table.setDataProvider(new DataProvider<LegalEntity>() {

            public List<LegalEntity> getData() {
                return JpaHelper.namedQuery("LegalEntity.findByName", "name", "%" + nameField.getValue() + "%");
            }
        });
    }

    // Event Handlers ---------------------------------------------------------
    /**
     * Handle the clear button click event.
     *
     * @return true
     */
    public boolean onClearClick() {
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
    public boolean onDeleteClick() {
        Long id = deleteLink.getValueLong();
        if (id != null) {
            JpaHelper.delete("LegalEntity.deleteById", "id", id);
        } else {
            throw new NotImplementedException(); 
        }
        return true;
    }
}

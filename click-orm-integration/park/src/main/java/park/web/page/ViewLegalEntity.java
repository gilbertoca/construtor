package park.web.page;

import com.google.constructor.extras.orm.jpa.BaseJPAService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author gilberto
 */
public class ViewLegalEntity extends BorderPage {

    private static final long serialVersionUID = 1L;

    protected Form form = new Form("form");
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("edit", getMessage("viewLegalEntity.edit"), EditLegalEntity.class);
    protected ActionLink deleteLink = new ActionLink("delete", getMessage("viewLegalEntity.delete"), this, "onDeleteClick");
    private TextField nameField = new TextField("name", getMessage("viewLegalEntity.name"));

    private BaseJPAService<LegalEntity, Long> legalEntityService;

    // Constructor ------------------------------------------------------------
    public ViewLegalEntity() {
        legalEntityService = new BaseJPAService<LegalEntity, Long>(LegalEntity.class);
        getModel().put("title", getMessage("viewLegalEntity.title"));
        getModel().put("heading", getMessage("viewLegalEntity.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        addControl(table);
        setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(nameField);
        form.add(new Submit("search",getMessage("viewLegalEntity.search")));
        form.add(new Submit("clear", getMessage("viewLegalEntity.clear"), this, "onClearClick"));
        form.add(new Submit("new", getMessage("viewLegalEntity.new"), this, "onNewClick"));

        // Setup LegalEntitys table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        table.addColumn(new Column("name",getMessage("viewLegalEntity.column.name")));

        table.addColumn(new Column("dtFoundation",getMessage("viewLegalEntity.column.dtFoundation")));

        editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setTitle(getMessage("viewLegalEntity.edit.title"));
        editLink.setParameter("referrer", "/view-legal-entity.htm");

        deleteLink.setImageSrc("/assets/images/table-delete.png");
        deleteLink.setTitle(getMessage("viewLegalEntity.delete.title"));
        deleteLink.setAttribute("onclick", "return window.confirm('"+getMessage("viewLegalEntity.delete.attribute")+"')");

        Column column = new Column("Action",getMessage("viewLegalEntity.column.action"));
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);
        System.out.println("%" + nameField.getValue() + "%");
        table.setDataProvider(new DataProvider<LegalEntity>() {

            public List<LegalEntity> getData() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "%" + nameField.getValue() + "%");
                return (List<LegalEntity>) legalEntityService.findByNamedQuery("LegalEntity.findByName", map);
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
            legalEntityService.delete(id);
        } else {
            throw new NotImplementedException(); 
        }
        return true;
    }
}

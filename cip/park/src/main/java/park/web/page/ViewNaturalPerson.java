package park.web.page;

import com.google.constructor.cip.orm.jpa.BaseJPAService;
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
import park.model.NaturalPerson;

public class ViewNaturalPerson extends park.web.page.BorderPage {

     private static final long serialVersionUID = 1L;

    protected Form form = new Form("form");
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("editLink", EditNaturalPerson.class);
    protected ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    private TextField nameField = new TextField("nameField");

    private BaseJPAService<NaturalPerson, Long> naturalPersonService;

    // Constructor ------------------------------------------------------------
    public ViewNaturalPerson() {
        naturalPersonService = new BaseJPAService<NaturalPerson, Long>(NaturalPerson.class);

        getModel().put("title", getMessage("viewNaturalPerson.title"));
        getModel().put("heading", getMessage("viewNaturalPerson.heading"));
        getModel().put("menu", "userMenu");

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

        // Setup NaturalPersons table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        Column column =  new Column("name");
        table.addColumn(column);
        column = new Column("legalDocument");
        table.addColumn(column);
        column = new Column("dtBirth");
        column.setFormat("{0,date,dd/MM/yyyy}");
        table.addColumn(column);

        //editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setParameter("referrer", "/view-natural-person.htm");

        //deleteLink.setImageSrc("/assets/images/table-delete.png");
        //deleteLink.setAttribute("onclick", "return window.confirm('Are you sure you want to delete this record?');");
        deleteLink.setAttribute("onclick", getMessage("deleteLink.attribute.onclick"));

        column = new Column("action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);

        table.setDataProvider(new DataProvider<NaturalPerson>() {

            public List<NaturalPerson> getData() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "%" + nameField.getValue() + "%");
                return (List<NaturalPerson>) naturalPersonService.findByNamedQuery("NaturalPerson.findByName", map);
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
        String path = getContext().getPagePath(EditNaturalPerson.class);
        path += "?referrer=/view-natural-person.htm";
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
            naturalPersonService.delete(id);
        } else {
            throw new NotImplementedException();
        }
        return true;
    }
}

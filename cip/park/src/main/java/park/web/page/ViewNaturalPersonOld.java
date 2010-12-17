package park.web.page;

import com.google.constructor.cip.orm.jpa.BaseJPAService;
import java.util.Collection;
import java.util.List;
import org.apache.click.control.AbstractLink;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.PageLink;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.extras.control.TableInlinePaginator;
import org.apache.commons.lang.NotImplementedException;
import park.model.orm.NaturalPerson;

public class ViewNaturalPersonOld extends park.web.page.BorderPage {

    private static final long serialVersionUID = 1L;
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("editLink", EditNaturalPerson.class);
    protected ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    private BaseJPAService<NaturalPerson, Long> naturalPersonService;

    // Constructor ------------------------------------------------------------
    public ViewNaturalPersonOld() {
        naturalPersonService = new BaseJPAService<NaturalPerson, Long>(NaturalPerson.class);

        getModel().put("title", getMessage("viewNaturalPersonOld.title"));
        getModel().put("heading", getMessage("viewNaturalPersonOld.heading"));
        getModel().put("menu", "userMenu");

        addControl(table);
        addControl(deleteLink);
        addControl(editLink);
        //setStateful(true);

        // Setup NaturalPersons table
        table.setClass(Table.CLASS_COMPLEX);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        Column column = new Column("name");
        table.addColumn(column);
        column = new Column("legalDocument");
        table.addColumn(column);
        column = new Column("dtBirth");
        column.setFormat("{0,date,dd/MM/yyyy}");
        table.addColumn(column);

        editLink.setParameter("referrer", "/view-natural-person-old.htm");
        deleteLink.setAttribute("onclick", getMessage("deleteLink.attribute.onclick"));

        column = new Column("action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);

        /*table.setDataProvider(new DataProvider<NaturalPerson>() {

            public List<NaturalPerson> getData() {
                return (List<NaturalPerson>) naturalPersonService.getAll();
            }
        });*/
    }

    // Event Handlers ---------------------------------------------------------
    @Override
    public void onRender() {
        List nPl = (List<NaturalPerson>) naturalPersonService.getAll();
        addModel("naturalPersonList", nPl);
        table.setRowList(nPl);

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

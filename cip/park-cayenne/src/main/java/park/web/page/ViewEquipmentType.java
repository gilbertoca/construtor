package park.web.page;

import park.model.EquipmentType;
import java.util.List;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.query.SelectQuery;
import org.apache.click.control.AbstractLink;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.PageLink;
import org.apache.click.control.Table;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.extras.control.TableInlinePaginator;
import org.apache.commons.lang.NotImplementedException;

public class ViewEquipmentType extends org.apache.click.Page {

    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("editLink", EditEquipmentType.class);
    protected ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    private ObjectContext context = DataContext.getThreadObjectContext();

    // Constructor ------------------------------------------------------------
    public ViewEquipmentType() {
        System.out.println("\n ViewEditEquipmentType() method \n");

        addControl(table);
        addControl(deleteLink);
        addControl(editLink);
        //setStateful(true);

        // Setup EditEquipmentTypes table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        Column column = new Column("description");
        table.addColumn(column);

        //editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setParameter("referrer", "/view-equipment-type.htm");

        //deleteLink.setImageSrc("/assets/images/table-delete.png");
        deleteLink.setAttribute("onclick", "return window.confirm('Are you sure you want to delete this record?');");

        column = new Column("action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "id"));
        column.setSortable(false);
        table.addColumn(column);

        table.setDataProvider(new DataProvider<EquipmentType>() {

            public List<EquipmentType> getData() {
                return (List<EquipmentType>) getAll();
            }
        });
    }

    private List getAll() {
        System.out.println("\n getAll() method \n");
        SelectQuery query = new SelectQuery(EquipmentType.class);
        List equipmentTypes = context.performQuery(query);
        return equipmentTypes;

    }
    // Event Handlers ---------------------------------------------------------


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

    private void delete(Long id) throws Exception {
        //We need transation
        context.deleteObject(Cayenne.objectForPK(context,EquipmentType.class, id));
        context.commitChanges();
    }
}

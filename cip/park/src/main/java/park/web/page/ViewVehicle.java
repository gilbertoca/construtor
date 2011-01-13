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
import park.model.orm.Vehicle;

/**
 *
 * @author gilberto
 */
public class ViewVehicle extends BorderPage {

    private static final long serialVersionUID = 1L;

    private Form form = new Form("form");
    private Table table = new Table("table");
    //private PageLink editLink = new PageLink("editLink", EditVehicle.class);
    private ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    private TextField nameField = new TextField("nameField");

    private BaseJPAService<Vehicle, String> vehicleService;

    // Constructor ------------------------------------------------------------
    public ViewVehicle() {
        vehicleService = new BaseJPAService<Vehicle, String>(Vehicle.class);

        getModel().put("title", getMessage("viewVehicle.title"));
        getModel().put("heading", getMessage("viewVehicle.heading"));
        getModel().put("menu", "userMenu");

        addControl(form);
        addControl(table);
        addControl(deleteLink);
        //addControl(editLink);
        //setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(nameField);
        form.add(new Submit("searchBt"));
        form.add(new Submit("clearBt", this, "onClearClick"));
        form.add(new Submit("newBt", this, "onNewClick"));

        // Setup Vehicles table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        Column column =  new Column("licensePlate");
        table.addColumn(column);
        column = new Column("color");
        table.addColumn(column);
        column = new Column("customer.name");
        table.addColumn(column);
        column = new Column("priceTable.price");
        table.addColumn(column);
        column = new Column("vehicleType.type");
        table.addColumn(column);

        //editLink.setImageSrc("/assets/images/table-edit.png");
        //editLink.setParameter("referrer", "/view-legal-entity.htm");

        //deleteLink.setImageSrc("/assets/images/table-delete.png");
        //deleteLink.setAttribute("onclick", "return window.confirm('Are you sure you want to delete this record?');");
        deleteLink.setAttribute("onclick", getMessage("deleteLink.attribute.onclick"));

        column = new Column("action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{null, deleteLink};
        column.setDecorator(new LinkDecorator(table, links, "licensePlate"));
        column.setSortable(false);
        table.addColumn(column);

        table.setDataProvider(new DataProvider<Vehicle>() {

            public List<Vehicle> getData() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "%" + nameField.getValue() + "%");
                return (List<Vehicle>) vehicleService.findByNamedQuery("Vehicle.findByName", map);
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
        //String path = getContext().getPagePath(EditVehicle.class);
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
        String id = deleteLink.getValue();
        if (id != null) {
            vehicleService.delete(id);
        } else {
            throw new NotImplementedException();
        }
        return true;
    }
}

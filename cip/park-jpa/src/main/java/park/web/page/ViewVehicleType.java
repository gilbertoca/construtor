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
import park.model.orm.VehicleType;
import park.orm.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class ViewVehicleType extends BorderPage {

    private static final long serialVersionUID = 1L;

    protected Form form = new Form("form");
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("editLink", EditVehicleType.class);
    protected ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    protected TextField vehicleTypeField = new TextField("vehicleType");

    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor ------------------------------------------------------------
    public ViewVehicleType() {
        System.out.println("\n ViewVehicleType() method \n");

        getModel().put("title", getMessage("viewVehicleType.title"));
        getModel().put("heading", getMessage("viewVehicleType.heading"));

        addControl(form);
        addControl(table);
        addControl(deleteLink);
        addControl(editLink);
        //setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(vehicleTypeField);
        form.add(new Submit("searchBt"));
        form.add(new Submit("clearBt", this, "onClearClick"));
        form.add(new Submit("newBt", this, "onNewClick"));

        // Setup VehicleTypes table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);
        table.setPaginator(new TableInlinePaginator(table));
        table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

        Column columnVehicleType =  new Column("vehicleType");
        table.addColumn(columnVehicleType);
        Column columnManufacturer = new Column("manufacturer");
        table.addColumn(columnManufacturer);
        Column columnModel = new Column("model");
        table.addColumn(columnModel);

        //editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setParameter("referrer", "/view-vehicle-type.htm");

        //deleteLink.setImageSrc("/assets/images/table-delete.png");
        deleteLink.setAttribute("onclick", getMessage("deleteLink.attribute.onclick"));

        Column columnAction = new Column("action");
        columnAction.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        columnAction.setDecorator(new LinkDecorator(table, links, "vehicleType"));
        columnAction.setSortable(false);
        table.addColumn(columnAction);

        table.setDataProvider(new DataProvider<VehicleType>() {
            public List<VehicleType> getData() {
                return (List<VehicleType>) findByVehicleType();
            }
        });
    }
    
   private List findByVehicleType(){
        System.out.println("\n findByLicensePlate() method \n");
        Query queryObject = em.createNamedQuery("VehicleType.findByVehicleType");
        queryObject.setParameter("vehicleType",  "%" + vehicleTypeField.getValue() + "%");
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
        String path = getContext().getPagePath(EditVehicleType.class);
        path += "?referrer=/view-vehicle-type.htm";
        setRedirect(path);
        return false;
    }

    /**
     * Handle the delete link click event.
     *
     * @return true
     */
    public boolean onDeleteClick() {
        System.out.println("\n onDeleteClick() method \n");
        String _id = deleteLink.getValue();
        if (_id != null) {
            //We need transation
            try {
                em.getTransaction().begin();
                em.remove(em.find(VehicleType.class, _id));
                em.getTransaction().commit();
            } finally {
                em.close();
                //return false;
            }
        } else {
            throw new NotImplementedException();
        }
        return true;
    }
    
    @Override
    public void onDestroy() {
        System.out.println("\n onDestroy() method \n");
        super.onDestroy();
        if(em.isOpen()){
            em.close();
        }
    }

}

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
import park.model.Vehicle;
import park.service.util.EntityManagerContext;

/**
 *
 * @author gilberto
 */
public class ViewVehicle extends BorderPage {

    private static final long serialVersionUID = 1L;

    protected Form form = new Form("form");
    protected Table table = new Table("table");
    protected PageLink editLink = new PageLink("editLink", EditVehicle.class);
    protected ActionLink deleteLink = new ActionLink("deleteLink", this, "onDeleteClick");
    protected TextField licensePlateField = new TextField("licensePlateField");

    private EntityManager em = EntityManagerContext.getEntityManager();

    // Constructor ------------------------------------------------------------
    public ViewVehicle() {
        System.out.println("\n ViewVehicle() method \n");

        getModel().put("title", getMessage("viewVehicle.title"));
        getModel().put("heading", getMessage("viewVehicle.heading"));

        addControl(form);
        addControl(table);
        addControl(deleteLink);
        addControl(editLink);
        //setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(licensePlateField);
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

        Column columnLicensePlate =  new Column("licensePlate");
        table.addColumn(columnLicensePlate);
        Column columnColor = new Column("color");
        table.addColumn(columnColor);
        Column columnCustomerName = new Column("customer.person.name");
        table.addColumn(columnCustomerName);
        Column columnPrice = new Column("priceTable.price");
        table.addColumn(columnPrice);
        Column columnVType = new Column("vehicleType.vehicleType");
        table.addColumn(columnVType);

        //editLink.setImageSrc("/assets/images/table-edit.png");
        editLink.setParameter("referrer", "/view-vehicle.htm");

        //deleteLink.setImageSrc("/assets/images/table-delete.png");
        //deleteLink.setAttribute("onclick", "return window.confirm('Are you sure you want to delete this record?');");
        deleteLink.setAttribute("onclick", getMessage("deleteLink.attribute.onclick"));

        Column columnAction = new Column("action");
        columnAction.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[]{editLink, deleteLink};
        columnAction.setDecorator(new LinkDecorator(table, links, "licensePlate"));
        columnAction.setSortable(false);
        table.addColumn(columnAction);
        //table.setRowList(findByLicensePlate());
        
        table.setDataProvider(new DataProvider<Vehicle>() {
            public List<Vehicle> getData() {
                System.out.println("\n getData() method which will call findByLicensePlate() \n");
                return (List<Vehicle>) findByLicensePlate();
            }
        });
    }
    
   private List findByLicensePlate(){
        System.out.println("\n findByLicensePlate() method \n");
        Query queryObject = em.createNamedQuery("Vehicle.findByLicensePlate");
        queryObject.setParameter("licensePlate",  "%" + licensePlateField.getValue() + "%");
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
        String path = getContext().getPagePath(EditVehicle.class);
        path += "?referrer=/view-vehicle.htm";
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
                em.remove(em.find(Vehicle.class, _id));
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

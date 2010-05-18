package park.web.page;

import java.util.List;
import org.apache.click.Page;
import org.apache.click.control.AbstractLink;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.PageLink;
import org.apache.click.control.Table;
import org.apache.click.extras.control.LinkDecorator;
import park.model.LegalEntity;
import park.service.util.JpaHelper;

/**
 *
 * @author gilberto
 */
public class ViewLegalEntity extends BorderPage {
    private static final long serialVersionUID = 1L;
	public Table table = new Table();
	public PageLink editLink = new PageLink("Edit", EditLegalEntity.class);
	public ActionLink deleteLink = new ActionLink("Delete", this, "onDeleteClick");

	// ------------------------------------------------------------ Constructor

	public ViewLegalEntity() {
		// Set Page to stateful to preserve Table sort and paging state while
		// editing legalEntitys
		// NOTE: stateful pages doesn't work inside GAE, disabling for now
		// setStateful(true);

		table.setClass(Table.CLASS_ORANGE1);
		table.setPageSize(10);
		table.setShowBanner(true);
		table.setSortable(true);

		table.addColumn(new Column("id"));

		table.addColumn(new Column("name"));

		table.addColumn(new Column("dtFoundation"));

		editLink.setImageSrc("/assets/images/table-edit.png");
		editLink.setTitle("Edit legalEntity details");
		editLink.setParameter("referrer", "/view-legalEntitys.htm");

		deleteLink.setImageSrc("/assets/images/table-delete.png");
		deleteLink.setTitle("Delete legalEntity record");
		deleteLink.setAttribute("onclick","return window.confirm('Are you sure you want to delete this record?');");

		Column column = new Column("Action");
		column.setTextAlign("center");
		AbstractLink[] links = new AbstractLink[] { editLink, deleteLink };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		column.setSortable(false);
		table.addColumn(column);
	}

	// --------------------------------------------------------- Event Handlers

	public boolean onDeleteClick() {
		Integer id = deleteLink.getValueInteger();
		JpaHelper.delete((LegalEntity) JpaHelper.retrieve(LegalEntity.class, id));
		return true;
	}

	/**
	 * @see Page#onRender()
	 */
	@Override
	public void onRender() {
		List list = JpaHelper.getEntityManager().createNamedQuery("LegalEntity.findAll").getResultList();
		table.setRowList(list);
	}
}

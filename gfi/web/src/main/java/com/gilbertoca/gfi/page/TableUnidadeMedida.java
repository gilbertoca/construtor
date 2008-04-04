package com.gilbertoca.gfi.page;

import java.io.Serializable;
import java.util.List;

import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.PageLink;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;

import com.gilbertoca.gfi.inventario.service.UnidadeMedidaService;
import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

/**
 * Provides an demonstration of Table control paging.
 *
 * @author Malcolm Edgar
 */
public class TableUnidadeMedida extends BorderPage implements Serializable {

    private static final long serialVersionUID = 1L;

    public Form form = new Form();
    public Table table = new Table();
    public PageLink editLink = new PageLink("Edit", EditUnidadeMedida.class);
    public ActionLink deleteLink = new ActionLink("Delete", this, "onDeleteClick");

    private TextField descricaoUnidadeField = new TextField("Descrição");

    // ----------------------------------------------------------- Constructors

    public TableUnidadeMedida() {
    	setService(new UnidadeMedidaService());
        setStateful(true);

        // Setup the search form
        form.setColumns(2);
        form.add(descricaoUnidadeField);
        form.add(new Submit("Search"));
        form.add(new Submit("Clear", this, "onClearClick"));
        //form.add(new Button("0   "));
        form.add(new Submit("New...", this, "onNewClick"));

        // Setup customers table
        table.setClass(Table.CLASS_ITS);
        table.setPageSize(10);
        table.setShowBanner(true);
        table.setSortable(true);

        table.addColumn(new Column("cdUnidadeMedida", "Unidade"));
        table.addColumn( new Column("descricaoUnidade","Descrição"));
        editLink.setImageSrc("/images/window-edit.png");
        editLink.setTitle("Edit customer details");
        editLink.setParameter("referrer", "/table-unidade-medida.htm");

        deleteLink.setImageSrc("/images/window-delete.png");
        deleteLink.setTitle("Delete customer record");
        deleteLink.setAttribute("onclick", "return window.confirm('Tem certeza de que deseja excluir este registro?');");

        Column column = new Column("Action");
        column.setTextAlign("center");
        AbstractLink[] links = new AbstractLink[] { editLink, deleteLink };
        column.setDecorator(new LinkDecorator(table, links, "cdUnidadeMedida"));
        column.setSortable(false);
        table.addColumn(column);
    }

    // --------------------------------------------------------- Event Handlers

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
        String path = getContext().getPagePath(EditUnidadeMedida.class);
        path += "?referrer=/table-unidade-medida.htm";
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
        log.debug("Link Delete pressionado, argumento utilizado: {}", id);
        getService().deleteByPk(id);
        return true;
    }

    /**
     * @see net.sf.click.Page#onRender()
     */
    public void onRender() {
    	log.debug("Preparando a página para ser exibida.");
        List uMs = (List) getService().findLike(new UnidadeMedida(null, descricaoUnidadeField.getValue()));
        table.setRowList(uMs);
    }
}
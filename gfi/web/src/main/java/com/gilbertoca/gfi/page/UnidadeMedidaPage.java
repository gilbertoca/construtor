package com.gilbertoca.gfi.page;

import java.util.Iterator;
import java.util.List;

import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.FieldColumn;
import net.sf.click.extras.control.FormTable;

import com.gilbertoca.gfi.inventario.service.UnidadeMedidaService;
import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

/**
 * Provides an demonstration of Table control paging.
 *
 * @author Malcolm Edgar
 */
public class UnidadeMedidaPage extends BorderPage {

    private static final int NUM_ROWS = 20;

    public FormTable table = new FormTable();

    // ------------------------------------------------------------ Constructor

    public UnidadeMedidaPage() {
    	setService(new UnidadeMedidaService());
        // Setup customers table
        table.setClass(Table.CLASS_SIMPLE);
        table.setWidth("700px");
        table.getForm().setButtonAlign(Form.ALIGN_RIGHT);
        table.setPageSize(10);
        table.setShowBanner(true);

        table.addColumn(new Column("cdUnidadeMedida"));

        FieldColumn column = new FieldColumn("descricaoUnidade", new TextField());
        column.getField().setRequired(true);
        column.setVerticalAlign("baseline");
        table.addColumn(column);


        table.getForm().add(new Submit("ok", "  OK  ", this, "onOkClick"));
        table.getForm().add(new Submit("cancel", this, "onCancelClick"));
    }

    // --------------------------------------------------------- Event Handlers

    /**
     * @see net.sf.click.Page#onInit()
     */
    public void onInit() {
        List uMs = (List) getService().findAll();
        table.setRowList(uMs);
    }

    public boolean onOkClick() {
        if (table.getForm().isValid()) {
            // With other ORM frameworks like Hibernate you would retrieve
            // rows for the table as persist those objects. For example:
            
            List rowList = table.getRowList();
            for (Iterator i = rowList.iterator(); i.hasNext();) {
            	UnidadeMedida row = (UnidadeMedida) i.next();
                getService().update(row);
            }
            
        }
        return true;
    }

    public boolean onCancelClick() {
        List uMs = (List) getService().findAll();

        table.setRowList(uMs);
        table.setRenderSubmittedValues(false);

        return true;
    }

}

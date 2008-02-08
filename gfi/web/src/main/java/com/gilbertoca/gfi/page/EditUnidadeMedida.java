package com.gilbertoca.gfi.page;
import net.sf.click.Page;
import net.sf.click.control.FieldSet;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.gilbertoca.gfi.inventario.service.UnidadeMedidaService;
import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

/**
 * Provides an edit Customer Form example. The Customer business object
 * is initially passed to this Page as a request attribute.
 * <p/>
 * Note the public visibility "referrer" HiddenField and the "id" field
 * have their value automatically set with any identically named request
 * parameters after the page is created.
 *
 * @author Malcolm Edgar
 */

public class EditUnidadeMedida extends BorderPage {

	    // Public controls are automatically added to the page
	    public Form form = new Form("form");
	    public HiddenField referrerField = new HiddenField("referrer", String.class);

	    // Public variables can automatically have their value set by request parameters
	    public String cdUnidadeMedida;

	    private HiddenField idField = new HiddenField("cdUnidadeMedida", String.class);

	    public EditUnidadeMedida() {
	    	setService(new UnidadeMedidaService());
	        form.add(referrerField);

	        form.add(idField);

	        FieldSet fieldSet = new FieldSet("Unidade Medida");
	        form.add(fieldSet);

	        TextField cdUnidadeMedidaField = new TextField("cdUnidadeMedida", true);
	        cdUnidadeMedidaField.setMinLength(5);
	        cdUnidadeMedidaField.setFocus(true);
	        fieldSet.add(cdUnidadeMedidaField);

	        TextField descricaoUnidadeField = new TextField("descricaoUnidade", true);
	        descricaoUnidadeField.setMinLength(100);
	        descricaoUnidadeField.setFocus(true);
	        fieldSet.add(descricaoUnidadeField);

	        form.add(new Submit("ok", "  OK  ", this, "onOkClick"));
	        form.add(new Submit("cancel", this, "onCancelClick"));
	    }

	    /**
	     * When page is first displayed on the GET request.
	     *
	     * @see Page#onGet()
	     */
	    public void onGet() {
	        if (cdUnidadeMedida != null) {
	        	UnidadeMedida unidadeMedida = (UnidadeMedida) getService().findByPk(cdUnidadeMedida);

	            if (unidadeMedida != null) {
	                form.copyFrom(unidadeMedida);
	            }
	        }
	    }

	    public boolean onOkClick() {
	        if (form.isValid()) {
	            String id = idField.getValue();
	            UnidadeMedida unidadeMedida = (UnidadeMedida) getService().findByPk(id);

	            form.copyTo(unidadeMedida);

	            getService().update(unidadeMedida);

	            String referrer = referrerField.getValue();
	            if (referrer != null) {
	                setRedirect(referrer);
	            } else {
	                setRedirect(HomePage.class);
	            }

	            return true;

	        } else {
	            return true;
	        }
	    }

	    public boolean onCancelClick() {
	        String referrer = referrerField.getValue();
	        if (referrer != null) {
	            setRedirect(referrer);
	        } else {
	            setRedirect(HomePage.class);
	        }
	        return true;
	    }

	}

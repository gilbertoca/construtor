package com.gilbertoca.gfi.page;

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
    public HiddenField versionField = new HiddenField("version", Integer.class);
    public String cdUnidadeMedida;

    public EditUnidadeMedida() {
        setService(new UnidadeMedidaService());
        form.add(referrerField);
        form.add(versionField);
        FieldSet fieldSet = new FieldSet("Unidade Medida");
        form.add(fieldSet);
        TextField cdUnidadeMedidaField = new TextField("cdUnidadeMedida", true);
        cdUnidadeMedidaField.setMinLength(2);
        cdUnidadeMedidaField.setFocus(true);
        fieldSet.add(cdUnidadeMedidaField);
        TextField descricaoUnidadeField = new TextField("descricaoUnidade", true);
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
        	form.getField("cdUnidadeMedida").setReadonly(true);
            UnidadeMedida unidadeMedida = (UnidadeMedida) getService().findByPk(cdUnidadeMedida);
            if (unidadeMedida != null) {
                form.copyFrom(unidadeMedida);
            }
        }
    }

    public boolean onOkClick() {
        if (form.isValid()) {
            UnidadeMedida unidadeMedida = new UnidadeMedida();
            form.copyTo(unidadeMedida);
            if (unidadeMedida.getVersion() != -1) {
                unidadeMedida = (UnidadeMedida) getService().findByPk(cdUnidadeMedida);
            	getService().update(unidadeMedida);
            }else{
            	getService().insert(unidadeMedida);            	
            }
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

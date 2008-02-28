package com.gilbertoca.gfi.page;

import net.sf.click.control.FieldSet;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.gilbertoca.gfi.inventario.service.CategoriaService;
import com.gilbertoca.gfi.inventario2.model.Categoria;

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
public class EditCategoria extends BorderPage {
    // Public controls are automatically added to the page
    public Form form = new Form("form");
    public HiddenField referrerField = new HiddenField("referrer", String.class);
    public String cdCategoria;

    public EditCategoria() {
        setService(new CategoriaService());
        form.add(referrerField);
        FieldSet fieldSet = new FieldSet("Unidade Medida");
        form.add(fieldSet);
        TextField cdCategoriaField = new TextField("cdCategoria", true);
        cdCategoriaField.setMinLength(2);
        cdCategoriaField.setFocus(true);
        fieldSet.add(cdCategoriaField);
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
        if (cdCategoria != null) {
            Categoria categoria = (Categoria) getService().findByPk(cdCategoria);
            if (categoria != null) {
                form.copyFrom(categoria);
            }
        }
    }

    public boolean onOkClick() {
        if (form.isValid()) {
            Categoria categoria = new Categoria();            
            if (cdCategoria != null) {
                categoria = (Categoria) getService().findByPk(cdCategoria);
            }
            form.copyTo(categoria,true);
            getService().update(categoria);
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

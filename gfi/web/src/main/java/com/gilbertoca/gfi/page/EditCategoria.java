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
    public HiddenField versionField = new HiddenField("version", Integer.class);
    public String cdCategoria;

    public EditCategoria() {
        setService(new CategoriaService());
        form.add(referrerField);
        form.add(versionField);
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
    	log.debug("Preparando a p�gina para ser exibida.");
    	Categoria categoria;
        if (cdCategoria != null) {
        	log.debug("Identificador encontrado: {}, preparar p�gina para edi��o de entidade",cdCategoria);
        	form.getField("cdCategoria").setReadonly(true);
            categoria = (Categoria) getService().findByPk(cdCategoria);
            if (categoria != null) {
                form.copyFrom(categoria);
                log.debug("Liga��o (Binding) Entidade/Form realizada: {}",categoria);
            }
        }else{
        	categoria = new Categoria();
        	form.copyFrom(categoria);
        }
        	
    }

    public boolean onOkClick() {
    	log.debug("Bot�o OK pressionado");
        if (form.isValid()) {
            Categoria categoria = new Categoria();
            log.debug("Cria��o de um novo objeto: {}",categoria);
            form.copyTo(categoria);
            log.debug("Liga��o (Binding) Form/Entidade realizada: {}",categoria);
            if (categoria.getVersion() != -1) {
            	getService().update(categoria);
            	log.debug("Opera��o de atualiza��o realizada.");
            }else{
            	getService().insert(categoria);            	
            	log.debug("Opera��o de inser��o realizada.");
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
    	form.setJavaScriptValidation(false);
        String referrer = referrerField.getValue();
        if (referrer != null) {
            setRedirect(referrer);
        } else {
            setRedirect(HomePage.class);
        }
        return true;
    }
}

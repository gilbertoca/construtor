package com.gilbertoca.gfi.page;

import net.sf.click.control.FieldSet;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;
import com.gilbertoca.gfi.service.BaseService;

/**
 * Fornece um formulário para edição de Unidade de Medidas. 
 * A entidade UnidadeMedia é inicialmente passada para essa Página como atributo da requisição.
 * <p/>
 * Note que a visibilidade publica dos campos "referrer" e "id" HiddenField têm seus valores automáticamente
 * ajustados com parâmetros de requisição de igual nome depois da criação da Página.
 *
 * @author Gilberto Caetano de Andrade
 */
public class EditUnidadeMedida extends BorderPage {
    // Public controls are automatically added to the page
    public Form form = new Form("form");
    //refere-se à origem do processo
    public HiddenField referrerField = new HiddenField("referrer", String.class);
    //usando o atributo version para determinar: inclusão ou edição
    public HiddenField versionField = new HiddenField("version", Integer.class);
    public String cdUnidadeMedida;

    public EditUnidadeMedida() {
        setIService(new BaseService<UnidadeMedida, String>(UnidadeMedida.class));
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
    	log.debug("Preparando a página para ser exibida.");
    	UnidadeMedida unidadeMedida = new UnidadeMedida();
        if (cdUnidadeMedida != null) {
        	unidadeMedida.setCdUnidadeMedida(cdUnidadeMedida);
        	log.debug("Identificador encontrado: {}, preparar página para edição de entidade",cdUnidadeMedida);
        	form.getField("cdUnidadeMedida").setReadonly(true);
            if (getIService().find(unidadeMedida)) {
                form.copyFrom(unidadeMedida);
                log.debug("Ligação (Binding) Entidade/Form realizada: {}",unidadeMedida);
            }
        }else{
        	form.copyFrom(unidadeMedida);
        }
        	
    }

    public boolean onOkClick() {
    	log.debug("Botão OK pressionado");
        if (form.isValid()) {
            UnidadeMedida unidadeMedida = new UnidadeMedida();
            log.debug("Criação de um objeto temporário: {}",unidadeMedida);
            form.copyTo(unidadeMedida);
            log.debug("Ligação (Binding) Form/Entidade realizada: {}",unidadeMedida);
            if (unidadeMedida.getVersion() != -1) {
            	getIService().update(unidadeMedida);
            	log.debug("Operação de atualização realizada.");
            }else{
            	getIService().insert(unidadeMedida);            	
            	log.debug("Operação de inserção realizada.");
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

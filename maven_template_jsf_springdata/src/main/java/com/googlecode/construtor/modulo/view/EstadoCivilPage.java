package com.googlecode.construtor.modulo.view;

import com.googlecode.construtor.modulo.domain.EstadoCivil;
import com.googlecode.construtor.modulo.service.GroupOneMODULOService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author gilberto.andrade
 */
@ManagedBean
@ViewScoped
public class EstadoCivilPage {
    
    @ManagedProperty("#{groupOneMODULOService}")
    private GroupOneMODULOService groupOneMODULOService;

    private int selectedId;
    private EstadoCivil selectedEstadoCivil;

    /**
     * Executado quando estadoCivilPage.xhtml és acessado
     */
    @PostConstruct
    public void initialize() {
        System.out.println("---- initialize ----");
    }
    
    public List<EstadoCivil> getEstadoCivis() {
        return groupOneMODULOService.findAllEstadoCivil();
    }

    public void loadEstadoCivil() {
        selectedEstadoCivil = groupOneMODULOService.findOneEstadoCivil(selectedId);
    }

    public void addInfoMessage() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String msg = "View action " + (ctx.isPostback() ? "on postback" : "on initial request");
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        ctx.getExternalContext().getFlash().setKeepMessages(true);
    }

    public EstadoCivil getSelectedEstadoCivil() {
        return selectedEstadoCivil;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setGroupOneMODULOService(GroupOneMODULOService groupOneMODULOService) {
        this.groupOneMODULOService = groupOneMODULOService;
    }
}

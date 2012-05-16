package com.gilbertoca.igreja.view.component;

import com.gilbertoca.igreja.service.base.BaseService;
import com.gilbertoca.igreja.view.dialogs.ConfirmationDialog;
import com.gilbertoca.igreja.view.dialogs.ErrorDialog;


/**
 *
 * @author acer
 */
public abstract class Listador<T> extends javax.swing.JDialog{

    private boolean buscando = false;
    private Buscador buscador;
    private String variable;

    protected abstract void modificar();
    protected abstract T select();

    public Listador(java.awt.Frame parent, boolean modal){
        super(parent, modal);
    }

    public Listador(java.awt.Frame parent, boolean modal, String variable){
        super(parent, modal);
        this.setVariable(variable);

        this.inicializar();
    }

    //ABM
    protected abstract void refresh();
    public abstract void buscar();

    public void eliminar(String mensaje) {

       T o = this.select();
       if(o != null && ConfirmationDialog.show(mensaje)){

            try {
                BaseService.get().remove(o);
                refresh();
            } catch (Exception ex) {
                ErrorDialog.show("No es posible eliminarlo.");
                ex.printStackTrace();
            }
        }

    }

    public void setearBuscado(){
        this.getBuscador().setObjeto(this.getVariable(), this.select());
        this.dispose();
    }

    public void seleccionoDeLista(){
        if (this.isBuscando()){
            this.setearBuscado();
        }else{
            this.modificar();
        }
    }

    protected void inicializar() {
    }

    public Buscador getBuscador() {
        return buscador;
    }

    public void setBuscador(Buscador buscador) {
        this.buscador = buscador;
    }

    public boolean isBuscando() {
        return buscando;
    }

    public void setBuscando(boolean buscando) {
        this.buscando = buscando;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

}

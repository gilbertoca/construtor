/*
 * ConfigurarClaseFitnessDialog.java
 *
 * Created on 30-ene-2010, 12:21:00
 */

package com.gilbertoca.igreja.view.action;

import com.gilbertoca.igreja.model.security.Accion;
import com.gilbertoca.igreja.model.security.Rol;
import com.gilbertoca.igreja.service.base.BaseService;
import com.gilbertoca.igreja.service.security.SeguridadService;
import com.gilbertoca.igreja.view.dialogs.AfirmacionDialog;
import com.gilbertoca.igreja.view.dialogs.ErrorDialog;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jdesktop.application.Action;

/**
 *
 * @author Seba
 */
public class ConfigurarAccionesDialog extends javax.swing.JDialog {

    private Rol rol;

    private List<Accion> accionesDisponibles;

    /** Creates new form ConfigurarClaseFitnessDialog */
    public ConfigurarAccionesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ConfigurarAccionesDialog(JFrame mainFrame, boolean b, Rol rol) {
        this(mainFrame, b );

        this.setRol(rol);
        
        this.getLbl_titulo().setText(this.getLbl_titulo().getText()+rol);

        this.setAccionesDisponibles(SeguridadService.get().getAllAcciones());
        this.cargarAcciones();

    }


    private void cargarAcciones() {
        DefaultListModel modelo_disponibles = new DefaultListModel();
        DefaultListModel modelo_asignados = new DefaultListModel();

        for(Accion a : this.getAccionesDisponibles()){

            if (this.getRol().tieneAccion(a)){
                modelo_asignados.addElement(a);
            }else{
                modelo_disponibles.addElement(a);
            }
        }

        list_disponibles.setModel(modelo_disponibles);
        list_asignados.setModel(modelo_asignados);
    }

    @Action
    public void agregarAccion(){
        
        DefaultListModel modelo_disponibles = (DefaultListModel)list_disponibles.getModel();
        DefaultListModel modelo_asignado = (DefaultListModel)list_asignados.getModel();

        Accion a = null;

        for(Object obj_accion : list_disponibles.getSelectedValues()){

           a = (Accion) obj_accion;

           if (!this.getRol().tieneAccion(a) ){

                modelo_disponibles.removeElement(a);
                modelo_asignado.addElement(a);

           }
           
        }

    }

    @Action
    public void quitarAccion(){

      DefaultListModel modelo_dis = (DefaultListModel)list_disponibles.getModel();
      DefaultListModel modelo_asig = (DefaultListModel)list_asignados.getModel();

       Accion a = null;

        for(Object obj_acc : list_asignados.getSelectedValues()){

           a = (Accion) obj_acc;

            modelo_asig.removeElement(a);
            modelo_dis.addElement(a);

        }
        
    }

    @Action
    public void guardar(){

         if ( this.getRol() != null ) {

            try {
                //El usuario ya esta grabada, ya tiene id, y las acciones tmb.

                DefaultListModel modelo_dis = (DefaultListModel)list_disponibles.getModel();
                DefaultListModel modelo_asignados = (DefaultListModel)list_asignados.getModel();

                Accion a = null;
                
                //Asocio el rol del usuario con la accion
                for (int i_asig=0; i_asig<modelo_asignados.getSize(); i_asig++ ){

                    a = (Accion)modelo_asignados.get(i_asig);

                    if (!getRol().tieneAccion(a)){

                        this.getRol().agregarAccion(a);
                        
                    }

                }
                //Desasocio aquellas profesores que no fueron asignados para esta clase
                for (int i_asig=0; i_asig<modelo_dis.getSize(); i_asig++ ){

                    a = (Accion)modelo_dis.get(i_asig);

                    if (this.getRol().tieneAccion(a)){

                        this.getRol().quitarAccion(a);
                    }

                }

                //Desasociar todo
                String borrado = "DELETE FROM rol_accion WHERE Rol_ID = "+this.getRol().getId();
                BaseService.get().executeNativeSQL(borrado);

                //Asociar seleccionados
                String grabado;

                 for (Accion acc : this.getRol().getAcciones()){

                    grabado =  "insert into rol_accion values ("+this.getRol().getId()+","+acc.getId()+");";
                    BaseService.get().executeNativeSQL(grabado);

                }

                AfirmacionDialog.show("El usuario "+this.getRol()+" se ha guardado exitosamente");

            } catch (Exception e) {

                ErrorDialog.show("Error al guardar la clase:"+this.getRol());
                e.printStackTrace();

            }

            dispose();

        }
         
    }

    @Action
    public void cerrar() {
        dispose();
    }

    public JLabel getLbl_titulo() {
        return lbl_titulo;
    }

    public void setLbl_titulo(JLabel lbl_titulo) {
        this.lbl_titulo = lbl_titulo;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_asignados = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_disponibles = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        lbl_profesDisp = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ConfigurarAccionesDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("jPanel2.border.highlightColor"), resourceMap.getColor("jPanel2.border.shadowColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(664, 81));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(ConfigurarAccionesDialog.class, this);
        jButton3.setAction(actionMap.get("guardar")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        jButton4.setAction(actionMap.get("cerrar")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(183, 183, 183)
                .add(jButton3)
                .add(108, 108, 108)
                .add(jButton4)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton4)
                    .add(jButton3))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 720, 70));

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("jPanel1.border.highlightColor"), resourceMap.getColor("jPanel1.border.shadowColor"))); // NOI18N
        jPanel1.setForeground(resourceMap.getColor("jPanel1.foreground")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        list_asignados.setName("list_asignados"); // NOI18N
        list_asignados.setSelectionBackground(resourceMap.getColor("list_asignados.selectionBackground")); // NOI18N
        jScrollPane2.setViewportView(list_asignados);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 310, 240));

        jButton2.setAction(actionMap.get("quitarAccion")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        list_disponibles.setName("list_disponibles"); // NOI18N
        list_disponibles.setSelectionBackground(resourceMap.getColor("list_disponibles.selectionBackground")); // NOI18N
        jScrollPane1.setViewportView(list_disponibles);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 270, 240));

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, -1));

        lbl_profesDisp.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        lbl_profesDisp.setText(resourceMap.getString("lbl_profesDisp.text")); // NOI18N
        lbl_profesDisp.setName("lbl_profesDisp"); // NOI18N
        jPanel1.add(lbl_profesDisp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton1.setAction(actionMap.get("agregarAccion")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 720, 330));

        jPanel3.setName("jPanel3"); // NOI18N

        lbl_titulo.setFont(resourceMap.getFont("lbl_titulo.font")); // NOI18N
        lbl_titulo.setForeground(resourceMap.getColor("lbl_titulo.foreground")); // NOI18N
        lbl_titulo.setText(resourceMap.getString("lbl_titulo.text")); // NOI18N
        lbl_titulo.setName("lbl_titulo"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(222, 222, 222)
                .add(lbl_titulo)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbl_titulo)
                .addContainerGap(449, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_profesDisp;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JList list_asignados;
    private javax.swing.JList list_disponibles;
    // End of variables declaration//GEN-END:variables


    public List<Accion> getAccionesDisponibles() {
        return accionesDisponibles;
    }

    public void setAccionesDisponibles(List<Accion> accionesDisponibles) {
        this.accionesDisponibles = accionesDisponibles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

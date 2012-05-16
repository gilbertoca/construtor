/*
 * AfirmacionDialog.java
 *
 * Created on 24 de septiembre de 2008, 19:15
 */

package com.gilbertoca.igreja.view.dialogs;

import com.gilbertoca.igreja.view.Application;
import org.jdesktop.application.Action;

/**
 *
 * @author  Mono
 */
public class WarningDialog extends javax.swing.JDialog {

    /** Creates new form AfirmacionDialog */
    public WarningDialog(java.awt.Frame parent, String mensaje) {
        super(parent, Boolean.TRUE);
        initComponents();
        this.mensaje.setText("<html>"+mensaje+"</html>");
        this.setTitle(Application.getApplication().getContext().getResourceMap(Application.class).getString("Application.name"));
    }
    
    @Action
    public void cerrar() {
        dispose();
    }
    
    public static void show(String mensaje) {
        WarningDialog dialog = new WarningDialog(Application.getApplication().getMainFrame(), mensaje);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        dialog.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mensaje = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.gilbertoca.igreja.view.Application.class).getContext().getResourceMap(WarningDialog.class);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        mensaje.setFont(resourceMap.getFont("mensaje.font")); // NOI18N
        mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje.setText(resourceMap.getString("mensaje.text")); // NOI18N
        mensaje.setName("mensaje"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.gilbertoca.igreja.view.Application.class).getContext().getActionMap(WarningDialog.class, this);
        jButton1.setAction(actionMap.get("cerrar")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mensaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(251, Short.MAX_VALUE)
                .add(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, mensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel mensaje;
    // End of variables declaration//GEN-END:variables

}

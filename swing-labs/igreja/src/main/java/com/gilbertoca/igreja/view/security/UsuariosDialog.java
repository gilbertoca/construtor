package com.gilbertoca.igreja.view.security;

import com.gilbertoca.igreja.model.security.Usuario;
import com.gilbertoca.igreja.service.UsuarioService;
import com.gilbertoca.igreja.service.base.BaseService;
import com.gilbertoca.igreja.service.security.SeguridadService;
import com.gilbertoca.igreja.view.Application;
import com.gilbertoca.igreja.view.component.Listador;
import com.gilbertoca.igreja.view.dialogs.AfirmacionDialog;
import com.gilbertoca.igreja.view.dialogs.ConfirmationDialog;
import com.gilbertoca.igreja.view.dialogs.ErrorDialog;
import com.gilbertoca.igreja.view.dialogs.WarningDialog;
import com.gilbertoca.igreja.view.security.models.UsuarioTableModel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.Timer;
import org.jdesktop.application.Action;

public class UsuariosDialog extends Listador <Usuario> {


    private Timer keyTimer;

    SeguridadService seguridadService;

    public UsuariosDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refresh();
        seguridadService = SeguridadService.get();

        //se inicia el timer para hacer el delay en la busqueda de profesores.
         keyTimer = new Timer(1000, new ActionListener (){
         public void actionPerformed(ActionEvent e)
          {
             btn_buscar.doClick();
             keyTimer.stop();
          }
        });

    }

    public void refresh() {
        List<Usuario> usuarios = BaseService.get().getAll(Usuario.class);
        tabla.setModel(new UsuarioTableModel(usuarios));
        profesoresActivosLabel.setText("Cantidad de Usuarios: "+ usuarios.size());
        ocultarOpciones();
    }

    @Action
    public void cerrar() {
        dispose();
    }

    @Action
    public void limpiar(){
        this.codigo.setText("");
    }

    @Action
    public void modificar() {
        
        Usuario usr = this.select();
        if(usr != null){
            UsuarioDialog dialog = new UsuarioDialog(Application.getApplication().getMainFrame(), true, usr);
            dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
            Application.getApplication().show(dialog);
            refresh();
        }
    }

    @Action
    public void nuevo() {
        UsuarioDialog dialog = new UsuarioDialog(Application.getApplication().getMainFrame(), true, new Usuario());
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
        refresh();
    }

    public Usuario select(){
        int row = tabla.getSelectedRow();
        Usuario u = null;
        if (row >= 0) {
             u = ((UsuarioTableModel) tabla.getModel()).getElements().get(tabla.convertRowIndexToModel(row));

        }else {
            WarningDialog.show("Debe seleccionar un usuario.");
        }

        return u;
    }
    @Action
    public void eliminar() {

       Usuario u = this.select();
       if(u != null && ConfirmationDialog.show("¿Desea eliminar el usuario seleccionado?")){

            try {
                BaseService.get().remove(u);
                refresh();
            } catch (Exception ex) {
                ErrorDialog.show("No es posible eliminar el usuario.");
                ex.printStackTrace();
            }
        }
            
    }

    //-------------------------------------------------------------------------------------------
    @Action
    public void buscar() {
        List<Usuario> usuarios = seguridadService.search(codigo.getText());
        tabla.setModel(new UsuarioTableModel(usuarios));
        ocultarOpciones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupTabla = new javax.swing.JPopupMenu();
        menuItemFichaCliente = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        profesoresActivosLabel = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        panel_opciones = new javax.swing.JPanel();
        panel_habilitar = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        panel_deshablitar = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();

        popupTabla.setName("popupTabla"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.gilbertoca.igreja.view.Application.class).getContext().getActionMap(UsuariosDialog.class, this);
        menuItemFichaCliente.setAction(actionMap.get("modificar")); // NOI18N
        menuItemFichaCliente.setName("menuItemModificarProfesor"); // NOI18N
        popupTabla.add(menuItemFichaCliente);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.gilbertoca.igreja.view.Application.class).getContext().getResourceMap(UsuariosDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("jPanel1.border.highlightColor"), resourceMap.getColor("jPanel1.border.shadowColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        codigo.setName("codigo"); // NOI18N
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
        });

        btn_buscar.setAction(actionMap.get("buscar")); // NOI18N
        btn_buscar.setIcon(resourceMap.getIcon("btn_buscar.icon")); // NOI18N
        btn_buscar.setName("btn_buscar"); // NOI18N

        profesoresActivosLabel.setName("profesoresActivosLabel"); // NOI18N

        jButton5.setAction(actionMap.get("limpiar")); // NOI18N
        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(codigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 124, Short.MAX_VALUE)
                        .add(profesoresActivosLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 305, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(btn_buscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(codigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(profesoresActivosLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton5)
                    .add(btn_buscar))
                .addContainerGap())
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setName("tabla"); // NOI18N
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("jPanel2.border.highlightColor"), resourceMap.getColor("jPanel2.border.shadowColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(162, 455));

        jButton1.setAction(actionMap.get("nuevo")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setEnabled(Application.getApplication().getUsuario().tieneAccion(Usuario.COD_CREAR));
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setAction(actionMap.get("modificar")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setEnabled(Application.getApplication().getUsuario().tieneAccion(Usuario.COD_MODIFICAR));
        jButton2.setName("jButton2"); // NOI18N

        jButton3.setAction(actionMap.get("eliminar")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setEnabled(Application.getApplication().getUsuario().tieneAccion(Usuario.COD_ELIMINAR));
        jButton3.setName("jButton3"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jButton4.setAction(actionMap.get("cerrar")); // NOI18N
        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        panel_opciones.setName("panel_opciones"); // NOI18N
        panel_opciones.setLayout(new java.awt.CardLayout());

        panel_habilitar.setBackground(resourceMap.getColor("panel_habilitar.background")); // NOI18N
        panel_habilitar.setName("panel_habilitar"); // NOI18N

        jButton6.setAction(actionMap.get("habilitarUsuario")); // NOI18N
        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N

        org.jdesktop.layout.GroupLayout panel_habilitarLayout = new org.jdesktop.layout.GroupLayout(panel_habilitar);
        panel_habilitar.setLayout(panel_habilitarLayout);
        panel_habilitarLayout.setHorizontalGroup(
            panel_habilitarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_habilitarLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_habilitarLayout.setVerticalGroup(
            panel_habilitarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_habilitarLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        panel_opciones.add(panel_habilitar, "Habilitar");

        panel_deshablitar.setBackground(resourceMap.getColor("panel_deshablitar.background")); // NOI18N
        panel_deshablitar.setName("panel_deshablitar"); // NOI18N

        jButton7.setAction(actionMap.get("deshabilitarUsuario")); // NOI18N
        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N

        org.jdesktop.layout.GroupLayout panel_deshablitarLayout = new org.jdesktop.layout.GroupLayout(panel_deshablitar);
        panel_deshablitar.setLayout(panel_deshablitarLayout);
        panel_deshablitarLayout.setHorizontalGroup(
            panel_deshablitarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_deshablitarLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_deshablitarLayout.setVerticalGroup(
            panel_deshablitarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_deshablitarLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        panel_opciones.add(panel_deshablitar, "Deshabilitar");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addContainerGap())
            .add(panel_opciones, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3)
                .add(18, 18, 18)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_opciones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 225, Short.MAX_VALUE)
                .add(jButton4)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed
    //se abre el menu contextual.

        mostrarOpcion();

        if(evt.getButton() == 3){
            popupTabla.show(tabla, evt.getX(), evt.getY());
        }

        if(evt.getClickCount() == 2){
            this.seleccionoDeLista();
        }
    }//GEN-LAST:event_tablaMousePressed

    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             //Si presiona el enter, busca y se posiciona en la primer fila de la tabla
             btn_buscar.doClick();
             tabla.requestFocusInWindow();
             evt.setKeyCode(KeyEvent.VK_DOWN);

         }else if(KeyEvent.VK_A <= evt.getKeyCode() && evt.getKeyCode() <= KeyEvent.VK_Z
                 || evt.getKeyCode() == KeyEvent.VK_PERIOD
                 || KeyEvent.VK_0 <= evt.getKeyCode() && evt.getKeyCode() <= KeyEvent.VK_9){
            //Si presiona una tecla valida, busca y se posiciona en la primer fila de la tabla
            keyTimer.start();

         }else if(KeyEvent.VK_LEFT <= evt.getKeyCode() && evt.getKeyCode() <= KeyEvent.VK_DOWN){
             //Si toca las flechas, qiere avegar por la tabla
             tabla.requestFocusInWindow();
             evt.setKeyCode(KeyEvent.VK_DOWN);
         }
    }//GEN-LAST:event_codigoKeyPressed

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
       // Codigo que maneja las teclas presionadas sobre la tabla
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            //Si presiona el enter, selecciona el cliente de la tabla
            this.seleccionoDeLista();

        }else   if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            //Si presiona escape, se reanuda la busqueda desde cero y gana el foco el input
             codigo.setText(null);
             btn_buscar.doClick();
             codigo.requestFocusInWindow();

        }else if(KeyEvent.VK_A <= evt.getKeyCode() && evt.getKeyCode() <= KeyEvent.VK_Z
                 || evt.getKeyCode() == KeyEvent.VK_PERIOD
                 || KeyEvent.VK_0 <= evt.getKeyCode() && evt.getKeyCode() <= KeyEvent.VK_9){
            //Si presiona un caracter valido busca y se posiciona en la primer fila de la tabla
            codigo.setText(codigo.getText()+evt.getKeyChar());
            codigo.requestFocusInWindow();
            keyTimer.start();
         }
    }//GEN-LAST:event_tablaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JTextField codigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem menuItemFichaCliente;
    private javax.swing.JPanel panel_deshablitar;
    private javax.swing.JPanel panel_habilitar;
    private javax.swing.JPanel panel_opciones;
    private javax.swing.JPopupMenu popupTabla;
    private javax.swing.JLabel profesoresActivosLabel;
    private org.jdesktop.swingx.JXTable tabla;
    // End of variables declaration//GEN-END:variables

    private void mostrarOpcion(){

         Usuario u = this.select();

         panel_opciones.setVisible(true);
         if (u.getBorrado()){
               ((CardLayout)panel_opciones.getLayout()).show(panel_opciones, "Habilitar");
         }else{
               ((CardLayout)panel_opciones.getLayout()).show(panel_opciones, "Deshabilitar");
         }
    }

    private void ocultarOpciones(){
         panel_opciones.setVisible(false);
    }

    @Action
    public void deshabilitarUsuario(){
         Usuario u = this.select();
         u.setBorrado(true);

         try {
            UsuarioService usuarioService = UsuarioService.get();
            usuarioService.update(u);
            AfirmacionDialog.show("El usuario se ha deshabilitado exitosamente");
         } catch (Exception e) {
            e.printStackTrace();
            ErrorDialog.show("Error al deshabilitar usuario.");
         }
    }

    @Action
    public void habilitarUsuario(){
         Usuario u = this.select();
         u.setBorrado(false);

         try {
            UsuarioService usuarioService = UsuarioService.get();
            usuarioService.update(u);
            AfirmacionDialog.show("El usuario se habilitó exitosamente");
         } catch (Exception e) {
            e.printStackTrace();
            ErrorDialog.show("Error al deshabilitar usuario.");
         }
    }
}

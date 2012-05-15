package com.gilbertoca.igreja.view;

import base.services.reportes.VisorDeReportesJasper;
import base.view.backup.BackupDialog;
import base.view.seguridad.RolesView;
import java.awt.Dialog.ModalExclusionType;


import sforinci.view.reportes.ReportesDialog;
import sforinci.view.seguridad.UsuariosDialog;
import java.awt.Dimension;

import java.awt.Toolkit;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jdesktop.swingworker.SwingWorker;
import sforinci.model.orden.OrdenProvision;
import sforinci.model.orden.Presupuesto;
import sforinci.view.model.LocalidadesDialog;
import sforinci.view.model.RegionesDialog;
import sforinci.view.model.orden.OrdenDialog;
import sforinci.view.model.orden.OrdenesDialog;
import sforinci.view.model.orden.PresupuestoDialog;
import sforinci.view.model.orden.PresupuestosDialog;
import sforinci.view.model.producto.MarcasDialog;
import sforinci.view.model.producto.MaterialesDialog;
import sforinci.view.model.producto.ProductosDialog;
import sforinci.view.model.producto.stock.DepositosDialog;
import sforinci.view.usuarios.CambiarContraseniaDialog;
import sforinci.view.usuarios.ClientesDialog;
import sforinci.view.usuarios.MedicosDialog;
import sforinci.view.usuarios.OrtopedistasDialog;
import sforinci.view.usuarios.VendedoresDialog;



public class MainView extends FrameView {

    public MainView(SingleFrameApplication app) {
        super(app);

        initComponents();

        this.startClock();
       
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        messageTimer.setRepeats(false);

        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;

            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {

                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }

                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();

                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());

                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());

                }
            }
        });

        this.setearAccesosDirectos();

    }

    public void startClock() {
        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEEEEEEEE dd,  MMMMMMMMMMMM, yyyy - HH:mm:ss");
                    clockLabel.setText(formatter.format(new Date()));
                    Thread.sleep(1000);
                }
            }
        };

        worker.execute();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Application.getApplication().getMainFrame();
            aboutBox = new AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Application.getApplication().show(aboutBox);
    }


    @Action
    public void roles() {
       RolesView dialog = new RolesView(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void backup() {
        BackupDialog backupDialog = new BackupDialog(Application.getApplication().getMainFrame(), true);
        backupDialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(backupDialog);
    }

  
    @Action
    public void reportesDialog() {
        ReportesDialog dialog = new ReportesDialog(Application.getApplication().getMainFrame(), false);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void usuarios() {
        UsuariosDialog dialog = new UsuariosDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void medicos() {
        MedicosDialog dialog = new MedicosDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void clientes() {
        ClientesDialog dialog = new ClientesDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void ortopedistas() {
        OrtopedistasDialog dialog = new OrtopedistasDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void verReporte(JasperPrint reporte, String titulo) {
        VisorDeReportesJasper visorDeReportes = new VisorDeReportesJasper(titulo);
        JRViewer viewer;
        try {
            viewer = new JRViewer(reporte);
            visorDeReportes.getContentPane().add(viewer);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            visorDeReportes.setSize(screenSize.width, screenSize.height);
            visorDeReportes.setLocation(0, 0);
//            visorDeReportes.show();
            visorDeReportes.setFocusable(true);
            visorDeReportes.setFocusableWindowState(true);
            visorDeReportes.setResizable(true);
            visorDeReportes.setEnabled(true);
            visorDeReportes.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
            visorDeReportes.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(VisorDeReportesJasper.class.getName()).log(Level.ERROR, null, ex);
        }
    }

    @Action
    public void regiones(){
        RegionesDialog dialog = new RegionesDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void localidades(){
        LocalidadesDialog dialog = new LocalidadesDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void ordenes(){
        OrdenesDialog dialog = new OrdenesDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void nuevaOrden() {
        OrdenDialog dialog = new OrdenDialog(Application.getApplication().getMainFrame(), true, new OrdenProvision(Application.getApplication().getUsuario()));
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void vendedores() {
       VendedoresDialog dialog = new VendedoresDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

     @Action
    public void presupuesto() {
        PresupuestosDialog dialog = new PresupuestosDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    public void centrar(JDesktopPane desktop, JComponent component) {
        Dimension pantalla = desktop.getSize();
        Dimension ventana = component.getSize();
        component.setLocation(
                (pantalla.width - ventana.width) / 2,
                (pantalla.height - ventana.height) / 2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        panel_accesoRapido = new javax.swing.JPanel();
        btn_buscarOrden = new javax.swing.JButton();
        btn_nuevaOrden = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        itemMenu_salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemMenu_usuarios = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        Resguardo = new javax.swing.JMenuItem();
        menu_config = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        item_clientes = new javax.swing.JMenuItem();
        itemMenu_medico = new javax.swing.JMenuItem();
        item_ortopedistas = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemMenu_ordenes = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        menu_stock = new javax.swing.JMenu();
        menuItem_productos = new javax.swing.JMenuItem();
        menuItem_marcas = new javax.swing.JMenuItem();
        menuItem_materiales = new javax.swing.JMenuItem();
        menuItem_depositos = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        versionLabel = new javax.swing.JLabel();
        clockLabel = new javax.swing.JLabel();

        mainPanel.setMinimumSize(new java.awt.Dimension(880, 540));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(880, 540));
        mainPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mainPanelFocusGained(evt);
            }
        });
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("jSeparator2"); // NOI18N
        mainPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 20, 531));

        panel_accesoRapido.setName("panel_accesoRapido"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainView.class, this);
        btn_buscarOrden.setAction(actionMap.get("ordenes")); // NOI18N
        btn_buscarOrden.setName("btn_buscarOrden"); // NOI18N

        btn_nuevaOrden.setAction(actionMap.get("nuevaOrden")); // NOI18N
        btn_nuevaOrden.setName("btn_nuevaOrden"); // NOI18N

        jButton2.setAction(actionMap.get("nuevoPresupuesto")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        org.jdesktop.layout.GroupLayout panel_accesoRapidoLayout = new org.jdesktop.layout.GroupLayout(panel_accesoRapido);
        panel_accesoRapido.setLayout(panel_accesoRapidoLayout);
        panel_accesoRapidoLayout.setHorizontalGroup(
            panel_accesoRapidoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_accesoRapidoLayout.createSequentialGroup()
                .addContainerGap()
                .add(panel_accesoRapidoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, panel_accesoRapidoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(btn_buscarOrden, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .add(btn_nuevaOrden, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel_accesoRapidoLayout.setVerticalGroup(
            panel_accesoRapidoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_accesoRapidoLayout.createSequentialGroup()
                .add(51, 51, 51)
                .add(btn_nuevaOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(btn_buscarOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(324, Short.MAX_VALUE))
        );

        mainPanel.add(panel_accesoRapido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 530));

        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1330, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 40, Short.MAX_VALUE)
        );

        jPanel1.setBounds(0, 0, 0, 40);
        jDesktopPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrame1.setName("jInternalFrame1"); // NOI18N

        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jPasswordField1.setName("jPasswordField1"); // NOI18N

        jButton1.setName("jButton1"); // NOI18N

        org.jdesktop.layout.GroupLayout jInternalFrame1Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPasswordField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jInternalFrame1.setBounds(310, 130, 260, 150);
        jDesktopPane1.add(jInternalFrame1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mainPanel.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setName("jLabel3"); // NOI18N
        mainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 690, 530));

        menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menuBar.setName("menuBar"); // NOI18N

        jMenu3.setName("jMenu3"); // NOI18N

        jMenuItem6.setAction(actionMap.get("cambiarContrasenia")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu3.add(jMenuItem6);

        itemMenu_salir.setAction(actionMap.get("quit")); // NOI18N
        itemMenu_salir.setName("itemMenu_salir"); // NOI18N
        jMenu3.add(itemMenu_salir);

        menuBar.add(jMenu3);

        jMenu2.setName("jMenu2"); // NOI18N

        itemMenu_usuarios.setAction(actionMap.get("usuarios")); // NOI18N
        itemMenu_usuarios.setName("itemMenu_usuarios"); // NOI18N
        jMenu2.add(itemMenu_usuarios);

        jMenuItem3.setAction(actionMap.get("roles")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu2.add(jMenuItem3);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu2.add(jSeparator1);

        Resguardo.setAction(actionMap.get("backup")); // NOI18N
        Resguardo.setName("Resguardo"); // NOI18N
        jMenu2.add(Resguardo);

        menuBar.add(jMenu2);

        menu_config.setAction(actionMap.get("diasNoHabiles")); // NOI18N
        menu_config.setName("menu_config"); // NOI18N

        jMenuItem2.setAction(actionMap.get("localidades")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        menu_config.add(jMenuItem2);

        jMenuItem1.setAction(actionMap.get("regiones")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        menu_config.add(jMenuItem1);

        jSeparator3.setName("jSeparator3"); // NOI18N
        menu_config.add(jSeparator3);

        item_clientes.setAction(actionMap.get("clientes")); // NOI18N
        item_clientes.setName("item_clientes"); // NOI18N
        menu_config.add(item_clientes);

        itemMenu_medico.setAction(actionMap.get("medicos")); // NOI18N
        itemMenu_medico.setName("itemMenu_medico"); // NOI18N
        menu_config.add(itemMenu_medico);

        item_ortopedistas.setAction(actionMap.get("ortopedistas")); // NOI18N
        item_ortopedistas.setName("item_ortopedistas"); // NOI18N
        menu_config.add(item_ortopedistas);

        jMenuItem4.setAction(actionMap.get("vendedores")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        menu_config.add(jMenuItem4);

        menuBar.add(menu_config);

        jMenu1.setName("jMenu1"); // NOI18N

        itemMenu_ordenes.setAction(actionMap.get("ordenes")); // NOI18N
        itemMenu_ordenes.setName("itemMenu_ordenes"); // NOI18N
        jMenu1.add(itemMenu_ordenes);

        jMenuItem5.setAction(actionMap.get("presupuesto")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu1.add(jMenuItem5);

        menuBar.add(jMenu1);

        menu_stock.setName("menu_stock"); // NOI18N

        menuItem_productos.setAction(actionMap.get("productos")); // NOI18N
        menuItem_productos.setName("menuItem_productos"); // NOI18N
        menu_stock.add(menuItem_productos);

        menuItem_marcas.setAction(actionMap.get("marcas")); // NOI18N
        menuItem_marcas.setName("menuItem_marcas"); // NOI18N
        menu_stock.add(menuItem_marcas);

        menuItem_materiales.setAction(actionMap.get("materiales")); // NOI18N
        menuItem_materiales.setName("menuItem_materiales"); // NOI18N
        menu_stock.add(menuItem_materiales);

        menuItem_depositos.setAction(actionMap.get("depositos")); // NOI18N
        menuItem_depositos.setName("menuItem_depositos"); // NOI18N
        menu_stock.add(menuItem_depositos);

        menuBar.add(menu_stock);

        statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(860, 30));
        statusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N
        statusPanel.add(statusPanelSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 0, 40));

        versionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        versionLabel.setName("versionLabel"); // NOI18N
        statusPanel.add(versionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 350, 20));

        clockLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        clockLabel.setName("clockLabel"); // NOI18N
        statusPanel.add(clockLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 460, 10));

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_formPropertyChange

    private void mainPanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mainPanelFocusGained
        // this.label1.setText("Bienvenido "+ Application.getApplication().getUsuario().getNombreUsuario());
}//GEN-LAST:event_mainPanelFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem Resguardo;
    public javax.swing.JButton btn_buscarOrden;
    public javax.swing.JButton btn_nuevaOrden;
    public javax.swing.JLabel clockLabel;
    public javax.swing.JMenuItem itemMenu_medico;
    public javax.swing.JMenuItem itemMenu_ordenes;
    public javax.swing.JMenuItem itemMenu_salir;
    public javax.swing.JMenuItem itemMenu_usuarios;
    public javax.swing.JMenuItem item_clientes;
    public javax.swing.JMenuItem item_ortopedistas;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JDesktopPane jDesktopPane1;
    public javax.swing.JInternalFrame jInternalFrame1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPasswordField jPasswordField1;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JPanel mainPanel;
    public javax.swing.JMenuBar menuBar;
    public javax.swing.JMenuItem menuItem_depositos;
    public javax.swing.JMenuItem menuItem_marcas;
    public javax.swing.JMenuItem menuItem_materiales;
    public javax.swing.JMenuItem menuItem_productos;
    public javax.swing.JMenu menu_config;
    public javax.swing.JMenu menu_stock;
    public javax.swing.JPanel panel_accesoRapido;
    public javax.swing.JPanel statusPanel;
    public javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    private void setearAccesosDirectos() {
        itemMenu_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
//        itemMenu_reportes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        itemMenu_usuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        itemMenu_medico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        itemMenu_ordenes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));

    }

    /* Menu de Productos */

    @Action
    public void marcas() {
        MarcasDialog dialog = new MarcasDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void materiales() {
        MaterialesDialog dialog = new MaterialesDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void productos() {
        ProductosDialog dialog = new ProductosDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void depositos() {
        DepositosDialog dialog = new DepositosDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

     @Action
    public void nuevoPresupuesto() {
        PresupuestoDialog dialog = new PresupuestoDialog(Application.getApplication().getMainFrame(), true, new Presupuesto());
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void cambiarContrasenia() {
        CambiarContraseniaDialog dialog = new CambiarContraseniaDialog(Application.getApplication().getMainFrame(), true);
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }
}

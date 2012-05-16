package com.gilbertoca.igreja.view.security;

import com.gilbertoca.igreja.model.Localidad;
import com.gilbertoca.igreja.model.Region;
import com.gilbertoca.igreja.model.security.Rol;
import com.gilbertoca.igreja.model.security.Usuario;
import com.gilbertoca.igreja.service.UsuarioService;
import com.gilbertoca.igreja.service.base.BaseService;
import com.gilbertoca.igreja.service.security.SeguridadService;
import com.gilbertoca.igreja.view.Application;
import com.gilbertoca.igreja.view.component.Buscador;
import com.gilbertoca.igreja.view.dialogs.AfirmacionDialog;
import com.gilbertoca.igreja.view.dialogs.ErrorDialog;
import com.gilbertoca.igreja.view.dialogs.common.LocalidadesDialog;
import com.gilbertoca.igreja.view.dialogs.common.RegionesDialog;
import com.gilbertoca.igreja.view.util.TiposGenericosSimplesDialog;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.application.Action;

public class UsuarioDialog extends javax.swing.JDialog implements Buscador{

    private Usuario usuario;

    private Boolean modificando = false;

   private Localidad localidad_seleccionada;

    private Region region_seleccionada;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDialog(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        this.modificando = usuario.getId() != null;
        initComponents();
        this.setUsuario(usuario);
        init();

        //Se esta modificando el alumno si este ya tiene un id
    }

    private void init() {
        //Inicializo el combo de roles
        ComboBoxModel model = new DefaultComboBoxModel();
        for(Rol r : SeguridadService.get().getAllRoles()){
            ((DefaultComboBoxModel)model).addElement(r);
        }
        combo_roles.setModel(  model);

        if (this.getUsuario() != null) {
            //sI YA ESTA CREADO
            llenarCampos();
        }
    }

    private void llenarCampos() {
        nombres.setText(this.getUsuario().getInformacionPersonal().getNombres());
        apellidos.setText(this.getUsuario().getInformacionPersonal().getApellidos());
        direccion.setText(this.getUsuario().getInformacionPersonal().getDireccion());
        seleccionarLocalidad(this.getUsuario().getInformacionPersonal().getLocalidad());
        seleccionarRegion(this.getUsuario().getInformacionPersonal().getRegion());
        tel_fijo.setText(this.getUsuario().getInformacionPersonal().getTelefonoFijo());
        tel_celu.setText(this.getUsuario().getInformacionPersonal().getTelefonoMovil());
        tel_laboral.setText(this.getUsuario().getInformacionPersonal().getTelefonoLaboral());
        mail.setText(this.getUsuario().getInformacionPersonal().geteMail());

        username.setText(this.getUsuario().getUsername());
        contrasena.setText(this.getUsuario().getPassword());
        combo_roles.setSelectedItem(this.getUsuario().getRol());
    }

    private void llenarSeleccionado() {
        this.getUsuario().getInformacionPersonal().setNombres( nombres.getText() );
        this.getUsuario().getInformacionPersonal().setApellidos(apellidos.getText());
        this.getUsuario().getInformacionPersonal().setDireccion(direccion.getText());
        this.getUsuario().getInformacionPersonal().setLocalidad(getLocalidad_seleccionada());
        this.getUsuario().getInformacionPersonal().setRegion(getRegion_seleccionada());
        this.getUsuario().getInformacionPersonal().setTelefonoFijo(tel_fijo.getText());
        this.getUsuario().getInformacionPersonal().setTelefonoMovil(tel_celu.getText());
        this.getUsuario().getInformacionPersonal().setTelefonoLaboral(tel_laboral.getText());
        this.getUsuario().getInformacionPersonal().seteMail(mail.getText());

        this.getUsuario().setUsername(username.getText());
        this.getUsuario().setPassword(String.valueOf(contrasena.getPassword()));
        this.getUsuario().setRol((Rol) combo_roles.getSelectedItem());
    }

    @Action
    public void guardar() {
        if (this.getUsuario() != null && validarCamposVacios() && validarDatos()) {
            llenarSeleccionado();
            try {
                UsuarioService usuarioService = UsuarioService.get();
                usuarioService.update(this.getUsuario());
                AfirmacionDialog.show("El usuario se ha guardado exitosamente");

            } catch (Exception e) {
                ErrorDialog.show("Error al guardar usuario.");
                e.printStackTrace();
            }
            dispose();
            
        }else if (this.getUsuario() == null && validarCamposVacios() && validarDatos()){

            this.setUsuario(new Usuario());

            llenarSeleccionado();
            try {
                BaseService.get().save(this.getUsuario());
                AfirmacionDialog.show("El usuario se ha guardado exitosamente");

            } catch (Exception e) {
                ErrorDialog.show("Error al guardar usuario.");
                e.printStackTrace();
            }
            dispose();
        }
    }

   private Boolean validarDatos() {
        boolean valor = Boolean.TRUE;

        Usuario usr = SeguridadService.get().getUsuarioPorNombreDeUsuario(username.getText());

        if (usr != null) {
            //Existe un usuario con ese nombre de usuario
            
            if (this.modificando && !usr.getId().equals(this.getUsuario().getId())){
                //Si estoy modificando  y el id es distinto, entonces son dos usuario distintos
                ErrorDialog.show("Ya existe un usuario con el nombre de usuario ingresado.");
                valor = Boolean.FALSE;
            }
        }

        return valor;
    }

    private Boolean validarCamposVacios(){
        String error = "Los datos de: ";
        boolean valor = Boolean.TRUE;
        if(nombres.getText().equalsIgnoreCase("") || apellidos.getText().equalsIgnoreCase("")){
            error = error + "apellido y nombres ";
            valor = Boolean.FALSE;
        }
        if (username.getText().equalsIgnoreCase("") || contrasena.getText().equalsIgnoreCase("")){
            error = error + "nombre de usuario y contraseña";
            valor = Boolean.FALSE;
        }
        if (combo_roles.getSelectedIndex() < 0 ){
            error = error + "rol ";
            valor = Boolean.FALSE;
        }
        if (valor == Boolean.FALSE)
            ErrorDialog.show(error + "son obligatorios");
        return valor;
    }


    @Action
    public void cerrar() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_info_usuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        combo_roles = new javax.swing.JComboBox();
        contrasena = new javax.swing.JPasswordField();
        panel_botones = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panel_info_persona = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tel_fijo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tel_celu = new javax.swing.JTextField();
        tel_laboral = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        localidad = new javax.swing.JTextField();
        region = new javax.swing.JTextField();
        btn_localidad = new javax.swing.JButton();
        btn_localidad1 = new javax.swing.JButton();
        btn_limpiar_region = new javax.swing.JButton();
        btn_limpiar_localidad = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.gilbertoca.igreja.view.Application.class).getContext().getResourceMap(UsuarioDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        panel_info_usuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_info_usuario.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_info_usuario.border.titleFont"), resourceMap.getColor("panel_info_usuario.border.titleColor"))); // NOI18N
        panel_info_usuario.setName("panel_info_usuario"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        username.setName("username"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        combo_roles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_roles.setName("combo_roles"); // NOI18N

        contrasena.setEditable(!this.modificando);
        contrasena.setText(resourceMap.getString("contrasena.text")); // NOI18N
        contrasena.setName("contrasena"); // NOI18N

        org.jdesktop.layout.GroupLayout panel_info_usuarioLayout = new org.jdesktop.layout.GroupLayout(panel_info_usuario);
        panel_info_usuario.setLayout(panel_info_usuarioLayout);
        panel_info_usuarioLayout.setHorizontalGroup(
            panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_info_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .add(panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(contrasena)
                    .add(combo_roles, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(username, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel_info_usuarioLayout.setVerticalGroup(
            panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_info_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .add(panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(username, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(contrasena, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_usuarioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(combo_roles, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_botones.setBackground(resourceMap.getColor("panel_botones.background")); // NOI18N
        panel_botones.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("panel_botones.border.highlightColor"), resourceMap.getColor("panel_botones.border.shadowColor"))); // NOI18N
        panel_botones.setName("panel_botones"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.gilbertoca.igreja.view.Application.class).getContext().getActionMap(UsuarioDialog.class, this);
        jButton2.setAction(actionMap.get("cerrar")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        jButton1.setAction(actionMap.get("guardar")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panel_botonesLayout = new org.jdesktop.layout.GroupLayout(panel_botones);
        panel_botones.setLayout(panel_botonesLayout);
        panel_botonesLayout.setHorizontalGroup(
            panel_botonesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_botonesLayout.createSequentialGroup()
                .add(66, 66, 66)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 211, Short.MAX_VALUE)
                .add(jButton2)
                .add(59, 59, 59))
        );
        panel_botonesLayout.setVerticalGroup(
            panel_botonesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_botonesLayout.createSequentialGroup()
                .addContainerGap()
                .add(panel_botonesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_info_persona.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("panel_info_personal.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_info_personal.border.titleFont"), resourceMap.getColor("panel_info_personal.border.titleColor"))); // NOI18N
        panel_info_persona.setForeground(resourceMap.getColor("panel_info_personal.foreground")); // NOI18N
        panel_info_persona.setName("panel_info_personal"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        nombres.setName("apellidos"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        apellidos.setName("nombres"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        direccion.setName("direccion"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        mail.setName("mail"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        tel_fijo.setName("telefono_fijo"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        tel_celu.setName("tel_celu"); // NOI18N

        tel_laboral.setName("tel_laboral"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        localidad.setEditable(false);
        localidad.setText(resourceMap.getString("localidad.text")); // NOI18N
        localidad.setName("localidad"); // NOI18N

        region.setEditable(false);
        region.setText(resourceMap.getString("region.text")); // NOI18N
        region.setName("region"); // NOI18N

        btn_localidad.setAction(actionMap.get("buscarRegion")); // NOI18N
        btn_localidad.setIcon(resourceMap.getIcon("btn_localidad.icon")); // NOI18N
        btn_localidad.setText(resourceMap.getString("btn_localidad.text")); // NOI18N
        btn_localidad.setToolTipText(resourceMap.getString("btn_localidad.toolTipText")); // NOI18N
        btn_localidad.setName("btn_localidad"); // NOI18N

        btn_localidad1.setAction(actionMap.get("buscarLocalidad")); // NOI18N
        btn_localidad1.setIcon(resourceMap.getIcon("btn_localidad1.icon")); // NOI18N
        btn_localidad1.setText(resourceMap.getString("btn_localidad1.text")); // NOI18N
        btn_localidad1.setToolTipText(resourceMap.getString("btn_localidad1.toolTipText")); // NOI18N
        btn_localidad1.setName("btn_localidad1"); // NOI18N

        btn_limpiar_region.setAction(actionMap.get("limpiarRegion")); // NOI18N
        btn_limpiar_region.setIcon(resourceMap.getIcon("btn_limpiar_region.icon")); // NOI18N
        btn_limpiar_region.setText(resourceMap.getString("btn_limpiar_region.text")); // NOI18N
        btn_limpiar_region.setToolTipText(resourceMap.getString("btn_limpiar_region.toolTipText")); // NOI18N
        btn_limpiar_region.setName("btn_limpiar_region"); // NOI18N

        btn_limpiar_localidad.setAction(actionMap.get("limpiarLocalidad")); // NOI18N
        btn_limpiar_localidad.setIcon(resourceMap.getIcon("btn_limpiar_localidad.icon")); // NOI18N
        btn_limpiar_localidad.setText(resourceMap.getString("btn_limpiar_localidad.text")); // NOI18N
        btn_limpiar_localidad.setToolTipText(resourceMap.getString("btn_limpiar_localidad.toolTipText")); // NOI18N
        btn_limpiar_localidad.setName("btn_limpiar_localidad"); // NOI18N

        org.jdesktop.layout.GroupLayout panel_info_personaLayout = new org.jdesktop.layout.GroupLayout(panel_info_persona);
        panel_info_persona.setLayout(panel_info_personaLayout);
        panel_info_personaLayout.setHorizontalGroup(
            panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panel_info_personaLayout.createSequentialGroup()
                .addContainerGap()
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panel_info_personaLayout.createSequentialGroup()
                        .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .add(jLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(panel_info_personaLayout.createSequentialGroup()
                        .add(jLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(panel_info_personaLayout.createSequentialGroup()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(5, 5, 5)))
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(tel_laboral, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tel_celu, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tel_fijo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(direccion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(apellidos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(nombres, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .add(panel_info_personaLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(panel_info_personaLayout.createSequentialGroup()
                                .add(localidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btn_localidad1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btn_limpiar_localidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(panel_info_personaLayout.createSequentialGroup()
                                .add(region, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btn_localidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btn_limpiar_region, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panel_info_personaLayout.setVerticalGroup(
            panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel_info_personaLayout.createSequentialGroup()
                .addContainerGap()
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(nombres, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(7, 7, 7)
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(apellidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(8, 8, 8)
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(direccion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(panel_info_personaLayout.createSequentialGroup()
                        .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel13)
                            .add(localidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel16)
                            .add(region, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(12, 12, 12))
                    .add(panel_info_personaLayout.createSequentialGroup()
                        .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btn_limpiar_localidad, 0, 0, Short.MAX_VALUE)
                            .add(btn_localidad1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btn_limpiar_region, 0, 0, Short.MAX_VALUE)
                            .add(btn_localidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tel_fijo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tel_celu, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(tel_laboral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_personaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addContainerGap())
        );

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel8.foreground")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panel_info_usuario, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panel_botones, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panel_info_persona, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(199, 199, 199)
                        .add(jLabel8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_persona, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_info_usuario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panel_botones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidos;
    private javax.swing.JButton btn_limpiar_localidad;
    private javax.swing.JButton btn_limpiar_region;
    private javax.swing.JButton btn_localidad;
    private javax.swing.JButton btn_localidad1;
    private javax.swing.JComboBox combo_roles;
    private javax.swing.JPasswordField contrasena;
    private javax.swing.JTextField direccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField localidad;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField nombres;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_info_persona;
    private javax.swing.JPanel panel_info_usuario;
    private javax.swing.JTextField region;
    private javax.swing.JTextField tel_celu;
    private javax.swing.JTextField tel_fijo;
    private javax.swing.JTextField tel_laboral;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
    private TiposGenericosSimplesDialog tiposDialog;

     @Action
    public void buscarLocalidad(){
        LocalidadesDialog dialog = new LocalidadesDialog(Application.getApplication().getMainFrame(), true, this, "localidad");
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

    @Action
    public void buscarRegion(){
        RegionesDialog dialog = new RegionesDialog(Application.getApplication().getMainFrame(), true, this, "region");
        dialog.setLocationRelativeTo(Application.getApplication().getMainFrame());
        Application.getApplication().show(dialog);
    }

     public void setObjeto(String variable, Object objeto) {
       if (variable.equals("localidad")){
            seleccionarLocalidad((Localidad)objeto);
        }else if (variable.equals("region")){
            seleccionarRegion((Region)objeto);
        }
    }

     private void seleccionarLocalidad(Localidad objeto) {
        setLocalidad_seleccionada(objeto);
        if (objeto != null){
            localidad.setText(getLocalidad_seleccionada().toString());
            btn_limpiar_localidad.setEnabled(true);
        }
    }

    private void seleccionarRegion(Region objeto) {
        setRegion_seleccionada(objeto);
        if (objeto != null){
            region.setText(getRegion_seleccionada().toString());
            btn_limpiar_region.setEnabled(true);
        }
    }

    public Localidad getLocalidad_seleccionada() {
        return localidad_seleccionada;
    }

    public void setLocalidad_seleccionada(Localidad localidad_seleccionada) {
        this.localidad_seleccionada = localidad_seleccionada;
    }

    public Region getRegion_seleccionada() {
        return region_seleccionada;
    }

    public void setRegion_seleccionada(Region region_seleccionada) {
        this.region_seleccionada = region_seleccionada;
    }

       @Action
    public void limpiarLocalidad(){
       setLocalidad_seleccionada(null);
       localidad.setText("");
       btn_limpiar_localidad.setEnabled(false);
    }

    @Action
    public void limpiarRegion(){
       setRegion_seleccionada(null);
       region.setText("");
       btn_limpiar_region.setEnabled(false);
    }
    
}

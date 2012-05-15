/*
 * BaseApp.java
 */

package com.gilbertoca.igreja.view;

import com.gilbertoca.model.security.Usuario;
import com.gilbertoca.service.BaseService;
import com.gilbertoca.service.ServiceFactory;
import com.gilbertoca.service.Updater;
import com.gilbertoca.view.seguridad.LoginDialog;
import javax.swing.JFrame;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class BaseApp extends SingleFrameApplication {

    private Usuario usuario;

    @Override
    protected void ready() {
        super.ready();
        
       ((BaseView)this.getMainView()).getMenuBar().setVisible(false);
       ((BaseView)this.getMainView()).panel_accesoRapido.setVisible(false);
        ServiceFactory.initJPA();

        Updater.exe();
        login();
        
        ((BaseView)this.getMainView()).getMenuBar().setVisible(true);
        ((BaseView)this.getMainView()).panel_accesoRapido.setVisible(true);

         String version = BaseService.get().getVersion();
         ((BaseView)this.getMainView()).versionLabel.setText("Versión: "+version);
    }


    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
     //   ResourceMap resourceMap = this.getContext().getResourceMap();
        
        show(new BaseView(this));
//        BaseApp.getApplication().getMainFrame().setIconImage(resourceMap.getImageIcon("icon").getImage());
       // PropertyConfigurator.configure("log4j.properties");
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of BaseApp
     */
    public static BaseApp getApplication() {
        return Application.getInstance(BaseApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(BaseApp.class, args);
    }
    
    public void login() {

        JFrame mainFrame = BaseApp.getApplication().getMainFrame();
        LoginDialog loginDialog = new LoginDialog(mainFrame, true);
        loginDialog.setLocationRelativeTo(mainFrame);
        BaseApp.getApplication().show(loginDialog);
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

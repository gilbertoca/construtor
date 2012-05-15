/*
 * Application.java
 */

package com.gilbertoca.igreja.view;

import com.gilbertoca.igreja.model.security.Usuario;
import com.gilbertoca.igreja.service.base.BaseService;
import com.gilbertoca.igreja.service.base.ServiceFactory;
import com.gilbertoca.igreja.service.base.Updater;
import com.gilbertoca.igreja.view.security.LoginDialog;
import javax.swing.JFrame;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class Application extends SingleFrameApplication {

    private Usuario usuario;

    @Override
    protected void ready() {
        super.ready();
        
       ((MainView)this.getMainView()).getMenuBar().setVisible(false);
       ((MainView)this.getMainView()).panel_accesoRapido.setVisible(false);
        ServiceFactory.initJPA();

        Updater.exe();
        login();
        
        ((MainView)this.getMainView()).getMenuBar().setVisible(true);
        ((MainView)this.getMainView()).panel_accesoRapido.setVisible(true);

         String version = BaseService.get().getVersion();
         ((MainView)this.getMainView()).versionLabel.setText("Versión: "+version);
    }


    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
     //   ResourceMap resourceMap = this.getContext().getResourceMap();
        
        show(new MainView(this));
//        Application.getApplication().getMainFrame().setIconImage(resourceMap.getImageIcon("icon").getImage());
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
     * @return the instance of Application
     */
    public static Application getApplication() {
        return Application.getInstance(Application.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(Application.class, args);
    }
    
    public void login() {

        JFrame mainFrame = Application.getApplication().getMainFrame();
        LoginDialog loginDialog = new LoginDialog(mainFrame, true);
        loginDialog.setLocationRelativeTo(mainFrame);
        Application.getApplication().show(loginDialog);
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

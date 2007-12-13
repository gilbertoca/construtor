package com.gilbertoca.gfi.page;

import net.sf.click.Page;
import net.sf.click.control.Form;
import net.sf.click.control.PasswordField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

/**
 *
 * @author gilberto
 */
public class Login extends Page {

     public Form form = new Form();

     public Login() {
         form.add(new TextField("username", true));
         form.add(new PasswordField("password", true));
         form.add(new Submit("ok", "  OK  ", this, "onOkClick"));
         form.add(new Submit("cancel", this, "onCancelClick"));
     }

     public boolean onOkClick() {
   /*      if (form.isValid()) {
             User user = new User();
             form.copyTo(user);

             if (getUserService().isAuthenticatedUser(user)) {
                 getContext().setSessionAttribute("user", user);
                 setRedirect(HomePage.class);
             }
             else {
                 form.setError(getMessage("authentication-error"));
             }
         }*/
         return true;
     }

     public boolean onCancelClick() {
         setRedirect(HomePage.class);
         return false;
     }
 } 
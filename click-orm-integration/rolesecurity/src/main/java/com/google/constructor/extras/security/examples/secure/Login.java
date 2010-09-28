package com.google.constructor.extras.security.examples.secure;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.extras.security.jpa.model.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public class Login extends Page {

    private static final long serialVersionUID = 1L;
    private Form form = new Form("form");
    private HiddenField redirectField = new HiddenField("redirect", String.class);
    private TextField usernameField = new TextField("username", true);
    private PasswordField passwordField = new PasswordField("password", true);

    // Constructor ------------------------------------------------------------
    public Login() {
        addControl(form);
        usernameField.setMaxLength(50);
        usernameField.setMinLength(5);
        usernameField.setFocus(true);
        form.add(usernameField);

        passwordField.setMaxLength(20);
        passwordField.setMinLength(5);
        form.add(passwordField);

        form.add(new Submit("ok", " OK ", this, "onOkClicked"));
        form.add(redirectField);
    }

    // Event Handlers ---------------------------------------------------------
    @Override
    public void onInit() {
        super.onInit();

        if (getContext().isGet()) {
            redirectField.setValue(getContext().getRequestParameter("redirect"));
        }
    }

    /**
     * Return true if the control and page processing should continue, or false otherwise. 
     * @return
     */
    public boolean onOkClicked() {
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ STARTING Authentication");
        if (form.isValid()) {
            User user = new User();
            form.copyTo(user);
            //create a UsernamePasswordToken using the username and password provided by the user
            //See:  http://incubator.apache.org/shiro/static/current/apidocs/org/apache/shiro/authc/UsernamePasswordToken.html
            UsernamePasswordToken token =
                    new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                //get the user (aka subject) associated with this request.
                //See: http://incubator.apache.org/shiro/static/current/apidocs/org/apache/shiro/subject/Subject.html#login(org.apache.shiro.authc.AuthenticationToken)
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                //clear the information stored in the token
                //see: http://incubator.apache.org/shiro/static/current/apidocs/org/apache/shiro/authc/UsernamePasswordToken.html#clear()
                token.clear();
                String redirect = redirectField.getValue();
                if (StringUtils.isNotBlank(redirect)) {
                    setRedirect(redirect);
                } else {
                    setRedirect("/secure/index.html");
                }
            } catch (UnknownAccountException uae) {
                //no account for the submitted username – retry?
                uae.printStackTrace();
                form.setError(uae.getMessage());
            } catch (IncorrectCredentialsException ice) {
                //submitted password was incorrect - retry?
                ice.printStackTrace();
                form.setError(ice.getMessage());
            } catch (LockedAccountException lae) {
                //account currently locked – unusable – nice error msg.
                lae.printStackTrace();
                form.setError(lae.getMessage());
            } catch (ExcessiveAttemptsException eae) {
                //too many unsuccessful login accounts. Lock it?
                eae.printStackTrace();
                form.setError(eae.getMessage());
            } catch (AuthenticationException ae) {
                //unexpected error?
                ae.printStackTrace();
                form.setError("Login NOT SUCCESSFUL - cause not known!");
            }
        }
        return true;
    }
}

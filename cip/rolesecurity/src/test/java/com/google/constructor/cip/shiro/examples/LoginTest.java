package com.google.constructor.cip.shiro.examples;

import junit.framework.Assert;
import org.apache.click.control.Form;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;

public class LoginTest extends BaseClickTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Using the IniSecurityManagerFactory, which will use the an INI file
        // as the security file.
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
            new IniSecurityManagerFactory("classpath:shiro.ini");

        // Setting up the SecurityManager...
        org.apache.shiro.mgt.SecurityManager securityManager
            = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    public void testLoginCreationPage() {
       // Bootstrap the container
        container.start();

        // Set the form name to ensure a Form submission occurs
        container.setParameter(Form.FORM_NAME, "form");

        // Set the field value as a request parameter
        container.setParameter("username", "bruce@hotmail.com");
        container.setParameter("password", "bruce");


        // Simulate a user requesting the page, Login.
        Login page = (Login) container.testPage(Login.class);

        Assert.assertTrue(page.onOkClicked());

        // Assert that the Field named "myfield", was bound to request parameter "myfield"
        Assert.assertEquals("bruce@hotmail.com", page.form.getFieldValue("username"));

        container.stop();
    }

}

package com.google.constructor.extras.security.examples.secure;

import com.google.constructor.extras.security.examples.secure.Login;
import junit.framework.Assert;
import org.apache.click.servlet.MockRequest;

public class LoginTest extends BaseClickTestCase {

    public void testLoginCreationPage() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, HomePage.   
        Login page = (Login) container.testPage(Login.class);

        //Login parameter
        page.getContext().setRequestAttribute("username", "bruce@hotmail.com");
        page.getContext().setRequestAttribute("password", "bruce");

        Assert.assertTrue(page.onOkClicked());

        container.stop();
    }
}

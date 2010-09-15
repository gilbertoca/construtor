/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.click.extras.security.examples.secure;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import junit.framework.TestCase;

public class ShiroTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(ShiroTest.class);

    public void testShiro() {
        // Using the IniSecurityManagerFactory, which will use the an INI file
        // as the security file.
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
            new IniSecurityManagerFactory("classpath:shiro.ini");

        // Setting up the SecurityManager...
        org.apache.shiro.mgt.SecurityManager securityManager
            = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject user = SecurityUtils.getSubject();

        logger.info("User is authenticated:  " + user.isAuthenticated());
    }
}

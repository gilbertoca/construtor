/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.web.page;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gilberto
 */
public class ViewParkingTest extends BaseClickTestCase {

    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, ViewParking.
        container.getRequest().setMethod("GET");
        ViewParking page = (ViewParking) container.testPage(ViewParking.class);

        // Assert that ViewParking presents the title field.
        assertEquals(page.getMessage("viewParking.title"), page.getModel().get("title"));

        container.stop();
    }

}
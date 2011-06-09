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
public class ViewVehicleTypeTest extends BaseClickTestCase {

    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, ViewVehicleType.
        container.getRequest().setMethod("GET");
        ViewVehicleType page = (ViewVehicleType) container.testPage(ViewVehicleType.class);

        // Assert that ViewVehicleType presents the title field.
        assertEquals(page.getMessage("viewVehicleType.title"), page.getModel().get("title"));

        container.stop();
    }

}
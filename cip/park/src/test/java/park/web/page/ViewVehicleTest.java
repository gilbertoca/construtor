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
public class ViewVehicleTest extends BaseClickTestCase {

    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, ViewVehicle.
        container.getRequest().setMethod("GET");
        ViewVehicle page = (ViewVehicle) container.testPage(ViewVehicle.class);

        // Assert that ViewVehicle presents the title field.
        assertEquals(page.getMessage("viewVehicle.title"), page.getModel().get("title"));

        //assertNotNull(page.table.getRowList());

        container.stop();
    }

}
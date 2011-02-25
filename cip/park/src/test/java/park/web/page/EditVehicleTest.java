/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.web.page;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditVehicleTest extends BaseClickTestCase {

    @Test
    public void testOnGetWithLicencePlateNotNull() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        params.put("licensePlate", "LC100");
        // Simulate a user requesting the page, EditVehicle.
        container.getRequest().setMethod("GET");
        EditVehicle page = (EditVehicle) container.testPage(EditVehicle.class,params);

        // Assert that EditVehicle presents the isNewField iqual to false.
        assertEquals(false, page.isNewField.getValueObject());

        container.stop();
    }
    @Test
    public void testOnGetWithLicencePlateNull() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        //params.put("licencePlate", "");
        // Simulate a user requesting the page, EditVehicle.
        container.getRequest().setMethod("GET");
        EditVehicle page = (EditVehicle) container.testPage(EditVehicle.class,params);

        // Assert that EditVehicle presents the isNewField iqual to true.
        assertEquals(true,page.isNewField.getValueObject());

        container.stop();
    }

}
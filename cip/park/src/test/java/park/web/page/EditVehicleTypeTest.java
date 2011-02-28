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
public class EditVehicleTypeTest extends BaseClickTestCase {

    @Test
    public void testOnGetWithVehicleTypeNotNull() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        params.put("vehicleType", "CAR");
        // Simulate a user requesting the page, EditVehicle.
        container.getRequest().setMethod("GET");
        EditVehicleType page = (EditVehicleType) container.testPage(EditVehicleType.class,params);

        // Assert that EditVehicleType presents the isNewField iqual to false.
        assertEquals(false, page.isNewField.getValueObject());

        container.stop();
    }
    @Test
    public void testOnGetWithVehicleTypeNull() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        //params.put("vehicleType", "");
        // Simulate a user requesting the page, EditVehicleType.
        container.getRequest().setMethod("GET");
        EditVehicleType page = (EditVehicleType) container.testPage(EditVehicleType.class,params);

        // Assert that EditVehicleType presents the isNewField iqual to true.
        assertEquals(true,page.isNewField.getValueObject());

        container.stop();
    }

}
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
public class ViewPriceTableTest extends BaseClickTestCase {

    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, ViewPriceTable.
        container.getRequest().setMethod("GET");
        ViewPriceTable page = (ViewPriceTable) container.testPage(ViewPriceTable.class);

        // Assert that ViewPriceTable presents the title field.
        assertEquals(page.getMessage("viewPriceTable.title"), page.getModel().get("title"));

        container.stop();
    }

}
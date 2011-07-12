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
public class ViewStayTest extends BaseClickTestCase {

    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, ViewStay.
        container.getRequest().setMethod("GET");
        ViewStay page = (ViewStay) container.testPage(ViewStay.class);

        // Assert that ViewStay presents the title field.
        assertEquals(page.getMessage("viewStay.title"), page.getModel().get("title"));

        container.stop();
    }

}
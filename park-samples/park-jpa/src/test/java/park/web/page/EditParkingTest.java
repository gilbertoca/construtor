package park.web.page;

import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditParkingTest extends BaseClickTestCase {
    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, EditParking.
        EditParking page = (EditParking) container.testPage(EditParking.class);

        // Assert that EditLegalEntity presents the title field.
        assertEquals(page.getMessage("editParking.title"), page.getModel().get("title"));

        container.stop();
    }
}

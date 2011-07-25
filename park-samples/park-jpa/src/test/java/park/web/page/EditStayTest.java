package park.web.page;

import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditStayTest extends BaseClickTestCase {
    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, EditStay.
        EditStay page = (EditStay) container.testPage(EditStay.class);

        // Assert that EditLegalEntity presents the title field.
        assertEquals(page.getMessage("editStay.title"), page.getModel().get("title"));

        container.stop();
    }
}

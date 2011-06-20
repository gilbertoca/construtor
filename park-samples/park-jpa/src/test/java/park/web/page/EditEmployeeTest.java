package park.web.page;

import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditEmployeeTest extends BaseClickTestCase {
    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, EditCustomer.
        EditCustomer page = (EditCustomer) container.testPage(EditCustomer.class);

        // Assert that EditLegalEntity presents the title field.
        assertEquals(page.getMessage("editCustomer.title"), page.getModel().get("title"));

        container.stop();
    }
}

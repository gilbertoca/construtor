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

        // Simulate a user requesting the page, EditEmployee.
        EditEmployee page = (EditEmployee) container.testPage(EditEmployee.class);

        // Assert that EditEmployee presents the title field.
        assertEquals(page.getMessage("editEmployee.title"), page.getModel().get("title"));

        container.stop();
    }
}

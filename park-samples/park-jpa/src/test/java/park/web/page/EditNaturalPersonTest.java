package park.web.page;

import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditNaturalPersonTest extends BaseClickTestCase {
    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, EditNaturalPerson.
        EditNaturalPerson page = (EditNaturalPerson) container.testPage(EditNaturalPerson.class);

        // Assert that EditLegalEntity presents the title field.
        assertEquals(page.getMessage("editNaturalPerson.title"), page.getModel().get("title"));

        container.stop();
    }
}

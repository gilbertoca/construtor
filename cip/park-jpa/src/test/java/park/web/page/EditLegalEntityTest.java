package park.web.page;

import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditLegalEntityTest extends BaseClickTestCase {
    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, EditLegalEntity.
        EditLegalEntity page = (EditLegalEntity) container.testPage(EditLegalEntity.class);

        // Assert that EditLegalEntity presents the title field.
        assertEquals(page.getMessage("editLegalEntity.title"), page.getModel().get("title"));

        container.stop();
    }
}

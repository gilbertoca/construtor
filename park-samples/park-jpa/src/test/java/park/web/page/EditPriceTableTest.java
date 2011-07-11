package park.web.page;

import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditPriceTableTest extends BaseClickTestCase {
    @Test
    public void testConstructor() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, EditPriceTable.
        EditPriceTable page = (EditPriceTable) container.testPage(EditPriceTable.class);

        // Assert that EditLegalEntity presents the title field.
        assertEquals(page.getMessage("editPriceTable.title"), page.getModel().get("title"));

        container.stop();
    }
}

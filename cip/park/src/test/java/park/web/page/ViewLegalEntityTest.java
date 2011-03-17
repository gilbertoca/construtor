package park.web.page;


public class ViewLegalEntityTest extends BaseClickTestCase {

    public void testConstructor() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, ViewLegalEntity.
        ViewLegalEntity page = (ViewLegalEntity) container.testPage(ViewLegalEntity.class);

        // Assert that ViewLegalEntity presents the title field.
        assertEquals(page.getMessage("viewLegalEntity.title"), page.getModel().get("title"));

        //assertNotNull(page.table.getRowList());
        
        container.stop();
    }
}

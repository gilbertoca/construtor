package park.web.page;
import junit.framework.Assert;

public class ViewLegalEntityTest extends BaseClickTestCase {

    public void testLegalEntityCreationPage() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, HomePage.   
        ViewLegalEntity page = (ViewLegalEntity) container.testPage(ViewLegalEntity.class);

        // Assert that HomePage presents the title field.  
        Assert.assertTrue(page.table.getName().equals("table"));

        container.stop();
    }
    public void testLegalEntityDeleteClickEvent() {
        // Bootstrap the container
        container.start();

        // Simulate a user requesting the page, HomePage.
        ViewLegalEntity page = (ViewLegalEntity) container.testPage(ViewLegalEntity.class);

        // Assert that HomePage presents the title field.

        page.deleteLink.setValue("1002");
        Assert.assertTrue(page.onDeleteClick());

        container.stop();
    }

}

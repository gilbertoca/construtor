package park.web.page;
import junit.framework.Assert;

public class LegalEntityPageTest extends BaseClickTestCase {

    public void testLegalEntityCreationPage() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, HomePage.   
        LegalEntityPage page = (LegalEntityPage) container.testPage(LegalEntityPage.class);

        // Assert that HomePage presents the title field.  
        Assert.assertTrue(page.msg.equals("Home"));

        container.stop();
    }
}

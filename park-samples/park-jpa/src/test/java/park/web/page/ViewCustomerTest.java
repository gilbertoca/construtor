package park.web.page;


public class ViewCustomerTest extends BaseClickTestCase {

    public void testConstructor() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, ViewCustomer.
        ViewCustomer page = (ViewCustomer) container.testPage(ViewCustomer.class);

        // Assert that ViewCustomer presents the title field.
        assertEquals(page.getMessage("viewCustomer.title"), page.getModel().get("title"));

        container.stop();
    }
}

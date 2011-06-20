package park.web.page;

public class ViewEmployeeTest extends BaseClickTestCase {

    public void testConstructor() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, ViewEmployee.
        ViewEmployee page = (ViewEmployee) container.testPage(ViewEmployee.class);

        // Assert that ViewEmployee presents the title field.
        assertEquals(page.getMessage("viewEmployee.title"), page.getModel().get("title"));

        container.stop();
    }
}

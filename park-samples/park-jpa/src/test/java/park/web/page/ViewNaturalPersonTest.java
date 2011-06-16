package park.web.page;


public class ViewNaturalPersonTest extends BaseClickTestCase {

    public void testConstructor() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, ViewNaturalPerson.
        ViewNaturalPerson page = (ViewNaturalPerson) container.testPage(ViewNaturalPerson.class);

        // Assert that ViewNaturalPerson presents the title field.
        assertEquals(page.getMessage("viewNaturalPerson.title"), page.getModel().get("title"));

        container.stop();
    }
}

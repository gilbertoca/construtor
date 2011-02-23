package park.web.page;


public class MainMenuTest extends BaseClickTestCase {

    public void testMainMenu() {
        // Bootstrap the container   
        container.start();

        // Simulate a user requesting the page, HomePage.   
        MainMenu page = (MainMenu) container.testPage(MainMenu.class);

        // Assert that MainMenu presents the title field.
        assertEquals(page.getMessage("mainMenu.title"), page.getModel().get("title"));
        
        container.stop();
    }
}

package park.web.page;

import java.util.HashMap;
import java.util.Map;
import org.apache.click.MockContext;
import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditLegalEntityTest extends BaseClickTestCase {

    @Test
    public void testPageCreation() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        params.put("id", "1002");
        // Simulate a user requesting the page, EditLegalEntity.
        container.getRequest().setMethod("GET");
        EditLegalEntity page = (EditLegalEntity) container.testPage(EditLegalEntity.class,params);

        assertNotNull(page);
        container.stop();
    }

    @Test
    public void testOnGetWithIdNotNull() {
        // Bootstrap the container
        container.start();
        MockContext.initContext();
        
        Map params = new HashMap();
        params.put("id", "1002");
        // Simulate a user requesting the page, EditLegalEntity.
        container.getRequest().setMethod("GET");
        EditLegalEntity page = new EditLegalEntity();

        page.onInit();
        // Assert that EditLegalEntity presents the isNewField iqual to false.
        assertEquals(false, page.isNewField.getValueObject());

        container.stop();
    }

   /*
    @Test
    public void testOnGetIdNull() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        //params.put("licencePlate", "");
        // Simulate a user requesting the page, EditLegalEntity.
        container.getRequest().setMethod("GET");
        EditLegalEntity page = (EditLegalEntity) container.testPage(EditLegalEntity.class,params);

        // Assert that EditLegalEntity presents the isNewField iqual to true.
        assertEquals(true,page.isNewField.getValueObject());

        container.stop();
    }
*/
}
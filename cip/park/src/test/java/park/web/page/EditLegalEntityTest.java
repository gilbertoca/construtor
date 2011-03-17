package park.web.page;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class EditLegalEntityTest extends BaseClickTestCase {

    @Test
    public void testOnGetWithIdNotNull() {
        // Bootstrap the container
        container.start();

        Map params = new HashMap();
        params.put("id", "LC100");
        // Simulate a user requesting the page, EditLegalEntity.
        container.getRequest().setMethod("GET");
        EditLegalEntity page = (EditLegalEntity) container.testPage(EditLegalEntity.class,params);

        // Assert that EditLegalEntity presents the isNewField iqual to false.
        assertEquals(false, page.isNewField.getValueObject());

        container.stop();
    }
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

}
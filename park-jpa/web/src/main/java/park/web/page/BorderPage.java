package park.web.page;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.click.Page;
import org.apache.click.extras.control.Menu;

/**
 *
 * @author Gilberto
 */
public class BorderPage extends Page{
   /** The root menu. */
    protected Menu rootMenu = Menu.getRootMenu();
    //@PersistenceContext protected EntityManager em;
    protected  EntityManagerFactory emf = Persistence.createEntityManagerFactory("parkPU");


    public BorderPage() {
        addModel("title", "Main Page");
    }

    /**
     * Returns the name of the border template: &nbsp; <tt>"/border-template.htm"</tt>
     *
     * @see Page#getTemplate()
     */
    public String getTemplate() {
        return "/border-template.htm";
    }
    
}

package park.web.page;

import java.util.Date;
import org.apache.click.control.Panel;

/**
 * Provides the applications home page.
 *
 * @author Gilberto
 */
public class HomePage extends BorderPage {
    private Panel panel = new Panel("panel", "/simple-panel.htm");
    public HomePage() {
        //getModel().put("title", "Parking Control");
          Date time = new Date();

         // Add the $time variable to the panel model
         panel.getModel().put("time", time);

         addControl(panel);
    }

}

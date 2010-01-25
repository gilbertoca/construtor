package com.gilbertoca.gfi.page;

import org.apache.click.Page;
import org.apache.click.extras.control.Menu;

/**
 *
 * @author Gilberto
 */
public class BorderPage extends Page{
   /** The root menu. */
    public Menu rootMenu = Menu.getRootMenu();

    public BorderPage() {
        addModel("title", "Página Principal");
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

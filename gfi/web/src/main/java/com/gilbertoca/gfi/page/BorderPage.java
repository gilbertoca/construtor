/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.page;

import net.sf.click.Page;
import net.sf.click.extras.control.Menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gilbertoca.gfi.service.Service;
/**
 *
 * @author Gilberto
 */
public class BorderPage extends Page{
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected Service service;
   /** The root menu. */
    public Menu rootMenu = Menu.getRootMenu();
    /**
     * Create a BorderedPage and set the model attributes <tt>$title</tt> and
     * <tt>$srcPath</tt>.
     * <ul>
     * <li><tt>$title</tt> &nbsp; - &nbsp; the Page title from classname</li>
     * <li><tt>$srcPath</tt> &nbsp; - &nbsp; the Page Java source path</li>
     * </ul>
     */
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.page;

import org.apache.click.Page;
import org.apache.click.extras.control.Menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gilbertoca.gfi.service.IService;
/**
 *
 * @author Gilberto
 */
public class BorderPage extends Page{
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @SuppressWarnings("unchecked")
	protected IService service;
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

	public IService getIService() {
		return service;
	}

	public void setIService(IService service) {
		this.service = service;
	}
    
}

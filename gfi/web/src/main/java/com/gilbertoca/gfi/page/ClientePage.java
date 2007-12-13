package com.gilbertoca.gfi.page;

import net.sf.click.Page;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

/**
 * Provides the applications home page.
 *
 * @author Gilberto
 */
public class ClientePage extends Page{

    public Form form = new Form();
    public ClientePage() {
        form.add(new TextField("nome", true));
        form.add(new TextField("CPF", true));
        form.add(new TextField("Rg", true));
        form.add(new TextField("endereço", true));
        form.add(new Submit("ok", "  OK  ", this, "onOkClick"));
        form.add(new Submit("cancel", this, "onCancelClick"));
        getModel().put("title", "Finança e Inventário");
    }    
}

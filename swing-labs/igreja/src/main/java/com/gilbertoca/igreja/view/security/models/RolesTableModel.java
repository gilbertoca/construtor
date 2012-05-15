package com.gilbertoca.igreja.view.security.models;

import com.gilbertoca.igreja.model.security.Rol;
import com.gilbertoca.igreja.view.component.BaseTableModel;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mono
 */
public class RolesTableModel extends BaseTableModel<Rol> {

    public RolesTableModel(List<Rol> roles) {
        super();
        this.setColumnNames(Arrays.asList("Nombre","Descripcion"));
        this.setFieldNames(Arrays.asList("nombre","descripcion"));
        this.setColumnClasses(Arrays.asList(String.class,String.class,String.class,String.class,String.class));
        this.setElements(roles);        
    }
}

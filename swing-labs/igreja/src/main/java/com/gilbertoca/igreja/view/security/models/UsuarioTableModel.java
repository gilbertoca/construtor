package com.gilbertoca.igreja.view.security.models;

import com.gilbertoca.igreja.model.security.Rol;
import com.gilbertoca.igreja.model.security.Usuario;
import com.gilbertoca.igreja.view.component.BaseTableModel;
import java.util.Arrays;
import java.util.List;

/**
 * Modelo de JTable para la clase de dominio Usuario.
 * 
 * @author Mono
 */
public class UsuarioTableModel  extends BaseTableModel<Usuario> {

    public UsuarioTableModel(List<Usuario> usuarios) {
        super();
        this.setColumnNames(Arrays.asList("Usuario","Nombre","Apellido","Mail","Teléfono","Rol"));
        this.setFieldNames(Arrays.asList("username","nombre","apellido","mail","telefono","rol"));
        this.setColumnClasses(Arrays.asList(String.class,String.class,String.class,String.class,String.class,Rol.class));
        this.setElements(usuarios);
        
    }

        @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usr = this.getElements().get(rowIndex);

        switch (columnIndex ){

            case 0:return usr.getUsername();
            case 1: return usr.getInformacionPersonal().getNombres();
            case 2: return usr.getInformacionPersonal().getApellidos();
            case 3: return usr.getInformacionPersonal().geteMail();
            case 4: return usr.getInformacionPersonal().getTelefonoFijo();
            case 5: return usr.getRol();
            default: return "";

        }
    }
}

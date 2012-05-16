package com.gilbertoca.igreja.view.dialogs.common.models;

import com.gilbertoca.igreja.model.Localidad;
import com.gilbertoca.igreja.view.component.BaseTableModel;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class LocalidadTableModel extends BaseTableModel<Localidad>{

    public LocalidadTableModel(List<Localidad> localidades){
        super();
        this.setColumnNames(Arrays.asList("Nombre"));
        this.setFieldNames(Arrays.asList("nombre"));
        this.setColumnClasses(Arrays.asList(String.class));
        this.setElements(localidades);
    }

         @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Localidad loc = this.getElements().get(rowIndex);

        switch (columnIndex ){

            case 0:return loc.getNombre();
            default: return "";

        }
    }
}

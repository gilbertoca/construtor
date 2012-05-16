package com.gilbertoca.igreja.view.dialogs.common.models;

import com.gilbertoca.igreja.model.Region;
import com.gilbertoca.igreja.view.component.BaseTableModel;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class RegionTableModel extends BaseTableModel<Region>{

    public RegionTableModel(List<Region> regiones){
        super();
        this.setColumnNames(Arrays.asList("Nombre"));
        this.setFieldNames(Arrays.asList("nombre"));
        this.setColumnClasses(Arrays.asList(String.class));
        this.setElements(regiones);
    }

         @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Region reg = this.getElements().get(rowIndex);

        switch (columnIndex ){

            case 0:return reg.getNombre();
            default: return "";

        }
    }
}

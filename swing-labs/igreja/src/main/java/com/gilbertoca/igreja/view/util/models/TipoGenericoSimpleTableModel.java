package com.gilbertoca.igreja.view.util.models;


import com.gilbertoca.igreja.model.util.TipoGenericoSimple;
import com.gilbertoca.igreja.view.component.BaseTableModel;
import java.util.Arrays;
import java.util.List;

public class TipoGenericoSimpleTableModel extends BaseTableModel {

    public TipoGenericoSimpleTableModel(List<?extends TipoGenericoSimple> caracteresUsuarios) {
       
        this.setColumnNames(Arrays.asList("Descripcion"));
        this.setColumnClasses(Arrays.asList(String.class));
        //this.setFieldNames(Arrays.asList("descripcion"));

         this.setElements(caracteresUsuarios);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        TipoGenericoSimple caracter  = (TipoGenericoSimple) this.getElements().get(rowIndex);

        return caracter.getDescripcion();

    }



   


}


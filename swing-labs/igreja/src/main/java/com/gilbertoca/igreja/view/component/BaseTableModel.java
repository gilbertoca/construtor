/*
 * BaseTableModel implementa la logica necesaria para poder
 * facilmente instanciar un modelo de JTable, solo con propocionar
 * una lista de objetos, el nombre de sus atributos, el nombre de las columnas
 * y las clases de cada atributo.
 * 
 */

package com.gilbertoca.igreja.view.component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * Modelo basico de JTable.
 * 
 * @author José Larghi
 */
public class BaseTableModel<T> extends AbstractTableModel implements TableModel {

    private List<T> elements;
    
    private List<String> columnNames;
    
    private List<? extends Class> columnClasses;
    
    private List<String> fieldNames;
    
    public BaseTableModel(List<T> elements,List<String> columnNames, List<? extends Class> columnClasses, List<String> fieldNames ) {
        this.setColumnClasses(columnClasses);
        this.setColumnNames(columnNames);
        this.setFieldNames(fieldNames);
        this.setElements(elements);
    }

    public BaseTableModel() { }

    public int getRowCount() {
        return this.getElements().size();
    }

    public int getColumnCount() {
        return this.getColumnNames().size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.getColumnNames().get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (this.getColumnClasses() == null) {
            return String.class;
        }
        return this.getColumnClasses().get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = this.getElements().get(rowIndex);
        try {
            return obj.getClass().getDeclaredMethod("get" + toCamelCase(this.getFieldNames().get(columnIndex))).invoke(obj);
        } catch (Exception ex) {
            Logger.getLogger(BaseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Object obj = this.getElements().get(rowIndex);
        try {
            obj.getClass().getDeclaredMethod("set" + toCamelCase(this.getFieldNames().get(columnIndex)),this.getColumnClass(columnIndex)).invoke(obj,aValue);
        } catch (Exception ex) {
            Logger.getLogger(BaseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    /*
     * Modificado del original;
     *
     * Original: public List<? extends T> getElements() {
     */
     public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
    
    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<? extends Class> getColumnClasses() {
        return columnClasses;
    }

    public void setColumnClasses(List<? extends Class> columnClasses) {
        this.columnClasses = columnClasses;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }
    
    private String toCamelCase(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
    
}

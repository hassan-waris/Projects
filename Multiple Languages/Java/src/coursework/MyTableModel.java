/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import coursework.Cheese;

/**
 *
 * @author Hassan
 */
public class MyTableModel extends AbstractTableModel {

    ArrayList<Cheese> data;
    ArrayList columnNames = new ArrayList();
    
    public MyTableModel() {
    }
    
    public void setData(ArrayList<Cheese> data) {
        this.data = data;
        fireTableDataChanged();
        
    }
      public void setColumn(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
        fireTableDataChanged();
        
    }

    @Override
    public int getRowCount() {
        fireTableDataChanged();
        return data.size();
        
    }

    @Override
    public int getColumnCount() {
        fireTableDataChanged();
        return columnNames.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        fireTableDataChanged();
        return data.get(rowIndex).getRows(columnIndex); 
            
    }
    
//    @Override
//    public String getColumnName(int columnIndex) {
//        return getColumnName(columnIndex);
//    }
//
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }



//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void addTableModelListener(TableModelListener l) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void removeTableModelListener(TableModelListener l) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}

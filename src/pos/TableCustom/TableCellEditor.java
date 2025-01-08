/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.TableCustom;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class TableCellEditor extends DefaultCellEditor{
    
    private DefaultTableModel model;
    public TableCellEditor() {
        super(new JCheckBox());
    }
    

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        model = (DefaultTableModel)value;
        JScrollPane js = new JScrollPane();
        JTable jTable = new JTable(model);
        jTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(jTable));
//        jTable.setTableHeader(null);   
        js.setViewportView(jTable);
        return js;
       
    }

    @Override
    public Object getCellEditorValue() {
        return model; 
    }
    
    
}

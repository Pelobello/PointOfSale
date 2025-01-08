/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.TableCustom;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TableCellRenderer implements javax.swing.table.TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DefaultTableModel model =(DefaultTableModel)value;
        JScrollPane js = new JScrollPane();
        JTable table = new JTable(model);
          table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
//          table.setTableHeader(null);
        js.setViewportView(table);
        return js;
    }
    
}

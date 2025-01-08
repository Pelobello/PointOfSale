
package pos.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import pos.Factory.OrderInterface;
import pos.Factory.ProductsFactory;
import pos.Factory.ProductsInterface;
import pos.Model.ItemModel;
import pos.Model.OrdersModel;
import pos.TableCustom.TableCellEditor;
import pos.TableCustom.TableCellRenderer;
import pos.TableCustom.TableHeaderAlignment;

/**
 *
 * @author USER
 */
public class HistoryForms extends javax.swing.JPanel {

     private ProductsFactory productsFactory = new ProductsFactory();
    private OrderInterface orderController = productsFactory.CreateOrderController();
    public HistoryForms() {
        initComponents();
         init();
    
      

        populateOrdersToTable();
       
    }
    private void init(){
        historyTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(historyTable));
          historyTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        historyTable.putClientProperty(FlatClientProperties.STYLE, ""
                 + "rowHeight:70;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
    }
          private void populateOrdersToTable() {
    historyTable.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer());
    historyTable.getColumnModel().getColumn(5).setCellEditor(new TableCellEditor());

    // Update historyTable
    DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
    model.setRowCount(0);

    List<OrdersModel> listOfOrders = orderController.getOrderedProducts("");

    for (OrdersModel listOfOrder : listOfOrders) {
        // Create a new subtableData for each order
        DefaultTableModel subtableData = new DefaultTableModel();
        subtableData.addColumn("Product ID");
        subtableData.addColumn("Title");
        subtableData.addColumn("Category");
        subtableData.addColumn("Quantity");
        subtableData.addColumn("Price");

        // Get the list of items for the current order
        List<ItemModel> items = listOfOrder.getList_of_products();  // Get the list of items for the order

        // Add items to subtableData
        for (ItemModel item : items) {
            subtableData.addRow(new Object[]{
                item.getItemId(),
                item.getTitle(),
                item.getCategory(),
                item.getQuantity(),
                item.getPrice()
            });
        }

        // Add the order data along with the subtable (subtableData) to the main table
        model.addRow(new Object[]{
            listOfOrder.getOrdersId(),
            listOfOrder.getCashier(),
            listOfOrder.getTotal(),
            listOfOrder.getCash(),
            listOfOrder.getChange(),
            subtableData  // This will now be a different subtable for each order
        });
    }
}




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        historyTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "OrdersID", "Cashier", "Total", "Cash", "Change", "Detail"
            }
        ));
        historyTable.setRowHeight(300);
        jScrollPane1.setViewportView(historyTable);
        if (historyTable.getColumnModel().getColumnCount() > 0) {
            historyTable.getColumnModel().getColumn(0).setMinWidth(70);
            historyTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            historyTable.getColumnModel().getColumn(0).setMaxWidth(70);
            historyTable.getColumnModel().getColumn(1).setMinWidth(150);
            historyTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            historyTable.getColumnModel().getColumn(1).setMaxWidth(150);
            historyTable.getColumnModel().getColumn(2).setMinWidth(70);
            historyTable.getColumnModel().getColumn(2).setPreferredWidth(70);
            historyTable.getColumnModel().getColumn(2).setMaxWidth(70);
            historyTable.getColumnModel().getColumn(3).setMinWidth(70);
            historyTable.getColumnModel().getColumn(3).setPreferredWidth(70);
            historyTable.getColumnModel().getColumn(3).setMaxWidth(70);
            historyTable.getColumnModel().getColumn(4).setMinWidth(70);
            historyTable.getColumnModel().getColumn(4).setPreferredWidth(70);
            historyTable.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable historyTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

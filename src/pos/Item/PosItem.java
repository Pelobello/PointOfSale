
package pos.Item;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import pos.Categories.Categories;
import pos.Model.ItemModel;


public class PosItem extends javax.swing.JPanel {

  
    public ItemModel getData() {
        return data;
    }

    private static final DecimalFormat df = new DecimalFormat("00.00");
    public void setData(ItemModel data) {
        this.data = data;
       
        
      
              imageItem.setImage(data.getItemImage().getIcon());
        
        lbTitle.setText(data.getTitle());
        if (data.getStatus().equals("Available")) {
          lbStatus.setForeground(Color.GREEN);
        }else{
             lbStatus.setForeground(Color.RED);
        }
        lbStatus.setText(data.getStatus());
        String priceData = df.format(data.getPrice());
        lbPrice.setText("$ "+priceData);
         Categories category = data.getCategory();
         lbCategory.setText(category != null ? category.name() : "Unknown");
    }

   
    public PosItem() {
        initComponents();
          jPanel1.putClientProperty(FlatClientProperties.STYLE,"arc: 15");
        setOpaque(false);
    }

   private ItemModel data;
   
   
   public JButton getUpdateBtn(){
       return updateBtn;
   }
    public JButton getOrderBtn(){
       return orderBtn;
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imageItem = new pos.Swing.PictureBox();
        lbTitle = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        orderBtn = new javax.swing.JButton();
        lbCategory = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(102, 102, 102));
        lbTitle.setText("Title");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(102, 102, 102));
        lbPrice.setText("Price");

        lbStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(102, 102, 102));
        lbStatus.setText("Status");

        updateBtn.setBackground(new java.awt.Color(0, 102, 0));
        updateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("UPDATE");
        updateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        orderBtn.setBackground(new java.awt.Color(0, 0, 102));
        orderBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderBtn.setForeground(new java.awt.Color(255, 255, 255));
        orderBtn.setText("ORDER");
        orderBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbCategory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCategory.setForeground(new java.awt.Color(102, 102, 102));
        lbCategory.setText("Category");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageItem, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderBtn)
                    .addComponent(updateBtn))
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
    private pos.Swing.PictureBox imageItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JButton orderBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}

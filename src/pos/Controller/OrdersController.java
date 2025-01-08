/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pos.Categories.Categories;
import pos.Database.GetDatabaseConnection;
import pos.Factory.OrderInterface;
import pos.Model.ItemModel;
import pos.Model.OrdersModel;

public class OrdersController implements OrderInterface{
 private PreparedStatement ps;
    private ResultSet rs;
    GetDatabaseConnection databaseConnection;
    public OrdersController() {
         databaseConnection = new GetDatabaseConnection();
    }

    @Override
    public void createOrder(OrdersModel orders) {
        try {
           String sql = "INSERT INTO orders_table (OrdersID, Cashier, ProductID, Title, Category,Quantity, Price, Cash, CChange, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
           ps = databaseConnection.prepareStatement(sql);
           
            for (ItemModel product : orders.getList_of_products()) {
                ps.setString(1, orders.getOrdersId());
                ps.setString(2, orders.getCashier());
                ps.setString(3, product.getItemId());
                ps.setString(4, product.getTitle());
                ps.setString(5, product.getCategory().toString());
                ps.setInt(6, product.getQuantity());
                ps.setDouble(7, product.getPrice());
                ps.setDouble(8, orders.getCash());
                ps.setDouble(9, orders.getChange());
                ps.setDouble(10, orders.getTotal());
                
                ps.addBatch();
                
                
            }
           
           ps.executeBatch();
            JOptionPane.showMessageDialog(null, "Success");
           
           
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error!");
        }finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }

    @Override
    public void updateProductsOrdered(OrdersModel orders) {
        try {
            String sql = "UPDATE products_table SET Product_Quantity = Product_Quantity - ? WHERE Product_ID = ?";
            ps = databaseConnection.prepareStatement(sql);
            
             for (ItemModel product : orders.getList_of_products()){
                 ps.setInt(1, product.getQuantity());
                 ps.setString(2, product.getItemId());
                 
                 ps.addBatch();
             }
               ps.executeBatch();
             
        } catch (Exception e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(null, "error!");
        }finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    }

    @Override
 public List<OrdersModel> getOrderedProducts(String orderNumber) {
    List<OrdersModel> listOfOrders = new ArrayList<>();
    try {
        // Main query to get order information
        String sql = "SELECT OrdersID, MAX(Cashier) AS Cashier, MAX(Cash) AS Cash, MAX(CChange) AS CChange, MAX(Total) AS Total " +
                     "FROM point_of_sale_db.orders_table GROUP BY OrdersID ORDER BY OrdersID;";

        // Detail query to get items for each order
        String sqlDetail = "SELECT ProductID,Title, Category, Quantity, Price FROM point_of_sale_db.orders_table WHERE OrdersID = ?;";

        // Prepare statements
        ps = databaseConnection.prepareStatement(sql);
        PreparedStatement pd = databaseConnection.prepareStatement(sqlDetail);

        // Execute the main query
        rs = ps.executeQuery();

        // Loop through each order
        while (rs.next()) {
            String orderId = rs.getString("OrdersID");
            String cashier = rs.getString("Cashier");
            double cash = rs.getDouble("Cash");
            double change = rs.getDouble("CChange");
            double total = rs.getDouble("Total");

            // Prepare the detail query using the orderId
            pd.setString(1, orderId);
            ResultSet rd = pd.executeQuery();

            // Create a list to hold ItemModels for this specific order
            List<ItemModel> list_of_products = new ArrayList<>();

            // Loop through all items for the current order
            while (rd.next()) {
                String title = rd.getString("Title");
                String categoryStr = rd.getString("Category");
                String productID = rd.getString("ProductID");
                Categories categories = Categories.valueOf(categoryStr); // Assuming Categories enum
                int qty = rd.getInt("Quantity");
                double price = rd.getDouble("Price");

                // Create and populate ItemModel
                ItemModel itemModel = new ItemModel();
                itemModel.setItemId(productID);
                itemModel.setTitle(title);
                itemModel.setCategory(categories);
                itemModel.setQuantity(qty);
                itemModel.setPrice(price);

                // Add the item to the list for this specific order
                list_of_products.add(itemModel);
            }

            // Now that we have the list of products for this order, create the OrdersModel
            OrdersModel ordersModel = new OrdersModel(orderId, cashier, list_of_products, total, cash, change);

            // Add the order to the list of orders
            listOfOrders.add(ordersModel);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    // Return the list of orders, each containing its own list of products
    return listOfOrders;
}

    
}

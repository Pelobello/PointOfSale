/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Controller;

import com.mysql.cj.jdbc.Blob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.coobird.thumbnailator.Thumbnails;
import pos.Categories.Categories;
import pos.Database.GetDatabaseConnection;
import pos.Factory.ProductsInterface;
import pos.Model.FileImageModel;
import pos.Model.ItemModel;


public class ProductsController implements ProductsInterface{
    private PreparedStatement ps;
    private ResultSet rs;
    GetDatabaseConnection databaseConnection;
    public ProductsController() {
        databaseConnection = new GetDatabaseConnection();
    }

    @Override
    public void createProducts(ItemModel data) {
        try {
            String sql = "Insert into products_table(Product_ID,Product_Title,products_Category,Product_Image,Product_Price,Product_Quantity,Product_Status)values(?,?,?,?,?,?,?)";
            ps = databaseConnection.prepareStatement(sql);
            
            ps.setString(1, data.getItemId());
            ps.setString(2, data.getTitle());
            ps.setString(3, data.getCategory().toString());
              
            if (data.getItemImage()!=null) {
                ps.setBytes(4, getByteImage(data.getItemImage().getPath()));
            }else{
                ps.setBytes(4, null);
            }
            ps.setDouble(5, data.getPrice());
            ps.setInt(6, data.getQuantity());
            ps.setString(7, data.getStatus());
            
            ps.execute();
            System.out.println("Succesfully Added");
            
        } catch (Exception e) {
         e.printStackTrace();
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
    public void updateProducts(ItemModel Productsdata) {
        try {
            boolean isEditProductImage = Productsdata.getItemImage()!=null;
            String sql =isEditProductImage 
                    ?"UPDATE products_table SET Product_Title= ?,products_Category = ?, Product_Price = ?, Product_Quantity = ?, Product_Status = ?, Product_Image = ?,DateUpdated = CURRENT_TIMESTAMP WHERE Product_ID = ?"
                    :"UPDATE products_table SET Product_Title= ?,products_Category = ?, Product_Price = ?, Product_Quantity = ?, Product_Status = ?,DateUpdated = CURRENT_TIMESTAMP WHERE Product_ID = ?";
            ps = databaseConnection.prepareStatement(sql);
            
            ps.setString(1, Productsdata.getTitle());
            ps.setString(2, Productsdata.getCategory().toString());
              ps.setDouble(3, Productsdata.getPrice());
               ps.setInt(4, Productsdata.getQuantity());
               ps.setString(5, Productsdata.getStatus());
               
            if (isEditProductImage) {
              if (Productsdata.getItemImage()!=null) {
                ps.setBytes(6, getByteImage((File)Productsdata.getItemImage().getPath()));
            }else{
                ps.setBytes(6, null);
            }
             ps.setString(7,Productsdata.getItemId());
            }else{
               ps.setString(6,Productsdata.getItemId());
            }
            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
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
    public void deleteProducts(ItemModel Productsdata) {
        try {
            String sql = "UPDATE products_table SET DateDeleted = CURRENT_TIMESTAMP WHERE Product_ID = ?";
            ps = databaseConnection.prepareStatement(sql);
            
            ps.setString(1, Productsdata.getItemId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<ItemModel> getProducts(String search,String categories,String orderBy) {
          List<ItemModel> listOfProducts = new ArrayList<>();
    try {
   
        String sql = "SELECT * FROM products_table WHERE DateDeleted IS NULL order by case when products_Category LIKE ? then 0 else 1 end "+orderBy+", products_Category "+orderBy;

        // Prepare the statement
        ps = databaseConnection.prepareStatement(sql);
        
        ps.setString(1, "%" + categories + "%"); 

        // Execute the query
        rs = ps.executeQuery();

        while (rs.next()) {
            Blob blob = (Blob) rs.getBlob("Product_Image");
            ImageIcon icon = null;
            String categoryString = rs.getString("products_Category");
            Categories category = Categories.valueOf(categoryString);

            if (blob != null) {
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                icon = new ImageIcon(imageBytes);
            } else {
                icon = new ImageIcon(getClass().getResource("/pos/Image/default-product.png"));
            }

            FileImageModel fileImageModel = new FileImageModel();
            fileImageModel.setIcon(icon);

            ItemModel productDataModel = new ItemModel(
                rs.getString("Product_ID"), 
                category, 
                fileImageModel, 
                rs.getString("Product_Title"),
                rs.getDouble("Product_Price"), 
                rs.getString("Product_Status"), 
                rs.getInt("Product_Quantity")
            );

            listOfProducts.add(productDataModel);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    return listOfProducts;
    }

       private byte[] getByteImage(File file) throws IOException {
        BufferedImage image = Thumbnails.of(file)
                .width(500)
                .outputQuality(0.7f)
                .asBufferedImage();
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", out);
            byte[] data = out.toByteArray();
            return data;
        } finally {
            if (out != null) {
                out.close();
            }
        }}

    @Override
    public List<ItemModel> searchProducts(String search) throws SQLException {
        List<ItemModel> listOfProducts = new ArrayList<>();
    try {
   
        String sql = "SELECT * FROM products_table WHERE DateDeleted IS NULL AND Product_Title LIKE ? ORDER BY Product_Title ASC";

        // Prepare the statement
        ps = databaseConnection.prepareStatement(sql);
        
        ps.setString(1, "%" + search + "%"); 

        // Execute the query
        rs = ps.executeQuery();

        while (rs.next()) {
            Blob blob = (Blob) rs.getBlob("Product_Image");
            ImageIcon icon = null;
            String categoryString = rs.getString("products_Category");
            Categories category = Categories.valueOf(categoryString);

            if (blob != null) {
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                icon = new ImageIcon(imageBytes);
            } else {
                icon = new ImageIcon(getClass().getResource("/pos/Image/default-product.png"));
            }

            FileImageModel fileImageModel = new FileImageModel();
            fileImageModel.setIcon(icon);

            ItemModel productDataModel = new ItemModel(
                rs.getString("Product_ID"), 
                category, 
                fileImageModel, 
                rs.getString("Product_Title"),
                rs.getDouble("Product_Price"), 
                rs.getString("Product_Status"), 
                rs.getInt("Product_Quantity")
            );

            listOfProducts.add(productDataModel);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    return listOfProducts;
    }

    
}

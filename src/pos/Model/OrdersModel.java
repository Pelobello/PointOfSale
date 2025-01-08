
package pos.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersModel {

 
    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public List<ItemModel> getList_of_products() {
        return list_of_products;
    }

    public void setList_of_products(List<ItemModel> list_of_products) {
        this.list_of_products = list_of_products;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getChange() {
        return change;
    }
    public void setChange(Double change) {
        this.change = change;
    }

    public OrdersModel(String ordersId, String cashier, List<ItemModel> list_of_products, Double total, Double cash, Double change) {
        this.ordersId = ordersId;
        this.cashier = cashier;
        this.list_of_products = list_of_products;
        this.total = total;
        this.cash = cash;
        this.change = change;
    }

    public OrdersModel() {
    }
   
    private String ordersId;
    private String cashier;
    private List<ItemModel>list_of_products;
    private Double total;
    private Double cash;
    private Double change;
    
   public Object[] toObject() {
            return new Object[] { ordersId, cashier, total, cash, change};
    
   
}
 


}

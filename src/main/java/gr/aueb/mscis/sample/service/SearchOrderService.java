package gr.aueb.mscis.sample.service;


import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Order;

import java.util.List;

public class SearchOrderService {

    private OrderService os;

    public SearchOrderService(OrderService os) {
        this.os = os;
    }

    public void searchAllOrders(){
        List<Order> orders = os.findAllOrders();
        for (Order order : orders) {
            showOrderDetails(order);
            if(order.getLineItems().size() != 0){
                for (LineItem li : order.getLineItems()) {
                    showOrderProductNameAndQuantity(li);
                }
            }
            System.out.println("-----------------------------------------------");
        }
    }

    private void showOrderDetails(Order order) {
        System.out.println("Order Date: " + order.getOrderdate());
        System.out.println("Order Status: " + order.getStatus());
        System.out.println("Total Cost: " + order.getTotal());
    }

    private void showOrderProductNameAndQuantity(LineItem lineItem) {
        System.out.println("Product/Quantity Requested: " + lineItem.getProduct().getName() + " / " + lineItem.getQuantity());
    }
}

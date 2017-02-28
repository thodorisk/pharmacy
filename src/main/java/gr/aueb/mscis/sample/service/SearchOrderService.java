package gr.aueb.mscis.sample.service;


import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.util.SimpleCalendar;

import java.util.List;
import java.util.Set;

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
    
    public void searchAllOrdersByPharmacist(String afm){
        List<Order> orders = os.findOrdersByPharmacist(afm);
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
    
    public void searchAllOrdersByPharmacist(String afm, SimpleCalendar startDate, SimpleCalendar endDate){
        List<Order> orders = os.findOrdersByPharmacist(afm, startDate, endDate);
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
    
    public void searchSalesOfProductPerPeriod(String eof, SimpleCalendar startDate, SimpleCalendar endDate) {
    	
    	
    	for (LineItem li : os.salesOfProductPerPeriod(eof,startDate,endDate)) {
    		System.out.println("Sales quantity of " + li.getProduct().getName() + ": " + li.getQuantity() + " for the period " + startDate + " - " + endDate);
    	}
    	System.out.println("-----------------------------------------------");
    	
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

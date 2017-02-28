package gr.aueb.mscis.sample.service;

import java.util.List;

import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.util.SimpleCalendar;


public class StatisticService {

	    private OrderService os;

	    public StatisticService(OrderService os) {
	        this.os = os;
	    }

	    public double earningsByPharmacist(String afm){
	    	
	    	double result = 0;
	    	List<Order> orders = os.findOrdersByPharmacist(afm);
	    	if (hasOrders(orders)) {
	    		result = getTotal(orders);
	    	}
	    	
	    	return result;
	    }
	    
	    public double earningsByPharmacist(String afm, SimpleCalendar startDate, SimpleCalendar endDate){
	    	
	    	double result = 0;
	    	List<Order> orders = os.findOrdersByPharmacist(afm, startDate, endDate);
	    	if (hasOrders(orders)) {
	    		result = getTotal(orders);
	    	}
	    	
	    	return result;
	    }
	    
	    public double totalSales(){
	    	
	    	double result = 0;
	    	List<Order> orders = os.findAllOrders();
	    	if (hasOrders(orders)) {
	    		result = getTotal(orders);
	    	}
	    	
	    	return result;
	    }
	    
	    public List<Order> historyOrderPerPharmacist(String afm, SimpleCalendar startDate, SimpleCalendar endDate) {
	    	
	    	return os.findOrdersByPharmacist(afm, startDate, endDate);
	       
	    }
	    
	    public double salesPerProductPerPeriod(String eof, SimpleCalendar startDate, SimpleCalendar endDate) {
	    	
	    	double result = 0;
	    	for (LineItem li : os.salesOfProductPerPeriod(eof,startDate,endDate)) {
	    		result += li.getQuantity();
	    	}

	    	return result;	    	
	    }
	    
	    
	    private boolean hasOrders(List<Order> orders) {
	        boolean answer = false;
	        if (orders.size() > 0) {
	            answer = true;
	        }
	        return answer;
	    }
	    
	    public double getTotal(List<Order> orders){
	        int total = 0;

	        for (Order order : orders) {
	        	total += order.getTotal();
	        }
	        return total;
	    }
	    
	    
}

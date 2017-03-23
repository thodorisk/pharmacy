package gr.aueb.mscis.sample.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.OrderState;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.util.SimpleCalendar;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class OrderInfo is a utility class for JAX-RS implementation.
 */

@XmlRootElement
public class OrderInfo {
	
    private Integer id;
    private String orderdate;
    private String status;
    private Double total;
    
    
    
	public OrderInfo() {
	}

	public OrderInfo(Integer id, String orderdate, String status, Double total) {
		this(orderdate,status,total);
		this.id = id;

	}

	public OrderInfo(String orderdate, String status, Double total) {
		super();
		this.orderdate = orderdate;
		this.status = status;
		this.total = total;
	}
	
	public OrderInfo(Order order) {
		id = order.getId();
		orderdate = order.getOrderdate().toString(); 
		status = order.getStatus().toString();
		total = order.getTotal();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	public static OrderInfo wrap(Order o) {
		return new OrderInfo(o);
	}
	
	public static List<OrderInfo> wrap(List<Order> orders) {

		List<OrderInfo> orderInfoList = new ArrayList<>();

		for (Order o : orders) {
			orderInfoList.add(new OrderInfo(o));
		}

		return orderInfoList;

	}

	public Order getOrder(EntityManager em) {

		Order order = null;
		OrderService cs = new OrderService(em);
		if (id != null) {
			order = cs.findOrderById(id);
		} else {
			order = new Order();
		}
		
		OrderState orderstatus = null;
		if (status == "PENDING")
			orderstatus = OrderState.PENDING;
		else if(status == "COMPLETED")
			orderstatus = OrderState.COMPLETED;
		
		
		String[] parts = orderdate.split("-");
				
		order.setOrderdate(new SimpleCalendar(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
		order.setStatus(orderstatus);
		order.setTotal(total);

		return order;
	}
}

package gr.aueb.mscis.sample.service;


import gr.aueb.mscis.sample.LibraryException;
import gr.aueb.mscis.sample.model.Account;
import gr.aueb.mscis.sample.model.Cart;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.OrderState;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import gr.aueb.mscis.sample.util.SystemDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderService {

    private EntityManager em;

    public OrderService(EntityManager em) {
        this.em = em;
    }

    public Order findOrderById(int id) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Order order = null;
        try {
            order = em.find(Order.class, id);
            tx.commit();
        } catch (NoResultException ex) {
            tx.rollback();
        }
        return order;
    }

    public List<Order> findAllOrders() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Order> results = null;

        results = em.createQuery("select o from Order o").getResultList();

        tx.commit();
        return results;
    }
    
    public List<LineItem> salesOfProductPerPeriod(String eof, SimpleCalendar startDate, SimpleCalendar endDate) {
    	
    	try {

    		if (startDate==null || endDate==null) {
    			throw new LibraryException("Start Date or End Date cannot be null.");
    		}

    		if (startDate.after(endDate)) {
    			throw new LibraryException("End Date cannot precede Start Date.");
    		}
    	}
    	catch(LibraryException e) {
    		return null;
    	}
    	List<LineItem> results = new ArrayList<LineItem>();
    	CatalogService cs = new CatalogService(em);
    	List<LineItem> tempresults = new ArrayList<LineItem>(cs.findProductByEOF(eof).get(0).getLineItems());
    	for (LineItem li : tempresults) {
    		//if (order.getOrderdate().after(startDate))
    		if (li.getOrder().getOrderdate().equals(startDate) || li.getOrder().getOrderdate().equals(endDate)) {
    			results.add(li);
    		}

    		if (li.getOrder().getOrderdate().after(startDate) && li.getOrder().getOrderdate().before(endDate)) {
    			results.add(li);
    		}

    	}
        return results;
    }
        
    public List<Order> findOrdersByPharmacist(String afm) {
    	
    	List<Order> results = null;
    	PharmacistService ps = new PharmacistService(em);
    	List<Pharmacist> pharmacist = ps.findPharmacistsByAFM(afm);
    	results = new ArrayList<Order>(pharmacist.get(0).getAccount().getOrders());
    	  		
    	return results;
    }
    
    public List<Order> findOrdersByPharmacist(String afm, SimpleCalendar startDate, SimpleCalendar endDate) {
    	
    	try {

    		if (startDate==null || endDate==null) {
    			throw new LibraryException("Start Date or End Date cannot be null.");
    		}

    		if (startDate.after(endDate)) {
    			throw new LibraryException("End Date cannot precede Start Date.");
    		}
    	}
    	catch(LibraryException e) {
    		return null;
    	}
    	   	
    	List<Order> results = new ArrayList<Order>();
    	PharmacistService ps = new PharmacistService(em);
    	List<Pharmacist> pharmacist = ps.findPharmacistsByAFM(afm);
    	List<Order> tempresults = new ArrayList<Order>(pharmacist.get(0).getAccount().getOrders());
    	for (Order order : tempresults) {
    		//if (order.getOrderdate().after(startDate))
    		if (order.getOrderdate().equals(startDate) || order.getOrderdate().equals(endDate)) {
    			results.add(order);
    		}

    		if (order.getOrderdate().after(startDate) && order.getOrderdate().before(endDate)) {
    			results.add(order);
    		}

    	}
    	return results;
    }
    
      
    public double calculateOrder(int order_id) {
    	double total = 0.0;
    	Order order_found = findOrderById(order_id);
    	List<LineItem> lineitems = new ArrayList<LineItem>(order_found.getLineItems());
    	for (LineItem lineitem : lineitems) 
    	{	
    		double finalprice = 0.0;
    		//System.out.println(lineitem.getProduct().getOnSale());
    		if (lineitem.getProduct().getOnSale() != (null))
    		{
    			finalprice = (lineitem.getProduct().getPrice()) - (lineitem.getProduct().getPrice() * lineitem.getProduct().getOnSale().getDiscount() / 100.0);
    			total += finalprice * lineitem.getQuantity();
    		}
    		else
    		{
    			finalprice = lineitem.getProduct().getPrice();
    			total += finalprice * lineitem.getQuantity();
    		}
    	}
    	
    	order_found.setTotal(total);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(order_found); 	    			
		tx.commit();
		
    	return total;
    }
    
    
    public LineItem addLineItem(int orderid, String pharmacist_afm, String product_eof, int desired_quantity) {

    	LineItem lineitem = new LineItem(desired_quantity);
    	
    	if (orderid == 0)
    		createOrder(pharmacist_afm);
    	else {

    		CatalogService cs = new CatalogService(em);
    		if (desired_quantity > cs.getStock(product_eof))
    		{
    			throw new LibraryException("There is not enough Stock for this product!");
    		}
    		else
    		{     		
    			
    			Order order_found = findOrderById(orderid);
    			Cart cart_found = order_found.getAccount().getPharmacist().getCart();
    			List<Product> listOfProducts = cs.findProductByEOF(product_eof);
    			Product product = listOfProducts.get(0);
    			List<Lot> lots = new ArrayList<Lot>(product.getLots());

    			int remaining_desired_quantity = desired_quantity;

    			for (Lot lot : lots) {
    				if (remaining_desired_quantity <= lot.getQuantity() && (lot.getQuantity() !=0)) {
    					lot.setQuantity(lot.getQuantity() - remaining_desired_quantity);
    					lineitem.setOrder(order_found);
    					order_found.getLineItems().add(lineitem);
    					lineitem.getLots().add(lot);    				
    					lot.getLineItems().add(lineitem);
    					lineitem.setProduct(product);
    					product.getLineItems().add(lineitem);
    					cart_found.getLineitems().add(lineitem);
    					lineitem.setCart(cart_found);

    					EntityTransaction tx = em.getTransaction();
    					tx.begin();
    					em.persist(lineitem);
    					em.persist(order_found);
    					em.persist(lot);
    					em.persist(product);  	    			
    					tx.commit();

    					break;
    				}
    				else if (remaining_desired_quantity >= lot.getQuantity() && (lot.getQuantity() !=0)) {
    					remaining_desired_quantity = remaining_desired_quantity - lot.getQuantity();
    					lot.setQuantity(0);
    					lineitem.getLots().add(lot);    				
    					lot.getLineItems().add(lineitem);

    					EntityTransaction tx = em.getTransaction();
    					tx.begin();
    					em.persist(lineitem);
    					em.persist(lot);
    					tx.commit();
    				}
    			}
    		}
    	}
    	
    	return lineitem;
    }
      
    public void updateOrder(String pharmacist_afm) {
    	
    }
    
    public int createOrder(String pharmacist_afm) {
    	
    //Find account based on AFM of a pharmacist
   	PharmacistService ps = new PharmacistService(em);
   	List<Pharmacist> pharmacist = ps.findPharmacistsByAFM(pharmacist_afm);
   	Pharmacist thepharmacist = pharmacist.get(0);
   	Account pharmacist_account = thepharmacist.getAccount();
   	
   	Cart newCart = new Cart(SystemDate.now());
   	
   	Order neworder = new Order(SystemDate.now(), OrderState.PENDING, 0.0);
   	
   	pharmacist_account.getOrders().add(neworder);
   	neworder.setAccount(pharmacist_account);
   	newCart.setPharmacist(thepharmacist);
   	thepharmacist.setCart(newCart);  
   	EntityTransaction tx = em.getTransaction();
   	try {
   		tx.begin();
   		em.persist(neworder);
   		em.persist(pharmacist_account);
   		em.persist(thepharmacist);
   		tx.commit();
   	} catch (NoResultException ex) {
   		tx.rollback();
   	}

   	return neworder.getId();
    }
}

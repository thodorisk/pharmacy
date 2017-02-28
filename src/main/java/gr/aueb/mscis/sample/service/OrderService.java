package gr.aueb.mscis.sample.service;


import gr.aueb.mscis.sample.LibraryException;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.util.SimpleCalendar;

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
}

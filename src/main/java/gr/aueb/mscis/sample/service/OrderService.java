package gr.aueb.mscis.sample.service;


import gr.aueb.mscis.sample.LibraryException;
import gr.aueb.mscis.sample.model.Account;
import gr.aueb.mscis.sample.model.Cart;
import gr.aueb.mscis.sample.model.CartItem;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		//tx.begin();
		Order order = null;
		try {
			order = em.find(Order.class, id);
			//  tx.commit();
		} catch (NoResultException ex) {
			//   tx.rollback();
			System.out.println("ERROR");
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

		

			if (startDate==null || endDate==null) {
				throw new LibraryException("Start Date or End Date cannot be null.");
			}

			if (startDate.after(endDate)) {
				throw new LibraryException("End Date cannot precede Start Date.");
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
	
	public List<CartItem> showCartByPharmacist(String afm) {

		List<CartItem> results = null;
		PharmacistService ps = new PharmacistService(em);
		List<Pharmacist> pharmacist = ps.findPharmacistsByAFM(afm);
		results = new ArrayList<CartItem>(pharmacist.get(0).getCart().getCartitems());
		return results;
	}

	public List<Order> findOrdersByPharmacist(String afm, SimpleCalendar startDate, SimpleCalendar endDate) {

			if (startDate==null || endDate==null) {
				throw new LibraryException("Start Date or End Date cannot be null.");
			}

			if (startDate.after(endDate)) {
				throw new LibraryException("End Date cannot precede Start Date.");
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

	//Υπολογισμός του συνολικού κόστους της παραγγελίας βάση
	//της τιμής, της ποσότητας και της έκπτωσης 
	//(αν υπάρχει σε συγκεκριμένο χρονικό διάστημα) σε κάθε προϊόν
	public double calculateOrder(int order_id) {
		double total = 0.0;
		Order order_found = findOrderById(order_id);
		List<LineItem> lineitems = new ArrayList<LineItem>(order_found.getLineItems());
		for (LineItem lineitem : lineitems) 
		{	
			double finalprice = 0.0;
			if ((lineitem.getProduct().getOnSale() != null) && order_found.getOrderdate().equals(lineitem.getProduct().getOnSale().getStartdate())  ||
					((lineitem.getProduct().getOnSale() != null) && order_found.getOrderdate().equals(lineitem.getProduct().getOnSale().getEnddate())) ||
					(((lineitem.getProduct().getOnSale() != null)	&& (order_found.getOrderdate().before(lineitem.getProduct().getOnSale().getEnddate()))) &&
					(order_found.getOrderdate().after(lineitem.getProduct().getOnSale().getStartdate()))))
			//if (lineitem.getProduct().getOnSale() != null) 			
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

	//Προσθήκη μιας γραμμής παραγγελίας σε μια υπάρχουσα παραγγελία
	//με συγκεκριμένο order_id
	public LineItem addLineItem(int orderid, String product_eof, int desired_quantity) {

		LineItem lineitem = new LineItem(desired_quantity);

		CatalogService cs = new CatalogService(em);
		if (desired_quantity > cs.getStock(product_eof))
		{
			throw new LibraryException("There is not enough Stock for this product!");
		}
		else
		{     		

			Order order_found = findOrderById(orderid);
			
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


		return lineitem;
	}
	
	//Προσθήκη ή ενημέρωση ενός cartItem σε ένα συγκεκριμένο
	//καλάθι με cartitem_id.
	public CartItem updateCartItem(int order_id, String product_eof, int desired_quantity, int cartitem_id) {
		CatalogService cs = new CatalogService(em);
		if (desired_quantity > cs.getStock(product_eof))
		{
			throw new LibraryException("There is not enough Stock for this product!");
		}

		Order order_found = findOrderById(order_id);
		Cart cart_found = order_found.getAccount().getPharmacist().getCart();

		//Αν το cartitem_id ισουται με -1 σημαινει ότι ο χρήστης
		//θέλει να προσθέσει ένα νέο cartitem
		if (cartitem_id == -1) {
			CartItem newCartItem = new CartItem(product_eof, desired_quantity);
			newCartItem.setCart(cart_found);
			cart_found.getCartitems().add(newCartItem);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(newCartItem);
			em.persist(cart_found);
			tx.commit();

			return newCartItem;
		}
		//Αν το cartitem_id έχει έναν αριθμό μεγαλύτερο του μηδενός
		//τότε ο χρήστης θέλει να ενημερώσει ένα ήδη υπάρχον cartitem
		//με το συγκεκριμένο cartitem_id
		else {
			CartItem cartItem_found = cs.findCartItemById(cartitem_id);
			cartItem_found.setQuantity(desired_quantity);
			cartItem_found.setEofn(product_eof);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cartItem_found);
			//		em.persist(cart_found);
			tx.commit();

			return cartItem_found;
		}

	}
	
	//Αφαίρεση ενός προϊόντος από το καλάθι
	public void removeCartItem(int cartitem_id) {
		CatalogService cs = new CatalogService(em);
		CartItem cartItem_found = cs.findCartItemById(cartitem_id);
		Cart cart_found = cartItem_found.getCart();
		//cartItem_found.setCart(null);
		cart_found.getCartitems().remove(cartItem_found);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(cart_found);
			em.remove(cartItem_found);
			tx.commit();
		}
		catch (NoResultException ex) {
			tx.rollback();
		}
		
	}
	

	//Δημιουργία καλαθιού και προσωρινής παραγγελίας σε κατάσταση PENDING
	public int createCartOrder(String pharmacist_afm) {

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
		neworder.setCart(newCart);
		newCart.getOrders().add(neworder);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(neworder);
			em.persist(pharmacist_account);
			em.persist(thepharmacist);
			em.persist(newCart);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}

		return neworder.getId();
	}
	
	//Ολοκλήρωση της παραγγελίας. 
	//Τα προϊόντα του καλαθιού μετατρέπονται σε γραμμές
	//παραγγελίας, υπολογίζεται το συνολικό κόστος της 
	//παραγγελίας βάση της κάθε γραμμής παραγγελίας.
	//Τέλος η παραγγελία αλλάζει κατάσταση σε 
	//COMPLETED και το καλάθι του συγκεκριμένου πελάτη 
	//αδειάζει.
	public double completeOrder(List<CartItem> listofcartitems) {
		
		Cart cart_found = listofcartitems.get(0).getCart();
		Pharmacist pharmacist_found = cart_found.getPharmacist();
		Order order_found = new ArrayList<Order>(cart_found.getOrders()).get(0);
		
		for (CartItem cartitem : listofcartitems)
		{
			addLineItem(order_found.getId(), cartitem.getEofn(), cartitem.getQuantity());
		}
		double total = calculateOrder(order_found.getId());
		double roundedtotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		order_found.setTotal(roundedtotal);
		order_found.setStatus(OrderState.COMPLETED);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(order_found);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}
		
		//Reset cart
		order_found.setCart(null);
		pharmacist_found.setCart(null);
		try {
			tx.begin();
			for (CartItem cartitem : cart_found.getCartitems())
				em.remove(cartitem);
			em.persist(pharmacist_found);
			em.persist(order_found);
			em.remove(cart_found);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}
		
		return  order_found.getTotal();
	}
}

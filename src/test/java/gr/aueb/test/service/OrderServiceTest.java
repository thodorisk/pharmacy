package gr.aueb.test.service;

import gr.aueb.mscis.sample.LibraryException;
import gr.aueb.mscis.sample.model.*;
import gr.aueb.mscis.sample.service.CatalogService;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.util.SimpleCalendar;

import org.junit.Before;
import org.junit.Test;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.persistence.Initializer;
import javax.persistence.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderServiceTest {

    private Initializer dataHelper;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @Test
    public void createCartTest() {
    int EXPECTED_CART_SIZE = 2; 
    EntityManager em = JPAUtil.getCurrentEntityManager();
    OrderService os = new OrderService(em);
    int orderid = os.createCartOrder("1234567890");
    os.updateCartItem(orderid, "111", 2, -1);
    os.updateCartItem(orderid, "112", 3, -1);
    //CartItem updatefirstcartitem = os.updateCartItem(orderid, "111", 1, firstcartitem.getId());
    //System.out.println(os.completeOrder(os.showCartByPharmacist("1234567890")) + "	Total");
    assertEquals(EXPECTED_CART_SIZE, os.showCartByPharmacist("1234567890").size());
    }
    
    @Test
    public void cartItemQuantityTest() {
    int EXPECTED_QUANTITY = 2; 
    EntityManager em = JPAUtil.getCurrentEntityManager();
    OrderService os = new OrderService(em);
    int orderid = os.createCartOrder("1234567890");
    os.updateCartItem(orderid, "111", 2, -1);
    //os.updateCartItem(orderid, "112", 3, -1);
    //CartItem updatefirstcartitem = os.updateCartItem(orderid, "111", 1, firstcartitem.getId());
    //System.out.println(os.completeOrder(os.showCartByPharmacist("1234567890")) + "	Total");
    assertEquals(EXPECTED_QUANTITY, os.showCartByPharmacist("1234567890").get(0).getQuantity());
    }
    
    @Test
    public void cartItemUpdateTest() {
    int EXPECTED_QUANTITY = 6; 
    EntityManager em = JPAUtil.getCurrentEntityManager();
    OrderService os = new OrderService(em);
    int orderid = os.createCartOrder("1234567890");
    CartItem cartitem = os.updateCartItem(orderid, "111", 2, -1);
    os.updateCartItem(orderid, "111", 6, cartitem.getId());
    //os.updateCartItem(orderid, "112", 3, -1);
    //CartItem updatefirstcartitem = os.updateCartItem(orderid, "111", 1, firstcartitem.getId());
    //System.out.println(os.completeOrder(os.showCartByPharmacist("1234567890")) + "	Total");
    assertEquals(EXPECTED_QUANTITY, os.showCartByPharmacist("1234567890").get(0).getQuantity());
    }
       
    @Test
    public void createOrderTest() {
    double EXPECTED_ORDER_TOTAL = 31; 
    EntityManager em = JPAUtil.getCurrentEntityManager();
    OrderService os = new OrderService(em);
    int orderid = os.createCartOrder("1234567890");
    os.updateCartItem(orderid, "111", 2, -1);
    os.updateCartItem(orderid, "112", 3, -1);
    //CartItem updatefirstcartitem = os.updateCartItem(orderid, "111", 1, firstcartitem.getId());
    double total =  os.completeOrder(os.showCartByPharmacist("1234567890"));
    assertEquals(EXPECTED_ORDER_TOTAL, total, 1e-2);
    }
    
    //Παραγγελία με ενεργοποιημένη έκπτωση 10%, εντός της περιόδου της έκπτωσης  
    @Test
    public void createOrderOnSale_PeriodTrueTest() {
    double EXPECTED_ORDER_TOTAL = 9; 
    EntityManager em = JPAUtil.getCurrentEntityManager();
    CatalogService cs = new CatalogService(em);
    Product product = cs.findProductByEOF("111").get(0);
    //Set OnSale for Depon product to 10%
    product.setOnSale(new OnSale(10.0,new SimpleCalendar(2017,1,1),new SimpleCalendar(2017,12,31)));
    OrderService os = new OrderService(em);
    int orderid = os.createCartOrder("1234567890");
    os.updateCartItem(orderid, "111", 2, -1);
   //os.updateCartItem(orderid, "112", 3, -1);
    //CartItem updatefirstcartitem = os.updateCartItem(orderid, "111", 1, firstcartitem.getId());
    double total =  os.completeOrder(os.showCartByPharmacist("1234567890"));
    assertEquals(EXPECTED_ORDER_TOTAL, total, 1e-2);
    }
    
    //Παραγγελία με ενεργοποιημένη έκπτωση 10%, εκτός της περιόδου της έκπτωσης  
    @Test
    public void createOrderOnSale_PeriodFalseTest() {
    double EXPECTED_ORDER_TOTAL = 10; 
    EntityManager em = JPAUtil.getCurrentEntityManager();
    CatalogService cs = new CatalogService(em);
    Product product = cs.findProductByEOF("111").get(0);
    //Set OnSale for Depon product to 10%
    product.setOnSale(new OnSale(10.0,new SimpleCalendar(2016,1,1),new SimpleCalendar(2016,12,31)));
    OrderService os = new OrderService(em);
    int orderid = os.createCartOrder("1234567890");
    os.updateCartItem(orderid, "111", 2, -1);
   //os.updateCartItem(orderid, "112", 3, -1);
    //CartItem updatefirstcartitem = os.updateCartItem(orderid, "111", 1, firstcartitem.getId());
    double total =  os.completeOrder(os.showCartByPharmacist("1234567890"));
    assertEquals(EXPECTED_ORDER_TOTAL, total, 1e-2);
    }
    
	@Test(expected = LibraryException.class)
	public void orderIncompletedTest() {
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    int orderid = os.createCartOrder("1234567890");
	    os.updateCartItem(orderid, "111", 50, -1);
	}
    
	@Test(expected = LibraryException.class)
	public void salesOfProductPerPeriodTest() {
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    os.salesOfProductPerPeriod("111",new SimpleCalendar(2017,1,1),new SimpleCalendar(2016,1,1));
	}
	
	@Test(expected = LibraryException.class)
	public void salesOfProductPerPeriodTest2() {
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    os.salesOfProductPerPeriod("111",null,new SimpleCalendar(2016,1,1));
	}
	
	@Test(expected = LibraryException.class)
	public void salesOfProductPerPeriodTest3() {
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    os.salesOfProductPerPeriod("111",null,null);
	}

	
    @Test
    public void findOrdersByPharmacistTest() {
    	int EXPECTED_ORDERS = 3; 
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    int total =  os.findOrdersByPharmacist("9876543210").size();
    assertEquals(EXPECTED_ORDERS, total);
    }
    
    @Test
    public void findOrdersByPharmacistTest2() {
    	int EXPECTED_ORDERS = 1; 
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    int total =  os.findOrdersByPharmacist("1234567890",new SimpleCalendar(2016,11,1),new SimpleCalendar(2016,11,1)).size();
    assertEquals(EXPECTED_ORDERS, total);
    }
    
    @Test(expected = LibraryException.class)
    public void findOrdersByPharmacistTest3() {
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    os.findOrdersByPharmacist("9876543210",new SimpleCalendar(2017,1,1),new SimpleCalendar(2016,1,1));
    }
    
    @Test(expected = LibraryException.class)
    public void findOrdersByPharmacistTest4() {
	    EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    os.findOrdersByPharmacist("9876543210",null,null);
    }
	
    @Test
    public void addLineItemTest() {
    	EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    int orderid = os.createCartOrder("1234567890");
	    os.addLineItem(orderid, "111", 2);
    }
    
    @Test(expected = LibraryException.class)
    public void addLineItemTest2() {
    	EntityManager em = JPAUtil.getCurrentEntityManager();
	    OrderService os = new OrderService(em);
	    int orderid = os.createCartOrder("1234567890");
	    os.addLineItem(orderid, "111", 180);
    }
    
    
    @Test
    public void orderDateTest() {
    	SimpleCalendar EXPECTED_DATE_firstNovember2016 = new SimpleCalendar(2016,11,1); 
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService cs = new OrderService(em);
        List<Order> orders = cs.findAllOrders();
        assertNotNull(orders);
        assertEquals(EXPECTED_DATE_firstNovember2016.toString(), orders.get(0).getOrderdate().toString());
    }

    @Test
    public void LineItemsInOrderTest() {
    	int EXPECTED_QUANTITY_OF_FIRST_ORDER = 7;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService cs = new OrderService(em);
        List<Order> orders = cs.findAllOrders();

        Set<LineItem> orderlineitems = orders.get(0).getLineItems();
        LineItem[] orderLineItemsArray = orderlineitems.toArray(new LineItem[orderlineitems.size()]);
        int total = 0;
        for (LineItem li : orderLineItemsArray)
        	total += li.getQuantity();
        assertEquals(EXPECTED_QUANTITY_OF_FIRST_ORDER,total);
    }

    @Test
    public void AccountOrdersTest(){
    	String EXPECTED_ACCOUNT_USERNAME = "terkap";
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService cs = new OrderService(em);
        List<Order> orders = cs.findAllOrders();

        Account accountOrders = orders.get(0).getAccount();
        assertNotNull(accountOrders);
        assertEquals(EXPECTED_ACCOUNT_USERNAME, accountOrders.getUsername());
    }
}

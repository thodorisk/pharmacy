package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.*;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import org.junit.Before;
import org.junit.Test;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.persistence.Initializer;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderServiceTest {

    private Initializer dataHelper;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @Test
    public void orderSizeTest() {
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
        assertEquals(EXPECTED_QUANTITY_OF_FIRST_ORDER,7);
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

package gr.aueb.test.persistence;

import gr.aueb.mscis.sample.model.*;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderServiceTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("pharmacy");
        em = emf.createEntityManager();
        initializeData();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }


    public void  eraseData() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query;

        query = em.createNativeQuery("DELETE FROM orders");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM products");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM lines");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM pharmacists");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM accounts");
        query.executeUpdate();
        tx.commit();

    }

    public void initializeData() {
        eraseData();

        Product deponproduct = new Product("Depon", "111", 5.00);
        Order orderone = new Order(new SimpleCalendar(2016,11,1), OrderState.PENDING, 20.0);
        LineItem firstlineitem = new LineItem(9);
        Pharmacist uni = new Pharmacist("Uni","Aueb","info@aueb.gr",null,"2109999999", "1234567890");
        Account uni_acc = new Account("uni", "888", false, "2017-01-01");

        uni.setAccount(uni_acc);
        firstlineitem.setOrder(orderone);
        orderone.getLineItems().add(firstlineitem);
        firstlineitem.setProduct(deponproduct);
        deponproduct.getLineItems().add(firstlineitem);
        orderone.setAccount(uni_acc);
        uni_acc.getOrders().add(orderone);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(uni);
        em.persist(uni_acc);
        em.persist(firstlineitem);
        em.persist(deponproduct);
        em.persist(orderone);
        tx.commit();
    }

    @Test
    public void orderSizeTest() {
        OrderService cs = new OrderService(em);
        List<Order> orders = cs.findAllOrders();
        assertNotNull(orders);
        assertEquals("2016-11-1", orders.get(0).getOrderdate().toString());

    }

    @Test
    public void LineItemsInOrderTest() {
        OrderService cs = new OrderService(em);
        List<Order> orders = cs.findAllOrders();

        Set<LineItem> orderlineitems = orders.get(0).getLineItems();
        LineItem[] orderLineItemsArray = orderlineitems.toArray(new LineItem[orderlineitems.size()]);
        assertTrue(orderLineItemsArray[0].getQuantity() == 9);

    }

    @Test
    public void AccountOrdersTest(){
        OrderService cs = new OrderService(em);
        List<Order> orders = cs.findAllOrders();

        Account accountOrders = orders.get(0).getAccount();
        assertNotNull(accountOrders);
        assertEquals("uni", accountOrders.getUsername());
    }
}

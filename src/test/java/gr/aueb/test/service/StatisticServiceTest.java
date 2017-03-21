package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.service.StatisticService;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class StatisticServiceTest {

    private Initializer dataHelper;
    private static final double DELTA = 1e-15;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @Test
    public void earningsByPharmacistTest(){
        double EXPECTED_EARNINGS = 76.0;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        StatisticService ss = new StatisticService(os);
        assertEquals(EXPECTED_EARNINGS, ss.earningsByPharmacist("9876543210"), DELTA);
    }
    
    @Test
    public void earningsByPharmacistPeriodTest(){
        double EXPECTED_EARNINGS = 16.0;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        StatisticService ss = new StatisticService(os);
        assertEquals(EXPECTED_EARNINGS, ss.earningsByPharmacist("9876543210",new SimpleCalendar(2017,3,1),new SimpleCalendar(2017,10,10)), DELTA);
    }

    @Test
    public void historyOrderPerPharmacistPeriodTest(){
        double EXPECTED_HISTORY_ORDERS_SIZE = 1;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        StatisticService ss = new StatisticService(os);
        assertEquals(EXPECTED_HISTORY_ORDERS_SIZE, ss.historyOrderPerPharmacist("9876543210",new SimpleCalendar(2017,3,1),new SimpleCalendar(2017,10,10)).size(),DELTA);
    }
    
    @Test
    public void salesPerProductPerPeriodTest(){
        double EXPECTED_QUANTITY_OF_A_PRODUCT = 24;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        StatisticService ss = new StatisticService(os);
        assertEquals(EXPECTED_QUANTITY_OF_A_PRODUCT, ss.salesPerProductPerPeriod("111",new SimpleCalendar(2017,1,1),new SimpleCalendar(2017,10,10)),DELTA);
    }
    
    @Test
    public void totalSalesTest(){
        double EXPECTED_SALES = 212.0;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        StatisticService ss = new StatisticService(os);
        assertEquals(EXPECTED_SALES, ss.totalSales(),DELTA);
    }
}

package gr.aueb.test.service;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.service.StatisticService;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

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
    public void totalSalesTest(){
        double EXPECTED_SALES = 212.0;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        StatisticService ss = new StatisticService(os);
        assertEquals(EXPECTED_SALES, ss.totalSales(),DELTA);
    }
}

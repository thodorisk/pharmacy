package gr.aueb.test.model;

import gr.aueb.mscis.sample.model.Order;
import org.junit.Assert;
import org.junit.Test;


public class OrderTest {

    @Test
    public void getId(){
        int EXPECTED_ID = 5;
        Order or = new Order();
        Assert.assertNotNull(or);
        or.setId(5);
        Assert.assertEquals(EXPECTED_ID, or.getId());
    }

    @Test
    public void getTotal(){
        Double EXPECTED_TOTAL = 10.0;
        Order or = new Order();
        Assert.assertNotNull(or);
        or.setTotal(10.0);
        Assert.assertEquals(EXPECTED_TOTAL, or.getTotal());
    }
}

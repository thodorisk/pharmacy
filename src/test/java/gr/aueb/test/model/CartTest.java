package gr.aueb.test.model;

import gr.aueb.mscis.sample.model.Cart;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import org.junit.Assert;
import org.junit.Test;

public class CartTest {

    @Test
    public void getCartDate(){
        Cart cr = new Cart();
        Assert.assertNotNull(cr);
        SimpleCalendar march_20_2017 = new SimpleCalendar(2017, 3 , 20);
        cr.setCartDate(march_20_2017);
        Assert.assertEquals(march_20_2017, cr.getCartDate());
    }
}

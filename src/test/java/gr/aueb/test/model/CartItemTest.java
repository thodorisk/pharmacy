package gr.aueb.test.model;

import gr.aueb.mscis.sample.model.CartItem;
import org.junit.Assert;
import org.junit.Test;


public class CartItemTest {

    @Test
    public void getEofn(){
        String EXPECTED_EOFN = "000";
        CartItem ci = new CartItem();
        Assert.assertNotNull(ci);
        ci.setEofn("000");
        Assert.assertEquals(EXPECTED_EOFN, ci.getEofn());
    }

    @Test
    public void getQuantity(){
        int EXPECTED_QUANTITY = 5;
        CartItem ci = new CartItem();
        Assert.assertNotNull(ci);
        ci.setQuantity(5);
        Assert.assertEquals(EXPECTED_QUANTITY, ci.getQuantity());
    }

    @Test
    public void getId(){
        int EXPECTED_ID = 5;
        CartItem ci = new CartItem();
        Assert.assertNotNull(ci);
        ci.setId(5);
        Assert.assertEquals(EXPECTED_ID, ci.getId());
    }
}

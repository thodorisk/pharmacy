package gr.aueb.test.model;
import gr.aueb.mscis.sample.model.OnSale;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by thodoriskaragiannis on 16/03/2017.
 */
public class OnSaleTest {
    @Test
    public void getDiscount(){
        Double EXPECTED_DISCOUNT = 5.0;
        OnSale os = new OnSale();
        Assert.assertNotNull(os);
        os.setDiscount(5.0);
        Assert.assertEquals(EXPECTED_DISCOUNT, os.getDiscount());
    }

    @Test
    public void getStartdate(){

    }

    @Test
    public void getEnddate(){

    }
}

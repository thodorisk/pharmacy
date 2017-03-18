package gr.aueb.test.model;
import gr.aueb.mscis.sample.model.OnSale;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OnSaleTest {
    @Test
    public void getDiscountTest(){
        Double EXPECTED_DISCOUNT = 5.0;
        OnSale os = new OnSale();
        Assert.assertNotNull(os);
        os.setDiscount(5.0);
        Assert.assertEquals(EXPECTED_DISCOUNT, os.getDiscount());
    }

    @Test
    public void getIdTest(){
        int EXPECTED_ID = 5;
        OnSale os = new OnSale();
        Assert.assertNotNull(os);
        os.setId(5);
        Assert.assertEquals(EXPECTED_ID, os.getId());
    }

    @Test
    public void getStartDateTest(){
        OnSale os = new OnSale();
        Assert.assertNotNull(os);
        SimpleCalendar march_20_2017 = new SimpleCalendar(2017, 3 , 20);
        os.setStartdate(march_20_2017);
        Assert.assertEquals(march_20_2017, os.getStartdate());
    }

    @Test
    public void getEndDateTest(){
        OnSale os = new OnSale();
        Assert.assertNotNull(os);
        SimpleCalendar march_20_2017 = new SimpleCalendar(2017, 3 , 20);
        os.setEnddate(march_20_2017);
        Assert.assertEquals(march_20_2017, os.getEnddate());
    }
}

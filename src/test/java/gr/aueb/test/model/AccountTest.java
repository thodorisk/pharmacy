package gr.aueb.test.model;


import gr.aueb.mscis.sample.model.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    @Before
    public void setUpTests() {

    }

    @After
    public void resetTests() {

    }

    @Test
    public void getUsername(){
        String EXPECTED_USERNAME = "thokar";
        Account acc = new Account();
        Assert.assertNotNull(acc);
        acc.setUsername("thokar");
        Assert.assertEquals(EXPECTED_USERNAME, acc.getUsername());
    }

    @Test
    public void getPassword(){
        String EXPECTED_PASS = "1234";
        Account acc = new Account();
        Assert.assertNotNull(acc);
        acc.setPassword("1234");
        Assert.assertEquals(EXPECTED_PASS, acc.getPassword());
    }

    @Test
    public void getIsClosed(){
        Boolean EXPECTED_STATE = false;
        Account acc = new Account();
        Assert.assertNotNull(acc);
        acc.setIsclosed(false);
        Assert.assertEquals(EXPECTED_STATE, acc.getIsclosed());
    }

    @Test
    public void getDateopened(){
        String EXPECTED_DATE = "30111985";
        Account acc = new Account();
        Assert.assertNotNull(acc);
        acc.setDateopened("30111985");
        Assert.assertEquals(EXPECTED_DATE, acc.getDateopened());
    }

}

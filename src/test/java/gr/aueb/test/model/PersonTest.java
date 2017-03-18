package gr.aueb.test.model;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Person;

public class PersonTest {
	
    @Test
    public void addNullForPhone() {
    	Person person = new Person();                    
    	person.setPhone(null);
        Assert.assertEquals(null,person.getPhone());
        Assert.assertSame(null,person.getPhone());
    }

    @Test
    public void addPhone() {
    	String EXPECTED_NUMBER = "2105545865";
    	Person person = new Person();                    
    	person.setPhone("2105545865");
        Assert.assertNotEquals(null,person.getPhone());
        Assert.assertEquals(EXPECTED_NUMBER, person.getPhone());
        Assert.assertSame(EXPECTED_NUMBER, person.getPhone());
    }
    
    @Test
    public void addNullForFirstName() {
    	Person person = new Person();                    
    	person.setFirstName(null);
        Assert.assertEquals(null,person.getFirstName());
        Assert.assertSame(null,person.getFirstName());
    }
    
    @Test
    public void addFirstName() {
    	String EXPECTED_FIRSTNAME = "Dionisis";
    	Person person = new Person();                    
    	person.setFirstName("Dionisis");
        Assert.assertNotEquals(null,person.getFirstName());
        Assert.assertSame(EXPECTED_FIRSTNAME, person.getFirstName());
    }
    
    @Test
    public void addNullForLastName() {
    	Person person = new Person();                    
    	person.setLastName(null);
        Assert.assertEquals(null,person.getLastName());
        Assert.assertSame(null,person.getLastName());
    }
    
    @Test
    public void addLastName() {
    	String EXPECTED_LASTNAME = "Koropoulis";
    	Person person = new Person();                    
    	person.setLastName("Koropoulis");
        Assert.assertNotEquals(null,person.getLastName());
        Assert.assertSame(EXPECTED_LASTNAME, person.getLastName());
    }
    
    @Test
    public void addNullForVatNo() {
    	Person person = new Person();                    
    	person.setVatNo(null);
        Assert.assertEquals(null,person.getVatNo());
        Assert.assertSame(null,person.getVatNo());
    }
    
    @Test
    public void addVatNo() {
    	String EXPECTED_VATNO = "987654321";
    	Person person = new Person();                    
    	person.setVatNo("987654321");
        Assert.assertNotEquals(null,person.getVatNo());
        Assert.assertSame(EXPECTED_VATNO, person.getVatNo());
    }
    
}

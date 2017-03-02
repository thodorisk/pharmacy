package gr.aueb.test.model;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Account;
import gr.aueb.mscis.sample.model.Cart;
import gr.aueb.mscis.sample.model.Person;
import gr.aueb.mscis.sample.model.Pharmacist;


public class PharmacistTest {
	
    @Test
    public void addNullForCart() {
        Pharmacist pharmacist = new Pharmacist();                    
        pharmacist.setCart(null);
        Assert.assertEquals(null,pharmacist.getCart());        
        Assert.assertSame(null,pharmacist.getCart());
    }
    
    @Test
    public void addOneCart() {
        Pharmacist pharmacist = new Pharmacist();
        Cart cart = new Cart();
        pharmacist.setCart(cart);
        Assert.assertNotEquals(null,pharmacist.getCart());
        Assert.assertEquals(cart,pharmacist.getCart());
        Assert.assertSame(cart,pharmacist.getCart());
    }
    
    @Test
    public void removeOneCart() {
        Pharmacist pharmacist = new Pharmacist();
        Cart cart = new Cart();
        pharmacist.setCart(cart);
        Assert.assertNotEquals(null,pharmacist.getCart());
        Assert.assertEquals(cart,pharmacist.getCart());
        Assert.assertSame(cart,pharmacist.getCart());
        pharmacist.setCart(null);
        Assert.assertEquals(null,pharmacist.getCart());
        Assert.assertNotEquals(cart,pharmacist.getCart());
    }
    
    
    @Test
    public void addNullForPerson() {
        Pharmacist pharmacist = new Pharmacist();                    
        pharmacist.setPerson(null);
        Assert.assertEquals(null,pharmacist.getPerson());        
        Assert.assertSame(null,pharmacist.getPerson());
    }
    
    @Test
    public void addPerson() {
        Pharmacist pharmacist = new Pharmacist();
        Person person = new Person();
        pharmacist.setPerson(person);
        Assert.assertNotEquals(null,pharmacist.getPerson());        
    }
    
    @Test
    public void addNullForAccount() {
        Pharmacist pharmacist = new Pharmacist();
        //Account account = new Account();
        pharmacist.setPerson(null);
        Assert.assertEquals(null,pharmacist.getPerson());        
        Assert.assertSame(null,pharmacist.getPerson());      
    }
    
    @Test
    public void addAccount() {
        Pharmacist pharmacist = new Pharmacist();
        Account account = new Account();
        pharmacist.setAccount(account);
        Assert.assertNotEquals(null,pharmacist.getPerson());        
    }
}

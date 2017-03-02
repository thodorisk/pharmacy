package gr.aueb.test.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.Product;



public class LineItemTest {
    
	@Before 
    public void setUpTests() {
    
    }
    
    @After
    public void resetTests() {
    
    }
    
    @Test
    public void getQuantity() {
    	int EXPECTED_QUANTITY = 2;
    	LineItem li = new LineItem();
    	Assert.assertNotNull(li);
    	li.setQuantity(2);
    	Assert.assertEquals(EXPECTED_QUANTITY, li.getQuantity());
    	Assert.assertSame(EXPECTED_QUANTITY, li.getQuantity());
    }
    
    @Test
    public void getOrder() {
    	LineItem li = new LineItem();
    	Order order = new Order();
    	li.setOrder(order);
    	Assert.assertEquals(order,li.getOrder());
    	Assert.assertSame(order,li.getOrder());
    }
    
    @Test
    public void getProduct() {
    	LineItem li = new LineItem();
    	Product product = new Product();
    	li.setProduct(product);
    	Assert.assertEquals(product,li.getProduct());
    	Assert.assertSame(product,li.getProduct());
    }
    
    @Test
    public void getId() {
    	int EXPECTED_ID = 10;
    	LineItem li = new LineItem();
    	li.setId(10);
    	Assert.assertNotNull(li);
    	Assert.assertEquals(EXPECTED_ID, li.getId());
    }

}

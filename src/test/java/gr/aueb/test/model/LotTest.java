package gr.aueb.test.model;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Product;



public class LotTest {
	
   
    @Test
    public void addQuantity() {
    	int EXPECTED_QUANTITY = 2;
    	Lot l = new Lot();
    	Assert.assertNotNull(l);
    	l.setQuantity(2);
    	Assert.assertEquals(EXPECTED_QUANTITY, l.getQuantity());
    	Assert.assertSame(EXPECTED_QUANTITY, l.getQuantity());
    }
    
    
  @Test
  public void addNullForProduct() {
	  Lot l = new Lot();
      l.setProduct(null);
      Assert.assertEquals(null,l.getProduct());
      Assert.assertSame(null,l.getProduct());         
  }
    
  @Test
  public void addProduct() {
	  Lot l = new Lot();
	  Product product = new Product();
      l.setProduct(product);
      Assert.assertEquals(product,l.getProduct());
      Assert.assertSame(product,l.getProduct());         
  }
      

}

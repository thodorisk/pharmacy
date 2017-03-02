package gr.aueb.test.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Product;

public class ProductTest {
	
	Product product;
	Lot lot;
	
    @Before
    public void setUp() {
    	product = new Product();
    	lot = new Lot();
    	lot.setId(1);
    	product.getLots().add(lot);
    	lot.setProduct(product);
    }
    
    @Test
    public void successfullSetUp() {
    	lotBidirectionalAssociationInvariant(product);
    	expectedLotsSize(product,1);
    	successfullAdditionofLot(product,lot);
    }
    
    @Test
    public void addNullAsLot() {
    	expectedLotsSize(product,1);
        product.setLots(null);
        expectedLotsSize(product,1);
        lotBidirectionalAssociationInvariant(product);
    }    
    
    @Test
    public void addNullAsCategory() {
    	product.setCategory(null);
    	Assert.assertEquals(null, product.getCategory());
    }
    
    @Test
    public void addCategory() {
        Category category = new Category();
        product.setCategory(category);
        Assert.assertEquals(category,product.getCategory());
    }

    @Test
    public void addingSameLotIntoTwoProducts() {
        Product product2 = new Product();
        product2.getLots().add(lot);
        lot.setProduct(product2);
        successfullAdditionofLot(product2,lot);
        lotBidirectionalAssociationInvariant(product);
        lotBidirectionalAssociationInvariant(product2);        
    }
    
    
    private void lotBidirectionalAssociationInvariant(Product product) {
        for(Lot lot : product.getLots()) {
            Assert.assertEquals(product, lot.getProduct());
        }
    }
    
    private void expectedLotsSize(Product product, int expectedSize) {
        Assert.assertEquals(expectedSize, product.getLots().size());
    }
    
    private void successfullAdditionofLot(Product product, Lot lot){
        Assert.assertTrue(product.getLots().contains(lot));
        Assert.assertSame(product, lot.getProduct());
    }
    
}

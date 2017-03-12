package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.CatalogService;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {

    private Initializer dataHelper;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @Test
    public void productSizeTest() {
    	int EXPECTED_NUMBER_OF_PRODUCTS = 4;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();
        assertNotNull(products);
        assertEquals(EXPECTED_NUMBER_OF_PRODUCTS,products.size());

    }

    @Test
    public void productLotsSizeTest() {
    	int EXPECTED_PRODUCT_LOTS_SIZE = 2;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        assertNotNull(products);

        //check Lots size
        Set<Lot> productLots = products.get(0).getLots();
        assertNotNull(productLots);
        assertEquals(EXPECTED_PRODUCT_LOTS_SIZE, productLots.size());
    }

    @Test
    public void lineItemSizeProductTest(){
    	int EXPECTED_LINEITEMS_OF_FIRSTPRODUCT = 2;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();
        Set<LineItem> productLineItems = products.get(0).getLineItems();
        assertNotNull(productLineItems);
        assertEquals(EXPECTED_LINEITEMS_OF_FIRSTPRODUCT, productLineItems.size());
    }

    @Test
    public void lineItemProductDataTest(){
    	int EXPECTED_QUANTITY_OF_FIRST_LINEITEMPRODUCT = 12;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByName("Depon");

        Set<LineItem> productLineItems = products.get(0).getLineItems();
        List <LineItem> productLineItemsArray = new ArrayList(productLineItems);
        assertTrue(productLineItemsArray.get(0).getQuantity() == 12);
        assertEquals(EXPECTED_QUANTITY_OF_FIRST_LINEITEMPRODUCT, productLineItemsArray.get(0).getQuantity());
    }

    @Test
    public void productCategoryDataTest(){
    	String EXPECTED_CATEGORY = "Drugs";
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();
        
        Category productCategory = products.get(0).getCategory();
        assertNotNull(productCategory);
        assertEquals(EXPECTED_CATEGORY, productCategory.getDescription());

    }

    @Test
    public void productLotsDataTest() {
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        //check Lots Data
        Set<Lot> productLots = products.get(0).getLots();

        Lot[] productLotsArray = productLots.toArray(new Lot[productLots.size()]);

        assertTrue(productLotsArray[0].getLotno() == 602 || productLotsArray[0].getLotno() == 601);
        assertTrue(productLotsArray[1].getLotno() == 602 || productLotsArray[1].getLotno() == 601);

        assertTrue(productLotsArray[0].getQuantity() == 10 || productLotsArray[0].getQuantity() == 11);
        assertTrue(productLotsArray[1].getQuantity() == 10 || productLotsArray[1].getQuantity() == 11);

    }
}
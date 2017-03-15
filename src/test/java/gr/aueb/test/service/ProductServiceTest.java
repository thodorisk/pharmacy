package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.*;
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
    	int EXPECTED_LINEITEMS_OF_FIRSTPRODUCT = 4;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();
        Set<LineItem> productLineItems = products.get(0).getLineItems();
        assertNotNull(productLineItems);
        assertEquals(EXPECTED_LINEITEMS_OF_FIRSTPRODUCT, productLineItems.size());
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

        assertTrue(productLotsArray[0].getQuantity() == 0 || productLotsArray[0].getQuantity() == 9);
        assertTrue(productLotsArray[1].getQuantity() == 0 || productLotsArray[1].getQuantity() == 9);

    }

    @Test
    public void saveProductTest(){
        int EXPECTED_QUANTITY_OF_PRODUCTS = 5;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        Product amoxilproduct = new Product("Amoxil", "000", 8.70);
        cs.save(amoxilproduct);
        List<Product> products = cs.findAllProducts();

        assertEquals(EXPECTED_QUANTITY_OF_PRODUCTS, products.size());
    }

    @Test
    public void deleteProductTest(){
        int EXPECTED_QUANTITY_OF_PRODUCTS = 5;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        Product amoxilproduct = new Product("Amoxil", "000", 8.70);
        cs.save(amoxilproduct);
        cs.deleteProduct(33);
        List<Product> products = cs.findAllProducts();

        assertEquals(EXPECTED_QUANTITY_OF_PRODUCTS, products.size());
    }

    @Test
    public void findProductByNameTest(){
        String EXPECTED_NAME = "Depon";
        EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByName("Depon");
        assertEquals(EXPECTED_NAME, products.get(0).getName());
    }

    @Test
    public void findProductByEofTest(){
        String EXPECTED_NAME = "Panadol";
        EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByEOF("120");
        assertEquals(EXPECTED_NAME, products.get(0).getName());
    }


}
package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.service.CatalogService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("pharmacy");
        em = emf.createEntityManager();
        initializeData();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }


    public void  eraseData() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query;

        query = em.createNativeQuery("DELETE FROM lots");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM products");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM lines");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM categories");
        query.executeUpdate();
        tx.commit();
    }

    public void initializeData() {
        eraseData();

        Product deponproduct = new Product("Depon", "111", 5.00);

        //Add stock to the above products
        Lot deponfirstlot = new Lot(601, 10);
        deponfirstlot.setProduct(deponproduct);

        Lot deponsecondlot = new Lot(602, 11);
        deponsecondlot.setProduct(deponproduct);

        deponproduct.getLots().add(deponfirstlot);
        deponproduct.getLots().add(deponsecondlot);

        //Add the above product to a lineitem
        LineItem firstlineitem = new LineItem(5);
        firstlineitem.setProduct(deponproduct);

        deponproduct.getLineItems().add(firstlineitem);

        //Add a category to the above product
        Category category1 = new Category("Paracetamol");
        deponproduct.setCategory(category1);

        category1.getProducts().add(deponproduct);



        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(deponfirstlot);
        em.persist(deponsecondlot);
        em.persist(deponproduct);
        em.persist(firstlineitem);
        em.persist(category1);
        tx.commit();
    }

    @Test
    public void productSizeTest() {
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();
        assertNotNull(products);
        assertEquals(1,products.size());

    }

    @Test
    public void productLotsSizeTest() {
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        assertNotNull(products);

        //check Lots size
        Set<Lot> productLots = products.get(0).getLots();
        assertNotNull(productLots);
        assertEquals(2, productLots.size());
    }

    @Test
    public void lineItemSizeProductTest(){
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        Set<LineItem> productLineItems = products.get(0).getLineItems();
        assertNotNull(productLineItems);
        assertEquals(1, productLineItems.size());
    }

    @Test
    public void lineItemProductDataTest(){
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        Set<LineItem> productLineItems = products.get(0).getLineItems();

        LineItem[] productLineItemsArray = productLineItems.toArray(new LineItem[productLineItems.size()]);
        assertTrue(productLineItemsArray[0].getQuantity() == 5);
    }

    @Test
    public void productCategoryDataTest(){
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        Category productCategory = products.get(0).getCategory();
        assertNotNull(productCategory);
        assertEquals("Paracetamol", productCategory.getDescription());

    }

    @Test
    public void productLotsDataTest() {
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();

        //check Lots Data
        Set<Lot> productLots = products.get(0).getLots();

        Lot[] productLotsArray = productLots.toArray(new Lot[productLots.size()]);
//        assertEquals(602, productLotsArray[0].getLotno());
//        assertEquals(11, productLotsArray[0].getQuantity());
//
//        assertEquals(601, productLotsArray[1].getLotno());
//        assertEquals(10, productLotsArray[1].getQuantity());

        assertTrue(productLotsArray[0].getLotno() == 602 || productLotsArray[0].getLotno() == 601);
        assertTrue(productLotsArray[1].getLotno() == 602 || productLotsArray[1].getLotno() == 601);

        assertTrue(productLotsArray[0].getQuantity() == 10 || productLotsArray[0].getQuantity() == 11);
        assertTrue(productLotsArray[1].getQuantity() == 10 || productLotsArray[1].getQuantity() == 11);

    }
}
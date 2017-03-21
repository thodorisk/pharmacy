package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.OnSale;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.CatalogService;
import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.util.SimpleCalendar;

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
    	int EXPECTED_LINEITEMS_OF_FIRSTPRODUCT = 3;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findAllProducts();
        Set<LineItem> productLineItems = products.get(0).getLineItems();
        assertNotNull(productLineItems);
        assertEquals(EXPECTED_LINEITEMS_OF_FIRSTPRODUCT, productLineItems.size());
    }

    @Test
    public void totalOrderTest(){
    	int EXPECTED_TOTAL_OF_ORDERS = 136;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        OrderService os = new OrderService(em);
        List<Order> orderlist = os.findOrdersByPharmacist("1234567890");
        double total = 0.0;
        for (Order order : orderlist)
        	{
        	total = total + order.getTotal();
        	}
        assertEquals(EXPECTED_TOTAL_OF_ORDERS, total, 1e-2);
        
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
    	int EXPECTED_DEPON_LOTS_QUANTITY = 59;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        List<Product> products = cs.findProductByEOF("111");
        Lot newlot = new Lot(455, 50);
        cs.addLot(products.get(0), newlot);
        //check Lots Data
        Set<Lot> productLots = products.get(0).getLots();
        Lot[] productLotsArray = productLots.toArray(new Lot[productLots.size()]);
		assertEquals(EXPECTED_DEPON_LOTS_QUANTITY  , productLotsArray[0].getQuantity() + productLotsArray[1].getQuantity() + productLotsArray[2].getQuantity());
        
    }
    
    @Test
    public void deleteProductTest() {
    	int EXPECTED_PRODUCTS_SIZE = 4;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        Product newproduct = new Product("Drug1","345",20.0);
        assertEquals(EXPECTED_PRODUCTS_SIZE, cs.findAllProducts().size());
        cs.save(newproduct);
        cs.deleteProduct(newproduct.getId());      
		assertEquals(EXPECTED_PRODUCTS_SIZE, cs.findAllProducts().size());
        
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
    public void findProductbyIdTest() {
    	String EXPECTED_PRODUCT_NAME = "Drug1";
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        Product newproduct = new Product("Drug1","345",20.0);
        cs.save(newproduct);
		assertEquals(EXPECTED_PRODUCT_NAME, cs.findProductById(newproduct.getId()).getName());
    }
    
    @Test
    public void findProductbyNameTest() {
    	String EXPECTED_PRODUCT_EOF = "345";
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        Product newproduct = new Product("Drug1","345",20.0);
        cs.save(newproduct);
		assertEquals(EXPECTED_PRODUCT_EOF, cs.findProductByName("Drug1").get(0).getEofn());
    }
    
    @Test
    public void findProductByCategoryTest() {
    	String EXPECTED_PRODUCT_EOF = "113";
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
		assertEquals(EXPECTED_PRODUCT_EOF, cs.findProductByCategory("Vitamins").get(0).getEofn());
    }
    
    
    @Test
    public void updateOnSaleTest() {
    	double EXPECTED_PRODUCT_ONSALE = 20.0;
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        CatalogService cs = new CatalogService(em);
        Product newproduct = new Product("Drug1","345",20.0);
        cs.save(newproduct);
        cs.UpdateOnSale(newproduct, new OnSale(20.0,new SimpleCalendar(2017,1,1),new SimpleCalendar(2017,1,10)));
		assertEquals(EXPECTED_PRODUCT_ONSALE, newproduct.getOnSale().getDiscount(), 1e-2);
    }

    @Test
    public void addLotTest(){
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Product product1 = new Product("Aspirin","999", 3.0);
        Lot lot1 = new Lot(987654, 88);
        CatalogService cs = new CatalogService(em);
        cs.addLot(product1, lot1);
        assertEquals(lot1.getLotno(), new ArrayList<Lot>(product1.getLots()).get(0).getLotno());
    }

    @Test
    public void getStockTest(){
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Product product1 = new Product("Aspirin","999", 3.0);
        Lot lot1 = new Lot(987654, 88);
        CatalogService cs = new CatalogService(em);
        cs.addLot(product1, lot1);
        assertEquals(lot1.getQuantity(), new ArrayList<Lot>(product1.getLots()).get(0).getQuantity());
    }
    
}
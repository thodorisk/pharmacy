package gr.aueb.test.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

import gr.aueb.mscis.sample.service.CatalogService;
import gr.aueb.mscis.sample.service.OrderService;


public abstract class PharmacyResourceTest extends JerseyTest {

	Initializer dataHelper;

	public PharmacyResourceTest() {
		super();
	}

	public PharmacyResourceTest(TestContainerFactory testContainerFactory) {
		super(testContainerFactory);
	}

	public PharmacyResourceTest(Application jaxrsApplication) {
		super(jaxrsApplication);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}

	
	public List<Product> findProductsByName(String name) {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		CatalogService service = new CatalogService(em);
		List<Product> products = service.findProductByName(name);
		
		em.close();
		return products;
	}
	
	public List<Order> findAllOrders() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		
		OrderService service = new OrderService(em);
		List<Order> orders = service.findAllOrders();
		
		em.close();
		return orders;
	}

}
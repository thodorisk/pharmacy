package gr.aueb.test.resource;


import static gr.aueb.mscis.sample.resource.PharmacyUri.productIdUri;
import static gr.aueb.mscis.sample.resource.PharmacyUri.PRODUCT_SEARCH_NAME;
import static gr.aueb.mscis.sample.resource.PharmacyUri.PRODUCT_SEARCH_EOFN;
import static gr.aueb.mscis.sample.resource.PharmacyUri.PRODUCT_SEARCH_CATEGORY;
import static gr.aueb.mscis.sample.resource.PharmacyUri.PRODUCTS;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;


import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.resource.DebugExceptionMapper;
import gr.aueb.mscis.sample.resource.PharmacyUri;
import gr.aueb.mscis.sample.resource.ProductInfo;
import gr.aueb.mscis.sample.resource.ProductResource;
import gr.aueb.test.resource.PharmacyResourceTest;

public class ProductResourceTest extends PharmacyResourceTest {

	@Override
	protected Application configure() {
		/*
		 * 
		 */
		return new ResourceConfig(ProductResource.class, DebugExceptionMapper.class);
	}
	
	@Test
	public void testListAllProducts() {

		List<ProductInfo> products = target(PRODUCTS).request().get(new GenericType<List<ProductInfo>>() {
		});
		Assert.assertEquals(4, products.size());
	}
	
	@Test
	public void testListProductById() {

		// get all products
		List<ProductInfo> products = target(PRODUCTS).request().get(new GenericType<List<ProductInfo>>() {
		});

		String firstProductId = Integer.toString(products.get(0).getId());

		ProductInfo product = target(productIdUri(firstProductId)).request().get(ProductInfo.class);
		Assert.assertNotNull(product);
		Assert.assertEquals("Depon", product.getName());
	}
	
	@Test
	public void testSearchProductByName() {
		System.out.println(PharmacyUri.productSearchUri("Depon"));
		List<ProductInfo> products = target(PRODUCT_SEARCH_NAME).queryParam("name", "Depon").request()
				.get(new GenericType<List<ProductInfo>>() {
				});

		Assert.assertEquals(1, products.size());
	}
	
	@Test
	public void testsearchProductByEofn() {
		List<ProductInfo> products = target(PRODUCT_SEARCH_EOFN).queryParam("eofn", "111").request()
				.get(new GenericType<List<ProductInfo>>() {
				});

		Assert.assertEquals(1, products.size());
	}
	
	@Test
	public void testsearchProductByCategory() {
		List<ProductInfo> products = target(PRODUCT_SEARCH_CATEGORY).queryParam("description", "Drugs").request()
				.get(new GenericType<List<ProductInfo>>() {
				});

		Assert.assertEquals(2, products.size());
	}
	
	@Test
	public void testCreateNewProduct() {
		// Create a product info object and submit
		ProductInfo productInfo = new ProductInfo("ADrug","888", 25.0);

		Response response = target(PRODUCTS).request().post(Entity.entity(productInfo, MediaType.APPLICATION_JSON));

		// Check status and database state
		Assert.assertEquals(201, response.getStatus());
		List<Product> foundProducts = findProductsByName("Depon");
		Assert.assertEquals(1, foundProducts.size());
	}
	
	
	@Test
	public void testUpdateProduct() {

		// Find a product and update its name
		List<Product> products = findProductsByName("Panadol");
		Assert.assertEquals(1, products.size());
		ProductInfo productInfo = ProductInfo.wrap(products.get(0));
		productInfo.setName("Another Panadol");

		// Submit the updated representation
		Response response = target(productIdUri(Integer.toString(productInfo.getId()))).request()
				.put(Entity.entity(productInfo, MediaType.APPLICATION_JSON));

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		List<Product> foundProducts = findProductsByName("Another Panadol");
		Assert.assertEquals(1, foundProducts.size());
	}
	
	@Test
	public void testDeleteExistingProduct() {
		// Delete a product
		List<Product> products = findProductsByName("Panadol");
		Assert.assertEquals(1, products.size());
		Product product = products.get(0);

		// Submit the updated representation
		Response response = target(productIdUri(Integer.toString(product.getId()))).request().delete();

		// assertion on request status and database state
		Assert.assertEquals(200, response.getStatus());
		List<Product> foundProducts = findProductsByName("Panadol");
		Assert.assertEquals(0, foundProducts.size());

	}
	
}

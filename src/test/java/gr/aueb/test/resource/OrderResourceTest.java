package gr.aueb.test.resource;

import static gr.aueb.mscis.sample.resource.PharmacyUri.ORDERS;
import static gr.aueb.mscis.sample.resource.PharmacyUri.PRODUCTS;
import static gr.aueb.mscis.sample.resource.PharmacyUri.orderIdUri;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.resource.DebugExceptionMapper;
import gr.aueb.mscis.sample.resource.OrderInfo;
import gr.aueb.mscis.sample.resource.OrderResource;
import gr.aueb.mscis.sample.resource.ProductInfo;

public class OrderResourceTest extends PharmacyResourceTest {

	@Override
	protected Application configure() {

		return new ResourceConfig(OrderResource.class, DebugExceptionMapper.class);
	}
	
	@Test
	public void testListAllOrders() {

		List<OrderInfo> orders = target(ORDERS).request().get(new GenericType<List<OrderInfo>>() {
		});
		Assert.assertEquals(6, orders.size());
	}
	
	@Test
	public void testListOrderById() {

		// get all orders
		List<OrderInfo> orders = target(ORDERS).request().get(new GenericType<List<OrderInfo>>() {
		});

		String firstOrderId = Integer.toString(orders.get(0).getId());

		OrderInfo order = target(orderIdUri(firstOrderId)).request().get(OrderInfo.class);
		Assert.assertNotNull(order);
		Assert.assertEquals("COMPLETED", order.getStatus().toString());
	}

}

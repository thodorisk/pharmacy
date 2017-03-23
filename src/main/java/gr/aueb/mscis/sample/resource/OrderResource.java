package gr.aueb.mscis.sample.resource;

import static gr.aueb.mscis.sample.resource.PharmacyUri.ORDERS;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.service.CatalogService;
import gr.aueb.mscis.sample.service.OrderService;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class OrderResource manages client requests related to orders.
 */

@Path(ORDERS)
public class OrderResource extends AbstractResource {
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderInfo> listAllOrders() {
		EntityManager em = getEntityManager();
		OrderService orderservice = new OrderService(em);
		List<Order> orders = orderservice.findAllOrders();

		List<OrderInfo> orderInfo = OrderInfo.wrap(orders);

		em.close();
		return orderInfo;
	}
	
	@GET
	@Path("{orderId:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderInfo getOrderDetails(@PathParam("orderId") int orderId) {
		EntityManager em = getEntityManager();

		OrderService orderservice = new OrderService(em);
		Order order = orderservice.findOrderById(orderId);

		OrderInfo orderInfo = OrderInfo.wrap(order);
		em.close();

		return orderInfo;
	}
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderInfo orderInfo) {
    	System.out.println("asdsdasdsjdsadjka");
    	EntityManager em = getEntityManager();
    	System.out.println(orderInfo.getOrderdate() + "	" + orderInfo.getStatus() + orderInfo.getId());


    	Order order = orderInfo.getOrder(em);

    	System.out.println(order.getOrderdate().toString() + "	" + order.getStatus().toString() + order.getId());

    	OrderService orderservice = new OrderService(em);
    	int orderid = orderservice.createCartOrder(order.getAccount().getPharmacist().getPerson().getVatNo());

    	UriBuilder ub = uriInfo.getAbsolutePathBuilder();
    	URI newOrderUri = ub.path(Integer.toString(orderid)).build();

    	em.close();

        return Response.created(newOrderUri).build();
    }

}

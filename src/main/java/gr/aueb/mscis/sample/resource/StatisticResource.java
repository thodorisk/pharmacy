package gr.aueb.mscis.sample.resource;

import static gr.aueb.mscis.sample.resource.PharmacyUri.STATISTICS;



import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


import gr.aueb.mscis.sample.service.OrderService;
import gr.aueb.mscis.sample.service.StatisticService;
import gr.aueb.mscis.sample.util.SimpleCalendar;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class StatisticResource manages client requests related to statistics.
 */

@Path(STATISTICS)
public class StatisticResource extends AbstractResource {
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("earnings")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticInfo earningsByPharmacist(@QueryParam("afm") String afm) {
		EntityManager em = getEntityManager();
		OrderService os = new OrderService(em);
		StatisticService statisticService = new StatisticService(os);
		StatisticInfo earnings = new StatisticInfo(statisticService.earningsByPharmacist(afm));
		
		em.close();
		return earnings;
	}
	
	@GET
	@Path("totalsales")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticInfo totalSales() {
		EntityManager em = getEntityManager();
		OrderService os = new OrderService(em);
		StatisticService statisticService = new StatisticService(os);
		StatisticInfo earnings = new StatisticInfo(statisticService.totalSales());
		
		em.close();
		return earnings;
	}	
	
	@GET
	@Path("earnings_per_period")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticInfo earningsByPharmacistByPeriod(@QueryParam("afm") String afm ,
														@QueryParam("startyear") String startyear,@QueryParam("startmonth") String startmonth,@QueryParam("startday") String startday,
														@QueryParam("endyear") String endyear,@QueryParam("endmonth") String endmonth,@QueryParam("endday") String endday) 
	{
		SimpleCalendar startdate = new SimpleCalendar(Integer.parseInt(startyear),Integer.parseInt(startmonth),Integer.parseInt(startday));
		SimpleCalendar enddate = new SimpleCalendar(Integer.parseInt(endyear),Integer.parseInt(endmonth),Integer.parseInt(endday));
		EntityManager em = getEntityManager();
		OrderService os = new OrderService(em);
		StatisticService statisticService = new StatisticService(os);
		StatisticInfo earnings = new StatisticInfo(statisticService.earningsByPharmacist(afm,startdate,enddate));
		
		em.close();
		return earnings;
	}
	
	@GET
	@Path("sales_per_product")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticInfo salesPerProductPerPeriod(@QueryParam("eof") String eof ,
														@QueryParam("startyear") String startyear,@QueryParam("startmonth") String startmonth,@QueryParam("startday") String startday,
														@QueryParam("endyear") String endyear,@QueryParam("endmonth") String endmonth,@QueryParam("endday") String endday) 
	{
		SimpleCalendar startdate = new SimpleCalendar(Integer.parseInt(startyear),Integer.parseInt(startmonth),Integer.parseInt(startday));
		SimpleCalendar enddate = new SimpleCalendar(Integer.parseInt(endyear),Integer.parseInt(endmonth),Integer.parseInt(endday));
		EntityManager em = getEntityManager();
		OrderService os = new OrderService(em);
		StatisticService statisticService = new StatisticService(os);
		StatisticInfo sales = new StatisticInfo(statisticService.salesPerProductPerPeriod(eof,startdate,enddate));
		
		em.close();
		return sales;
	}
	
	

}

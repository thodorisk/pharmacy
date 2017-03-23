package gr.aueb.test.resource;

import static gr.aueb.mscis.sample.resource.PharmacyUri.STATISTICS_EARNINGS;
import static gr.aueb.mscis.sample.resource.PharmacyUri.STATISTICS_TOTALSALES;
import static gr.aueb.mscis.sample.resource.PharmacyUri.STATISTICS_EARNINGS_PER_PERIOD;
import static gr.aueb.mscis.sample.resource.PharmacyUri.STATISTICS_SALES_PER_PRODUCT;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.sample.resource.DebugExceptionMapper;
import gr.aueb.mscis.sample.resource.StatisticInfo;
import gr.aueb.mscis.sample.resource.StatisticResource;

public class StatisticResourceTest  extends PharmacyResourceTest {

	@Override
	protected Application configure() {
		/*
		 * 
		 */
		return new ResourceConfig(StatisticResource.class, DebugExceptionMapper.class);
	}
	
	@Test
	public void testEarningsByPharmacist() {
		
		StatisticInfo earnings = target(STATISTICS_EARNINGS).queryParam("afm", "1234567890").request()
				.get(new GenericType<StatisticInfo>() {
				});

		Assert.assertEquals(136, earnings.getTotal(),1e-2);
	}
	
	
	@Test
	public void testTotalSales() {
		
		StatisticInfo totalsales = target(STATISTICS_TOTALSALES).request()
				.get(new GenericType<StatisticInfo>() {
				});

		Assert.assertEquals(212, totalsales.getTotal(),1e-2);
	}
	
	
	@Test
	public void testEarningsByPharmacistPerPeriod() {
		
		StatisticInfo earnings = target(STATISTICS_EARNINGS_PER_PERIOD)
				.queryParam("afm", "1234567890")
				.queryParam("startyear", "2017")
				.queryParam("startmonth", "1")
				.queryParam("startday", "1")
				.queryParam("endyear", "2017")
				.queryParam("endmonth", "5")
				.queryParam("endday", "1")
				.request()
				.get(new GenericType<StatisticInfo>() {
				});

		Assert.assertEquals(116, earnings.getTotal(),1e-2);
	}
	
	@Test
	public void testsalesPerProductPerPeriod() {
		
		StatisticInfo sales = target(STATISTICS_SALES_PER_PRODUCT)
				.queryParam("eof", "111")
				.queryParam("startyear", "2017")
				.queryParam("startmonth", "1")
				.queryParam("startday", "1")
				.queryParam("endyear", "2017")
				.queryParam("endmonth", "5")
				.queryParam("endday", "1")
				.request()
				.get(new GenericType<StatisticInfo>() {
				});

		Assert.assertEquals(24, sales.getTotal(),1e-2);
	}
}

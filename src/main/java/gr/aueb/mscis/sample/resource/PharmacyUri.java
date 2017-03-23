package gr.aueb.mscis.sample.resource;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class PharmacyUri defines all the endpoints available to a client.
 */

public class PharmacyUri {

	/**
	 * /products
	 */
	public static final String PRODUCTS = "products";

	/**
	 * /pharmacists
	 */
	public static final String PHARMACISTS = "pharmacists";
	
	/**
	 * /statistics
	 */
	public static final String STATISTICS = "statistics";
	
	/**
	 * /orders
	 */
	public static final String ORDERS = "orders";

	/**
	 * / product searches
	 */

	public static final String PRODUCT_SEARCH_NAME = "products/search";
	public static final String PRODUCT_SEARCH_EOFN = "products/search_eofn";
	public static final String PRODUCT_SEARCH_CATEGORY = "products/search_by_category";


	/**
	 * / statistic searches
	 */

	public static final String STATISTICS_EARNINGS = "statistics/earnings";
	public static final String STATISTICS_TOTALSALES = "statistics/totalsales";
	public static final String STATISTICS_EARNINGS_PER_PERIOD = "statistics/earnings_per_period";
	public static final String STATISTICS_SALES_PER_PRODUCT = "statistics/sales_per_product";

	/**
	 * / pharmacist searches
	 */

	public static final String PHARMACIST_SEARCH_LASTNAME = "pharmacists/search_lastname";
	public static final String PHARMACIST_SEARCH_AFM = "pharmacists/search_afm";
	
	public static String pharmacistIdUri(String pharmacistNo) {
		return PHARMACISTS + "/" + pharmacistNo;
	}
	
	/**
	 * /pharmacists/{vatNo}, <br>
	 * e.g. /pharmacists/999999999
	 */
	public static String pharmacistVatNoSearchUri(String vatNo) {
		return PHARMACIST_SEARCH_AFM + "?vatNo=" + vatNo;
	}

	/**
	 * /products?last_name={last_name}, <br>
	 * e.g. /pharmacists?last_name=Koropoulis
	 */
	public static String pharmacistSearchUri(String lastName) {
		return PHARMACIST_SEARCH_LASTNAME + "?lastname=" + lastName;
	}

	/**
	 * /products/{id}, <br>
	 * e.g. /products/1
	 */
	public static String productIdUri(String id) {
		return PRODUCTS + "/" + id;
	}

	/**
	 * /orders/{id}, <br>
	 * e.g. /orders/1
	 */

	public static String orderIdUri(String id) {
		return ORDERS + "/" + id;
	}

	/**
	 * /products?name={name}, <br>
	 * e.g. /products?name=Depon
	 */
	public static String productSearchUri(String name) {
		return PRODUCT_SEARCH_NAME + "?name=" + name;
	}

}

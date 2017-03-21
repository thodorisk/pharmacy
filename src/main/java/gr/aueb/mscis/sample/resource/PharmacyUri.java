package gr.aueb.mscis.sample.resource;

public class PharmacyUri {

	/**
	 * /products
	 */
	public static final String PRODUCTS = "products";

	/**
	 * /orders
	 */
	public static final String ORDERS = "orders";

	public static final String PRODUCT_SEARCH = "products/search";

	/**
	 * /books/{id},
	 * e.g. /books/1
	 */
	public static String productIdUri(String id) {
		return PRODUCTS + "/" + id;
	}

	/**
	 * /books?title={title},
	 * e.g. /books?title=UML
	 */
	public static String productSearchUri(String eofn) {
		return PRODUCT_SEARCH + "?eofn=" + eofn;
	}
	
	public static String orderUri(String itemId){
		return ORDERS + "/" + itemId;
	}

}

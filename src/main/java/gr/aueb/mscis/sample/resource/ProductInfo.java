package gr.aueb.mscis.sample.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.service.CatalogService;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class ProductInfo is a utility class for JAX-RS implementation.
 */

@XmlRootElement
public class ProductInfo {
	
	private Integer id;
	private String name;
	private String eofn;
	private Double price;
	
	
	
	public ProductInfo() {
	}

	public ProductInfo(Integer id, String name, String eofn, Double price) {
		this(name, eofn, price);
		this.id = id;
	}
	
	public ProductInfo(String name, String eofn, Double price) {
		super();
		this.name = name;
		this.eofn = eofn;
		this.price = price;
	}
	
	public ProductInfo(Product product) {
		id = product.getId();
		name = product.getName();
		eofn = product.getEofn();
		price = product.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEofn() {
		return eofn;
	}

	public void setEofn(String eofn) {
		this.eofn = eofn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public static ProductInfo wrap(Product p) {
		return new ProductInfo(p);
	}

	public static List<ProductInfo> wrap(List<Product> products) {

		List<ProductInfo> productInfoList = new ArrayList<>();

		for (Product p : products) {
			productInfoList.add(new ProductInfo(p));
		}

		return productInfoList;

	}

	public Product getProduct(EntityManager em) {

		Product product = null;
		CatalogService cs = new CatalogService(em);
		if (id != null) {
			product = cs.findProductById(id);
		} else {
			product = new Product();
		}

		product.setName(name);
		product.setEofn(eofn);
		product.setPrice(price);

		if (product.getEofn() == null || !product.getEofn().equals(eofn)) {
			product.setEofn(eofn);
		}

		return product;
	}
	
	

}

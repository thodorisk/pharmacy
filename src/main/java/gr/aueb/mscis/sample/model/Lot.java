package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class Lot defines a lot number of a product.
 */
@Entity
@Table(name = "lots")
public class Lot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(nullable = false, unique = true)
	int lotno;
	
	@Column(name = "quantity", nullable = false)
	int quantity;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "product_id")
	private Product product;
	
	@ManyToMany (cascade = {CascadeType.ALL})
	@JoinTable(
			name="lot_line",
			joinColumns={@JoinColumn(name="lot_id", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="line_id", referencedColumnName="ID")})
	private Set<LineItem> lineItems = new HashSet<LineItem>();
	

	public Lot() {
	}

	public Lot(int lotno, int quantity) {
		super();
		this.lotno = lotno;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLotno() {
		return lotno;
	}

	public void setLotno(int lotno) {
		this.lotno = lotno;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if (product != null)
			this.product = product;
	}
	
	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
	
	
	
	
	

}

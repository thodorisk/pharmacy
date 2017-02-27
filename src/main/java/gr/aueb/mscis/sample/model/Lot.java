package gr.aueb.mscis.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
		this.product = product;
	}
	
	
	
	
	
	
	
	

}

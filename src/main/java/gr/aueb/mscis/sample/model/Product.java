package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "eofn", nullable = false)
	private int eofn;

	@Column(name = "price", nullable = false)
	private Double price;
	
	
	public Product() {
	}

	public Product(String name, int eofn, Double price) {
		super();
		this.name = name;
		this.eofn = eofn;
		this.price = price;
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

	public int getEofn() {
		return eofn;
	}

	public void setEofn(int eofn) {
		this.eofn = eofn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@OneToOne
	@JoinColumn (name = "sale_id")
	private OnSale onSale;

	public OnSale getOnSale() {
		return onSale;
	}

	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn (name = "category_id")
	private Category category;
	public Category getCategory() {return category;}
	public void setCategory(Category category) {this.category = category;}
	
	@OneToMany(mappedBy = "product")
	private Set<Lot> lots  = new HashSet<>();

	public Set<Lot> getLots() {
		return lots;
	}

	public void setLots(Set<Lot> lots) {
		this.lots = lots;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Product other = (Product) obj;
		return new EqualsBuilder()
				.append(name, other.name)
				.append(eofn, other.eofn)
				.append(price, other.price)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name)
				.append(eofn)
				.append(price)
				.toHashCode();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", eofn=" + eofn + ", price=" + price + "]";
	}
}

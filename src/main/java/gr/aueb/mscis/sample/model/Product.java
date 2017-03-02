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
	private String eofn;

	@Column(name = "price", nullable = false)
	private Double price;
	
	
	public Product() {
	}

	public Product(String name, String eofn, Double price) {
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

	public String getEofn() {
		return eofn;
	}

	public void setEofn(String eofn) {
		if (eofn != null)
			this.eofn = eofn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "sale_id")
	private OnSale onSale;

	public OnSale getOnSale() {
		return onSale;
	}

	public void setOnSale (OnSale onSale) {this.onSale = onSale;}

	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "category_id")
	private Category category;
	public Category getCategory() {return category;}
	public void setCategory(Category category) {
		if (category != null)
		this.category = category;
	}
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
	private Set<Lot> lots  = new HashSet<>();
	public Set<Lot> getLots() {
		return lots;
	}
	public void setLots(Set<Lot> lots) {
		if (lots != null)
			this.lots = lots;
	}

	@OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
	private Set<LineItem> lineItems = new HashSet<>();
	public Set<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", eofn=" + eofn + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((eofn == null) ? 0 : eofn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lineItems == null) ? 0 : lineItems.hashCode());
		result = prime * result + ((lots == null) ? 0 : lots.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((onSale == null) ? 0 : onSale.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (eofn == null) {
			if (other.eofn != null)
				return false;
		} else if (!eofn.equals(other.eofn))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lineItems == null) {
			if (other.lineItems != null)
				return false;
		} else if (!lineItems.equals(other.lineItems))
			return false;
		if (lots == null) {
			if (other.lots != null)
				return false;
		} else if (!lots.equals(other.lots))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (onSale == null) {
			if (other.onSale != null)
				return false;
		} else if (!onSale.equals(other.onSale))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}

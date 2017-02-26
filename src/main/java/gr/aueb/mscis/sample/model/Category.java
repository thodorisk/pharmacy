package gr.aueb.mscis.sample.model;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(nullable = false, unique = true)
	String description;


	public Category() {
	}
	
	
		
	public Category(String description) {
		super();
		this.description = description;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Set<Product> products = new HashSet<>();
	
	public Set <Product> getProducts() {return products;}
	public void setProducts (Set <Product> products) {this.products = products;}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return new EqualsBuilder()
				.append(description, other.description)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(description)
				.toHashCode();
	}
}

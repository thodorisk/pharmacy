package gr.aueb.mscis.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", length = 512, nullable = false)
	private String name;

	@Column(name = "eofn", nullable = false)
	private int eofn;

	@Column(name = "lotn", length = 100, nullable = false)
	private String lotn;
	
	
	public Product() {
	}

	public Product(String name, int eofn, String lotn) {
		super();
		this.name = name;
		this.eofn = eofn;
		this.lotn = lotn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getLotn() {
		return lotn;
	}

	public void setLotn(String lotn) {
		this.lotn = lotn;
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
				.append(lotn, other.lotn)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name)
				.append(eofn)
				.append(lotn)
				.toHashCode();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", eofn=" + eofn + ", lotn=" + lotn + "]";
	}

	
	
}

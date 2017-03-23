package gr.aueb.mscis.sample.model;

import javax.persistence.*;

import gr.aueb.mscis.sample.util.SimpleCalendar;


/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class OnSale defines the basic discount information of a product.
 */
@Entity
@Table(name = "onsales")
public class OnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "starts", nullable = false)
    @org.hibernate.annotations.Type(
            type="gr.aueb.mscis.sample.persistence.SimpleCalendarCustomType")
    private SimpleCalendar startdate;

    @Column(name = "ends", nullable = false)
    @org.hibernate.annotations.Type(
            type="gr.aueb.mscis.sample.persistence.SimpleCalendarCustomType")
    private SimpleCalendar enddate;

    @OneToOne (mappedBy = "onSale", cascade = {CascadeType.ALL})
    private Product product;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public OnSale() {
    }

    public OnSale(Double discount, SimpleCalendar startdate, SimpleCalendar enddate) {
        super();
        this.discount = discount;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
	public SimpleCalendar getStartdate() {
		return startdate;
	}
	public void setStartdate(SimpleCalendar startdate) {
		this.startdate = startdate;
	}
	public SimpleCalendar getEnddate() {
		return enddate;
	}
	public void setEnddate(SimpleCalendar enddate) {
		this.enddate = enddate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((enddate == null) ? 0 : enddate.hashCode());
		result = prime * result + id;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((startdate == null) ? 0 : startdate.hashCode());
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
		OnSale other = (OnSale) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (id != other.id)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		return true;
	}

	


}

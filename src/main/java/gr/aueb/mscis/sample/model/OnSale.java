package gr.aueb.mscis.sample.model;

import javax.persistence.*;

import gr.aueb.mscis.sample.util.SimpleCalendar;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
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



}

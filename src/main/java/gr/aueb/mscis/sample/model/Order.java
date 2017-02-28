package gr.aueb.mscis.sample.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import gr.aueb.mscis.sample.util.SimpleCalendar;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "orderdate", nullable = false)
    @org.hibernate.annotations.Type(
            type="gr.aueb.mscis.sample.persistence.SimpleCalendarCustomType")
    private SimpleCalendar orderdate;

    @Enumerated (EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderState status;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne (cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn (name="account_id")
    private Account account;
    
    @OneToMany (mappedBy = "order")
    private Set <LineItem> lineItems = new HashSet<LineItem>();
    public Set <LineItem> getLineItems() {return lineItems;}
    public void setLineItems (Set <LineItem> lineItems) {this.lineItems = lineItems;}


    public Order() {
    }

    public Order(SimpleCalendar orderdate, OrderState status, Double total) {
        super();
        this.orderdate = orderdate;
        this.status= status;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleCalendar getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(SimpleCalendar orderdate) {
		this.orderdate = orderdate;
	}
	public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Account getAccount() {
    	return account;
    	}
    
    public void setAccount(Account account) {
		this.account = account;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != getClass())
            return false;
        Order other = (Order) obj;
        return new EqualsBuilder()
                .append(orderdate, other.orderdate)
                .append(status, other.status)
                .append(total, other.total)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderdate)
                .append(status)
                .append(total)
                .toHashCode();
    }
}


package gr.aueb.mscis.sample.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "orderdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate = new Date();

    @Enumerated (EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderState status;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "lot", nullable = false)
    private int lot;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne (cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn (name="account_id")
    private Account account;
    public Account getAccount(){return account;}

    @OneToMany (mappedBy = "order")
    private Set <LineItem> lineItems = new HashSet<LineItem>();
    public Set <LineItem> getLineItems() {return lineItems;}
    public void setLineItems (Set <LineItem> lineItems) {this.lineItems = lineItems;}


    public Order() {
    }

    public Order(Date orderdate, OrderState status, Double total, int lot, int quantity) {
        super();
        this.orderdate = orderdate;
        this.status= status;
        this.total = total;
        this.quantity = quantity;
        this.lot = lot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
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

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
                .append(lot, other.lot)
                .append(quantity, other.quantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderdate)
                .append(status)
                .append(total)
                .append(lot)
                .append(quantity)
                .toHashCode();
    }
}


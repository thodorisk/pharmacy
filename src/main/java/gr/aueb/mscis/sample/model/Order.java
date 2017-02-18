package gr.aueb.mscis.sample.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

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
    private String orderdate;

    @Column(name = "status", nullable = false)
    private OrderState status;

    @Column(name = "total", nullable = false)
    private Double total;


    public Order() {
    }

    public Order(String orderdate, OrderState status, Double total) {
        super();
        this.orderdate = orderdate;
        this.status = status;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
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


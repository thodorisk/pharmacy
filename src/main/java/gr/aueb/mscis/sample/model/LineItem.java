package gr.aueb.mscis.sample.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "lines")
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn (name = "order_id")
    private Order order;
    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}


    public LineItem(){

    }

    public LineItem(int quantity) {
        super();
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}


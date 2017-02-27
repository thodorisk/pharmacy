package gr.aueb.mscis.sample.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

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

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn (name = "product_id")
    private Product product;
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}


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


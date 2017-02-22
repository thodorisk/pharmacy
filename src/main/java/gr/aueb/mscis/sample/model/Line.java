package gr.aueb.mscis.sample.model;

import javax.persistence.*;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name = "Lines")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn (name = "order_id")
    private Order order;
    public Order getOrder() {return order;}
}


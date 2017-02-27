package gr.aueb.mscis.sample.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "cartdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cartDate = new Date();

    @OneToOne (mappedBy = "cart")
    private Pharmacist pharmacist;

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public Cart(){

    }

    public Cart(Date cartDate) {
        super();
        this.cartDate = cartDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCartDate() {
        return cartDate;
    }

    public void setCartDate(Date cartDate) {
        this.cartDate = cartDate;
    }

}

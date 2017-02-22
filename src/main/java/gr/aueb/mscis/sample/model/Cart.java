package gr.aueb.mscis.sample.model;

import javax.persistence.*;

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
    private String cartDate;

    @OneToOne (mappedBy = "pharmacist")
    private Pharmacist pharmacist;

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public Cart(){

    }

    public Cart(String cartDate) {
        super();
        this.cartDate = cartDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartDate() {
        return cartDate;
    }

    public void setCartDate(String cartDate) {
        this.cartDate = cartDate;
    }

}

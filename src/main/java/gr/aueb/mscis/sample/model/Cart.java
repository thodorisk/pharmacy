package gr.aueb.mscis.sample.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name = "cart")
public class Cart {


    @OneToOne (mappedBy = "pharmacist")
    private Pharmacist pharmacist;

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

}

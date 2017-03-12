package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import gr.aueb.mscis.sample.util.SystemDate;


@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "cartdate")
    @org.hibernate.annotations.Type(
            type="gr.aueb.mscis.sample.persistence.SimpleCalendarCustomType")
    private SimpleCalendar cartDate = SystemDate.now();

    @OneToOne (mappedBy = "cart")
    private Pharmacist pharmacist;
    
	@OneToMany(mappedBy="cart")
    private Set<LineItem> lineitems = new HashSet<LineItem>();
    

    public Pharmacist getPharmacist() {
        return pharmacist;
    }
    
    public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}



	public Cart(){

    }

    public Cart(SimpleCalendar cartDate) {
    	super();
    	if(cartDate != null)
        this.cartDate = cartDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleCalendar getCartDate() {
        return cartDate;
    }

    public void setCartDate(SimpleCalendar cartDate) {
        this.cartDate = cartDate;
    }

	public Set<LineItem> getLineitems() {
		return lineitems;
	}

	public void setLineitems(Set<LineItem> lineitems) {
		this.lineitems = lineitems;
	}
    
    

}

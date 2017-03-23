package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import gr.aueb.mscis.sample.util.SimpleCalendar;
import gr.aueb.mscis.sample.util.SystemDate;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class Cart defines the shopping cart.
 */

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
    private Set<Order> orders = new HashSet<Order>();
	    
	@OneToMany(mappedBy="cart")
    private Set<CartItem> cartitems = new HashSet<CartItem>();

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

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<CartItem> getCartitems() {
		return cartitems;
	}

	public void setCartitems(Set<CartItem> cartitems) {
		this.cartitems = cartitems;
	}
	
	
}

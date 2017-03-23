package gr.aueb.mscis.sample.model;

import javax.persistence.*;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class CartItem defines an item of a shopping cart.
 */

@Entity
@Table(name = "cartitems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
	@Column(name = "eofn", nullable = false)
	private String eofn;
	
    @ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "cart_id")
	private Cart cart;
    
    public CartItem() {
		super();
	}

	public CartItem(String eofn, int quantity) {
		super();
		this.eofn = eofn;
		this.quantity = quantity;
	}



	public String getEofn() {
		return eofn;
	}

	public void setEofn(String eofn) {
		this.eofn = eofn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

}

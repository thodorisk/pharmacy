package gr.aueb.mscis.sample.model;

import javax.persistence.*;

import java.util.HashSet;
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

	@ManyToMany (cascade = {CascadeType.ALL},mappedBy="lineItems")
	private Set<Lot> lots = new HashSet<Lot>();
	
    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn (name = "product_id")
    private Product product;
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}

    @ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "cart_id")
	private Cart cart;

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
	public Set<Lot> getLots() {
		return lots;
	}
	public void setLots(Set<Lot> lots) {
		this.lots = lots;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

    

}


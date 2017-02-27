package gr.aueb.mscis.sample.model;

import javax.persistence.*;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name = "sales")
public class OnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "starts", nullable = false)
    private String startdate;

    @Column(name = "ends", nullable = false)
    private String enddate;

    @OneToOne (mappedBy = "onSale", cascade = {CascadeType.ALL})
    private Product product;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public OnSale() {
    }

    public OnSale(Double discount, String startdate, String enddate) {
        super();
        this.discount = discount;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

}

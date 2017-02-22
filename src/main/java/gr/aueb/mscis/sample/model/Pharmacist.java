package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import gr.aueb.mscis.sample.contacts.Address;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name="pharmacists")
public class Pharmacist  {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="pharmacistno")
    private int pharmacistNo;

    @Embedded
    private Address address;

    @Embedded
    private Person person = new Person();

    @OneToOne
    @JoinColumn (name="account_id", unique = true)

    private Account account;

    public Account getAccount() {
        return account;
    }

    @OneToOne
    @JoinColumn (name="cart_id")

    private Cart cart;
    public  Cart getCart(){
        return cart;
    }

    public Pharmacist() { }


    public Pharmacist(int pharmacistNo, String firstName,
                    String lastName, String email, Address address) {
        this.pharmacistNo = pharmacistNo;
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        this.address = address == null ? null : new Address(address);
    }

    public int getPharmacistNo() {
        return pharmacistNo;
    }

    public void setPharmacistNo(int pharmacistNo) {
        this.pharmacistNo = pharmacistNo;
    }

    public void setFirstName(String firstName) {
        person.setFirstName(firstName);
    }

    public String getFirstName() {
        return person.getFirstName();
    }

    public void setLastName(String lastName) {
        person.setLastName(lastName);
    }

    public String getLastName() {
        return person.getLastName();
    }

    public void setEmail(String email) {
        person.setEmail(email);
    }

    public String getEmail() {
        return person.getEmail();
    }


    public void setAddress(Address address) {
        this.address = address == null ? null : new Address(address);
    }

    public Address getAddress() {
        return address == null ? null : new Address(address);
    }

}

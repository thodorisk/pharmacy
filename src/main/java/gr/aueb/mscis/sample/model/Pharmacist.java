package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gr.aueb.mscis.sample.contacts.Address;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Entity
@Table(name="pharmacists")
public class Pharmacist  {

    @Id
    @Column(name="pharmacistno")
    private int pharmacistNo;

    @Embedded
    private Address address;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy="pharmacist", fetch=FetchType.LAZY)
    private Set<Order> orders = new HashSet<Order>();

    @Embedded
    private Person person = new Person();

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

    public Set<Order> getOrders() {
        return new HashSet<Order>(orders);
    }
}

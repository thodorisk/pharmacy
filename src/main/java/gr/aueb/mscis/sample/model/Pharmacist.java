package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import gr.aueb.mscis.sample.contacts.Address;
import gr.aueb.mscis.sample.contacts.EmailAddress;


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

    @OneToOne (cascade=CascadeType.PERSIST)
    @JoinColumn (name="account_id", unique = true)
    private Account account;

    @OneToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn (name="cart_id")
    private Cart cart;
    
    @org.hibernate.annotations.Type(
            type="gr.aueb.mscis.sample.persistence.EMailCustomType")
    @Column(name="email", length=40, nullable = false)
    private EmailAddress email;

    public Person getPerson() {
		return person;
	}
    
	public void setPerson(Person person) {
		this.person = person;
	}
	
    public  Cart getCart(){
        return cart;
    }

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Pharmacist() { }


    public Pharmacist(String firstName,
                    String lastName, EmailAddress email, Address address, String phone, String vatNo) {
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        person.setVatNo(vatNo);
        this.email = email == null ? null : email;
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

    public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
        return account;
    }

    public void setAddress(Address address) {
        this.address = address == null ? null : new Address(address);
    }

    public Address getAddress() {
        return address == null ? null : new Address(address);
    }

}

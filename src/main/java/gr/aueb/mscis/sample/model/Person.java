package gr.aueb.mscis.sample.model;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class Person {
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="phone")
    private int phone;
    @Column(name="vatNo")
    private int vatNo;

    public Person() { }

    Person (String firstName, String lastName, String email, int phone, int vatNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.vatNo = vatNo;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setVatNo(int VatNo) {
        this.vatNo = vatNo;
    }

    public int getVatNo() {
        return vatNo;
    }

}
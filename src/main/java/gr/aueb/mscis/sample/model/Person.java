package gr.aueb.mscis.sample.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import gr.aueb.mscis.sample.contacts.EmailAddress;

@Embeddable
public class Person {
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;

    @Column(name="phone")
    private String phone;
    @Column(name="vatNo")
    private String vatNo;

    public Person() { }

    Person (String firstName, String lastName, EmailAddress email, String phone, String vatNo) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setVatNo(String VatNo) {
        this.vatNo = VatNo;
    }

    public String getVatNo() {
        return vatNo;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((vatNo == null) ? 0 : vatNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (vatNo == null) {
			if (other.vatNo != null)
				return false;
		} else if (!vatNo.equals(other.vatNo))
			return false;
		return true;
	}


	
    
    

}

package gr.aueb.mscis.sample.contacts;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

@Embeddable
public class Address {
    @Column(name="street", length=50)
    private String street;

    @Column(name="number", length = 10)
    private String number;

    @Column(name="city", length = 50)
    private String city;

    @Column(name="zipcode", length = 10)
    private Integer zipcode;

    @Column(name="country", length=50)
    private String country = "Greece";


    public Address() { }

    public Address(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.zipcode = address.getZipCode();
        this.country = address.getCountry();
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {return street;}

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZipCode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getZipCode() {
        return zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != getClass())
            return false;
        Address other = (Address) obj;
        return new EqualsBuilder()
                .append(street, other.street)
                .append(number, other.number)
                .append(city, other.city)
                .append(zipcode, other.zipcode)
                .append(country, other.country)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(street)
                .append(number)
                .append(city)
                .append(zipcode)
                .append(country)
                .toHashCode();
    }

}

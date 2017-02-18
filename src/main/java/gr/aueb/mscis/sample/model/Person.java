package gr.aueb.mscis.sample.model;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class Person {
    @Column(name="firstname", length=50, nullable = false)
    private String firstName;
    @Column(name="lastname", length=50, nullable = false)
    private String lastName;
    @Column(name="email", length=50, nullable = false)
    private String email;

    public Person() { }

    Person (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
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

}

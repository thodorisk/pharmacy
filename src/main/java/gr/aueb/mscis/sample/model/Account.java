package gr.aueb.mscis.sample.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false, unique = true)
    String password;

    @Column(nullable = false, unique = true)
    Boolean isclosed;

    @Column(nullable = false, unique = true)
    String dateopened;


    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }

    public String getDateopened() {
        return dateopened;
    }

    public void setDateopened(String dateopened) {
        this.dateopened = dateopened;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        return new EqualsBuilder()
                .append(username, other.username)
                .append(password, other.password)
                .append(isclosed, other.isclosed)
                .append(dateopened, other.dateopened)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(username)
                .append(password)
                .append(isclosed)
                .append(dateopened)
                .toHashCode();
    }
}


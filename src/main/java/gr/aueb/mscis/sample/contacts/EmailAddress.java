package gr.aueb.mscis.sample.contacts;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

public class EmailAddress {
    private String value;

    public EmailAddress(String email) {
        this.value = email;
    }

    public String getEmailAddress() {
        return value;
    }

    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof EmailAddress)) {
            return false;
        }

        EmailAddress theEmail = (EmailAddress) other;
        return value == null ? theEmail.getEmailAddress() == null
                : value.equals(theEmail.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
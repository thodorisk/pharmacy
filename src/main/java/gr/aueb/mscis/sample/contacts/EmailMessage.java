package gr.aueb.mscis.sample.contacts;

/**
 * Created by thodoriskaragiannis on 18/02/2017.
 */

public class EmailMessage {
    private EmailAddress from;
    private EmailAddress to;
    private String subject;
    private String body;

    public void setFrom(EmailAddress from) {
        this.from = from;
    }

    public EmailAddress getFrom() {
        return from;
    }

    public void setTo(EmailAddress to) {
        this.to = to;
    }

    public EmailAddress getTo() {
        return to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    /**
     * Επισυνάπτει κείμενο στο τέλος του μηνύματος.
     * @param text Το κείμενο που επισυνάπτεται στο τέλος του μηνύματος.
     */
    public void appendToBody(String text) {
        subject += text;
    }
}

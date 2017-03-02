package gr.aueb.mscis.sample.contacts;


public class EmailMessage {
    private EmailAddress from;
    private EmailAddress to;
    private String subject;
    private String body;
    
	public EmailMessage() {
		super();
	}

	public EmailMessage(EmailAddress from, EmailAddress to, String subject, String body) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailMessage other = (EmailMessage) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}
    
    
}

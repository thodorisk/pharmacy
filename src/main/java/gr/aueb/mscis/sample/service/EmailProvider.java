package gr.aueb.mscis.sample.service;

import gr.aueb.mscis.sample.contacts.EmailMessage;

public interface EmailProvider {

    void sendEmail(EmailMessage message);
}

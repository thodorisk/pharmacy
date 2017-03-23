package gr.aueb.mscis.sample.service;

import gr.aueb.mscis.sample.contacts.EmailMessage;

/**
 * Email provider.
 *
 */
public interface EmailProvider {
    /**
     * Send email.
     * @param message Email message.
     */
    void sendEmail(EmailMessage message);
}

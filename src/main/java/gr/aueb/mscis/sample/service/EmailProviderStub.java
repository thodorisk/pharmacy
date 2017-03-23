package gr.aueb.mscis.sample.service;

import java.util.ArrayList;
import java.util.List;


import gr.aueb.mscis.sample.contacts.EmailMessage;


public class EmailProviderStub implements EmailProvider{

    public List<EmailMessage> allMessages = new ArrayList<EmailMessage>(); 
    
   
    public List<EmailMessage> getAllEmails() {
        return allMessages;
    }

    public void sendEmail(EmailMessage message) {        
        allMessages.add(message);
    }
}

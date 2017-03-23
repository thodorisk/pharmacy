package gr.aueb.mscis.sample.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.sample.LibraryException;
import gr.aueb.mscis.sample.contacts.EmailAddress;
import gr.aueb.mscis.sample.contacts.EmailMessage;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.persistence.JPAUtil;

/**
 * Notification service of defective lot.
 */
public class NotificationService {
    private EmailProvider provider;
    
    
    /**
     * Sets the mail provider
     * @param provider Mail provider.
     */
    public void setProvider(EmailProvider provider) {
        this.provider = provider;
    }

    /**
     * Informs pharmacists that
     * they have received defective lot
     */
    @SuppressWarnings("unchecked")
	public void notifyPharmacists(int lotNumber) {
        if (provider == null) {
            throw new LibraryException();
        }

        EntityManager em  = JPAUtil.createEntityManager();
        CatalogService cs = new CatalogService(em);
        Lot lotfound = cs.findLotByLotNumber(lotNumber);
        List<LineItem> listlineitems = new ArrayList<LineItem>(lotfound.getLineItems());
        Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();
        try {
        	for (LineItem li : listlineitems) {
        		pharmacists.add(li.getOrder().getAccount().getPharmacist());
        	}
        }
        catch (LibraryException e) {throw new LibraryException("There are no pharmacists with defective batch.");}
            
        
        for (Pharmacist pharmacist : pharmacists) {
            if ( pharmacist.getEmail() != null && pharmacist.getEmail().isValid()) {
                String message = composeMessage(lotfound);
                sendEmail(pharmacist,
                        "Ελαττωματική παρτίδα", message);
            }
        }
        
        em.close();
    }


    private void sendEmail(Pharmacist pharmacist,
            String subject, String message) {
        EmailAddress eMail  = pharmacist.getEmail();
        if (eMail == null || !eMail.isValid()) {
            return;
        }
        
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(eMail);
        emailMessage.setSubject(subject);
        emailMessage.setBody(message);
        provider.sendEmail(emailMessage);
    }

    private String composeMessage(Lot lot) {
        String message = "Έχετε λάβει ελαττωματική παρτίδα με όνομα προϊόντος ";
        message += lot.getProduct().getName();
        message += "\n με αριθμό EOF ";
        message += lot.getProduct().getEofn();
        message += "\n Θα χρειαστεί να επιστρέψετε την συγκεκριμένη παρτίδα ";
        return message;
    }
}

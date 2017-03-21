package gr.aueb.test.service;

import org.junit.Assert;
import org.junit.Test;
import gr.aueb.mscis.sample.contacts.EmailMessage;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.service.EmailProviderStub;
import gr.aueb.mscis.sample.service.NotificationService;
import gr.aueb.mscis.sample.service.PharmacistService;
import gr.aueb.test.util.SystemDateStub;

public class NotificationServiceTest extends PharmacyServiceTest {

	private EmailProviderStub provider;

	@Override
	protected void beforeDatabasePreparation() {
		provider = new EmailProviderStub();
	}

	@Override
	protected void afterTearDown() {
		SystemDateStub.reset();
	}

	/**
	 * Ελαττωματική παρτίδα με αριθμό '601'.
	 * Πραγματοποιείτε αναζήτηση των φαρμακοποιών για τη συγκεκριμένη ελαττωματική 
	 * παρτίδα. Περιμένουμε την αποστολή μόνο ενός μηνύματος σε έναν φαρμακοποιό.
	 */
	@Test
	public void sendMessageOnDefectiveBatch() {
		int DefectiveBatch = 601;
		NotificationService service = new NotificationService();
		PharmacistService ps = new PharmacistService(em);
		service.setProvider(provider);
		service.notifyPharmacists(DefectiveBatch);

		Pharmacist kaparakou = ps.findPharmacistsByAFM("1234567890").get(0);
		Assert.assertEquals(1, provider.allMessages.size());
		EmailMessage message = provider.getAllEmails().get(0);
		Assert.assertEquals(kaparakou.getEmail(), message.getTo());
	}

}

package gr.aueb.test.contacts;

import org.junit.Test;

import gr.aueb.mscis.sample.contacts.EmailAddress;
import gr.aueb.mscis.sample.contacts.EmailMessage;
import gr.aueb.test.util.BasicEqualTester;

public class EmailMessageTest {
	
    @Test
    public void equalsAndHashCode() {
    	
        BasicEqualTester<EmailMessage> equalsTester = new BasicEqualTester<EmailMessage>();
        
        equalsTester.setObjectUnderTest(new EmailMessage());        
        
        
        equalsTester.otherObjectIsOfDifferentType(new Object());            
        
        equalsTester.bothObjectsHaveNoState(new EmailMessage());
        
        equalsTester.setObjectUnderTest(new EmailMessage(new EmailAddress("a@b.gr"),new EmailAddress("c@d.gr"),"Subject","Body"));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new EmailMessage());        
        equalsTester.objectsHaveDifferentState(new EmailMessage(new EmailAddress("a@b.gr"),new EmailAddress("c@d.gr"),"Subject2","Body2"));        
        equalsTester.bothObjectsHaveSameState(new EmailMessage(new EmailAddress("a@b.gr"),new EmailAddress("c@d.gr"),"Subject","Body"));
                
        EmailMessage emailmessage = equalsTester.getObjectUnderTest();
        equalsTester.sameReferences(emailmessage); 
    }

}

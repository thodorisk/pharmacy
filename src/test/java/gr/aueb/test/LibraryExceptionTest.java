package gr.aueb.test;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.sample.LibraryException;

public class LibraryExceptionTest {

	
    @Test
    public void libraryExceptionMessageTest() {
    	Throwable th = new Throwable("Cause!!");
    	LibraryException le = new LibraryException("message", th);
    	Assert.assertEquals(le.getMessage(),"message");
    }
    
    @Test
    public void libraryExceptionCauseTest() {
    	Throwable th = new Throwable("Cause!!");
    	LibraryException le = new LibraryException(th);
    	Assert.assertEquals(le.getCause(),th);
    }
    
    @Test
    public void libraryExceptionConstructorTest() {
    	LibraryException le = new LibraryException();
    	Assert.assertNotNull(le);
    }
}

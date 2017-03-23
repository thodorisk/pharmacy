package gr.aueb.test.resource;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.model.Person;
import gr.aueb.mscis.sample.resource.DebugExceptionMapper;
import gr.aueb.mscis.sample.resource.PharmacyUri;
import gr.aueb.mscis.sample.resource.PharmacistInfo;
import gr.aueb.mscis.sample.resource.PharmacistResource;
import gr.aueb.test.resource.PharmacyResourceTest;

import static gr.aueb.mscis.sample.resource.PharmacyUri.*;

public class PharmacistResourceTest extends PharmacyResourceTest {

    @Override
    protected Application configure() {
		/*
		 *
		 */
        return new ResourceConfig(PharmacistResource.class, DebugExceptionMapper.class);
    }

    @Test
    public void testListAllPharmacists() {

        List<PharmacistInfo> pharmacists = target(PHARMACISTS).request().get(new GenericType<List<PharmacistInfo>>() {
        });
        Assert.assertEquals(2, pharmacists.size());
    }


    @Test
    public void testSearchPharmacistByLastName() {
        System.out.println(PharmacyUri.pharmacistSearchUri("Kaparakou"));
        List<PharmacistInfo> pharmacists = target(PHARMACIST_SEARCH_LASTNAME).queryParam("last_name", "Kaparakou").request()
                .get(new GenericType<List<PharmacistInfo>>() {
                });

        Assert.assertEquals(1, pharmacists.size());
    }

    @Test
    public void testSearchPharmacistByAfm() {
        System.out.println(PharmacyUri.pharmacistVatNoSearchUri("1234567890"));
        List<PharmacistInfo> pharmacists = target(PHARMACIST_SEARCH_AFM).queryParam("vatNo", "1234567890").request()
                .get(new GenericType<List<PharmacistInfo>>() {
                });

        Assert.assertEquals(1, pharmacists.size());
    }
}

package gr.aueb.test.service;

import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.PharmacistService;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by thodoriskaragiannis on 14/03/2017.
 */
public class PharmacistServiceTest {

    private Initializer dataHelper;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @Test
    public void findPharmacistsByLastNameTest() {
        String EXPECTED_LASTNAME = "Kaparakou";
        EntityManager em = JPAUtil.getCurrentEntityManager();
        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findPharmacistsByLastName("Kaparakou");
        assertNotNull(pharmacists);
        assertEquals(EXPECTED_LASTNAME, pharmacists.get(0).getLastName());

    }

    @Test
    public void findPharmacistsByEmailTest() {
        String EXPECTED_NAME = "Tereza";
        EntityManager em = JPAUtil.getCurrentEntityManager();
        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findPharmacistsByEmail("tkaparakou@aueb.gr");
        assertNotNull(pharmacists);
        assertEquals(EXPECTED_NAME, pharmacists.get(0).getFirstName());

    }

    @Test
    public void findPharmacistsByAfmTest() {
        String EXPECTED_NAME = "Thodoris";
        EntityManager em = JPAUtil.getCurrentEntityManager();
        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findPharmacistsByAFM("9876543210");
        assertNotNull(pharmacists);
        assertEquals(EXPECTED_NAME, pharmacists.get(0).getFirstName());

    }

    @Test
    public void findAllPharmacistsTest() {
        int EXPECTED_NUMBER = 2;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findAllPharmacists();
        assertNotNull(pharmacists);
        assertEquals(EXPECTED_NUMBER, pharmacists.size());

    }
}

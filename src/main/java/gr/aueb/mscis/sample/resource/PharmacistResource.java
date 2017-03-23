package gr.aueb.mscis.sample.resource;

import static gr.aueb.mscis.sample.resource.PharmacyUri.PHARMACISTS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.service.PharmacistService;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class PharmacistResource manages client requests related to pharmacists.
 */

@Path(PHARMACISTS)
public class PharmacistResource extends AbstractResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PharmacistInfo> listAllPharmacists() {
        EntityManager em = getEntityManager();
        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findAllPharmacists();

        List<PharmacistInfo> pharmacistInfo = PharmacistInfo.wrap(pharmacists);

        em.close();
        return pharmacistInfo;

    }
    
    @GET
    @Path("{pharmacistId:[0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public PharmacistInfo listPharmacistById(@PathParam("pharmacistId") int pharmacistId) {
        EntityManager em = getEntityManager();
        PharmacistService ps = new PharmacistService(em);
        Pharmacist pharmacist = ps.findPharmacistById(pharmacistId);

        PharmacistInfo pharmacistInfo = PharmacistInfo.wrap(pharmacist);

        em.close();
        return pharmacistInfo;

    }

    @GET
    @Path("search_afm")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PharmacistInfo> searchPharmacistByAfm(@QueryParam("vatNo") String vatNo) {

        EntityManager em = getEntityManager();

        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findPharmacistsByAFM(vatNo);

        List<PharmacistInfo> pharmacistInfo = PharmacistInfo.wrap(pharmacists);
        em.close();

        return pharmacistInfo;
    }

    @GET
    @Path("search_lastname")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PharmacistInfo> searchPharmacistByLastName(@QueryParam("last_name") String last_name) {

        EntityManager em = getEntityManager();

        PharmacistService ps = new PharmacistService(em);
        List<Pharmacist> pharmacists = ps.findPharmacistsByLastName(last_name);

        List<PharmacistInfo> pharmacistInfo = PharmacistInfo.wrap(pharmacists);
        em.close();

        return pharmacistInfo;
    }

}

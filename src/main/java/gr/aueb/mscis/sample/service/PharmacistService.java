package gr.aueb.mscis.sample.service;

import java.util.List;
import javax.persistence.EntityManager;
import gr.aueb.mscis.sample.model.Pharmacist;

public class PharmacistService {

	private EntityManager em;

	public PharmacistService(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pharmacist> findPharmacistsByLastName(String last_name) {

		List<Pharmacist> results = null;
		results = em
				.createQuery(
						"select p from Pharmacist p where p.person.lastName like :surname ")
				.setParameter("surname", last_name).getResultList();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Pharmacist> findPharmacistsByVat(String vat_no) {

		List<Pharmacist> results = null;
		results = em
				.createQuery(
						"select p from Pharmacist p where p.person.vatNo like :vatno ")
				.setParameter("vatno", vat_no).getResultList();

		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pharmacist> findPharmacistsByEmail(String email) {

		List<Pharmacist> results = null;
		results = em
				.createQuery(
						"select p from Pharmacist p where p.person.email like :emailaddress")
				.setParameter("emailaddress","%" + email + "%").getResultList();

		return results;
	}
	
	
	public Pharmacist findPharmacistById(int id) {
		return em.find(Pharmacist.class, id);
	}
	
	public List<Pharmacist> findAllPharmacists() {
		List<Pharmacist> results = null;

		results = em.createQuery("select p from Pharmacist p", Pharmacist.class)
				.getResultList();

		return results;
	}
	
}

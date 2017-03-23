package gr.aueb.mscis.sample.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import gr.aueb.mscis.sample.contacts.EmailAddress;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.model.Product;

/**
 * PharmacistService is responsible for the pharmacist management
 */
public class PharmacistService {

	private EntityManager em;

	public PharmacistService(EntityManager em) {
		this.em = em;
	}
	
	
	public Pharmacist findPharmacistById(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Pharmacist pharmacist = null;
		try {
			pharmacist = em.find(Pharmacist.class, id);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}
		return pharmacist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pharmacist> findPharmacistsByLastName(String last_name) {

		List<Pharmacist> results = null;
		results = (List<Pharmacist>) em
				.createQuery(
						"select p from Pharmacist p where p.person.lastName like :surname ")
				.setParameter("surname", last_name).getResultList();

		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Pharmacist> findPharmacistsByAFM(String afm) {

		List<Pharmacist> results = null;
		results = em
				.createQuery(
						"select p from Pharmacist p where p.person.vatNo like :vatNumber")
				.setParameter("vatNumber", afm ).getResultList();

		return results;
	}

	public List<Pharmacist> findAllPharmacists() {
		List<Pharmacist> results = null;

		results = em.createQuery("select p from Pharmacist p", Pharmacist.class)
				.getResultList();

		return results;
	}
	
}

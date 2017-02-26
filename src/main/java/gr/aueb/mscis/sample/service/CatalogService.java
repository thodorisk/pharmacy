package gr.aueb.mscis.sample.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import gr.aueb.mscis.sample.model.Product;

public class CatalogService {
	
	private EntityManager em;

	public CatalogService(EntityManager em) {
		this.em = em;
	}
	
	public Product save(Product product) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (product.getId() != null) {
			// beware, always use the result of merge
			product = em.merge(product);
		} else {
			em.persist(product);
		}
		tx.commit();
		return product;

	}
}

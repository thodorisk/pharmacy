package gr.aueb.mscis.sample.service;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductByName(String name) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Product> results = null;

		results = em.createQuery("select p from Product p join fetch p.lots l where p.name like :name")

				.setParameter("name", "%" + name + "%").getResultList();
		tx.commit();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductByCategory (String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Product> results = null;

		results = em.createQuery("select p from Product p join fetch p.category c where p.category.description like :name")

				.setParameter("name", "%" + description + "%").getResultList();
		tx.commit();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findProductByEOF (String eof) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Product> results = null;

		results = em.createQuery("select p from Product p where p.eofn like :name")

				.setParameter("name", "%" + eof + "%").getResultList();
		tx.commit();
		return results;
	}
	
		
	public Product findProductById(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Product product = null;
		try {
			product = em.find(Product.class, id);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}
		return product;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findAllProducts() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Product> results = null;

		results = em.createQuery("select p from Product p").getResultList();

		tx.commit();
		return results;
	}

}

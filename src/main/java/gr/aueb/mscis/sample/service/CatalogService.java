package gr.aueb.mscis.sample.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import gr.aueb.mscis.sample.model.Cart;
import gr.aueb.mscis.sample.model.CartItem;
import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.OnSale;
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

	public boolean deleteProduct(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Product product = em.getReference(Product.class, id);
			System.out.println("Product deleted: " + product.getName());
			em.remove(product);
		} catch (EntityNotFoundException e) {
			tx.rollback();
			return false;
		}

		tx.commit();
		return true;

	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductByName(String name) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Product> results = null;

		results = em.createQuery("select p from Product p where p.name like :name")
				.setParameter("name", "%" + name + "%").getResultList();

		tx.commit();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductByCategory (String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Product> results = null;

		results = em.createQuery("select p from Product p where p.category.description like :name")
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
	
	@SuppressWarnings("unchecked")
	public Lot findLotByLotNumber (int lotno) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Lot result = null;
		result = (Lot) em.createQuery("select l from Lot l where l.lotno like :name")
				.setParameter("name",Integer.valueOf(lotno)).getSingleResult();
		tx.commit();
		return result;
	}
	
	public void addLot(Product product, Lot newlot) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		product.getLots().add(newlot);
		tx.commit();
	}
	
	
	
public void UpdateOnSale(Product product, OnSale onsale) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		product.setOnSale(onsale);
		tx.commit();
	}
	
	public void RemoveOnSale(Product product) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		product.setOnSale(null);
		tx.commit();
	}
	
	public int getStock(String eof) {
		int result = 0;
		List<Product> products = findProductByEOF(eof);
		Set<Lot> lots = products.get(0).getLots();
		for(Lot lot : lots)
			result += lot.getQuantity();
		
		return result;
	}
	
	public CartItem findCartItemById(int id) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		CartItem cartitem = null;
		try {
			cartitem = em.find(CartItem.class, id);
			tx.commit();
		} catch (NoResultException ex) {
			tx.rollback();
		}
		return cartitem;
	}
	
}

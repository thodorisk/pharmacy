package gr.aueb.mscis.sample.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.Movie;


public class Initializer  {


    /**
     * Remove all data from database.
     * The functionality must be executed within the bounds of a transaction
     */
    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
       
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = null;

        query = em.createNativeQuery("delete from movies");
        query.executeUpdate();
        
        query = em.createNativeQuery("delete from categories");
        query.executeUpdate();
        
        query = em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1");
        query.executeUpdate();
        
        tx.commit();
        
    }
    

    public void prepareData() {

        eraseData();                      

        Product Depon = new Product("Depon", 000345678, "D999777");
        Product Panadol = new Product("Panadol", 777888999, "P333444");
        
        Category cat = new Category();
        Category cat2 = new Category();
        cat.setDescription("Analgetic");
        cat2.setDescription("Analgetic");
       
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(depon);
        em.persist(panadol);
        em.persist(cat);
        em.persist(cat2);
        
        tx.commit();
    
    }
}

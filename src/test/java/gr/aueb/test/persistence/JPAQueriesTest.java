package gr.aueb.test.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.sample.persistence.JPAUtil;

import gr.aueb.mscis.sample.persistence.Initializer;

import gr.aueb.mscis.sample.model.Lot;
import gr.aueb.mscis.sample.model.Product;


/**
 * Η κλάση αυτή δεν έχει σκοπό να ελέγξει κάποια λειτουργικότητα
 * του λογισμικού αλλά περισσότερο να επιδείξει διαφορετικού τύπου
 * ερωτήματα στη JPA-QL
 * 
 *
 */
public class JPAQueriesTest {

    private Initializer dataHelper;

    @Before
    public void setUpJpa() {
        dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void simpleQuery1() {
        int EXPECTED_PRODUCT_NUMBER = 4;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
        List<Product> results = query.getResultList();      
        Assert.assertEquals(EXPECTED_PRODUCT_NUMBER, results.size());
        
    }
    

  
    
    @SuppressWarnings("unchecked")
    @Test
    public void restrictionQuery() {
        int EXPECTED_NUMBER_STARTING_WITH_DEPON = 1;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select p from Product p where name like :titleCrit");
        query.setParameter("titleCrit", "Depon");
        List<Product> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_NUMBER_STARTING_WITH_DEPON,results.size());
        
    }
      
    @SuppressWarnings("unchecked")
    @Test
    public void implicitJoin() {
        int EXPECTED_LOTS_STARTING_WITH_DEPON = 2;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select l from Lot l where l.product.name like 'Depon'");        
        List<Lot> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_LOTS_STARTING_WITH_DEPON,results.size());        
    }
    
    @Test
    public void innerJoin() {
        int EXPECTED_LOT_NUMBER = 6;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select l from Lot l join l.product p");        
        Assert.assertEquals(EXPECTED_LOT_NUMBER,query.getResultList().size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void outerJoin() {
        int EXPECTED_LOT_NUMBER = 6;
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select l from Lot l left join l.product p");        
        @SuppressWarnings("unused")
        List<Product> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_LOT_NUMBER,query.getResultList().size());
    }
    

}

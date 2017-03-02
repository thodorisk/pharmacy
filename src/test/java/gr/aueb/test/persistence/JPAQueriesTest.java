package gr.aueb.test.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("pharmacy");
        em = emf.createEntityManager();
        initializeData();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    

    
    @SuppressWarnings("unchecked")
    @Test
    public void simpleQuery() {
        int EXPECTED_PRODUCT_NUMBER = 1;
        Query query = em.createQuery("select p from Product p");
        List<Product> results = query.getResultList();      
        Assert.assertEquals(EXPECTED_PRODUCT_NUMBER, results.size());
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void restrictionQuery() {
        int EXPECTED_NUMBER_STARTING_WITH_DEPON = 1;
        Query query = em.createQuery("select p from Product p where name like :titleCrit");
        query.setParameter("titleCrit", "Depon");
        List<Product> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_NUMBER_STARTING_WITH_DEPON,results.size());
        
    }
      
    @SuppressWarnings("unchecked")
    @Test
    public void implicitJoin() {
        int EXPECTED_LOTS_STARTING_WITH_DEPON = 2;
        Query query = em.createQuery("select l from Lot l where l.product.name like 'Depon'");        
        List<Lot> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_LOTS_STARTING_WITH_DEPON,results.size());        
    }
    
    @Test
    public void innerJoin() {
        int EXPECTED_LOT_NUMBER = 2;
        Query query = em.createQuery("select l from Lot l join l.product p");        
        Assert.assertEquals(EXPECTED_LOT_NUMBER,query.getResultList().size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void outerJoin() {
        int EXPECTED_LOT_NUMBER = 2;
        Query query = em.createQuery("select l from Lot l left join l.product p");        
        @SuppressWarnings("unused")
        List<Product> results = query.getResultList();  
        Assert.assertEquals(EXPECTED_LOT_NUMBER,query.getResultList().size());
    }
    
    public void  eraseData() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query;

        query = em.createNativeQuery("DELETE FROM lots");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM products");
        query.executeUpdate();

        tx.commit();
    }
    
    public void initializeData() {
        eraseData();
        
        Product deponproduct = new Product("Depon", "111", 5.00);

        //Add stock to the above products
        Lot deponfirstlot = new Lot(601, 10);
        deponfirstlot.setProduct(deponproduct);

        Lot deponsecondlot = new Lot(602, 11);
        deponsecondlot.setProduct(deponproduct);

        deponproduct.getLots().add(deponfirstlot);
        deponproduct.getLots().add(deponsecondlot);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(deponfirstlot);
        em.persist(deponsecondlot);
        em.persist(deponproduct);
        tx.commit();
        
    }
}

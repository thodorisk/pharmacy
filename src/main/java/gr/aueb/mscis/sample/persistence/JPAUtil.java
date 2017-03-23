package gr.aueb.mscis.sample.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();

    // Create a new entity manager factory or get the existing.
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("pharmacy");
        }
        return emf;
    }

    // Create a new entity manager or get the existing.
    public static EntityManager getCurrentEntityManager() {      
        EntityManager em = currentEntityManager.get();         
        if (em  == null || !em.isOpen()) {
            em = getEntityManagerFactory().createEntityManager();
            currentEntityManager.set(em);
        }
        return em;
    }

    // Create a new entity manager.
    public static EntityManager createEntityManager() {
    	
    	return getEntityManagerFactory().createEntityManager();
    }
}

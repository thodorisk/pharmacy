package gr.aueb.mscis.sample.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;



import gr.aueb.mscis.sample.model.Category;
import gr.aueb.mscis.sample.model.LineItem;
import gr.aueb.mscis.sample.model.Product;
import gr.aueb.mscis.sample.model.Account;
import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.model.Order;
import gr.aueb.mscis.sample.model.OrderState;


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

        query = em.createNativeQuery("delete from products");
        query.executeUpdate();

        query = em.createNativeQuery("delete from categories");
        query.executeUpdate();

        query = em.createNativeQuery("delete from accounts");
        query.executeUpdate();

        query = em.createNativeQuery("delete from pharmacists");
        query.executeUpdate();

        query = em.createNativeQuery("delete from orders");
        query.executeUpdate();

        query = em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1");
        query.executeUpdate();

        tx.commit();
        em.close();

    }


    public void prepareData() {

        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();

        Order firstorder = new Order("2017-02-22",OrderState.PENDING,20.0,111,2);

        Set<LineItem>  asd = new HashSet<LineItem>();

        LineItem firstlineitem = new LineItem (5);
        asd.add(firstlineitem);
        firstorder.setLineItems(asd);

        firstlineitem.setOrder(firstorder);

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(firstorder);
        em.persist(firstlineitem);

        tx.commit();
        em.close();


    }
}
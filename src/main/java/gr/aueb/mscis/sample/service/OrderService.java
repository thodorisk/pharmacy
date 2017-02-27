package gr.aueb.mscis.sample.service;


import gr.aueb.mscis.sample.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class OrderService {

    private EntityManager em;

    public OrderService(EntityManager em) {
        this.em = em;
    }

    public Order findOrderById(int id) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Order order = null;
        try {
            order = em.find(Order.class, id);
            tx.commit();
        } catch (NoResultException ex) {
            tx.rollback();
        }
        return order;
    }

    public List<Order> findAllOrders() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Order> results = null;

        results = em.createQuery("select o from Order o").getResultList();

        tx.commit();
        return results;
    }
}

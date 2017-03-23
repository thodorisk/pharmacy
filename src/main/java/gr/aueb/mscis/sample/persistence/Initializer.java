package gr.aueb.mscis.sample.persistence;

import gr.aueb.mscis.sample.contacts.EmailAddress;
import gr.aueb.mscis.sample.model.*;
import gr.aueb.mscis.sample.service.*;
import gr.aueb.mscis.sample.util.SimpleCalendar;

import javax.persistence.*;

public class Initializer  {
	
    /**
     * Remove all data from database.
     * The functionality must be executed within the bounds of a transaction
     */
    public void  eraseData() {
    	EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query;
        
        query = em.createNativeQuery("DELETE FROM lot_line");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM lines");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM lots");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM pharmacists");
        query.executeUpdate();
   
        query = em.createNativeQuery("DELETE FROM cartitems");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM orders");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM cart");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM products");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM onsales");
        query.executeUpdate();
       
        query = em.createNativeQuery("DELETE FROM accounts");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM categories");
        query.executeUpdate();
        
        tx.commit();
    }

    
    public void prepareData() {

        // Delete all data before inserting new
        eraseData();
        EntityManager em = JPAUtil.createEntityManager();
        
        //Create Pharmacists
        Pharmacist terkap = new Pharmacist("Tereza","Kaparakou",new EmailAddress("tkaparakou@aueb.gr"),null,"2109999999", "1234567890");
        Pharmacist thokar = new Pharmacist("Thodoris","Karagiannis",new EmailAddress("thkaragiannis@aueb.gr"),null,"2109999998", "9876543210");
        
        
        //Create Pharmacists accounts
        Account terkap_acc = new Account("terkap", "12345", false, "2016-01-01");
        Account thokar_acc = new Account("thokar", "67891", false, "2016-01-01");
        
        //Connect Pharmacists to their accounts
        terkap.setAccount(terkap_acc);
        thokar.setAccount(thokar_acc);
        terkap_acc.setPharmacist(terkap);
        thokar_acc.setPharmacist(thokar);
        
        //Create products
        Product deponproduct = new Product("Depon", "111", 5.0);
        Product comtrexproduct = new Product("Comtrex", "112", 7.00);
        Product vitamincproduct = new Product("Vitamin C", "113", 8.00);


        //Add stock to the above products
        Lot deponfirstlot = new Lot(601, 10);
        deponfirstlot.setProduct(deponproduct);
        Lot deponsecondlot = new Lot(602, 11);
        deponsecondlot.setProduct(deponproduct);
        deponproduct.getLots().add(deponfirstlot);
        deponproduct.getLots().add(deponsecondlot);

        Lot comtrexfirstlot = new Lot(400, 20);
        comtrexfirstlot.setProduct(comtrexproduct);
        Lot comtrexsecondlot = new Lot(401, 21);
        comtrexsecondlot.setProduct(comtrexproduct);
        comtrexproduct.getLots().add(comtrexfirstlot);
        comtrexproduct.getLots().add(comtrexsecondlot);

        Lot vitamincfirstlot = new Lot(752, 30);
        vitamincfirstlot.setProduct(vitamincproduct);
        Lot vitamincsecondlot = new Lot(753, 31);
        vitamincsecondlot.setProduct(vitamincproduct);
        vitamincproduct.getLots().add(vitamincfirstlot);
        vitamincproduct.getLots().add(vitamincsecondlot);


        //Create categories
        Category drugscategory1 = new Category("Drugs");
        deponproduct.setCategory(drugscategory1);
        comtrexproduct.setCategory(drugscategory1);

        Category drugscategory2 = new Category("Vitamins");
        vitamincproduct.setCategory(drugscategory2);

        //Create OnSale
        deponproduct.setOnSale(new OnSale(10.0, new SimpleCalendar(2017,1,1), new SimpleCalendar(2017,12,31)));
        comtrexproduct.setOnSale(new OnSale(20.0, new SimpleCalendar(2016,1,1), new SimpleCalendar(2016,12,31)));

        //Create Order for the account terkap_acc
        Order firstOrder = new Order(new SimpleCalendar(2016,11,1), OrderState.COMPLETED, 20.0);
        // Create a line item of the order
        LineItem firstlineItem = new LineItem();
        firstlineItem.setProduct(deponproduct);
        firstlineItem.setQuantity(5);
        firstlineItem.setOrder(firstOrder);
        deponproduct.getLineItems().add(firstlineItem);
        firstOrder.getLineItems().add(firstlineItem);
        firstOrder.setAccount(terkap_acc);
        terkap_acc.getOrders().add(firstOrder);
        // Create second line item of the order
        LineItem secondlineItem = new LineItem();
        secondlineItem.setProduct(vitamincproduct);
        secondlineItem.setQuantity(2);
        secondlineItem.setOrder(firstOrder);
        vitamincproduct.getLineItems().add(secondlineItem);
        firstOrder.getLineItems().add(secondlineItem);

        //Create Order1 for the account thokar_acc
        Order KaragiannisfirstOrder = new Order(new SimpleCalendar(2017,1,1), OrderState.COMPLETED, 10.0);
        // Create first line item of the order
        LineItem KaragiannisfirstlineItem = new LineItem();
        KaragiannisfirstlineItem.setProduct(comtrexproduct);
        KaragiannisfirstlineItem.setQuantity(12);
        KaragiannisfirstlineItem.setOrder(KaragiannisfirstOrder);
        comtrexproduct.getLineItems().add(KaragiannisfirstlineItem);
        KaragiannisfirstOrder.getLineItems().add(KaragiannisfirstlineItem);
        KaragiannisfirstOrder.setAccount(thokar_acc);
        thokar_acc.getOrders().add(KaragiannisfirstOrder);
        // Create second line item of the order
        LineItem KaragiannissecondlineItem = new LineItem();
        KaragiannissecondlineItem.setProduct(vitamincproduct);
        KaragiannissecondlineItem.setQuantity(30);
        KaragiannissecondlineItem.setOrder(KaragiannisfirstOrder);
        vitamincproduct.getLineItems().add(KaragiannissecondlineItem);
        KaragiannisfirstOrder.getLineItems().add(KaragiannissecondlineItem);
        
        //Create Order2 for the account thokar_acc
        Order KaragiannisfirstOrder_Second = new Order(new SimpleCalendar(2017,2,1), OrderState.COMPLETED, 50.0);
        // Create first line item of the order
        LineItem KaragiannisfirstlineItem_Second = new LineItem();
        KaragiannisfirstlineItem_Second.setProduct(deponproduct);
        KaragiannisfirstlineItem_Second.setQuantity(12);
        KaragiannisfirstlineItem_Second.setOrder(KaragiannisfirstOrder_Second);
        deponproduct.getLineItems().add(KaragiannisfirstlineItem_Second);
        KaragiannisfirstOrder_Second.getLineItems().add(KaragiannisfirstlineItem_Second);
        KaragiannisfirstOrder_Second.setAccount(thokar_acc);
        thokar_acc.getOrders().add(KaragiannisfirstOrder_Second);
        // Create second line item of the order
        LineItem KaragiannissecondlineItem_Second = new LineItem();
        KaragiannissecondlineItem_Second.setProduct(vitamincproduct);
        KaragiannissecondlineItem_Second.setQuantity(55);
        KaragiannissecondlineItem_Second.setOrder(KaragiannisfirstOrder_Second);
        vitamincproduct.getLineItems().add(KaragiannissecondlineItem_Second);
        KaragiannisfirstOrder_Second.getLineItems().add(KaragiannissecondlineItem_Second);

        //Save data to database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(terkap);
        em.persist(thokar);
        em.persist(firstOrder);
        em.persist(firstlineItem);
        em.persist(secondlineItem);
        em.persist(deponfirstlot);
        em.persist(deponsecondlot);
        em.persist(comtrexfirstlot);
        em.persist(comtrexsecondlot);
        em.persist(vitamincfirstlot);
        em.persist(vitamincsecondlot);
        em.persist(deponproduct);
        em.persist(comtrexproduct);
        em.persist(vitamincproduct);
        em.persist(KaragiannisfirstOrder);
        em.persist(KaragiannisfirstlineItem);
        em.persist(KaragiannissecondlineItem);
        em.persist(KaragiannisfirstOrder_Second);
        em.persist(KaragiannisfirstlineItem_Second);
        em.persist(KaragiannissecondlineItem_Second);

        tx.commit();

        //-------------------------------------------------------------------------------------------------------
        // ADD another product using CatalogService save functionality
    	CatalogService cs = new CatalogService(em);
    	Product productnew = cs.save(new Product("Panadol", "120", 3.00));

    	EntityTransaction tx1 = em.getTransaction();
        tx1.begin();
    	em.persist(productnew);
    	tx1.commit();
        
        OrderService os = new OrderService(em);
        // Remove Discount of products with EOF number 111 & 112.
        cs.RemoveOnSale(cs.findProductByEOF("111").get(0));
        cs.RemoveOnSale(cs.findProductByEOF("112").get(0));

        //New Order for the Pharmacist: Kaparakou
        int orderid = os.createCartOrder("1234567890");
        
        // Add two cart items in the shopping cart
        CartItem firstcartitem = os.updateCartItem(orderid, "111", 12, -1);
        CartItem secondcartitem = os.updateCartItem(orderid, "112", 3, -1);

        // Complete the order
        os.completeOrder(os.showCartByPharmacist("1234567890"));

        //New Order for the Pharmacist: Kaparakou
        int orderid2 = os.createCartOrder("1234567890");

        // Add two cart items in the shopping cart
        CartItem firstcartitem2 = os.updateCartItem(orderid2, "111", 5, -1);
        CartItem secondcartitem2 = os.updateCartItem(orderid2, "112", 3, -1);
        //Change quantity to second cartitem
        CartItem updatesecondcartitem2 = os.updateCartItem(orderid2, "112", 5, secondcartitem2.getId());

        //Remove first cart item of the shopping cart
        os.removeCartItem(firstcartitem2.getId());

        // Complete the order
        os.completeOrder(os.showCartByPharmacist("1234567890"));

        //New Order for the Pharmacist: Karagiannis
        int orderid3 = os.createCartOrder("9876543210");

        CartItem firstcartitem3 = os.updateCartItem(orderid3, "113", 5, -1);
        CartItem updatefirstcartitem3 = os.updateCartItem(orderid3, "113", 2, firstcartitem3.getId());
        
        os.completeOrder(os.showCartByPharmacist("9876543210"));

        em.close();
    }
}
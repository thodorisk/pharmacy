package gr.aueb.mscis.sample.persistence;

import gr.aueb.mscis.sample.model.*;
import gr.aueb.mscis.sample.service.*;
import gr.aueb.mscis.sample.util.SimpleCalendar;

import javax.persistence.*;

public class Initializer  {
	
	public static final int KAPARAKOU_ID = 1;
	public static final int KARAGIANNIS_ID = 2;
    public static final int KOROPOULIS_ID = 3;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmacy");
    private EntityManager em = emf.createEntityManager();

    /**
     * Remove all data from database.
     * The functionality must be executed within the bounds of a transaction
     */
    public void  eraseData() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query;

        query = em.createNativeQuery("DELETE FROM products");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM categories");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM accounts");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM pharmacists");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM orders");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM lines");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM cart");
        query.executeUpdate();

        query = em.createNativeQuery("DELETE FROM onsales");
        query.executeUpdate();
        
        query = em.createNativeQuery("DELETE FROM lots");
        query.executeUpdate();


        tx.commit();
    }

    
    public void prepareData() {

        // Delete all data before inserting new
        eraseData();

        //Create Pharmacists
        Pharmacist terkap = new Pharmacist("Tereza","Kaparakou","tkaparakou@aueb.gr",null,"2109999999", "1234567890");
        Pharmacist thokar = new Pharmacist("Thodoris","Karagiannis","thkaragiannis@aueb.gr",null,"2109999998", "9876543210");
        Pharmacist dkoro = new Pharmacist("Dionusis","Koropoulis","dkoropoulis@aueb.gr",null,"2109999997", "5555555555");
        
        //Create Pharmacists accounts
        Account terkap_acc = new Account("terkap", "12345", false, "2016-01-01");
        Account thokar_acc = new Account("thokar", "67891", false, "2016-01-01");
        Account dkoro_acc = new Account("dkoro", "55555", false, "2017-01-01");
        
        //Connect Pharmacists to their accounts
        terkap.setAccount(terkap_acc);
        thokar.setAccount(thokar_acc);
        dkoro.setAccount(dkoro_acc);
        
        //Create products
        Product deponproduct = new Product("Depon", "111", 5.00);
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
        deponproduct.setOnSale(new OnSale(10.0, new SimpleCalendar(2017,3,1), new SimpleCalendar(2017,4,1)));
        comtrexproduct.setOnSale(new OnSale(8.0, new SimpleCalendar(2017,2,1), new SimpleCalendar(2017,5,1)));

        //Create Order - Pharmacist: Kaparakou
        Order firstOrder = new Order(new SimpleCalendar(2016,11,1), OrderState.PENDING, 20.0);
        LineItem firstlineItem = new LineItem();
        firstlineItem.setProduct(deponproduct);
        firstlineItem.setQuantity(5);
        firstlineItem.setOrder(firstOrder);
        deponproduct.getLineItems().add(firstlineItem);
        firstOrder.getLineItems().add(firstlineItem);
        firstOrder.setAccount(terkap_acc);
        terkap_acc.getOrders().add(firstOrder);

        LineItem secondlineItem = new LineItem();
        secondlineItem.setProduct(vitamincproduct);
        secondlineItem.setQuantity(2);
        secondlineItem.setOrder(firstOrder);
        vitamincproduct.getLineItems().add(secondlineItem);
        firstOrder.getLineItems().add(secondlineItem);
        
        //Create Two Orders - Pharmacist: Karagiannis
        //First Order - Karagiannis
        Order KaragiannisfirstOrder = new Order(new SimpleCalendar(2017,1,1), OrderState.PENDING, 10.0);
        LineItem KaragiannisfirstlineItem = new LineItem();
        KaragiannisfirstlineItem.setProduct(comtrexproduct);
        KaragiannisfirstlineItem.setQuantity(12);
        KaragiannisfirstlineItem.setOrder(KaragiannisfirstOrder);
        comtrexproduct.getLineItems().add(KaragiannisfirstlineItem);
        KaragiannisfirstOrder.getLineItems().add(KaragiannisfirstlineItem);
        KaragiannisfirstOrder.setAccount(thokar_acc);
        thokar_acc.getOrders().add(KaragiannisfirstOrder);
        
        LineItem KaragiannissecondlineItem = new LineItem();
        KaragiannissecondlineItem.setProduct(vitamincproduct);
        KaragiannissecondlineItem.setQuantity(30);
        KaragiannissecondlineItem.setOrder(KaragiannisfirstOrder);
        vitamincproduct.getLineItems().add(KaragiannissecondlineItem);
        KaragiannisfirstOrder.getLineItems().add(KaragiannissecondlineItem);
        
        //_Second (2) Order - Karagiannis
        Order KaragiannisfirstOrder_Second = new Order(new SimpleCalendar(2017,2,1), OrderState.PENDING, 50.0);
        LineItem KaragiannisfirstlineItem_Second = new LineItem();
        KaragiannisfirstlineItem_Second.setProduct(deponproduct);
        KaragiannisfirstlineItem_Second.setQuantity(12);
        KaragiannisfirstlineItem_Second.setOrder(KaragiannisfirstOrder_Second);
        deponproduct.getLineItems().add(KaragiannisfirstlineItem_Second);
        KaragiannisfirstOrder_Second.getLineItems().add(KaragiannisfirstlineItem_Second);
        KaragiannisfirstOrder_Second.setAccount(thokar_acc);
        thokar_acc.getOrders().add(KaragiannisfirstOrder_Second);
        
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
        productnew.setOnSale(new OnSale(20.0,new SimpleCalendar(2017,3,1),new SimpleCalendar(2017,4,1)));
    	productnew.setCategory(drugscategory1);
    	EntityTransaction tx1 = em.getTransaction();
        tx1.begin();
    	em.persist(productnew);
    	tx1.commit();

        //-------------------------------------------------------------------------------------------------------
    	//TODO: comment out to perform Delete operation on product with ID:19 -> Panadol
        //cs.deleteProduct(19);

        //-------------------------------------------------------------------------------------------------------
        //SHOW PRODUCTS
        System.out.println("\n-----------------");
        System.out.println("PRODUCTS SEARCHES");
        System.out.println("-----------------");

        SearchProductService searchProductService = new SearchProductService(cs);
        searchProductService.searchProductByCategory("Vitamins");
        searchProductService.searchProductByName("Depon");
        searchProductService.searchProductByEofn("120");

        //-------------------------------------------------------------------------------------------------------
        //SHOW ALL PHARMACISTs

        System.out.println("\n--------------------");
        System.out.println("PHARMACISTS SEARCHES");
        System.out.println("--------------------");

        SearchPharmacistService searchPharmacistService = new SearchPharmacistService(new PharmacistService(em));
        searchPharmacistService.searchAllPharmacist();

        //-------------------------------------------------------------------------------------------------------
        // SHOW ALL ORDERS
        System.out.println("\n---------------");
        System.out.println("ORDERS SEARCHES");
        System.out.println("---------------");

        SearchOrderService searchOrderService = new SearchOrderService(new OrderService(em));
        //searchOrderService.searchAllOrders();
        searchOrderService.searchAllOrdersByPharmacist("1234567890",new SimpleCalendar(2016,10,30),new SimpleCalendar(2016,11,11));
        //searchOrderService.searchAllOrdersByPharmacist("9876543210");

        //-------------------------------------------------------------------------------------------------------
        // SHOW ALL STATISTICS
        System.out.println("\n---------------");
        System.out.println("EARNINGS STATISTICS");
        System.out.println("---------------");
        StatisticService ss = new StatisticService(new OrderService(em));
        System.out.println(ss.earningsByPharmacist("9876543210",new SimpleCalendar(2016,12,30),new SimpleCalendar(2017,1,31)));
        searchOrderService.searchAllOrdersByPharmacist("9876543210",new SimpleCalendar(2016,12,30),new SimpleCalendar(2017,1,31));
        System.out.println(ss.totalSales());
        searchOrderService.searchSalesOfProductPerPeriod("111",new SimpleCalendar(2016,11,2),new SimpleCalendar(2017,2,1));
        System.out.println(ss.salesPerProductPerPeriod("111",new SimpleCalendar(2016,11,2),new SimpleCalendar(2017,2,1)));
        

        em.close();
        emf.close();
       
    }
}
package gr.aueb.mscis.sample.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import gr.aueb.mscis.sample.model.*;
import gr.aueb.mscis.sample.service.CatalogService;
import gr.aueb.mscis.sample.service.PharmacistService;

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

        query = em.createNativeQuery("DELETE FROM sales");
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
        Lot deponsecondlot = new Lot(602, 11);
        Lot comtrexfirstlot = new Lot(400, 20);
        Lot comtrexsecondlot = new Lot(401, 21);
        Lot vitamincfirstlot = new Lot(752, 30);
        Lot vitamincsecondlot = new Lot(753, 31);
        deponfirstlot.setProduct(deponproduct);
        deponsecondlot.setProduct(deponproduct);
        comtrexfirstlot.setProduct(comtrexproduct);
        comtrexsecondlot.setProduct(comtrexproduct);
        vitamincfirstlot.setProduct(vitamincproduct);
        vitamincsecondlot.setProduct(vitamincproduct);

        OnSale kalokairi = new OnSale(20.0,"2016","2017");

        //Create categories
        Category drugscategory1 = new Category("Drugs");
        Category drugscategory2 = new Category("Vitamins");
        deponproduct.setCategory(drugscategory1);
        comtrexproduct.setCategory(drugscategory1);
        vitamincproduct.setCategory(drugscategory2);

        Set asdg = new HashSet();
        asdg.add(vitamincfirstlot);
        asdg.add(vitamincsecondlot);
        vitamincproduct.setLots(asdg);
        
        
        //Create new order
        Order firstorder = new Order(new Date(),OrderState.PENDING,20.0,111,20);

        Set<LineItem> listofitems = new HashSet<>();
        
        //Create two lineitems
        LineItem firstlineitem = new LineItem (5);
        LineItem secondlineitem = new LineItem (12);
        
        
        listofitems.add(firstlineitem);
        listofitems.add(secondlineitem);
        
        firstorder.setLineItems(listofitems);

        firstlineitem.setOrder(firstorder);
        secondlineitem.setOrder(firstorder);
        
        //Save data to database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(terkap);
        em.persist(thokar);
        em.persist(firstorder);
        em.persist(firstlineitem);
        em.persist(secondlineitem);
        em.persist(deponfirstlot);
        em.persist(deponsecondlot);
        em.persist(comtrexfirstlot);
        em.persist(comtrexsecondlot);
        em.persist(vitamincfirstlot);
        em.persist(vitamincsecondlot);
        em.persist(deponproduct);
        em.persist(comtrexproduct);
        em.persist(vitamincproduct);       
        
        
        tx.commit();
        
        
        ////Random Queries for testing on an Entity Manager
        ////Random Queries for testing on an Entity Manager
        ////Random Queries for testing on an Entity Manager
        ////Random Queries for testing on an Entity Manager
        
        
        
//        Query query = em.createQuery("select order from Order order");
//        		//query.setParameter("quantityCrit", "5");
//        		List<Order> results = query.getResultList();
//        		
//        		Set <LineItem> size_items = results.get(0).getLineItems();
//        		for (LineItem item : size_items)
//        	        System.out.println(Integer.toString(item.getId()) + "		" + item.getQuantity());
        
        Order findorder = em.find(Order.class,5);
        Set <LineItem> size_items = findorder.getLineItems();
        System.out.println("");
        for (LineItem item : size_items)
        System.out.println(Integer.toString(item.getId()) + "		" + item.getQuantity());
        
        PharmacistService service = new PharmacistService(em);
    	List<Pharmacist> pharmacists = service.findPharmacistsByEmail("tkaparak");
    	for (Pharmacist pharmacist : pharmacists)
    	System.out.println(pharmacist.getLastName().toString());
    	
    	List<Pharmacist> listofAllpharmacists =  service.findAllPharmacists();
    	for (Pharmacist pharmacist : listofAllpharmacists)
    		System.out.println(pharmacist.getLastName().toString());
    	
    	List<Pharmacist> listpharmacistsbyAFM =  service.findPharmacistsByAFM("123456789");
    	for (Pharmacist pharmacist : listpharmacistsbyAFM)
        	System.out.println("123456789 	" + pharmacist.getLastName().toString());
    	
        
    	CatalogService cs = new CatalogService(em);
    	Product productnew = cs.save(new Product("Panadol", "120", 3.00));
        productnew.setOnSale(kalokairi);
    	System.out.println(productnew.getName().toString());
    	productnew.setCategory(drugscategory1);
    	EntityTransaction tx1 = em.getTransaction();
        tx1.begin();
    	em.persist(productnew);
    	tx1.commit();


        List<Product> allproducts =  cs.findProductByName("Vitamin C");
        int i=0;
        for (Product product : allproducts)
        {

            if (i < new ArrayList<Lot>(product.getLots()).size())
                System.out.println("Product Details: 	" + product.getName() + "	" + product.getCategory().getDescription() + "	" + product.getEofn() + "	" +  new ArrayList<Lot>(product.getLots()).get(i).getQuantity());
            i++;
        }

    	List<Product> allproductsbycategory =  cs.findProductByCategory("Vitamins");
    	for (Product product : allproductsbycategory)
        	System.out.println("Product:	" + product.getName().toString());
    	
    	
    	List<Product> allproductsbyEOF =  cs.findProductByEOF("111");
    	for (Product product : allproductsbyEOF)
        	System.out.println("Product:	" + product.getName().toString());


        //cs.deleteProduct(19);


        em.close();
        emf.close();
       
    }
}
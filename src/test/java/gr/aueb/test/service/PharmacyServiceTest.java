package gr.aueb.test.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public abstract class PharmacyServiceTest {

	Initializer dataHelper;
	protected EntityManager em;

	public PharmacyServiceTest() {
		super();
	}

	protected void beforeDatabasePreparation(){}
	
	protected void afterDatabasePreparation(){}

	@Before
	public final void setUp() {
		beforeDatabasePreparation();
		dataHelper = new Initializer();
		dataHelper.prepareData();
		em = JPAUtil.getCurrentEntityManager();
		afterDatabasePreparation();
	}

	protected void afterTearDown(){}
	
	@After
	public final void tearDown() {
		em.close();
		afterTearDown();
	}

}
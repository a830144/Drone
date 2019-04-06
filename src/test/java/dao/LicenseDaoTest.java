package dao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.impl.LicenseDaoImpl;
import entity.Licenses;
import junit.framework.TestCase;

public class LicenseDaoTest extends TestCase {
	LicenseDao LicenseDao;
	protected void setUp() throws Exception {
		LicenseDao = new LicenseDaoImpl();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDelete(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		LicenseDao = new LicenseDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		LicenseDao.setSessionFactory(sessionFactory);
		
		LicenseDao.deleteById(new Integer(10));
		
		tx1.commit();
	}
	
	public void testUpdate(){
		
	}
	
	

	public void testPersist(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		LicenseDao = new LicenseDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		LicenseDao.setSessionFactory(sessionFactory);
		
		
		
	}
	
	public void testFindAll(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		LicenseDao = new LicenseDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		LicenseDao.setSessionFactory(sessionFactory);
		
		
		
		List<Licenses> list = LicenseDao.findAll();
		tx1.commit();
		Iterator<Licenses> iterator = list.iterator();
		while(iterator.hasNext()){
			Licenses e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
	
	public void testFindById(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		LicenseDao = new LicenseDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		LicenseDao.setSessionFactory(sessionFactory);
		Licenses Licenses = LicenseDao.findById(new Integer(3));
		tx1.commit();
	
		
	}
	
	public void testFindByType(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		LicenseDao = new LicenseDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		LicenseDao.setSessionFactory(sessionFactory);
		
		Licenses e = LicenseDao.findByType("PB","1");
		tx1.commit();
		System.out.println(e);
		
		
	}
}

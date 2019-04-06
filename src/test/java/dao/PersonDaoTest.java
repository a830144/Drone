package dao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.impl.PersonDaoImpl;
import entity.Persons;
import junit.framework.TestCase;

public class PersonDaoTest extends TestCase {
	PersonDao PersonDao;
	protected void setUp() throws Exception {
		PersonDao = new PersonDaoImpl();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDeletebyid(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		PersonDao = new PersonDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		PersonDao.setSessionFactory(sessionFactory);
		
		PersonDao.deleteById(new Integer(10));
		
		tx1.commit();
	}
	
	
	
	public void testUpdate(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		PersonDao = new PersonDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		PersonDao.setSessionFactory(sessionFactory);
		
		Persons e = PersonDao.findById(new Integer(1));
		e.setFax("a83014");
		e.setNationality("ROC");
		e.setIdNumber("B121683618");
		
		PersonDao.update(e);
		
		tx1.commit();
	}
	
	

	public void testPersist(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		PersonDao = new PersonDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		PersonDao.setSessionFactory(sessionFactory);
		
		
		Persons entity = new  Persons();
		entity.setAddress("CALLE QUEB RAOCHO");;
		entity.setDateOfBirth(new Date());
		//entity.setEMail("a830144@gmail.con");
		entity.setIdNumber("b121683618");
		entity.setMobilePhone("095285670");
		PersonDao.persist(entity);
		tx1.commit();
	}
	
	public void testFindAll(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		PersonDao = new PersonDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		PersonDao.setSessionFactory(sessionFactory);
		
		
		
		List<Persons> list = PersonDao.findAll();
		tx1.commit();
		Iterator<Persons> iterator = list.iterator();
		while(iterator.hasNext()){
			Persons e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
	
	public void testFindById(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		PersonDao = new PersonDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		PersonDao.setSessionFactory(sessionFactory);
		Persons Persons = PersonDao.findById(new Integer(3));
		tx1.commit();
		System.out.println(Persons.getAddress());
		
	}
	
	public void testFindByName(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		PersonDao = new PersonDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		PersonDao.setSessionFactory(sessionFactory);
		
		List<Persons> list = PersonDao.findByName("plan");
		tx1.commit();
		Iterator<Persons> iterator = list.iterator();
		while(iterator.hasNext()){
			Persons e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
}

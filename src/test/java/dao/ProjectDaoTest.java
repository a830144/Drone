package dao;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.impl.ProjectDaoImpl;
import entity.Projects;
import junit.framework.TestCase;

public class ProjectDaoTest extends TestCase {
	ProjectDao ProjectDao;
	protected void setUp() throws Exception {
		ProjectDao = new ProjectDaoImpl();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDelete(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		ProjectDao = new ProjectDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		ProjectDao.setSessionFactory(sessionFactory);
		
		ProjectDao.deleteById(new Integer(10));
		
		tx1.commit();
	}
	
	public void testUpdate(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		ProjectDao = new ProjectDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		ProjectDao.setSessionFactory(sessionFactory);
		
		Projects e = ProjectDao.findById(new Integer(1));

		
		ProjectDao.update(e);
		
		tx1.commit();
	}
	
	

	public void testPersist(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		ProjectDao = new ProjectDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		ProjectDao.setSessionFactory(sessionFactory);
		
		
		Projects entity = new  Projects();
entity.setProjectManager("jim");
entity.setName("small oriject");
		ProjectDao.persist(entity);
		tx1.commit();
	}
	
	public void testFindAll(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		ProjectDao = new ProjectDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		ProjectDao.setSessionFactory(sessionFactory);
		
		
		
		List<Projects> list = ProjectDao.findAll();
		tx1.commit();
		Iterator<Projects> iterator = list.iterator();
		while(iterator.hasNext()){
			Projects e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
	
	public void testFindById(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		ProjectDao = new ProjectDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		ProjectDao.setSessionFactory(sessionFactory);
		Projects Projects = ProjectDao.findById(new Integer(3));
		tx1.commit();
		System.out.println(Projects.getName());
		
	}
	
	public void testFindByName(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		ProjectDao = new ProjectDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		ProjectDao.setSessionFactory(sessionFactory);
		
		List<Projects> list = ProjectDao.findByName("plan");
		tx1.commit();
		Iterator<Projects> iterator = list.iterator();
		while(iterator.hasNext()){
			Projects e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
}

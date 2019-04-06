package dao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.impl.EquipmentDaoImpl;
import entity.EquipmentFlow;
import entity.EquipmentPerformance;
import entity.Equipments;
import entity.MaintenanceDetail;
import entity.Maintenances;
import junit.framework.TestCase;

public class EquipmentDaoTest extends TestCase {
	EquipmentDao equipmentDao;
	protected void setUp() throws Exception {
		equipmentDao = new EquipmentDaoImpl();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDeletebyid(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		equipmentDao.deleteById(new Integer(10));
		
		tx1.commit();
	}
	
	public void testUpdate(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		Equipments e = equipmentDao.findById(new Integer(42));
		/*Equipments e = new Equipments();
		e.setEquipmentId(39);*/
		float s = 55.55f;
		e.getEquipmentPerformance().setAntiWind(s);
		e.getEquipmentPerformance().setMaxHeight(s);
		e.getEquipmentPerformance().setMaxDistance(s);
		
		equipmentDao.update(e);
		
		tx1.commit();
	}
	
	

	public void testPersist(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		
		Equipments entity = new  Equipments();
		EquipmentPerformance entity_performance = new EquipmentPerformance();
		EquipmentFlow entity_flow = new EquipmentFlow();
		float f = 10.5f;
		entity.setManufactoryName("DJI");
		entity.setProductName("Inspire 2");
		entity.setConstructionType("B");
		entity.setMaxWeight(f);
		
		entity_performance.setAirTime(f);
		entity_performance.setAntiWind(f);
		entity_performance.setEquipmentLength(f);
		entity_performance.setLoading(f);
		entity_performance.setMaxDistance(f);
		entity.setEquipmentPerformance(entity_performance);
		
		entity_flow.setSendDate(new Date());
		entity_flow.setSendId(1);
		entity.setEquipmentFlow(entity_flow);
		
		equipmentDao.persist(entity);
		
		tx1.commit();
	}
	
	public void testPersistWithMaintenance(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		
		Equipments entity =equipmentDao.findById(new Integer(34));
		Maintenances maintenances = new Maintenances();
		maintenances.setMaintenanceDate(new Date());
		entity.getMaintenanceses().add(maintenances);
		maintenances.setEquipments(entity);
		maintenances.setMaintenanceType("D");
		
		MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
		maintenanceDetail.setDetailType("1");
		maintenanceDetail.setStatus("R");
		maintenanceDetail.setMaintenances(maintenances);
		maintenances.getMaintenanceDetails().add(maintenanceDetail);
		equipmentDao.persist(entity);
		tx1.commit();
	}
	
	public void testFindAll(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		
		
		List<Equipments> list = equipmentDao.findAll();
		tx1.commit();
		Iterator<Equipments> iterator = list.iterator();
		while(iterator.hasNext()){
			Equipments e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
	
	public void testFindById(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		Equipments equipments = equipmentDao.findById(new Integer(46));
		tx1.commit();
		System.out.println(equipments.toString());
		System.out.println(equipments.getEquipmentPerformance().toString());
		System.out.println(equipments.getEquipmentFlow().toString());
		
	}
	
	public void testFindByName(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		List<Equipments> list = equipmentDao.findByName("Inspire 2");
		tx1.commit();
		Iterator<Equipments> iterator = list.iterator();
		while(iterator.hasNext()){
			Equipments e = iterator.next();
			System.out.println(e.toString());
		}
		
	}
	
	public void testDelete(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		equipmentDao = new EquipmentDaoImpl();
		Transaction tx1 =sessionFactory.getCurrentSession().beginTransaction();
		equipmentDao.setSessionFactory(sessionFactory);
		
		Equipments e = new Equipments();
		e.setEquipmentId(38);
		equipmentDao.delete(e);
		tx1.commit();
		
	}
}

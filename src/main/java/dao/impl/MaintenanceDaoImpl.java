package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.MaintenanceDao;
import entity.Equipments;
import entity.Maintenances;

public class MaintenanceDaoImpl implements MaintenanceDao{
	 private SessionFactory sessionFactory;
	 @Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void persist(Maintenances entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Maintenances entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Maintenances entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteAllDetailsByMaintenanceId(Integer id) {
		String hql = "Delete from MaintenanceDetail where maintenances.maintenanceId = :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("searchField", id );
		int rowCount = query.executeUpdate();
		return rowCount;
	}

	@Override
	public Maintenances findById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Maintenances maintenances = session.get(Maintenances.class, id);
        return maintenances; 
	}

	@Override
	public List<Maintenances> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Maintenances> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

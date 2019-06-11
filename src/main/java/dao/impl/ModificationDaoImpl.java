package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.ModificationDao;
import entity.Modifications;

public class ModificationDaoImpl implements ModificationDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persist(Modifications entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Modifications entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Modifications entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteAllDetailsByMaintenanceId(Integer id) {
		String hql = "Delete from ModificationDetail where modifications.modificationId = :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("searchField", id );
		int rowCount = query.executeUpdate();
		return rowCount;
	}

	@Override
	public Modifications findById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Modifications maintenances = session.get(Modifications.class, id);
        return maintenances; 
	}

	@Override
	public List<Modifications> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modifications> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

package dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.EquipmentDao;
import entity.Equipments;
import entity.Missions;

@Repository
public class EquipmentDaoImpl implements EquipmentDao{
 
	@Autowired
	private SessionFactory sessionFactory;
	
    @Override
    public void persist(Equipments entity) {    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Equipments entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.update(entity);
    }
    @Override
    public Equipments findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Equipments Equipments = session.get(Equipments.class, id);
        return Equipments; 
    }
    @Override
    public void delete(Equipments entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @SuppressWarnings("unchecked")
	@Override
    public List<Equipments> findAll() {
    	
		Session session = this.sessionFactory.getCurrentSession();
		List<Equipments> results = (List<Equipments>) session.createQuery("from Equipments E left outer join fetch E.registrations where E.equipmentFlow.state <> 'DELETED'").list();
        return results;
    }
    @Override
    public void deleteAll() {
        List<Equipments> entityList = findAll();
        for (Equipments entity : entityList) {
            delete(entity);
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipments> findByName(String name) {		
		String hql = "FROM Equipments as E left outer join fetch E.registrations where E.productName like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Equipments> query = session.createQuery(hql);
		query.setParameter("searchField", "%" + name + "%");
		List<Equipments> results = query.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipments> findByType(String type) {		
		String hql = "FROM Equipments as E left outer join fetch E.registrations where E.constructionType like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Equipments> query = session.createQuery(hql);
		query.setParameter("searchField", "%" + type + "%");
		List<Equipments> results = query.list();
		return results;
	}
	
	@Override
	public void deleteById(Integer id) {
		Equipments equipments = findById(id);
		delete(equipments);
	}
	@Override
	public List<Equipments> findByMission(Integer aerialPlanId) {
		String hql = "select distinct M.equipments FROM Missions M where M.aerialPlans.aerialPlanId="+aerialPlanId;
		Session session = this.sessionFactory.getCurrentSession();
		Query<Equipments> query = session.createQuery(hql);
		List<Equipments> results = query.list();
		
		return results;
	}

	

}

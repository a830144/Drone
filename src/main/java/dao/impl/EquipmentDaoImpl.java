package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.EquipmentDao;
import entity.Equipments;

@Repository
public class EquipmentDaoImpl implements EquipmentDao{
 
    public EquipmentDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
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
    @Override
    public List<Equipments> findAll() {
    	
		Session session = this.sessionFactory.getCurrentSession();
        List<Equipments> results = (List<Equipments>) session.createQuery("from Equipments e left outer join fetch e.registrations").list();
        return results;
    }
    @Override
    public void deleteAll() {
        List<Equipments> entityList = findAll();
        for (Equipments entity : entityList) {
            delete(entity);
        }
    }

	@Override
	public List<Equipments> findByName(String name) {
		
		
		String hql = "FROM Equipments as E left outer join fetch E.registrations where E.productName like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Equipments> query = session.createQuery(hql);
		query.setParameter("searchField", "%" + name + "%");

		List<Equipments> results = query.list();
		return results;
	}
	@Override
	public void deleteById(Integer id) {
		Equipments equipments = findById(id);
		delete(equipments);
	}

	

}

package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.TrainingDao;
import entity.Trainings;

public class TrainingDaoImpl implements TrainingDao{
 
    public TrainingDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void persist(Trainings entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Trainings entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Trainings findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Trainings Trainings = session.get(Trainings.class, id);
        return Trainings; 
    }
    @Override
    public void delete(Trainings entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Trainings> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Trainings> Trainingss = (List<Trainings>) session.createQuery("from Trainings").list();
        return Trainingss;
    }
    @Override
    public void deleteAll() {
        List<Trainings> entityList = findAll();
        for (Trainings entity : entityList) {
            delete(entity);
        }
    }

	@Override
	public Trainings findByName(String name) {
		String hql = "FROM Trainings as E where E.name='" + name +"'";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Trainings> query = session.createQuery(hql); 
		//query.setParameter("searchField","%"+type+"%");
		
		List<Trainings> results = query.list();
		return results.get(0);
	}
	@Override
	public void deleteById(Integer id) {
		Trainings Trainings = findById(id);
		delete(Trainings);
	}
	@Override
	public List<Trainings> findByNameWildcard(String name) {
		String hql = "FROM Trainings as E where E.name like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Trainings> query = session.createQuery(hql); 
		query.setParameter("searchField","%"+name+"%");
		
		List<Trainings> results = query.list();
		return results;
	}

	

}

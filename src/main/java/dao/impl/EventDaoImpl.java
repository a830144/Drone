package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.EventDao;
import entity.Events;
import entity.Participations;

public class EventDaoImpl implements EventDao{
 
    public EventDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void persist(Events entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
	public void persistParticipation(Participations entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
		
	}
    @Override
    public void update(Events entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Events findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Events Events = session.get(Events.class, id);
        return Events; 
    }
    @Override
    public void delete(Events entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Events> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Events> Eventss = (List<Events>) session.createQuery("from Events").list();
        return Eventss;
    }
    @Override
    public void deleteAll() {
        List<Events> entityList = findAll();
        for (Events entity : entityList) {
            delete(entity);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
	public Events findByName(String name) {
		String hql = "FROM Events as E where E.name='" + name +"'";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Events> query = session.createQuery(hql); 
		//query.setParameter("searchField","%"+type+"%");
		
		List<Events> results = query.list();
		return results.get(0);
	}
	@Override
	public void deleteById(Integer id) {
		Events Events = findById(id);
		delete(Events);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> findByNameWildcard(String name) {
		String hql = "FROM Events as E where E.name like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Events> query = session.createQuery(hql); 
		query.setParameter("searchField","%"+name+"%");
		
		List<Events> results = query.list();
		return results;
	}
	

	

}

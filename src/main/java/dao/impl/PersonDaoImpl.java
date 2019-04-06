package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.PersonDao;
import entity.Persons;

public class PersonDaoImpl implements PersonDao{
 
    public PersonDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void persist(Persons entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Persons entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Persons findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Persons Persons = session.get(Persons.class, id);
        return Persons; 
    }
    @Override
    public void delete(Persons entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Persons> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Persons> Personss = (List<Persons>) session.createQuery("from Persons").list();
        return Personss;
    }
    @Override
    public void deleteAll() {
        List<Persons> entityList = findAll();
        for (Persons entity : entityList) {
            delete(entity);
        }
    }

	@Override
	public List<Persons> findByName(String name) {
		String hql = "FROM Persons as E where E.name like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Persons> query = session.createQuery(hql); 
		query.setParameter("searchField","%"+name+"%");
		
		List<Persons> results = query.list();
		return results;
	}
	@Override
	public void deleteById(Integer id) {
		Persons Persons = findById(id);
		delete(Persons);
	}

	

}

package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.ProjectDao;
import entity.Projects;

public class ProjectDaoImpl implements ProjectDao{
 
    public ProjectDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void persist(Projects entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Projects entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Projects findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Projects Projects = session.get(Projects.class, id);
        return Projects; 
    }
    @Override
    public void delete(Projects entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Projects> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Projects> Projectss = (List<Projects>) session.createQuery("from Projects").list();
        return Projectss;
    }
    @Override
    public void deleteAll() {
        List<Projects> entityList = findAll();
        for (Projects entity : entityList) {
            delete(entity);
        }
    }

	@Override
	public List<Projects> findByName(String name) {
		String hql = "FROM Projects as E where E.name like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Projects> query = session.createQuery(hql); 
		query.setParameter("searchField","%"+name+"%");
		
		List<Projects> results = query.list();
		return results;
	}
	@Override
	public void deleteById(Integer id) {
		Projects Projects = findById(id);
		delete(Projects);
	}

	

}

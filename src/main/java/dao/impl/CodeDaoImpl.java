package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.CodeDao;
import entity.Code;
import entity.Events;

public class CodeDaoImpl implements CodeDao{
 
    public CodeDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void persist(Code entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Code entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Code findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Code Code = session.get(Code.class, id);
        return Code; 
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Code> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Code> code = (List<Code>) session.createQuery("from Code").list();
        return code;
    }

	@Override
	public List<Code> findByType(String type) {
		String hql = "FROM Code as c where c.codeType like :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Code> query = session.createQuery(hql); 
		query.setParameter("searchField","%"+type+"%");
		
		List<Code> results = query.list();
		return results;
	}

	

}

package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.LicenseDao;
import entity.Licenses;

public class LicenseDaoImpl implements LicenseDao{
 
    public LicenseDaoImpl() {
    }      
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void persist(Licenses entity) {
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public void update(Licenses entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
    }
    @Override
    public Licenses findById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Licenses Licenses = session.get(Licenses.class, id);
        return Licenses; 
    }
    @Override
    public void delete(Licenses entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Licenses> findAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Licenses> Licensess = (List<Licenses>) session.createQuery("from Licenses").list();
        return Licensess;
    }
    @Override
    public void deleteAll() {
        List<Licenses> entityList = findAll();
        for (Licenses entity : entityList) {
            delete(entity);
        }
    }

	@Override
	public Licenses findByType(String type,String subType) {
		String hql = "FROM Licenses as E where E.type='" + type +"' and E.subType='"+subType+"'";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Licenses> query = session.createQuery(hql); 
		//query.setParameter("searchField","%"+type+"%");
		
		List<Licenses> results = query.list();
		return results.get(0);
	}
	@Override
	public void deleteById(Integer id) {
		Licenses Licenses = findById(id);
		delete(Licenses);
	}

	

}

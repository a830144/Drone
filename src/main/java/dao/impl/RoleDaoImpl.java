package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.RoleDao;
import entity.Permissions;

@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
    @SuppressWarnings("unchecked")
    public List<Permissions> findAllPermissions() {
    	Session session = this.sessionFactory.getCurrentSession();
        List<Permissions> Permissions = (List<Permissions>) session.createQuery("from Permissions").list();
        return Permissions;
    }


}

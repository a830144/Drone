package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.RoleDao;
import entity.Licenses;
import entity.Permissions;
import entity.Persons;
import entity.Roles;

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

	@Override
	public void persist(Roles entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);		
	}

	@Override
	public void update(Roles entity) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}
	
	@Override
	public int deleteAllRolesPermissionsByRoleId(Integer id) {
		String hql = "Delete from RolesPermissions where roles.roleId = :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("searchField", id );
		int rowCount = query.executeUpdate();
		return rowCount;
	}

	@Override
	public List<Roles> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Roles> roles = (List<Roles>) session.createQuery("from Roles as E").list();
        for(int i=0;i<roles.size();i++){
        	Roles entity = roles.get(i);
        	session.detach(entity);
        }
        return roles;
	}

	@Override
	public Roles findById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Roles roles = session.get(Roles.class, id);
        return roles;
	}
	
	@Override
    public Permissions findPermissionById(Integer id) {
    	Session session = this.sessionFactory.getCurrentSession();
    	Permissions permissions = session.get(Permissions.class, id);
        return permissions; 
    }

	@Override
	public void delete(Roles entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
        Roles roles = session.get(Roles.class, id);
    	session.delete(roles);
	}

	@Override
	public List<Object[]> findPermissionsTruthTableByRoleId(Integer id) {
		String sql = 
				"select p.permission_id,p.permission_name,"
				+"CASE"
				+"	WHEN temp.permission_id is null THEN 'N'"
				+"  ELSE 'Y'"
				+"END as truth"
				+ " FROM"
				+ " (select rp.permission_id "
				+ " 	from roles r inner join roles_permissions rp"
				+ " 	on r.role_id = rp.role_id"
				+ " 	where r.role_id= :id) temp"
				+ " right outer join permissions p"
				+ " on temp.permission_id = p.permission_id  "
				+ " order by p.permission_id ";				
				
		Session session = this.sessionFactory.getCurrentSession();
		NativeQuery<Object[]> query = session.createNativeQuery(sql);
		query.setParameter("id", id);
		List<Object[]> results = query.list();
		return results;
	}

}

package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.UserDao;
import entity.Roles;
import entity.Users;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object[]> findByIdAndPassword(Integer id, String password) {
		String sql = 
				"SELECT DISTINCT f.Function_Name, GROUP_CONCAT(OP.TYPE)"
				+ " FROM"
				+ " users u"
				+ " 	inner join users_roles ur on u.user_id = ur.user_id"
				+ " 	inner join roles r on ur.role_id = r.role_id"
				+ " 	inner join roles_functions rf on r.role_id = rf.role_id"
				+ "     inner join functions f on rf.function_id = f.function_id"
				+ " 	inner join (Select rf2.Roles_Functions_ID,o.type "
				+ " 		from roles_functions rf2 "				
				+ " 		inner join operations o on rf2.Roles_Functions_ID = o.Roles_Functions_ID) op on rf.Roles_Functions_ID = op.Roles_Functions_ID"
				+ " WHERE f.type = 'Tree'"
				+ " AND u.user_id =:id"
				+ " AND u.password =:password"
				+ " GROUP BY f.function_name";
		Session session = this.sessionFactory.getCurrentSession();
		NativeQuery<Object[]> query = session.createNativeQuery(sql);
		query.setParameter("id", id);
		query.setParameter("password", password);
		List<Object[]> results = query.list();
		return results;
	}

	@Override
	public List<Users> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Users> users = (List<Users>) session.createQuery("from Users").list();
        for(int i=0;i<users.size();i++){
        	Users entity = users.get(i);
        	session.detach(entity);
        }
        return users;
	}
	
	@Override
	public Users findById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Users users = session.get(Users.class, id);
        return users;
	}
	
	@Override
	public void persist(Users entity) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);		
	}
	
	@Override
	public void update(Users entity) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
    	session.save(entity);
	}

	@Override
	public void deleteById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
        Users users = session.get(Users.class, id);
    	session.delete(users);
	}

	@Override
	public List<Object[]> findRolesTruthTableByUserId(Integer id) {
		String sql = 
				"select r.role_id,r.role_name,"
				+"CASE"
				+"	WHEN temp.role_id is null THEN 'N'"
				+"  ELSE 'Y'"
				+"END as truth"
				+ " FROM"
				+ " (select ur.role_id "
				+ " 	from users u inner join users_roles ur"
				+ " 	on u.user_id = ur.user_id"
				+ " 	where u.user_id= :id) temp"
				+ " right outer join roles r"
				+ " on temp.role_id = r.role_id  "
				+ " order by r.role_id ";				
				
		Session session = this.sessionFactory.getCurrentSession();
		NativeQuery<Object[]> query = session.createNativeQuery(sql);
		query.setParameter("id", id);
		List<Object[]> results = query.list();
		return results;
	}

	@Override
	public int deleteAllUsersRolesByUserId(Integer id) {
		String hql = "Delete from UsersRoles where users.userId = :searchField";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("searchField", id );
		int rowCount = query.executeUpdate();
		return rowCount;
	}

	
	
}

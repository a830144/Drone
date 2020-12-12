package dao;

import java.util.List;

import entity.Roles;
import entity.Users;

public interface UserDao {
	
	public void persist(Users entity);
	
	public void update(Users entity) ;
	
	public List<Users> findAll();
	
	public Users findById(Integer id);
    
    public List<Object[]> findByIdAndPassword(Integer id,String password);
    
    public void deleteById(Integer id);
    
    public List<Object[]> findRolesTruthTableByUserId(Integer id);
	
	public int deleteAllUsersRolesByUserId(Integer id);
    

}

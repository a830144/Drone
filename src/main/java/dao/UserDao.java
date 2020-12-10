package dao;

import java.util.List;

import entity.Users;

public interface UserDao {
	
	public List<Users> findAll();
    
    public List<Object[]> findByIdAndPassword(Integer id,String password);

}

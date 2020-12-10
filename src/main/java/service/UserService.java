package service;

import com.google.gson.JsonArray;

public interface UserService {
	public void persist(String jsonString);

	public void updateUser(String jsonString);
	
	public String queryUserById(Integer id);	
	
	public JsonArray queryUsers();
	
	public void delete(Integer id);
	
	

	
}

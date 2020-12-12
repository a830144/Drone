package service;

import com.google.gson.JsonArray;

public interface RoleService {
	public void persist(String jsonString);

	public void updateRole(String jsonString);
	
	public String queryRoleById(Integer id);
	
	public String queryRoleWithPermissionsById(Integer id);
	
	public JsonArray queryRoles();
	
	public void delete(Integer id);
	
	

	
}

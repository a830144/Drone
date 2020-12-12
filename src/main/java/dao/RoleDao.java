package dao;

import java.util.List;

import entity.Permissions;
import entity.Roles;

public interface RoleDao {
	public List<Permissions> findAllPermissions();

	public void persist(Roles entity);

	public void update(Roles entity);
	
	public List<Roles> findAll();

	public Roles findById(Integer id);
	
	public Permissions findPermissionById(Integer id);

	public void delete(Roles entity);

	public void deleteById(Integer id);
	
	public List<Object[]> findPermissionsTruthTableByRoleId(Integer id);
	
	public int deleteAllRolesPermissionsByRoleId(Integer id);

}

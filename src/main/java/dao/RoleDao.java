package dao;

import java.util.List;

import entity.Permissions;

public interface RoleDao {
	public List<Permissions> findAllPermissions();

}

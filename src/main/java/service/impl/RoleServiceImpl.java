package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.RoleDao;
import entity.Permissions;
import entity.Roles;
import entity.RolesPermissions;
import service.RoleService;
import vo.Permission;
import vo.Role;


@Service
public class RoleServiceImpl implements RoleService {

	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	
	@Autowired
	private RoleDao roleDao;
	
	
	
	public RoleServiceImpl() {
	}

	@Override
	public void persist(String jsonString) {
		Role vo = gson.fromJson(jsonString, Role.class);
		Roles entity_roles = new Roles();
		
		try {
			BeanUtils.copyProperties(entity_roles, vo);
			Iterator<Integer> iterator = vo.getPermissionsSet().iterator();
			while(iterator.hasNext()){
				int permissionId = Integer.parseInt(iterator.next().toString());
				RolesPermissions entity_rolePermissions = new RolesPermissions();
				//BeanUtils.copyProperties(entity_rolePermissions, vo);
				Permissions entity_permissions = roleDao.findPermissionById(permissionId);
				
				entity_rolePermissions.setRoles(entity_roles);
				entity_rolePermissions.setPermissions(entity_permissions);
				entity_roles.getRolesPermissionses().add(entity_rolePermissions);
				entity_permissions.getRolesPermissionses().add(entity_rolePermissions);
				System.out.println("pid::"+entity_rolePermissions.getPermissions().getPermissionId() +
						";rid::"+entity_rolePermissions.getRoles().getRoleId());
				
				
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		roleDao.persist(entity_roles);
	}

	@Override
	public void updateRole(String jsonString) {
		Role vo = gson.fromJson(jsonString, Role.class);
		Roles entity_roles = roleDao.findById(vo.getRoleId());
		try {
			BeanUtils.copyProperties(entity_roles, vo);
			Iterator<Integer> iterator = vo.getPermissionsSet().iterator();
			roleDao.deleteAllRolesPermissionsByRoleId(vo.getRoleId());
			while(iterator.hasNext()){
				int permissionId = Integer.parseInt(iterator.next().toString());
				RolesPermissions entity_rolePermissions = new RolesPermissions();
				//BeanUtils.copyProperties(entity_rolePermissions, vo);
				Permissions entity_permissions = roleDao.findPermissionById(permissionId);
				
				entity_rolePermissions.setRoles(entity_roles);
				entity_rolePermissions.setPermissions(entity_permissions);
				entity_roles.getRolesPermissionses().add(entity_rolePermissions);
				entity_permissions.getRolesPermissionses().add(entity_rolePermissions);		
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		roleDao.update(entity_roles);
	}

	@Override
	public String queryRoleById(Integer id) {
		Roles entity_roles = roleDao.findById(id);
		System.out.println("id::"+id +";;;"+entity_roles.getRoleName());
		Role vo = new Role();
		vo.setRoleId(entity_roles.getRoleId());
		vo.setRoleName(entity_roles.getRoleName());
		Iterator iterator = entity_roles.getRolesPermissionses().iterator();
		while(iterator.hasNext()){
			RolesPermissions rolesPermissions = (RolesPermissions)iterator.next();
			Permissions permissions = rolesPermissions.getPermissions();
			Role.OwnPermission ownPermission = vo.new OwnPermission();
			ownPermission.setPermissionId(permissions.getPermissionId());
			ownPermission.setPermissionName(permissions.getPermissionName());
			vo.getOwnPermissionSet().add(ownPermission);
		}
		
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}



	@Override
	public JsonArray queryRoles() {
		List<Roles> rolesList;
		rolesList = roleDao.findAll();
		Iterator<Roles> iterator = rolesList.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Roles entity_roles = (Roles) iterator.next();
			Role vo = new Role();
			try {
				BeanUtils.copyProperties(vo, entity_roles);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			JSONObject jsonObj = new JSONObject(vo);
			jsonArray.add(jsonObj.toString());
		}
		

		return jsonArray;
	}



	
	
	@Override
	public void delete(Integer id){
		roleDao.deleteById(id);
	}

	@Override
	public String queryRoleWithPermissionsById(Integer id) {
		// TODO Auto-generated method stub
		List<Object[]> resultlist= roleDao.findPermissionsTruthTableByRoleId(id);
		
		List<String[]> ownPermissionTruth = new LinkedList<String[]>();
		for(Object[] result : resultlist) {
			String[] temp = new String[3];
			
			Permission permission = new Permission();
			Integer permission_id = (Integer)result[0];
			String permission_name = (String) result[1];
			String truth = (String) result[2];
			temp[0] = permission_id.toString();
			temp[1] = permission_name;
			temp[2] = truth;
			
			ownPermissionTruth.add(temp);
		}
		
		return  gson.toJson(ownPermissionTruth);

	}	
		
	
	



}

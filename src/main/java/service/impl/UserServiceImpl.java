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
import dao.UserDao;
import entity.Permissions;
import entity.Roles;
import entity.RolesPermissions;
import entity.Users;
import entity.UsersRoles;
import service.UserService;
import vo.Permission;
import vo.Role;
import vo.User;
import vo.Role.OwnPermission;


@Service
public class UserServiceImpl implements UserService {

	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	
	
	public UserServiceImpl() {
	}

	@Override
	public void persist(String jsonString) {
		User vo = gson.fromJson(jsonString, User.class);
		Users entity_users = new Users();
		
		try {
			BeanUtils.copyProperties(entity_users, vo);
			Iterator<Integer> iterator = vo.getRolesSet().iterator();
			while(iterator.hasNext()){
				int roleId = Integer.parseInt(iterator.next().toString());
				UsersRoles entity_usersRoles = new UsersRoles();
				Roles entity_roles = roleDao.findById(roleId);
				
				entity_usersRoles.setUsers(entity_users);
				entity_usersRoles.setRoles(entity_roles);
				
				entity_users.getUsersRoleses().add(entity_usersRoles);
				entity_roles.getUsersRoleses().add(entity_usersRoles);
								
				
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
		userDao.persist(entity_users);
	}

	@Override
	public void updateUser(String jsonString) {
		User vo = gson.fromJson(jsonString, User.class);
		Users entity_users = userDao.findById(vo.getUserId());
		try {
			BeanUtils.copyProperties(entity_users, vo);
			Iterator<Integer> iterator = vo.getRolesSet().iterator();
			userDao.deleteAllUsersRolesByUserId(vo.getUserId());
			while(iterator.hasNext()){
				int roleId = Integer.parseInt(iterator.next().toString());
				UsersRoles entity_usersRoles = new UsersRoles();
				//BeanUtils.copyProperties(entity_rolePermissions, vo);
				Roles entity_roles = roleDao.findById(roleId);
				
				entity_usersRoles.setRoles(entity_roles);
				entity_usersRoles.setUsers(entity_users);
				entity_roles.getUsersRoleses().add(entity_usersRoles);
				entity_users.getUsersRoleses().add(entity_usersRoles);		
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDao.update(entity_users);
	}

	@Override
	public String queryUserById(Integer id) {
		Users entity_users = userDao.findById(id);
		User vo = new User();
		vo.setUserId(entity_users.getUserId());
		vo.setUserName(entity_users.getUserName());
		vo.setEMail(entity_users.getEMail());
		vo.setPhone(entity_users.getPhone());
		Iterator iterator = entity_users.getUsersRoleses().iterator();
		while(iterator.hasNext()){
			UsersRoles usersRoles = (UsersRoles)iterator.next();
			Roles roles = usersRoles.getRoles();
			User.OwnRole ownRole = vo.new OwnRole();
			ownRole.setRoleId(roles.getRoleId());
			ownRole.setRoleName(roles.getRoleName());
			vo.getOwnRoleSet().add(ownRole);
		}
		
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}



	@Override
	public JsonArray queryUsers() {
		List<Users> usersList;
		usersList = userDao.findAll();
		Iterator<Users> iterator = usersList.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Users entity_users = (Users) iterator.next();
			User vo = new User();
			try {
				BeanUtils.copyProperties(vo, entity_users);
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
		userDao.deleteById(id);
	}

	@Override
	public String queryUserWithRolesById(Integer id) {
		// TODO Auto-generated method stub
				List<Object[]> resultlist= userDao.findRolesTruthTableByUserId(id);
				
				List<String[]> ownRoleTruth = new LinkedList<String[]>();
				for(Object[] result : resultlist) {
					String[] temp = new String[3];
				
					Integer role_id = (Integer)result[0];
					String role_name = (String) result[1];
					String truth = (String) result[2];
					temp[0] = role_id.toString();
					temp[1] = role_name;
					temp[2] = truth;
					
					ownRoleTruth.add(temp);
				}
				
				return  gson.toJson(ownRoleTruth);
	}	
		
	
	



}

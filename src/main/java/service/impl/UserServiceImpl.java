package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.UserDao;
import entity.Users;
import service.UserService;
import vo.User;


@Service
public class UserServiceImpl implements UserService {

	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	
	@Autowired
	private UserDao userDao;
	
	
	
	public UserServiceImpl() {
	}

	@Override
	public void persist(String jsonString) {
		
	}

	@Override
	public void updateUser(String jsonString) {
		
	}

	@Override
	public String queryUserById(Integer id) {
		return null;
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
		
	}	
		
	
	



}

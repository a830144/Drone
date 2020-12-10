package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/user/AddUserProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addUser(String data) {
		System.out.println("AddUser");
		userService.persist(data);		
	}
	
	@RequestMapping(value="/user/DeleteUserProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteUser(Integer id) {
		System.out.println("DeleteUser");
		userService.delete(id);	
	}
	
	@RequestMapping(value="/user/UpdateUserProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateUser(String data) {
		userService.updateUser(data);		
	}
	
	@RequestMapping(value="/user/ViewUserProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewUser(String id) {
		String jsonString = userService.queryUserById(new Integer(Integer.parseInt(id)));	
		
		return jsonString;
	}
	
	/**
	 *
	 * @return
	 */
	@RequestMapping(value="/user/QueryUserProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryUser() {		
		JsonArray jsonArray = userService.queryUsers();
		String jsonString =jsonArray.toString();	
		return jsonString;
	}
	
}

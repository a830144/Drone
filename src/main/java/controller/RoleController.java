package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import service.RoleService;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value="/role/AddRoleProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addRole(String data) {
		System.out.println("AddRole");
		roleService.persist(data);		
	}
	
	@RequestMapping(value="/role/DeleteRoleProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteRole(Integer id) {
		System.out.println("DeleteRole");
		roleService.delete(id);	
	}
	
	@RequestMapping(value="/role/UpdateRoleProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateRole(String data) {
		roleService.updateRole(data);		
	}
	
	@RequestMapping(value="/role/ViewRoleProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewRole(String id) {
		String jsonString = roleService.queryRoleById(new Integer(Integer.parseInt(id)));	
		
		return jsonString;
	}
	
	/**
	 *
	 * @return
	 */
	@RequestMapping(value="/role/QueryRoleProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryRole() {		
		JsonArray jsonArray = roleService.queryRoles();
		String jsonString =jsonArray.toString();	
		return jsonString;
	}
	
}

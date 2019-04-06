package controller;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Projects;
import service.OperationService;

@Controller
public class OperationController {
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping(value="/operation/AddProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addProject(String data) {
		System.out.println("AddPerson");
		operationService.persistProject(data);		
	}
	
	
	
	@RequestMapping(value="/operation/UpdateProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateProject(String data) {
		operationService.updateProject(data);		
	}
	
	@RequestMapping(value="/operation/ViewProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewProject(String id) {
		String jsonString = operationService.queryProjectById(new Integer(Integer.parseInt(id)));	
		return jsonString;
	}
	
	
	@RequestMapping(value="/operation/QueryProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryProject(String name) {
		List<Projects> list = operationService.queryProjects(name);
		JSONArray jsonArray = new JSONArray();
		String jsonString = "";
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Projects projects = (Projects) iterator.next();
			JSONObject orderedJson = new JSONObject(projects);
			jsonArray.put(orderedJson);
		}

		if (jsonArray.length() > 0) {
			jsonString = jsonArray.toString();
		}

		
		return jsonString;
	}
}

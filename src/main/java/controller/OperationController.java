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

import com.google.gson.JsonArray;

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
	
	@RequestMapping(value="/operation/AddAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addAerialPlan(String data) {
		System.out.println("AddAerial Plan");
		operationService.persistAerialPlan(data);		
	}
	
	@RequestMapping(value="/operation/UpdateAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateAerialPlan(String data) {
		System.out.println("UpdateAerial Plan");
		operationService.updateAerialPlan(data);		
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
		System.out.println(jsonString);
		return jsonString;
	}
	
	
	@RequestMapping(value="/operation/QueryProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryProject(String name) {
		List<Projects> list = operationService.queryProjects(name);
		JSONArray jsonArray = new JSONArray();
		String jsonString = "";
		Iterator<Projects> iterator = list.iterator();
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
	
	@RequestMapping(value="/operation/QueryAerailPlansProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryAerialPlans(String projectId) {
		JsonArray jsonArray = operationService.queryAerialPlanByProjectId(new Integer(Integer.parseInt(projectId)));
		String jsonString =jsonArray.toString();
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	// view aerialPlan by aerialPlanId
	@RequestMapping(value = "/operation/ViewAerialPlanByAerialPlanId", method = {RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewAerialPlanByAerialPlanId(String id) {
		String jsonString = operationService.queryAerialPlanByAerialPlanId(new Integer(Integer.parseInt(id)));
		System.out.println("viewAerialPlanByAerialPlanId::" + jsonString);
		return jsonString;
	}
}

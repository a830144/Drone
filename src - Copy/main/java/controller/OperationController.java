package controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import entity.AerialPlans;
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
	
	@RequestMapping(value="/project/CheckProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkProject(Integer targetId) {
		System.out.println("CheckProject");
		operationService.check(targetId);		
	}
	
	@RequestMapping(value="/project/ApproveProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveProject(Integer targetId) {
		System.out.println("ApproveProject");
		operationService.approve(targetId);	
	}
	
	@RequestMapping(value="/project/RejectProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectProject(Integer targetId) {
		System.out.println("RejectProject");
		operationService.reject(targetId);	
	}
	
	@RequestMapping(value="/project/DeleteProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteProject(Integer id) {
		System.out.println("DeleteProject");
		operationService.delete(id);	
	}
	
	@RequestMapping(value="/operation/AddAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addAerialPlan(String data) {
		System.out.println("AddAerial Plan");
		operationService.persistAerialPlan(data);		
	}
	
	@RequestMapping(value="/aerialPlan/CheckAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkAerialPlan(Integer targetId) {
		System.out.println("CheckAerialPlan");
		operationService.checkAerialPlan(targetId);		
	}
	
	@RequestMapping(value="/aerialPlan/ApproveAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveAerialPlan(Integer targetId) {
		System.out.println("ApproveAerialPlan");
		operationService.approveAerialPlan(targetId);	
	}
	
	@RequestMapping(value="/aerialPlan/RejectAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectAerialPlan(Integer targetId) {
		System.out.println("RejectAerialPlan");
		operationService.rejectAerialPlan(targetId);	
	}
	
	@RequestMapping(value="/aerialPlan/DeleteAerialPlanProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteAerialPlan(Integer id) {
		System.out.println("DeleteAerialPlan");
		operationService.deleteAerialPlan(id);	
	}
	
	@RequestMapping(value="/operation/AddAerialActivityProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addAerialActivity(String data) {
		System.out.println("AddAerial Activity");
		operationService.persistAerialActivity(data);		
	}
	
	@RequestMapping(value="/aerialActivity/CheckAerialActivityProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkAerialActivity(Integer targetId) {
		System.out.println("CheckAerialActivity");
		operationService.checkAerialActivity(targetId);		
	}
	
	@RequestMapping(value="/aerialActivity/ApproveAerialActivityProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveAerialActivity(Integer targetId) {
		System.out.println("ApproveAerialActivity");
		operationService.approveAerialActivity(targetId);	
	}
	
	@RequestMapping(value="/aerialActivity/RejectAerialActivityProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectAerialActivity(Integer targetId) {
		System.out.println("RejectAerialActivity");
		operationService.rejectAerialActivity(targetId);	
	}
	
	@RequestMapping(value="/aerialActivity/DeleteAerialActivityProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteAerialActivity(Integer id) {
		System.out.println("DeleteAerialActivity");
		operationService.deleteAerialActivity(id);	
	}
	
	@RequestMapping(value="/operation/UpdateAerialActivityProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateAerialActivity(String data) {
		System.out.println("UpdateAerial Activity");
		operationService.updateAerialActivity(data);		
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
		return jsonString;
	}
	
	
	@RequestMapping(value="/operation/QueryProjectProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryProject(String name) {
		JsonArray jsonArray = operationService.queryProjects(name);
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/operation/QueryAerailPlansProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryAerialPlans(String projectId) {
		JsonArray jsonArray = operationService.queryAerialPlanByProjectId(new Integer(Integer.parseInt(projectId)));
		String jsonString =jsonArray.toString();
		//System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	/**
	 * This function is for UI to generate the tag in drop down list
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/operation/QueryAerailPlansIDsByProjectId" , method = {RequestMethod.POST})
	@ResponseBody
	public String showQueryAerialPlansIDsByProjectId(String projectId) {
		System.out.println("showQueryAerialPlansIDsByProjectId projectId::"+projectId);
		List<AerialPlans> list = operationService.queryAerialPlans(projectId);
		Iterator<AerialPlans> iterator = list.iterator();
		String tag ="<option value=''></option>";
		while (iterator.hasNext()) {
			AerialPlans entity_aerialPlans = (AerialPlans) iterator.next();
			tag += "<option value='"+entity_aerialPlans.getAerialPlanId()+"'>"+entity_aerialPlans.getAerialPlanId()+"</option>";
		}	
		System.out.println("tag::"+tag);
		return tag;
	}
	
	/** 
	 * view aerialPlan by aerialPlanId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/operation/ViewAerialPlanByAerialPlanId", method = {RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewAerialPlanByAerialPlanId(String id) {
		String jsonString = operationService.queryAerialPlanByAerialPlanId(new Integer(Integer.parseInt(id)));
		System.out.println("viewAerialPlanByAerialPlanId::" + jsonString);
		return jsonString;
	}
	
	/**
	 * This function use project Id to find all mapping aerial activity data
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/operation/QueryAerailActivitiesProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryAerailActivities(String projectId) {
		JsonArray jsonArray = operationService.queryAerialActivityByProjectId(new Integer(Integer.parseInt(projectId)));
		String jsonString =jsonArray.toString();
		System.out.println("showQueryAerailActivities jsonString::"+jsonString);
		return jsonString;
	}
	
	/** 
	 * view aerialActivity by aerialActivityId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/operation/ViewAerialActivityByAerialActivityId", method = {RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewAerialActivityByAerialActivityId(String id) {
		String jsonString = operationService.queryAerialActivityByAerialActivityId(new Integer(Integer.parseInt(id)));
		System.out.println("ViewAerialActivityByAerialActivityId::" + jsonString);
		return jsonString;
	}
}

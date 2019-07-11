package service;

import java.util.List;

import com.google.gson.JsonArray;

import entity.AerialPlans;
import entity.Operations;
import entity.Projects;

public interface OperationService {
	
	public void persistProject(String jsonString);

	public void updateProject(String jsonString);
	
	public void deleteProject(Operations entity);
	
	public void deleteAll();

	public String queryProjectById(Integer id);	
	
	public List<Projects> queryProjects(String ename);
	
	public List<AerialPlans> queryAerialPlans(String projectId);
	
	public void persistAerialPlan(String jsonString);
	
	public void updateAerialPlan(String jsonString);
	
	public void persistAerialActivity(String jsonString);
	
	public JsonArray queryAerialPlanByProjectId(Integer projectId);
	
	public String queryAerialPlanByAerialPlanId(Integer aerialPlanId);
	
	public JsonArray queryAerialActivityByProjectId(Integer projectId);
	
	public String queryAerialActivityByAerialActivityId(Integer aerialActivityId);
	
}

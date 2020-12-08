package service;

import java.util.List;

import com.google.gson.JsonArray;

import entity.AerialPlans;
import entity.Operations;

public interface OperationService {
	
	public void persistProject(String jsonString);

	public void updateProject(String jsonString);
	
	public void deleteProject(Operations entity);
	
	public void deleteAll();

	public String queryProjectById(Integer id);	
	
	public JsonArray queryProjects(String ename);
	
	public List<AerialPlans> queryAerialPlans(String projectId);
	
	public void persistAerialPlan(String jsonString);
	
	public void updateAerialPlan(String jsonString);
	
	public void updateAerialActivity(String jsonString);
	
	public void persistAerialActivity(String jsonString);
	
	public JsonArray queryAerialPlanByProjectId(Integer projectId);
	
	public String queryAerialPlanByAerialPlanId(Integer aerialPlanId);
	
	public JsonArray queryAerialActivityByProjectId(Integer projectId);
	
	public String queryAerialActivityByAerialActivityId(Integer aerialActivityId);
	
	public void check(Integer id);
	
	public void approve(Integer id);
	
	public void reject(Integer id);
	
	public void delete(Integer id);
	
	public void checkAerialActivity(Integer id);
	
	public void approveAerialActivity(Integer id);
	
	public void rejectAerialActivity(Integer id);
	
	public void deleteAerialActivity(Integer id);
	
	public void checkAerialPlan(Integer id);
	
	public void approveAerialPlan(Integer id);
	
	public void rejectAerialPlan(Integer id);
	
	public void deleteAerialPlan(Integer id);
	
}

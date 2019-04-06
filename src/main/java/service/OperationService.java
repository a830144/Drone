package service;

import java.util.List;

import entity.Operations;
import entity.Projects;

public interface OperationService {
	public void persistProject(String jsonString);

	public void updateProject(String jsonString);
	
	public void deleteProject(Operations entity);
	
	public void deleteAll();

	public String queryProjectById(Integer id);	
	
	public List<Projects> queryProjects(String ename);
	
}

package service;

import entity.Operations;

public interface OperationService {
	public void persistProject(String jsonString);

	public void modifyProject(String jsonString);
	
	public void deleteProject(Operations entity);
	
	public void deleteAll();

	public String queryProjectById(Integer id);	
	
	public String queryProjects(String ename);
	
}

package service;

import java.util.List;

import com.google.gson.JsonArray;

import entity.Persons;

public interface PersonService {
	public void persist(String jsonString);

	public void updatePerson(String jsonString);
	
	public void licenseInPerson(String jsonString);
	
	public void trainingInPerson(String jsonString);
	
	public void eventInPerson(String jsonString);
	
	public void delete(Persons entity);
	
	public void deleteAll();

	public String queryPersonById(Integer id);	
	
	public List<Persons> queryPersons(String ename);
	
	public JsonArray queryLicensesById(Integer id);
	
	public JsonArray queryTrainingsById(Integer id);
	
	public JsonArray queryEventsById(Integer id);
	
	public String queryLicenseInfo(Integer personId,Integer licenseId);
	
	public String queryTrainingInfo(Integer personId,Integer trainingId);
	
	public String queryEventInfo(Integer personId,Integer eventId);
	
}

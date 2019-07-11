package service;

import java.util.List;

import com.google.gson.JsonArray;

import entity.Persons;

public interface PersonService {
	public void persist(String jsonString);

	public void updatePerson(String jsonString);
	
	public void licenseInPerson(String jsonString);
	
	public void updatePersonLicense(String jsonString);
	
	public void trainingInPerson(String jsonString);
	
	public void updateCertificate(String jsonString);
	
	public void eventInPerson(String jsonString);
	
	public void updateParticipation(String jsonString);

	public String queryPersonById(Integer id);	
	
	public JsonArray queryPersons(String ename);
	
	public JsonArray queryLicensesById(Integer id);
	
	public JsonArray queryTrainingsById(Integer id);
	
	public JsonArray queryEventsById(Integer id);
	
	public JsonArray queryPersonsByLicenseType(String operationLimit);
	
	public String queryLicenseInfo(Integer personId,Integer licenseId);
	
	public String queryTrainingInfo(Integer personId,Integer trainingId);
	
	public String queryEventInfo(Integer personId,Integer eventId);

	void check(Integer id);

	void approve(Integer id);

	void reject(Integer id);

	void delete(Integer id);
	
	public void checkCertificate(Integer personId,Integer trainingId);
	
	public void approveCertificate(Integer personId,Integer trainingId);
	
	public void rejectCertificate(Integer personId,Integer trainingId);

	public void deleteCertificate(Integer personId,Integer trainingId);	
	
	public void checkPersonLicense(Integer personId, Integer licenseId);
	
	public void approvePersonLicense(Integer personId, Integer licenseId);
	
	public void rejectPersonLicense(Integer personId, Integer licenseId);

	public void deletePersonLicense(Integer personId, Integer licenseId);	
	
	public void checkParticipation(Integer personId,Integer eventId);
	
	public void approveParticipation(Integer personId,Integer eventId);
	
	public void rejectParticipation(Integer personId,Integer eventId);

	public void deleteParticipation(Integer personId,Integer eventId);	
	
	public JsonArray queryPersonsByMissionInfo(Integer aerialPlanId,Integer equipmentId);
	
}

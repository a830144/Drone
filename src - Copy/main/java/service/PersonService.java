package service;

import com.google.gson.JsonArray;

public interface PersonService {
	public void persist(String jsonString);

	public void updatePerson(String jsonString);
	
	public String licenseInPerson(String jsonString);
	
	public String updatePersonLicense(String jsonString);
	
	public void trainingInPerson(String jsonString);
	
	public void updateCertificate(String jsonString);
	
	public void eventInPerson(String jsonString);
	
	public void updateParticipation(String jsonString);

	public String queryPersonById(Integer id);	
	
	public JsonArray queryPersons(String ename);
	
	public JsonArray queryLicensesById(Integer id);
	
	public String queryLicenseByType(String type);
	
	public JsonArray queryTrainingsById(Integer id);
	
	public JsonArray queryEventsById(Integer id);
	
	public JsonArray queryPersonsByLicenseType(String constructionType ,boolean operationLimit);
	
	public String queryLicenseInfo(Integer targetId);		
	
	public String queryLicenseInfo(Integer personId,Integer licenseId);
	
	public String queryTrainingInfo(Integer targetId);
	
	public String queryEventInfo(Integer targetId);

	void check(Integer id);

	void approve(Integer id);

	void reject(Integer id);

	void delete(Integer id);
	
	public void checkCertificate(Integer targetId);
	
	public void approveCertificate(Integer targetId);
	
	public void rejectCertificate(Integer targetId);

	public void deleteCertificate(Integer targetId);	
	
	public void checkPersonLicense(Integer targetId);
	
	public void approvePersonLicense(Integer targetId);
	
	public void rejectPersonLicense(Integer targetId);

	public void deletePersonLicense(Integer targetId);	
	
	public void checkParticipation(Integer targetId);
	
	public void approveParticipation(Integer targetId);
	
	public void rejectParticipation(Integer targetId);

	public void deleteParticipation(Integer targetId);	
	
	public JsonArray queryPersonsByMissionInfo(Integer aerialPlanId,Integer equipmentId);

	
}

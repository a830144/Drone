package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import service.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	
	
	@RequestMapping(value="/person/AddPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addPerson(String data) {
		System.out.println("AddPerson");
		personService.persist(data);		
	}
	
	@RequestMapping(value="/person/CheckPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkPerson(Integer targetId) {
		System.out.println("CheckPerson");
		personService.check(targetId);		
	}
	
	@RequestMapping(value="/person/ApprovePersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approvePerson(Integer targetId) {
		System.out.println("ApprovePerson");
		personService.approve(targetId);	
	}
	
	@RequestMapping(value="/person/RejectPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectPerson(Integer targetId) {
		System.out.println("RejectPerson");
		personService.reject(targetId);	
	}
	
	@RequestMapping(value="/person/DeletePersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deletePerson(Integer id) {
		System.out.println("DeletePerson");
		personService.delete(id);	
	}
	
	@RequestMapping(value="/person/EventInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void eventInPerson(String data) {
		personService.eventInPerson(data);		
	}
	
	@RequestMapping(value="/event/CheckEventProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkParticipation(Integer targetId) {
		System.out.println("CheckParticipation");
		personService.checkParticipation(targetId);		
	}
	
	@RequestMapping(value="/event/ApproveEventProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveParticipation(Integer targetId) {
		System.out.println("ApproveParticipation");
		personService.approveParticipation(targetId);	
	}
	
	@RequestMapping(value="/event/RejectEventProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectParticipation(Integer targetId) {
		System.out.println("RejectParticipation");
		personService.rejectParticipation(targetId);	
	}
	
	@RequestMapping(value="/person/DeleteParticipationProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteParticipation(Integer targetId) {
		System.out.println("DeleteParticipation");
		personService.deleteParticipation(targetId);	
	}
	
	
	@RequestMapping(value="/person/LicenseInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void licenseInPerson(String data) {
		personService.licenseInPerson(data);		
	}
	
	@RequestMapping(value="/license/CheckLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkPersonLicense(Integer targetId) {
		System.out.println("CheckPersonLicense::"+targetId);
		personService.checkPersonLicense(targetId);		
	}
	
	@RequestMapping(value="/license/ApproveLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approvePersonLicense(Integer targetId) {
		System.out.println("ApprovePersonLicense");
		personService.approvePersonLicense(targetId);	
	}
	
	@RequestMapping(value="/license/RejectLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectPersonLicense(Integer targetId) {
		System.out.println("RejectPersonLicense");
		personService.rejectPersonLicense(targetId);	
	}
	
	@RequestMapping(value="/person/DeletePersonLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deletePersonLicense(Integer targetId) {
		System.out.println("DeletePersonLicense");
		personService.deletePersonLicense(targetId);	
	}
	
	@RequestMapping(value="/person/TrainingInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void trainingInPerson(String data) {
		personService.trainingInPerson(data);		
	}
	
	@RequestMapping(value="/training/CheckTrainingProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkCertificate(Integer targetId) {
		System.out.println("CheckCertificate");
		personService.checkCertificate(targetId);		
	}
	
	@RequestMapping(value="/training/ApproveTrainingProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveCertificate(Integer targetId) {
		System.out.println("ApproveCertificate");
		personService.approveCertificate(targetId);	
	}
	
	@RequestMapping(value="/training/RejectTrainingProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectCertificate(Integer targetId) {
		System.out.println("RejectCertificate");
		personService.rejectCertificate(targetId);	
	}
	
	@RequestMapping(value="/person/DeleteCertificateProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteCertificate(Integer targetId) {
		System.out.println("DeleteCertificate");
		personService.deleteCertificate(targetId);	
	}
	
	@RequestMapping(value="/person/UpdatePersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updatePerson(String data) {
		personService.updatePerson(data);		
	}
	
	@RequestMapping(value="/person/ViewPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewPerson(String id) {
		String jsonString = personService.queryPersonById(new Integer(Integer.parseInt(id)));	
		return jsonString;
	}
	
	@RequestMapping(value="/person/QueryLicenses" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryLicenses(String id) {
		JsonArray jsonArray = personService.queryLicensesById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/person/ViewLicenseInfo" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewLicenseInfo(String targetId,String type) {
		String jsonString="";
		if(targetId!=null){
			jsonString = personService.queryLicenseInfo(new Integer(targetId));
			
		}else if(type!=null){
			jsonString = personService.queryLicenseByType(type);			
		}
		return jsonString;
	}
	
	@RequestMapping(value="/person/QueryTrainings" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryTrainings(String id) {
		JsonArray jsonArray = personService.queryTrainingsById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/person/ViewTrainingInfo" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewTrainingInfo(String targetId) {
		String jsonString = personService.queryTrainingInfo(new Integer(targetId));
		return jsonString;
	}
	
	@RequestMapping(value="/person/QueryEvents" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryEvents(String id) {
		JsonArray jsonArray = personService.queryEventsById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/person/ViewEventInfo" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewEventInfo(String targetId) {
		String jsonString = personService.queryEventInfo(new Integer(targetId));
		return jsonString;
	}
	
	/**
	 * 1. when we use name , we use the person name to query person
	 * 2. when we use operationLimit and constructionType, these two parameter will be used to filter the person list who own the mapping license
	 * 3. when we use aerialPlanID and equipmentID, these two factor will be used to query the mission table
	 * @param name
	 * @param operationLimit
	 * @param aerialPlanId
	 * @param equipmentId
	 * @return
	 */
	@RequestMapping(value="/person/QueryPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryPerson(String name,String constructionType,boolean operationLimit,String aerialPlanId, String equipmentId) {		
		System.out.println("here: name :"+name+";operationLimit="+operationLimit+";aerialPlanId:"+aerialPlanId);
		if(!"".equals(name)&& name!=null&&!"empty".equals(name)){
			JsonArray jsonArray = personService.queryPersons(name);
			String jsonString =jsonArray.toString();	
			return jsonString;
		}else if("empty".equals(name)){
			System.out.println("empty");
			JsonArray jsonArray = personService.queryPersons(null);
			String jsonString =jsonArray.toString();	
			System.out.println(jsonString);
			return jsonString;
		}else if(!"".equals(constructionType) && constructionType!=null){
			JsonArray jsonArray = personService.queryPersonsByLicenseType(constructionType,operationLimit);
			String jsonString =jsonArray.toString();	
			return jsonString;
		}else if(!"".equals(aerialPlanId) && aerialPlanId!=null && !"".equals(equipmentId) && equipmentId!=null){
			JsonArray jsonArray = personService.queryPersonsByMissionInfo(new Integer(aerialPlanId),new Integer(equipmentId));
			String jsonString =jsonArray.toString();	
			return jsonString;
		}else{
			return "";
		}
	}
	
	@RequestMapping(value="/person/UpdateLicenseInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateLicenseInPerson(String data) {
		System.out.println("Update License in person");
		personService.updatePersonLicense(data);	
	}
	
	@RequestMapping(value="/person/UpdateTrainingInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateTrainingInPerson(String data) {
		System.out.println("Update Training in person");
		personService.updateCertificate(data);		
	}
	
	@RequestMapping(value="/person/UpdateEventInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateEventInPerson(String data) {
		System.out.println("Update Event in person");
		personService.updateParticipation(data);		
	}
}

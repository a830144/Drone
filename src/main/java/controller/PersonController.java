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
	public void checkPerson(Integer id) {
		System.out.println("CheckPerson");
		personService.check(id);		
	}
	
	@RequestMapping(value="/person/ApprovePersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approvePerson(Integer id) {
		System.out.println("ApprovePerson");
		personService.approve(id);	
	}
	
	@RequestMapping(value="/person/RejectPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectPerson(Integer id) {
		System.out.println("RejectPerson");
		personService.reject(id);	
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
	
	@RequestMapping(value="/person/CheckParticipationProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkParticipation(Integer personId,Integer eventId) {
		System.out.println("CheckParticipation");
		personService.checkParticipation(personId,eventId);		
	}
	
	@RequestMapping(value="/person/ApproveParticipationProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveParticipation(Integer personId,Integer eventId) {
		System.out.println("ApproveParticipation");
		personService.approveParticipation(personId,eventId);	
	}
	
	@RequestMapping(value="/person/RejectParticipationProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectParticipation(Integer personId,Integer eventId) {
		System.out.println("RejectParticipation");
		personService.rejectParticipation(personId,eventId);	
	}
	
	@RequestMapping(value="/person/DeleteParticipationProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteParticipation(Integer personId,Integer eventId) {
		System.out.println("DeleteParticipation");
		personService.deleteParticipation(personId,eventId);	
	}
	
	
	@RequestMapping(value="/person/LicenseInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void licenseInPerson(String data) {
		personService.licenseInPerson(data);		
	}
	
	@RequestMapping(value="/person/CheckPersonLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkPersonLicense(Integer personId, Integer licenseId) {
		System.out.println("CheckPersonLicense");
		personService.checkPersonLicense(personId,licenseId);		
	}
	
	@RequestMapping(value="/person/ApprovePersonLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approvePersonLicense(Integer personId, Integer licenseId) {
		System.out.println("ApprovePersonLicense");
		personService.approvePersonLicense(personId,licenseId);	
	}
	
	@RequestMapping(value="/person/RejectPersonLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectPersonLicense(Integer personId, Integer licenseId) {
		System.out.println("RejectPersonLicense");
		personService.rejectPersonLicense(personId,licenseId);	
	}
	
	@RequestMapping(value="/person/DeletePersonLicenseProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deletePersonLicense(Integer personId, Integer licenseId) {
		System.out.println("DeletePersonLicense");
		personService.deletePersonLicense(personId,licenseId);	
	}
	
	@RequestMapping(value="/person/TrainingInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void trainingInPerson(String data) {
		personService.trainingInPerson(data);		
	}
	
	@RequestMapping(value="/person/CheckCertificateProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkCertificate(Integer personId,Integer trainingId) {
		System.out.println("CheckCertificate");
		personService.checkCertificate(personId,trainingId);		
	}
	
	@RequestMapping(value="/person/ApproveCertificateProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveCertificate(Integer personId,Integer trainingId) {
		System.out.println("ApproveCertificate");
		personService.approveCertificate(personId,trainingId);	
	}
	
	@RequestMapping(value="/person/RejectCertificateProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectCertificate(Integer personId,Integer trainingId) {
		System.out.println("RejectCertificate");
		personService.rejectCertificate(personId,trainingId);	
	}
	
	@RequestMapping(value="/person/DeleteCertificateProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteCertificate(Integer personId,Integer trainingId) {
		System.out.println("DeleteCertificate");
		personService.deleteCertificate(personId,trainingId);	
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
	public String viewLicenseInfo(String personId,String licenseId) {
		String jsonString = personService.queryLicenseInfo(new Integer(personId),new Integer(licenseId));
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
	public String viewTrainingInfo(String personId,String trainingId) {
		String jsonString = personService.queryTrainingInfo(new Integer(personId),new Integer(trainingId));
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
	public String viewEventInfo(String personId,String eventId) {
		String jsonString = personService.queryEventInfo(new Integer(personId),new Integer(eventId));
		return jsonString;
	}
	
	
	@RequestMapping(value="/person/QueryPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryPerson(String name,String operationLimit) {
		System.out.println("name:"+name);
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
		}
		else if(!"".equals(operationLimit) && operationLimit!=null){
			JsonArray jsonArray = personService.queryPersonsByLicenseType(operationLimit);
			String jsonString =jsonArray.toString();	
			return jsonString;
		}else{
			return "";
		}
	}
	
	@RequestMapping(value="/person/UpdateLicenseInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateLicenseInPerson(String data) {
		System.out.println("UpdateMaintainEquipment");
		//personService.updateMaintainEquipment(data);		
	}
	
	@RequestMapping(value="/person/UpdateTrainingInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateTrainingInPerson(String data) {
		System.out.println("UpdateModifyEquipment");
		//personService.updateModifyEquipment(data);		
	}
}

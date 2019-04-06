package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import entity.Persons;
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
	
	@RequestMapping(value="/person/EventInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void eventInPerson(String data) {
		personService.eventInPerson(data);		
	}
	
	@RequestMapping(value="/person/LicenseInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void licenseInPerson(String data) {
		personService.licenseInPerson(data);		
	}
	
	@RequestMapping(value="/person/TrainingInPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void maintainPerson(String data) {
		personService.trainingInPerson(data);		
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
	
	@RequestMapping(value="/person/ViewLicense" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewLicense(String id) {
		JsonArray jsonArray = personService.queryLicensesById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/person/ViewTraining" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewTraining(String id) {
		JsonArray jsonArray = personService.queryTrainingsById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/person/ViewEvent" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewEvent(String id) {
		JsonArray jsonArray = personService.queryEventsById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	
	@RequestMapping(value="/person/QueryPersonProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryPerson(String name) {
		List<Persons> list = personService.queryPersons(name);
		Iterator<Persons> iterator = list.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Persons entity_persons = (Persons) iterator.next();
			Persons vo = new Persons();
			try {
				BeanUtils.copyProperties(vo, entity_persons);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			JSONObject jsonObj = new JSONObject(vo);
			jsonArray.add(jsonObj.toString());
		}

		String jsonString =jsonArray.toString();	
		return jsonString;
	}
}

package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import entity.Persons;
import service.PersonService;

public class ConcretePersonServiceDecorator extends PersonServiceDecorator{


	public ConcretePersonServiceDecorator(PersonService personService) {
		super(personService);
	}

	public JsonArray queryPersonsJsonSring(String ename) {
		List<Persons> list = this.queryPersons(ename);
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

		return jsonArray;
	}

	@Override
	public void licenseInPerson(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trainingInPerson(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventInPerson(String jsonString) {
		// TODO Auto-generated method stub
		
	}
}

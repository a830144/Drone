package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.EventDao;
import dao.LicenseDao;
import dao.PersonDao;
import dao.TrainingDao;
import entity.Certificates;
import entity.Events;
import entity.Licenses;
import entity.Participations;
import entity.Persons;
import entity.PersonsLicenses;
import entity.Trainings;
import service.PersonService;
import vo.EventInPerson;
import vo.LicenseInPerson;
import vo.Person;
import vo.TrainingInPerson;

public class PersonServiceImpl implements PersonService {

	private PersonDao personDao;
	private LicenseDao licenseDao;
	private TrainingDao trainingDao;
	private EventDao eventDao;

	public PersonServiceImpl() {
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public LicenseDao getLicenseDao() {
		return licenseDao;
	}

	public void setLicenseDao(LicenseDao licenseDao) {
		this.licenseDao = licenseDao;
	}

	public TrainingDao getTrainingDao() {
		return trainingDao;
	}

	public void setTrainingDao(TrainingDao trainingDao) {
		this.trainingDao = trainingDao;
	}

	public EventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public void persist(String jsonString) {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Persons entity = gson.fromJson(jsonString, Persons.class);
		personDao.persist(entity);
	}

	@Override
	public void updatePerson(String jsonString) {
		Gson gson = new Gson();
		Persons vo = gson.fromJson(jsonString, Persons.class);
		Persons entity = personDao.findById(vo.getPersonId());
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		personDao.update(entity);
	}

	@Override
	public String queryPersonById(Integer id) {
		Persons entity_persons = personDao.findById(id);
		//sessionFactory.getCurrentSession().evict(persons);
		// get through the result set
		Person vo = new Person();
		try {
			BeanUtils.copyProperties(vo, entity_persons);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";

		JSONObject jsonObj = new JSONObject(vo);
		jsonString = jsonObj.toString();

		return jsonString;
	}

	@Override
	public void delete(Persons entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Persons> queryPersons(String ename) {
		List<Persons> personsList;
		if ("".equals(ename) || ename == null) {
			personsList = personDao.findAll();
		} else {
			personsList = personDao.findByName(ename);
		}
		return personsList;
		//tx.commit();
		// get through the result set
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void licenseInPerson(String jsonString) {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		LicenseInPerson vo = gson.fromJson(jsonString, LicenseInPerson.class);

		Persons entity_persons = personDao.findById(vo.getPersonId());
		PersonsLicenses entity_personsLicenses = new PersonsLicenses();
		Licenses entity_licenses = licenseDao.findByType(vo.getType(), vo.getSubType());
		try {
			BeanUtils.copyProperties(entity_personsLicenses, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		entity_persons.getPersonsLicenseses().add(entity_personsLicenses);

		entity_licenses.getPersonsLicenseses().add(entity_personsLicenses);
		entity_personsLicenses.setPersons(entity_persons);
		entity_personsLicenses.setLicenses(entity_licenses);

		personDao.persist(entity_persons);
		licenseDao.persist(entity_licenses);
	}

	@Override
	public void trainingInPerson(String jsonString) {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		TrainingInPerson vo = gson.fromJson(jsonString, TrainingInPerson.class);

		Persons entity_persons = personDao.findById(vo.getPersonId());
		Certificates entity_certificates = new Certificates();
		Trainings entity_trainings = trainingDao.findByName(vo.getName());
		try {
			BeanUtils.copyProperties(entity_certificates, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		entity_persons.getCertificateses().add(entity_certificates);
		entity_trainings.getCertificateses().add(entity_certificates);

		entity_certificates.setPersons(entity_persons);
		entity_certificates.setTrainings(entity_trainings);

		personDao.persist(entity_persons);
		trainingDao.persist(entity_trainings);
	}

	@Override
	public void eventInPerson(String jsonString) {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		EventInPerson vo = gson.fromJson(jsonString, EventInPerson.class);

		Persons entity_persons = personDao.findById(vo.getPersonId());
		Participations entity_participations = new Participations();
		Events entity_events = eventDao.findByName(vo.getName());
		try {
			BeanUtils.copyProperties(entity_participations, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		entity_persons.getParticipationses().add(entity_participations);
		entity_events.getParticipationses().add(entity_participations);

		entity_participations.setPersons(entity_persons);
		entity_participations.setEvents(entity_events);

		personDao.persist(entity_persons);
		eventDao.persist(entity_events);
	}

	@Override
	public JsonArray queryLicensesById(Integer id) {
		Persons entity_persons = personDao.findById(id);
		Iterator<PersonsLicenses> iterator_personsLicensess = entity_persons.getPersonsLicenseses().iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_personsLicensess.hasNext()) {
			LicenseInPerson vo = new LicenseInPerson();
			PersonsLicenses entity_personsLicenses = (PersonsLicenses)iterator_personsLicensess.next();
			try {
				BeanUtils.copyProperties(vo, entity_personsLicenses);
				BeanUtils.copyProperties(vo, entity_personsLicenses.getLicenses());
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
	public JsonArray queryTrainingsById(Integer id) {
		Persons entity_persons = personDao.findById(id);
		Iterator<Certificates> iterator_certificates = entity_persons.getCertificateses().iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_certificates.hasNext()) {
			TrainingInPerson vo = new TrainingInPerson();
			Certificates entity_certificates = (Certificates)iterator_certificates.next();
			try {
				BeanUtils.copyProperties(vo, entity_certificates);
				BeanUtils.copyProperties(vo, entity_certificates.getTrainings());
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
	public JsonArray queryEventsById(Integer id) {
		Persons entity_persons = personDao.findById(id);
		Iterator<Participations> iterator_participations = entity_persons.getParticipationses().iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_participations.hasNext()) {
			EventInPerson vo = new EventInPerson();
			Participations entity_participations = (Participations)iterator_participations.next();
			try {
				BeanUtils.copyProperties(vo, entity_participations);
				BeanUtils.copyProperties(vo, entity_participations.getEvents());
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

}

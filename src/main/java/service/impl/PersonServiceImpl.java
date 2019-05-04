package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

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
	
	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

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
		Persons entity = gson.fromJson(jsonString, Persons.class);
		personDao.persist(entity);
	}

	@Override
	public void updatePerson(String jsonString) {
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
		Person vo = new Person();
		try {
			BeanUtils.copyProperties(vo, entity_persons);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";
		jsonString = gson.toJson(vo);

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
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void licenseInPerson(String jsonString) {
		LicenseInPerson vo = gson.fromJson(jsonString, LicenseInPerson.class);
		System.out.println("vo.getPersonId()::"+vo.getPersonId());
		Persons entity_persons = personDao.findById(vo.getPersonId());
		PersonsLicenses entity_personsLicenses = new PersonsLicenses();
		System.out.println(vo.getType());
		Licenses entity_licenses = licenseDao.findByType(vo.getType());
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
		TrainingInPerson vo = gson.fromJson(jsonString, TrainingInPerson.class);

		Persons entity_persons = personDao.findById(vo.getPersonId());
		Certificates entity_certificates = new Certificates();
		Trainings entity_trainings = trainingDao.findById(vo.getTrainingId());
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
		EventInPerson vo = gson.fromJson(jsonString, EventInPerson.class);

		Persons entity_persons = personDao.findById(vo.getPersonId());
		Participations entity_participations = new Participations();
		Events entity_events = eventDao.findById(vo.getEventId());
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

			jsonArray.add(gson.toJson(vo));

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
			vo.setShowTrainingType();
			jsonArray.add(gson.toJson(vo));

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
			
			jsonArray.add(gson.toJson(vo));

		}
		return jsonArray;
	}

	@Override
	public String queryLicenseInfo(Integer personId, Integer licenseId) {
		PersonsLicenses entity_personLicenses = personDao.findLicenseInfo(personId,licenseId);
		LicenseInPerson vo = new LicenseInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_personLicenses);
			BeanUtils.copyProperties(vo, entity_personLicenses.getPersons());
			BeanUtils.copyProperties(vo, entity_personLicenses.getLicenses());			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}

	@Override
	public String queryTrainingInfo(Integer personId, Integer trainingId) {
		Certificates entity_certificates = personDao.findTrainingInfo(personId,trainingId);
		TrainingInPerson vo = new TrainingInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_certificates);
			BeanUtils.copyProperties(vo, entity_certificates.getPersons());
			BeanUtils.copyProperties(vo, entity_certificates.getTrainings());			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";
		jsonString = gson.toJson(vo);
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}

	@Override
	public String queryEventInfo(Integer personId, Integer eventId) {
		Participations entity_participations = personDao.findEventInfo(personId, eventId);
		EventInPerson vo = new EventInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_participations);
			BeanUtils.copyProperties(vo, entity_participations.getPersons());
			BeanUtils.copyProperties(vo, entity_participations.getEvents());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}

	

}

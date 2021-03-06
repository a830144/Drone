package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.EventDao;
import dao.LicenseDao;
import dao.PersonDao;
import dao.TrainingDao;
import entity.CertificateFlow;
import entity.Certificates;
import entity.Events;
import entity.Licenses;
import entity.ParticipationFlow;
import entity.Participations;
import entity.PersonFlow;
import entity.Persons;
import entity.PersonsLicenses;
import entity.PersonsLicensesFlow;
import entity.Trainings;
import service.PersonService;
import stateMachine.States;
import stateMachine.certificate.CertificatePersistStateMachineHandler;
import stateMachine.participation.ParticipationPersistStateMachineHandler;
import stateMachine.person.PersonPersistStateMachineHandler;
import stateMachine.personlicense.PersonlicensePersistStateMachineHandler;
import util.EntityConstants;
import vo.EventInPerson;
import vo.LicenseInPerson;
import vo.Person;
import vo.TrainingInPerson;

@Service
public class PersonServiceImpl implements PersonService {

	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private LicenseDao licenseDao;
	
	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	@Qualifier("personPersistStateMachineHandler")
	private PersonPersistStateMachineHandler personPersistStateMachineHandler;
	
	@Autowired
	@Qualifier("participationPersistStateMachineHandler")
	private ParticipationPersistStateMachineHandler participationPersistStateMachineHandler;
	
	@Autowired
	@Qualifier("certificatePersistStateMachineHandler")
	private CertificatePersistStateMachineHandler certificatePersistStateMachineHandler;
	
	@Autowired
	@Qualifier("personlicensePersistStateMachineHandler")
	private PersonlicensePersistStateMachineHandler personlicensePersistStateMachineHandler;	

	public PersonServiceImpl() {
	}

	@Override
	public void persist(String jsonString) {		
		Persons entity = gson.fromJson(jsonString, Persons.class);
		PersonFlow entity_personFlow = gson.fromJson(jsonString, PersonFlow.class);
		entity_personFlow.setState(States.PROCESSING);
		entity.setPersonFlow(entity_personFlow);
		personDao.persist(entity);
	}

	@Override
	public void updatePerson(String jsonString) {
		Person vo = gson.fromJson(jsonString, Person.class);
		Persons entity_persons = personDao.findById(vo.getPersonId());
		try {
			BeanUtils.copyProperties(entity_persons, vo);
			if(entity_persons.getPersonFlow()!=null){
				BeanUtils.copyProperties( entity_persons.getPersonFlow(),vo);
			}else{
				PersonFlow entity_personFlow = new PersonFlow();
				BeanUtils.copyProperties( entity_personFlow,vo);
				entity_persons.setPersonFlow(entity_personFlow);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		updatePersonState(entity_persons, stateMachine.Events.UPDATE);
		personDao.update(entity_persons);
	}

	@Override
	public String queryPersonById(Integer id) {
		Persons entity_persons = personDao.findById(id);
		Person vo = new Person();
		try {
			BeanUtils.copyProperties(vo, entity_persons);
			if(entity_persons.getPersonFlow()!=null){
				BeanUtils.copyProperties(vo, entity_persons.getPersonFlow());
			}
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
	public JsonArray queryPersons(String ename) {
		List<Persons> personsList;
		if ("".equals(ename) || ename == null) {
			personsList = personDao.findAll();
		} else {
			personsList = personDao.findByName(ename);
		}
		Iterator<Persons> iterator = personsList.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Persons entity_persons = (Persons) iterator.next();
			Person vo = new Person();
			try {
				BeanUtils.copyProperties(vo, entity_persons);
				if (entity_persons.getPersonFlow() != null) {
					BeanUtils.copyProperties(vo, entity_persons.getPersonFlow());
				}
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
	public String licenseInPerson(String jsonString) {
		LicenseInPerson vo = gson.fromJson(jsonString, LicenseInPerson.class);
		PersonsLicenses entity_personsLicenses = personDao.findLicenseInfo(vo.getPersonId(),
				Integer.parseInt(vo.getType()));
		if (entity_personsLicenses != null) {
			String[] strings = {"error", "此人員已有所選取的工作證"};
			return gson.toJson(strings);
		} else {

			Persons entity_persons = personDao.findById(vo.getPersonId());
			entity_personsLicenses = new PersonsLicenses();
			PersonsLicensesFlow entity_personsLicensesFlow = gson.fromJson(jsonString, PersonsLicensesFlow.class);
			entity_personsLicensesFlow.setState(States.PROCESSING);
			entity_personsLicenses.setPersonsLicensesFlow(entity_personsLicensesFlow);
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
			
			String[] strings = {"success", "新增操作證紀錄成功"};
			return gson.toJson(strings);
		}
	}
	
	@Override
	public String updatePersonLicense(String jsonString) {
		LicenseInPerson vo = gson.fromJson(jsonString, LicenseInPerson.class);
		PersonsLicenses entity_personsLicenses = personDao.findLicenseInfo(vo.getPersonId(),
				Integer.parseInt(vo.getType()));
		if (entity_personsLicenses != null) {
			String[] strings = {"error", "您所打算修改的工作證類別,此人員已經擁有"};
			return gson.toJson(strings);
		} else {
			PersonsLicenses entity_personslicenses = personDao.findLicenseInfo(vo.getPersonsLicensesId());
			try {
				BeanUtils.copyProperties(entity_personslicenses, vo);
				if (entity_personslicenses.getLicenses().getLicenseId() != vo.getLicenseId()) {
					Licenses entity_licenses = licenseDao.findById(vo.getLicenseId());
					entity_personslicenses.setLicenses(entity_licenses);
				}
				if (entity_personslicenses.getPersonsLicensesFlow() != null) {
					BeanUtils.copyProperties(entity_personslicenses.getPersonsLicensesFlow(), vo);
				} else {
					PersonsLicensesFlow entity_personsLicensesFlow = new PersonsLicensesFlow();
					BeanUtils.copyProperties(entity_personsLicensesFlow, vo);
					entity_personslicenses.setPersonsLicensesFlow(entity_personsLicensesFlow);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			updatePersonLicenseState(entity_personslicenses, stateMachine.Events.UPDATE);
			personDao.updatePersonsLicenses(entity_personslicenses);
			String[] strings = {"success", "修改操作證紀錄成功"};
			return gson.toJson(strings);
		}
	}

	@Override
	public void trainingInPerson(String jsonString) {
		TrainingInPerson vo = gson.fromJson(jsonString, TrainingInPerson.class);
		Persons entity_persons = personDao.findById(vo.getPersonId());
		Certificates entity_certificates = new Certificates();
		CertificateFlow entity_certificateFlow = gson.fromJson(jsonString, CertificateFlow.class);
		entity_certificateFlow.setState(States.PROCESSING);
		entity_certificates.setCertificateFlow(entity_certificateFlow);
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
	public void updateCertificate(String jsonString) {
		TrainingInPerson vo = gson.fromJson(jsonString, TrainingInPerson.class);
		Certificates entity_certificates = personDao.findTrainingInfo(vo.getCertificateId());
		try {
			BeanUtils.copyProperties(entity_certificates, vo);
			if(entity_certificates.getTrainings().getTrainingId()!=vo.getTrainingId()){
				Trainings entity_trainings = trainingDao.findById(vo.getTrainingId());
				entity_certificates.setTrainings(entity_trainings);
			}
			if(entity_certificates.getCertificateFlow()!=null){
				BeanUtils.copyProperties( entity_certificates.getCertificateFlow(),vo);
			}else{
				CertificateFlow entity_certificateFlow = new CertificateFlow();
				BeanUtils.copyProperties( entity_certificateFlow,vo);
				entity_certificates.setCertificateFlow(entity_certificateFlow);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		updateCertificateState(entity_certificates, stateMachine.Events.UPDATE);
		personDao.updateCertificate(entity_certificates);
		
	}

	@Override
	public void eventInPerson(String jsonString) {
		EventInPerson vo = gson.fromJson(jsonString, EventInPerson.class);

		Persons entity_persons = personDao.findById(vo.getPersonId());
		Participations entity_participations = new Participations();
		ParticipationFlow entity_participationFlow = gson.fromJson(jsonString, ParticipationFlow.class);
		entity_participationFlow.setState(States.PROCESSING);
		entity_participations.setParticipationFlow(entity_participationFlow);
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
	public void updateParticipation(String jsonString) {
		EventInPerson vo = gson.fromJson(jsonString, EventInPerson.class);
		Participations entity_participations = personDao.findEventInfo(vo.getParticipationId());
		try {
			BeanUtils.copyProperties(entity_participations, vo);
			if(entity_participations.getEvents().getEventId()!=vo.getEventId()){
				Events entity_events = eventDao.findById(vo.getEventId());
				entity_participations.setEvents(entity_events);
			}
			if(entity_participations.getParticipationFlow()!=null){
				BeanUtils.copyProperties( entity_participations.getParticipationFlow(),vo);
			}else{
				ParticipationFlow entity_participationFlow = new ParticipationFlow();
				BeanUtils.copyProperties( entity_participationFlow,vo);
				entity_participations.setParticipationFlow(entity_participationFlow);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		updateParticipationState(entity_participations, stateMachine.Events.UPDATE);
		personDao.updateParticipation(entity_participations);
		
	}

	@Override
	public JsonArray queryLicensesById(Integer id) {
		/*
		 * State,type,got_Date,Code_Content,personsLicensesId,construction_type,construction_type description
		 */
		List<Object[]> resultlist= personDao.findLicenseDetail(id);
		JsonArray jsonArray = new JsonArray();
		for(Object[] result : resultlist) {
			LicenseInPerson vo = new LicenseInPerson();
			String state = (String) result[0];
		    Integer licenseId = (Integer) result[1];
		    Date gotDate = (Date)result[2];
		    String codeContent = (String) result[3];
		    Integer personsLicensesId = (Integer) result[4];
		    String constructionType = (String) result[6];
		    
		    vo.setState(stateMachine.States.valueOf(state));
		    vo.setLicenseId(licenseId);
		    vo.setGotDate(gotDate);
		    vo.setCodeContent(codeContent);
		    vo.setPersonsLicensesId(personsLicensesId);
		    vo.setConstructionType(constructionType);
		
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
				if (entity_certificates.getCertificateFlow() != null) {
					BeanUtils.copyProperties(vo, entity_certificates.getCertificateFlow());
				}
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
				if (entity_participations.getParticipationFlow() != null) {
					BeanUtils.copyProperties(vo, entity_participations.getParticipationFlow());
				}
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
			if (entity_personLicenses.getPersonsLicensesFlow() != null) {
				BeanUtils.copyProperties(vo, entity_personLicenses.getPersonsLicensesFlow());
			}
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
	public String queryLicenseInfo(Integer targetId) {
		PersonsLicenses entity_personLicenses = personDao.findLicenseInfo(targetId);
		LicenseInPerson vo = new LicenseInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_personLicenses);
			BeanUtils.copyProperties(vo, entity_personLicenses.getPersons());
			BeanUtils.copyProperties(vo, entity_personLicenses.getLicenses());
			if (entity_personLicenses.getPersonsLicensesFlow() != null) {
				BeanUtils.copyProperties(vo, entity_personLicenses.getPersonsLicensesFlow());
			}
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
	public String queryTrainingInfo(Integer targetId) {
		Certificates entity_certificates = personDao.findTrainingInfo(targetId);
		TrainingInPerson vo = new TrainingInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_certificates);
			BeanUtils.copyProperties(vo, entity_certificates.getPersons());
			BeanUtils.copyProperties(vo, entity_certificates.getTrainings());
			if (entity_certificates.getCertificateFlow() != null) {
				BeanUtils.copyProperties(vo, entity_certificates.getCertificateFlow());
			}
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
	public String queryEventInfo(Integer targetId) {
		Participations entity_participations = personDao.findEventInfo(targetId);
		EventInPerson vo = new EventInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_participations);
			BeanUtils.copyProperties(vo, entity_participations.getPersons());
			BeanUtils.copyProperties(vo, entity_participations.getEvents());
			if (entity_participations.getParticipationFlow() != null) {
				BeanUtils.copyProperties(vo, entity_participations.getParticipationFlow());
			}
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
	public JsonArray queryPersonsByLicenseType(String constructionType ,boolean operationLimit) {
		JsonArray jsonArray = new JsonArray();
		if(constructionType!=null){
			List<Persons> personsList;
			if (operationLimit) {
				Set<String> typesStr = new HashSet<String>();
				typesStr.add("13");
				typesStr.add("14");
				typesStr.add("15");
				typesStr.add("16");
				typesStr.add("17");
				personsList = personDao.findPersonWithLicense(constructionType,typesStr);
			} else {
				personsList = personDao.findPersonWithLicense(constructionType,null);
			}
			Iterator<Persons> iterator = personsList.iterator();

			while (iterator.hasNext()) {
				Persons entity_persons = (Persons) iterator.next();
				Person vo = new Person();
				try {
					BeanUtils.copyProperties(vo, entity_persons);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				jsonArray.add(gson.toJson(vo));

			}
		}
		return jsonArray;
	}
	
	
	private Boolean updatePersonState(Integer id, stateMachine.Events event) {
		System.out.println("use updatePersonState!!!");
		Persons entity_persons = personDao.findById(id);
		return personPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_persons).build(),
						entity_persons.getPersonFlow().getState().name());
	}

	private Boolean updatePersonState(Persons entity_persons, stateMachine.Events event) {
		System.out.println("use updatePersonState!!!");
		return personPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_persons).build(),
						entity_persons.getPersonFlow().getState().name());
	}

	@Override
	public void check(Integer id){
		updatePersonState(id, stateMachine.Events.CHECK);
	}
	
	@Override
	public void approve(Integer id){
		updatePersonState(id, stateMachine.Events.APPROVE);
	}
	
	@Override
	public void reject(Integer id){
		updatePersonState(id, stateMachine.Events.REJECT);
	}
	
	@Override
	public void delete(Integer id){
		updatePersonState(id, stateMachine.Events.DELETE);
	}	
		
	private Boolean updateCertificateState(Integer targetId, stateMachine.Events event) {
		System.out.println("use updateCertificateState!!!");
		Certificates entity_certificates = personDao.findTrainingInfo(targetId);
		return certificatePersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_certificates).build(),
								entity_certificates.getCertificateFlow().getState().name());
	}

	private Boolean updateCertificateState(Certificates entity_certificates, stateMachine.Events event) {
		System.out.println("use updateCertificateState!!!");
		return certificatePersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_certificates).build(),
								entity_certificates.getCertificateFlow().getState().name());
	}
	
	@Override
	public void checkCertificate(Integer targetId) {
		updateCertificateState(targetId, stateMachine.Events.CHECK);
	}

	@Override
	public void approveCertificate(Integer targetId) {
		updateCertificateState(targetId, stateMachine.Events.APPROVE);
	}
	
	@Override
	public void rejectCertificate(Integer targetId) {
		updateCertificateState(targetId, stateMachine.Events.REJECT);
	}
	
	@Override
	public void deleteCertificate(Integer targetId) {
		updateCertificateState(targetId, stateMachine.Events.DELETE);
	}	
	
	private Boolean updatePersonLicenseState(Integer targetId, stateMachine.Events event) {
		System.out.println("use updatePersonLicenseState!!!");
		PersonsLicenses entity_personsLicenses = personDao.findLicenseInfo(targetId);
		return personlicensePersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_personsLicenses).build(),
								entity_personsLicenses.getPersonsLicensesFlow().getState().name());
	}

	private Boolean updatePersonLicenseState(PersonsLicenses entity_personsLicenses, stateMachine.Events event) {
		System.out.println("use updatePersonLicenseState!!!");
		return personlicensePersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_personsLicenses).build(),
								entity_personsLicenses.getPersonsLicensesFlow().getState().name());
	}
	
	@Override
	public void checkPersonLicense(Integer targetId) {
		updatePersonLicenseState(targetId, stateMachine.Events.CHECK);		
	}

	@Override
	public void approvePersonLicense(Integer targetId) {
		updatePersonLicenseState(targetId, stateMachine.Events.APPROVE);		
	}

	@Override
	public void rejectPersonLicense(Integer targetId) {
		updatePersonLicenseState(targetId, stateMachine.Events.REJECT);		
	}
	
	@Override
	public void deletePersonLicense(Integer targetId) {
		updatePersonLicenseState(targetId, stateMachine.Events.DELETE);		
	}	
	
	private Boolean updateParticipationState(Integer targetId, stateMachine.Events event) {
		System.out.println("use updateParticipationState!!!");
		Participations entity_participations = personDao.findEventInfo(targetId);
		return participationPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_participations).build(),
								entity_participations.getParticipationFlow().getState().name());
	}

	private Boolean updateParticipationState(Participations entity_participations, stateMachine.Events event) {
		System.out.println("use updateParticipationState!!!");
		return participationPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_participations).build(),
								entity_participations.getParticipationFlow().getState().name());
	}
	
	
	@Override
	public void checkParticipation(Integer targetId) {
		updateParticipationState(targetId, stateMachine.Events.CHECK);		
	}

	@Override
	public void approveParticipation(Integer targetId) {
		updateParticipationState(targetId, stateMachine.Events.APPROVE);				
	}

	@Override
	public void rejectParticipation(Integer targetId) {
		updateParticipationState(targetId, stateMachine.Events.REJECT);		
		
	}
		
	@Override
	public void deleteParticipation(Integer targetId) {
		updateParticipationState(targetId, stateMachine.Events.DELETE);				
	}

	@Override
	public JsonArray queryPersonsByMissionInfo(Integer aerialPlanId, Integer equipmentId) {
		List<Persons> personsList = personDao.findByMission(aerialPlanId, equipmentId);
		Iterator<Persons> iterator = personsList.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Persons entity_persons = (Persons) iterator.next();
			Person vo = new Person();
			try {
				BeanUtils.copyProperties(vo, entity_persons);
				if (entity_persons.getPersonFlow() != null) {
					BeanUtils.copyProperties(vo, entity_persons.getPersonFlow());
				}
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
	public String queryLicenseByType(String type) {
		Licenses entity_licenses = licenseDao.findByType(type);
		LicenseInPerson vo = new LicenseInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_licenses);
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

package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

import dao.AerialActivityDao;
import dao.AerialPlanDao;
import dao.EquipmentDao;
import dao.PersonDao;
import dao.ProjectDao;
import entity.AerialActivities;
import entity.AerialActivityFlow;
import entity.AerialPlanFlow;
import entity.AerialPlans;
import entity.Equipments;
import entity.Missions;
import entity.Operations;
import entity.Persons;
import entity.ProjectFlow;
import entity.Projects;
import entity.RealMissions;
import service.OperationService;
import stateMachine.Events;
import stateMachine.States;
import stateMachine.aerialactivity.AerialactivityPersistStateMachineHandler;
import stateMachine.aerialplan.AerialplanPersistStateMachineHandler;
import stateMachine.project.ProjectPersistStateMachineHandler;
import util.EntityConstants;
import vo.AerialActivity;
import vo.AerialActivity.EquipmentPersonInActivity;
import vo.AerialPlan;
import vo.AerialPlan.EquipmentPerson;
import vo.Project;

@Service
public class OperationServiceImpl implements OperationService {
	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

	@Autowired
	private AerialPlanDao aerialPlanDao;
	
	@Autowired
	private AerialActivityDao aerialActivityDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	@Qualifier("projectPersistStateMachineHandler")
	private ProjectPersistStateMachineHandler projectPersistStateMachineHandler;
	
	@Autowired
	@Qualifier("aerialplanPersistStateMachineHandler")
	private AerialplanPersistStateMachineHandler aerialplanPersistStateMachineHandler;
	
	@Autowired
	@Qualifier("aerialactivityPersistStateMachineHandler")
	private AerialactivityPersistStateMachineHandler aerialactivityPersistStateMachineHandler;	

	

	/**
	 * Persist Missions entity
	 * @param entity_aerialPlans
	 * @param equipmentId
	 * @param personId
	 */
	private void persistMission(AerialPlans entity_aerialPlans,Integer equipmentId,Integer personId){
		Equipments entity_equipments = equipmentDao.findById(equipmentId);
		Persons entity_persons = personDao.findById(personId);
		Missions entity_missions = new Missions();
		entity_missions.setAerialPlans(entity_aerialPlans);
		entity_missions.setEquipments(entity_equipments);
		entity_missions.setPersons(entity_persons);
		entity_aerialPlans.getMissionses().add(entity_missions);
		entity_equipments.getMissionses().add(entity_missions);
		entity_persons.getMissionses().add(entity_missions);
		aerialPlanDao.persistMission(entity_missions);
	}
	
	/**
	 * Persist RealMissions entity
	 * @param entity_aerialPlans
	 * @param equipmentId
	 * @param personId
	 */
	private void persistRealMission(AerialActivities entity_aerialActivities,Integer equipmentId,Integer personId){
		Equipments entity_equipments = equipmentDao.findById(equipmentId);
		Persons entity_persons = personDao.findById(personId);
		RealMissions entity_realMissions = new RealMissions();
		entity_realMissions.setAerialActivities(entity_aerialActivities);
		entity_realMissions.setEquipments(entity_equipments);
		entity_realMissions.setPersons(entity_persons);
		entity_aerialActivities.getRealmissionses().add(entity_realMissions);
		entity_equipments.getRealmissionses().add(entity_realMissions);
		entity_persons.getRealmissionses().add(entity_realMissions);
		aerialActivityDao.persistRealMission(entity_realMissions);
	}


	@Override
	public List<AerialPlans> queryAerialPlans(String projectId) {
		List<AerialPlans> aerialPlansList;
		if ("".equals(projectId) || projectId == null) {
			aerialPlansList = aerialPlanDao.findAll();
		} else {
			aerialPlansList = aerialPlanDao.findByProjectId(Integer.parseInt(projectId));
		}
		return aerialPlansList;
	}
	
	@Override
	public JsonArray queryAerialPlanByProjectId(Integer projectId) {
		List<AerialPlans> list_aerialPlan = aerialPlanDao.findByProjectId(projectId);
		Iterator<AerialPlans> iterator_aerialPlanses = list_aerialPlan.iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_aerialPlanses.hasNext()) {
			AerialPlan vo = new AerialPlan();
			AerialPlans entity_aerialPlans = (AerialPlans) iterator_aerialPlanses.next();
			try {
				BeanUtils.copyProperties(vo, entity_aerialPlans);
				if(entity_aerialPlans.getAerialPlanFlow()!=null){
					BeanUtils.copyProperties(vo, entity_aerialPlans.getAerialPlanFlow());
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

	/**
	 * 1. project : aerialPlan = 1: m
	 * 2. get aerialActivity data, but we don't need project data in this case
	 * 3. get the mission data from the aerialPlan ID
	 * 		3.1 for online update, this data must include default and selectable options
	 * 		3.2 aggregate to display in value object
	 */
	@Override
	public String queryAerialPlanByAerialPlanId(Integer aerialPlanId) {
		AerialPlans entity_aerialPlans = aerialPlanDao.findById(aerialPlanId,true);
		AerialPlan vo = new AerialPlan();
		try {
			BeanUtils.copyProperties(vo, entity_aerialPlans);
			if(entity_aerialPlans.getAerialPlanFlow()!=null){
				BeanUtils.copyProperties(vo, entity_aerialPlans.getAerialPlanFlow());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		List<Object[]> resultlist = aerialPlanDao.findMission(aerialPlanId);
		Map<Integer,LinkedList<String>> map = new HashMap<Integer,LinkedList<String>>();
		for(Object[] result : resultlist) {
		    Number equipmentId = (Number) result[0];
		    Number personId = (Number) result[1];
		    LinkedList<String> list;
			if(map.get(equipmentId)==null){
				list = new LinkedList<String>();
			}else{
				list = map.get(equipmentId);			
			}
			String[] availPersonIds = ((String)result[2]).split(",");
			String tag = "";
			for(int i=0;i<availPersonIds.length;i++){
				if(availPersonIds[i].equals(personId.toString())){
					tag+="selected "+availPersonIds[i]+",";
				}else{
					tag+=availPersonIds[i]+",";
				}
			}
			/*String tag ="<select>";
			for(int i=0;i<availPersonIds.length;i++){
				if(availPersonIds[i].equals(personId.toString())){
					tag+="<option value='"+availPersonIds[i]+"' selected>"+availPersonIds[i]+"</option>";
				}else{
					tag+="<option value='"+availPersonIds[i]+"'>"+availPersonIds[i]+"</option>";
				}
			}
			tag+="<select>";*/
			list.add(tag);
			map.put(equipmentId.intValue(),list);
		}
		
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> iterator_keySet = keySet.iterator();
		List<EquipmentPerson> resultList = new LinkedList<EquipmentPerson>();
		while(iterator_keySet.hasNext()){
			Integer key = iterator_keySet.next();
			LinkedList<String> value = map.get(key);
			AerialPlan.EquipmentPerson vo_equipmentPerson = new AerialPlan().new EquipmentPerson();
			vo_equipmentPerson.setEquipmentId(key.toString());
			vo_equipmentPerson.setPersonId_1(value.get(0).toString());
			if(value.size()>1){
				vo_equipmentPerson.setPersonId_2(value.get(1).toString());
			}
			resultList.add(vo_equipmentPerson);
		}
		vo.setEquipmentsArray(resultList);
		
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}

	public OperationServiceImpl() {
		
	}

	

	@Override
	public void deleteAll() {

	}

	/**
	 * 1. persist project
	 * 2. set project state as 'PROCESSING'
	 */
	@Override
	public void persistProject(String jsonString) {
		Projects entity = gson.fromJson(jsonString, Projects.class);
		ProjectFlow entity_projectFlow = gson.fromJson(jsonString, ProjectFlow.class);
		entity_projectFlow.setState(States.PROCESSING);
		entity.setProjectFlow(entity_projectFlow);
		projectDao.persist(entity);
	}
	
	/**
	 * 1. Project : AerialPlan = 1:m
	 * 2. set AerialPlan status information as initial, which is 'PROCESSING'
	 * 3. also persist mission information
	 */
	@Override
	public void persistAerialPlan(String jsonString) {
		AerialPlan vo = gson.fromJson(jsonString, AerialPlan.class);
		Projects entity_projects = projectDao.findById(vo.getProjectId(),false);
		AerialPlans entity_aerialPlans = new AerialPlans();
		List<EquipmentPerson> equipmentPersonList = vo.getEquipmentsArray();
		try {
			BeanUtils.copyProperties(entity_aerialPlans, vo);
			AerialPlanFlow entity_aerialPlanFlow = gson.fromJson(jsonString, AerialPlanFlow.class);
			entity_aerialPlanFlow.setState(States.PROCESSING);
			entity_aerialPlans.setAerialPlanFlow(entity_aerialPlanFlow);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		for(int i =0;i<equipmentPersonList.size();i++){
			EquipmentPerson equipmentPerson = equipmentPersonList.get(i);
			if(equipmentPerson.getPersonId_1()!=null&& !"".equals(equipmentPerson.getPersonId_1())){
				persistMission(entity_aerialPlans,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_1()));
			}
			if(equipmentPerson.getPersonId_2()!=null&& !"".equals(equipmentPerson.getPersonId_2())){
				persistMission(entity_aerialPlans,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_2()));
			}
		}
		entity_aerialPlans.setProjects(entity_projects);
		aerialPlanDao.persist(entity_aerialPlans);
	}
	
	/**
	 * This function is for saving aerialActivity entity.
	 * The relationship between project-->aerialPlan-->aerialActivity is 1:n-->1:n-->1:n
	 * 1. aerialPlan : aerialActivity = 1:m
	 * 2. set aerialActivity state as 'PROCESSING'
	 * 3. also persist real mission information
	 */
	@Override
	public void persistAerialActivity(String jsonString) {
		AerialActivity vo = gson.fromJson(jsonString, AerialActivity.class);
		AerialPlans entity_aerialPlans = aerialPlanDao.findById(vo.getAerialPlanId(),false);
		AerialActivities entity_aerialActivities = new AerialActivities();
		List<EquipmentPersonInActivity> equipmentPersonList = vo.getEquipmentPersonArray();
		try {
			BeanUtils.copyProperties(entity_aerialActivities, vo);
			AerialActivityFlow entity_aerialActivityFlow = gson.fromJson(jsonString, AerialActivityFlow.class);
			entity_aerialActivityFlow.setState(States.PROCESSING);
			entity_aerialActivities.setAerialActivityFlow(entity_aerialActivityFlow);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		for(int i =0;i<equipmentPersonList.size();i++){
			EquipmentPersonInActivity equipmentPerson = equipmentPersonList.get(i);
			if(equipmentPerson.getPersonId_1()!=null&& !"".equals(equipmentPerson.getPersonId_1())){
				persistRealMission(entity_aerialActivities,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_1()));
			}
			if(equipmentPerson.getPersonId_2()!=null&& !"".equals(equipmentPerson.getPersonId_2())){
				persistRealMission(entity_aerialActivities,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_2()));
			}
		}
		entity_aerialActivities.setAerialPlans(entity_aerialPlans);
		aerialActivityDao.persist(entity_aerialActivities);
		
	}

	@Override
	public void updateProject(String jsonString) {
		Project vo = gson.fromJson(jsonString, Project.class);
		Projects entity_projects = projectDao.findById(vo.getProjectId(),false);
		try {
			BeanUtils.copyProperties(entity_projects, vo);
			if(entity_projects.getProjectFlow()!=null){
				BeanUtils.copyProperties( entity_projects.getProjectFlow(),vo);
			}else{
				ProjectFlow entity_projectFlow = new ProjectFlow();
				BeanUtils.copyProperties( entity_projectFlow,vo);
				entity_projects.setProjectFlow(entity_projectFlow);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		updateProjectState(entity_projects, stateMachine.Events.UPDATE);
		projectDao.update(entity_projects);
	}
	/**
	 * 1. update aerialPlan itself
	 * 2. use UPDATE event to change the aerial plan state
	 * 3. update mission data (delete and insert)
	 */
	@Override
	public void updateAerialPlan(String jsonString) {
		AerialPlan vo = gson.fromJson(jsonString, AerialPlan.class);
		AerialPlans entity_aerialPlans = aerialPlanDao.findById(vo.getAerialPlanId(),false);
		List<EquipmentPerson> equipmentPersonList = vo.getEquipmentsArray();
		try {
			BeanUtils.copyProperties(entity_aerialPlans, vo);
			AerialPlanFlow entity_aerialPlanFlow = gson.fromJson(jsonString, AerialPlanFlow.class);
			entity_aerialPlans.setAerialPlanFlow(entity_aerialPlanFlow);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Iterator<Missions> iterator = entity_aerialPlans.getMissionses().iterator();
		while(iterator.hasNext()){
			Missions entity_missions = iterator.next();
			personDao.deleteMission(entity_missions);
		}
		entity_aerialPlans.getMissionses().clear();
		for(int i =0;i<equipmentPersonList.size();i++){
			EquipmentPerson equipmentPerson = equipmentPersonList.get(i);
			if(equipmentPerson.getPersonId_1()!=null&& !"".equals(equipmentPerson.getPersonId_1())){
				persistMission(entity_aerialPlans,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_1()));
			}
			if(equipmentPerson.getPersonId_2()!=null&& !"".equals(equipmentPerson.getPersonId_2())){
				persistMission(entity_aerialPlans,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_2()));
			}
		}
		updateAerialPlanState(entity_aerialPlans, stateMachine.Events.UPDATE);
		aerialPlanDao.update(entity_aerialPlans);
	}
	
	/**
	 * 1. update aerialActivity itself
	 * 2. use UPDATE event to change the aerial activity state
	 * 3. update real mission data (delete and insert)
	 */
	@Override
	public void updateAerialActivity(String jsonString) {
		AerialActivity vo = gson.fromJson(jsonString, AerialActivity.class);
		AerialActivities entity_aerialActivities = aerialActivityDao.findById(vo.getAerialActivityId(),false);
		List<EquipmentPersonInActivity> equipmentPersonList = vo.getEquipmentPersonArray();
		try {
			BeanUtils.copyProperties(entity_aerialActivities, vo);
			AerialActivityFlow entity_aerialActivityFlow = gson.fromJson(jsonString, AerialActivityFlow.class);
			entity_aerialActivities.setAerialActivityFlow(entity_aerialActivityFlow);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Iterator<RealMissions> iterator = entity_aerialActivities.getRealmissionses().iterator();
		while(iterator.hasNext()){
			RealMissions entity_realMissions = iterator.next();
			personDao.deleteRealMission(entity_realMissions);
		}
		for(int i =0;i<equipmentPersonList.size();i++){
			EquipmentPersonInActivity equipmentPerson = equipmentPersonList.get(i);
			if(equipmentPerson.getPersonId_1()!=null&& !"".equals(equipmentPerson.getPersonId_1())){
				persistRealMission(entity_aerialActivities,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_1()));
			}
			if(equipmentPerson.getPersonId_2()!=null&& !"".equals(equipmentPerson.getPersonId_2())){
				persistRealMission(entity_aerialActivities,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_2()));
			}
		}
		updateAerialActivityState(entity_aerialActivities, stateMachine.Events.UPDATE);
		aerialActivityDao.update(entity_aerialActivities);
		
	}

	@Override
	public void deleteProject(Operations entity) {
		
	}

	@Override
	public String queryProjectById(Integer id) {
		Projects entity_projects = projectDao.findById(id,false);
		Project vo = new Project();
		try {
			BeanUtils.copyProperties(vo, entity_projects);
			if(entity_projects.getProjectFlow()!=null){
				BeanUtils.copyProperties(vo, entity_projects.getProjectFlow());
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
	public JsonArray queryProjects(String ename) {
		List<Projects> projectList;
		if ("".equals(ename) || ename == null) {
			projectList = projectDao.findAll();
		} else {
			projectList = projectDao.findByName(ename);
		}
		Iterator<Projects> iterator = projectList.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Projects entity_projects = (Projects) iterator.next();
			Project vo = new Project();
			try {
				BeanUtils.copyProperties(vo, entity_projects);
				if (entity_projects.getProjectFlow() != null) {
					BeanUtils.copyProperties(vo, entity_projects.getProjectFlow());
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
	public JsonArray queryAerialActivityByProjectId(Integer projectId) {
		List<AerialActivities> list_aerialActivities = aerialActivityDao.findByProjectId(projectId);
		Iterator<AerialActivities> iterator_aerialActivities = list_aerialActivities.iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_aerialActivities.hasNext()) {
			AerialActivity vo = new AerialActivity();
			AerialActivities entity_aerialActivities = (AerialActivities) iterator_aerialActivities.next();
			try {
				BeanUtils.copyProperties(vo, entity_aerialActivities);
				vo.setAerialPlanId(entity_aerialActivities.getAerialPlans().getAerialPlanId());
				if(entity_aerialActivities.getAerialActivityFlow()!=null){
					BeanUtils.copyProperties(vo, entity_aerialActivities.getAerialActivityFlow());
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

	/**
	 * 1. aerialPlan : aerialActivity = 1: m
	 * 2. get aerialActivity data, also get the aerialPlan data at the same time
	 * 3. get the real mission data from the aerialActivity ID
	 * 		3.1 for online update, this data must include default and selectable options
	 * 		3.2 aggregate to display in value object
	 */
	@Override
	public String queryAerialActivityByAerialActivityId(Integer aerialActivityId) {
		AerialActivities entity_aerialActivities = aerialActivityDao.findById(aerialActivityId,true);
		AerialActivity vo = new AerialActivity();
		try {
			BeanUtils.copyProperties(vo, entity_aerialActivities);
			BeanUtils.copyProperties(vo, entity_aerialActivities.getAerialPlans());
			if(entity_aerialActivities.getAerialActivityFlow()!=null){
				BeanUtils.copyProperties(vo, entity_aerialActivities.getAerialActivityFlow());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		List<Object[]> resultlist = aerialActivityDao.findRealMission(aerialActivityId);
		Map<Integer,LinkedList<String>> map = new HashMap<Integer,LinkedList<String>>();
		for(Object[] result : resultlist) {
		    Number equipmentId = (Number) result[0];
		    Number personId = (Number) result[1];
		    LinkedList<String> list;
			if(map.get(equipmentId)==null){
				list = new LinkedList<String>();
			}else{
				list = map.get(equipmentId);			
			}
			String[] availPersonIds = ((String)result[2]).split(",");
			String tag = "";
			for(int i=0;i<availPersonIds.length;i++){
				if(availPersonIds[i].equals(personId.toString())){
					tag+="selected "+availPersonIds[i]+",";
				}else{
					tag+=availPersonIds[i]+",";
				}
			}
			/*String tag ="<select>";
			for(int i=0;i<availPersonIds.length;i++){
				if(availPersonIds[i].equals(personId.toString())){
					tag+="<option value='"+availPersonIds[i]+"' selected>"+availPersonIds[i]+"</option>";					
				}else{
					tag+="<option value='"+availPersonIds[i]+"'>"+availPersonIds[i]+"</option>";
				}
			}
			tag+="<select>";*/
			list.add(tag);
			map.put(equipmentId.intValue(),list);
		}
		
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> iterator_keySet = keySet.iterator();
		List<EquipmentPersonInActivity> resultList = new LinkedList<EquipmentPersonInActivity>();
		while(iterator_keySet.hasNext()){
			Integer key = iterator_keySet.next();
			LinkedList<String> value = map.get(key);
			AerialActivity.EquipmentPersonInActivity vo_equipmentPerson = new AerialActivity().new EquipmentPersonInActivity();
			vo_equipmentPerson.setEquipmentId(key.toString());
			vo_equipmentPerson.setPersonId_1(value.get(0).toString());
			if(value.size()>1){
				vo_equipmentPerson.setPersonId_2(value.get(1).toString());
			}
			resultList.add(vo_equipmentPerson);
		}
		vo.setEquipmentPersonArray(resultList);
		
		
				
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}

	

	@Override
	public void check(Integer id) {
		updateProjectState(id, Events.CHECK);
		
	}

	@Override
	public void approve(Integer id) {
		updateProjectState(id, Events.APPROVE);
		
	}

	@Override
	public void reject(Integer id) {
		updateProjectState(id, Events.REJECT);
		
	}

	@Override
	public void delete(Integer id) {
		updateProjectState(id, Events.DELETE);
		
	}
	
	public Boolean updateProjectState(Integer id, stateMachine.Events event) {
		System.out.println("use updateProjectState!!!");
		Projects entity_projects = projectDao.findById(id,false);
		return projectPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_projects).build(),
						entity_projects.getProjectFlow().getState().name());
	}

	public Boolean updateProjectState(Projects entity_projects, stateMachine.Events event) {
		System.out.println("use updateProjectState!!!");
		return projectPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_projects).build(),
						entity_projects.getProjectFlow().getState().name());
	}

	@Override
	public void checkAerialActivity(Integer id) {
		updateAerialActivityState(id, Events.CHECK);
		
	}

	@Override
	public void approveAerialActivity(Integer id) {
		updateAerialActivityState(id, Events.APPROVE);
		
	}

	@Override
	public void rejectAerialActivity(Integer id) {
		updateAerialActivityState(id, Events.REJECT);
		
	}

	@Override
	public void deleteAerialActivity(Integer id) {
		updateAerialActivityState(id, Events.DELETE);
		
	}
	
	public Boolean updateAerialActivityState(Integer id, stateMachine.Events event) {
		System.out.println("use updateAerialActivityState!!!");
		AerialActivities entity_aerialActivities = aerialActivityDao.findById(id,false);
		return aerialactivityPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_aerialActivities).build(),
						entity_aerialActivities.getAerialActivityFlow().getState().name());
	}

	public Boolean updateAerialActivityState(AerialActivities entity_aerialActivities, stateMachine.Events event) {
		System.out.println("use updateAerialActivityState!!!");
		return aerialactivityPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_aerialActivities).build(),
						entity_aerialActivities.getAerialActivityFlow().getState().name());
	}
	
	

	@Override
	public void checkAerialPlan(Integer id) {
		updateAerialPlanState(id, Events.CHECK);
		
	}

	@Override
	public void approveAerialPlan(Integer id) {
		updateAerialPlanState(id, Events.APPROVE);
		
	}

	@Override
	public void rejectAerialPlan(Integer id) {
		updateAerialPlanState(id, Events.REJECT);
		
	}

	@Override
	public void deleteAerialPlan(Integer id) {
		updateAerialPlanState(id, Events.DELETE);
		
	}

	public Boolean updateAerialPlanState(Integer id, stateMachine.Events event) {
		System.out.println("use updateAerialPlanState!!!");
		AerialPlans entity_aerialPlans = aerialPlanDao.findById(id,false);
		return aerialplanPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_aerialPlans).build(),
						entity_aerialPlans.getAerialPlanFlow().getState().name());
	}

	public Boolean updateAerialPlanState(AerialPlans entity_aerialPlans, stateMachine.Events event) {
		System.out.println("use updateAerialPlanState!!!");
		return aerialplanPersistStateMachineHandler
				.handleEventWithState(
						MessageBuilder.withPayload(event.name())
								.setHeader(EntityConstants.entityHeader, entity_aerialPlans).build(),
						entity_aerialPlans.getAerialPlanFlow().getState().name());
	}

	

}

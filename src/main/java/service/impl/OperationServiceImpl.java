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
import entity.AerialPlans;
import entity.Equipments;
import entity.Missions;
import entity.Operations;
import entity.Persons;
import entity.Projects;
import entity.RealMissions;
import service.OperationService;
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

	@Override
	public void persistAerialPlan(String jsonString) {
		AerialPlan vo = gson.fromJson(jsonString, AerialPlan.class);
		Projects entity_projects = projectDao.findById(vo.getProjectId(),false);
		AerialPlans entity_aerialPlans = new AerialPlans();
		System.out.println(vo);
		List<EquipmentPerson> equipmentPersonList = vo.getEquipmentsArray();
		try {
			BeanUtils.copyProperties(entity_aerialPlans, vo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		for(int i =0;i<equipmentPersonList.size();i++){
			EquipmentPerson equipmentPerson = equipmentPersonList.get(i);
			persistMission(entity_aerialPlans,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_1()));
			persistMission(entity_aerialPlans,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_2()));
		}
		entity_aerialPlans.setProjects(entity_projects);
		aerialPlanDao.persist(entity_aerialPlans);
	}

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
	public String queryAerialPlanByAerialPlanId(Integer aerialPlanId) {
		AerialPlans entity_aerialPlans = aerialPlanDao.findById(aerialPlanId);
		AerialPlan vo = new AerialPlan();
		try {
			BeanUtils.copyProperties(vo, entity_aerialPlans);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";
		jsonString = gson.toJson(vo);
		return jsonString;
	}

	public OperationServiceImpl() {
		
	}

	

	@Override
	public void deleteAll() {

	}

	@Override
	public void persistProject(String jsonString) {
		Projects entity = gson.fromJson(jsonString, Projects.class);
		projectDao.persist(entity);
	}

	@Override
	public void updateProject(String jsonString) {
		Project vo = gson.fromJson(jsonString, Project.class);
		Projects entity = projectDao.findById(vo.getProjectId(),false);
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		projectDao.update(entity);
	}

	@Override
	public void deleteProject(Operations entity) {
		
	}

	@Override
	public String queryProjectById(Integer id) {
		Projects projects = projectDao.findById(id,true);
		// get through the result set
		String jsonString = "";
		JSONObject jsonObj = new JSONObject(projects);
		jsonString = jsonObj.toString();
		return jsonString;
	}

	@Override
	public List<Projects> queryProjects(String ename) {
		List<Projects> projectList;
		if ("".equals(ename) || ename == null) {
			projectList = projectDao.findAll();
		} else {
			projectList = projectDao.findByName(ename);
		}
		return projectList;
	}



	@Override
	public void updateAerialPlan(String jsonString) {
		// TODO Auto-generated method stub
		
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
	public String queryAerialActivityByAerialActivityId(Integer aerialActivityId) {
		AerialActivities entity_aerialActivities = aerialActivityDao.findById(aerialActivityId);
		AerialActivity vo = new AerialActivity();
		try {
			BeanUtils.copyProperties(vo, entity_aerialActivities);
			BeanUtils.copyProperties(vo, entity_aerialActivities.getAerialPlans());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		List<Object[]> resultlist = aerialActivityDao.findRealMission(aerialActivityId);
		System.out.println("list size::"+resultlist.size());
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
			String tag ="<select>";
			for(int i=0;i<availPersonIds.length;i++){
				if(availPersonIds[i].equals(personId.toString())){
					tag+="<option value='"+availPersonIds[i]+"' selected>"+availPersonIds[i]+"</option>";
				}else{
					tag+="<option value='"+availPersonIds[i]+"'>"+availPersonIds[i]+"</option>";
				}
			}
			tag+="<select>";
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

	/**
	 * This function is for saving aerialActivity entity.
	 * The relationship between project-->aerialPlan-->aerialActivity is 1:n-->1:n-->1:n
	 */
	@Override
	public void persistAerialActivity(String jsonString) {
		AerialActivity vo = gson.fromJson(jsonString, AerialActivity.class);
		System.out.println(jsonString+";"+vo);
		AerialPlans entity_aerialPlans = aerialPlanDao.findById(vo.getAerialPlanId());
		AerialActivities entity_aerialActivities = new AerialActivities();
		List<EquipmentPersonInActivity> equipmentPersonList = vo.getEquipmentPersonArray();
		try {
			BeanUtils.copyProperties(entity_aerialActivities, vo);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		for(int i =0;i<equipmentPersonList.size();i++){
			EquipmentPersonInActivity equipmentPerson = equipmentPersonList.get(i);
			persistRealMission(entity_aerialActivities,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_1()));
			persistRealMission(entity_aerialActivities,Integer.parseInt(equipmentPerson.getEquipmentId()),Integer.parseInt(equipmentPerson.getPersonId_2()));
		}
		entity_aerialActivities.setAerialPlans(entity_aerialPlans);
		aerialActivityDao.persist(entity_aerialActivities);
		
	}



}

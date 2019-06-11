package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.AerialPlanDao;
import dao.EquipmentDao;
import dao.PersonDao;
import dao.ProjectDao;
import entity.AerialPlans;
import entity.Equipments;
import entity.Missions;
import entity.Operations;
import entity.Persons;
import entity.Projects;
import service.OperationService;
import vo.AerialPlan;
import vo.AerialPlan.EquipmentPerson;

public class OperationServiceImpl implements OperationService {
	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

	@Autowired
	private AerialPlanDao aerialPlanDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private ProjectDao projectDao;		

	@Override
	public void persistAerialPlan(String jsonString) {
		AerialPlan vo = gson.fromJson(jsonString, AerialPlan.class);
		Projects entity_projects = projectDao.findById(vo.getProjectId());
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
		//entity_projects.getAerialPlanses().add(entity_aerialPlans);
		aerialPlanDao.persist(entity_aerialPlans);
		//projectDao.persist(entity);
	}

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


	@Override
	public List<AerialPlans> queryAerialPlans(String projectId) {
		return null;
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

	public ProjectDao getProjectDao() {
		return projectDao;
	}



	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}



	public OperationServiceImpl() {
		
	}

	

	@Override
	public void deleteAll() {

	}

	@Override
	public void persistProject(String jsonString) {
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Projects entity = gson.fromJson(jsonString, Projects.class);
		projectDao.persist(entity);
	}

	@Override
	public void updateProject(String jsonString) {
		Gson gson = new Gson();
		Projects vo = gson.fromJson(jsonString, Projects.class);
		Projects entity = projectDao.findById(vo.getProjectId());
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
		Projects projects = projectDao.findById(id);
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

}

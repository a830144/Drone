package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.AerialPlanDao;
import dao.ProjectDao;
import dao.impl.PersonDaoImpl;
import dao.impl.ProjectDaoImpl;
import entity.Equipments;
import entity.Operations;
import entity.Persons;
import entity.Projects;
import service.OperationService;
import util.HibernateUtil;

public class OperationServiceImpl implements OperationService {

	private ProjectDao projectDao;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String queryProjectById(Integer id) {
		Projects projects = projectDao.findById(id);
		// get through the result set
		JSONArray jsonArray = new JSONArray();
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
		// get through the result set
		/*JSONArray jsonArray = new JSONArray();
		String jsonString = "";
		while (iterator.hasNext()) {
			Projects projects = (Projects) iterator.next();
			JSONObject orderedJson = new JSONObject(projects);
			jsonArray.put(orderedJson);
		}

		if (jsonArray.length() > 0) {
			jsonString = jsonArray.toString();
		}

		
		return jsonString;*/
	}

}

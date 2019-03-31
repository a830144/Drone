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
import entity.Operations;
import entity.Persons;
import entity.Projects;
import service.OperationService;
import util.HibernateUtil;

public class OperationServiceImpl implements OperationService {

	private ProjectDao projectDao;
	private AerialPlanDao aerialPlanDao;

	public OperationServiceImpl() {
		projectDao = null;
		aerialPlanDao = null;
	}

	

	@Override
	public void deleteAll() {

	}

	@Override
	public void persistProject(String jsonString) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		projectDao = new ProjectDaoImpl();
		projectDao.setSessionFactory(sessionFactory);

		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Projects entity = gson.fromJson(jsonString, Projects.class);

		projectDao.persist(entity);

		tx.commit();
	}

	@Override
	public void modifyProject(String jsonString) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		projectDao = new ProjectDaoImpl();
		projectDao.setSessionFactory(sessionFactory);

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

		tx.commit();
	}

	@Override
	public void deleteProject(Operations entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String queryProjectById(Integer id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		projectDao = new ProjectDaoImpl();
		projectDao.setSessionFactory(sessionFactory);

		Projects projects = projectDao.findById(id);
		// get through the result set
		JSONArray jsonArray = new JSONArray();
		String jsonString = "";
		JSONObject orderedJson = new JSONObject(projects);
		jsonArray.put(orderedJson);

		if (jsonArray.length() > 0) {
			jsonString = jsonArray.toString();
		}

		tx.commit();
		return jsonString;
	}

	@Override
	public String queryProjects(String ename) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		projectDao = new ProjectDaoImpl();
		projectDao.setSessionFactory(sessionFactory);

		Iterator<Projects> iterator;
		if ("".equals(ename) || ename == null) {
			List<Projects> PersonsList = projectDao.findAll();
			iterator = PersonsList.iterator();
		} else {
			List<Projects> PersonsList = projectDao.findByName(ename);
			iterator = PersonsList.iterator();
		}
		tx.commit();
		// get through the result set
		JSONArray jsonArray = new JSONArray();
		String jsonString = "";
		while (iterator.hasNext()) {
			Projects projects = (Projects) iterator.next();
			JSONObject orderedJson = new JSONObject(projects);
			jsonArray.put(orderedJson);
		}

		if (jsonArray.length() > 0) {
			jsonString = jsonArray.toString();
		}

		
		return jsonString;
	}

}

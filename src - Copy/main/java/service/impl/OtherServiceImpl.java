package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.CodeDao;
import dao.EventDao;
import dao.LicenseDao;
import dao.TrainingDao;
import dao.UserDao;
import entity.Code;
import entity.Events;
import entity.Trainings;
import service.OtherService;
import vo.AerialActivity;
import vo.EventInPerson;
import vo.TrainingInPerson;
import vo.User;

@Service
public class OtherServiceImpl implements OtherService {
	
	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	
	@Autowired
	private LicenseDao licenseDao;
	
	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private CodeDao codeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void persistEvent(String jsonString) {		
		Events entity = gson.fromJson(jsonString, Events.class);
		eventDao.persist(entity);
	}
	
	@Override
	public void persistTraining(String jsonString) {		
		Trainings entity = gson.fromJson(jsonString, Trainings.class);
		trainingDao.persist(entity);
	}
	
	@Override
	public void persistCode(String jsonString) {
		Code entity = gson.fromJson(jsonString, Code.class);
		codeDao.persist(entity);	
	}

	@Override
	public String queryTrainingById(Integer id) {
		Trainings entity_trainings = trainingDao.findById(id);
		TrainingInPerson vo = new TrainingInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_trainings);
			
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
	public List<Trainings> queryTrainings(String ename) {
		List<Trainings> trainingsList;
		if ("".equals(ename) || ename == null) {
			trainingsList = trainingDao.findAll();
		} else {
			trainingsList = trainingDao.findByNameWildcard(ename);
		}
		return trainingsList;
	}

	@Override
	public String queryEventById(Integer id) {
		Events entity_events = eventDao.findById(id);
		EventInPerson vo = new EventInPerson();
		try {
			BeanUtils.copyProperties(vo, entity_events);
			
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
	public List<Events> queryEvents(String ename) {
		List<Events> eventsList;
		if ("".equals(ename) || ename == null) {
			eventsList = eventDao.findAll();
		} else {
			eventsList = eventDao.findByNameWildcard(ename);
		}
		return eventsList;
	}

	@Override
	public String queryCodeById(Integer id) {
		Code entity_code = codeDao.findById(id);
		String jsonString = "";
		jsonString = gson.toJson(entity_code);
		return jsonString;
	}

	@Override
	public List<Code> queryCodes(String type) {
		List<Code> eventsList;
		if ("".equals(type) || type == null) {
			eventsList = codeDao.findAll();
		} else {
			eventsList = codeDao.findByType(type);
		}
		return eventsList;
	}

	@Override
	public User accessIAM(Integer id, String password) {
		List<Object[]> resultlist = userDao.findByIdAndPassword(id, password);
		Map<String,HashSet<String>> functionOperations = new HashMap<String,HashSet<String>>();
		for(Object[] result : resultlist) {
			String functionName = (String) result[0];
			String[] types = (String[]) result[1];
			HashSet<String> typesSet = new HashSet<String>();
			
			for(int i=0;i<types.length;i++){
				typesSet.add(types[i]);
			}
			functionOperations.put(functionName, typesSet);
		}
		User vo = new User();
		vo.setUserId(id);
		vo.setFunctionOperations(functionOperations);
		return vo;
	}

	

}

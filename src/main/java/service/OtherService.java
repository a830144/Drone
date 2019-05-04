package service;

import java.util.List;

import entity.Code;
import entity.Events;
import entity.Trainings;

public interface OtherService {
	public String queryTrainingById(Integer id);

	public List<Trainings> queryTrainings(String ename);

	public String queryEventById(Integer id);

	public List<Events> queryEvents(String ename);
	
	public String queryCodeById(Integer id);

	public List<Code> queryCodes(String type);
	
	public void persistEvent(String jsonString);
	
	public void persistTraining(String jsonString);
	
	public void persistCode(String jsonString);

}

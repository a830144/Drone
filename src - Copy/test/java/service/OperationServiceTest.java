package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;
import vo.AerialPlan;

public class OperationServiceTest extends TestCase{

	
	public void testAddAerialPlan(){
		String json = "{"+"projectId"+":"+"1"+","+"equipmentsArray"+":"+"{'equipmentId':42,'personId_1':1,'personId_2':2},"+"}";
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		AerialPlan entity = gson.fromJson(json, AerialPlan.class);
	}
}

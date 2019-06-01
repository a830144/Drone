package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import entity.Code;
import entity.Events;
import entity.Trainings;
import service.OtherService;
import util.CodeTreeHierarchyHelper;
import util.DFS;
import util.TreeNode;
import vo.EventInPerson;
import vo.TrainingInPerson;

@Controller
public class OtherController {
	
	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
	@Autowired
	private OtherService otherService;
	
	@RequestMapping(value="/other/AddTrainingProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addTraining(String data) {
		otherService.persistTraining(data);		
	}
	
	@RequestMapping(value="/other/AddEventProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addEvent(String data) {
		otherService.persistEvent(data);		
	}
	
	@RequestMapping(value="/other/AddCodeProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addcode(String data) {
		otherService.persistCode(data);		
	}
	
	@RequestMapping(value="/other/ViewTrainingProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewTraining(String id) {
		String jsonString = otherService.queryTrainingById(new Integer(Integer.parseInt(id)));	
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	@RequestMapping(value="/other/ViewEventProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewEvent(String id) {
		String jsonString = otherService.queryEventById(new Integer(Integer.parseInt(id)));	
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	@RequestMapping(value="/other/ViewCodeProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewCode(String id) {
		String jsonString = otherService.queryCodeById(new Integer(Integer.parseInt(id)));	
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	
	@RequestMapping(value="/other/QueryTrainingProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryTraining(String name) {
		List<Trainings> list = otherService.queryTrainings(name);
		Iterator<Trainings> iterator = list.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Trainings entity_trainings = (Trainings) iterator.next();
			TrainingInPerson vo = new TrainingInPerson();
			try {
				BeanUtils.copyProperties(vo, entity_trainings);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			vo.setShowTrainingType();
			jsonArray.add(gson.toJson(vo));
		}

		String jsonString =jsonArray.toString();	
		return jsonString;
	}
	
	@RequestMapping(value="/other/QueryTrainingIDs" , method = {RequestMethod.POST})
	@ResponseBody
	public String showQueryTrainingIDs() {
		List<Trainings> list = otherService.queryTrainings("");
		Iterator<Trainings> iterator = list.iterator();
		String tag ="";
		while (iterator.hasNext()) {
			Trainings entity_trainings = (Trainings) iterator.next();
			tag += "<option value='"+entity_trainings.getTrainingId()+"'>"+entity_trainings.getTrainingId()+"</option>";
		}	
		System.out.println("tag::"+tag);
		return tag;
	}
	
	@RequestMapping(value="/other/QueryEventProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryEvent(String name) {
		List<Events> list = otherService.queryEvents(name);
		Iterator<Events> iterator = list.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Events entity_events = (Events) iterator.next();
			EventInPerson vo = new EventInPerson();
			try {
				BeanUtils.copyProperties(vo, entity_events);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			jsonArray.add(gson.toJson(vo));
		}

		String jsonString =jsonArray.toString();
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	@RequestMapping(value="/other/QueryEventIDs" , method = {RequestMethod.POST})
	@ResponseBody
	public String showQueryEventIDs() {
		List<Events> list = otherService.queryEvents("");
		Iterator<Events> iterator = list.iterator();
		String tag ="";
		while (iterator.hasNext()) {
			Events entity_events = (Events) iterator.next();
			tag += "<option value='"+entity_events.getEventId()+"'>"+entity_events.getEventId()+"</option>";
		}	
		System.out.println("tag::"+tag);
		return tag;
	}
	
	
	@RequestMapping(value="/other/QueryCodeProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryCode(String name) {
		List<Code> list = otherService.queryCodes(name);
		Iterator<Code> iterator = list.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Code entity_code = (Code) iterator.next();
			jsonArray.add(gson.toJson(entity_code));
		}

		String jsonString =jsonArray.toString();
		System.out.println("jsonString::"+jsonString);
		return jsonString;
	}
	
	
	
	@RequestMapping(value="/other/QueryCodes" , method = {RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String showQueryCode(String type,String code) {
		List<Code> list = otherService.queryCodes(type);
		
		CodeTreeHierarchyHelper CodeTreeHierarchyHelper = new CodeTreeHierarchyHelper();
		Map map = CodeTreeHierarchyHelper.codeTreeHierarchyMap(list);
		TreeNode root = CodeTreeHierarchyHelper.getTree().getRoot();
		DFS dfs = new DFS();
		dfs.preorderPrint(root);
		List dfsList = dfs.getList();
		String tag ="";
		for(int i =0;i<dfsList.size();i++){
			TreeNode treeNode = (TreeNode) dfsList.get(i);
			int level = (int)map.get(treeNode);
			String dash = "";
			for(int j=0;j<level;j++){
				dash += "-";
			}
			if(treeNode.getChildren().size()!=0){
				tag +=  "<option value='"+treeNode.getCode()+"' disabled>"+dash+treeNode.getCodeContent()+"</option>";
			}else{
				tag +=  "<option value='"+treeNode.getCode()+"'>"+dash+treeNode.getCodeContent()+"</option>";
			}
		}
		
		return tag;
	}
	
	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/other/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(MultipartHttpServletRequest request, HttpServletResponse response) {
		/*Iterator<String> itr =  request.getFileNames();
		System.out.println("name::"+request.getParameter("equipmentPhoto"));
		System.out.println("file::"+request.getFileNames().next());*/
	    String[] names = new String[1] ;
	    names[0] = request.getParameter("equipmentPhoto");
	    MultipartFile[] files = new MultipartFile[1];
	    files[0] = request.getFile(request.getFileNames().next());
		
	    
		return util.FileUploader.multiFiles(names, files,"equipments");

	}
}

package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;

import entity.Equipments;
import service.EquipmentService;
import vo.Equipment;

@Controller
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	
	
	@RequestMapping(value="/equipment/AddEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void addEquipment(String data) {
		System.out.println("AddEquipment");
		equipmentService.persist(data);		
	}
	
	@RequestMapping(value="/equipment/ModifyEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void modifyEquipment(String data) {
		System.out.println("ModifyEquipment");
		equipmentService.modifyEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/MaintainEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void maintainEquipment(String data) {
		System.out.println("MaintainEquipment");
		equipmentService.maintainEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/UpdateEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateEquipment(String data) {
		System.out.println("UpdateEquipment");
		equipmentService.updateEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/ViewEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewEquipment(String id) {
		System.out.println("ViewEquipmentProcess");
		String jsonString = equipmentService.queryEquipmentById(new Integer(Integer.parseInt(id)));	
		return jsonString;
	}
	
	@RequestMapping(value="/equipment/ViewMaintenance" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewMaintenance(String id) {
		System.out.println("viewMaintenance");
		JsonArray jsonArray = equipmentService.queryMaintenancesById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	@RequestMapping(value="/equipment/ViewModification" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewModification(String id) {
		System.out.println("viewModification");
		JsonArray jsonArray = equipmentService.queryModificationsById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	
	
	@RequestMapping(value="/equipment/QueryEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showQueryEquipment(String name) {
		System.out.println("QueryEquipment");
		List<Equipments> list = equipmentService.queryEquipments(name);
		Iterator<Equipments> iterator = list.iterator();
		JsonArray jsonArray = new JsonArray();
		while (iterator.hasNext()) {
			Equipments entity_equipments = (Equipments) iterator.next();
			Equipment vo = new Equipment();
			try {
				BeanUtils.copyProperties(vo, entity_equipments);
				if (entity_equipments.getEquipmentPerformance() != null) {
					BeanUtils.copyProperties(vo, entity_equipments.getEquipmentPerformance());
				}
				if (entity_equipments.getEquipmentFlow() != null) {
					BeanUtils.copyProperties(vo, entity_equipments.getEquipmentFlow());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			JSONObject jsonObj = new JSONObject(vo);
			jsonArray.add(jsonObj.toString());
		}

		String jsonString =jsonArray.toString();	
		return jsonString;
	}
}

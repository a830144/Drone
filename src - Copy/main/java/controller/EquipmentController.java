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
	
	@RequestMapping(value="/equipment/CheckEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkEquipment(Integer targetId) {
		System.out.println("CheckEquipment");
		equipmentService.check(targetId);		
	}
	
	@RequestMapping(value="/equipment/ApproveEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveEquipment(Integer targetId) {
		System.out.println("ApproveEquipment");
		equipmentService.approve(targetId);	
	}
	
	@RequestMapping(value="/equipment/RejectEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectEquipment(Integer targetId) {
		System.out.println("RejectEquipment");
		equipmentService.reject(targetId);	
	}
	
	@RequestMapping(value="/equipment/DeleteEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteEquipment(Integer id) {
		System.out.println("DeleteEquipment");
		equipmentService.delete(id);	
	}
	
	@RequestMapping(value="/maintain/CheckMaintainProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkMaintenance(Integer targetId) {
		System.out.println("CheckMaintenance");
		equipmentService.checkMaintenance(targetId);		
	}
	
	@RequestMapping(value="/maintain/ApproveMaintainProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveMaintenance(Integer targetId) {
		System.out.println("ApproveMaintenance");
		equipmentService.approveMaintenance(targetId);	
	}
	
	@RequestMapping(value="/maintain/RejectMaintainProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectMaintenance(Integer targetId) {
		System.out.println("RejectMaintenance");
		equipmentService.rejectMaintenance(targetId);	
	}
	
	@RequestMapping(value="/maintain/DeleteMaintainProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteMaintenance(Integer id) {
		System.out.println("DeleteMaintenance");
		equipmentService.deleteMaintenance(id);	
	}
	
	@RequestMapping(value="/modify/CheckModifyProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void checkModification(Integer targetId) {
		System.out.println("CheckModification");
		equipmentService.checkModification(targetId);		
	}
	
	@RequestMapping(value="/modify/ApproveModifyProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void approveModification(Integer targetId) {
		System.out.println("ApproveModification");
		equipmentService.approveModification(targetId);	
	}
	
	@RequestMapping(value="/modify/RejectModifyProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void rejectModification(Integer targetId) {
		System.out.println("RejectModification");
		equipmentService.rejectModification(targetId);	
	}
	
	@RequestMapping(value="/modify/DeleteModifyProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void deleteModification(Integer id) {
		System.out.println("DeleteModification");
		equipmentService.deleteModification(id);	
	}
	
	@RequestMapping(value="/equipment/MaintainEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void maintainEquipment(String data) {
		System.out.println("MaintainEquipment");
		equipmentService.maintainEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/ModifyEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void modifyEquipment(String data) {
		System.out.println("ModifyEquipment");
		equipmentService.modifyEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/UpdateEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateEquipment(String data) {
		System.out.println("UpdateEquipment");
		equipmentService.updateEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/UpdateMaintainEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateMaintainEquipment(String data) {
		System.out.println("UpdateMaintainEquipment");
		equipmentService.updateMaintainEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/UpdateModifyEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public void updateModifyEquipment(String data) {
		System.out.println("UpdateModifyEquipment");
		equipmentService.updateModifyEquipment(data);		
	}
	
	@RequestMapping(value="/equipment/ViewEquipmentProcess" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewEquipment(String id) {
		System.out.println("ViewEquipmentProcess");
		String jsonString = equipmentService.queryEquipmentById(new Integer(Integer.parseInt(id)));	
		return jsonString;
	}
	//view maintenance by equipmentId
	@RequestMapping(value="/equipment/ViewMaintenance" , method = {RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String viewMaintenance(String id) {
		System.out.println("viewMaintenance");
		JsonArray jsonArray = equipmentService.queryMaintenancesById(new Integer(id));
		String jsonString =jsonArray.toString();
		return jsonString;
	}
	//view maintenance by maintenanceId
	@RequestMapping(value = "/equipment/ViewMaintenanceByMaintenanceId", method = {RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewMaintenanceByMaintenanceId(String id) {
		String jsonString = equipmentService.queryMaintenanceByMaintenanceId(new Integer(Integer.parseInt(id)));
		System.out.println("ViewMaintenanceByMaintenanceId::" + jsonString);
		return jsonString;
	}
	//view Modification by ModificationId
	@RequestMapping(value = "/equipment/ViewModificationByModificationId", method = {RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewModificationByModificationId(String id) {
		String jsonString = equipmentService.queryModificationByModificationId(new Integer(Integer.parseInt(id)));
		System.out.println("ViewModificationByModificationId::" + jsonString);
		return jsonString;
	}
	
	//view modification by equipmentId
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
	public String showQueryEquipment(String name,String type,String aerialPlanId) {
		System.out.println("showQueryEquipment :: "+ aerialPlanId);
		List<Equipments> list=null;
		if(name != null){
			list = equipmentService.queryEquipments(name);
		}
		if(!"".equals(type)&& type!=null){
			list = equipmentService.queryEquipmentsByType(type);
		}
		if(!"".equals(aerialPlanId)&& aerialPlanId!=null){
			list = equipmentService.queryEquipmentsByAerialPlanId(Integer.parseInt(aerialPlanId));
		}
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

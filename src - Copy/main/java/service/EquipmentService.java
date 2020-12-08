package service;

import java.util.List;

import com.google.gson.JsonArray;

import entity.Equipments;

public interface EquipmentService {
	
	public void persist(String jsonString);

	public void updateEquipment(String jsonString);
	
	public void maintainEquipment(String jsonString);
	
	public void modifyEquipment(String jsonString);
	
	public void updateMaintainEquipment(String jsonString);
	
	public void updateModifyEquipment(String jsonString);

	public String queryEquipmentById(Integer id);	
	
	public List<Equipments> queryEquipments(String ename);
	
	public List<Equipments> queryEquipmentsByType(String type);
	
	public List<Equipments> queryEquipmentsByAerialPlanId(Integer aerialPlanId);
	
	public JsonArray queryMaintenancesById(Integer id);
	
	public JsonArray queryModificationsById(Integer id);
	
	public String queryMaintenanceByMaintenanceId(Integer id);
	
	public String queryModificationByModificationId(Integer id);
	
	public void check(Integer id);
	
	public void approve(Integer id);
	
	public void reject(Integer id);
	
	public void delete(Integer id);
	
	public void checkMaintenance(Integer id);
	
	public void approveMaintenance(Integer id);
	
	public void rejectMaintenance(Integer id);
	
	public void deleteMaintenance(Integer id);
	
	public void checkModification(Integer id);
	
	public void approveModification(Integer id);
	
	public void rejectModification(Integer id);
	
	public void deleteModification(Integer id);
}

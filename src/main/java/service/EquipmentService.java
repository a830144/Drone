package service;

import java.util.List;

import com.google.gson.JsonArray;

import dao.EquipmentDao;
import entity.Equipments;

public interface EquipmentService {
	public void setEquipmentDao(EquipmentDao equipmentDao);
	
	public void persist(String jsonString);

	public void updateEquipment(String jsonString);
	
	public void maintainEquipment(String jsonString);
	
	public void modifyEquipment(String jsonString);
	
	public void delete(Equipments entity);
	
	public void deleteAll();

	public String queryEquipmentById(Integer id);	
	
	public List<Equipments> queryEquipments(String ename);
	
	public JsonArray queryMaintenancesById(Integer id);
	
	public JsonArray queryModificationsById(Integer id);
	
}

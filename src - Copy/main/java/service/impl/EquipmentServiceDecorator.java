package service.impl;

import java.util.List;

import com.google.gson.JsonArray;

import dao.EquipmentDao;
import entity.Equipments;
import service.EquipmentService;

public abstract class EquipmentServiceDecorator implements EquipmentService{

	public EquipmentServiceDecorator(EquipmentService equipmentService) {
		super();
		this.equipmentService = equipmentService;
	}

	private EquipmentService equipmentService;
	

	@Override
	public void setEquipmentDao(EquipmentDao equipmentDao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persist(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEquipment(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maintainEquipment(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyEquipment(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Equipments entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String queryEquipmentById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipments> queryEquipments(String ename) {		

		return equipmentService.queryEquipments(ename);
	}

	@Override
	public JsonArray queryMaintenancesById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonArray queryModificationsById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

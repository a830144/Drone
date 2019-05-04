package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.EquipmentDao;
import dao.MaintenanceDao;
import dao.ModificationDao;
import entity.EquipmentFlow;
import entity.EquipmentPerformance;
import entity.Equipments;
import entity.MaintenanceDetail;
import entity.Maintenances;
import entity.ModificationDetail;
import entity.Modifications;
import service.EquipmentService;
import stateMachine.Events;
import stateMachine.States;
import util.EntityConstants;
import vo.BaseEquipmentAttachment.TempAttach;
import vo.Equipment;
import vo.MaintainEquipment;
import vo.ModifyEquipment;

public class EquipmentServiceImpl implements EquipmentService {
	
	private Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

	private EquipmentDao equipmentDao;
	private MaintenanceDao maintenanceDao;
	private ModificationDao modificationDao;

	@Autowired
	private PersistStateMachineHandler persistStateMachineHandler;

	public EquipmentDao getEquipmentDao() {
		return equipmentDao;
	}

	public void setEquipmentDao(EquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}

	public MaintenanceDao getMaintenanceDao() {
		return maintenanceDao;
	}

	public void setMaintenanceDao(MaintenanceDao maintenanceDao) {
		this.maintenanceDao = maintenanceDao;
	}

	public ModificationDao getModificationDao() {
		return modificationDao;
	}

	public void setModificationDao(ModificationDao modificationDao) {
		this.modificationDao = modificationDao;
	}
	
	public EquipmentServiceImpl() {
		
	}

	@Override
	public void persist(String jsonString) {
		Equipments entity = gson.fromJson(jsonString, Equipments.class);
		EquipmentPerformance entity_equipmentPerformance = gson.fromJson(jsonString, EquipmentPerformance.class);
		EquipmentFlow entity_equipmentFlow = gson.fromJson(jsonString, EquipmentFlow.class);
		entity.setEquipmentPerformance(entity_equipmentPerformance);
		entity_equipmentFlow.setState(States.PROCESSING);
		entity.setEquipmentFlow(entity_equipmentFlow);
		equipmentDao.persist(entity);
	}

	@Override
	public void updateEquipment(String jsonString) {
			Equipment vo = gson.fromJson(jsonString, Equipment.class);
			Equipments entity_equipments = equipmentDao.findById(vo.getEquipmentId());
			try {
				BeanUtils.copyProperties(entity_equipments, vo);
				if(entity_equipments.getEquipmentPerformance()!=null){
					BeanUtils.copyProperties( entity_equipments.getEquipmentPerformance(),vo);
				}else{
					EquipmentPerformance entity_equipmentPerformance = new EquipmentPerformance();
					BeanUtils.copyProperties( entity_equipmentPerformance,vo);
					entity_equipments.setEquipmentPerformance(entity_equipmentPerformance);
				}
				if(entity_equipments.getEquipmentFlow()!=null){
					BeanUtils.copyProperties( entity_equipments.getEquipmentFlow(),vo);
				}else{
					EquipmentFlow entity_equipmentFlow = new EquipmentFlow();
					BeanUtils.copyProperties( entity_equipmentFlow,vo);
					entity_equipments.setEquipmentFlow(entity_equipmentFlow);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			updateState(entity_equipments, Events.UPDATE);
			equipmentDao.update(entity_equipments);
			
	}
	@Override
	public void updateMaintainEquipment(String jsonString) {
		MaintainEquipment vo = gson.fromJson(jsonString, MaintainEquipment.class);
		Maintenances entity_maintenances = maintenanceDao.findById(vo.getMaintenanceId());
		try {
			BeanUtils.copyProperties(entity_maintenances, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int rowcount = maintenanceDao.deleteAllDetailsByMaintenanceId(vo.getMaintenanceId());
		System.out.println("delete detail::"+rowcount);
		Map<String, TempAttach> map = vo.getResultHashMap();
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			TempAttach value = map.get(key);
			MaintenanceDetail entity_maintenanceDetail = new DetailBuilder().setSeq(++i).buildMaintainDetail(value);			
			entity_maintenances.getMaintenanceDetails().add(entity_maintenanceDetail);
			entity_maintenanceDetail.setMaintenances(entity_maintenances);
		}
		maintenanceDao.update(entity_maintenances);
		
	}

	@Override
	public void updateModifyEquipment(String jsonString) {
		ModifyEquipment vo = gson.fromJson(jsonString, ModifyEquipment.class);
		Modifications entity_modifications = modificationDao.findById(vo.getModificationId());
		try {
			BeanUtils.copyProperties(entity_modifications, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int rowcount = modificationDao.deleteAllDetailsByMaintenanceId(vo.getModificationId());
		System.out.println("delete detail::"+rowcount);
		Map<String, TempAttach> map = vo.getResultHashMap();
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			TempAttach value = map.get(key);
			ModificationDetail entity_modificationDetail = new DetailBuilder().setSeq(++i).buildModifyDetail(value);	

			entity_modifications.getModificationDetails().add(entity_modificationDetail);
			entity_modificationDetail.setModifications(entity_modifications);
		}
		modificationDao.update(entity_modifications);
		
	}
	
	@Override
	public String queryEquipmentById(Integer id) {
		Equipments entity_equipments = equipmentDao.findById(id);

		Equipment vo = new Equipment();
		try {
			BeanUtils.copyProperties(vo, entity_equipments);
			if(entity_equipments.getEquipmentPerformance()!=null){
				BeanUtils.copyProperties(vo, entity_equipments.getEquipmentPerformance());
			}
			if(entity_equipments.getEquipmentFlow()!=null){
				BeanUtils.copyProperties(vo, entity_equipments.getEquipmentFlow());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String jsonString = "";
		jsonString = gson.toJson(vo);

		return jsonString;
	}
	
	public List<Equipments> queryEquipments(String productName) {
		List<Equipments> equipmentsList;
		if ("".equals(productName) || productName == null) {
			equipmentsList = equipmentDao.findAll();
		} else {
			equipmentsList = equipmentDao.findByName(productName);
		}
		return equipmentsList;
	}

	@Override
	public void maintainEquipment(String jsonString) {		
		MaintainEquipment vo = gson.fromJson(jsonString, MaintainEquipment.class);
		Equipments entity_equipments = equipmentDao.findById(vo.getEquipmentId());
		Maintenances entity_maintenances = new Maintenances();
		try {
			BeanUtils.copyProperties(entity_maintenances, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		entity_equipments.getMaintenanceses().add(entity_maintenances);
		entity_maintenances.setEquipments(entity_equipments);
		Map<String, TempAttach> map = vo.getResultHashMap();
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			TempAttach value = map.get(key);
			MaintenanceDetail entity_maintenanceDetail = new DetailBuilder().setSeq(++i).buildMaintainDetail(value);			
			entity_maintenances.getMaintenanceDetails().add(entity_maintenanceDetail);
			entity_maintenanceDetail.setMaintenances(entity_maintenances);
		}
		equipmentDao.persist(entity_equipments);
	}
	
	@Override
	public void modifyEquipment(String jsonString) {
		ModifyEquipment vo = gson.fromJson(jsonString, ModifyEquipment.class);
		Equipments entity_equipments = equipmentDao.findById(vo.getEquipmentId());
		Modifications entity_modifications = new Modifications();
		try {
			BeanUtils.copyProperties(entity_modifications, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		entity_equipments.getModificationses().add(entity_modifications);
		entity_modifications.setEquipments(entity_equipments);
		
		Map<String,TempAttach> map = vo.getResultHashMap();
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while(iterator.hasNext()){
			String key =iterator.next().toString();
			TempAttach value = map.get(key);
			ModificationDetail entity_modificationDetail = new DetailBuilder().setSeq(++i).buildModifyDetail(value);	

			entity_modifications.getModificationDetails().add(entity_modificationDetail);
			entity_modificationDetail.setModifications(entity_modifications);
		}	
		equipmentDao.persist(entity_equipments);		
	}


	private MaintainEquipment getMaintenanceDetail(Maintenances entity_maintenances, MaintainEquipment vo) {
		Iterator<MaintenanceDetail> iter = entity_maintenances.getMaintenanceDetails().iterator();
		while (iter.hasNext()) {
			MaintenanceDetail detail = (MaintenanceDetail) iter.next();
			if (detail.getSeq() == 1) {
				vo.setAirframe(detail.getStatus());
				vo.setAirframe_comment(detail.getComment());
			} else if (detail.getSeq() == 2) {
				vo.setPropulsion(detail.getStatus());
				vo.setPropulsion_comment(detail.getComment());
			} else if (detail.getSeq() == 3) {
				vo.setBattery(detail.getStatus());
				vo.setBattery_comment(detail.getComment());
			} else if (detail.getSeq() == 4) {
				vo.setController(detail.getStatus());
				vo.setController_comment(detail.getComment());
			} else if (detail.getSeq() == 5) {
				vo.setPayload(detail.getStatus());
				vo.setPayload_comment(detail.getComment());
			} else if (detail.getSeq() == 6) {
				vo.setOthers_comment(detail.getComment());
			}
		}
		return vo;
	}
	
	private ModifyEquipment getModifyDetail(Modifications entity_modifications, ModifyEquipment vo) {
		Iterator<ModificationDetail> iter = entity_modifications.getModificationDetails().iterator();
		while (iter.hasNext()) {
			ModificationDetail detail = (ModificationDetail) iter.next();
			if (detail.getSeq() == 1) {
				vo.setAirframe(detail.getStatus());
				vo.setAirframe_comment(detail.getComment());
			} else if (detail.getSeq() == 2) {
				vo.setPropulsion(detail.getStatus());
				vo.setPropulsion_comment(detail.getComment());
			} else if (detail.getSeq() == 3) {
				vo.setBattery(detail.getStatus());
				vo.setBattery_comment(detail.getComment());
			} else if (detail.getSeq() == 4) {
				vo.setController(detail.getStatus());
				vo.setController_comment(detail.getComment());
			} else if (detail.getSeq() == 5) {
				vo.setPayload(detail.getStatus());
				vo.setPayload_comment(detail.getComment());
			} else if (detail.getSeq() == 6) {
				vo.setOthers_comment(detail.getComment());
			}
		}
		return vo;
	}

	

	@Override
	public JsonArray queryMaintenancesById(Integer id) {
		Equipments entity_equipments = equipmentDao.findById(id);
		Iterator<Maintenances> iterator_maintenances = entity_equipments.getMaintenanceses().iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_maintenances.hasNext()) {
			MaintainEquipment vo = new MaintainEquipment();
			Maintenances entity_maintenances = (Maintenances) iterator_maintenances.next();
			try {
				BeanUtils.copyProperties(vo, entity_maintenances);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			vo = getMaintenanceDetail(entity_maintenances, vo);
			vo.setShowMaintenanceType();
			jsonArray.add(gson.toJson(vo));

		}
		return jsonArray;
	}
	
	@Override
	public String queryMaintenanceByMaintenanceId(Integer id) {
		Maintenances entity_maintenances = maintenanceDao.findById(id);

		MaintainEquipment vo = new MaintainEquipment();
		try {
			BeanUtils.copyProperties(vo, entity_maintenances);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		vo = getMaintenanceDetail(entity_maintenances, vo);
		String jsonString = "";
		jsonString = gson.toJson(vo);

		return jsonString;
	}

	@Override
	public JsonArray queryModificationsById(Integer id) {
		Equipments entity_equipments = equipmentDao.findById(id);
		Iterator<Modifications> iterator_modifications = entity_equipments.getModificationses().iterator();
		JsonArray jsonArray = new JsonArray();

		while (iterator_modifications.hasNext()) {
			ModifyEquipment vo = new ModifyEquipment();
			Modifications entity_modifications = (Modifications) iterator_modifications.next();
			try {
				BeanUtils.copyProperties(vo, entity_modifications);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			vo = getModifyDetail(entity_modifications, vo);

			jsonArray.add(gson.toJson(vo));
		}
		return jsonArray;
	}
	@Override
	public String queryModificationByModificationId(Integer id) {
		Modifications entity_modifications = modificationDao.findById(id);

		ModifyEquipment vo = new ModifyEquipment();
		try {
			BeanUtils.copyProperties(vo, entity_modifications);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		vo = getModifyDetail(entity_modifications, vo);
		String jsonString = "";
		jsonString = gson.toJson(vo);

		return jsonString;
	}

	@Override
	public void check(Integer id){
		updateState(id, Events.CHECK);
	}
	
	@Override
	public void approve(Integer id){
		updateState(id, Events.APPROVE);
	}
	
	@Override
	public void reject(Integer id){
		updateState(id, Events.REJECT);
	}
	
	@Override
	public void delete(Integer id){
		updateState(id, Events.DELETE);
	}
	
	public Boolean updateState(Integer id, stateMachine.Events event) {
		Equipments entity_equipments = equipmentDao.findById(id);
	    return persistStateMachineHandler.handleEventWithState(
	        MessageBuilder.withPayload(event.name()).setHeader(EntityConstants.entityHeader, entity_equipments).build(),
	        entity_equipments.getEquipmentFlow().getState().name()
	    );
	  }
	
	public Boolean updateState(Equipments entity_equipments, stateMachine.Events event) {
	    return persistStateMachineHandler.handleEventWithState(
	        MessageBuilder.withPayload(event.name()).setHeader(EntityConstants.entityHeader, entity_equipments).build(),
	        entity_equipments.getEquipmentFlow().getState().name()
	    );
	  }
}

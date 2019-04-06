package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import dao.EquipmentDao;
import entity.EquipmentFlow;
import entity.EquipmentPerformance;
import entity.Equipments;
import entity.MaintenanceDetail;
import entity.Maintenances;
import entity.ModificationDetail;
import entity.Modifications;
import service.EquipmentService;
import vo.BaseEquipmentAttachment.TempAttach;
import vo.Equipment;
import vo.MaintainEquipment;
import vo.ModifyEquipment;

public class EquipmentServiceImpl implements EquipmentService {

	private EquipmentDao equipmentDao;

	public EquipmentDao getEquipmentDao() {
		return equipmentDao;
	}

	public void setEquipmentDao(EquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}

	public EquipmentServiceImpl() {
		
	}

	@Override
	public void persist(String jsonString) {
		Gson gson = new Gson();
		Equipments entity = gson.fromJson(jsonString, Equipments.class);
		EquipmentPerformance entity_equipmentPerformance = gson.fromJson(jsonString, EquipmentPerformance.class);
		EquipmentFlow entity_equipmentFlow = gson.fromJson(jsonString, EquipmentFlow.class);
		entity.setEquipmentPerformance(entity_equipmentPerformance);
		entity.setEquipmentFlow(entity_equipmentFlow);
		equipmentDao.persist(entity);
	}

	@Override
	public void updateEquipment(String jsonString) {

			Gson gson = new Gson();
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

			equipmentDao.update(entity_equipments);
	}

	
	@Override
	public String queryEquipmentById(Integer id) {
		Equipments entity_equipments = equipmentDao.findById(id);
		//sessionFactory.getCurrentSession().evict(entity_equipments);
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

		JSONObject jsonObj = new JSONObject(vo);
		jsonString = jsonObj.toString();

		return jsonString;
	}

	@Override
	public void delete(Equipments entity) {

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
	public void deleteAll() {

	}

	@Override
	public void maintainEquipment(String jsonString) {

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
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
			//entity_maintenanceDetail.setSeq(++i);
			entity_maintenances.getMaintenanceDetails().add(entity_maintenanceDetail);
			entity_maintenanceDetail.setMaintenances(entity_maintenances);
		}

		equipmentDao.persist(entity_equipments);

	}
	
	@Override
	public void modifyEquipment(String jsonString) {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
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
			//entity_modificationDetail.setSeq(++i);
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
			} else if (detail.getSeq() == 2) {
				vo.setPropulsion(detail.getStatus());
			} else if (detail.getSeq() == 3) {
				vo.setBattery(detail.getStatus());
			} else if (detail.getSeq() == 4) {
				vo.setController(detail.getStatus());
			} else if (detail.getSeq() == 5) {
				vo.setPayload(detail.getStatus());
			} else if (detail.getSeq() == 6) {
				vo.setOthers(detail.getComment());
			}
		}
		return vo;
	}
	
	private ModifyEquipment getModifyDetail(Modifications entity_modifications, ModifyEquipment vo) {
		Iterator<ModificationDetail> iter = entity_modifications.getModificationDetails().iterator();
		while (iter.hasNext()) {
			ModificationDetail detail = (ModificationDetail) iter.next();
			if (detail.getDetailType() == 1) {
				vo.setAirframe(detail.getStatus());
			} else if (detail.getDetailType() == 2) {
				vo.setPropulsion(detail.getStatus());
			} else if (detail.getDetailType() == 3) {
				vo.setBattery(detail.getStatus());
			} else if (detail.getDetailType() == 4) {
				vo.setController(detail.getStatus());
			} else if (detail.getDetailType() == 5) {
				vo.setPayload(detail.getStatus());
			} else if (detail.getDetailType() == 6) {
				vo.setOthers(detail.getComment());
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
			JSONObject jsonObj = new JSONObject(vo);
			jsonArray.add(jsonObj.toString());

		}
		return jsonArray;
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
			JSONObject jsonObj = new JSONObject(vo);
			jsonArray.add(jsonObj.toString());

		}
		return jsonArray;
	}

}

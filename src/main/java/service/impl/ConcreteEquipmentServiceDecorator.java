package service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import entity.Equipments;
import service.EquipmentService;
import vo.Equipment;

public class ConcreteEquipmentServiceDecorator extends EquipmentServiceDecorator{


	public ConcreteEquipmentServiceDecorator(EquipmentService equipmentService) {
		super(equipmentService);
	}

	public JsonArray queryEquipmentsJsonSring(String ename) {
		List<Equipments> list = this.queryEquipments(ename);
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

		return jsonArray;
	}
}

package vo;

import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;
import vo.BaseEquipmentAttachment.TempAttach;

public class ModifyEquipmentTest extends TestCase{
	public void testModifyEquipment(){
		ModifyEquipment vo = new ModifyEquipment();
		vo.setAirframe("M");
		vo.setBattery("M");
		vo.setController("M");
		Map<String,TempAttach> map = vo.getResultHashMap();
		Iterator iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String key =iterator.next().toString();
			TempAttach value = map.get(key);
			System.out.println(key+"::"+value);
		}
		
	}
}

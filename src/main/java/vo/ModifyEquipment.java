package vo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ModifyEquipment extends BaseEquipmentAttachment{
	private String airframe;
	private String propulsion;
	private String battery;
	private String controller;
	private String payload;
	public String getAirframe() {
		return airframe;
	}
	public void setAirframe(String airframe) {
		this.airframe = airframe;
	}
	public String getPropulsion() {
		return propulsion;
	}
	public void setPropulsion(String propulsion) {
		this.propulsion = propulsion;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getAirframe_comment() {
		return airframe_comment;
	}
	public void setAirframe_comment(String airframe_comment) {
		this.airframe_comment = airframe_comment;
	}
	public String getPropulsion_comment() {
		return propulsion_comment;
	}
	public void setPropulsion_comment(String propulsion_comment) {
		this.propulsion_comment = propulsion_comment;
	}
	public String getBattery_comment() {
		return battery_comment;
	}
	public void setBattery_comment(String battery_comment) {
		this.battery_comment = battery_comment;
	}
	public String getController_comment() {
		return controller_comment;
	}
	public void setController_comment(String controller_comment) {
		this.controller_comment = controller_comment;
	}
	public String getPayload_comment() {
		return payload_comment;
	}
	public void setPayload_comment(String payload_comment) {
		this.payload_comment = payload_comment;
	}
	public String getOthers_comment() {
		return others_comment;
	}
	public void setOthers_comment(String others_comment) {
		this.others_comment = others_comment;
	}
	public byte[] getAirframe_photo() {
		return airframe_photo;
	}
	public void setAirframe_photo(byte[] airframe_photo) {
		this.airframe_photo = airframe_photo;
	}
	public byte[] getPropulsion_photo() {
		return propulsion_photo;
	}
	public void setPropulsion_photo(byte[] propulsion_photo) {
		this.propulsion_photo = propulsion_photo;
	}
	public byte[] getBattery_photo() {
		return battery_photo;
	}
	public void setBattery_photo(byte[] battery_photo) {
		this.battery_photo = battery_photo;
	}
	public byte[] getController_photo() {
		return controller_photo;
	}
	public void setController_photo(byte[] controller_photo) {
		this.controller_photo = controller_photo;
	}
	public byte[] getPayload_photo() {
		return payload_photo;
	}
	public void setPayload_photo(byte[] payload_photo) {
		this.payload_photo = payload_photo;
	}
	public byte[] getOthers_photo() {
		return others_photo;
	}
	public void setOthers_photo(byte[] others_photo) {
		this.others_photo = others_photo;
	}
	private String others;
	
	private String airframe_comment;
	private String propulsion_comment;
	private String battery_comment;
	private String controller_comment;
	private String payload_comment;
	private String others_comment;
	
	private byte[] airframe_photo;
	private byte[] propulsion_photo;
	private byte[] battery_photo;
	private byte[] controller_photo;
	private byte[] payload_photo;
	private byte[] others_photo;
	
	public Map<String,TempAttach> getResultHashMap(){
		Map<String,TempAttach> map = new HashMap<String,TempAttach>();
		if(!this.getClass().getSuperclass().equals(Object.class)){
			Class<?> c = this.getClass();
			Field[] fields = c.getDeclaredFields();
		    for(int i =0;i<fields.length;i++){		    	
		    	Field field = fields[i];
		    	field.setAccessible(true);
		    	String key = null;
		    	TempAttach tempAttach = null;
		    	String value_status = null;
		    	String value_comment = null;
		    	byte[] modificationPhoto = null;
		    	
		    		try {
						key = detailMap.get(field.getName());		    		
						if(key!=null){
							value_status = (String)field.get(this);
							value_comment = (String)c.getDeclaredField(field.getName()+"_comment").get(this);
							modificationPhoto = (byte[])c.getDeclaredField(field.getName()+"_photo").get(this);
							tempAttach = new TempAttach(key,value_status,value_comment,modificationPhoto);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
							    	
		    	if(key!=null && tempAttach!=null){
		    		map.put(key, tempAttach);
		    	}
		    }
		}	    
	    return map;
	}
}

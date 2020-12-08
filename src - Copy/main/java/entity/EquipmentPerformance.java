package entity;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EquipmentPerformance {
	
	private Float wingWidth;
	private Float equipmentLength;
	private String propulsionSystem;
	@Override
	public String toString() {
		return "EquipmentPerformance [wingWidth=" + wingWidth + ", equipmentLength=" + equipmentLength
				+ ", propulsionSystem=" + propulsionSystem + ", powerType=" + powerType + ", loading=" + loading
				+ ", speed=" + speed + ", maxHeight=" + maxHeight + ", airTime=" + airTime + ", maxDistance="
				+ maxDistance + ", antiWind=" + antiWind + ", note=" + note + "]";
	}
	private String powerType;
	private Float loading;
	private Float speed;
	private Float maxHeight;
	private Float airTime;
	private Float maxDistance;
	private Float antiWind;
	private String note;
	
	@Column(name = "Wing_Width", precision = 12, scale = 0)
	public Float getWingWidth() {
		return wingWidth;
	}
	public void setWingWidth(Float wingWidth) {
		this.wingWidth = wingWidth;
	}
	
	@Column(name = "Equipment_Length", precision = 12, scale = 0)
	public Float getEquipmentLength() {
		return this.equipmentLength;
	}

	public void setEquipmentLength(Float equipmentLength) {
		this.equipmentLength = equipmentLength;
	}
	
	@Column(name = "Propulsion_System", length = 20)
	public String getPropulsionSystem() {
		return propulsionSystem;
	}
	public void setPropulsionSystem(String propulsionSystem) {
		this.propulsionSystem = propulsionSystem;
	}
	
	@Column(name = "Power_Type", length = 20)
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	
	@Column(name = "Loading", precision = 12, scale = 0)
	public Float getLoading() {
		return loading;
	}
	public void setLoading(Float loading) {
		this.loading = loading;
	}
	
	@Column(name = "Speed", precision = 12, scale = 0)
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	
	@Column(name = "Max_height", precision = 12, scale = 0)
	public Float getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(Float maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	@Column(name = "Air_Time", precision = 12, scale = 0)
	public Float getAirTime() {
		return airTime;
	}
	public void setAirTime(Float airTime) {
		this.airTime = airTime;
	}
	
	@Column(name = "Max_Distance", precision = 12, scale = 0)
	public Float getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(Float maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	@Column(name = "Anti_Wind", precision = 12, scale = 0)
	public Float getAntiWind() {
		return antiWind;
	}
	public void setAntiWind(Float antiWind) {
		this.antiWind = antiWind;
	}
	
	@Column(name = "Note", length = 50)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}

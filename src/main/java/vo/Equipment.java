package vo;

import java.util.Date;

public class Equipment {
	private Integer equipmentId;
	private String productName;
	private String manufactoryName;
	private String constructionType;	
	private Float maxWeight;
	private byte[] equipmentPhoto;
	
	public byte[] getEquipmentPhoto() {
		return equipmentPhoto;
	}
	public void setEquipmentPhoto(byte[] equipmentPhoto) {
		this.equipmentPhoto = equipmentPhoto;
	}
	private Float wingWidth;
	private Float equipmentLength;
	private String propulsionSystem;
	private String powerType;
	private Float loading;
	private Float speed;
	private Float maxHeight;
	private Float airTime;
	private Float maxDistance;
	private Float antiWind;
	private String note;
	
	private Date sendDate;
	private Integer sendId;
	private Date checkDate;
	private Integer checkId;
	private Date approveDate;
	private Integer approveId;
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufactoryName() {
		return manufactoryName;
	}
	public void setManufactoryName(String manufactoryName) {
		this.manufactoryName = manufactoryName;
	}
	public String getConstructionType() {
		return constructionType;
	}
	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}
	public Float getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}
	public Float getWingWidth() {
		return wingWidth;
	}
	public void setWingWidth(Float wingWidth) {
		this.wingWidth = wingWidth;
	}
	
	public Float getEquipmentLength() {
		return equipmentLength;
	}
	public void setEquipmentLength(Float equipmentLength) {
		this.equipmentLength = equipmentLength;
	}
	public String getPropulsionSystem() {
		return propulsionSystem;
	}
	public void setPropulsionSystem(String propulsionSystem) {
		this.propulsionSystem = propulsionSystem;
	}
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	public Float getLoading() {
		return loading;
	}
	public void setLoading(Float loading) {
		this.loading = loading;
	}
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public Float getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(Float maxHeight) {
		this.maxHeight = maxHeight;
	}
	public Float getAirTime() {
		return airTime;
	}
	public void setAirTime(Float airTime) {
		this.airTime = airTime;
	}
	public Float getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(Float maxDistance) {
		this.maxDistance = maxDistance;
	}
	public Float getAntiWind() {
		return antiWind;
	}
	public void setAntiWind(Float antiWind) {
		this.antiWind = antiWind;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getSendId() {
		return sendId;
	}
	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public Integer getApproveId() {
		return approveId;
	}
	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}
}

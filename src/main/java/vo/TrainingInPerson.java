package vo;

import java.util.Date;

public class TrainingInPerson extends Person{
	
	private Integer trainingId;
	private String trainingName;
	private String unit;
	private String trainingType;	
	private Date startDate;
	private Date endDate;
	private Integer hours;
	private byte[] photo;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	
	public void setShowTrainingType(){
		if("1".equals(trainingType)){
			trainingType = "學科";
		}else if ("2".equals(trainingType)){
			trainingType = "術科";
		}else{
			trainingType = "異常資料";
		}
	}
}

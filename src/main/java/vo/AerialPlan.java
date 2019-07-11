package vo;

import java.util.Date;
import java.util.List;

public class AerialPlan {
	private Integer projectId;
	private Integer aerialPlanId;
	public Integer getAerialPlanId() {
		return aerialPlanId;
	}

	public void setAerialPlanId(Integer aerialPlanId) {
		this.aerialPlanId = aerialPlanId;
	}

	public Integer getProjectId() {
		return projectId;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}


	//用途
	private String usage;
	//作業開始日期
	private Date aerialPlanStartDate;
	//作業結束日期
	private Date aerialPlanEndDate;
	//作業高度起
	private Integer amslFrom;
	//作業高度至
	private Integer amslTo;
	//實際高度
	private Integer agl;
	//起飛降落地點名稱
	private String takeOffLocationName;
	//起飛/降落地點
	private Integer takeOffNDegree;
	private Integer takeOffNMinute;
	private Float takeOffNSecond;
	private Integer takeOffEDegree;
	private Integer takeOffEMinute;
	private Float takeOffESecond;
	//作業範圍中心點
	private Integer areaCenterNDegree;
	public Integer getTakeOffNDegree() {
		return takeOffNDegree;
	}


	public void setTakeOffNDegree(Integer takeOffNDegree) {
		this.takeOffNDegree = takeOffNDegree;
	}


	public Integer getTakeOffNMinute() {
		return takeOffNMinute;
	}


	public void setTakeOffNMinute(Integer takeOffNMinute) {
		this.takeOffNMinute = takeOffNMinute;
	}


	public Float getTakeOffNSecond() {
		return takeOffNSecond;
	}


	public void setTakeOffNSecond(Float takeOffNSecond) {
		this.takeOffNSecond = takeOffNSecond;
	}


	public Integer getTakeOffEDegree() {
		return takeOffEDegree;
	}


	public void setTakeOffEDegree(Integer takeOffEDegree) {
		this.takeOffEDegree = takeOffEDegree;
	}


	public Integer getTakeOffEMinute() {
		return takeOffEMinute;
	}


	public void setTakeOffEMinute(Integer takeOffEMinute) {
		this.takeOffEMinute = takeOffEMinute;
	}


	public Float getTakeOffESecond() {
		return takeOffESecond;
	}


	public void setTakeOffESecond(Float takeOffESecond) {
		this.takeOffESecond = takeOffESecond;
	}


	public Integer getAreaCenterNDegree() {
		return areaCenterNDegree;
	}


	public void setAreaCenterNDegree(Integer areaCenterNDegree) {
		this.areaCenterNDegree = areaCenterNDegree;
	}


	public Integer getAreaCenterNMinute() {
		return areaCenterNMinute;
	}


	public void setAreaCenterNMinute(Integer areaCenterNMinute) {
		this.areaCenterNMinute = areaCenterNMinute;
	}


	public Float getAreaCenterNSecond() {
		return areaCenterNSecond;
	}


	public void setAreaCenterNSecond(Float areaCenterNSecond) {
		this.areaCenterNSecond = areaCenterNSecond;
	}


	public Integer getAreaCenterEDegree() {
		return areaCenterEDegree;
	}


	public void setAreaCenterEDegree(Integer areaCenterEDegree) {
		this.areaCenterEDegree = areaCenterEDegree;
	}


	public Integer getAreaCenterEMinute() {
		return areaCenterEMinute;
	}


	public void setAreaCenterEMinute(Integer areaCenterEMinute) {
		this.areaCenterEMinute = areaCenterEMinute;
	}


	public Float getAreaCenterESecond() {
		return areaCenterESecond;
	}


	public void setAreaCenterESecond(Float areaCenterESecond) {
		this.areaCenterESecond = areaCenterESecond;
	}


	public Integer getOperationDiameter() {
		return operationDiameter;
	}


	public void setOperationDiameter(Integer operationDiameter) {
		this.operationDiameter = operationDiameter;
	}


	private Integer areaCenterNMinute;
	private Float areaCenterNSecond;
	private Integer areaCenterEDegree;
	private Integer areaCenterEMinute;
	private Float areaCenterESecond;
	@Override
	public String toString() {
		return "AerialPlan [projectId=" + projectId + ", aerialPlanId=" + aerialPlanId + ", usage=" + usage
				+ ", aerialPlanStartDate=" + aerialPlanStartDate + ", aerialPlanEndDate=" + aerialPlanEndDate
				+ ", amslFrom=" + amslFrom + ", amslTo=" + amslTo + ", agl=" + agl + ", takeOffLocationName="
				+ takeOffLocationName + ", takeOffNDegree=" + takeOffNDegree + ", takeOffNMinute=" + takeOffNMinute
				+ ", takeOffNSecond=" + takeOffNSecond + ", takeOffEDegree=" + takeOffEDegree + ", takeOffEMinute="
				+ takeOffEMinute + ", takeOffESecond=" + takeOffESecond + ", areaCenterNDegree=" + areaCenterNDegree
				+ ", areaCenterNMinute=" + areaCenterNMinute + ", areaCenterNSecond=" + areaCenterNSecond
				+ ", areaCenterEDegree=" + areaCenterEDegree + ", areaCenterEMinute=" + areaCenterEMinute
				+ ", areaCenterESecond=" + areaCenterESecond + ", operationDiameter=" + operationDiameter
				+ ", equipmentsArray=" + equipmentsArray + "]";
	}
	


	//作業半徑
	private Integer operationDiameter;

	public Integer getAmslFrom() {
		return amslFrom;
	}


	public void setAmslFrom(Integer amslFrom) {
		this.amslFrom = amslFrom;
	}


	public Integer getAmslTo() {
		return amslTo;
	}


	public void setAmslTo(Integer amslTo) {
		this.amslTo = amslTo;
	}


	public Integer getAgl() {
		return agl;
	}


	public void setAgl(Integer agl) {
		this.agl = agl;
	}


	public String getTakeOffLocationName() {
		return takeOffLocationName;
	}


	public void setTakeOffLocationName(String takeOffLocationName) {
		this.takeOffLocationName = takeOffLocationName;
	}


	public String getUsage() {
		return usage;
	}


	public void setUsage(String usage) {
		this.usage = usage;
	}


	public Date getAerialPlanStartDate() {
		return aerialPlanStartDate;
	}

	public void setAerialPlanStartDate(Date aerialPlanStartDate) {
		this.aerialPlanStartDate = aerialPlanStartDate;
	}

	public Date getAerialPlanEndDate() {
		return aerialPlanEndDate;
	}

	public void setAerialPlanEndDate(Date aerialPlanEndDate) {
		this.aerialPlanEndDate = aerialPlanEndDate;
	}


	List<EquipmentPerson> equipmentsArray;
	
	
	public List<EquipmentPerson> getEquipmentsArray() {
		return equipmentsArray;
	}


	public void setEquipmentsArray(List<EquipmentPerson> equipmentsArray) {
		this.equipmentsArray = equipmentsArray;
	}


	public class EquipmentPerson{
		String equipmentId;
		String personId_1;
		String personId_2;
		public String getEquipmentId() {
			return equipmentId;
		}
		public void setEquipmentId(String equipmentId) {
			this.equipmentId = equipmentId;
		}
		public String getPersonId_1() {
			return personId_1;
		}
		public void setPersonId_1(String personId_1) {
			this.personId_1 = personId_1;
		}
		public String getPersonId_2() {
			return personId_2;
		}
		public void setPersonId_2(String personId_2) {
			this.personId_2 = personId_2;
		}
	}

}

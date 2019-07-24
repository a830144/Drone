package vo;

import java.util.Date;
import java.util.List;

public class AerialActivity {
	@Override
	public String toString() {
		return "AerialActivity [projectId=" + projectId + ", aerialPlanId=" + aerialPlanId + ", aerialActivityId="
				+ aerialActivityId + ", usage=" + usage + ", aerialPlanStartDate=" + aerialPlanStartDate
				+ ", aerialPlanEndDate=" + aerialPlanEndDate + ", aerialActivityStartDate=" + aerialActivityStartDate
				+ ", aerialActivityEndDate=" + aerialActivityEndDate + ", equipmentPersonArray=" + equipmentPersonArray
				+ "]";
	}

	private Integer projectId;
	private Integer aerialPlanId;
	private int aerialActivityId;
	private String usage;
	private Date aerialPlanStartDate;
	private Date aerialPlanEndDate;
	private Date aerialActivityStartDate;
	private Date aerialActivityEndDate;
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
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public Date getAerialActivityStartDate() {
		return aerialActivityStartDate;
	}
	public void setAerialActivityStartDate(Date aerialActivityStartDate) {
		this.aerialActivityStartDate = aerialActivityStartDate;
	}
	public Date getAerialActivityEndDate() {
		return aerialActivityEndDate;
	}
	public void setAerialActivityEndDate(Date aerialActivityEndDate) {
		this.aerialActivityEndDate = aerialActivityEndDate;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getAerialPlanId() {
		return aerialPlanId;
	}
	public void setAerialPlanId(Integer aerialPlanId) {
		this.aerialPlanId = aerialPlanId;
	}
	public int getAerialActivityId() {
		return aerialActivityId;
	}
	public void setAerialActivityId(int aerialActivityId) {
		this.aerialActivityId = aerialActivityId;
	}
	
	List<EquipmentPersonInActivity> equipmentPersonArray;

	public List<EquipmentPersonInActivity> getEquipmentPersonArray() {
		return equipmentPersonArray;
	}
	public void setEquipmentPersonArray(List<EquipmentPersonInActivity> equipmentPersonArray) {
		this.equipmentPersonArray = equipmentPersonArray;
	}

	public class EquipmentPersonInActivity{
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

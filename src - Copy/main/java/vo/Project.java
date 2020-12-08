package vo;

import java.util.Date;

public class Project {
	private Integer projectId;
	private String name;
	private String company;
	private String telephone;
	private Date projectEndDate;
	private String projectManager;
	private String result;
	private String areaData;
	private String otherData;
	private stateMachine.States state;
	public stateMachine.States getState() {
		return state;
	}
	public void setState(stateMachine.States state) {
		this.state = state;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAreaData() {
		return areaData;
	}
	public void setAreaData(String areaData) {
		this.areaData = areaData;
	}
	public String getOtherData() {
		return otherData;
	}
	public void setOtherData(String otherData) {
		this.otherData = otherData;
	}
}

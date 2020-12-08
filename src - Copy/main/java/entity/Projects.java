package entity;
// Generated Mar 9, 2019 2:17:56 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Projects generated by hbm2java
 */
@Entity
@Table(name = "projects")
public class Projects implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7860842730928288569L;
	private Integer projectId;
	private String name;
	private String company;
	private String telephone;
	private Date projectEndDate;
	private String projectManager;
	private String result;
	private String areaData;
	private String otherData;
	private String status;
	private Set<AerialPlans> aerialPlanses = new HashSet<AerialPlans>(0);
	
	private ProjectFlow projectFlow;
	
	@Embedded
	public ProjectFlow getProjectFlow() {
		return projectFlow;
	}

	public void setProjectFlow(ProjectFlow projectFlow) {
		this.projectFlow = projectFlow;
	}

	public Projects() {
	}

	public Projects(Integer projectId, String name, String company, String telephone, Date projectEndDate,
			String projectManager, String result, String areaData, String otherData, String status,
			Set<AerialPlans> aerialPlanses, ProjectFlow projectFlow) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.company = company;
		this.telephone = telephone;
		this.projectEndDate = projectEndDate;
		this.projectManager = projectManager;
		this.result = result;
		this.areaData = areaData;
		this.otherData = otherData;
		this.status = status;
		this.aerialPlanses = aerialPlanses;
		this.projectFlow = projectFlow;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Project_ID", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "Name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Company", length = 50)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "Telephone", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "End_Date", length = 10)
	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	@Column(name = "Project_Manager", length = 20)
	public String getProjectManager() {
		return this.projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	@Column(name = "Result", length = 2)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "Area_Data")
	public String getAreaData() {
		return this.areaData;
	}

	public void setAreaData(String areaData) {
		this.areaData = areaData;
	}

	@Column(name = "Other_Data")
	public String getOtherData() {
		return this.otherData;
	}

	public void setOtherData(String otherData) {
		this.otherData = otherData;
	}

	@Column(name = "Status", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	public Set<AerialPlans> getAerialPlanses() {
		return this.aerialPlanses;
	}

	public void setAerialPlanses(Set<AerialPlans> aerialPlanses) {
		this.aerialPlanses = aerialPlanses;
	}

}

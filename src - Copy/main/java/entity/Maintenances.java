package entity;

// default package
// Generated Mar 23, 2019 3:15:02 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Maintenances generated by hbm2java
 */
@Entity
@Table(name = "maintenances")
public class Maintenances implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7792908004713952644L;
	private Integer maintenanceId;
	private Equipments equipments;
	private String maintenanceType;
	private Date maintenanceDate;
	private String maintenancePerson;
	private Set<MaintenanceDetail> maintenanceDetails = new HashSet<MaintenanceDetail>(0);
	
	private MaintenanceFlow maintenanceFlow;

	public Maintenances() {
	}
	
	@Embedded
	public MaintenanceFlow getMaintenanceFlow() {
		return maintenanceFlow;
	}

	public void setMaintenanceFlow(MaintenanceFlow maintenanceFlow) {
		this.maintenanceFlow = maintenanceFlow;
	}

	public Maintenances(Equipments equipments, String maintenanceType, Date maintenanceDate, String maintenancePerson,
			Set<MaintenanceDetail> maintenanceDetails) {
		this.equipments = equipments;
		this.maintenanceType = maintenanceType;
		this.maintenanceDate = maintenanceDate;
		this.maintenancePerson = maintenancePerson;
		this.maintenanceDetails = maintenanceDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Maintenance_ID", unique = true, nullable = false)
	public Integer getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Equipment_ID")
	public Equipments getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Equipments equipments) {
		this.equipments = equipments;
	}

	@Column(name = "Maintenance_Type", length = 2)
	public String getMaintenanceType() {
		return this.maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Maintenance_Date", length = 10)
	public Date getMaintenanceDate() {
		return this.maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	@Column(name = "Maintenance_Person", length = 20)
	public String getMaintenancePerson() {
		return this.maintenancePerson;
	}

	public void setMaintenancePerson(String maintenancePerson) {
		this.maintenancePerson = maintenancePerson;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "maintenances")
	public Set<MaintenanceDetail> getMaintenanceDetails() {
		return this.maintenanceDetails;
	}

	public void setMaintenanceDetails(Set<MaintenanceDetail> maintenanceDetails) {
		this.maintenanceDetails = maintenanceDetails;
	}

}

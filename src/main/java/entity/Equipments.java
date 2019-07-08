package entity;
// default package
// Generated Mar 22, 2019 10:16:14 PM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Equipments generated by hbm2java
 */
@Entity
@Table(name = "equipments", catalog = "drone")
public class Equipments implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8210248968272821781L;
	private Integer equipmentId;
	private String productName;
	private String manufactoryName;
	private String constructionType;	
	private Float maxWeight;	
	private String equipmentPhoto;
	
	private EquipmentPerformance equipmentPerformance;
	private EquipmentFlow equipmentFlow;
	
	@Embedded
	public EquipmentPerformance getEquipmentPerformance() {
		return equipmentPerformance;
	}

	public void setEquipmentPerformance(EquipmentPerformance equipmentPerformance) {
		this.equipmentPerformance = equipmentPerformance;
	}

	@Embedded
	public EquipmentFlow getEquipmentFlow() {
		return equipmentFlow;
	}

	public void setEquipmentFlow(EquipmentFlow equipmentFlow) {
		this.equipmentFlow = equipmentFlow;
	}

	private Set<Maintenances> maintenanceses = new HashSet<Maintenances>(0);
	private Set<Modifications> modificationses = new HashSet<Modifications>(0);
	private Set<Missions> missionses = new HashSet<Missions>(0);
	private Set<RealMissions> realmissionses = new HashSet<RealMissions>(0);
	private Registrations registrations;

	public Equipments() {
	}

	public Equipments(String productName, String manufactoryName, String constructionType) {
		this.productName = productName;
		this.manufactoryName = manufactoryName;
		this.constructionType = constructionType;
	}

	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Equipment_ID", unique = true, nullable = false)
	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "Product_Name", nullable = false, length = 20)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "Manufactory_Name", nullable = false, length = 20)
	public String getManufactoryName() {
		return this.manufactoryName;
	}

	public void setManufactoryName(String manufactoryName) {
		this.manufactoryName = manufactoryName;
	}

	@Column(name = "Construction_Type", nullable = false, length = 2)
	public String getConstructionType() {
		return this.constructionType;
	}

	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}	

	@Column(name = "Max_Weight", precision = 12, scale = 0)
	public Float getMaxWeight() {
		return this.maxWeight;
	}

	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}	

	@Column(name = "Equipment_Photo")
	public String getEquipmentPhoto() {
		return this.equipmentPhoto;
	}

	public void setEquipmentPhoto(String equipmentPhoto) {
		this.equipmentPhoto = equipmentPhoto;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "equipments")
	public Set<Maintenances> getMaintenanceses() {
		return this.maintenanceses;
	}

	public void setMaintenanceses(Set<Maintenances> maintenanceses) {
		this.maintenanceses = maintenanceses;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "equipments")
	public Set<Modifications> getModificationses() {
		return this.modificationses;
	}

	public void setModificationses(Set<Modifications> modificationses) {
		this.modificationses = modificationses;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "equipments")
	public Registrations getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(Registrations registrations) {
		this.registrations = registrations;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipments")
	public Set<Missions> getMissionses() {
		return this.missionses;
	}

	public void setMissionses(Set<Missions> missionses) {
		this.missionses = missionses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipments")
	public Set<RealMissions> getRealmissionses() {
		return realmissionses;
	}

	public void setRealmissionses(Set<RealMissions> realmissionses) {
		this.realmissionses = realmissionses;
	}

}

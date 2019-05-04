package entity;

// default package
// Generated Mar 21, 2019 12:09:06 AM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Trainings generated by hbm2java
 */
@Entity
@Table(name = "trainings", catalog = "drone")
public class Trainings implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -983843516214658177L;
	private Integer trainingId;
	private String trainingName;
	private String unit;
	private String trainingType;
	private Set<Certificates> certificateses = new HashSet<Certificates>(0);

	public Trainings() {
	}

	public Trainings(String trainingName, String unit, String trainingType, Set<Certificates> certificateses) {
		this.trainingName = trainingName;
		this.unit = unit;
		this.trainingType = trainingType;
		this.certificateses = certificateses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Training_ID", unique = true, nullable = false)
	public Integer getTrainingId() {
		return this.trainingId;
	}

	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}

	@Column(name = "Name", length = 20)
	public String getTrainingName() {
		return this.trainingName;
	}

	public void settrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	@Column(name = "Unit", length = 20)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "Training_Type", length = 2)
	public String getTrainingType() {
		return this.trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "trainings")
	public Set<Certificates> getCertificateses() {
		return this.certificateses;
	}

	public void setCertificateses(Set<Certificates> certificateses) {
		this.certificateses = certificateses;
	}

}

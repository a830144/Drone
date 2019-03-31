package entity;
// Generated Mar 9, 2019 2:17:56 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OperationlimitsAerialplans generated by hbm2java
 */
@Entity
@Table(name = "operationlimits_aerialplans", catalog = "drone")
public class OperationlimitsAerialplans implements java.io.Serializable {

	private Integer operationLimitsAerialPlansId;
	private AerialPlans aerialPlans;
	private OperationLimits operationLimits;

	public OperationlimitsAerialplans() {
	}

	public OperationlimitsAerialplans(AerialPlans aerialPlans, OperationLimits operationLimits) {
		this.aerialPlans = aerialPlans;
		this.operationLimits = operationLimits;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OperationLimits_AerialPlans_ID", unique = true, nullable = false)
	public Integer getOperationLimitsAerialPlansId() {
		return this.operationLimitsAerialPlansId;
	}

	public void setOperationLimitsAerialPlansId(Integer operationLimitsAerialPlansId) {
		this.operationLimitsAerialPlansId = operationLimitsAerialPlansId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AerialPlan_ID")
	public AerialPlans getAerialPlans() {
		return this.aerialPlans;
	}

	public void setAerialPlans(AerialPlans aerialPlans) {
		this.aerialPlans = aerialPlans;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Operation_Limit_ID")
	public OperationLimits getOperationLimits() {
		return this.operationLimits;
	}

	public void setOperationLimits(OperationLimits operationLimits) {
		this.operationLimits = operationLimits;
	}

}

package entity;

// default package
// Generated Mar 23, 2019 3:15:02 AM by Hibernate Tools 5.2.3.Final

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
 * MaintenanceDetail generated by hbm2java
 */
@Entity
@Table(name = "maintenance_detail")
public class MaintenanceDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -808030656661112324L;
	private Integer maintenanceDetailId;
	private Maintenances maintenances;
	private int seq;
	private String detailType;
	private String status;
	private String comment;

	public MaintenanceDetail() {
	}

	public MaintenanceDetail(int seq, String detailType, String status, String comment) {
		super();
		this.seq = seq;
		this.detailType = detailType;
		this.status = status;
		this.comment = comment;
	}

	public MaintenanceDetail(Maintenances maintenances, int seq) {
		this.maintenances = maintenances;
		this.seq = seq;
	}

	public MaintenanceDetail(Maintenances maintenances, int seq, String detailType, String status, String comment) {
		this.maintenances = maintenances;
		this.seq = seq;
		this.detailType = detailType;
		this.status = status;
		this.comment = comment;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Maintenance_Detail_ID", unique = true, nullable = false)
	public Integer getMaintenanceDetailId() {
		return this.maintenanceDetailId;
	}

	public void setMaintenanceDetailId(Integer maintenanceDetailId) {
		this.maintenanceDetailId = maintenanceDetailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Maintenance_ID", nullable = false)
	public Maintenances getMaintenances() {
		return this.maintenances;
	}

	public void setMaintenances(Maintenances maintenances) {
		this.maintenances = maintenances;
	}

	@Column(name = "Seq", nullable = false)
	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Column(name = "Detail_Type", length = 2)
	public String getDetailType() {
		return this.detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	@Column(name = "Status", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "Comment", length = 50)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

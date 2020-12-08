package entity;

// default package
// Generated Mar 21, 2019 12:09:06 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Certificates generated by hbm2java
 */
@Entity
@Table(name = "certificates")
public class Certificates implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1753396938578742822L;
	private Integer certificateId;
	private Persons persons;
	private Trainings trainings;
	private Date startDate;
	private Date endDate;
	private Integer hours;
	private String photo;
	
	private CertificateFlow certificateFlow;
	
	
	@Embedded
	public CertificateFlow getCertificateFlow() {
		return certificateFlow;
	}

	public void setCertificateFlow(CertificateFlow certificateFlow) {
		this.certificateFlow = certificateFlow;
	}

	public Certificates() {
	}


	public Certificates(Persons persons, Trainings trainings) {
		this.persons = persons;
		this.trainings = trainings;
	}

	public Certificates(Persons persons, Trainings trainings, Date startDate, Date endDate, Integer hours,
			String photo) {
		this.persons = persons;
		this.trainings = trainings;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hours = hours;
		this.photo = photo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Certificate_ID", unique = true, nullable = false)
	public Integer getCertificateId() {
		return this.certificateId;
	}

	public void setCertificateId(Integer certificateId) {
		this.certificateId = certificateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Person_ID", nullable = false)
	public Persons getPersons() {
		return this.persons;
	}

	public void setPersons(Persons persons) {
		this.persons = persons;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Training_ID", nullable = false)
	public Trainings getTrainings() {
		return this.trainings;
	}

	public void setTrainings(Trainings trainings) {
		this.trainings = trainings;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Start_Date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "End_Date", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "Hours")
	public Integer getHours() {
		return this.hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	@Column(name = "Photo")
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}

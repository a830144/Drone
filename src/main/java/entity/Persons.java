package entity;

// default package
// Generated Mar 21, 2019 12:09:06 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persons generated by hbm2java
 */
@Entity
@Table(name = "persons", catalog = "drone")
public class Persons implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8235281917533633213L;
	private Integer personId;
	private String name;
	private Character sex;
	private String nationality;
	private String idNumber;
	private Date dateOfBirth;
	private String telephone;
	private String mobilePhone;
	private String fax;
	private String address;
	private String email;
	private byte[] recentPhoto;
	private byte[] investigation;
	private Character status;
	private Date sendDate;
	private Integer sendId;
	private Date checkDate;
	private Integer checkId;
	private Date approveDate;
	private Integer approveId;
	private Set<Participations> participationses = new HashSet<Participations>(0);
	private Set<Certificates> certificateses = new HashSet<Certificates>(0);
	private Set<PersonsLicenses> personsLicenseses = new HashSet<PersonsLicenses>(0);
	private Set<AerialPlans> aerialPlans = new HashSet<AerialPlans>(0);
	public Persons() {
	}

	public Persons(String name, Character sex, String nationality, String idNumber, Date dateOfBirth, String telephone,
			String mobilePhone, String fax, String address, String email, byte[] recentPhoto, byte[] investigation,
			Character status, Date sendDate, Integer sendId, Date checkDate, Integer checkId, Date approveDate,
			Integer approveId, Set<Participations> participationses, Set<Certificates> certificateses, Set<PersonsLicenses> personsLicenseses) {
		this.name = name;
		this.sex = sex;
		this.nationality = nationality;
		this.idNumber = idNumber;
		this.dateOfBirth = dateOfBirth;
		this.telephone = telephone;
		this.mobilePhone = mobilePhone;
		this.fax = fax;
		this.address = address;
		this.email = email;
		this.recentPhoto = recentPhoto;
		this.investigation = investigation;
		this.status = status;
		this.sendDate = sendDate;
		this.sendId = sendId;
		this.checkDate = checkDate;
		this.checkId = checkId;
		this.approveDate = approveDate;
		this.approveId = approveId;
		this.participationses = participationses;
		this.certificateses = certificateses;
		this.personsLicenseses = personsLicenseses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Person_ID", unique = true, nullable = false)
	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@Column(name = "Name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Sex", length = 1)
	public Character getSex() {
		return this.sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	@Column(name = "Nationality", length = 20)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "ID_Number", length = 20)
	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Date_Of_Birth", length = 10)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "Telephone", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "Mobile_Phone", length = 20)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "Fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "Address", length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Email", length = 20)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Recent_Photo")
	public byte[] getRecentPhoto() {
		return this.recentPhoto;
	}

	public void setRecentPhoto(byte[] recentPhoto) {
		this.recentPhoto = recentPhoto;
	}

	@Column(name = "Investigation")
	public byte[] getInvestigation() {
		return this.investigation;
	}

	public void setInvestigation(byte[] investigation) {
		this.investigation = investigation;
	}

	@Column(name = "Status", length = 1)
	public Character getStatus() {
		return this.status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Send_Date", length = 10)
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "Send_ID")
	public Integer getSendId() {
		return this.sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Check_Date", length = 10)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "Check_ID")
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Approve_Date", length = 10)
	public Date getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	@Column(name = "Approve_ID")
	public Integer getApproveId() {
		return this.approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "persons")
	public Set<Participations> getParticipationses() {
		return this.participationses;
	}

	public void setParticipationses(Set<Participations> participationses) {
		this.participationses = participationses;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "persons")
	public Set<Certificates> getCertificateses() {
		return this.certificateses;
	}

	public void setCertificateses(Set<Certificates> certificateses) {
		this.certificateses = certificateses;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "persons")
	public Set<PersonsLicenses> getPersonsLicenseses() {
		return this.personsLicenseses;
	}

	public void setPersonsLicenseses(Set<PersonsLicenses> personsLicenseses) {
		this.personsLicenseses = personsLicenseses;
	}
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.ALL })
    @JoinTable(
        name = "persons_aerialplans", 
        joinColumns = { @JoinColumn(name = "Person_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "Aerial_Plan_ID") }
    )
	public Set<AerialPlans> getAerialPlans() {
		return this.aerialPlans;
	}

	public void setAerialPlans(Set<AerialPlans> aerialPlans) {
		this.aerialPlans = aerialPlans;
	}

}
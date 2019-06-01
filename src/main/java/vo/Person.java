package vo;

import java.util.Date;

public class Person {
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
	private stateMachine.States state;
	public stateMachine.States getState() {
		return state;
	}
	public void setState(stateMachine.States state) {
		this.state = state;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getRecentPhoto() {
		return recentPhoto;
	}
	public void setRecentPhoto(byte[] recentPhoto) {
		this.recentPhoto = recentPhoto;
	}
	public byte[] getInvestigation() {
		return investigation;
	}
	public void setInvestigation(byte[] investigation) {
		this.investigation = investigation;
	}
	
}

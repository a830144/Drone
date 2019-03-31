package vo;

import java.util.Date;

public class LicenseInPerson extends Person{
	
	
	private Date gotDate;
	private byte[] photo;
	
	private Integer licenseId;
	private String type;
	private String subType;
	
	public Date getGotDate() {
		return gotDate;
	}
	public void setGotDate(Date gotDate) {
		this.gotDate = gotDate;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public Integer getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	@Override
	public String toString() {
		return "LicenseInPerson [gotDate=" + gotDate + ", type=" + type + ", subType=" + subType + "]";
	}
	
}
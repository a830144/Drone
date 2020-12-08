package vo;

import java.util.Date;

public class LicenseInPerson extends Person{
	
	
	private Date gotDate;
	private String photo;
	
	private Integer licenseId;
	private String type;
	private String subType;
	private String codeContent;
	private String constructionType;
	
	public String getConstructionType() {
		return constructionType;
	}
	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}
	public String getCodeContent() {
		return codeContent;
	}
	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}
	private Integer personsLicensesId;
	
	public Integer getPersonsLicensesId() {
		return personsLicensesId;
	}
	public void setPersonsLicensesId(Integer personsLicensesId) {
		this.personsLicensesId = personsLicensesId;
	}
	public Date getGotDate() {
		return gotDate;
	}
	public void setGotDate(Date gotDate) {
		this.gotDate = gotDate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
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
		return "LicenseInPerson [gotDate=" + gotDate + ", photo=" + photo + ", licenseId=" + licenseId + ", type="
				+ type + ", subType=" + subType + ", codeContent=" + codeContent + ", constructionType="
				+ constructionType + ", personsLicensesId=" + personsLicensesId + "]";
	}
	
	
}

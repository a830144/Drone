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
 * Licenses generated by hbm2java
 */
@Entity
@Table(name = "licenses")
public class Licenses implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5620711983973281481L;
	private Integer licenseId;
	private String type;
	private Set<PersonsLicenses> personsLicenseses = new HashSet<PersonsLicenses>(0);

	public Licenses() {
	}

	public Licenses(String type,  Set<PersonsLicenses> personsLicenseses) {
		this.type = type;
		this.personsLicenseses = personsLicenseses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "License_ID", unique = true, nullable = false)
	public Integer getLicenseId() {
		return this.licenseId;
	}

	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}

	@Column(name = "Type", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Licenses [licenseId=" + licenseId + ", type=" + type  + ", personsLicenseses="
				+ personsLicenseses + "]";
	}


	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "licenses")
	public Set<PersonsLicenses> getPersonsLicenseses() {
		return this.personsLicenseses;
	}

	public void setPersonsLicenseses(Set<PersonsLicenses> personsLicenseses) {
		this.personsLicenseses = personsLicenseses;
	}

}

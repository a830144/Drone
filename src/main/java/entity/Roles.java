package entity;
// Generated Mar 9, 2019 2:17:56 AM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", catalog = "drone")
public class Roles implements java.io.Serializable {

	private Integer roleId;
	private String roleName;
	private String roleType;
	private Set rolesFunctionses = new HashSet(0);
	private Set usersRoleses = new HashSet(0);

	public Roles() {
	}

	public Roles(String roleName, String roleType, Set rolesFunctionses, Set usersRoleses) {
		this.roleName = roleName;
		this.roleType = roleType;
		this.rolesFunctionses = rolesFunctionses;
		this.usersRoleses = usersRoleses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Role_ID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "Role_Name", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "Role_Type", length = 50)
	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set getRolesFunctionses() {
		return this.rolesFunctionses;
	}

	public void setRolesFunctionses(Set rolesFunctionses) {
		this.rolesFunctionses = rolesFunctionses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(Set usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

}
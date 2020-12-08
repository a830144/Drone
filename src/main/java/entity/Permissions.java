package entity;
// default package
// Generated Dec 7, 2020 9:16:49 PM by Hibernate Tools 5.2.3.Final

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Permissions generated by hbm2java
 */
@Entity
@Table(name = "permissions")
public class Permissions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7936429588528641180L;
	private Integer permissionId;
	private Actions actions;
	private Resources resources;
	private String permissionName;
	private Set<RolesPermissions> rolesPermissionses = new HashSet<RolesPermissions>(0);

	public Permissions() {
	}

	public Permissions(Actions actions, Resources resources, String permissionName, Set<RolesPermissions> rolesPermissionses) {
		this.actions = actions;
		this.resources = resources;
		this.permissionName = permissionName;
		this.rolesPermissionses = rolesPermissionses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Permission_ID", unique = true, nullable = false)
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Action_ID")
	public Actions getActions() {
		return this.actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Resource_ID")
	public Resources getResources() {
		return this.resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	@Column(name = "Permission_Name", length = 45)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "permissions")
	public Set<RolesPermissions> getRolesPermissionses() {
		return this.rolesPermissionses;
	}

	public void setRolesPermissionses(Set<RolesPermissions> rolesPermissionses) {
		this.rolesPermissionses = rolesPermissionses;
	}

}

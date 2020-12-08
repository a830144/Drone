package entity;
// default package
// Generated Dec 7, 2020 9:02:24 PM by Hibernate Tools 5.2.3.Final

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
 * Actions generated by hbm2java
 */
@Entity
@Table(name = "actions")
public class Actions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7838051877523065752L;
	private Integer actionId;
	private String actionName;
	private Set<Permissions> permissionses = new HashSet<Permissions>(0);

	public Actions() {
	}

	public Actions(String actionName, Set<Permissions> permissionses) {
		this.actionName = actionName;
		this.permissionses = permissionses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Action_ID", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Column(name = "Action_Name", length = 45)
	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "actions")
	public Set<Permissions> getPermissionses() {
		return this.permissionses;
	}

	public void setPermissionses(Set<Permissions> permissionses) {
		this.permissionses = permissionses;
	}

}

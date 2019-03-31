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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RolesFunctions generated by hbm2java
 */
@Entity
@Table(name = "roles_functions", catalog = "drone")
public class RolesFunctions implements java.io.Serializable {

	private Integer rolesFunctionsId;
	private Functions functions;
	private Roles roles;
	private Set operationses = new HashSet(0);

	public RolesFunctions() {
	}

	public RolesFunctions(Functions functions, Roles roles, Set operationses) {
		this.functions = functions;
		this.roles = roles;
		this.operationses = operationses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Roles_Functions_ID", unique = true, nullable = false)
	public Integer getRolesFunctionsId() {
		return this.rolesFunctionsId;
	}

	public void setRolesFunctionsId(Integer rolesFunctionsId) {
		this.rolesFunctionsId = rolesFunctionsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Function_ID")
	public Functions getFunctions() {
		return this.functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Role_ID")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rolesFunctions")
	public Set getOperationses() {
		return this.operationses;
	}

	public void setOperationses(Set operationses) {
		this.operationses = operationses;
	}

}
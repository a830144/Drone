package entity;
// Generated Mar 9, 2019 2:17:56 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Operations generated by hbm2java
 */
@Entity
@Table(name = "operations", catalog = "drone")
public class Operations implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 805963983773536602L;
	private Integer operationId;
	private RolesFunctions rolesFunctions;
	private String type;

	public Operations() {
	}

	public Operations(RolesFunctions rolesFunctions, String type) {
		this.rolesFunctions = rolesFunctions;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Operation_ID", unique = true, nullable = false)
	public Integer getOperationId() {
		return this.operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Roles_Functions_ID")
	public RolesFunctions getRolesFunctions() {
		return this.rolesFunctions;
	}

	public void setRolesFunctions(RolesFunctions rolesFunctions) {
		this.rolesFunctions = rolesFunctions;
	}

	@Column(name = "Type", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

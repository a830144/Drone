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
 * UsersRoles generated by hbm2java
 */
@Entity
@Table(name = "users_roles", catalog = "drone")
public class UsersRoles implements java.io.Serializable {

	private Integer usersRolesId;
	private Roles roles;
	private Users users;

	public UsersRoles() {
	}

	public UsersRoles(Roles roles, Users users) {
		this.roles = roles;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Users_Roles_ID", unique = true, nullable = false)
	public Integer getUsersRolesId() {
		return this.usersRolesId;
	}

	public void setUsersRolesId(Integer usersRolesId) {
		this.usersRolesId = usersRolesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Role_ID", nullable = false)
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_ID", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}

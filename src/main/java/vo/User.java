package vo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import vo.Role.OwnPermission;

public class User {
	private Integer userId;
	private String userName;
	private String password;
	private String EMail;
	private String phone;
	private Set<Integer> rolesSet = new HashSet<Integer>();
	private Set<OwnRole> ownRoleSet = new HashSet<OwnRole>();
	
	
	public Set<Integer> getRolesSet() {
		return rolesSet;
	}
	public void setRolesSet(Set<Integer> rolesSet) {
		this.rolesSet = rolesSet;
	}
	public Set<OwnRole> getOwnRoleSet() {
		return ownRoleSet;
	}
	public void setOwnRoleSet(Set<OwnRole> ownRoleSet) {
		this.ownRoleSet = ownRoleSet;
	}

	private Map<String,HashSet<String>> functionOperations;
	public Map<String, HashSet<String>> getFunctionOperations() {
		return functionOperations;
	}
	public void setFunctionOperations(Map<String, HashSet<String>> functionOperations) {
		this.functionOperations = functionOperations;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public class OwnRole{
		Integer roleId;
		String roleName;
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		
	}

}

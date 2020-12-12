package vo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Role {
	private Integer roleId;
	private String roleName;
	private Set<Integer> permissionsSet = new HashSet<Integer>();
	private Set<OwnPermission> ownPermissionSet = new HashSet<OwnPermission>();
	private List<String[]> ownPermissionTruthMap = new LinkedList<String[]>();
	
	

	

	public List<String[]> getOwnPermissionTruthMap() {
		return ownPermissionTruthMap;
	}

	public void setOwnPermissionTruthMap(List<String[]> ownPermissionTruthMap) {
		this.ownPermissionTruthMap = ownPermissionTruthMap;
	}

	public void setPermissionsSet(Set<Integer> permissionsSet) {
		this.permissionsSet = permissionsSet;
	}
	
	public Set getPermissionsSet() {
		return permissionsSet;
	}
	
	
	public Set<OwnPermission> getOwnPermissionSet() {
		return ownPermissionSet;
	}

	public void setOwnPermissionSet(Set<OwnPermission> ownPermissionSet) {
		this.ownPermissionSet = ownPermissionSet;
	}

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
	
	public class OwnPermission{
		Integer permissionId;
		String permissionName;
		public Integer getPermissionId() {
			return permissionId;
		}
		public void setPermissionId(Integer permissionId) {
			this.permissionId = permissionId;
		}
		public String getPermissionName() {
			return permissionName;
		}
		public void setPermissionName(String permissionName) {
			this.permissionName = permissionName;
		}
	}
	
	
}

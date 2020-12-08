package vo;

import entity.Actions;
import entity.Resources;

public class Permission {
	private Integer permissionId;
	private Actions actions;
	private Resources resources;
	private String permissionName;
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public Actions getActions() {
		return actions;
	}
	public void setActions(Actions actions) {
		this.actions = actions;
	}
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}

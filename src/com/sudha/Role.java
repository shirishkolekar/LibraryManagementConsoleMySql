package com.sudha;

public class Role {

	private int roleId;
	private String roleName;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role() {
		this.roleId = 0;
		this.roleName = "";
	}

	public Role(int roleId, String roleName) {

		this.roleId = roleId;
		this.roleName = roleName;
	}
}
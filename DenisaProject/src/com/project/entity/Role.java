package com.project.entity;

public enum Role {
	ADMIN(0), USER(1);
	private final int roleId;

	Role(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}
}
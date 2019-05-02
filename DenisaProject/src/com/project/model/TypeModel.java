package com.project.model;

public class TypeModel {

	private Integer typeId;
	private String type;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TypeModel [typeId=" + typeId + ", type=" + type + "]";
	}

}

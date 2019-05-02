package com.project.model;

import java.util.Date;

public class ApplicationModel {
	
	private Integer applicationId;
	private TypeModel typeModel;
	private Date startDate;
	private Date finishDate;
	private String description;
	private boolean isApproved;
	private UserModel userModel;
	
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	
	public TypeModel getTypeModel() {
		return typeModel;
	}
	public void setTypeModel(TypeModel typeModel) {
		this.typeModel = typeModel;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	@Override
	public String toString() {
		return "ApplicationModel [applicationId=" + applicationId + ", typeModel=" + typeModel + ", startDate="
				+ startDate + ", finishDate=" + finishDate + ", description=" + description + ", isApproved="
				+ isApproved + ", userModel=" + userModel + "]";
	}
	
	

}

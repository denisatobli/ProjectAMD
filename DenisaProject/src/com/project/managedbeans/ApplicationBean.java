package com.project.managedbeans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import com.project.entity.*;
import com.project.model.*;

@ManagedBean(name = "application")
@ViewScoped
public class ApplicationBean {
	
	private Date startDate;
	private Date finishDate;
	private String description;
	private boolean isApproved;
	private User user;
	private Type type;
	
	private List<ApplicationModel> applications;
	
	@PostConstruct
	public void init() {
		
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public List<ApplicationModel> getApplications() {
		return applications;
	}
	public void setApplications(List<ApplicationModel> applications) {
		this.applications = applications;
	}
	@Override
	public String toString() {
		return "ApplicationBean [startDate=" + startDate + ", finishDate=" + finishDate + ", description=" + description
				+ ", isApproved=" + isApproved + ", user=" + user + ", type=" + type + "]";
	}

}

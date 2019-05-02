package com.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class Application implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicationId", nullable = false)
	private Integer applicationId;

	@ManyToOne
	@JoinColumn(name = "typeId", nullable = false)
	private Type type;
	
	@Column(name="startDate")
	private Date startDate;
	@Column(name="finishDate")
	private Date finishDate;
	@Column(name="description")
	private String description;
	@Column(name="isApproved")
	private boolean isApproved;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", type=" + type + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", description=" + description + ", isApproved=" + isApproved
				+ ", user=" + user + "]";
	}
	
}

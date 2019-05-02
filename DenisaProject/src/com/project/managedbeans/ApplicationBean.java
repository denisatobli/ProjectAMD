package com.project.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import java.util.List;
import com.project.model.*;
import com.project.service.ApplicationService;
import com.project.service.UserService;

@ManagedBean(name = "applicationBean")
@ViewScoped
public class ApplicationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer applicationId;
	private Date startDate;
	private Date finishDate;
	private String description;
	private boolean isApproved;
	private UserModel userModel;
	private TypeModel typeModel;
	private String selectedType;

	private List<ApplicationModel> applications;
	private List<TypeModel> types;
	private ApplicationService applicationService = new ApplicationService();
	private UserService userService = new UserService();

	@PostConstruct
	public void init() {
		applications = applicationService.getAllApplications();
		types = applicationService.getAllApplicationTypes();
	}

	public void valueChangedType(ValueChangeEvent event) {
		selectedType = (String) event.getNewValue();
	}

	public String addApplication() {
		FacesContext context = FacesContext.getCurrentInstance();
		typeModel = applicationService.getType(types, selectedType);
		if (typeModel != null) {
			if (applicationService.addApplication(startDate, finishDate, description, userService.getLoggedUser(),
					typeModel)) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Your application was successfully completed!",
						null));
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Your application was not completed, something went wrong!", null));
			}
		}
		return "userLandingPage";
	}

	public String approveApplication(Integer applicationId) {
		System.out.println("approveApplication"+applicationId);
		FacesContext context = FacesContext.getCurrentInstance();
		if(applicationService.updateApplicationApproval(applicationId, true)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
					"Application approved!"));
		}
		return "admin/manageApplication?faces-redirect=true";
	}

	public String rejectApplication(Integer applicationId) {
		System.out.println("rejectApplication"+applicationId);
		FacesContext context = FacesContext.getCurrentInstance();
		if(applicationService.updateApplicationApproval(applicationId, false)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
					"Application rejected!"));
		}
		return "admin/manageApplication?faces-redirect=true";
	}
	
	public String deleteApplication(Integer applicationId) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (applicationService.deleteApplication(applicationId)) {
			context.addMessage(null, new FacesMessage("Successful deleted", null));
		} else {
			context.addMessage(null, new FacesMessage("Error!! Not deletetd", null));
		}
		return "/previousApplication?faces-redirect=true";
	}
	
	public String onRowEdit(RowEditEvent event) {
		System.out.println("editttttttttt");
		ApplicationModel applicationModel = (ApplicationModel) event.getObject();
		FacesContext context = FacesContext.getCurrentInstance();
		if (applicationService.updateApplication(applicationModel)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!",
					"Application edited!"));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null ,
					"Not edited!"));
		}
		return "previousApplications";
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit canceled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	
	

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
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

	public TypeModel getTypeModel() {
		return typeModel;
	}

	public void setTypeModel(TypeModel typeModel) {
		this.typeModel = typeModel;
	}

	public List<ApplicationModel> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationModel> applications) {
		this.applications = applications;
	}

	public List<TypeModel> getTypes() {
		return types;
	}

	public void setTypes(List<TypeModel> types) {
		this.types = types;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	@Override
	public String toString() {
		return "ApplicationBean [startDate=" + startDate + ", finishDate=" + finishDate + ", description=" + description
				+ ", isApproved=" + isApproved + ", user=" + userModel + ", type=" + typeModel + "]";
	}

}

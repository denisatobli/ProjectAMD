package com.project.service;

import com.project.converters.ApplicationConverter;
import com.project.converters.TypeConverter;
import com.project.converters.UserConverter;
import com.project.model.*;
import com.project.repository.ApplicationRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ApplicationService  {
	
	private ApplicationRepository applicationRepo = new ApplicationRepository();

	public List<ApplicationModel> getAllApplications() {
		return ApplicationConverter.listEntityToModel(applicationRepo.getAllApplications());
	}

	public List<TypeModel> getAllApplicationTypes() {
		return TypeConverter.listEntityToModel(applicationRepo.getAllApplicationTypes());
	}

	public TypeModel getType(List<TypeModel> types, String type) {
		if (type == null) {
			return null;
		}
		for (TypeModel typeModel : types) {
			if (type.equals(typeModel.getType())) {
				return typeModel;
			}
		}
		return null;
	}

	public boolean addApplication(Date startDate, Date finishDate, String description, UserModel userModel,
			TypeModel typeModel) {
		return applicationRepo.addApplication(startDate, finishDate, description,
				UserConverter.modelToEntity(userModel), TypeConverter.modelToEntity(typeModel));
	}

	public boolean updateApplicationApproval(Integer applicationId, boolean isApproved) {
		return applicationRepo.updateApplicationApproval(applicationId, isApproved);
	}

	public boolean deleteApplication(Integer applicationId) {
		return applicationRepo.deleteApplication(applicationId);
	}

	public boolean updateApplication(ApplicationModel applicationModel) {
		return applicationRepo.updateApplication(applicationModel.getApplicationId(), applicationModel.getStartDate(),
				applicationModel.getFinishDate(), applicationModel.getDescription());
	}
}

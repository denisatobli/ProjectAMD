package com.project.service;

import com.project.converters.ApplicationConverter;
import com.project.converters.TypeConverter;
import com.project.converters.UserConverter;
import com.project.model.*;
import com.project.repository.ApplicationRepository;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationService implements Serializable {

	private static final long serialVersionUID = 1L;
	private ApplicationRepository applicationRepo = new ApplicationRepository();

	public List<ApplicationModel> getAllApplications(boolean isApproved) {
		return ApplicationConverter.listEntityToModel(applicationRepo.getAllApplications(isApproved));
	}

	public List<ApplicationModel> getUserApplications(Integer userId) {
		return ApplicationConverter.listEntityToModel(applicationRepo.getUserApplications(userId));
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
		applicationModel.setStartDate(applicationModel.getStartDate());
		applicationModel.setFinishDate(applicationModel.getFinishDate());
		applicationModel.setDescription(applicationModel.getDescription());
		return applicationRepo.updateApplication(ApplicationConverter.modelToEntity(applicationModel));
	}

	public long getRemainingDays(Date startDate, Date finishDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		dateFormat.format(currentDate);
		long diff;
		if (finishDate.compareTo(currentDate) <= 0) {
			return -2;
		} else if (startDate.compareTo(currentDate) < 0) {
			diff = finishDate.getTime() - currentDate.getTime();
		} else if (startDate.compareTo(currentDate) > 0) {
			diff = finishDate.getTime() - startDate.getTime();
		} else {
			diff = finishDate.getTime() - startDate.getTime();
		}
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}

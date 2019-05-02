package com.project.converters;

import java.util.ArrayList;
import java.util.List;

import com.project.entity.Application;
import com.project.model.ApplicationModel;

public class ApplicationConverter {

	public static ApplicationModel entityToModel(Application application) {
		if (application != null) {
			ApplicationModel applicationModel = new ApplicationModel();
			applicationModel.setApplicationId(application.getApplicationId());
			applicationModel.setStartDate(application.getStartDate());
			applicationModel.setFinishDate(application.getFinishDate());
			applicationModel.setDescription(application.getDescription());
			applicationModel.setApproved(application.isApproved());
			applicationModel.setUserModel(UserConverter.entityToModel(application.getUser()));
			applicationModel.setTypeModel(TypeConverter.entityToModel(application.getType()));
			return applicationModel;
		} else {
			return null;
		}
	}

	public static Application modelToEntity(ApplicationModel applicationModel) {
		if (applicationModel != null) {
			Application application = new Application();
			application.setApplicationId(applicationModel.getApplicationId());
			application.setStartDate(applicationModel.getStartDate());
			application.setFinishDate(applicationModel.getFinishDate());
			application.setDescription(applicationModel.getDescription());
			application.setApproved(applicationModel.isApproved());
			application.setUser(UserConverter.modelToEntity(applicationModel.getUserModel()));
			application.setType(TypeConverter.modelToEntity(applicationModel.getTypeModel()));
			return application;
		} else {
			return null;
		}
	}

	public static List<ApplicationModel> listEntityToModel(List<Application> applications) {
		List<ApplicationModel> applicationsModel = new ArrayList<>();
		if (applications != null) {
			for (Application application : applications) {
				applicationsModel.add(entityToModel(application));
			}
		}
		return applicationsModel;
	}

	public static List<Application> listModelToEntity(List<ApplicationModel> applications) {
		List<Application> applicationsEntity = new ArrayList<>();
		if (applications != null) {
			for (ApplicationModel application : applications) {
				applicationsEntity.add(modelToEntity(application));
			}
		}
		return applicationsEntity;
	}

}

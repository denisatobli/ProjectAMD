package com.project.converters;

import java.util.ArrayList;
import java.util.List;
import com.project.entity.Type;
import com.project.model.TypeModel;

public class TypeConverter {

	public static TypeModel entityToModel(Type type) {
		if (type != null) {
			TypeModel typeModel = new TypeModel();
			typeModel.setTypeId(type.getTypeId());
			typeModel.setType(type.getType());
			return typeModel;
		} else {
			return null;
		}
	}

	public static Type modelToEntity(TypeModel typeModel) {
		if (typeModel != null) {
			Type type = new Type();
			type.setTypeId(typeModel.getTypeId());
			type.setType(typeModel.getType());
			return type;
		} else {
			return null;
		}
	}

	public static List<TypeModel> listEntityToModel(List<Type> types) {
		List<TypeModel> typesModel = new ArrayList<>();
		if (types != null) {
			for (Type type : types) {
				typesModel.add(entityToModel(type));
			}
		}
		return typesModel;
	}

	public static List<Type> listModelToEntity(List<TypeModel> types) {
		List<Type> typesEntity = new ArrayList<>();
		if (types != null) {
			for (TypeModel type : types) {
				typesEntity.add(modelToEntity(type));
			}
		}
		return typesEntity;
	}

}

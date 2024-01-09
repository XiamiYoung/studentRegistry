package edu.dufe.student.registry.converter;

import org.springframework.stereotype.Component;

import edu.dufe.student.registry.converter.base.BaseConverter;
import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.entity.UserModel;

@Component
public class UserConverter extends BaseConverter{
	
	public UserData modelToData(UserModel model){
		UserData data = null;
		if(model!=null){
			data = new UserData();
			super.modelToData(model, data);
			data.setUsername(model.getUserName());
			data.setPassword(model.getPassword());
		}
		return data;
	}
	
	public UserModel dataToModel(UserData data,UserModel model){
		if(data!=null){
			super.dataToModel(data, model);
			model.setUserName(data.getUsername());
			model.setPassword(data.getPassword());
		}
		return model;
	}
}

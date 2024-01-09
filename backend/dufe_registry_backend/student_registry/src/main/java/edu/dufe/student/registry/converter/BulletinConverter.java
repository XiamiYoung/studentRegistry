package edu.dufe.student.registry.converter;

import org.springframework.stereotype.Component;

import edu.dufe.student.registry.converter.base.BaseConverter;
import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.entity.BulletinModel;

@Component
public class BulletinConverter extends BaseConverter{
	
	public BulletinData modelToData(BulletinModel model){
		BulletinData data = null;
		if(model!=null){
			data = new BulletinData();
			super.modelToData(model, data);
			data.setContent(model.getContent());
		}
		return data;
	}
	
	public BulletinModel dataToModel(BulletinData data,BulletinModel model){
		if(data!=null){
			super.dataToModel(data, model);
			model.setContent(data.getContent());
		}
		return model;
	}
}

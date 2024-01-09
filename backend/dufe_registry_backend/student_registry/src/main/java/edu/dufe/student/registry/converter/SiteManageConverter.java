package edu.dufe.student.registry.converter;

import org.springframework.stereotype.Component;

import edu.dufe.student.registry.converter.base.BaseConverter;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.entity.SiteManageModel;

@Component
public class SiteManageConverter extends BaseConverter{
	
	public SiteManageData modelToData(SiteManageModel model){
		SiteManageData data = null;
		if(model!=null){
			data = new SiteManageData();
			super.modelToData(model, data);
			data.setIsSiteOpen(model.getIsSiteOpen());
		}
		return data;
	}
	
	public SiteManageModel dataToModel(SiteManageData data,SiteManageModel model){
		if(data!=null){
			super.dataToModel(data, model);
			if(data.getIsSiteOpen()==null){
				data.setIsSiteOpen(new Boolean(true));
			}
			model.setIsSiteOpen(data.getIsSiteOpen());
		}
		return model;
	}
}

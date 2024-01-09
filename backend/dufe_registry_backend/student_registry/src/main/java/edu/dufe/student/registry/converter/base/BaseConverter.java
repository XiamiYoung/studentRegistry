package edu.dufe.student.registry.converter.base;

import edu.dufe.student.registry.data.in.base.BaseData;
import edu.dufe.student.registry.entity.base.BaseEntityModel;

public class BaseConverter {
	protected void modelToData(BaseEntityModel model,BaseData data) {
		if(model!=null){
			if(model.getPk()!=null) {
				data.setPk(model.getPk());
			}
		}
	}
	
	protected void dataToModel(BaseData data, BaseEntityModel model) {
		if(data!=null){
			if(data.getPk()!=null) {
				model.setPk(data.getPk());
			}
		}
	}
}

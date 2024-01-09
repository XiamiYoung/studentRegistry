package edu.dufe.student.registry.converter;

import org.springframework.stereotype.Component;

import edu.dufe.student.registry.converter.base.BaseConverter;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.entity.StudentRegistryModel;

@Component
public class StudentRegistryConverter extends BaseConverter{
	
	public StudentRegistryData modelToData(StudentRegistryModel model){
		StudentRegistryData data = null;
		if(model!=null){
			data = new StudentRegistryData();
			super.modelToData(model, data);
			data.setStudentName(model.getStudentName());
			data.setStudentSex(model.getStudentSex());
			data.setStudentStudyNum(model.getStudentStudyNum());
			data.setStudentGrade(model.getStudentGrade());
			data.setStudentMajor(model.getStudentMajor());
			data.setStudentIdentityNum(model.getStudentIdentityNum());
			data.setStudentTel(model.getStudentTel());
			data.setLanguage(model.getLanguage());
		}
		return data;
	}
	
	public StudentRegistryModel dataToModel(StudentRegistryData data,StudentRegistryModel model){
		if(data!=null){
			super.dataToModel(data, model);
			model.setStudentName(data.getStudentName());
			model.setStudentSex(data.getStudentSex());
			model.setStudentStudyNum(data.getStudentStudyNum());
			model.setStudentGrade(data.getStudentGrade());
			model.setStudentMajor(data.getStudentMajor());
			model.setStudentIdentityNum(data.getStudentIdentityNum());
			model.setStudentTel(data.getStudentTel());
			model.setLanguage(data.getLanguage());
		}
		return model;
	}
}

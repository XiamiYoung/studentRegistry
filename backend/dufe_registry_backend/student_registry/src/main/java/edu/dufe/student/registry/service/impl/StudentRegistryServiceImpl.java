package edu.dufe.student.registry.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.converter.StudentRegistryConverter;
import edu.dufe.student.registry.dao.StudentRegistryDao;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.entity.StudentRegistryModel;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.service.StudentRegistryService;
import edu.dufe.student.registry.utils.ImageUtils;
import edu.dufe.student.registry.utils.ZipUtils;

@Service
public class StudentRegistryServiceImpl implements StudentRegistryService {
	
	@Resource
	private StudentRegistryDao studentRegistryDao;
	@Resource
	private StudentRegistryConverter studentRegistryConverter;
	@Resource
	private ImageUtils imageUtils;
	@Resource
	private ZipUtils zipUtils;
	
	public Long submitRegistry(StudentRegistryData studentRegistryData){
		StudentRegistryModel studentRegistryModel = null;
		
		//check if identity id already exists
		Long count = studentRegistryDao.countByStudentIdentityNumOrStudentStudyNum(studentRegistryData.getStudentIdentityNum(), studentRegistryData.getStudentStudyNum());
		if(count!=null&&count>0){
			//throw error
			throw new RestfulException(ErrorDictionary.REGISTRY_IDENDITY_DUPLICATED);
		}else{
			//save registry and image
			try{
				imageUtils.base64StringToImage(studentRegistryData.getImageContent(), studentRegistryData.getStudentIdentityNum());
			}catch(Exception errorCreate){
				errorCreate.printStackTrace();
				throw new RestfulException(ErrorDictionary.IMAGE_FAILED_TO_CREATE);
			}
			
			try{
				studentRegistryModel = studentRegistryConverter.dataToModel(studentRegistryData, new StudentRegistryModel());	
				studentRegistryModel = studentRegistryDao.save(studentRegistryModel);
			}catch(Exception errorDB){
				//delete image in case DB meets any error
				try{
					imageUtils.deleteImage(studentRegistryData.getStudentIdentityNum());
				}catch(Exception errorDelete){
					throw new RestfulException(ErrorDictionary.IMAGE_FAILED_TO_DELETE);
				}
				
			}
			
		}
		
		return studentRegistryModel.getPk();
	}

	@Override
	public StudentRegistryData findRegistryByIdentityNum(String identityNum) {
		StudentRegistryData studentRegistryData = new StudentRegistryData();
		StudentRegistryModel studentRegistryModel = studentRegistryDao.findByStudentIdentityNum(identityNum);
		
		if(studentRegistryModel!=null&&studentRegistryModel.getPk()!=null){
			//found data
			studentRegistryData = studentRegistryConverter.modelToData(studentRegistryModel);
			String imageBase64 = "";
			try{
				imageBase64 = imageUtils.imageToBase64(identityNum);
			}catch(Exception errorCreate){
				studentRegistryData.setImageContent("");
//				throw new RestfulException(ErrorDictionary.IMAGE_FAILED_TO_READ);
			}
			studentRegistryData.setImageContent(imageBase64);
		}
		return studentRegistryData;
	}

	@Override
	public void deleteStudentRegistryByIdentityNum(String studentIdentityNum) {
		try{
			imageUtils.deleteImage(studentIdentityNum);
		}catch(Exception errorDelete){
			throw new RestfulException(ErrorDictionary.IMAGE_FAILED_TO_DELETE);
		}
		studentRegistryDao.deleteByStudentIdentityNum(studentIdentityNum);
	}

	@Override
	public ArrayList<StudentRegistryData> findAllRegistry() {
		List<StudentRegistryModel> modelList = studentRegistryDao.findAll();
		if(modelList==null||modelList.size()==0){
			//will create empty excel
			modelList = new ArrayList<StudentRegistryModel>();
		}
		ArrayList<StudentRegistryData> dataList = new ArrayList<StudentRegistryData>();
		for(StudentRegistryModel model:modelList){
			dataList.add(studentRegistryConverter.modelToData(model));
		}
		return dataList;
	}

	@Override
	public ArrayList<String> generateZipFiles(){
		try {
			return zipUtils.zipAllImages();
		} catch (Exception e) {
			throw new RestfulException(ErrorDictionary.ZIP_FAILED_TO_CREATE);
		}
	}

	@Override
	public void downloadZipFile(String zipFileName, HttpServletResponse response) {
		try {
			zipUtils.downloadZip(zipFileName, response);
		} catch (Exception e) {
			throw new RestfulException(ErrorDictionary.ZIP_FAILED_TO_DOWNLOAD);
		}
	}
}

package edu.dufe.student.registry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.converter.SiteManageConverter;
import edu.dufe.student.registry.dao.SiteManageDao;
import edu.dufe.student.registry.dao.StudentRegistryDao;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.entity.SiteManageModel;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.service.SiteManageService;
import edu.dufe.student.registry.utils.ZipUtils;

@Service
public class SiteManageServiceImpl implements SiteManageService {
	
	@Value("${image.folder}")
    private String imageFolder;

	@Resource
	private SiteManageDao siteManageDao;
	@Resource
	private StudentRegistryDao studentRegistryDao;
	@Resource
	private SiteManageConverter siteManageConverter;
	@Resource
	private ZipUtils zipUtils;
	
	@Override
	public SiteManageData loadSiteManageData() {
		List<SiteManageModel> modelList = siteManageDao.findAll();
		if(modelList==null||modelList.size()==0){
			return new SiteManageData();
		}else if(modelList.size()>1){
			throw new RestfulException(ErrorDictionary.SITE_MANAGE_IDENDITY_DUPLICATED);
		}else{
			SiteManageData data = siteManageConverter.modelToData(modelList.get(0));
			return data;
		}
	}
	
	@Override
	@Transactional
	public Long saveSiteManageData(SiteManageData siteManageData) {
		SiteManageModel model = new SiteManageModel();
		Long pk = siteManageData.getPk();
		
		if(pk!=null){
			model = siteManageDao.findByPk(pk);
			if(model==null){
				siteManageDao.deleteAll();
				model = new SiteManageModel();
			}
		}
		
		model = siteManageConverter.dataToModel(siteManageData, model);
		siteManageDao.save(model);
		
		return model.getPk();
	}

	
	@Override
	@Transactional
	public void clearAllData(){
		try {
			studentRegistryDao.deleteAll();
		} catch (Exception e) {
			throw new RestfulException(ErrorDictionary.SITE_MANAGE_FAILED_TO_DELETE_REGISTRY_DATA);
		}
		
		try {
			zipUtils.deleteAllFiles(imageFolder);
		} catch (Exception e) {
			throw new RestfulException(ErrorDictionary.IMAGE_FAILED_TO_DELETE);
		}
	}
	
}

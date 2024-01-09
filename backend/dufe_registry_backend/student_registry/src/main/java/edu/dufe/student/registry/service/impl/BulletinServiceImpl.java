package edu.dufe.student.registry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.converter.BulletinConverter;
import edu.dufe.student.registry.dao.BulletinDao;
import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.entity.BulletinModel;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.service.BulletinService;

@Service
public class BulletinServiceImpl implements BulletinService {

	@Resource
	private BulletinDao bulletinDao;
	@Resource
	private BulletinConverter bulletinConverter;
	@Override
	public BulletinData loadBulletinContent() {
		List<BulletinModel> modelList = bulletinDao.findAll();
		if(modelList==null||modelList.size()==0){
			return new BulletinData();
		}else if(modelList.size()>1){
			throw new RestfulException(ErrorDictionary.BULLETIN_IDENDITY_DUPLICATED);
		}else{
			BulletinData data = bulletinConverter.modelToData(modelList.get(0));
			return data;
		}
	}
	
	@Override
	@Transactional
	public Long saveBulletinData(BulletinData bulletinData) {
		BulletinModel model = new BulletinModel();
		Long pk = bulletinData.getPk();
		
		if(pk!=null){
			model = bulletinDao.findByPk(pk);
			if(model==null){
				bulletinDao.deleteAll();
				model = new BulletinModel();
			}
		}
		
		model = bulletinConverter.dataToModel(bulletinData, model);
		bulletinDao.save(model);
		
		return model.getPk();
	}



}

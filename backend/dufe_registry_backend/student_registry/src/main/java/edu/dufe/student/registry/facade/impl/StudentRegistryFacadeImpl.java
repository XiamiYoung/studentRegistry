package edu.dufe.student.registry.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.facade.StudentRegistryFacade;
import edu.dufe.student.registry.service.BulletinService;
import edu.dufe.student.registry.service.SiteManageService;
import edu.dufe.student.registry.service.StudentRegistryService;

@Service
public class StudentRegistryFacadeImpl implements StudentRegistryFacade {
	@Resource
	private StudentRegistryService studentRegistryService;
	
	@Resource
	private BulletinService bulletinService;
	
	@Resource
	private SiteManageService siteManageService;
	
	public Long submitRegistry(StudentRegistryData StudentRegistryData){
		return studentRegistryService.submitRegistry(StudentRegistryData);
	}
	
	@Override
	public BulletinData findBulletinContent() {
		return bulletinService.loadBulletinContent();
	}
	
	@Override
	public SiteManageData loadSiteManageData() {
		return siteManageService.loadSiteManageData();
	}
}

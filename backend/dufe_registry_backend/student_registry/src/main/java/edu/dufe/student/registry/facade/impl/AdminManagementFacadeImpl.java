package edu.dufe.student.registry.facade.impl;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.facade.AdminManagementFacade;
import edu.dufe.student.registry.service.BulletinService;
import edu.dufe.student.registry.service.SiteManageService;
import edu.dufe.student.registry.service.StudentRegistryService;

@Service
public class AdminManagementFacadeImpl implements AdminManagementFacade {

	@Resource
	private StudentRegistryService studentRegistryService;
	
	@Resource
	private BulletinService bulletinService;
	
	@Resource
	private SiteManageService siteManageService;
	
	@Override
	public StudentRegistryData findRegistryByIdentityNum(String identityNum) {
		return studentRegistryService.findRegistryByIdentityNum(identityNum);
	}

	@Override
	public void deleteStudentRegistryByIdentityNum(String studentIdentityNum) {
		studentRegistryService.deleteStudentRegistryByIdentityNum(studentIdentityNum);
	}

	@Override
	public ArrayList<StudentRegistryData> findAllRegistry() {
		return studentRegistryService.findAllRegistry();
	}

	@Override
	public ArrayList<String> generateZipFiles() {
		return studentRegistryService.generateZipFiles();
	}

	@Override
	public void downloadZipFile(String zipFileName, HttpServletResponse response) {
		studentRegistryService.downloadZipFile(zipFileName, response);
	}

	@Override
	public BulletinData findBulletinContent() {
		return bulletinService.loadBulletinContent();
	}

	@Override
	public Long submitBulletin(BulletinData bulletinData) {
		return bulletinService.saveBulletinData(bulletinData);
	}

	@Override
	public SiteManageData loadSiteManageData() {
		return siteManageService.loadSiteManageData();
	}

	@Override
	public Long saveSiteManageData(SiteManageData siteManageData) {
		return siteManageService.saveSiteManageData(siteManageData);
	}

	@Override
	public void clearAllData() {
		siteManageService.clearAllData();
	}
}

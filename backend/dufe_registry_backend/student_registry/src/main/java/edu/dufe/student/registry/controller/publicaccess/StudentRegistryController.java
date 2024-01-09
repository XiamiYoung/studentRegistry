package edu.dufe.student.registry.controller.publicaccess;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.controller.base.PublicAccessBaseController;
import edu.dufe.student.registry.controller.validator.ControllerValidator;
import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.data.out.BulletinResData;
import edu.dufe.student.registry.data.out.SiteManageResData;
import edu.dufe.student.registry.data.out.SyncPKResData;
import edu.dufe.student.registry.exception.RestfulException;
import edu.dufe.student.registry.facade.StudentRegistryFacade;

@RestController
@RequestMapping("/student/registry")
public class StudentRegistryController extends PublicAccessBaseController{
	
	@Resource
	private StudentRegistryFacade studentRegistryFacade;
	
	@RequestMapping(value= "/submit",method = RequestMethod.POST)
	public SyncPKResData submitRegistry(@RequestBody StudentRegistryData StudentRegistryData) {
		SiteManageData siteManageData = studentRegistryFacade.loadSiteManageData();
		if(siteManageData!=null&&!siteManageData.getIsSiteOpen()){
			throw new RestfulException(ErrorDictionary.SITE_MANAGE_SITE_CLOSED);
		}
		ControllerValidator.checkInbound(StudentRegistryData);
		SyncPKResData syncPKResData = new SyncPKResData();
		Long pk = studentRegistryFacade.submitRegistry(StudentRegistryData);
		syncPKResData.setPk(pk);
		return syncPKResData;
	}
	
	@RequestMapping(value= "/bulletin",method = RequestMethod.GET)
	public BulletinResData getBulletin() throws Exception {
		BulletinResData bulletinResData = new BulletinResData();
		BulletinData bulletinData = studentRegistryFacade.findBulletinContent();
		bulletinResData.setBulletinData(bulletinData);
		return bulletinResData;
	}
	
	@RequestMapping(value= "/site/manage",method = RequestMethod.GET)
	public SiteManageResData getSiteManageData() throws Exception {
		SiteManageResData siteManageResData = new SiteManageResData();
		SiteManageData siteManageData = studentRegistryFacade.loadSiteManageData();
		siteManageResData.setSiteManageData(siteManageData);
		return siteManageResData;
	}
}

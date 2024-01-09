package edu.dufe.student.registry.controller.adminconsole;


import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.dufe.student.registry.controller.base.AdminBaseController;
import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.data.out.BulletinResData;
import edu.dufe.student.registry.data.out.DefaultResData;
import edu.dufe.student.registry.data.out.GenerateZipResData;
import edu.dufe.student.registry.data.out.SiteManageResData;
import edu.dufe.student.registry.data.out.StudentRegistryResData;
import edu.dufe.student.registry.data.out.SyncPKResData;
import edu.dufe.student.registry.facade.AdminManagementFacade;
import edu.dufe.student.registry.utils.ExcelUtils;

@RestController
@RequestMapping("/admin")
public class AdminConsoleManagementController extends AdminBaseController{
	
	@Resource
	private AdminManagementFacade adminManagementFacade;
	
	@RequestMapping(value= "/registry/{identityNum}",method = RequestMethod.GET)
	public StudentRegistryResData queryRegistry(@PathVariable("identityNum") String identityNum) throws Exception {
		StudentRegistryResData studentRegistryResData = new StudentRegistryResData();
		StudentRegistryData studentRegistryData = adminManagementFacade.findRegistryByIdentityNum(identityNum);
		studentRegistryResData.setStudentRegistryData(studentRegistryData);
		return studentRegistryResData;
	}
	
	@RequestMapping(value= "/registry/{identityNum}",method = RequestMethod.DELETE)
	public DefaultResData deleteRegistry(@PathVariable("identityNum") String identityNum) throws Exception {
		adminManagementFacade.deleteStudentRegistryByIdentityNum(identityNum);
		DefaultResData defaultResData = new DefaultResData();
		return defaultResData;
	}
	
	@RequestMapping(value= "/registry/export",method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response) throws Exception {
		ArrayList<StudentRegistryData> dataList = adminManagementFacade.findAllRegistry();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=报名信息表.xlsx");
        ByteArrayInputStream stream = ExcelUtils.generateExcelForRegistries(dataList);
        IOUtils.copy(stream, response.getOutputStream());
    }
	
	@RequestMapping(value= "/registry/zip/generate",method = RequestMethod.GET)
	public GenerateZipResData generateZip() throws Exception {
		GenerateZipResData generateZipResData = new GenerateZipResData();
		ArrayList<String> zipFileNames = adminManagementFacade.generateZipFiles();
		generateZipResData.setZipFileNames(zipFileNames);
		return generateZipResData;
    }
	
	@RequestMapping(value= "/registry/zip/download/{zipFileName}",method = RequestMethod.GET)
	public void exportExcel(@PathVariable("zipFileName") String zipFileName, HttpServletResponse response) throws Exception {
		String decodedName = URLDecoder.decode(zipFileName, "UTF-8");
		adminManagementFacade.downloadZipFile(decodedName, response);
    }
	
	@RequestMapping(value= "/bulletin",method = RequestMethod.GET)
	public BulletinResData getBulletin() throws Exception {
		BulletinResData bulletinResData = new BulletinResData();
		BulletinData bulletinData = adminManagementFacade.findBulletinContent();
		bulletinResData.setBulletinData(bulletinData);
		return bulletinResData;
	}
	
	@RequestMapping(value= "/bulletin",method = RequestMethod.POST)
	public SyncPKResData submiBulletinContent(@RequestBody BulletinData bulletinData) {
		SyncPKResData syncPKResData = new SyncPKResData();
		Long pk = adminManagementFacade.submitBulletin(bulletinData);
		syncPKResData.setPk(pk);
		return syncPKResData;
	}
	
	@RequestMapping(value= "/site/manage",method = RequestMethod.GET)
	public SiteManageResData getSiteManageData() throws Exception {
		SiteManageResData siteManageResData = new SiteManageResData();
		SiteManageData siteManageData = adminManagementFacade.loadSiteManageData();
		siteManageResData.setSiteManageData(siteManageData);
		return siteManageResData;
	}
	
	@RequestMapping(value= "/site/manage",method = RequestMethod.POST)
	public SyncPKResData submiSiteManageData(@RequestBody SiteManageData siteManageData) {
		SyncPKResData syncPKResData = new SyncPKResData();
		Long pk = adminManagementFacade.saveSiteManageData(siteManageData);
		syncPKResData.setPk(pk);
		return syncPKResData;
	}
	
	@RequestMapping(value= "/site/clear",method = RequestMethod.DELETE)
	public void submiSiteClear() {
		adminManagementFacade.clearAllData();
	}
}

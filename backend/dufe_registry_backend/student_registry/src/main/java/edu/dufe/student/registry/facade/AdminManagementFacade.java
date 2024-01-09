package edu.dufe.student.registry.facade;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.in.StudentRegistryData;

public interface AdminManagementFacade {
	public StudentRegistryData findRegistryByIdentityNum(String identityNum);
	public ArrayList<StudentRegistryData> findAllRegistry();
	public void deleteStudentRegistryByIdentityNum(String studentIdentityNum);
	public ArrayList<String> generateZipFiles();
	public void downloadZipFile(String zipFileName, HttpServletResponse response);
	public BulletinData findBulletinContent();
	public Long submitBulletin(BulletinData bulletinData);
	public SiteManageData loadSiteManageData();
	public Long saveSiteManageData(SiteManageData siteManageData);
	public void clearAllData();
}

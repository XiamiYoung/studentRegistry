package edu.dufe.student.registry.service;

import edu.dufe.student.registry.data.in.SiteManageData;

public interface SiteManageService{
	public SiteManageData loadSiteManageData();
	public Long saveSiteManageData(SiteManageData siteManageData);
	public void clearAllData();
}

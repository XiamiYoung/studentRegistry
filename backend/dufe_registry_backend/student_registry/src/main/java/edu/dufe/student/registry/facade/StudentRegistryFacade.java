package edu.dufe.student.registry.facade;

import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.in.StudentRegistryData;

public interface StudentRegistryFacade {
	public Long submitRegistry(StudentRegistryData StudentRegistryData);
	public BulletinData findBulletinContent();
	public SiteManageData loadSiteManageData();
}

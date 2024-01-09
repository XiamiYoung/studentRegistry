package edu.dufe.student.registry.data.out;

import edu.dufe.student.registry.data.in.SiteManageData;
import edu.dufe.student.registry.data.out.base.BaseResBody;

public class SiteManageResData extends BaseResBody {
	private SiteManageData siteManageData;

	public SiteManageData getSiteManageData() {
		return siteManageData;
	}

	public void setSiteManageData(SiteManageData siteManageData) {
		this.siteManageData = siteManageData;
	}
}

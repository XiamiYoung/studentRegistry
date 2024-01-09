package edu.dufe.student.registry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import edu.dufe.student.registry.entity.base.BaseEntityModel;

@Entity
@Table(name = "tbl_site_manage")
public class SiteManageModel extends BaseEntityModel {
	@Column
	@Lob
	private Boolean isSiteOpen;

	public Boolean getIsSiteOpen() {
		return isSiteOpen;
	}

	public void setIsSiteOpen(Boolean isSiteOpen) {
		this.isSiteOpen = isSiteOpen;
	}
	
}

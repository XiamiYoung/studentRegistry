package edu.dufe.student.registry.service;

import edu.dufe.student.registry.data.in.BulletinData;

public interface BulletinService{
	public BulletinData loadBulletinContent();
	public Long saveBulletinData(BulletinData bulletinData);
}

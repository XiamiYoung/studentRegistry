package edu.dufe.student.registry.data.out;

import edu.dufe.student.registry.data.in.BulletinData;
import edu.dufe.student.registry.data.out.base.BaseResBody;

public class BulletinResData extends BaseResBody {
	private BulletinData bulletinData;

	public BulletinData getBulletinData() {
		return bulletinData;
	}

	public void setBulletinData(BulletinData bulletinData) {
		this.bulletinData = bulletinData;
	}

	
}

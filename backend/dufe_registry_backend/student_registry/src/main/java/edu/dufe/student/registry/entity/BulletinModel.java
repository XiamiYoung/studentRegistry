package edu.dufe.student.registry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import edu.dufe.student.registry.entity.base.BaseEntityModel;

@Entity
@Table(name = "tbl_bulletin")
public class BulletinModel extends BaseEntityModel {
	@Column
	@Lob
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

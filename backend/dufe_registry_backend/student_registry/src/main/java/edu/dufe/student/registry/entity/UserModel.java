package edu.dufe.student.registry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import edu.dufe.student.registry.entity.base.BaseEntityModel;

@Entity
@Table(name = "tbl_user",uniqueConstraints = {@UniqueConstraint(columnNames="userName")})
public class UserModel extends BaseEntityModel {
	@Column
	private String userName;
	@Column
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

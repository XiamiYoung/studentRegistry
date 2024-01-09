package edu.dufe.student.registry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import edu.dufe.student.registry.entity.base.BaseEntityModel;

@Entity
@Table(name = "tbl_registry",uniqueConstraints = {@UniqueConstraint(columnNames="studentIdentityNum")})
public class StudentRegistryModel extends BaseEntityModel {
	@Column
	private String studentName;
	@Column
	private String studentSex;
	@Column
	private String studentStudyNum;
	@Column
	private String studentGrade;
	@Column
	private String studentMajor;
	@Column
	private String studentIdentityNum;
	@Column
	private String studentTel;
	@Column
	private String assistUnit;
	@Column
	private String language;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public String getStudentStudyNum() {
		return studentStudyNum;
	}
	public void setStudentStudyNum(String studentStudyNum) {
		this.studentStudyNum = studentStudyNum;
	}
	public String getStudentGrade() {
		return studentGrade;
	}
	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}
	public String getStudentMajor() {
		return studentMajor;
	}
	public void setStudentMajor(String studentMajor) {
		this.studentMajor = studentMajor;
	}
	public String getStudentIdentityNum() {
		return studentIdentityNum;
	}
	public void setStudentIdentityNum(String studentIdentityNum) {
		this.studentIdentityNum = studentIdentityNum;
	}
	public String getStudentTel() {
		return studentTel;
	}
	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}
	public String getAssistUnit() {
		return assistUnit;
	}
	public void setAssistUnit(String assistUnit) {
		this.assistUnit = assistUnit;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}

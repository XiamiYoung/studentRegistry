package edu.dufe.student.registry.data.in;

import edu.dufe.student.registry.data.in.base.BaseData;

public class StudentRegistryData extends BaseData{
	
	private String studentName;
	private String studentSex;
	private String studentStudyNum;
	private String studentGrade;
	private String studentMajor;
	private String studentIdentityNum;
	private String studentTel;
	private String imageContent;
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
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getImageContent() {
		return imageContent;
	}
	public void setImageContent(String imageContent) {
		this.imageContent = imageContent;
	}
}

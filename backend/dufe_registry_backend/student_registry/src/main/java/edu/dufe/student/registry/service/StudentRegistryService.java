package edu.dufe.student.registry.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import edu.dufe.student.registry.data.in.StudentRegistryData;

public interface StudentRegistryService {
	public Long submitRegistry(StudentRegistryData StudentRegistryData);
	public StudentRegistryData findRegistryByIdentityNum(String identityNum);
	public void deleteStudentRegistryByIdentityNum(String studentIdentityNum);
	public ArrayList<StudentRegistryData> findAllRegistry();
	public ArrayList<String> generateZipFiles();
	public void downloadZipFile(String zipFileName, HttpServletResponse response);
}

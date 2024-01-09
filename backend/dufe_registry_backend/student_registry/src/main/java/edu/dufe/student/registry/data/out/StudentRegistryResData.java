package edu.dufe.student.registry.data.out;

import edu.dufe.student.registry.data.in.StudentRegistryData;
import edu.dufe.student.registry.data.out.base.BaseResBody;

public class StudentRegistryResData extends BaseResBody {
	private StudentRegistryData StudentRegistryData;

	public StudentRegistryData getStudentRegistryData() {
		return StudentRegistryData;
	}

	public void setStudentRegistryData(StudentRegistryData studentRegistryData) {
		StudentRegistryData = studentRegistryData;
	}
	
}

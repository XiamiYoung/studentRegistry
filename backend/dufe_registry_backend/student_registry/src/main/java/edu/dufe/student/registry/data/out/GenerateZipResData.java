package edu.dufe.student.registry.data.out;

import java.util.ArrayList;

import edu.dufe.student.registry.data.out.base.BaseResBody;

public class GenerateZipResData extends BaseResBody {
	private ArrayList<String> zipFileNames;

	public ArrayList<String> getZipFileNames() {
		return zipFileNames;
	}

	public void setZipFileNames(ArrayList<String> zipFileNames) {
		this.zipFileNames = zipFileNames;
	}


}

package edu.dufe.student.registry.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.exception.RestfulException;

@Component
public class ZipUtils {
	@Value("${zip.folder}")
    private String zipFolder;
	@Value("${zip.entry.limit}")
    private int zipFileCount;
	@Value("${image.folder}")
    private String imageFolder;
	
	public ArrayList<String> zipAllImages() throws Exception{
		//find all files
		File imageFileFolder = new File(imageFolder);
		File[] imageFiles = imageFileFolder.listFiles();
		if(imageFiles == null || imageFiles.length == 0 || zipFileCount == 0){
			throw new RestfulException(ErrorDictionary.NO_ZIP_FILE_FOUND);
		}
		
		//create folder if not exists
		mkdirsIfNotExists();
		
		//delete files if exists
		deleteAllFiles(zipFolder);
		
		//create zip file one by one
		int imageFileCounter = 0;
		int zipFileCounter = 1;
		String rawZipFileName = "照片";
		String zipFieName = rawZipFileName;
		FileInputStream fis = null;
	    BufferedInputStream bins = null;
	    FileOutputStream fos = new FileOutputStream(new File(zipFolder + File.separator + zipFieName + ".zip"));
	    ZipOutputStream zos = new ZipOutputStream(fos);
	    ArrayList<String> zipFileNames = new ArrayList<String>();
		for(File imageFile: imageFiles){
			try {
				fis = new FileInputStream(imageFile);
		        bins = new BufferedInputStream(fis, 1024);
		        ZipEntry entry = new ZipEntry(imageFile.getName());
		        zos.putNextEntry(entry);
		        
		        byte[] buffer = new byte[1024];
		        int len = -1;
		        while ((len = bins.read(buffer)) != -1) {
		        	zos.write(buffer, 0, len);
		        }
		        
		        zos.closeEntry();
		        
		        imageFileCounter++;
		        
		        if(imageFileCounter == imageFiles.length){
		        	//if reaching the last file?
		        	//finish process
		        	zipFileNames.add(zipFieName);
		        	zos.flush();
		        	zos.close();
		        	fos.close();
		        	break;
		        }else if(imageFileCounter%zipFileCount == 0){
		        	//if reaching single zip file count limit?
		        	zipFileNames.add(zipFieName);
		        	zos.flush();
		        	zos.close();
		        	//creating new zip
		        	zipFileCounter++;
		        	zipFieName = rawZipFileName + zipFileCounter;
		        	fos = new FileOutputStream(new File(zipFolder + File.separator + zipFieName + ".zip"));
		    	    zos = new ZipOutputStream(fos);
		        }
		        
		    } catch (Exception e) {
		        throw e;
		    }finally{
		    	if(bins!=null){
		    		bins.close();
		    	}
		    	if(fis!=null){
		    		fis.close();
		    	}
		    }
		}
		return zipFileNames;
	}
	
	
	public void downloadZip(String zipFileName, HttpServletResponse response) throws Exception {
		File zipFileFolder = new File(zipFolder + File.separator + zipFileName + ".zip");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
	    try {
	    	if(!zipFileFolder.exists()){
	    		throw new RestfulException(ErrorDictionary.NO_ZIP_FILE_FOUND);
	    	}
	    	
	    	bis = new BufferedInputStream(new FileInputStream(zipFileFolder));
	        byte[] buffer = new byte[bis.available()];
	        bis.read(buffer);

	        bos = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;filename=" + new String(zipFileFolder.getName().getBytes("utf-8"), "ISO8859-1"));
	        bos.write(buffer);
	        bos.flush();
	    } catch (Exception ex) {
	    	throw new RestfulException(ErrorDictionary.ZIP_FAILED_TO_DOWNLOAD);
	    } finally {
	        if(zipFileFolder.exists()){
	        	zipFileFolder.delete();
	        }
	        if(bis!=null){
	        	bis.close();
	    	}
	    	if(bos!=null){
	    		bos.close();
	    	}
	    }
	}
	
	private void mkdirsIfNotExists(){
    	File targetFolder = new File(zipFolder);
    	if(!targetFolder.exists()){
    		targetFolder.mkdirs();
    	}
    }
	
	public void deleteAllFiles(String folderPath){
    	File targetFolder = new File(folderPath);
    	for(File file: targetFolder.listFiles()){
    		file.delete();
    	}
    }
}

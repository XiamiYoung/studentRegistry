package edu.dufe.student.registry.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Component
public class ImageUtils {
	
	@Value("${image.folder}")
    private String imageFolder;

    @Value("${image.type}")
    
    private String imageType;
    @Value("${image.base64.prefix}")
    private String imagePrefix;
    

    public String imageToBase64(String imageFileName) throws Exception {
    	mkdirsIfNotExists();
    	String fileFullPath = imageFolder + File.separator + imageFileName + "." + imageType;
        File imageFile = new File(fileFullPath);
        BufferedImage bi = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, imageType, baos);
        byte[] bytes = baos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        return imagePrefix + encoder.encodeBuffer(bytes).trim();
    }

    public void base64StringToImage(String rawbase64String, String imageFileName) throws Exception {
    	mkdirsIfNotExists();
    	BASE64Decoder decoder = new BASE64Decoder();
    	String base64ImageStr = rawbase64String.split(",")[1];
        byte[] bytes = decoder.decodeBuffer(base64ImageStr);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(bais);
        String fileFullPath = imageFolder + File.separator + imageFileName + "." + imageType;
        File imageFile = new File(fileFullPath);
        ImageIO.write(bi, imageType, imageFile);
    }
    
    public void deleteImage(String imageFileName) throws Exception {
        String fileFullPath = imageFolder + File.separator + imageFileName + "." + imageType;
        File imageFile = new File(fileFullPath);
        imageFile.delete();
    }
    
    private void mkdirsIfNotExists(){
    	File targetFolder = new File(imageFolder);
    	if(!targetFolder.exists()){
    		targetFolder.mkdirs();
    	}
    }
}

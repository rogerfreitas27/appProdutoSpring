package br.com.appproduto.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import org.apache.commons.logging.impl.Log4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;



@Service
public class ArmazenamentoService {

	Logger log = LoggerFactory.getLogger(ArmazenamentoService.class);
	@Value("${aws.s3.audio.bucket}")
    private String bucketName;

   
    private AmazonS3 s3Client;
	
	public ArmazenamentoService (AmazonS3 s3Client) {
		this.s3Client= s3Client;
	}
	
	
	 public String uploadFile(MultipartFile file) {
	        File fileObj = convertMultiPartFileToFile(file);
	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	           
	        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj ).withCannedAcl(CannedAccessControlList.PublicRead));
	       URL endereco = s3Client.getUrl(bucketName, fileName);
	        String url = endereco.toString();
	      
	       
			
			fileObj.delete();  	        
	        return url;
	    }


	    public byte[] downloadFile(String fileName) {
	        S3Object s3Object = s3Client.getObject(bucketName, fileName);
	        S3ObjectInputStream inputStream = s3Object.getObjectContent();
	        try {
	            byte[] content = IOUtils.toByteArray(inputStream);
	            return content;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removendo ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Erro  ao  converter arquivo", e);
        }
        return convertedFile;
    }
}

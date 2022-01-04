package br.com.appproduto.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class ArmazenamentoConfiguration {
	
	@Value("${aws.access.key.id}")
	private String accessKey;
	
	@Value("${aws.access.key.secret}")
	private String acessSecret;
	
	@Value("${aws.region}")
	private String region;
	
	@Bean
	public AmazonS3 S3Client() {
		AWSCredentials credentials=new BasicAWSCredentials(accessKey=accessKey, accessKey=acessSecret);
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(region).build();
	}
}




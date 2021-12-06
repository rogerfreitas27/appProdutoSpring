package br.com.appproduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppprodutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppprodutoApplication.class, args);
	}
	
	@Bean
	public  BCryptPasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
		return encoder;
	}

}

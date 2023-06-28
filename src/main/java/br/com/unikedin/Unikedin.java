package br.com.unikedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.unikedin.model")
public class Unikedin {

	public static void main(String[] args) {
		SpringApplication.run(Unikedin.class, args);
	}

}

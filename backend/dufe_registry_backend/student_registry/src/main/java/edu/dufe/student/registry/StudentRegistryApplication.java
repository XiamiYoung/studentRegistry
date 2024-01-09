package edu.dufe.student.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"edu.dufe.student.registry.entity"} )
public class StudentRegistryApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentRegistryApplication.class, args);
	}
}
 
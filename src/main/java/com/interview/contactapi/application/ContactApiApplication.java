package com.interview.contactapi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application class, main method is the the entry point for the
 * contact api calls. Base package "com.interview.contactapi" being scanned
 * to identify all spring beans. Application can run in following ways:
 * 1. Running main method of this class in the IDE
 * 2. Running java -jar contactapi-1.0.0-RC.war command. WAR file is
 * generated at"/build/libs/contactapi-1.0.0-RC.war".
 */
@SpringBootApplication(scanBasePackages = "com.interview.contactapi")
public class ContactApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApiApplication.class, args);
	}
}

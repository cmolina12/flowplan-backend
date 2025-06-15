package com.cmolina12.flowplan_backend;

import javax.xml.crypto.Data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FlowplanBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowplanBackendApplication.class, args);
	}

}

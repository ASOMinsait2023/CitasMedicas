package com.misait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CitasMedicasDoctoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasDoctoresApplication.class, args);
	}

}

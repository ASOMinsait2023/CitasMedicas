package com.minsait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CitasMedicasPacienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasPacienteApplication.class, args);
	}

}

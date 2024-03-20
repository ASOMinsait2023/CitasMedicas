package com.minsait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CitasMedicasConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasConfigApplication.class, args);
	}

}

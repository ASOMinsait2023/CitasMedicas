package com.minsait;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableDiscoveryClient//THIS ANNOTATION IS USED TO MAKE THE SERVICE REGISTER ITSELF WITH THE DISCOVERY SERVER
@EnableFeignClients//THIS ANNOTATION IS USED TO ENABLE THE FEIGN CLIEN

class CitasMedicasDoctorApplicationTests {

	@Test
	void contextLoads() {
	}

}

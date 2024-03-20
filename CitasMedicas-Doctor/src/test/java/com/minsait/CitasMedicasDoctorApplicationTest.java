package com.minsait;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(CitasMedicasDoctorApplicationTest.class)
class CitasMedicasDoctorApplicationTest {

    @Test
    void main() {
        String[] args = {"Hola"};
        SpringApplication springApplicationMock = Mockito.mock(SpringApplication.class);
        CitasMedicasDoctorApplication.main(args);
    }
}
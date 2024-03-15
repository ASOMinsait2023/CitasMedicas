package com.minsait;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(CitasMedicasCitasApplicationTest.class)
class CitasMedicasCitasApplicationTest {

    @Test
    void main() {
        String[] args = {"Hola"};
        SpringApplication springApplicationMock = Mockito.mock(SpringApplication.class);
        CitasMedicasDoctorApplication.main(args);
    }
}
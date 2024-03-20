package com.minsait;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class CitasMedicasPacienteApplicationTest {

    @Test
    void main() {
        String[] args = {"Hola"};
        SpringApplication springApplicationMock = Mockito.mock(SpringApplication.class);
        CitasMedicasPacienteApplication.main(args);
    }
}
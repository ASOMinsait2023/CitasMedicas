package com.minsait.service;

import com.minsait.models.Paciente;
import com.minsait.repository.IPacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class PacienteServiceTest {

    @Mock
    private IPacienteRepository repository;

    @InjectMocks
    private PacienteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);
        paciente1.setName("Jorge");

        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);
        paciente2.setName("Exel");

        when(repository.findAll()).thenReturn(Arrays.asList(paciente1, paciente2));
        List<Paciente> pacientes = service.findAll();
        assertEquals(2, pacientes.size());

    }

    @Test
    void findById() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setName("Juan");

        when(repository.findById(1L)).thenReturn(Optional.of(paciente));
        Paciente resultado = service.findById(1L);
        assertEquals(paciente, resultado);
    }

    @Test
    void save() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setName("Jorge");
        paciente.setLastname("Cuellar");
        paciente.setNss("12345");

        when(repository.save(paciente)).thenReturn(paciente);
        Paciente resultado = service.save(paciente);
        assertEquals(paciente, resultado);
    }

    @Test
    void update() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setName("Juan1");
        paciente.setLastname("velazco1");
        paciente.setNss("123555");

        when(repository.save(paciente)).thenReturn(paciente);
        Paciente resultado = service.update(paciente);
        assertEquals(paciente, resultado);
    }

    @Test
    void deleteById() {
        Long pacienteId = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);

        when(repository.findById(pacienteId)).thenReturn(Optional.of(paciente));
        boolean result = service.deleteById(pacienteId);
        assertTrue(result);
    }

    @Test
    void deleteByIdException() {
        Long pacienteId = 1L;
        when(repository.findById(pacienteId)).thenReturn(Optional.empty());
        boolean result = service.deleteById(pacienteId);
        assertFalse(result);
    }
}
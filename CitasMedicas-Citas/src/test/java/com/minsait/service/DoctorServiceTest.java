package com.minsait.service;

import com.minsait.models.Doctor;
import com.minsait.repository.IDoctoresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig(DoctorServiceTest.class)
class DoctorServiceTest {

    @Mock
    private IDoctoresRepository repository;

    @InjectMocks
    private DoctorService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setName("Jorge");
        doctor1.setLastname("Cuellar");
        doctor1.setStatus((byte) 1L);

        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setName("Exel");
        doctor2.setName("Escamilla");
        doctor2.setStatus((byte) 1L);


        when(repository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));
        List<Doctor> pacientes = service.findAll();
        assertEquals(2, pacientes.size());
    }

    @Test
    void findById() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Juan");

        when(repository.findById(1L)).thenReturn(Optional.of(doctor));
        Doctor resultado = service.findById(1L);
        assertEquals(doctor, resultado);
    }

    @Test
    void save() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Jorge");
        doctor.setLastname("Cuellar");
        doctor.setStatus((byte) 1L);

        when(repository.save(doctor)).thenReturn(doctor);
        Doctor resultado = service.save(doctor);
        assertEquals(doctor, resultado);
    }

    @Test
    void update() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Juan1");
        doctor.setLastname("velazco1");
        doctor.setStatus((byte) 1L);

        when(repository.save(doctor)).thenReturn(doctor);
        Doctor resultado = service.update(doctor);
        assertEquals(doctor, resultado);
    }

    @Test
    void deleteById() {
        Long pacienteId = 1L;
        Doctor paciente = new Doctor();
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

    @Test
    void testFindContarEspecialidad() {
        Long idEspecialidad = 123L;

        List<String> resultadosEsperados = Arrays.asList("resultado1", "resultado2");
        when(repository.findContarEspecialidad(idEspecialidad)).thenReturn(resultadosEsperados);

        List<String> result = service.findContarEspecialidad(idEspecialidad);
        assertEquals(resultadosEsperados, result);
    }
}
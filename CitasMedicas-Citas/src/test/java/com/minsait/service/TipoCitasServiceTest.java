package com.minsait.service;

import com.minsait.models.TipoCitas;
import com.minsait.repository.ITipoCitasRepository;
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
@SpringJUnitConfig(TipoCitasServiceTest.class)
class TipoCitasServiceTest {

    @Mock
    private ITipoCitasRepository repository;

    @InjectMocks
    private TipoCitasService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void findAll() {

        TipoCitas tipoCitas = new TipoCitas();
        tipoCitas.setIdtipocita(1L);
        tipoCitas.setTipo_cita("General");

        TipoCitas tipoCitas2 = new TipoCitas();
        tipoCitas2.setIdtipocita(2L);
        tipoCitas2.setTipo_cita("Ginecologo");

        when(repository.findAll()).thenReturn(Arrays.asList(tipoCitas, tipoCitas2));
        List<TipoCitas> pacientes = service.findAll();
        assertEquals(2, pacientes.size());
    }

    @Test
    void findById() {
        TipoCitas tipoCitas = new TipoCitas();
        tipoCitas.setIdtipocita(1L);
        tipoCitas.setTipo_cita("Medico General");

        when(repository.findById(1L)).thenReturn(Optional.of(tipoCitas));
        TipoCitas resultado = service.findById(1L);
        assertEquals(tipoCitas, resultado);
    }

    @Test
    void save() {
        TipoCitas tipoCitas = new TipoCitas();
        tipoCitas.setIdtipocita(1L);
        tipoCitas.setTipo_cita("Medico General");

        when(repository.save(tipoCitas)).thenReturn(tipoCitas);
        TipoCitas resultado = service.save(tipoCitas);
        assertEquals(tipoCitas, resultado);
    }

    @Test
    void update() {
        TipoCitas paciente = new TipoCitas();
        paciente.setIdtipocita(1L);
        paciente.setTipo_cita("general1");

        when(repository.save(paciente)).thenReturn(paciente);
        TipoCitas resultado = service.update(paciente);
        assertEquals(paciente, resultado);
    }

    @Test
    void deleteById() {
        TipoCitas tipoCitas = new TipoCitas();
        tipoCitas.setIdtipocita(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(tipoCitas));
        boolean result = service.deleteById(1L);
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
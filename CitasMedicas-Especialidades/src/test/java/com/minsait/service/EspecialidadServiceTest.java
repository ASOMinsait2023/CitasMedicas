package com.minsait.service;

import com.minsait.models.Especialidades;
import com.minsait.repository.IEspecialidadRepository;
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
@SpringJUnitConfig(EspecialidadServiceTest.class)
class EspecialidadServiceTest {

    @Mock
    private IEspecialidadRepository repository;

    @InjectMocks
    private EspecialidadService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("Medico General");

        Especialidades especialidades2 = new Especialidades();
        especialidades2.setIdespecialidades(2L);
        especialidades2.setNombre_especialidad("Ginecologo");

        when(repository.findAll()).thenReturn(Arrays.asList(especialidades, especialidades2));
        List<Especialidades> pacientes = service.findAll();
        assertEquals(2, pacientes.size());

    }

    @Test
    void findById() {
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("Medico General");

        when(repository.findById(1L)).thenReturn(Optional.of(especialidades));
        Especialidades resultado = service.findById(1L);
        assertEquals(especialidades, resultado);
    }

    @Test
    void save() {
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("Medico General");

        when(repository.save(especialidades)).thenReturn(especialidades);
        Especialidades resultado = service.save(especialidades);
        assertEquals(especialidades, resultado);
    }

    @Test
    void deleteEspecialidadById() {
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(especialidades));
        boolean result = service.deleteEspecialidadById(1L);
        assertTrue(result);
    }

    @Test
    void findByEspecialidades() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        boolean result = service.deleteEspecialidadById(1L);
        assertFalse(result);
    }
}
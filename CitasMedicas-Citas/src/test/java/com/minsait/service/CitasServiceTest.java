package com.minsait.service;

import com.minsait.models.Citas;
import com.minsait.models.Doctor;
import com.minsait.repository.ICitasRepository;
import com.minsait.repository.IDoctoresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig(CitasServiceTest.class)
class CitasServiceTest {

    @Mock
    private ICitasRepository repository;

    @InjectMocks
    private CitasService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Citas citas = new Citas();
        citas.setIdCitas(1L);
        citas.setDescripcion("Requiero una cita medica");
        citas.setStatus((byte) 1);


        Citas citas2 = new Citas();
        citas2.setIdCitas(2L);
        citas2.setDescripcion("Requiero una cita medica");
        citas2.setStatus((byte) 1);


        when(repository.findAll()).thenReturn(Arrays.asList(citas, citas2));
        List<Citas> pacientes = service.findAll();
        assertEquals(2, pacientes.size());
    }

    @Test
    void findById() {
        Citas citas = new Citas();
        citas.setIdCitas(1L);
        citas.setDescripcion("Requiero una cita medica");
        citas.setStatus((byte) 1);

        when(repository.findById(1L)).thenReturn(Optional.of(citas));
        Citas resultado = service.findById(1L);
        assertEquals(citas, resultado);
    }

    @Test
    void save() {
        Citas citas = new Citas();
        citas.setIdCitas(1L);
        citas.setDescripcion("Requiero una cita medica");
        citas.setStatus((byte) 1);

        when(repository.save(citas)).thenReturn(citas);
        Citas resultado = service.save(citas);
        assertEquals(citas, resultado);
    }

    @Test
    void update() {
        Citas citas = new Citas();
        citas.setIdCitas(1L);
        citas.setDescripcion("Requiero una cita medica1");
        citas.setStatus((byte) 1);

        when(repository.save(citas)).thenReturn(citas);
        Citas resultado = service.update(citas);
        assertEquals(citas, resultado);
    }

    @Test
    void deleteById() {
        Long citaId = 1L;
        Citas citas = new Citas();
        citas.setIdCitas(citaId);

        when(repository.findById(citaId)).thenReturn(Optional.of(citas));
        boolean result = service.deleteById(citaId);
        assertTrue(result);
    }
    @Test
    void deleteByIdException() {
        Long citaId = 1L;

        when(repository.findById(citaId)).thenReturn(Optional.empty());
        boolean result = service.deleteById(citaId);
        assertFalse(result);
    }

    @Test
    void testFindCitasFechas() {
        Citas cita1 = new Citas();
        cita1.setIdCitas(1L);
        cita1.setFechaCita(LocalDateTime.parse("2024-03-12T12:18:00"));

        Citas cita2 = new Citas();
        cita2.setIdCitas(2L);
        cita2.setFechaCita(LocalDateTime.parse("2024-03-12T12:19:00"));

        when(repository.findCitasFechas()).thenReturn(Arrays.asList(cita1, cita2));

        List<Citas> citasList = service.findCitasFechas();

        assertEquals(2, citasList.size());
        assertEquals(cita1, citasList.get(0));
        assertEquals(cita2, citasList.get(1));

    }

    @Test
    void testFincontar() {
        List<String> resultadosEsperados = Arrays.asList("fecha1", "fecha2");
        when(repository.fincontar()).thenReturn(resultadosEsperados);

        List<String> result = service.fincontar();
        assertEquals(resultadosEsperados, result);

    }

    @Test
    void testFindCitasFechasEspe() {
        String fecha = "2024-03-12T12:19:00";
        Citas cita1 = new Citas();
        cita1.setIdCitas(1L);
        cita1.setFechaCita(LocalDateTime.parse(fecha));

        Citas cita2 = new Citas();
        cita2.setIdCitas(2L);
        cita2.setFechaCita(LocalDateTime.parse(fecha));

        when(repository.findCitasFechasEspe(fecha)).thenReturn(Arrays.asList(cita1, cita2));

        // Ejecutar el método que queremos probar
        List<Citas> citasList = service.findCitasFechasEspe(fecha);

        // Verificar el resultado
        assertEquals(2, citasList.size());
        assertEquals(cita1, citasList.get(0));
        assertEquals(cita2, citasList.get(1));
    }


}
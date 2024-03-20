package com.minsait.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minsait.models.*;
import com.minsait.service.CitasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CitaController.class)
@SpringJUnitConfig(CitaController.class)
class CitaControllerTest {
    @MockBean

    private CitasService services;


    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Autowired
    private MockMvc mvc;
    @Test
    void findAll() throws Exception {
        when(services.findAll()).thenReturn(
                List.of(Datos.crearCita1().get()));
        mvc.perform(get("/api/v1/citas"))
                .andExpect(jsonPath("$[0].descripcion").value("Requiero una Cita General"));

    }

    @Test
    void testNotFoundAll() throws Exception{
        when(services.findAll()).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/citas"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findById() throws Exception {
        when(services.findById(1L)).thenReturn(Datos.crearCita1().get());
        mvc.perform(get("/api/v1/citas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCitas").value(1));
    }

    @Test
    void testNotFound() throws Exception{
        when(services.findById(1L)).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/citas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void save() throws Exception {
        Especialidades especialidades = new Especialidades();
        Doctor doctor = new Doctor();
        Paciente paciente = new Paciente();
        TipoCitas tipoCitas = new TipoCitas();

        especialidades.setIdespecialidades(1L);
        doctor.setId(1L);
        paciente.setId(1L);
        tipoCitas.setIdtipocita(1L);

        LocalDateTime fechaHora = LocalDateTime.of(2024, 3, 12, 1, 18, 0);

        var citas = new Citas(1L, "Requiero una Cita General", (byte) 1L, fechaHora, tipoCitas,paciente,doctor);
        doAnswer(llamada -> {
            Citas nuevo = llamada.getArgument(0);
            nuevo.setIdCitas(1L);
            return nuevo;
        }).when(services).save(citas);

        mvc.perform(post("/api/v1/citas/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(citas)))

                .andExpect(jsonPath("$.idCitas").value(citas.getIdCitas()))
                .andExpect(jsonPath("$.descripcion").value(citas.getDescripcion()));
    }

    @Test
    void testNotSave() throws Exception{
        Especialidades especialidades = new Especialidades();
        Doctor doctor = new Doctor();
        Paciente paciente = new Paciente();
        TipoCitas tipoCitas = new TipoCitas();

        especialidades.setIdespecialidades(1L);
        doctor.setId(1L);
        paciente.setId(1L);
        tipoCitas.setIdtipocita(1L);

        LocalDateTime fechaHora = LocalDateTime.of(2024, 3, 12, 1, 18, 0);

        var citas = new Citas(1L, "Requiero una Cita General", (byte) 1L, fechaHora, tipoCitas,paciente,doctor);
        doThrow(new RuntimeException()).when(services).save(citas);

        mvc.perform(post("/api/v1/citas/save")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(citas)))
                .andExpect(status().isBadRequest());


    }

    @Test
    void testDelete() throws Exception {
        when(services.deleteById(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/citas/1"))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteException() throws Exception {
        when(services.deleteById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(delete("/api/v1/citas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        Especialidades especialidades = new Especialidades();
        Doctor doctor = new Doctor();
        Paciente paciente = new Paciente();
        TipoCitas tipoCitas = new TipoCitas();

        especialidades.setIdespecialidades(1L);
        doctor.setId(1L);
        paciente.setId(1L);
        tipoCitas.setIdtipocita(1L);

        LocalDateTime fechaHora = LocalDateTime.of(2024, 3, 12, 1, 18, 0);

        var citas = new Citas(1L, "Requiero una Cita General", (byte) 1L, fechaHora, tipoCitas,paciente,doctor);
        var actualizar =  Datos.crearCita1();
        when(services.findById(citas.getIdCitas())).thenReturn(actualizar.get());
        when(services.save(any(Citas.class))).thenAnswer(call -> {
            Citas actualizarCitas = call.getArgument(0);
            return actualizarCitas;
        });
        mvc.perform(put("/api/v1/citas/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(citas)))
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.idCitas").value(citas.getIdCitas()),
                        jsonPath("$.descripcion").value(citas.getDescripcion()));

    }

    @Test
    void testUpdateNotFoundException() throws Exception {
        when(services.findById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(put("/api/v1/citas/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(Datos.crearDoctor1().orElse(null))))
                .andExpect(status().isBadRequest());
    }
}
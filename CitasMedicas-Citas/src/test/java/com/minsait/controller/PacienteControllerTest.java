package com.minsait.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.models.Paciente;
import com.minsait.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PacienteController.class)
@SpringJUnitConfig(PacienteController.class)
class PacienteControllerTest {

    @MockBean
    private PacienteService services;


    private ObjectMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new ObjectMapper();
    }

    @Autowired
    private MockMvc mvc;


    @Test
    void findAllPacientes() throws Exception {
        when(services.findAll()).thenReturn(
                List.of(Datos.crearPaciente1().get(),
                        Datos.crearPaciente2().get(),
                        Datos.crearPaciente3().get()));

        mvc.perform(get("/api/v1/pacientes"))
                .andExpect(jsonPath("$[0].name").value("ricardo"))
                .andExpect(jsonPath("$[1].name").value("jorge"))
                .andExpect(jsonPath("$[2].name").value("exel"));
    }

    @Test
    void testNotall() throws Exception{
        when(services.findAll()).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/pacientes"))
                .andExpect(status().isNotFound());
    }


    @Test
    void findById() throws Exception {
        when(services.findById(1L)).thenReturn(Datos.crearPaciente1().get());
        mvc.perform(get("/api/v1/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("ricardo"));
    }
    @Test
    void testNotFound() throws Exception{
        when(services.findById(1L)).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/pacientes/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void save() throws Exception {
        var paciente = new Paciente(1L, "ricardo", "Azcar", "12345");
        doAnswer(llamada -> {
            Paciente nuevo = llamada.getArgument(0);
            nuevo.setId(4L);
            return nuevo;
        }).when(services).save(paciente);

        mvc.perform(post("/api/v1/pacientes/save")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(paciente)))

                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value(paciente.getName()))
                .andExpect(jsonPath("$.lastname").value(paciente.getLastname()))
                .andExpect(jsonPath("$.nss").value(paciente.getNss()));
    }
    @Test
    void testNotSave() throws Exception{
        var paciente = new Paciente(1L, "ricardo", "Azcar", "12345");
        doThrow(new RuntimeException()).when(services).save(paciente);

        mvc.perform(post("/api/v1/pacientes/save")
                .contentType("application/json")
                .content(mapper.writeValueAsBytes(paciente)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {
        when(services.deleteById(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/pacientes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteException() throws Exception {
        when(services.deleteById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(delete("/api/v1/pacientes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        var paciente = new Paciente(1L, "ricardo", "Azcar", "12345");
        var actualizar =  Datos.crearPaciente1();
        when(services.findById(paciente.getId())).thenReturn(actualizar.get());
        when(services.save(any(Paciente.class))).thenAnswer(call -> {
            Paciente actualizarPaciente = call.getArgument(0);
            return actualizarPaciente;
        });
        mvc.perform(put("/api/v1/pacientes/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(paciente)))
                        .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.name").value(paciente.getName()),
                        jsonPath("$.lastname").value(paciente.getLastname()),
                        jsonPath("$.nss").value(paciente.getNss()));
    }
    @Test
    void testUpdateNotFoundException() throws Exception {
        when(services.findById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(put("/api/v1/pacientes/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(Datos.crearPaciente2().orElse(null))))
                        .andExpect(status().isBadRequest());
    }
}
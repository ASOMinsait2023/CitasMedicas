package com.minsait.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.models.Especialidades;
import com.minsait.service.EspecialidadService;
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

@WebMvcTest(EspecialidadesController.class)
@SpringJUnitConfig(EspecialidadesController.class)
class EspecialidadesControllerTest {

    @MockBean
    private EspecialidadService services;


    private ObjectMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new ObjectMapper();
    }

    @Autowired
    private MockMvc mvc;

    @Test
    void findAllEspecialidades() throws Exception {
        when(services.findAll()).thenReturn(
                List.of(Datos.crearEspecialidad1().get(),
                        Datos.crearEspecialidad2().get(),
                        Datos.crearEspecialidad3().get()));

        mvc.perform(get("/api/v1/especialidades"))
                .andExpect(jsonPath("$[0].nombre_especialidad").value("General"))
                .andExpect(jsonPath("$[1].nombre_especialidad").value("General"))
                .andExpect(jsonPath("$[2].nombre_especialidad").value("General"));
    }
    @Test
    void testNotall() throws Exception{
        when(services.findAll()).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/especialidades"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findById() throws Exception {
        when(services.findById(1L)).thenReturn(Datos.crearEspecialidad1().get());
        mvc.perform(get("/api/v1/especialidades/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idespecialidades").value(1))
                .andExpect(jsonPath("$.nombre_especialidad").value("General"));
    }
    @Test
    void testNotFound() throws Exception{
        when(services.findById(1L)).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/especialidades/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void save() throws Exception {
        var especialidades = new Especialidades(4L,"Genera");
        doAnswer(llamada -> {
            Especialidades nuevo = llamada.getArgument(0);
            nuevo.setIdespecialidades(4L);
            return nuevo;
        }).when(services).save(especialidades);

        mvc.perform(post("/api/v1/especialidades/especialidad")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(especialidades)))
                .andExpect(jsonPath("$.idespecialidades").value(especialidades.getIdespecialidades()))
                .andExpect(jsonPath("$.nombre_especialidad").value(especialidades.getNombre_especialidad()));
    }

    @Test
    void testNotSave() throws Exception{
        var especialidades = new Especialidades(4L,"Genera");
        doThrow(new RuntimeException()).when(services).save(especialidades);

        mvc.perform(post("/api/v1/especialidades/especialidad")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(especialidades)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {
        when(services.deleteEspecialidadById(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/especialidades/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteException() throws Exception {
        when(services.deleteEspecialidadById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(delete("/api/v1/especialidades/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        var especialidades = new Especialidades(1L,"Genera");
        var actualizar =  Datos.crearEspecialidad1().get();
        when(services.findById(actualizar.getIdespecialidades())).thenReturn(actualizar);
        when(services.save(any(Especialidades.class))).thenAnswer(call -> {
            return call.<Especialidades>getArgument(0);
        });
        mvc.perform(put("/api/v1/especialidades/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(especialidades)))
                        .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.idespecialidades").value(especialidades.getIdespecialidades()),
                        jsonPath("$.nombre_especialidad").value(especialidades.getNombre_especialidad()));

    }
    @Test
    void testUpdateNotFoundException() throws Exception {
        when(services.findById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(put("/api/v1/especialidades/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(Datos.crearEspecialidad1().orElse(null))))
                .andExpect(status().isBadRequest());
    }

}
package com.minsait.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.minsait.models.Doctor;
import com.minsait.models.Especialidades;
import com.minsait.service.DoctorService;
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



@WebMvcTest(DoctorController.class)
@SpringJUnitConfig(DoctorController.class)
class DoctorControllerTest {

    @MockBean
    private DoctorService services;


    private ObjectMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new ObjectMapper();
    }

    @Autowired
    private MockMvc mvc;

    @Test
    void findAll() throws Exception {
        when(services.findAll()).thenReturn(
                List.of(Datos.crearDoctor1().get(),
                        Datos.crearDoctor2().get(),
                        Datos.crearDoctor3().get()));

        mvc.perform(get("/api/v1/doctores"))
                .andExpect(jsonPath("$[0].name").value("ricardo"))
                .andExpect(jsonPath("$[1].name").value("jorge"))
                .andExpect(jsonPath("$[2].name").value("exel"));

    }

    @Test
    void findById() throws Exception {
        when(services.findById(1L)).thenReturn(Datos.crearDoctor1().get());
        mvc.perform(get("/api/v1/doctores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("ricardo"));
    }
    @Test
    void testNotFound() throws Exception{
        when(services.findById(1L)).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/doctores/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void save() throws Exception {
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("General");
        var doctor = new Doctor(1L, "ricardo", "Azcar", (byte) 1L,especialidades);
        doAnswer(llamada -> {
            Doctor nuevo = llamada.getArgument(0);
            nuevo.setId(4L);
            return nuevo;
        }).when(services).save(doctor);

        mvc.perform(post("/api/v1/doctores/save")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(doctor)))

                .andExpect(jsonPath("$.name").value(doctor.getName()))
                .andExpect(jsonPath("$.lastname").value(doctor.getLastname()));
    }
    @Test
    void testNotSave() throws Exception{
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("General");
        var doctor = new Doctor(1L, "ricardo", "Azcar", (byte) 1L,especialidades);
        doThrow(new RuntimeException()).when(services).save(doctor);

        mvc.perform(post("/api/v1/doctores/save")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(doctor)))
                .andExpect(status().isBadRequest());


    }
    @Test
    void testDelete() throws Exception {
        when(services.deleteById(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/doctores/1"))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteException() throws Exception {
        when(services.deleteById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(delete("/api/v1/doctores/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        var doctor = new Doctor(1L, "ricardo", "Azcar", (byte) 1L,especialidades);
        var actualizar =  Datos.crearDoctor1();
        when(services.findById(doctor.getId())).thenReturn(actualizar.get());
        when(services.save(any(Doctor.class))).thenAnswer(call -> {
            Doctor actualizarDoctor = call.getArgument(0);
            return actualizarDoctor;
        });
        mvc.perform(put("/api/v1/doctores/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(doctor)))
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.name").value(doctor.getName()),
                        jsonPath("$.lastname").value(doctor.getLastname()));

    }

    @Test
    void testUpdateNotFoundException() throws Exception {
        when(services.findById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(put("/api/v1/doctores/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(Datos.crearDoctor1().orElse(null))))
                .andExpect(status().isBadRequest());
    }
}
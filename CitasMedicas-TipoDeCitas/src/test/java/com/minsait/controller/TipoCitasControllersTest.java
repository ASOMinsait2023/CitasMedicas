package com.minsait.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.models.TipoCitas;
import com.minsait.service.TipoCitasService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TipoCitasControllers.class)
@SpringJUnitConfig(TipoCitasControllers.class)
class TipoCitasControllersTest {

    @MockBean
    private TipoCitasService services;


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
                List.of(Datos.crearTipocita1().get(),
                        Datos.crearTipocita2().get(),
                        Datos.crearTipocita3().get()));

        mvc.perform(get("/api/v1/tipocitas"))
                .andExpect(jsonPath("$[0].tipo_cita").value("General"))
                .andExpect(jsonPath("$[1].tipo_cita").value("General"))
                .andExpect(jsonPath("$[2].tipo_cita").value("General"));
    }
    @Test
    void testNotall() throws Exception{
        when(services.findAll()).thenThrow(new RuntimeException());
        mvc.perform(get("/api/v1/tipocitas"))
                .andExpect(status().isNotFound());
    }



    @Test
    void findById() throws Exception {
        when(services.findById(1L)).thenReturn(Datos.crearTipocita1().get());
        mvc.perform(get("/api/v1/tipocitas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idtipocita").value(1))
                .andExpect(jsonPath("$.tipo_cita").value("General"));
    }

    @Test
    void testNotFound() throws Exception{
        when(services.findById(1L)).thenThrow(new NoSuchElementException());
        mvc.perform(get("/api/v1/tipocitas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTipoCitas() throws Exception {
        var tipocitas = new TipoCitas(4L,"Genera");
        doAnswer(llamada -> {
            TipoCitas nuevo = llamada.getArgument(0);
            nuevo.setIdtipocita(4L);
            return nuevo;
        }).when(services).save(tipocitas);

        mvc.perform(post("/api/v1/tipocitas/save")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(tipocitas)))
                .andExpect(jsonPath("$.idtipocita").value(tipocitas.getIdtipocita()))
                .andExpect(jsonPath("$.tipo_cita").value(tipocitas.getTipo_cita()));
    }

    @Test
    void testNotSave() throws Exception{
        var tipocitas = new TipoCitas(4L,"Genera");
        doThrow(new RuntimeException()).when(services).save(tipocitas);

        mvc.perform(post("/api/v1/tipocitas/save")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(tipocitas)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        var tipocitas = new TipoCitas(1L,"Genera");
        var actualizar =  Datos.crearTipocita1().get();
        when(services.findById(actualizar.getIdtipocita())).thenReturn(actualizar);
        when(services.save(any(TipoCitas.class))).thenAnswer(call -> {
            return call.<TipoCitas>getArgument(0);
        });
        mvc.perform(put("/api/v1/tipocitas/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(tipocitas)))
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.idtipocita").value(tipocitas.getIdtipocita()),
                        jsonPath("$.tipo_cita").value(tipocitas.getTipo_cita()));

    }
    @Test
    void testUpdateNotFoundException() throws Exception {
        when(services.findById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(put("/api/v1/tipocitas/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(Datos.crearTipocita1().orElse(null))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {
        when(services.deleteById(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/tipocitas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteException() throws Exception {
        when(services.deleteById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(delete("/api/v1/tipocitas/1"))
                .andExpect(status().isNotFound());
    }
}
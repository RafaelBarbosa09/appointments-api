package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.ProfessionalService;
import com.infnet.appointmentsapi.infrastructure.models.Professional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessionalControllerTest {

    @Mock
    private ProfessionalService service;

    @InjectMocks
    private ProfessionalController controller;

    @Test
    void getAll() {
        when(service.getAllProfessionals()).thenReturn(Arrays.asList(
            new Professional(),
            new Professional(),
            new Professional()

        ));

        assertDoesNotThrow(() -> controller.getAll());

        verify(service, times(1)).getAllProfessionals();
    }

    @Test
    void getAllException() {
        when(service.getAllProfessionals()).thenThrow(new RuntimeException("Erro ao buscar todos os professionals"));

        ResponseEntity<Object> response = controller.getAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getById() {
        when(service.getProfessionalById(any())).thenReturn(new Professional());

        assertDoesNotThrow(() -> controller.getById(any()));

        verify(service, times(1)).getProfessionalById(any());
    }

    @Test
    void getByIdException() {
        when(service.getProfessionalById(any())).thenThrow(new RuntimeException("Erro ao buscar professional por id"));

        ResponseEntity<Object> response = controller.getById(any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        when(service.createProfessional(any(), any())).thenReturn(new Professional());

        assertDoesNotThrow(() -> controller.create(new Professional(), 1L));

        verify(service, times(1)).createProfessional(any(), any());
    }

    @Test
    void createException() {
        when(service.createProfessional(any(), any())).thenThrow(new RuntimeException("Erro ao criar professional"));

        ResponseEntity<Object> response = controller.create(new Professional(), 1L);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void getByUserId() {
        when(service.getPofessionalByUserId(any())).thenReturn(new Professional());

        assertDoesNotThrow(() -> controller.getByUserId(1L));

        verify(service, times(1)).getPofessionalByUserId(1L);
    }

    @Test
    void getByUserIdException() {
        when(service.getPofessionalByUserId(any())).thenThrow(new RuntimeException("Erro ao buscar professional por user id"));

        ResponseEntity<Object> response = controller.getByUserId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
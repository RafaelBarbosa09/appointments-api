package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.AppointmentService;
import com.infnet.appointmentsapi.infrastructure.models.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService service;

    @InjectMocks
    private AppointmentController controller;

    @Test
    void create() {
        when(service.create(any())).thenReturn(new Appointment());

        assertDoesNotThrow(() -> controller.create(new Appointment()));

        verify(service, times(1)).create(any());
    }

    @Test
    void createException() {
        when(service.create(any())).thenThrow(new RuntimeException("Erro ao criar o appointment"));

        ResponseEntity<Object> response = controller.create(new Appointment());

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void getAll() {
        when(service.getAllAppointments()).thenReturn(Arrays.asList(
            new Appointment(),
            new Appointment(),
            new Appointment()

        ));

        assertDoesNotThrow(() -> controller.getAll());

        verify(service, times(1)).getAllAppointments();
    }

    @Test
    void getAllException() {
        when(service.getAllAppointments()).thenThrow(new RuntimeException("Erro ao buscar todos os appointments"));

        ResponseEntity<Object> response = controller.getAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getByCustomerId() {
        when(service.getAppointmentsByCustomerId(1L)).thenReturn(Arrays.asList(
            new Appointment(),
            new Appointment(),
            new Appointment()
        ));

        assertDoesNotThrow(() -> controller.getByCustomerId(1L));

        verify(service, times(1)).getAppointmentsByCustomerId(1L);
    }

    @Test
    void getByCustomerIdException() {
        when(service.getAppointmentsByCustomerId(1L)).thenThrow(new RuntimeException("Erro ao buscar appointments por customer id"));

        ResponseEntity<Object> response = controller.getByCustomerId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getByProfessionalId() {
        when(service.getAppointmentsByProfessionalId(1L)).thenReturn(Arrays.asList(
            new Appointment(),
            new Appointment(),
            new Appointment()
        ));

        assertDoesNotThrow(() -> controller.getByProfessionalId(1L));

        verify(service, times(1)).getAppointmentsByProfessionalId(1L);
    }

    @Test
    void getByProfessionalIdException() {
        when(service.getAppointmentsByProfessionalId(1L)).thenThrow(new RuntimeException("Erro ao buscar appointments por professional id"));

        ResponseEntity<Object> response = controller.getByProfessionalId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateStatus() {
        when(service.updateStatus(1L)).thenReturn(new Appointment());

        assertDoesNotThrow(() -> controller.updateStatus(1L));

        verify(service, times(1)).updateStatus(1L);
    }

    @Test
    void updateStatusException() {
        when(service.updateStatus(1L)).thenThrow(new RuntimeException("Erro ao atualizar status do appointment"));

        ResponseEntity<Object> response = controller.updateStatus(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
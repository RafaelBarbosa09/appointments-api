package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.AvailabilityService;
import com.infnet.appointmentsapi.infrastructure.models.Availability;
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
class AvailabilityControllerTest {

    @Mock
    private AvailabilityService availabilityService;

    @InjectMocks
    private AvailabilityController availabilityController;

    @Test
    void getAll() {
        when(availabilityService.getAllAvailabilities()).thenReturn(Arrays.asList(
            new Availability(),
            new Availability(),
            new Availability()
        ));

        assertDoesNotThrow(() -> availabilityController.getAll());

        verify(availabilityService, times(1)).getAllAvailabilities();
    }

    @Test
    void getAllException() {
        when(availabilityService.getAllAvailabilities()).thenThrow(new RuntimeException("Erro ao buscar todos os availabilities"));

        ResponseEntity<Object> response = availabilityController.getAll();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void create() {
        when(availabilityService.createAvailability(any())).thenReturn(new Availability());

        assertDoesNotThrow(() -> availabilityController.create(any()));

        verify(availabilityService, times(1)).createAvailability(any());
    }

    @Test
    void createException() {
        when(availabilityService.createAvailability(any())).thenThrow(new RuntimeException("Erro ao criar availability"));

        ResponseEntity<Object> response = availabilityController.create(any());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getByDate() {
        when(availabilityService.getAvailabilityByDate(any())).thenReturn(new Availability());

        assertDoesNotThrow(() -> availabilityController.getByDate(any()));

        verify(availabilityService, times(1)).getAvailabilityByDate(any());
    }

    @Test
    void getByDateException() {
        when(availabilityService.getAvailabilityByDate(any())).thenThrow(new RuntimeException("Erro ao buscar availability por data"));

        ResponseEntity<Object> response = availabilityController.getByDate(any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getByDateAndProfessionalId() {
        when(availabilityService.getAllAvaialabilitiesByDateAndProfessionalId(any(), any())).thenReturn(new Availability());

        assertDoesNotThrow(() -> availabilityController.getByDateAndProfessionalId(any(), any()));

        verify(availabilityService, times(1)).getAllAvaialabilitiesByDateAndProfessionalId(any(), any());
    }

    @Test
    void getByDateAndProfessionalIdException() {
        when(availabilityService.getAllAvaialabilitiesByDateAndProfessionalId(any(), any())).thenThrow(new RuntimeException("Erro ao buscar availability por data e id do professional"));

        ResponseEntity<Object> response = availabilityController.getByDateAndProfessionalId(any(), any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.TimeSlotService;
import com.infnet.appointmentsapi.infrastructure.models.TimeSlot;
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
class TimeSlotControllerTest {

    @Mock
    private TimeSlotService timeSlotService;

    @InjectMocks
    private TimeSlotController timeSlotController;

    @Test
    void create() {
        when(timeSlotService.createTimeSlot(any())).thenReturn(Arrays.asList(
            new TimeSlot(),
            new TimeSlot(),
            new TimeSlot()
        ));

        assertDoesNotThrow(() -> timeSlotController.create(any()));

        verify(timeSlotService, times(1)).createTimeSlot(any());
    }

    @Test
    void createException() {
        when(timeSlotService.createTimeSlot(any())).thenThrow(new RuntimeException("Erro ao criar time slot"));

        ResponseEntity<Object> response = timeSlotController.create(new TimeSlot());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
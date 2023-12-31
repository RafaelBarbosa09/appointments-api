package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.WorkService;
import com.infnet.appointmentsapi.infrastructure.models.Work;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WorkControllerTest {

    @Mock
    private WorkService workService;

    @InjectMocks
    private WorkController workController;

    @Test
    void getAll() {
        when(workService.getAllWorks()).thenReturn(Arrays.asList(
                new Work(),
                new Work()
        ));

        assertDoesNotThrow(() -> workController.getAll());

        verify(workService, times(1)).getAllWorks();
    }

    @Test
    void getAllException() {
        when(workService.getAllWorks()).thenThrow(new RuntimeException("Erro ao buscar todos os works"));

        ResponseEntity<Object> response = workController.getAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
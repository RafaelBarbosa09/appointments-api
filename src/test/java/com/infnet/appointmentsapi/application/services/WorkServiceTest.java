package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.WorkRepository;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WorkServiceTest {

    @Mock
    private WorkRepository workRepository;

    @InjectMocks
    private WorkService workService;

    @Test
    void getAllWorks() {
        when(workRepository.findAll()).thenReturn(null);

        assertDoesNotThrow(() -> workService.getAllWorks());

        verify(workRepository, times(1)).findAll();
    }
}
package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.ProfessionalRepository;
import com.infnet.appointmentsapi.domain.repositories.UserRepository;
import com.infnet.appointmentsapi.infrastructure.models.Professional;
import com.infnet.appointmentsapi.infrastructure.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessionalServiceTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProfessionalService professionalService;

    @Test
    void getAllProfessionals() {
        when(professionalRepository.findAll()).thenReturn(List.of(new Professional()));

        assertDoesNotThrow(() -> professionalService.getAllProfessionals());

        verify(professionalRepository, times(1)).findAll();
    }

    @Test
    void getProfessionalById() {
        when(professionalRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Professional()));

        assertDoesNotThrow(() -> professionalService.getProfessionalById(1L));

        verify(professionalRepository, times(1)).findById(anyLong());
    }

    @Test
    void getProfessionalById_ThrowsException() {
        when(professionalRepository.findById(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> professionalService.getProfessionalById(1L));

        verify(professionalRepository, times(1)).findById(anyLong());
    }

    @Test
    void createProfessional() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Professional professional = new Professional();

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(professionalRepository.save(professional)).thenReturn(professional);

        Professional createdProfessional = professionalService.createProfessional(professional, userId);

        verify(userRepository, times(1)).findById(userId);
        verify(professionalRepository, times(1)).save(professional);

        assertEquals(professional, createdProfessional);
        assertEquals(user, createdProfessional.getUser());
    }

    @Test
    void getPofessionalByUserId() {
        when(professionalRepository.getByUserId(1L)).thenReturn(new Professional());

        assertDoesNotThrow(() -> professionalService.getPofessionalByUserId(1L));

        verify(professionalRepository, times(1)).getByUserId(1L);
    }

    @Test
    void getPofessionalByUserId_ShouldThrowNotFound() {
        when(professionalRepository.getByUserId(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> professionalService.getPofessionalByUserId(1L));

        verify(professionalRepository, times(1)).getByUserId(1L);
    }
}
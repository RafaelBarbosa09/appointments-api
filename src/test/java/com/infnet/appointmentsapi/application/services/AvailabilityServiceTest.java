package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.dto.AvailabilityRequestDTO;
import com.infnet.appointmentsapi.domain.dto.TimeSlotRequestDTO;
import com.infnet.appointmentsapi.domain.repositories.AvailabilityRepository;
import com.infnet.appointmentsapi.domain.repositories.ProfessionalRepository;
import com.infnet.appointmentsapi.domain.repositories.TimeSlotRepository;
import com.infnet.appointmentsapi.infrastructure.models.Availability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceTest {
    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @Mock
    private TimeSlotService timeSlotService;

    @Mock
    private ProfessionalRepository professionalRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    @Test
    void testGetAllAvaialabilitiesByDateAndProfessionalId_Success() {
        Mockito.when(availabilityRepository.findAllAvaialabilitiesByDateAndProfessionalId(
                Mockito.any(LocalDate.class),
                Mockito.any(Long.class)))
                .thenReturn(new Availability());

        assertDoesNotThrow(() -> availabilityService.getAllAvaialabilitiesByDateAndProfessionalId("2023-11-23", 1L));

        Mockito.verify(
                availabilityRepository,
                Mockito.times(1))
                .findAllAvaialabilitiesByDateAndProfessionalId(Mockito.any(LocalDate.class), Mockito.any(Long.class));

    }

    @Test
    void getAllAvailabilities_Success() {
        Mockito.when(availabilityRepository.findAll()).thenReturn(List.of(new Availability()));

        assertDoesNotThrow(() -> availabilityService.getAllAvailabilities());

        Mockito.verify(availabilityRepository, Mockito.times(1)).findAll();
    }

    @Test
    void createAvailability_Success() {
        Mockito.when(availabilityRepository.save(Mockito.any(Availability.class))).thenReturn(new Availability());

        assertDoesNotThrow(() -> availabilityService.createAvailability(new AvailabilityRequestDTO(
                "2023-11-23",
                new TimeSlotRequestDTO(
                        "10:00",
                        "11:00"
                ),
                1L
        )));

        Mockito.verify(availabilityRepository, Mockito.times(1)).save(Mockito.any(Availability.class));
    }

    @Test
    void getAvailabilityByDate() {
        Mockito.when(availabilityRepository.findByDate(Mockito.any(LocalDate.class))).thenReturn(new Availability());

        assertDoesNotThrow(() -> availabilityService.getAvailabilityByDate("2023-11-23"));

        Mockito.verify(availabilityRepository, Mockito.times(1)).findByDate(Mockito.any(LocalDate.class));
    }
}
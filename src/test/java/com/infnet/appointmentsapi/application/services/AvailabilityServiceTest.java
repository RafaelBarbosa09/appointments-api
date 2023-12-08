package com.infnet.appointmentsapi.application.services;

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
    void testGetAllAvaialabilitiesByDateAndProfessionalIdShouldReturnSuccess() {
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

}
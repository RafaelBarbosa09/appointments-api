package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.TimeSlotRepository;
import com.infnet.appointmentsapi.infrastructure.models.TimeSlot;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TimeSlotServiceTest {

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private TimeSlotService timeSlotService;

    @Test
    void createTimeSlot() {
        when(timeSlotRepository.saveAll(anyList())).thenReturn(Arrays.asList(
            new TimeSlot(),
            new TimeSlot(),
            new TimeSlot()
        ));

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(12, 0);
        TimeSlot timeSlotDTO = new TimeSlot();
        timeSlotDTO.setStartTime(startTime);
        timeSlotDTO.setEndTime(endTime);

        assertDoesNotThrow(() -> timeSlotService.createTimeSlot(timeSlotDTO));

        verify(timeSlotRepository, times(1)).saveAll(anyList());
    }
}
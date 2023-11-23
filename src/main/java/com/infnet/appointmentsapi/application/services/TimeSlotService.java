package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.repositories.TimeSlotRepository;
import com.infnet.appointmentsapi.infrastructure.models.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> createTimeSlot(TimeSlot timeSlotDTO) {
        LocalTime startTime = timeSlotDTO.getStartTime();
        LocalTime endTime = timeSlotDTO.getEndTime();

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        List<TimeSlot> timeSlots = new ArrayList<>();

        while (startTime.isBefore(endTime)) {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setStartTime(startTime);
            timeSlot.setEndTime(startTime.plusHours(1));
            timeSlot.setAvailable(true);

            timeSlots.add(timeSlot);
            startTime = startTime.plusHours(1);
        }

        return timeSlotRepository.saveAll(timeSlots);
    }
}

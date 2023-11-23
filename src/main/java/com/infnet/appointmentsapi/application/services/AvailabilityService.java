package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.dto.AvailabilityRequestDTO;
import com.infnet.appointmentsapi.domain.dto.TimeSlotRequestDTO;
import com.infnet.appointmentsapi.domain.repositories.AvailabilityRepository;
import com.infnet.appointmentsapi.domain.repositories.TimeSlotRepository;
import com.infnet.appointmentsapi.infrastructure.models.Availability;
import com.infnet.appointmentsapi.infrastructure.models.TimeSlot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotService timeSlotService;

    public AvailabilityService(AvailabilityRepository availabilityRepository, TimeSlotRepository timeSlotRepository,
            TimeSlotService timeSlotService) {
        this.availabilityRepository = availabilityRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotService = timeSlotService;
    }

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability createAvailability(AvailabilityRequestDTO availabilityDto) {
        TimeSlotRequestDTO timeSlotDto = availabilityDto.timeSlot();
        TimeSlot timeSlot = new TimeSlot();
        LocalTime startTime = LocalTime.parse(timeSlotDto.startTime());
        LocalTime endTime = LocalTime.parse(timeSlotDto.endTime());
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);

        List<TimeSlot> timeSlotsCreated = timeSlotService.createTimeSlot(timeSlot);

        // List<TimeSlot> timeSlotsFromDB =
        // timeSlotRepository.findAllById(timeSlots.stream().map(TimeSlot::getId).toList());
        // availability.setTimeSlots(timeSlotsFromDB);
        Availability availability = new Availability();
        availability.setDate(LocalDate.parse(availabilityDto.date()));
        availability.setTimeSlots(timeSlotsCreated);

        return availabilityRepository.save(availability);
    }

    public Availability createWeekAvailability(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(7);

        return null;
    }

}

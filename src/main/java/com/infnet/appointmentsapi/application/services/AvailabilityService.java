package com.infnet.appointmentsapi.application.services;

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

    public AvailabilityService(AvailabilityRepository availabilityRepository, TimeSlotRepository timeSlotRepository, TimeSlotService timeSlotService) {
        this.availabilityRepository = availabilityRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotService = timeSlotService;
    }

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability createAvailability(Availability availability) {
        List<TimeSlot> timeSlots = availability.getTimeSlots();
        List<TimeSlot> timeSlotsFromDB = timeSlotRepository.findAllById(timeSlots.stream().map(TimeSlot::getId).toList());
        availability.setTimeSlots(timeSlotsFromDB);

        return availabilityRepository.save(availability);
    }

    public Availability createWeekAvailability(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(7);


        return null;
    }

}

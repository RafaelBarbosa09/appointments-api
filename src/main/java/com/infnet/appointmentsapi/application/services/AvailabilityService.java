package com.infnet.appointmentsapi.application.services;

import com.infnet.appointmentsapi.domain.dto.AvailabilityRequestDTO;
import com.infnet.appointmentsapi.domain.dto.TimeSlotRequestDTO;
import com.infnet.appointmentsapi.domain.repositories.AvailabilityRepository;
import com.infnet.appointmentsapi.domain.repositories.ProfessionalRepository;
import com.infnet.appointmentsapi.domain.repositories.TimeSlotRepository;
import com.infnet.appointmentsapi.infrastructure.models.Availability;
import com.infnet.appointmentsapi.infrastructure.models.Professional;
import com.infnet.appointmentsapi.infrastructure.models.TimeSlot;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotService timeSlotService;
    private final ProfessionalRepository professionalRepository;

    public AvailabilityService(
            AvailabilityRepository availabilityRepository,
            TimeSlotRepository timeSlotRepository,
            TimeSlotService timeSlotService,
            ProfessionalRepository professionalRepository) {
        this.availabilityRepository = availabilityRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotService = timeSlotService;
        this.professionalRepository = professionalRepository;
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
        Professional professional = professionalRepository.findById(availabilityDto.professionalId()).orElse(null);

        // List<TimeSlot> timeSlotsFromDB =
        // timeSlotRepository.findAllById(timeSlots.stream().map(TimeSlot::getId).toList());
        // availability.setTimeSlots(timeSlotsFromDB);
        Availability availability = new Availability();
        availability.setDate(LocalDate.parse(availabilityDto.date()));
        availability.setTimeSlots(timeSlotsCreated);
        availability.setProfessional(professional);

        return availabilityRepository.save(availability);
    }

    public Availability getAvailabilityByDate(String date) {
        return availabilityRepository.findByDate(LocalDate.parse(date));
    }

    public List<Availability> getAllAvaialabilitiesByDateAndProfessionalId(String date, Long professionalId) {
        return availabilityRepository.findAllAvaialabilitiesByDateAndProfessionalId(LocalDate.parse(date), professionalId);
    }

    public Availability createWeekAvailability(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(7);

        return null;
    }

}

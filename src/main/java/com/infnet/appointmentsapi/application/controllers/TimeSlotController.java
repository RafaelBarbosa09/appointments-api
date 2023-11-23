package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.TimeSlotService;
import com.infnet.appointmentsapi.infrastructure.models.TimeSlot;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody TimeSlot timeSlotDTO) {
        try {
            List<TimeSlot> timeSlots = timeSlotService.createTimeSlot(timeSlotDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(timeSlots);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

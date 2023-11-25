package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.AvailabilityService;
import com.infnet.appointmentsapi.domain.dto.AvailabilityRequestDTO;
import com.infnet.appointmentsapi.infrastructure.models.Availability;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        try {
            List<Availability> availabilities = availabilityService.getAllAvailabilities();
            return ResponseEntity.status(HttpStatus.OK).body(availabilities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody AvailabilityRequestDTO availability) {
        try {
            Availability createdAvailability = availabilityService.createAvailability(availability);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAvailability);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/date")
    public ResponseEntity<Object> getByDate(@RequestParam String date) {
        try {
            Availability availabilities = availabilityService.getAvailabilityByDate(date);
            return ResponseEntity.status(HttpStatus.OK).body(availabilities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

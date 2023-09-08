package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.AppointmentService;
import com.infnet.appointmentsapi.infrastructure.models.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody Appointment appointment) {
        try {
            Appointment response = service.create(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<Appointment> appointments = service.getAllAppointments();
            return ResponseEntity.status(HttpStatus.OK).body(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

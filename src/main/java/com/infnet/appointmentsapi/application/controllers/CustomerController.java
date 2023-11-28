package com.infnet.appointmentsapi.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infnet.appointmentsapi.application.services.CustomerService;
import com.infnet.appointmentsapi.infrastructure.models.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Object> create(@RequestBody Customer customerDto, @PathVariable Long userId) {
        try {
            Customer customer = customerService.createCustomer(customerDto, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getByUserId(@PathVariable Long userId) {
        try {
            Customer customer = customerService.getCustomerByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

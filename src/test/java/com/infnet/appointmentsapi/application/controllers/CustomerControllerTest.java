package com.infnet.appointmentsapi.application.controllers;

import com.infnet.appointmentsapi.application.services.CustomerService;
import com.infnet.appointmentsapi.infrastructure.models.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void create() {
        when(customerService.createCustomer(any(), any())).thenReturn(new Customer());

        assertDoesNotThrow(() -> customerController.create(any(), any()));

        verify(customerService, times(1)).createCustomer(any(), any());
    }

    @Test
    void createException() {
        when(customerService.createCustomer(any(), any())).thenThrow(new RuntimeException("Erro ao criar customer"));

        ResponseEntity<Object> response = customerController.create(new Customer(), 1L);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void getByUserId() {
        when(customerService.getCustomerByUserId(any())).thenReturn(new Customer());

        assertDoesNotThrow(() -> customerController.getByUserId(any()));

        verify(customerService, times(1)).getCustomerByUserId(any());
    }

    @Test
    void getByUserIdException() {
        when(customerService.getCustomerByUserId(any())).thenThrow(new RuntimeException("Erro ao buscar customer"));

        ResponseEntity<Object> response = customerController.getByUserId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
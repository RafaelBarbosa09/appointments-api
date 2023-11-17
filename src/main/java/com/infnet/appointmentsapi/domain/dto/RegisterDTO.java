package com.infnet.appointmentsapi.domain.dto;

import com.infnet.appointmentsapi.domain.objectvalues.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {}

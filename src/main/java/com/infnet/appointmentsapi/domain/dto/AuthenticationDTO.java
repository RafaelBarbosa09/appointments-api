package com.infnet.appointmentsapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record AuthenticationDTO(String login, String password) {}

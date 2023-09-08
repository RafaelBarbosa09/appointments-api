package com.infnet.appointmentsapi.domain.objectvalues;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("Pendente"),
    CANCELLED("Cancelado"),
    FINISHED("Finalizado");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}

package es.daw2.microservice_email_v1.services.dtos.stadistics.reservas;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservasPorDiaDTO implements Serializable{
    private LocalDate dia;
    private Long totalReservas;
}

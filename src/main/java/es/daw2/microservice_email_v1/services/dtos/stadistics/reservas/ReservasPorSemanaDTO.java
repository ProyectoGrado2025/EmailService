package es.daw2.microservice_email_v1.services.dtos.stadistics.reservas;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReservasPorSemanaDTO implements Serializable{
    private Integer anio;
    private Integer semana;
    private Long totalReservas;
}

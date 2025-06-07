package es.daw2.microservice_email_v1.services.dtos.stadistics.reservas;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReservasPorMesDTO implements Serializable{
    private Integer anio;
    private Integer mes;
    private Long totalReservas;
}

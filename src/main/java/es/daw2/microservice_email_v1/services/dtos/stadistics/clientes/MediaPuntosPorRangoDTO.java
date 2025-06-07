package es.daw2.microservice_email_v1.services.dtos.stadistics.clientes;

import java.io.Serializable;

import lombok.Data;

@Data
public class MediaPuntosPorRangoDTO implements Serializable {
    private String nombreRango;
    private Double mediaPuntos;
}
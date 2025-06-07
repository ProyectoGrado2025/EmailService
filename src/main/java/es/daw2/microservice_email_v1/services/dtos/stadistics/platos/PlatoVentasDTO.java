package es.daw2.microservice_email_v1.services.dtos.stadistics.platos;

import java.io.Serializable;

import lombok.Data;

@Data
public class PlatoVentasDTO implements Serializable{
    
    private Long platoId;
    private String nombre;
    private Long totalVendidos;
}

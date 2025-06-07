package es.daw2.microservice_email_v1.services.dtos.stadistics.clientes;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientePuntosDTO implements Serializable{
    private Long id;
    private String nombre; 
    private Long puntos; 
    private String nombreRango;
}
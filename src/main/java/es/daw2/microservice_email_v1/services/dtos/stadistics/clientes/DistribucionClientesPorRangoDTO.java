package es.daw2.microservice_email_v1.services.dtos.stadistics.clientes;

import java.io.Serializable;

import lombok.Data;

@Data
public class DistribucionClientesPorRangoDTO implements Serializable {

    private String nombreRango;
    private Long totalClientes;
}
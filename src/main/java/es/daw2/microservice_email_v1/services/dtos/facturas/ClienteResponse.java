package es.daw2.microservice_email_v1.services.dtos.facturas;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteResponse implements Serializable{

    private Long clienteId;
    private String nombreCliente;
    private String emailCliente;
    private String numeroCliente;
}

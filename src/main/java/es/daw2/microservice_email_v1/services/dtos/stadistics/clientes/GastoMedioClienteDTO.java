package es.daw2.microservice_email_v1.services.dtos.stadistics.clientes;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class GastoMedioClienteDTO implements Serializable{
    
    private Long clienteId;
    private BigDecimal gastoMedio;
}
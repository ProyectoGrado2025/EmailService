package es.daw2.microservice_email_v1.services.dtos.facturas;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PedidoResponse implements Serializable{
    
    private Long pedidoId;
    private List<LineaPedidoResponse> lineasPedidoResponse;
}

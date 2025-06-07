package es.daw2.microservice_email_v1.services.dtos.facturas;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class LineaPedidoResponse implements Serializable{
    
    private String nombrePlato;
    private Integer cantidadPlato;
    private Float precioUnitario;
    private BigDecimal precioConjunto;
}

package es.daw2.microservice_email_v1.services.dtos.facturas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FacturaResponse implements Serializable {
    
    private Long facturaId;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fechaFactura;
    
    private BigDecimal facturaPrecio;
    private String formaPago;

    private PedidoResponse pedidoResponse;
    private ClienteResponse clienteResponse;

    private Long reservaId;
    private String beneficioCodigo;

    private BigDecimal descuentoAplicado;
    private Long puntosGenerados;
    private String nombreRangoAplicado;
}
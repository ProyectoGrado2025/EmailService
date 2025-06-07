package es.daw2.microservice_email_v1.services.dtos.stadistics.clientes;

import java.util.List;

import lombok.Data;

@Data
public class ClienteStadisticsBodyDTO {
    private List<GastoMedioClienteDTO> gastoMedioClientes;
    private List<DistribucionClientesPorRangoDTO> distribucionClientesPorRango;
    private List<MediaPuntosPorRangoDTO> mediaPuntosPorRango;
    private List<ClientePuntosDTO> topClientesPorPuntos;
    private Long totalPuntosFidelizacion;
}
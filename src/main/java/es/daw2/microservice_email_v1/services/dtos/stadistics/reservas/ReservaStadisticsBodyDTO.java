package es.daw2.microservice_email_v1.services.dtos.stadistics.reservas;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ReservaStadisticsBodyDTO implements Serializable{
    private Double avgPersonasPorReserva;
    private List<ContadorReservasPorEstadoDTO> reservasPorEstado;
    private List<ReservasPorDiaDTO> reservasPorDiaUltimoMes;
    private List<ReservasPorSemanaDTO> reservasPorSemanaUltimos3Meses;
    private List<ReservasPorMesDTO> reservasPorMesUltimoAnio;
}
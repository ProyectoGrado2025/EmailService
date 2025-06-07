package es.daw2.microservice_email_v1.services.dtos.stadistics.reservas;

import java.io.Serializable;

import es.daw2.microservice_email_v1.util.ReservaStatus;
import lombok.Data;

@Data
public class ContadorReservasPorEstadoDTO implements Serializable{
    private ReservaStatus reservaStatus;
    private Long cantidad;
}

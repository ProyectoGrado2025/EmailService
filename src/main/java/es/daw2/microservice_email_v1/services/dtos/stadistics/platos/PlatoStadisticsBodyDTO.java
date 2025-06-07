package es.daw2.microservice_email_v1.services.dtos.stadistics.platos;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PlatoStadisticsBodyDTO implements Serializable{
    List<PlatoVentasDTO> top3PlatosMasVendidos;
    List<PlatoVentasDTO> top3PlatosMenosVendidos;
}

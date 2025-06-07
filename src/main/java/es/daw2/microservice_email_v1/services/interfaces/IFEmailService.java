package es.daw2.microservice_email_v1.services.interfaces;

import java.io.IOException;

import es.daw2.microservice_email_v1.services.dtos.contacto.ContactoResponse;
import es.daw2.microservice_email_v1.services.dtos.facturas.FacturaResponse;
import es.daw2.microservice_email_v1.services.dtos.reservas.ReservaResponse;
import es.daw2.microservice_email_v1.services.dtos.stadistics.clientes.ClienteStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.facturas.FacturaStadisticsbodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.platos.PlatoStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.reservas.ReservaStadisticsBodyDTO;
import jakarta.mail.MessagingException;

public interface IFEmailService {

    void sendConfirmacionReservaEmail(ReservaResponse reserva) throws MessagingException;
    void sendModificacionReservaEmail(ReservaResponse reserva) throws MessagingException;
    void sendCancelacionReservaEmail(ReservaResponse reserva) throws MessagingException;
    void sendInfoFactura (FacturaResponse facturaResponse) throws MessagingException, IOException;
    void sendStatsReserva(ReservaStadisticsBodyDTO stats) throws MessagingException, IOException;
    void sendStatsFactura(FacturaStadisticsbodyDTO stats) throws MessagingException, IOException;
    void sendStatsPlato(PlatoStadisticsBodyDTO stats) throws MessagingException, IOException;
    void sendStatsCliente(ClienteStadisticsBodyDTO stats) throws MessagingException, IOException;
    void sendInfoContacto (ContactoResponse contactoResponse) throws MessagingException;
}

package es.daw2.microservice_email_v1.services.interfaces;

import es.daw2.microservice_email_v1.services.dtos.ReservaResponse;
import jakarta.mail.MessagingException;

public interface IFEmailService {

    void sendConfirmacionReservaEmail(ReservaResponse reserva) throws MessagingException;
    void sendModificacionReservaEmail(ReservaResponse reserva) throws MessagingException;
    void sendCancelacionReservaEmail(ReservaResponse reserva) throws MessagingException;
}

package es.daw2.microservice_email_v1.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.daw2.microservice_email_v1.services.dtos.contacto.ContactoResponse;
import es.daw2.microservice_email_v1.services.dtos.facturas.FacturaResponse;
import es.daw2.microservice_email_v1.services.dtos.reservas.ReservaResponse;
import es.daw2.microservice_email_v1.services.dtos.stadistics.clientes.ClienteStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.facturas.FacturaStadisticsbodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.platos.PlatoStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.reservas.ReservaStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.interfaces.IFEmailService;
import jakarta.mail.MessagingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    IFEmailService emailService;

    @PostMapping("/send/confirmacion")
    public ResponseEntity<?> sendEmailConfirmation (@RequestBody ReservaResponse reservaResponse) throws MessagingException {
        emailService.sendConfirmacionReservaEmail(reservaResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/modificacion")
    public ResponseEntity<?> sendEmailModification (@RequestBody ReservaResponse reservaResponse) throws MessagingException {
        emailService.sendModificacionReservaEmail(reservaResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/cancelacion")
    public ResponseEntity<?> sendEmaiCancelation (@RequestBody ReservaResponse reservaResponse) throws MessagingException {
        emailService.sendCancelacionReservaEmail(reservaResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/factura")
    public ResponseEntity<?> sendFactura(@RequestBody FacturaResponse facturaResponse) throws MessagingException, IOException {
        emailService.sendInfoFactura(facturaResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/contacto")
    public ResponseEntity<?> sendInfoContacto(@RequestBody ContactoResponse contactoResponse) throws MessagingException {
        emailService.sendInfoContacto(contactoResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/reserva/stats")
    public ResponseEntity<?> sendReservaStats(@RequestBody ReservaStadisticsBodyDTO stats) throws MessagingException, IOException {
        emailService.sendStatsReserva(stats);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/factura/stats")
    public ResponseEntity<?> sendFacturaStats(@RequestBody FacturaStadisticsbodyDTO stats) throws MessagingException, IOException {
        emailService.sendStatsFactura(stats);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/plato/stats")
    public ResponseEntity<?> sendPlatoStats(@RequestBody PlatoStadisticsBodyDTO stats) throws MessagingException, IOException {
        emailService.sendStatsPlato(stats);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }

    @PostMapping("/send/cliente/stats")
    public ResponseEntity<?> sendClienteStats(@RequestBody ClienteStadisticsBodyDTO stats) throws MessagingException, IOException {
        emailService.sendStatsCliente(stats);
        return ResponseEntity.ok().body("Correo enviado exitosamente");
    }
}
package es.daw2.microservice_email_v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.daw2.microservice_email_v1.services.dtos.ReservaResponse;
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
        return ResponseEntity.ok().body("Correo enviado exitosamente!");
    }

    @PostMapping("/send/modificacion")
    public ResponseEntity<?> sendEmailModification (@RequestBody ReservaResponse reservaResponse) throws MessagingException {
        emailService.sendModificacionReservaEmail(reservaResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente!");
    }

    @PostMapping("/send/cancelacion")
    public ResponseEntity<?> sendEmaiCancelation (@RequestBody ReservaResponse reservaResponse) throws MessagingException {
        emailService.sendCancelacionReservaEmail(reservaResponse);
        return ResponseEntity.ok().body("Correo enviado exitosamente!");
    }
    
}

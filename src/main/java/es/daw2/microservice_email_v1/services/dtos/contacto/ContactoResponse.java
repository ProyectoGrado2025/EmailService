package es.daw2.microservice_email_v1.services.dtos.contacto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContactoResponse implements Serializable{
    
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String motivoContacto;
}

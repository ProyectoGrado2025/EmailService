package es.daw2.microservice_email_v1.services.implementations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import es.daw2.microservice_email_v1.services.dtos.contacto.ContactoResponse;
import es.daw2.microservice_email_v1.services.dtos.facturas.FacturaResponse;
import es.daw2.microservice_email_v1.services.dtos.reservas.ReservaResponse;
import es.daw2.microservice_email_v1.services.dtos.stadistics.clientes.ClienteStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.facturas.FacturaStadisticsbodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.platos.PlatoStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.reservas.ReservaStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.interfaces.IFEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:email.properties")
public class ImpEmailService implements IFEmailService{

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final PDFService pdfService;

    @Value("${email.usernameContact}")
    private String EMAIL_CONTACT;

    public ImpEmailService (JavaMailSender javaMailSender, TemplateEngine templateEngine, PDFService pdfService){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.pdfService = pdfService;
    }

    @Override
    public void sendConfirmacionReservaEmail(ReservaResponse reserva) throws MessagingException {
        String icsContent = generarContenidoICS(reserva);
        sendEmailWithTemplate(reserva, "emailBodyConfirmation.html", "Confirmación de tu reserva", icsContent);
    }

    @Override
    public void sendModificacionReservaEmail(ReservaResponse reserva) throws MessagingException {
        String icsContent = generarContenidoICS(reserva);
        sendEmailWithTemplate(reserva, "emailBodyModification.html", "Modificación de tu reserva", icsContent);
    }

    @Override
    public void sendCancelacionReservaEmail(ReservaResponse reserva) throws MessagingException {
        sendEmailWithTemplate(reserva, "emailBodyCancelation.html", "Cancelación de tu reserva", null);
    }

    @Override
    public void sendInfoFactura (FacturaResponse facturaResponse) throws MessagingException, IOException{
        sendFacturaTemplate(facturaResponse, "emailBodyInvoice.html", "Factura de tu reserva");
    }

    @Override
    public void sendInfoContacto (ContactoResponse contactoResponse) throws MessagingException{
        sendContactoTemplate(contactoResponse, "emailBodyContacto.html", "Nuevo mensaje de contacto");
    }

    @Override
    public void sendStatsReserva(ReservaStadisticsBodyDTO stats) throws MessagingException, IOException {
        byte[] pdf = pdfService.generarReservaReporte(stats);
        sendStatsEmail(pdf, "Estadísticas generadas - Reservas", "reporte-reservas");
    }

    @Override
    public void sendStatsFactura(FacturaStadisticsbodyDTO stats) throws MessagingException, IOException {
        byte[] pdf = pdfService.generarFacturaReporte(stats);
        sendStatsEmail(pdf, "Estadísticas generadas - Facturas", "reporte-facturas");
    }

    @Override
    public void sendStatsPlato(PlatoStadisticsBodyDTO stats) throws MessagingException, IOException{
        byte[] pdf = pdfService.generarPlatoReporte(stats);
        sendStatsEmail(pdf, "Estadísticas generadas - Platos", "reporte-platos");
    }

    @Override
    public void sendStatsCliente(ClienteStadisticsBodyDTO stats) throws MessagingException, IOException{
        byte[] pdf = pdfService.generarClienteReporte(stats);
        sendStatsEmail(pdf, "Estadísticas generadas - Cliente y Rangos", "reporte-clientes-rango");
    }

    private void sendStatsEmail(byte[] pdf, String subject, String prefijo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(EMAIL_CONTACT);
        helper.setSubject(subject);
        helper.setText("Se han generado los stats de la base de datos", false);

        String filename = prefijo + "-" + LocalDate.now() + ".pdf";
        helper.addAttachment(filename, new ByteArrayResource(pdf), "application/pdf");

        javaMailSender.send(message);
    }

    private void sendContactoTemplate(ContactoResponse contactoResponse, String templateName, String subject) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(EMAIL_CONTACT);
        helper.setSubject(subject);

        Context context = new Context();
        context.setVariable("contacto", contactoResponse);

        String htmlContent = templateEngine.process(templateName, context);

        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    private void sendFacturaTemplate(FacturaResponse facturaResponse, String templateName, String subject) throws MessagingException, IOException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(facturaResponse.getClienteResponse().getEmailCliente());
        helper.setSubject(subject);

        Context context = new Context();
        context.setVariable("factura", facturaResponse);
        context.setVariable("address", "Boadilla del Monte");

        String htmlContent = templateEngine.process(templateName, context);

        helper.setText(htmlContent, true);

        ClassPathResource logo = new ClassPathResource("static/images/logo_restaurant.webp");
        helper.addInline("logoImage", logo);

        byte[] pdf = pdfService.generarFacturaPdf(facturaResponse);
        helper.addAttachment("factura-" + facturaResponse.getFacturaId() + ".pdf", new ByteArrayResource(pdf), "application/pdf");

        javaMailSender.send(message);
    }

    private void sendEmailWithTemplate(ReservaResponse reserva, String templateName, String subject, String icsContent) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reserva.getClienteEmail());
        helper.setSubject(subject);

        Context context = new Context();
        context.setVariable("reserva", reserva);
        context.setVariable("address", "Boadilla del Monte");

        String htmlContent = templateEngine.process(templateName, context);

        helper.setText(htmlContent, true);

        ClassPathResource logo = new ClassPathResource("static/images/logo_restaurant.webp");
        helper.addInline("logoImage", logo);

        if(icsContent != null){
            helper.addAttachment(
                "reserva.ics",
                new ByteArrayResource(icsContent.getBytes(StandardCharsets.UTF_8)),
                "text/calendar"
            );
        }

        javaMailSender.send(message);
    }

    private String generarContenidoICS(ReservaResponse reserva) {
        ZoneId zonaMadrid = ZoneId.of("Europe/Madrid");
        DateTimeFormatter dtfLocal = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
        DateTimeFormatter dtfUTC = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").withZone(ZoneOffset.UTC);

        ZonedDateTime startZdt = reserva.getReservaFecha().atZone(zonaMadrid);
        ZonedDateTime endZdt = reserva.getReservaFin().atZone(zonaMadrid);
        String start = startZdt.format(dtfLocal);
        String end = endZdt.format(dtfLocal);
        String dtStamp = dtfUTC.format(Instant.now());

        return "BEGIN:VCALENDAR\n" +
            "VERSION:2.0\n" +
            "PRODID:-//Las Tres Nueces//ReservaApp//ES\n" +
            "BEGIN:VEVENT\n" +
            "UID:" + reserva.getReservaId() + "@tresnueces.com\n" +
            "DTSTAMP:" + dtStamp + "\n" +
            "DTSTART;TZID=Europe/Madrid:" + start + "\n" +
            "DTEND;TZID=Europe/Madrid:" + end + "\n" +
            "SUMMARY:Reserva en Las Tres Nueces\n" +
            "DESCRIPTION:Reserva para " + reserva.getNumeroPersonas() + " persona(s) en la mesa " + reserva.getMesaNumero() + ".\n" +
            "END:VEVENT\n" +
            "END:VCALENDAR";
    }
}

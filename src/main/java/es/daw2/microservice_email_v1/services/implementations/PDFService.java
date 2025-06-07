package es.daw2.microservice_email_v1.services.implementations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import es.daw2.microservice_email_v1.services.dtos.facturas.FacturaResponse;
import es.daw2.microservice_email_v1.services.dtos.stadistics.clientes.ClienteStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.facturas.FacturaStadisticsbodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.platos.PlatoStadisticsBodyDTO;
import es.daw2.microservice_email_v1.services.dtos.stadistics.reservas.ReservaStadisticsBodyDTO;

@Service
public class PDFService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generarFacturaPdf(FacturaResponse facturaResponse) throws IOException {
        return generarPdfDesdeTemplate("emailBodyInvoicepdf", "factura", facturaResponse);
    }

    public byte[] generarReservaReporte(ReservaStadisticsBodyDTO stats) throws IOException {
        return generarPdfDesdeTemplate("emailBodyStatsReserva", "stats", stats);
    }

    public byte[] generarFacturaReporte(FacturaStadisticsbodyDTO stats) throws IOException{
        return generarPdfDesdeTemplate("emailBodyStatsFactura", "stats", stats);
    }

    public byte[] generarPlatoReporte(PlatoStadisticsBodyDTO stats) throws IOException {
        return generarPdfDesdeTemplate("emailBodyStatsPlatos", "stats", stats);
    }

    public byte[] generarClienteReporte(ClienteStadisticsBodyDTO stats) throws IOException{
        return generarPdfDesdeTemplate("emailBodyStatsCliente", "stats", stats);
    }
    
    private byte[] generarPdfDesdeTemplate(String templateNombre, String variableNombre, Object variableValor) throws IOException {
        Context context = new Context();
        context.setVariable(variableNombre, variableValor);

        String html = templateEngine.process(templateNombre, context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }


}
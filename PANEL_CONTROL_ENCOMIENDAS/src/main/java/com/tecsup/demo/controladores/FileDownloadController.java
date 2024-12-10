package com.tecsup.demo.controladores;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.tecsup.demo.modelo.daos.*;
import com.tecsup.demo.modelo.entidades.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class FileDownloadController {

    @Autowired
    private ReclamoRepository reclamoRepository;

    @Autowired
    private MotivoRepository motivoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EncomiendaRepository encomiendaRepository;

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private SeguridadRepository seguridadRepository;

    // Métodos para descargar PDF y XLS de Reclamos y Motivos

    @GetMapping("/download/reclamos/pdf")
    public ResponseEntity<ByteArrayResource> downloadReclamosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Reclamos").setFontSize(18));

        List<Reclamo> reclamos = reclamoRepository.findAll();

        for (Reclamo reclamo : reclamos) {
            document.add(new Paragraph("ID: " + reclamo.getId()));
            document.add(new Paragraph("Descripción: " + reclamo.getDescripcion()));
            document.add(new Paragraph("Fecha: " + reclamo.getFecha().toString()));
            document.add(new Paragraph("Estado: " + reclamo.getEstado()));
            document.add(new Paragraph("Motivo: " + reclamo.getMotivo()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_reclamos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/reclamos/xls")
    public ResponseEntity<ByteArrayResource> downloadReclamosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reclamos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");
        headerRow.createCell(2).setCellValue("Fecha");
        headerRow.createCell(3).setCellValue("Estado");
        headerRow.createCell(4).setCellValue("Motivo");

        List<Reclamo> reclamos = reclamoRepository.findAll();

        int rowCount = 1;
        for (Reclamo reclamo : reclamos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(reclamo.getId());
            row.createCell(1).setCellValue(reclamo.getDescripcion());
            row.createCell(2).setCellValue(reclamo.getFecha().toString());
            row.createCell(3).setCellValue(reclamo.getEstado());
            row.createCell(4).setCellValue(reclamo.getMotivo().getDescripcion()); // Corregido aquí
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_reclamos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/download/motivos/pdf")
    public ResponseEntity<ByteArrayResource> downloadMotivosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Motivos").setFontSize(18));

        List<Motivo> motivos = motivoRepository.findAll();

        for (Motivo motivo : motivos) {
            document.add(new Paragraph("ID: " + motivo.getId()));
            document.add(new Paragraph("Descripción: " + motivo.getDescripcion()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_motivos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/motivos/xls")
    public ResponseEntity<ByteArrayResource> downloadMotivosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Motivos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");

        List<Motivo> motivos = motivoRepository.findAll();

        int rowCount = 1;
        for (Motivo motivo : motivos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(motivo.getId());
            row.createCell(1).setCellValue(motivo.getDescripcion());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_motivos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Clientes

    @GetMapping("/download/clientes/pdf")
    public ResponseEntity<ByteArrayResource> downloadClientesPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Clientes").setFontSize(18));

        List<Cliente> clientes = clienteRepository.findAll();

        for (Cliente cliente : clientes) {
            document.add(new Paragraph("ID: " + cliente.getId()));
            document.add(new Paragraph("DNI: " + cliente.getDni()));
            document.add(new Paragraph("Nombres: " + cliente.getNombres()));
            document.add(new Paragraph("Apellidos: " + cliente.getApellidos()));
            document.add(new Paragraph("Teléfono: " + cliente.getTelefono()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_clientes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/clientes/xls")
    public ResponseEntity<ByteArrayResource> downloadClientesXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("DNI");
        headerRow.createCell(2).setCellValue("Nombres");
        headerRow.createCell(3).setCellValue("Apellidos");
        headerRow.createCell(4).setCellValue("Teléfono");

        List<Cliente> clientes = clienteRepository.findAll();

        int rowCount = 1;
        for (Cliente cliente : clientes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(cliente.getId());
            row.createCell(1).setCellValue(cliente.getDni());
            row.createCell(2).setCellValue(cliente.getNombres());
            row.createCell(3).setCellValue(cliente.getApellidos());
            row.createCell(4).setCellValue(cliente.getTelefono());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_clientes.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Contactos

    @GetMapping("/download/contactos/pdf")
    public ResponseEntity<ByteArrayResource> downloadContactosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Contactos").setFontSize(18));

        List<Contacto> contactos = contactoRepository.findAll();

        for (Contacto contacto : contactos) {
            document.add(new Paragraph("ID: " + contacto.getId()));
            document.add(new Paragraph("Email: " + contacto.getEmail()));
            document.add(new Paragraph("Nombre: " + contacto.getNombre()));
            document.add(new Paragraph("Asunto: " + contacto.getAsunto()));
            document.add(new Paragraph("Mensaje: " + contacto.getMensaje()));
            document.add(new Paragraph("Fecha: " + contacto.getFecha().toString()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_contactos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/contactos/xls")
    public ResponseEntity<ByteArrayResource> downloadContactosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contactos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Nombre");
        headerRow.createCell(3).setCellValue("Asunto");
        headerRow.createCell(4).setCellValue("Mensaje");
        headerRow.createCell(5).setCellValue("Fecha");

        List<Contacto> contactos = contactoRepository.findAll();

        int rowCount = 1;
        for (Contacto contacto : contactos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(contacto.getId());
            row.createCell(1).setCellValue(contacto.getEmail());
            row.createCell(2).setCellValue(contacto.getNombre());
            row.createCell(3).setCellValue(contacto.getAsunto());
            row.createCell(4).setCellValue(contacto.getMensaje());
            row.createCell(5).setCellValue(contacto.getFecha().toString());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_contactos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Terminales

    @GetMapping("/download/terminales/pdf")
    public ResponseEntity<ByteArrayResource> downloadTerminalesPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Terminales").setFontSize(18));

        List<Terminal> terminales = terminalRepository.findAll();

        for (Terminal terminal : terminales) {
            document.add(new Paragraph("ID: " + terminal.getId()));
            document.add(new Paragraph("Nombre: " + terminal.getNombre()));
            document.add(new Paragraph("Dirección: " + terminal.getDireccion()));
            document.add(new Paragraph("Ubicación: " + terminal.getUbicacion()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_terminales.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/terminales/xls")
    public ResponseEntity<ByteArrayResource> downloadTerminalesXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Terminales");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Dirección");
        headerRow.createCell(3).setCellValue("Ubicación");

        List<Terminal> terminales = terminalRepository.findAll();

        int rowCount = 1;
        for (Terminal terminal : terminales) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(terminal.getId());
            row.createCell(1).setCellValue(terminal.getNombre());
            row.createCell(2).setCellValue(terminal.getDireccion());
            row.createCell(3).setCellValue(terminal.getUbicacion());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_terminales.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Empleados

    @GetMapping("/download/empleados/pdf")
    public ResponseEntity<ByteArrayResource> downloadEmpleadosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Empleados").setFontSize(18));

        List<Empleado> empleados = empleadoRepository.findAll();

        for (Empleado empleado : empleados) {
            document.add(new Paragraph("ID: " + empleado.getId()));
            document.add(new Paragraph("Nombres: " + empleado.getNombres()));
            document.add(new Paragraph("Apellidos: " + empleado.getApellidos()));
            document.add(new Paragraph("Correo: " + empleado.getCorreo()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_empleados.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/empleados/xls")
    public ResponseEntity<ByteArrayResource> downloadEmpleadosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombres");
        headerRow.createCell(2).setCellValue("Apellidos");
        headerRow.createCell(3).setCellValue("Correo");

        List<Empleado> empleados = empleadoRepository.findAll();

        int rowCount = 1;
        for (Empleado empleado : empleados) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(empleado.getId());
            row.createCell(1).setCellValue(empleado.getNombres());
            row.createCell(2).setCellValue(empleado.getApellidos());
            row.createCell(3).setCellValue(empleado.getCorreo());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_empleados.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Vehículos

    @GetMapping("/download/vehiculos/pdf")
    public ResponseEntity<ByteArrayResource> downloadVehiculosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Vehículos").setFontSize(18));

        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        for (Vehiculo vehiculo : vehiculos) {
            document.add(new Paragraph("ID: " + vehiculo.getId()));
            document.add(new Paragraph("Placa: " + vehiculo.getPlacaVehiculo()));
            document.add(new Paragraph("Estado: " + vehiculo.getEstadoVehiculo()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_vehiculos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/vehiculos/xls")
    public ResponseEntity<ByteArrayResource> downloadVehiculosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Vehículos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Placa");
        headerRow.createCell(2).setCellValue("Estado");

        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        int rowCount = 1;
        for (Vehiculo vehiculo : vehiculos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(vehiculo.getId());
            row.createCell(1).setCellValue(vehiculo.getPlacaVehiculo());
            row.createCell(2).setCellValue(vehiculo.getEstadoVehiculo());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_vehiculos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Encomiendas

    @GetMapping("/download/encomiendas/pdf")
    public ResponseEntity<ByteArrayResource> downloadEncomiendasPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Encomiendas").setFontSize(18));

        List<Encomienda> encomiendas = encomiendaRepository.findAll();

        for (Encomienda encomienda : encomiendas) {
            document.add(new Paragraph("ID: " + encomienda.getId()));
            document.add(new Paragraph("Descripción: " + encomienda.getDescripcion()));
            document.add(new Paragraph("Remitente: " + encomienda.getRemitente().getNombres()));
            document.add(new Paragraph("Destinatario: " + encomienda.getDestinatario().getNombres()));
            document.add(new Paragraph("Vehículo: " + encomienda.getVehiculo().getPlacaVehiculo()));
            document.add(new Paragraph("Terminal Partida: " + encomienda.getTerminalPartida().getNombre()));
            document.add(new Paragraph("Terminal Destino: " + encomienda.getTerminalDestino().getNombre()));
            document.add(new Paragraph("Volumen: " + encomienda.getVolumen()));
            document.add(new Paragraph("Fecha Salida: " + encomienda.getFechaSalida()));
            document.add(new Paragraph("Fecha Llegada: " + encomienda.getFechaLlegada()));
            document.add(new Paragraph("Estado: " + encomienda.getEstado()));
            document.add(new Paragraph("Condición Envío: " + encomienda.getCondicionEnvio()));
            document.add(new Paragraph("Cantidad Paquetes: " + encomienda.getCantidadPaquetes()));
            document.add(new Paragraph("Fecha Registro: " + encomienda.getFechaRegistro()));
            document.add(new Paragraph("Fecha Entrega: " + encomienda.getFechaEntrega()));
            document.add(new Paragraph("Empleado Registro: " + encomienda.getEmpleadoRegistro().getNombres()));
            document.add(new Paragraph("Empleado Entrega: " + (encomienda.getEmpleadoEntrega() != null ? encomienda.getEmpleadoEntrega().getNombres() : "")));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_encomiendas.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/encomiendas/xls")
    public ResponseEntity<ByteArrayResource> downloadEncomiendasXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Encomiendas");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");
        headerRow.createCell(2).setCellValue("Remitente");
        headerRow.createCell(3).setCellValue("Destinatario");
        headerRow.createCell(4).setCellValue("Vehículo");
        headerRow.createCell(5).setCellValue("Terminal Partida");
        headerRow.createCell(6).setCellValue("Terminal Destino");
        headerRow.createCell(7).setCellValue("Volumen");
        headerRow.createCell(8).setCellValue("Fecha Salida");
        headerRow.createCell(9).setCellValue("Fecha Llegada");
        headerRow.createCell(10).setCellValue("Estado");
        headerRow.createCell(11).setCellValue("Condición Envío");
        headerRow.createCell(12).setCellValue("Cantidad Paquetes");
        headerRow.createCell(13).setCellValue("Fecha Registro");
        headerRow.createCell(14).setCellValue("Fecha Entrega");
        headerRow.createCell(15).setCellValue("Empleado Registro");
        headerRow.createCell(16).setCellValue("Empleado Entrega");

        List<Encomienda> encomiendas = encomiendaRepository.findAll();

        int rowCount = 1;
        for (Encomienda encomienda : encomiendas) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(encomienda.getId());
            row.createCell(1).setCellValue(encomienda.getDescripcion());
            row.createCell(2).setCellValue(encomienda.getRemitente().getNombres());
            row.createCell(3).setCellValue(encomienda.getDestinatario().getNombres());
            row.createCell(4).setCellValue(encomienda.getVehiculo().getPlacaVehiculo());
            row.createCell(5).setCellValue(encomienda.getTerminalPartida().getNombre());
            row.createCell(6).setCellValue(encomienda.getTerminalDestino().getNombre());
            row.createCell(7).setCellValue(encomienda.getVolumen());
            row.createCell(8).setCellValue(encomienda.getFechaSalida().toString());
            row.createCell(9).setCellValue(encomienda.getFechaLlegada().toString());
            row.createCell(10).setCellValue(encomienda.getEstado());
            row.createCell(11).setCellValue(encomienda.getCondicionEnvio());
            row.createCell(12).setCellValue(encomienda.getCantidadPaquetes());
            row.createCell(13).setCellValue(encomienda.getFechaRegistro().toString());
            row.createCell(14).setCellValue(encomienda.getFechaEntrega() != null ? encomienda.getFechaEntrega().toString() : "");
            row.createCell(15).setCellValue(encomienda.getEmpleadoRegistro().getNombres());
            row.createCell(16).setCellValue(encomienda.getEmpleadoEntrega() != null ? encomienda.getEmpleadoEntrega().getNombres() : "");
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_encomiendas.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Comprobantes

    @GetMapping("/download/comprobantes/pdf")
    public ResponseEntity<ByteArrayResource> downloadComprobantesPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Comprobantes").setFontSize(18));

        List<Comprobante> comprobantes = comprobanteRepository.findAll();

        for (Comprobante comprobante : comprobantes) {
            document.add(new Paragraph("ID: " + comprobante.getId()));
            document.add(new Paragraph("Encomienda: " + comprobante.getEncomienda().getId()));
            document.add(new Paragraph("Estado Pago: " + comprobante.getEstadoPago()));
            document.add(new Paragraph("Monto: " + comprobante.getMonto()));
            document.add(new Paragraph("Fecha Pago: " + comprobante.getFechaPago()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_comprobantes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/comprobantes/xls")
    public ResponseEntity<ByteArrayResource> downloadComprobantesXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Comprobantes");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Encomienda");
        headerRow.createCell(2).setCellValue("Estado Pago");
        headerRow.createCell(3).setCellValue("Monto");
        headerRow.createCell(4).setCellValue("Fecha Pago");

        List<Comprobante> comprobantes = comprobanteRepository.findAll();

        int rowCount = 1;
        for (Comprobante comprobante : comprobantes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(comprobante.getId());
            row.createCell(1).setCellValue(comprobante.getEncomienda().getId());
            row.createCell(2).setCellValue(comprobante.getEstadoPago());
            row.createCell(3).setCellValue(comprobante.getMonto());
            row.createCell(4).setCellValue(comprobante.getFechaPago() != null ? comprobante.getFechaPago().toString() : "");
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_comprobantes.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Métodos para descargar PDF y XLS de Seguridad

    @GetMapping("/download/seguridad/pdf")
    public ResponseEntity<ByteArrayResource> downloadSeguridadPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Seguridad").setFontSize(18));

        List<Seguridad> seguridadList = seguridadRepository.findAll();

        for (Seguridad seguridad : seguridadList) {
            document.add(new Paragraph("ID: " + seguridad.getId()));
            document.add(new Paragraph("Encomienda: " + seguridad.getEncomienda().getId()));
            document.add(new Paragraph("Clave Habilitada: " + seguridad.getClaveHabilitada()));
            document.add(new Paragraph("Clave Estática: " + seguridad.getClaveEstatica()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_seguridad.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/seguridad/xls")
    public ResponseEntity<ByteArrayResource> downloadSeguridadXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Seguridad");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Encomienda");
        headerRow.createCell(2).setCellValue("Clave Habilitada");
        headerRow.createCell(3).setCellValue("Clave Estática");

        List<Seguridad> seguridadList = seguridadRepository.findAll();

        int rowCount = 1;
        for (Seguridad seguridad : seguridadList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(seguridad.getId());
            row.createCell(1).setCellValue(seguridad.getEncomienda().getId());
            row.createCell(2).setCellValue(seguridad.getClaveHabilitada());
            row.createCell(3).setCellValue(seguridad.getClaveEstatica());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_seguridad.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
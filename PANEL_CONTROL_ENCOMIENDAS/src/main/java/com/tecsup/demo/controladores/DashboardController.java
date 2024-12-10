package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Comprobante;
import com.tecsup.demo.modelo.entidades.Encomienda;
import com.tecsup.demo.modelo.entidades.Reclamo;
import com.tecsup.demo.servicios.ComprobanteService;
import com.tecsup.demo.servicios.EncomiendaService;
import com.tecsup.demo.servicios.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private EncomiendaService encomiendaService;

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Encomienda> encomiendas = encomiendaService.listar();
        List<Reclamo> reclamos = reclamoService.listar();
        List<Comprobante> comprobantes = comprobanteService.listar();

        Map<Integer, Long> encomiendasPorMes = encomiendas.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getFechaRegistro().getMonthValue(),
                        Collectors.counting()
                ));

        Map<Integer, Long> reclamosPorMes = reclamos.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getFecha().getMonth(),
                        Collectors.counting()
                ));

        Map<Integer, Double> montosComprobantesPorMes = comprobantes.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getFechaPago().getMonthValue(),
                        Collectors.summingDouble(Comprobante::getMonto)
                ));

        model.addAttribute("encomiendasPorMes", encomiendasPorMes);
        model.addAttribute("reclamosPorMes", reclamosPorMes);
        model.addAttribute("montosComprobantesPorMes", montosComprobantesPorMes);

        return "dashboard";
    }
}
package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Comprobante;
import com.tecsup.demo.servicios.ComprobanteService;
import com.tecsup.demo.servicios.EncomiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private EncomiendaService encomiendaService;

    @GetMapping("/listarComprobantes")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Comprobantes");
        model.addAttribute("comprobantes", comprobanteService.listar());
        return "listarComprobantesView";
    }

    @GetMapping("/formComprobante")
    public String crear(Map<String, Object> model) {
        Comprobante comprobante = new Comprobante();
        model.put("comprobante", comprobante);
        model.put("titulo", "Formulario de Comprobante");
        model.put("encomiendas", encomiendaService.listar());
        return "formComprobanteView";
    }

    @GetMapping("/formComprobante/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Comprobante comprobante = null;
        if (id > 0) {
            comprobante = comprobanteService.buscar(id);
            if (comprobante == null) {
                return "redirect:/listarComprobantes";
            }
        } else {
            return "redirect:/listarComprobantes";
        }
        model.put("comprobante", comprobante);
        model.put("titulo", "Editar Comprobante");
        model.put("encomiendas", encomiendaService.listar());
        return "formComprobanteView";
    }

    @PostMapping("/guardarComprobante")
    public String guardar(@Valid Comprobante comprobante, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Comprobante");
            model.addAttribute("encomiendas", encomiendaService.listar());
            return "formComprobanteView";
        }
        comprobanteService.grabar(comprobante);
        status.setComplete();
        return "redirect:/listarComprobantes";
    }

    @GetMapping("/eliminarComprobante/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            comprobanteService.eliminar(id);
        }
        return "redirect:/listarComprobantes";
    }
}
package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Seguridad;
import com.tecsup.demo.servicios.SeguridadService;
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
@SessionAttributes("seguridad")
public class SeguridadController {

    @Autowired
    private SeguridadService seguridadService;

    @Autowired
    private EncomiendaService encomiendaService;

    @GetMapping("/listarSeguridades")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Configuraciones de Seguridad");
        model.addAttribute("seguridades", seguridadService.listar());
        return "listarSeguridadesView";
    }

    @GetMapping("/formSeguridad")
    public String crear(Map<String, Object> model) {
        Seguridad seguridad = new Seguridad();
        model.put("seguridad", seguridad);
        model.put("titulo", "Formulario de Seguridad");
        model.put("encomiendas", encomiendaService.listar());
        return "formSeguridadView";
    }

    @GetMapping("/formSeguridad/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Seguridad seguridad = null;
        if (id > 0) {
            seguridad = seguridadService.buscar(id);
            if (seguridad == null) {
                return "redirect:/listarSeguridades";
            }
        } else {
            return "redirect:/listarSeguridades";
        }
        model.put("seguridad", seguridad);
        model.put("titulo", "Editar Seguridad");
        model.put("encomiendas", encomiendaService.listar());
        return "formSeguridadView";
    }

    @PostMapping("/guardarSeguridad")
    public String guardar(@Valid Seguridad seguridad, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Seguridad");
            model.addAttribute("encomiendas", encomiendaService.listar());
            return "formSeguridadView";
        }
        seguridadService.grabar(seguridad);
        status.setComplete();
        return "redirect:/listarSeguridades";
    }

    @GetMapping("/eliminarSeguridad/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            seguridadService.eliminar(id);
        }
        return "redirect:/listarSeguridades";
    }
}
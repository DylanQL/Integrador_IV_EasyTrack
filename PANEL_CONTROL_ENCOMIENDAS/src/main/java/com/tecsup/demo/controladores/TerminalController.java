package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Terminal;
import com.tecsup.demo.servicios.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @GetMapping("/listarTerminales")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Terminales");
        model.addAttribute("terminales", terminalService.listar());
        return "listarTerminalesView";
    }

    @GetMapping("/formTerminal")
    public String crear(Map<String, Object> model) {
        Terminal terminal = new Terminal();
        model.put("terminal", terminal);
        model.put("titulo", "Formulario de Terminal");
        return "formTerminalView";
    }

    @GetMapping("/formTerminal/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Terminal terminal = null;
        if (id > 0) {
            terminal = terminalService.buscar(id);
            if (terminal == null) {
                return "redirect:/listarTerminales";
            }
        } else {
            return "redirect:/listarTerminales";
        }
        model.put("terminal", terminal);
        model.put("titulo", "Editar Terminal");
        return "formTerminalView";
    }

    @PostMapping("/guardarTerminal")
    public String guardar(@Valid Terminal terminal, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Terminal");
            return "formTerminalView";
        }
        terminalService.grabar(terminal);
        status.setComplete();
        return "redirect:/listarTerminales";
    }

    @GetMapping("/eliminarTerminal/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            terminalService.eliminar(id);
        }
        return "redirect:/listarTerminales";
    }
}
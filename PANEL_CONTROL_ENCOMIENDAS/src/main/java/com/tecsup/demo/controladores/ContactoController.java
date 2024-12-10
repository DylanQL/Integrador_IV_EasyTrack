package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Contacto;
import com.tecsup.demo.servicios.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("contacto")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping("/listarContactos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Contactos");
        model.addAttribute("contactos", contactoService.listar());
        return "listarContactosView";
    }

    @GetMapping("/formContacto")
    public String crear(Map<String, Object> model) {
        Contacto contacto = new Contacto();
        model.put("contacto", contacto);
        model.put("titulo", "Formulario de Contacto");
        return "formContactoView";
    }

    @GetMapping("/formContacto/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Contacto contacto = null;
        if (id > 0) {
            contacto = contactoService.buscar(id);
            if (contacto == null) {
                return "redirect:/listarContactos";
            }
        } else {
            return "redirect:/listarContactos";
        }
        model.put("contacto", contacto);
        model.put("titulo", "Editar Contacto");
        return "formContactoView";
    }

    @PostMapping("/guardarContacto")
    public String guardar(@Valid Contacto contacto, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Contacto");
            return "formContactoView";
        }
        contactoService.grabar(contacto);
        status.setComplete();
        return "redirect:/listarContactos";
    }

    @GetMapping("/eliminarContacto/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            contactoService.eliminar(id);
        }
        return "redirect:/listarContactos";
    }
}
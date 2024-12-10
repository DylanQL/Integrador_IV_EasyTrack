package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Cliente;
import com.tecsup.demo.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listarClientes")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clienteService.listar());
        return "listarClientesView";
    }

    @GetMapping("/formCliente")
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "formClienteView";
    }

    @GetMapping("/formCliente/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteService.buscar(id);
            if (cliente == null) {
                return "redirect:/listarClientes";
            }
        } else {
            return "redirect:/listarClientes";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "formClienteView";
    }

    @PostMapping("/guardarCliente")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            return "formClienteView";
        }
        clienteService.grabar(cliente);
        status.setComplete();
        return "redirect:/listarClientes";
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            clienteService.eliminar(id);
        }
        return "redirect:/listarClientes";
    }
}
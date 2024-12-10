package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.*;
import com.tecsup.demo.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("encomienda")
public class EncomiendaController {

    @Autowired
    private EncomiendaService encomiendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listarEncomiendas")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Encomiendas");
        model.addAttribute("encomiendas", encomiendaService.listar());
        return "listarEncomiendasView";
    }

    @GetMapping("/formEncomienda")
    public String crear(Map<String, Object> model) {
        Encomienda encomienda = new Encomienda();
        model.put("encomienda", encomienda);
        model.put("titulo", "Formulario de Encomienda");
        cargarListas(model);
        return "formEncomiendaView";
    }

    @GetMapping("/formEncomienda/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Encomienda encomienda = null;
        if (id > 0) {
            encomienda = encomiendaService.buscar(id);
            if (encomienda == null) {
                return "redirect:/listarEncomiendas";
            }
        } else {
            return "redirect:/listarEncomiendas";
        }
        model.put("encomienda", encomienda);
        model.put("titulo", "Editar Encomienda");
        cargarListas(model);
        return "formEncomiendaView";
    }

    @PostMapping("/guardarEncomienda")
    public String guardar(@Valid Encomienda encomienda, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Encomienda");
            cargarListas(model.asMap());
            return "formEncomiendaView";
        }
        encomiendaService.grabar(encomienda);
        status.setComplete();
        return "redirect:/listarEncomiendas";
    }

    @GetMapping("/eliminarEncomienda/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            encomiendaService.eliminar(id);
        }
        return "redirect:/listarEncomiendas";
    }

    private void cargarListas(Map<String, Object> model) {
        List<Cliente> clientes = clienteService.listar();
        List<Vehiculo> vehiculos = vehiculoService.listar();
        List<Terminal> terminales = terminalService.listar();
        List<Empleado> empleados = empleadoService.listar();
        model.put("clientes", clientes);
        model.put("vehiculos", vehiculos);
        model.put("terminales", terminales);
        model.put("empleados", empleados);
    }
}
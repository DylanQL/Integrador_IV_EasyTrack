package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Empleado;
import com.tecsup.demo.servicios.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listarEmpleados")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Empleados");
        model.addAttribute("empleados", empleadoService.listar());
        return "listarEmpleadosView";
    }

    @GetMapping("/formEmpleado")
    public String crear(Map<String, Object> model) {
        Empleado empleado = new Empleado();
        model.put("empleado", empleado);
        model.put("titulo", "Formulario de Empleado");
        return "formEmpleadoView";
    }

    @GetMapping("/formEmpleado/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Empleado empleado = null;
        if (id > 0) {
            empleado = empleadoService.buscar(id);
            if (empleado == null) {
                return "redirect:/listarEmpleados";
            }
        } else {
            return "redirect:/listarEmpleados";
        }
        model.put("empleado", empleado);
        model.put("titulo", "Editar Empleado");
        return "formEmpleadoView";
    }

    @PostMapping("/guardarEmpleado")
    public String guardar(@Valid Empleado empleado, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Empleado");
            return "formEmpleadoView";
        }
        empleadoService.grabar(empleado);
        status.setComplete();
        return "redirect:/listarEmpleados";
    }

    @GetMapping("/eliminarEmpleado/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            empleadoService.eliminar(id);
        }
        return "redirect:/listarEmpleados";
    }
}
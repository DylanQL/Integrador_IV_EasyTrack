package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Vehiculo;
import com.tecsup.demo.servicios.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/listarVehiculos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Vehículos");
        model.addAttribute("vehiculos", vehiculoService.listar());
        return "listarVehiculosView";
    }

    @GetMapping("/formVehiculo")
    public String crear(Map<String, Object> model) {
        Vehiculo vehiculo = new Vehiculo();
        model.put("vehiculo", vehiculo);
        model.put("titulo", "Formulario de Vehículo");
        return "formVehiculoView";
    }

    @GetMapping("/formVehiculo/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Vehiculo vehiculo = null;
        if (id > 0) {
            vehiculo = vehiculoService.buscar(id);
            if (vehiculo == null) {
                return "redirect:/listarVehiculos";
            }
        } else {
            return "redirect:/listarVehiculos";
        }
        model.put("vehiculo", vehiculo);
        model.put("titulo", "Editar Vehículo");
        return "formVehiculoView";
    }

    @PostMapping("/guardarVehiculo")
    public String guardar(@Valid Vehiculo vehiculo, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Vehículo");
            return "formVehiculoView";
        }
        vehiculoService.grabar(vehiculo);
        status.setComplete();
        return "redirect:/listarVehiculos";
    }

    @GetMapping("/eliminarVehiculo/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            vehiculoService.eliminar(id);
        }
        return "redirect:/listarVehiculos";
    }
}
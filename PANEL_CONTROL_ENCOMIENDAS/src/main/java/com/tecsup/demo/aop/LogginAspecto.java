package com.tecsup.demo.aop;

import com.tecsup.demo.modelo.daos.AuditoriaDAO;
import com.tecsup.demo.modelo.entidades.Auditoria;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;

@Component
@Aspect
public class LogginAspecto {

    @Autowired
    private AuditoriaDAO auditoriaDao;

    @After("execution(* com.tecsup.demo.controladores.*Controller.guardar*(..)) ||" +
            "execution(* com.tecsup.demo.controladores.*Controller.editar*(..)) ||" +
            "execution(* com.tecsup.demo.controladores.*Controller.eliminar*(..))")
    public void auditoria(JoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Integer id = null;

        Object[] parametros = joinPoint.getArgs();
        String tabla = obtenerTablaParaEntidad(joinPoint);

        if (parametros.length > 0 && parametros[0] instanceof Integer) {
            id = (Integer) parametros[0];
        } else if (parametros.length > 0 && parametros[0] instanceof Object) {
            id = obtenerIdDesdeEntidad(parametros[0]);
        }

        // Obtener el usuario autenticado
        String usuario = obtenerUsuarioAutenticado();

        auditoriaDao.save(new Auditoria(tabla, id, Calendar.getInstance().getTime(), usuario, metodo));
        logger.info(metodo + "(): registrando auditoria para entidad.");
    }

    private String obtenerTablaParaEntidad(JoinPoint joinPoint) {
        String nombreClase = joinPoint.getTarget().getClass().getSimpleName();
        switch (nombreClase) {
            case "ReclamoController":
                return "reclamos";
            case "MotivoController":
                return "motivo";
            case "ClienteController":
                return "cliente";
            case "ContactoController":
                return "contacto";
            case "TerminalController":
                return "terminal";
            case "EmpleadoController":
                return "empleado";
            case "VehiculoController":
                return "vehiculo";
            case "EncomiendaController":
                return "encomienda";
            case "ComprobanteController":
                return "comprobante";
            case "SeguridadController":
                return "seguridad";
            default:
                return "desconocido";
        }
    }

    private Integer obtenerIdDesdeEntidad(Object entidad) {
        try {
            return (Integer) entidad.getClass().getMethod("getId").invoke(entidad);
        } catch (Exception e) {
            return null;
        }
    }

    private String obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            return oAuth2User.getAttribute("email"); // Ajusta esto según la estructura de tu usuario autenticado
        }
        return "usuario"; // Valor por defecto si no se puede obtener el usuario
    }
}
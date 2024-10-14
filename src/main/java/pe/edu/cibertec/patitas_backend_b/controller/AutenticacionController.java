package pe.edu.cibertec.patitas_backend_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_b.dto.*;
import pe.edu.cibertec.patitas_backend_b.service.AutenticacionService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        try {
            Thread.sleep(Duration.ofSeconds(5));
            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            System.out.println("Respuesta backend: " + Arrays.toString(datosUsuario));

            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }
            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");

        }

    }

    @PostMapping("/logout")
    public LogoutResponseDTO logout(@RequestBody LogoutRequest2DTO logoutRequestDTO) {
        try {
            Thread.sleep(Duration.ofSeconds(5));
            String[] datosUsuario = autenticacionService.getUser(logoutRequestDTO);
            Date fechaLogout = autenticacionService.cerrarSesionUsuario(new LogoutRequestDTO(datosUsuario[0],datosUsuario[1]));
            System.out.println("Respuesta backend: " + fechaLogout);

            if (fechaLogout == null) {
                return new LogoutResponseDTO(false, null, "Error: No se pudo registrar auditoría");
            }
            return new LogoutResponseDTO(true, fechaLogout, "");

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new LogoutResponseDTO(false, null, "Error: Ocurrió un problema");
        }

    }

}

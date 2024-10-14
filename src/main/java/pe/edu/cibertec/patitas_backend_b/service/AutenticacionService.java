package pe.edu.cibertec.patitas_backend_b.service;

import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LogoutRequest2DTO;
import pe.edu.cibertec.patitas_backend_b.dto.LogoutRequestDTO;

import java.io.IOException;
import java.util.Date;

public interface AutenticacionService {
    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;
    Date cerrarSesionUsuario(LogoutRequestDTO logoutRequestDTO) throws IOException;
    String[] getUser(LogoutRequest2DTO logoutRequest2DTO) throws IOException;
}

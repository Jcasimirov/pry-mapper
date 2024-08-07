package com.mapper.platform.user.application.validators;

import com.mapper.platform.shared.domain.model.valueobjects.Notification;
import com.mapper.platform.user.domain.commands.RegisterUser;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserValidator {
    private static final int DNI_MAX_LENGTH = 8;

    public RegisterUserValidator() {
    }

    public Notification validate(RegisterUser command)
    {
        Notification notification = new Notification();

        String correo = command.getCorreo().trim();
        if (correo.isEmpty()) notification.addError("User correo is required");

        String clave = command.getClave().trim();
        if (clave.isEmpty()) notification.addError("User clave is required");

        String nombre = command.getNombre().trim();
        if (nombre.isEmpty()) notification.addError("User nombre is required");

        String apellido = command.getApellido().trim();
        if (apellido.isEmpty()) notification.addError("User apellido is required");

        String dni = command.getDni().trim();
        if (dni.isEmpty()) notification.addError("Security dni is required");
        if (dni.length() != DNI_MAX_LENGTH) notification.addError("Client dni must be " + DNI_MAX_LENGTH + " characters");

        String foto = command.getFoto().trim();
        String direccion = command.getDireccion().trim();

        if (notification.hasErrors()) return notification;

        return notification;
    }
}

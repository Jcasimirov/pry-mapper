package com.mapper.platform.user.interfaces.rest.transform;

import com.mapper.platform.user.domain.commands.RegisterUser;
import com.mapper.platform.user.interfaces.rest.resources.RegisterUserResource;

public class RegisterUserCommandFromResourceAssembler {
    public static RegisterUser toCommandFromResource(RegisterUserResource resource) {
        return new RegisterUser(
                resource.id(),
                resource.correo(),
                resource.clave(),
                resource.nombre(),
                resource.apellido(),
                resource.dni(),
                resource.foto(),
                resource.direccion(),
                resource.createdAt());
    }
}

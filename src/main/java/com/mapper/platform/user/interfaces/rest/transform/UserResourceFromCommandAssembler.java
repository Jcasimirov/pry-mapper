package com.mapper.platform.user.interfaces.rest.transform;

import com.mapper.platform.user.domain.commands.RegisterUser;
import com.mapper.platform.user.interfaces.rest.resources.UserResource;

public class UserResourceFromCommandAssembler {
    public static UserResource toResourceFromRegisterUser(RegisterUser command) {
        return new UserResource(
                command.getId(),
                command.getCorreo(),
                command.getClave(),
                command.getNombre(),
                command.getApellido(),
                command.getDni(),
                command.getFoto(),
                command.getDireccion() );
    }
    /*
    public static UserResource toResourceFromEditUser(EditClient command) {
        return new UserResource(
                command.getId(),
                command.getCorreo(),
                command.getClave());
    }
    */
}

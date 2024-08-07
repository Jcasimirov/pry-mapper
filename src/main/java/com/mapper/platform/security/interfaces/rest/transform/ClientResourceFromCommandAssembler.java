package com.mapper.platform.security.interfaces.rest.transform;

import com.mapper.platform.security.domain.commands.EditClient;
import com.mapper.platform.security.domain.commands.RegisterClient;
import com.mapper.platform.security.interfaces.rest.resources.ClientResource;

public class ClientResourceFromCommandAssembler {
    public static ClientResource toResourceFromRegisterClient(RegisterClient command) {
        return new ClientResource(
            command.getId(),
            command.getFirstName(),
            command.getLastName(),
            command.getDni(),
            command.getTurn());
    }

    public static ClientResource toResourceFromEditClient(EditClient command) {
        return new ClientResource(
                command.getId(),
                command.getFirstName(),
                command.getLastName(),
                command.getDni(),
                command.getTurn());
    }
}

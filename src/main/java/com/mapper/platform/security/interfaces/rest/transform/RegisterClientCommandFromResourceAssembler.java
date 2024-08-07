package com.mapper.platform.security.interfaces.rest.transform;

import com.mapper.platform.security.domain.commands.RegisterClient;
import com.mapper.platform.security.interfaces.rest.resources.RegisterClientResource;

public class RegisterClientCommandFromResourceAssembler {
    public static RegisterClient toCommandFromResource(RegisterClientResource resource) {
        return new RegisterClient(
                resource.id(),
                resource.firstName(),
                resource.lastName(),
                resource.dni(),
                resource.turn(),
                resource.createdAt());
    }
}

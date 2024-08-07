package com.mapper.platform.security.interfaces.rest.transform;

import com.mapper.platform.security.domain.commands.EditClient;
import com.mapper.platform.security.interfaces.rest.resources.EditClientResource;

public class EditClientCommandFromResourceAssembler {
    public static EditClient toCommandFromResource(EditClientResource resource) {
        return new EditClient(
            resource.id(),
            resource.firstName(),
            resource.lastName(),
            resource.dni(),
            resource.turn(),
            resource.updatedAt());
    }
}

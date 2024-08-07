package com.mapper.platform.security.interfaces.rest.resources;

import com.mapper.platform.security.domain.projections.ClientProjection;
import com.mapper.platform.shared.domain.model.valueobjects.Error;
import java.util.List;

public record GetClientsResponseResource(
    List<ClientProjection> success,
    List<Error> errors
) {}

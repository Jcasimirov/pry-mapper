package com.mapper.platform.security.interfaces.rest.resources;

import com.mapper.platform.shared.domain.model.valueobjects.Error;
import java.util.List;

public record EditClientResponseResource(
        ClientResource success,
        List<Error> errors
) {}

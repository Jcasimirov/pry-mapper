package com.mapper.platform.user.interfaces.rest.resources;

import com.mapper.platform.shared.domain.model.valueobjects.Error;
import java.util.List;

public record RegisterUserResponseResource (
    UserResource success,
    List<Error> errors
) {}
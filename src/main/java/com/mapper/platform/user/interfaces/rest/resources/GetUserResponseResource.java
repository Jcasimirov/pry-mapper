package com.mapper.platform.user.interfaces.rest.resources;

import com.mapper.platform.shared.domain.model.valueobjects.Error;
import com.mapper.platform.user.domain.projections.UserProjection;

import java.util.List;

public record GetUserResponseResource(
    List<UserProjection> success,
    List<Error> errors
) {}
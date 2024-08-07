package com.mapper.platform.security.interfaces.rest.resources;

import com.mapper.platform.security.domain.projections.ClientAuditLogProjection;
import com.mapper.platform.shared.domain.model.valueobjects.Error;
import java.util.List;

public record ClientAuditLogResponseResource(
    List<ClientAuditLogProjection> success,
    List<Error> errors
) {}

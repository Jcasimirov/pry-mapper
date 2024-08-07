package com.mapper.platform.security.domain.queries;

import lombok.Value;

@Value
public class GetClientAuditLogsByClientId {
    private final Long id;

    public GetClientAuditLogsByClientId(Long id) {
        this.id = id;
    }
}

package com.mapper.platform.security.application.queryservices;

import com.mapper.platform.security.domain.projections.ClientProjection;
import com.mapper.platform.security.domain.projections.ClientAuditLogProjection;
import com.mapper.platform.security.domain.queries.GetClientAuditLogsByClientId;
import com.mapper.platform.security.domain.queries.GetClients;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClientQueryService {
    private final QueryGateway queryGateway;

    public ClientQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public List<ClientAuditLogProjection> getAuditLogsByClientId(Long clientId) throws Exception {
        var query = new GetClientAuditLogsByClientId(clientId);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(ClientAuditLogProjection.class)).join();
    }

    public List<ClientProjection> getClients(Integer page, Integer limit) throws Exception {
        var query = new GetClients(page, limit);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(ClientProjection.class)).join();
    }
}

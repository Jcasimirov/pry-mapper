package com.mapper.platform.user.application.queryservices;

import com.mapper.platform.user.domain.projections.UserProjection;
import com.mapper.platform.user.domain.queries.GetUsers;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserQueryService {
    private final QueryGateway queryGateway;

    public UserQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    /*
    public List<ClientAuditLogProjection> getAuditLogsByClientId(Long clientId) throws Exception {
        var query = new GetClientAuditLogsByClientId(clientId);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(ClientAuditLogProjection.class)).join();
    }
    */

    public List<UserProjection> getSession(String correo) throws Exception {
        var query = new GetUsers(correo);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(UserProjection.class)).join();
    }
}

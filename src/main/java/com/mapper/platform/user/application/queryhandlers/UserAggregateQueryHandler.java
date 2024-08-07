package com.mapper.platform.user.application.queryhandlers;

import com.mapper.platform.user.domain.projections.UserProjection;
import com.mapper.platform.user.domain.queries.GetUsers;
import com.mapper.platform.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAggregateQueryHandler {
    private final UserRepository userRepository;

    public UserAggregateQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    public List<UserProjection> handle(GetUsers query) {
        return this.userRepository.getSession(query.getCorreo());
    }

}

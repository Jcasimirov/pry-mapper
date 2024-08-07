package com.mapper.platform.shared.infrastructure.axon;

import com.mapper.platform.security.domain.model.aggregates.Client;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
    @Primary
    @Bean
    public Repository<Client> accountEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Client.class)
            .eventStore(eventStore)
            .build();
    }
}

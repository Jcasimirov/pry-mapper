package com.mapper.platform.security.interfaces.eventhandlers;

import com.mapper.platform.security.domain.events.ClientEdited;
import com.mapper.platform.security.domain.events.ClientRegistered;
import com.mapper.platform.security.domain.processors.ClientDni;
import com.mapper.platform.security.infrastructure.persistence.jpa.repositories.ClientDniRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@ProcessingGroup("clientDni")
public class ClientDniProcessorsEventHandler {
    private final ClientDniRepository clientDniRepository;

    public ClientDniProcessorsEventHandler(ClientDniRepository clientDniRepository) {
        this.clientDniRepository = clientDniRepository;
    }

    @EventHandler
    public void on(ClientRegistered event) {
        clientDniRepository.save(new ClientDni(event.getId(), event.getDni()));
    }

    @EventHandler
    public void on(ClientEdited event) {
        Optional<ClientDni> clientDniOptional = clientDniRepository.findById(event.getId());
        clientDniOptional.ifPresent(clientDniRepository::delete);
        clientDniRepository.save(new ClientDni(event.getId(), event.getDni()));
    }
}

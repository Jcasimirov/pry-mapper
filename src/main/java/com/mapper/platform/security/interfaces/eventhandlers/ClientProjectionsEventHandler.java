package com.mapper.platform.security.interfaces.eventhandlers;

import com.mapper.platform.security.domain.events.ClientEdited;
import com.mapper.platform.security.domain.events.ClientRegistered;
import com.mapper.platform.security.domain.model.valueobjects.ClientStatus;
import com.mapper.platform.security.domain.projections.ClientProjection;
import com.mapper.platform.security.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ClientProjectionsEventHandler {
    private final ClientRepository clientRepository;

    public ClientProjectionsEventHandler(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @EventHandler
    public void on(ClientRegistered event) {
        ClientProjection client = new ClientProjection(
            event.getId(),
            event.getFirstName(),
            event.getLastName(),
            event.getDni(),
            ClientStatus.ACTIVE.name(),
            event.getCreatedAt() /*,
            event.getCampoDefecto()*/ );
        clientRepository.save(client);
    }

    @EventHandler
    public void on(ClientEdited event) {
        Optional<ClientProjection> viewOptional = clientRepository.findById(event.getId());
        if (viewOptional.isPresent()) {
            ClientProjection view = viewOptional.get();
            view.setFirstName(event.getFirstName());
            view.setLastName(event.getLastName());
            view.setDni(event.getDni());
            view.setUpdatedAt(event.getUpdatedAt());
            clientRepository.save(view);
        }
    }
}

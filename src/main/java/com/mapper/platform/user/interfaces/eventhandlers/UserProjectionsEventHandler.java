package com.mapper.platform.user.interfaces.eventhandlers;

import com.mapper.platform.user.domain.events.UserRegistered;
import com.mapper.platform.user.domain.model.valueobjects.UserStatus;
import com.mapper.platform.user.domain.projections.UserProjection;
import com.mapper.platform.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjectionsEventHandler {
    private final UserRepository userRepository;

    public UserProjectionsEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserRegistered event) {
        UserProjection client = new UserProjection(
                event.getId(),
                event.getCorreo(),
                event.getClave(),
                event.getNombre(),
                event.getApellido(),
                event.getDni(),
                event.getFoto(),
                event.getDireccion(),
                event.getCreatedAt());
        userRepository.save(client);
    }
}

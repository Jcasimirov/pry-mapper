package com.mapper.platform.security.application.validators;

import com.mapper.platform.security.domain.commands.RegisterClient;
import com.mapper.platform.security.domain.processors.ClientDni;
import com.mapper.platform.security.infrastructure.persistence.jpa.repositories.ClientDniRepository;
import com.mapper.platform.shared.domain.model.valueobjects.Notification;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class RegisterClientValidator {
    private final ClientDniRepository clientDniRepository;
    private static final int DNI_MAX_LENGTH = 8;

    public RegisterClientValidator(ClientDniRepository clientDniRepository) {
        this.clientDniRepository = clientDniRepository;
    }

    public Notification validate(RegisterClient command)
    {
        Notification notification = new Notification();

        String firstName = command.getFirstName().trim();
        if (firstName.isEmpty()) notification.addError("Security firstname is required");

        String lastName = command.getLastName().trim();
        if (lastName.isEmpty()) notification.addError("Security lastname is required");

        String dni = command.getDni().trim();
        if (dni.isEmpty()) notification.addError("Security dni is required");
        if (dni.length() != DNI_MAX_LENGTH) notification.addError("Client dni must be " + DNI_MAX_LENGTH + " characters");

        String turn = command.getTurn().trim();
        if (turn.isEmpty()) notification.addError("Security turn is required");

        if (notification.hasErrors()) return notification;

        Optional<ClientDni> optional = clientDniRepository.getByDni(dni);
        if (optional.isPresent()) notification.addError("Security dni is taken");

        return notification;
    }
}

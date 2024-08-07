package com.mapper.platform.security.application.commandservices;

import com.mapper.platform.security.application.validators.EditClientValidator;
import com.mapper.platform.security.application.validators.RegisterClientValidator;
import com.mapper.platform.security.domain.commands.EditClient;
import com.mapper.platform.security.domain.commands.RegisterClient;
import com.mapper.platform.shared.domain.model.valueobjects.Notification;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class ClientCommandService {
    private final CommandGateway commandGateway;
    private final RegisterClientValidator registerClientValidator;
    private final EditClientValidator editClientValidator;

    public ClientCommandService(CommandGateway commandGateway, RegisterClientValidator registerClientValidator, EditClientValidator editClientValidator) {
        this.commandGateway = commandGateway;
        this.registerClientValidator = registerClientValidator;
        this.editClientValidator = editClientValidator;
    }

    public Notification register(RegisterClient command) throws Exception {
        Notification notification = this.registerClientValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }

    public Notification edit(EditClient command) throws Exception {
        Notification notification = this.editClientValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }
}

package com.mapper.platform.user.application.commandservices;

import com.mapper.platform.user.application.validators.RegisterUserValidator;
import com.mapper.platform.shared.domain.model.valueobjects.Notification;
import com.mapper.platform.user.domain.commands.RegisterUser;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class UserCommandService {
    private final CommandGateway commandGateway;
    private final RegisterUserValidator registerUserValidator;

    public UserCommandService(CommandGateway commandGateway, RegisterUserValidator registerUserValidator) {
        this.commandGateway = commandGateway;
        this.registerUserValidator = registerUserValidator;
    }

    public Notification register(RegisterUser command) throws Exception {
        Notification notification = this.registerUserValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }

}

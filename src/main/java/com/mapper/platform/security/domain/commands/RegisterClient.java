package com.mapper.platform.security.domain.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import lombok.Value;
import java.time.LocalDateTime;

@Value
public class RegisterClient {
    @TargetAggregateIdentifier
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String turn;
    private LocalDateTime createdAt;

}

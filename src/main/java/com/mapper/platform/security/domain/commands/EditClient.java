package com.mapper.platform.security.domain.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.time.LocalDateTime;

@Value
public class EditClient {
    @TargetAggregateIdentifier
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String turn;
    private LocalDateTime updatedAt;
}

package com.mapper.platform.user.domain.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class RegisterUser {
    @TargetAggregateIdentifier
    private Long id;
    private String correo;
    private String clave;
    private String nombre;
    private String apellido;
    private String dni;
    private String foto;
    private String direccion;
    private LocalDateTime createdAt;

}

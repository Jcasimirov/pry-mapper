package com.mapper.platform.user.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record RegisterUserResource(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id,
    String correo,
    String clave,
    String nombre,
    String apellido,
    String dni,
    String foto,
    String direccion,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    LocalDateTime createdAt
) {
    public RegisterUserResource withId(Long id) {
        LocalDateTime createdAt = LocalDateTime.now();
        return new RegisterUserResource(id, this.correo, this.clave, this.nombre, this.apellido, this.dni, this.foto, this.direccion, createdAt);
    }
}

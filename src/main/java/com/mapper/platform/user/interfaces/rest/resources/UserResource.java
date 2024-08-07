package com.mapper.platform.user.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserResource(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id,
    String correo,
    String clave,
    String nombre,
    String apellido,
    String dni,
    String foto,
    String direccion
) {}

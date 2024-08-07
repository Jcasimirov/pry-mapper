package com.mapper.platform.security.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ClientResource (
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id,
    String firstName,
    String lastName,
    String dni,
    String turn
) {}

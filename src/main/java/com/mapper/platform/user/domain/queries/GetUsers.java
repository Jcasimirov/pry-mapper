package com.mapper.platform.user.domain.queries;

import lombok.Value;

@Value
public class GetUsers {
    private final String correo;

    public GetUsers(String correo) {
        this.correo = correo;
    }
}

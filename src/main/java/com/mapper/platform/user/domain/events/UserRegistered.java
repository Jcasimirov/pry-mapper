package com.mapper.platform.user.domain.events;

import lombok.Value;
import java.time.LocalDateTime;

@Value
public class UserRegistered {
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

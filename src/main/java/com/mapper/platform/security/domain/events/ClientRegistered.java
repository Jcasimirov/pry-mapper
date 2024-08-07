package com.mapper.platform.security.domain.events;

import lombok.Value;
import java.time.LocalDateTime;

@Value
public class ClientRegistered {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private LocalDateTime createdAt;
    //private String campoDefecto;
}

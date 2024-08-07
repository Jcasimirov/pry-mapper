package com.mapper.platform.security.domain.events;

import lombok.Value;
import java.time.LocalDateTime;

@Value
public class ClientEdited {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private LocalDateTime updatedAt;
}

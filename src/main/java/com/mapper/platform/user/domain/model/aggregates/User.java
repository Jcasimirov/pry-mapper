package com.mapper.platform.user.domain.model.aggregates;

import com.mapper.platform.security.domain.commands.*;
import com.mapper.platform.security.domain.events.*;
import com.mapper.platform.security.domain.model.valueobjects.ClientStatus;
import com.mapper.platform.shared.domain.model.valueobjects.DatosUser;
import com.mapper.platform.shared.domain.model.valueobjects.Dni;
import com.mapper.platform.shared.domain.model.valueobjects.PersonName;
import com.mapper.platform.user.domain.commands.RegisterUser;
import com.mapper.platform.user.domain.events.UserRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {
    @AggregateIdentifier
    private Long id;
    private DatosUser datos;
    private ClientStatus status;

    public User() {
    }

    @CommandHandler
    public User(RegisterUser command) {
        UserRegistered event = new UserRegistered(
                command.getId(),
                command.getCorreo(),
                command.getClave(),
                command.getNombre(),
                command.getApellido(),
                command.getDni(),
                command.getFoto(),
                command.getDireccion(),
                command.getCreatedAt()
        );
        apply(event);
    }


    @EventSourcingHandler
    protected void on(UserRegistered event) {
        id = event.getId();
        datos = DatosUser.create(event.getCorreo(), event.getClave()).getSuccess();
        status = ClientStatus.ACTIVE;
    }
}

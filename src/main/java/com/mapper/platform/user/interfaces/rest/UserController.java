package com.mapper.platform.user.interfaces.rest;

import com.mapper.platform.user.application.commandservices.UserCommandService;
import com.mapper.platform.user.application.queryservices.UserQueryService;
import com.mapper.platform.user.domain.projections.UserProjection;
import com.mapper.platform.user.interfaces.rest.resources.GetUserResponseResource;
import com.mapper.platform.user.interfaces.rest.resources.RegisterUserResource;
import com.mapper.platform.user.interfaces.rest.resources.RegisterUserResponseResource;
import com.mapper.platform.user.interfaces.rest.transform.RegisterUserCommandFromResourceAssembler;
import com.mapper.platform.user.interfaces.rest.transform.UserResourceFromCommandAssembler;
import io.hypersistence.tsid.TSID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User Management Endpoints")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Register a new user - citizen")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Register User received successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @CrossOrigin
    public ResponseEntity<RegisterUserResponseResource> register(@RequestBody RegisterUserResource resource) {
        try {
            Long id = TSID.Factory.getTsid().toLong();
            resource = resource.withId(id);
            var command = RegisterUserCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = userCommandService.register(command);
            if (notification.hasErrors()) {
                var response = new RegisterUserResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var userResource = UserResourceFromCommandAssembler.toResourceFromRegisterUser(command);
            var responseResource = new RegisterUserResponseResource(userResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new RegisterUserResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/session/byEmail/{email}")
    @Operation(summary = "Get session user")
    @CrossOrigin
    public ResponseEntity<GetUserResponseResource> getSession(@PathVariable("email") String correo) {
        try {
            List<UserProjection> clients = userQueryService.getSession(correo);
            var response = new GetUserResponseResource(clients, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetUserResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

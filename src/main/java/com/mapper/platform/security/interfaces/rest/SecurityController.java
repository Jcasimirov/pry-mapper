package com.mapper.platform.security.interfaces.rest;

import com.mapper.platform.security.application.commandservices.ClientCommandService;
import com.mapper.platform.security.application.queryservices.ClientQueryService;
import com.mapper.platform.security.domain.projections.ClientProjection;
import com.mapper.platform.security.interfaces.rest.resources.*;
import com.mapper.platform.security.interfaces.rest.transform.ClientResourceFromCommandAssembler;
import com.mapper.platform.security.interfaces.rest.transform.EditClientCommandFromResourceAssembler;
import com.mapper.platform.security.interfaces.rest.transform.RegisterClientCommandFromResourceAssembler;
import com.mapper.platform.shared.interfaces.rest.Pagination;
import io.hypersistence.tsid.TSID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/security", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Segurity", description = "Security Management Endpoints")
@CrossOrigin
public class SecurityController {
    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public SecurityController(ClientCommandService clientCommandService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    @Operation(summary = "Register a new security")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Register Security received successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RegisterClientResponseResource> register(@RequestBody RegisterClientResource resource) {
        try {
            Long id = TSID.Factory.getTsid().toLong();
            resource = resource.withId(id);
            var command = RegisterClientCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = clientCommandService.register(command);
            if (notification.hasErrors()) {
                var response = new RegisterClientResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var clientResource = ClientResourceFromCommandAssembler.toResourceFromRegisterClient(command);
            var responseResource = new RegisterClientResponseResource(clientResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new RegisterClientResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit a security")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Edit Security received successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EditClientResponseResource> edit(@PathVariable("id") Long id, @RequestBody EditClientResource resource) {
        try {
            resource = resource.withId(id);
            var command = EditClientCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = clientCommandService.edit(command);
            if (notification.hasErrors()) {
                var response = new EditClientResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var clientResource = ClientResourceFromCommandAssembler.toResourceFromEditClient(command);
            var responseResource = new EditClientResponseResource(clientResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new EditClientResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{page}/limit/{limit}")
    @Operation(summary = "Get citizen")
    public ResponseEntity<GetClientsResponseResource> getClients(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        try {
            List<ClientProjection> clients = clientQueryService.getClients(page, limit);
            var response = new GetClientsResponseResource(clients, null);
            HttpHeaders headers = Pagination.createPaginationHeaders(clients.size(), page, limit);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetClientsResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

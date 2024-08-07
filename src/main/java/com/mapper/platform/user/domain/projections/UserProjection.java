package com.mapper.platform.user.domain.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserProjection {
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Column(length=100, unique=true)
    private String correo;

    @Column(length=20)
    private String clave;

    @Column(length=50)
    private String nombre;

    @Column(length=50)
    private String apellido;

    @Column(length=8, unique=true)
    private String dni;

    @Column(nullable = true)
    private String foto;

    @Column(nullable = true)
    private String direccion;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    public UserProjection() {
    }

    public UserProjection(Long id, String correo, String clave, String nombre, String apellido, String dni, String foto, String direccion, LocalDateTime createdAt) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.foto = foto;
        this.direccion = direccion;
        this.createdAt = createdAt;
    }

    public String getCorreo() {
        return this.correo;
    }
}

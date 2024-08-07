package com.mapper.platform.shared.domain.model.valueobjects;

public record DatosUser(String firstName, String lastName) {
    private static final int MAX_LENGTH = 100;

    public static Result<DatosUser, Notification> create(String correo, String clave) {
        Notification notification = new Notification();
        correo = correo.trim();
        clave = clave.trim();
        if (correo.isEmpty()) notification.addError("correo is required");
        if (clave.isEmpty()) notification.addError("clave is required");
        if (correo.length() > MAX_LENGTH)
            notification.addError("The maximum length of a correo is " + MAX_LENGTH + " characters including spaces");
        if (clave.length() > MAX_LENGTH)
            notification.addError("The maximum length of a clave is " + MAX_LENGTH + " characters including spaces");
        if (notification.hasErrors()) return Result.failure(notification);
        return Result.success(new DatosUser(correo, clave));
    }
}

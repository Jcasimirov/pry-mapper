package com.mapper.platform.user.domain.model.valueobjects;

public enum UserStatus {
    ACTIVE (1),
    INACTIVE(0);

    private int id;

    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

package com.femissiameri.api.model;

public enum EStatus {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    EStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

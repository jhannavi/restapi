package com.meena.server.model;

public enum SimStatusEnum {
    INACTIVE( "Sim Not activated"),
    ACTIVATED("Sim Activated."),
    NOTISSUED("Sim Not Issued to customer"),
    ERROR("Error Processing");

    private String description;

    SimStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

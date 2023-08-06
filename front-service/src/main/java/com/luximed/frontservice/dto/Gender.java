package com.luximed.frontservice.dto;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private final Integer description;

    Gender(Integer desc) {
        this.description = desc;
    }

    public Integer getDescription() {
        return description;
    }

    public static Gender valueOf(Integer gen) {
        switch (gen) {
            case 1:
                return MALE;
            case 2:
                return FEMALE;
            default:
                return null;
        }
    }
}

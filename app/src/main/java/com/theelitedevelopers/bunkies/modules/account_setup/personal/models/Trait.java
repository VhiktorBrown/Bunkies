package com.theelitedevelopers.bunkies.modules.account_setup.personal.models;

public class Trait {
    String id;
    String traitName;

    String roomType;
    String location;
    String rent;
    String deposit;
    String bathroom;
    String availability;
    String bills;
    String roomSize;
    Boolean smoke;
    Boolean vegan;
    Boolean student;
    Boolean pet;

    public Trait(String traitName) {
        this.traitName = traitName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }
}

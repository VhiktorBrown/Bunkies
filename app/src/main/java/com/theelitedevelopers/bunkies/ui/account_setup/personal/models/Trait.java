package com.theelitedevelopers.bunkies.ui.account_setup.personal.models;

public class Trait {
    String id;
    String traitName;

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

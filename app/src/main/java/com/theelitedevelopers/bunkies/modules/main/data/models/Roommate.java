package com.theelitedevelopers.bunkies.modules.main.data.models;

import com.google.gson.annotations.SerializedName;

public class Roommate {
    String email;
    String name;
    String uid;
    String dateOfBirth;
    @SerializedName("neighbourhood")
    String location;
    String password;
    String bio;
    String image;
    String gender;
    Boolean verified;
//    ArrayList<Trait> traits;
//    ArrayList<Interest> interests;
    @SerializedName("preferences_done")
    Boolean preferences_done;
    @SerializedName("personal_traits_done")
    Boolean personal_traits_done;
    @SerializedName("personal_interests_done")
    Boolean personal_interests_done;
    @SerializedName("personal_habits_done")
    Boolean personal_habits_done;
    @SerializedName("living_habits_done")
    Boolean living_habits_done;
    @SerializedName("living_choices_done")
    Boolean living_choices_done;
    @SerializedName("setup_profile_done")
    Boolean setup_profile_done;

    public Roommate(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roommate(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

//    public ArrayList<Trait> getTraits() {
//        return traits;
//    }
//
//    public void setTraits(ArrayList<Trait> traits) {
//        this.traits = traits;
//    }
//
//    public ArrayList<Interest> getInterests() {
//        return interests;
//    }
//
//    public void setInterests(ArrayList<Interest> interests) {
//        this.interests = interests;
//    }


    public Boolean getPreferences_done() {
        return preferences_done;
    }

    public void setPreferences_done(Boolean preferences_done) {
        this.preferences_done = preferences_done;
    }

    public Boolean getPersonal_traits_done() {
        return personal_traits_done;
    }

    public void setPersonal_traits_done(Boolean personal_traits_done) {
        this.personal_traits_done = personal_traits_done;
    }

    public Boolean getPersonal_interests_done() {
        return personal_interests_done;
    }

    public void setPersonal_interests_done(Boolean personal_interests_done) {
        this.personal_interests_done = personal_interests_done;
    }

    public Boolean getPersonal_habits_done() {
        return personal_habits_done;
    }

    public void setPersonal_habits_done(Boolean personal_habits_done) {
        this.personal_habits_done = personal_habits_done;
    }

    public Boolean getLiving_habits_done() {
        return living_habits_done;
    }

    public void setLiving_habits_done(Boolean living_habits_done) {
        this.living_habits_done = living_habits_done;
    }

    public Boolean getLiving_choices_done() {
        return living_choices_done;
    }

    public void setLiving_choices_done(Boolean living_choices_done) {
        this.living_choices_done = living_choices_done;
    }

    public Boolean getSetup_profile_done() {
        return setup_profile_done;
    }

    public void setSetup_profile_done(Boolean setup_profile_done) {
        this.setup_profile_done = setup_profile_done;
    }
}

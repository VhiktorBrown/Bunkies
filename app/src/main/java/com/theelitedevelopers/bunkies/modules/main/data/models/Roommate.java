package com.theelitedevelopers.bunkies.modules.main.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;
import com.google.gson.annotations.SerializedName;

public class Roommate implements Parcelable {
    String id;
    String email;
    String name;
    String uid;
    Timestamp date_of_birth;
    String location;
    String roomType;
    String password;
    String bio;
    String image;
    String gender;
    Boolean verified;
    String city;
    String streetAddress;
    String occupation;
    String adType;
    String rent;
    String deposit;
    boolean immediately;
    boolean billsIncluded;
    String budget;
    String neighbourhood;
    Timestamp date;
    String numberOfRooms;
//    List<String> traits;
//    List<String > interests;
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

    public Roommate(String image) {
        this.image = image;
    }

    protected Roommate(Parcel in) {
        id = in.readString();
        email = in.readString();
        name = in.readString();
        uid = in.readString();
        date_of_birth = in.readParcelable(Timestamp.class.getClassLoader());
        location = in.readString();
        roomType = in.readString();
        password = in.readString();
        bio = in.readString();
        image = in.readString();
        gender = in.readString();
        byte tmpVerified = in.readByte();
        verified = tmpVerified == 0 ? null : tmpVerified == 1;
        city = in.readString();
        streetAddress = in.readString();
        occupation = in.readString();
        adType = in.readString();
        rent = in.readString();
        deposit = in.readString();
        immediately = in.readByte() != 0;
        billsIncluded = in.readByte() != 0;
        budget = in.readString();
        neighbourhood = in.readString();
        date = in.readParcelable(Timestamp.class.getClassLoader());
        numberOfRooms = in.readString();
//        traits = in.createStringArrayList();
//        interests = in.createStringArrayList();
        byte tmpPreferences_done = in.readByte();
        preferences_done = tmpPreferences_done == 0 ? null : tmpPreferences_done == 1;
        byte tmpPersonal_traits_done = in.readByte();
        personal_traits_done = tmpPersonal_traits_done == 0 ? null : tmpPersonal_traits_done == 1;
        byte tmpPersonal_interests_done = in.readByte();
        personal_interests_done = tmpPersonal_interests_done == 0 ? null : tmpPersonal_interests_done == 1;
        byte tmpPersonal_habits_done = in.readByte();
        personal_habits_done = tmpPersonal_habits_done == 0 ? null : tmpPersonal_habits_done == 1;
        byte tmpLiving_habits_done = in.readByte();
        living_habits_done = tmpLiving_habits_done == 0 ? null : tmpLiving_habits_done == 1;
        byte tmpLiving_choices_done = in.readByte();
        living_choices_done = tmpLiving_choices_done == 0 ? null : tmpLiving_choices_done == 1;
        byte tmpSetup_profile_done = in.readByte();
        setup_profile_done = tmpSetup_profile_done == 0 ? null : tmpSetup_profile_done == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(uid);
        dest.writeParcelable(date_of_birth, flags);
        dest.writeString(location);
        dest.writeString(roomType);
        dest.writeString(password);
        dest.writeString(bio);
        dest.writeString(image);
        dest.writeString(gender);
        dest.writeByte((byte) (verified == null ? 0 : verified ? 1 : 2));
        dest.writeString(city);
        dest.writeString(streetAddress);
        dest.writeString(occupation);
        dest.writeString(adType);
        dest.writeString(rent);
        dest.writeString(deposit);
        dest.writeByte((byte) (immediately ? 1 : 0));
        dest.writeByte((byte) (billsIncluded ? 1 : 0));
        dest.writeString(budget);
        dest.writeString(neighbourhood);
        dest.writeParcelable(date, flags);
        dest.writeString(numberOfRooms);
//        dest.writeStringList(traits);
//        dest.writeStringList(interests);
        dest.writeByte((byte) (preferences_done == null ? 0 : preferences_done ? 1 : 2));
        dest.writeByte((byte) (personal_traits_done == null ? 0 : personal_traits_done ? 1 : 2));
        dest.writeByte((byte) (personal_interests_done == null ? 0 : personal_interests_done ? 1 : 2));
        dest.writeByte((byte) (personal_habits_done == null ? 0 : personal_habits_done ? 1 : 2));
        dest.writeByte((byte) (living_habits_done == null ? 0 : living_habits_done ? 1 : 2));
        dest.writeByte((byte) (living_choices_done == null ? 0 : living_choices_done ? 1 : 2));
        dest.writeByte((byte) (setup_profile_done == null ? 0 : setup_profile_done ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Roommate> CREATOR = new Creator<Roommate>() {
        @Override
        public Roommate createFromParcel(Parcel in) {
            return new Roommate(in);
        }

        @Override
        public Roommate[] newArray(int size) {
            return new Roommate[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Timestamp date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public boolean isImmediately() {
        return immediately;
    }

    public void setImmediately(boolean immediately) {
        this.immediately = immediately;
    }

    public boolean isBillsIncluded() {
        return billsIncluded;
    }

    public void setBillsIncluded(boolean billsIncluded) {
        this.billsIncluded = billsIncluded;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

//    public List<String> getTraits() {
//        return traits;
//    }
//
//    public void setTraits(List<String> traits) {
//        this.traits = traits;
//    }
//
//    public List<String> getInterests() {
//        return interests;
//    }
//
//    public void setInterests(List<String> interests) {
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

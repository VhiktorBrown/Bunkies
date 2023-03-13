package com.theelitedevelopers.bunkies.modules.main.listing.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;

import java.util.List;

public class RoommateListing implements Parcelable {
    String city;
    String streetAddress;
    String bio;
    String adType;
    String rentPerYear;
    String deposit;
    boolean immediately;
    boolean billsIncluded;
    String budget;
    String neighbourhood;
    Timestamp date;
    String numberOfRooms;
    List<String> suitableFor;
    List<String> roomAttributes;
    String uid;

    public RoommateListing(){}

    protected RoommateListing(Parcel in) {
        city = in.readString();
        streetAddress = in.readString();
        bio = in.readString();
        adType = in.readString();
        rentPerYear = in.readString();
        deposit = in.readString();
        immediately = in.readByte() != 0;
        billsIncluded = in.readByte() != 0;
        budget = in.readString();
        neighbourhood = in.readString();
        date = in.readParcelable(Timestamp.class.getClassLoader());
        numberOfRooms = in.readString();
        suitableFor = in.createStringArrayList();
        roomAttributes = in.createStringArrayList();
        uid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(streetAddress);
        dest.writeString(bio);
        dest.writeString(adType);
        dest.writeString(rentPerYear);
        dest.writeString(deposit);
        dest.writeByte((byte) (immediately ? 1 : 0));
        dest.writeByte((byte) (billsIncluded ? 1 : 0));
        dest.writeString(budget);
        dest.writeString(neighbourhood);
        dest.writeParcelable(date, flags);
        dest.writeString(numberOfRooms);
        dest.writeStringList(suitableFor);
        dest.writeStringList(roomAttributes);
        dest.writeString(uid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RoommateListing> CREATOR = new Creator<RoommateListing>() {
        @Override
        public RoommateListing createFromParcel(Parcel in) {
            return new RoommateListing(in);
        }

        @Override
        public RoommateListing[] newArray(int size) {
            return new RoommateListing[size];
        }
    };

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getRentPerYear() {
        return rentPerYear;
    }

    public void setRentPerYear(String rentPerYear) {
        this.rentPerYear = rentPerYear;
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

    public List<String> getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(List<String> suitableFor) {
        this.suitableFor = suitableFor;
    }

    public List<String> getRoomAttributes() {
        return roomAttributes;
    }

    public void setRoomAttributes(List<String> roomAttributes) {
        this.roomAttributes = roomAttributes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

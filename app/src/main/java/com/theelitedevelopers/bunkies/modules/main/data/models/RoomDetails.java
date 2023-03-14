package com.theelitedevelopers.bunkies.modules.main.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

public class RoomDetails implements Parcelable {
    String city;
    String streetAddress;
    String bio;
    String adType;
    String rent;
    String deposit;
    boolean immediately;
    boolean billsIncluded;
    String budget;
    String neighbourhood;
    Timestamp date;
    String numberOfRooms;
//    String[] suitableFor;
//    String[] roomAttributes;
    String uid;
    String image;
    String roomType;

    public RoomDetails(){
    }
    public RoomDetails(String image) {
        this.image = image;
    }

    protected RoomDetails(Parcel in) {
        city = in.readString();
        streetAddress = in.readString();
        bio = in.readString();
        adType = in.readString();
        rent = in.readString();
        deposit = in.readString();
        immediately = in.readByte() != 0;
        billsIncluded = in.readByte() != 0;
        budget = in.readString();
        neighbourhood = in.readString();
        date = in.readParcelable(Timestamp.class.getClassLoader());
        numberOfRooms = in.readString();
//        suitableFor = in.createStringArray();
//        roomAttributes = in.createStringArray();
        uid = in.readString();
        image = in.readString();
        roomType = in.readString();
    }

    public static final Creator<RoomDetails> CREATOR = new Creator<RoomDetails>() {
        @Override
        public RoomDetails createFromParcel(Parcel in) {
            return new RoomDetails(in);
        }

        @Override
        public RoomDetails[] newArray(int size) {
            return new RoomDetails[size];
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

//    public String[] getSuitableFor() {
//        return suitableFor;
//    }
//
//    public void setSuitableFor(String[] suitableFor) {
//        this.suitableFor = suitableFor;
//    }
//
//    public String[] getRoomAttributes() {
//        return roomAttributes;
//    }
//
//    public void setRoomAttributes(String[] roomAttributes) {
//        this.roomAttributes = roomAttributes;
//    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(city);
        parcel.writeString(streetAddress);
        parcel.writeString(bio);
        parcel.writeString(adType);
        parcel.writeString(rent);
        parcel.writeString(deposit);
        parcel.writeByte((byte) (immediately ? 1 : 0));
        parcel.writeByte((byte) (billsIncluded ? 1 : 0));
        parcel.writeString(budget);
        parcel.writeString(neighbourhood);
        parcel.writeParcelable(date, i);
        parcel.writeString(numberOfRooms);
//        parcel.writeStringArray(suitableFor);
//        parcel.writeStringArray(roomAttributes);
        parcel.writeString(uid);
        parcel.writeString(image);
        parcel.writeString(roomType);
    }
}

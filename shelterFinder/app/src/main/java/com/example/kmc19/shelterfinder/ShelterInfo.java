package com.example.kmc19.shelterfinder;

import android.os.Parcel;
import android.os.Parcelable;


class ShelterInfo implements Parcelable {
    private String shelterName;
    private String capacity;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String specialNotes;
    private String phone;

    public ShelterInfo() {
        shelterName = "";
        capacity = "";
        restrictions = "";
        longitude = 0.0;
        latitude = 0.0;
        address = "";
        specialNotes = "";
        phone = "";
    }

    private ShelterInfo (Parcel in) {
        shelterName = in.readString();
        capacity = in.readString();
        restrictions = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        address = in.readString();
        specialNotes = in.readString();
        phone = in.readString();
    }

    public static final Creator<ShelterInfo> CREATOR = new Creator<ShelterInfo>() {
        @Override
        public ShelterInfo createFromParcel(Parcel in) {
            return new ShelterInfo(in);
        }

        @Override
        public ShelterInfo[] newArray(int size) {
            return new ShelterInfo[size];
        }
    };

    public String getShelterName() {
        return shelterName;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public String getPhone() {
        return phone;
    }
    @Override
    public String toString() {
        return shelterName  + " \n" + "Current Capacity: " +
                capacity;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public void setCapacity(String capacity) {
        if (capacity == null) {
            throw new IllegalArgumentException("The capacity cannot be null.");
        }
        if ("".equals(capacity)) {
            this.capacity = "N/A";
        } else {
            this.capacity = capacity;
        }
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public void setLongitude(String longitude) {
        this.longitude = Double.parseDouble(longitude);
    }

    public void setLatitude(String latitude) {
        this.latitude = Double.parseDouble(latitude);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSpecialNotes(String specialNotes) {
        if (specialNotes == null) {
            throw new IllegalArgumentException("Special notes can not be null.");
        } else if (specialNotes.equals("")) {
            this.specialNotes = "N/A";
        } else {
            this.specialNotes = specialNotes;
        }
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shelterName);
        dest.writeString(capacity);
        dest.writeString(restrictions);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(address);
        dest.writeString(specialNotes);
        dest.writeString(phone);
    }
}

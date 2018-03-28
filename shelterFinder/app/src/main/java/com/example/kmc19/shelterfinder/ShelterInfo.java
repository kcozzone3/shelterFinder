package com.example.kmc19.shelterfinder;

/**
 * Created by Jacksonfan on 2/24/18.
 */

class ShelterInfo {
    private String shelterName;
    private String capacity;

    public String getShelterName() {
        return shelterName;
    }

    public String getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return
                shelterName  + " \n" + "Current Capacity: " +
                capacity;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}

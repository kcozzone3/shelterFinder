package com.example.kmc19.shelterfinder;

/**
 * An enum representing the different types of users that will be utilizing the app. Different types
 * have different permissions within the app.
 */
public enum UserType {
    General ("GR"),
    ADMIN("ADMIN"),
    Shelter("Shelter");

    /** full representation of the UserType String **/
    private final String userType;


    /**
     * Constructor for the enumeration
     *
     * @param userType   full name of the course
     */
    UserType(String userType){
        this.userType = userType;
    }
    /**
     *
     * @return   the full class name
     */
    public String getUserType() {return userType; }


}
package com.example.kmc19.shelterfinder;


public enum UserType {
    General ("GR"),
    ADMIN("ADMIN"),
    Shelter("Shelter");

    /** full representation of the ClassStanding String **/
    private final String userType;

    /**
     * Constructor for the enumeration
     *
     * @param userType   full name of the userType
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
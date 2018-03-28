package com.example.kmc19.shelterfinder;

/**
 * Created by Jacksonfan on 2/21/18.
 */

public enum UserType {
    General ("GR"),
    ADMIN("ADMIN"),
    Shelter("Shelter");

    /** full representation of the ClassStanding String **/
    private String userType;

    /** full representation of the ClassStanding Code **/


    /**
     * Constructor for the enumeration
     *
     * @param usertype   full name of the course
     */
    UserType(String usertype){
        userType = usertype;
    }
    /**
     *
     * @return   the full class name
     */
    public String getUserType() {return userType; }


}
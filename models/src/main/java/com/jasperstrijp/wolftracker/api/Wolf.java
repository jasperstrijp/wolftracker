package com.jasperstrijp.wolftracker.api;

import com.jasperstrijp.wolftracker.api.enums.Gender;

import java.util.Date;

public class Wolf {
    public long id;
    public String firstName;
    public String middleName;
    public String lastName;
    public Gender gender;
    public Date birthdate;
    public String location;

    public Wolf() {

    }

    public Wolf(String firstName, String middleName, String lastName, Gender gender, Date birthdate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthdate = birthdate;
    }
}

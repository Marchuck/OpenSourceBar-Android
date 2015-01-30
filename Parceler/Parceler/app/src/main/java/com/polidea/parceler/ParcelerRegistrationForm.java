package com.polidea.parceler;

import org.parceler.Parcel;

@Parcel
public class ParcelerRegistrationForm {

    int age;

    boolean isHuman;

    String firstName;

    String lastName;

    public ParcelerRegistrationForm() {
    }

    @Override
    public String toString() {
        return "ParcelerRegistrationForm{" +
                "age=" + age +
                ", isHuman=" + isHuman +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

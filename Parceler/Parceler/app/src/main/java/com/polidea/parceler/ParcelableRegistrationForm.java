package com.polidea.parceler;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableRegistrationForm implements Parcelable {

    int age;

    boolean isHuman;

    String firstName;

    String lastName;

    public ParcelableRegistrationForm() {
    }

    private ParcelableRegistrationForm(Parcel in) {
        age = in.readInt();
        isHuman = in.readByte() != 0;
        firstName = in.readString();
        lastName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableRegistrationForm> CREATOR = new Creator<ParcelableRegistrationForm>() {
        @Override
        public ParcelableRegistrationForm createFromParcel(Parcel source) {
            return new ParcelableRegistrationForm(source);
        }

        @Override
        public ParcelableRegistrationForm[] newArray(int size) {
            return new ParcelableRegistrationForm[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeByte((byte) (isHuman ? 1 : 0));
        dest.writeString(firstName);
        dest.writeString(lastName);
    }

    @Override
    public String toString() {
        return "ParcelableRegistrationForm{" +
                "age=" + age +
                ", isHuman=" + isHuman +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

package com.example.ief.mastermind;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ief on 23/10/2017.
 * Voor testen van Parcelable, deze klasse heeft geen verdere waarde
 */

public class ParcelablePerson implements Parcelable {
    private Person person;

    public ParcelablePerson(Person person) {
        this.person = person;
    }

    protected ParcelablePerson(Parcel in) {
    }

    public Person getPerson() {
        return person;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // call dest.write... methods
    }

    public static final Creator<ParcelablePerson> CREATOR = new Creator<ParcelablePerson>() {
        @Override
        public ParcelablePerson createFromParcel(Parcel in) {
            return null;
        }

        @Override
        public ParcelablePerson[] newArray(int size) {
            return null;
        }
    };


}

package com.example.ief.mastermind;

import  android.os.Parcelable;
import android.os.Parcel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ief on 23/10/2017.
 */
public class ParcelablePersonTest {

    @Test
    public void testPersonTakesRoundTripThroughParcel() throws Exception {
        Person testPerson = new Person();
        ParcelablePerson testObject = new ParcelablePerson(testPerson);
        Parcel parcel = Parcel.obtain();
        testObject.writeToParcel(parcel, 0);
        // done writing, now reset parcel for reading
        parcel.setDataPosition(0);
        //finis riound trip
        ParcelablePerson createFromParcel = ParcelablePerson.CREATOR.createFromParcel(parcel);
        assertEquals(testPerson, createFromParcel.getPerson());

    }
}
package com.example.ief.mastermind;

import android.os.Parcel;

import com.example.Kleur;
import com.example.Pion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ief on 23/10/2017.
 */

public class ParcelablePionTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {
        ParcelablePion p = new ParcelablePion(){{setKleur(Kleur.zwart);}};
        Parcel parcel = Parcel.obtain();
        p.writeToParcel(parcel,0);
        parcel.setDataPosition(0);
        ParcelablePion uitParcel = ParcelablePion.CREATOR.createFromParcel(parcel);
        assertEquals(p,uitParcel);
    }

    @Test
    public void setKleur() throws Exception {
        Kleur kleur = Kleur.zwart;
        ParcelablePion parcelablePion = new ParcelablePion(){{setKleur(Kleur.zwart);}};
        assertEquals(kleur, parcelablePion.getKleur());
    }

    @Test
    public void getKleur() throws Exception {
        ParcelablePion p = new ParcelablePion(){{setKleur(Kleur.zwart);}};
        assertEquals(Kleur.zwart, p.getKleur());
    }



}
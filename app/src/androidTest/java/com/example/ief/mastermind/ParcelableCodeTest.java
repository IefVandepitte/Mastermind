package com.example.ief.mastermind;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.Kleur;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ief on 23/10/2017.
 */

public class ParcelableCodeTest {
    @Test
    public void writeToParcel() throws Exception {

        ParcelablePion p1 = new ParcelablePion(){{kleur = Kleur.zwart;}};
        ParcelablePion p2 = new ParcelablePion(){{kleur = Kleur.rood;}};
        ParcelablePion p3 = new ParcelablePion(){{kleur = Kleur.geel;}};
        ParcelablePion p4 = new ParcelablePion(){{kleur = Kleur.fout;}};

        ParcelableCode code = new ParcelableCode(p1, p2, p3, p4);
        Parcel parcel = Parcel.obtain();
        code.writeToParcel(parcel,0);
        parcel.setDataPosition(0);
        ParcelableCode codeUitParcel = ParcelableCode.CREATOR.createFromParcel(parcel);

        Assert.assertEquals(code, codeUitParcel);
    }
}

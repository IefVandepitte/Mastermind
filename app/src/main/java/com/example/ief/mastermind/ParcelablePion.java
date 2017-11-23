package com.example.ief.mastermind;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.Kleur;
import com.example.Pion;

/**
 * Created by ief on 18/10/2017.
 */

class ParcelablePion  extends Pion implements Parcelable{

    protected ParcelablePion(Parcel in) {
       kleur = Kleur.valueOf(in.readString());
    }

//    public ParcelablePion(Kleur kleur) {
//        super.kleur = kleur;
//    }

    public static final Creator<ParcelablePion> CREATOR = new Creator<ParcelablePion>() {
        @Override
        public ParcelablePion createFromParcel(Parcel in) {
            return new ParcelablePion(in);
        }

        @Override
        public ParcelablePion[] newArray(int size) {
            return new ParcelablePion[size];
        }
    };

    public ParcelablePion() {
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(this.kleur.name());
    }
}

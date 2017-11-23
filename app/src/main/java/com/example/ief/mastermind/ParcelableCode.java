package com.example.ief.mastermind;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.Code;
import com.example.Pion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ief on 18/10/2017.
 */

class ParcelableCode extends Code implements Parcelable{
    public ParcelablePion[] getParcelablePionnen() {
        return ParcelablePionnen;
    }

    private ParcelablePion[] ParcelablePionnen = new ParcelablePion[4];


    public ParcelableCode(final List<Pion> _pionnen) {
        super(_pionnen);
        for ( int i = 0; i < _pionnen.size(); i++){
            final int innerI = i;
            ParcelablePionnen[i] = new ParcelablePion(/*pionnen.get(i).getKleur()*/){{setKleur(_pionnen.get(innerI).getKleur());}};
        }
    }

    public ParcelableCode(Pion p1, Pion p2, Pion p3, Pion p4) {

        super(p1, p2, p3, p4);

        ParcelablePion pp1 = new ParcelablePion();
        pp1.setKleur(p1.getKleur());
        ParcelablePion pp2 = new ParcelablePion();
        pp2.setKleur(p2.getKleur());
        ParcelablePion pp3 = new ParcelablePion();
        pp3.setKleur(p3.getKleur());
        ParcelablePion pp4 = new ParcelablePion();
        pp4.setKleur(p4.getKleur());

        ParcelablePionnen[0] = pp1;
        ParcelablePionnen[1] = pp2;
        ParcelablePionnen[2] = pp3;
        ParcelablePionnen[3] = pp4;


    }

    protected ParcelableCode(Parcel in) {

        super(  new Pion(),
                new Pion(),
                new Pion(),
                new Pion());

//        ParcelablePionnen[0] = in.readParcelable(ParcelablePion.class.getClassLoader());
//        ParcelablePionnen[1] = in.readParcelable(ParcelablePion.class.getClassLoader());
//        ParcelablePionnen[2] = in.readParcelable(ParcelablePion.class.getClassLoader());
//        ParcelablePionnen[3] = in.readParcelable(ParcelablePion.class.getClassLoader());


        for (int i = 0; i< super.size(); i++){
            ParcelablePion p = in.readParcelable(ParcelablePion.class.getClassLoader());
//            super._pionnen.get(i).setKleur(ParcelablePionnen[i].getKleur());
            if (p == null) continue;
            super._pionnen.get(i).setKleur(p.getKleur());
        }
        for (int i = super.size()-1; i>0; i--){
            if (super._pionnen.get(i).getKleur() == null){
                super._pionnen.remove(i);
            }
        }



    }

    public static final Creator<ParcelableCode> CREATOR = new Creator<ParcelableCode>() {
        @Override
        public ParcelableCode createFromParcel(Parcel in) {
            return new ParcelableCode(in);
        }

        @Override
        public ParcelableCode[] newArray(int size) {
            return new ParcelableCode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //parcel.writeParcelableArray(ParcelablePionnen, 0);
        ParcelablePion [] pionnen = getParcelablePionnen();
       for (ParcelablePion p : pionnen){
           parcel.writeParcelable(p, 0);
       }
    }
}

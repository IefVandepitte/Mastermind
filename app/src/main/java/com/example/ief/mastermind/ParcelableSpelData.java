package com.example.ief.mastermind;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.Code;
import com.example.SpelData;

import java.util.List;

/**
 * Created by ief on 17/10/2017.
 */

public class ParcelableSpelData extends SpelData implements Parcelable{

//    protected int beurtTeller;
//    protected Code teZoekencode;
//    protected List<Code> gokken;
//    protected List<Code> resultaten;
    public static final String SPEL_DATA ="com.example.ief.mastermind.SPEL_DATA";
    public static final String SPEL_DATA_CODE ="com.example.ief.mastermind.SPEL_DATA_CODE";
    public static final String SPEL_DATA_GOKKEN ="com.example.ief.mastermind.SPEL_DATA_GOKKEN";
    public static final String SPEL_DATA_RESULTATEN ="com.example.ief.mastermind.SPEL_DATA_RESULTATEN";
    public static final String SPEL_DATA_BEURTTELLER ="com.example.ief.mastermind.SPEL_DATA_BEURTTELLER";


    private ParcelableCode parcelableTeZoekenCode = new ParcelableCode(new Code());
    private Parcelable[] parcelableGokken = new Parcelable[10];
    private Parcelable[] parcelableResultaten = new Parcelable[10];

   public ParcelableSpelData(Code tezoekenCode){
       super(tezoekenCode);
       parcelableTeZoekenCode = new ParcelableCode(tezoekenCode);

   }

    public ParcelableSpelData( int beurtTeller ,Code teZoekencode, List<Code> gokken,  List<Code> resultaten){
        super(beurtTeller, teZoekencode, gokken,resultaten);
//         super.beurtTeller = beurtTeller;
//        super.teZoekencode = teZoekencode;
//        super.gokken = gokken;
//        super.resultaten = resultaten;
        parcelableTeZoekenCode = new ParcelableCode(teZoekencode);
        for(int i = 0 ; i < gokken.size(); i++){
        parcelableGokken[i] = new ParcelableCode(gokken.get(i));
        }
        for(int i = 0 ; i < resultaten.size(); i++){
            parcelableResultaten[i] = new ParcelableCode(resultaten.get(i));
        }
    }
    protected ParcelableSpelData(Parcel in) {

//        ParcelableSpelData(in.readInt(),
//                in.readParcelable(ParcelableCode.class.getClassLoader(),
//                        in.readParcelableArray(Parcelable[].class.getClassLoader(),
//                                in.readParcelableArray(Parcelable[].class.getClassLoader()      ))));
        beurtTeller = in.readInt();
        parcelableTeZoekenCode = in.readParcelable(ParcelableCode.class.getClassLoader());
        //Parcelable [] parcelableGoks = in.readParcelableArray(ParcelableCode.class.getClassLoader());
        parcelableGokken = in.readParcelableArray(ParcelableCode.class.getClassLoader());
        parcelableResultaten = in.readParcelableArray(ParcelableCode.class.getClassLoader());
        System.out.println();
    }
    public ParcelableCode getParcelableTeZoekenCode() {
        return parcelableTeZoekenCode;
    }

    public void setParcelableTeZoekenCode(ParcelableCode parcelableTeZoekenCode) {
        this.parcelableTeZoekenCode = parcelableTeZoekenCode;
    }

    public Parcelable[] getParcelableGokken() {
        return parcelableGokken;
    }

    public void setParcelableGok(int i, ParcelableCode c) {
        this.parcelableGokken[i] = c;
    }
//    public void addGok(int beurtTeller, ParcelableCode code){
//        parcelableGokken[beurtTeller -1]= code;
//    }
//
//    public void addResultaat(int beurtTeller, Parcelable code){
//        parcelableResultaten[beurtTeller-1] = code;
//    }
    public Parcelable[] getParcelableResultaten() {
        return parcelableResultaten;
    }

    public void setParcelableResultaat(int i, ParcelableCode c) {
        this.parcelableResultaten[i] = c;
    }
    public void setBeurtTeller(int i){
        super.setBeurtTeller(i);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(beurtTeller);
        dest.writeParcelable(parcelableTeZoekenCode, 0);
        dest.writeParcelableArray(parcelableGokken, 0);
        dest.writeParcelableArray(parcelableResultaten, 0);
    }
    public static final Creator<ParcelableSpelData> CREATOR = new Creator<ParcelableSpelData>() {
        @Override
        public ParcelableSpelData createFromParcel(Parcel in) {
            return new ParcelableSpelData(in);
        }

        @Override
        public ParcelableSpelData[] newArray(int size) {
            return new ParcelableSpelData[size];
        }
    };
}

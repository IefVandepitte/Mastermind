package com.example.ief.mastermind;

import android.os.Parcel;

import com.example.Code;
import com.example.Kleur;
import com.example.Pion;
import com.example.Spel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ief on 24/10/2017.
 */
public class ParcelableSpelDataTest {
    private Spel s;
    private ParcelableCode parcelableCode;
    private ParcelableCode parcelableGok;
    private ParcelableCode parcelableResultaat;

    private ParcelableSpelData data;
    private int beurtTeller;

    private ArrayList<Code> gokken;
    private ArrayList<Code> resultaten;
    private ParcelablePion zwartePion= new ParcelablePion(){{setKleur(Kleur.zwart);}};
    private ParcelablePion rodePion = new ParcelablePion(){{setKleur(Kleur.rood);}};
    private ParcelablePion gelePion = new ParcelablePion(){{setKleur(Kleur.geel);}};
    private ParcelablePion foutePion = new ParcelablePion(){{setKleur(Kleur.fout);}};


    @Before
    public void setUp() throws Exception {


        s = new Spel();

        parcelableCode = new ParcelableCode(zwartePion, rodePion, gelePion, foutePion);
        s.setCode(parcelableCode);
//
//        parcelableGok = new ParcelableCode(p2,p3,p1,p4);
//
//        ArrayList<Pion> resultaat =s.doeGok(parcelableGok.get(0), parcelableGok.get(1), parcelableGok.get(2), parcelableGok.get(3));
//        parcelableResultaat =new ParcelableCode(resultaat.get(0), resultaat.get(1), resultaat.get(2), resultaat.get(3));
//        beurtTeller = 1;
//
//        gokken = new ArrayList<>();
//        gokken.add(parcelableGok);
//        resultaten = new ArrayList<>();
//        resultaten.add(parcelableResultaat);
       data = new ParcelableSpelData(s.getCode());
//        data = new ParcelableSpelData(s.getRondeTeller(), parcelableCode, gokken, resultaten);

    }

    @Test
    public void getParcelableTeZoekenCode() throws Exception {
        Code spelCode = s.getCode();
        data = new ParcelableSpelData(spelCode);
        assertEquals(spelCode, data.getParcelableTeZoekenCode());
    }
    @Test
    public void getBeurtTeller() throws Exception {
        int spelRondeTeller = s.getRondeTeller();
        data.setBeurtTeller(spelRondeTeller);
        assertEquals(spelRondeTeller, data.getBeurtTeller());
    }
    @Test
    public void getParcelableGokken() throws Exception {
        Code gok = new Code(zwartePion, zwartePion, zwartePion, zwartePion);
        ParcelableCode parcelableCode = new ParcelableCode(gok);

        data.setParcelableGok(s.getRondeTeller(), parcelableCode);
        assertEquals(gok, data.getParcelableGokken()[s.getRondeTeller()]);
    }

    @Test
    public void getParcelableResultaten() throws Exception {
        Code gok = new Code(zwartePion, zwartePion, zwartePion, zwartePion);
        Code resultaat = new Code(s.doeGok(gok.get(0),gok.get(1),gok.get(2),gok.get(3)));

        ParcelableCode parcelableGok = new ParcelableCode(gok);
        ParcelableCode parcelableResultaat = new ParcelableCode(resultaat);

        data.setParcelableGok(s.getRondeTeller()-1, parcelableGok);
        assertEquals(gok, data.getParcelableGokken()[s.getRondeTeller()-1]);

        data.setParcelableResultaat(s.getRondeTeller()-1, parcelableResultaat);
        assertEquals(resultaat, data.getParcelableResultaten()[s.getRondeTeller()-1]);
    }


    @Test
    public void writeToParcel() throws Exception {

        Code gok = new Code(zwartePion, zwartePion, zwartePion, zwartePion);
        Code resultaat = new Code(s.doeGok(gok.get(0),gok.get(1),gok.get(2),gok.get(3)));

        ParcelableCode parcelableGok = new ParcelableCode(gok);
        ParcelableCode parcelableResultaat = new ParcelableCode(resultaat);

        data.setTeZoekencode(s.getCode());
        data.setBeurtTeller(s.getRondeTeller());
        data.setParcelableGok(s.getRondeTeller()-1, parcelableGok);
        assertEquals(gok, data.getParcelableGokken()[s.getRondeTeller()-1]);

        data.setParcelableResultaat(s.getRondeTeller()-1, parcelableResultaat);
        assertEquals(resultaat, data.getParcelableResultaten()[s.getRondeTeller()-1]);


        Parcel parcel = Parcel.obtain();
        data.writeToParcel(parcel,0);
        parcel.setDataPosition(0);

        data = ParcelableSpelData.CREATOR.createFromParcel(parcel);
        assertEquals(s.getRondeTeller(), data.getBeurtTeller());
        assertEquals(s.getCode(), data.getParcelableTeZoekenCode());
        assertEquals(gok, data.getParcelableGokken()[s.getRondeTeller()-1]);
        assertEquals(resultaat, data.getParcelableResultaten()[s.getRondeTeller()-1]);

        List<Code> parcelableGokken = new ArrayList<>();
        parcelableGokken.add(parcelableGok);

        List<Code> parcelableResultaten = new ArrayList<>();
        parcelableResultaten.add(parcelableResultaat);

        data = new ParcelableSpelData(s.getRondeTeller(), s.getCode(), parcelableGokken, parcelableResultaten );
        assertEquals(s.getRondeTeller(), data.getBeurtTeller());
        assertEquals(s.getCode(), data.getParcelableTeZoekenCode());
        assertEquals(gok, data.getParcelableGokken()[s.getRondeTeller()-1]);
        assertEquals(resultaat, data.getParcelableResultaten()[s.getRondeTeller()-1]);
    }

    @Test
    public void fullOptionConstructor() throws Exception {
        Code gok = new Code(zwartePion, zwartePion, zwartePion, zwartePion);
        Code resultaat = new Code(s.doeGok(gok.get(0),gok.get(1),gok.get(2),gok.get(3)));

        ParcelableCode parcelableGok = new ParcelableCode(gok);
        ParcelableCode parcelableResultaat = new ParcelableCode(resultaat);

        List<Code> parcelableGokken = new ArrayList<>();
        parcelableGokken.add(parcelableGok);

        List<Code> parcelableResultaten = new ArrayList<>();
        parcelableResultaten.add(parcelableResultaat);

        data = new ParcelableSpelData(s.getRondeTeller(), s.getCode(), parcelableGokken, parcelableResultaten );

        Parcel parcel = Parcel.obtain();
        data.writeToParcel(parcel,0);
        parcel.setDataPosition(0);

        data = ParcelableSpelData.CREATOR.createFromParcel(parcel);
        assertEquals(s.getRondeTeller(), data.getBeurtTeller());
        assertEquals(s.getCode(), data.getParcelableTeZoekenCode());
        assertEquals(gok, data.getParcelableGokken()[s.getRondeTeller()-1]);
        assertEquals(resultaat, data.getParcelableResultaten()[s.getRondeTeller()-1]);
    }
}
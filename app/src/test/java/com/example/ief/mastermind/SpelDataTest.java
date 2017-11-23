package com.example.ief.mastermind;

import com.example.Code;
import com.example.Kleur;
import com.example.Pion;
import com.example.Spel;
import com.example.SpelData;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ief on 24/10/2017.
 */

public class SpelDataTest {

    private Spel s;
    private Code teBreken;

    @Before
    public void SetUp(){
        s = new Spel();
        teBreken = new Code(
                new Pion(){{setKleur(Kleur.zwart);}},
                new Pion(){{setKleur(Kleur.rood);}},
                new Pion(){{setKleur(Kleur.geel);}},
                new Pion(){{setKleur(Kleur.fout);}});
        s.setCode(teBreken);
    }

    @Test
    public void getSpelRondeTeller() throws Exception {
        assertEquals(0, s.getRondeTeller());
        s.doeGok(teBreken.get(0), teBreken.get(1), teBreken.get(2), teBreken.get(3));
        assertEquals(1, s.getRondeTeller());
    }

    @Test
    public void Speldata() throws Exception {
        SpelData data = new SpelData();
        data.setTeZoekencode(teBreken);
        assertEquals(teBreken, data.getTeZoekencode());

        data.setBeurtTeller(s.getRondeTeller());
        assertEquals(s.getRondeTeller(), data.getBeurtTeller());

        ArrayList<Code> gokken = new ArrayList<>();
        gokken.add(new Code(teBreken));
        data.setGokken(gokken);
        assertEquals(teBreken, data.getGokken().get(0));


        ArrayList<Code> resultaten = new ArrayList<>();
        Code resultaat = new Code(s.doeGok(teBreken.get(0), teBreken.get(1), teBreken.get(2), teBreken.get(3)));
        resultaten.add(new Code(s.doeGok(teBreken.get(0), teBreken.get(1), teBreken.get(2), teBreken.get(3))));
        data.setResultaten(resultaten);
        Code dateResultaat = data.getResultaten().get(0);
        assertEquals(resultaat, dateResultaat);
    }
    @Test
    public void fullOptionConstructor() throws Exception{

        SpelData data = new SpelData();

        data.setBeurtTeller(s.getRondeTeller());
        assertEquals(s.getRondeTeller(), data.getBeurtTeller());

        data.setTeZoekencode(teBreken);
        assertEquals(teBreken, data.getTeZoekencode());

        ArrayList<Code> gokken = new ArrayList<>();
        gokken.add(new Code(teBreken));
        data.setGokken(gokken);
        assertEquals(teBreken, data.getGokken().get(0));


        ArrayList<Code> resultaten = new ArrayList<>();
        Code resultaat = new Code(s.doeGok(teBreken.get(0), teBreken.get(1), teBreken.get(2), teBreken.get(3)));
        resultaten.add(new Code(s.doeGok(teBreken.get(0), teBreken.get(1), teBreken.get(2), teBreken.get(3))));
        data.setResultaten(resultaten);
        Code dateResultaat = data.getResultaten().get(0);
        assertEquals(resultaat, dateResultaat);

        SpelData test = new SpelData(s.getRondeTeller(), teBreken ,gokken , resultaten);
        assertEquals(s.getRondeTeller(), test.getBeurtTeller());
        assertEquals(teBreken, test.getTeZoekencode());
        assertEquals(teBreken, test.getGokken().get(0));
        assertEquals(resultaat, test.getResultaten().get(0));
    }
}




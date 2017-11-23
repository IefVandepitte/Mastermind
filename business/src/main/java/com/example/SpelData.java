package com.example;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ief on 17/10/2017.
 */

public class SpelData extends AbstractList<Code> {

    protected List<Code> gokken = new ArrayList<>();
    protected List<Code> resultaten = new ArrayList<>();
    protected int beurtTeller;
    protected Code teZoekencode;

    @Override
    public String toString() {
        return "beurt:" +getBeurtTeller() + "code:" +teZoekencode +
                "gokken:" + getGokken().size() +
                "resultaten:" +getResultaten().size();
    }

    public SpelData(int beurtTeller, Code teZoekencode, List<Code> gokken, List<Code> resultaten) {
        this.gokken = gokken;
        this.resultaten = resultaten;
        this.beurtTeller = beurtTeller;
        this.teZoekencode = teZoekencode;
    }

    public SpelData(Code code) {
        this.teZoekencode = code;
    }

    public SpelData() {
    }

    public int getBeurtTeller() {
        return beurtTeller;
    }

    public void setBeurtTeller(int beurtTeller) {
        this.beurtTeller = beurtTeller;
    }


    public Code getTeZoekencode() {
        return teZoekencode;
    }

    public void setTeZoekencode(Code teZoekencode) {
        this.teZoekencode = teZoekencode;
    }

    public List<Code> getGokken() {
        return gokken;
    }
    public void setGokken(List<Code> gokken) {
        this.gokken = gokken;
    }

    public List<Code> getResultaten() {
        return resultaten;
    }

    public void setResultaten(List<Code> resultaten) {
        this.resultaten = resultaten;
    }





    @Override
    public Code get(int i) {
        return gokken.get(i);
    }

    @Override
    public int size() {
        return gokken.size();
    }
}

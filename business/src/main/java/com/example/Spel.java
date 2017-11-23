package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Spel {

    private Code code ;
    private Pion juistKleurEnPositie = new Pion() {{setKleur(Kleur.zwart);}};
    private Pion juistKleurFoutePositie = new Pion() {{setKleur(Kleur.wit);}};


    public Code getCode(){
        return code;
    }
    public void setCode(Code code){ this.code = code;} // TODO: 12/10/2017 verwijderen uit rc

    public SpelData getSpelData() {
        return spelData;
    }

    private SpelData spelData = new SpelData(code);

    public int getRondeTeller() {
        return rondeTeller;
    }

    private int rondeTeller = 0;



    public ArrayList<Pion> doeGok(Pion a, Pion b, Pion c, Pion d){

        ArrayList<Pion> pions = new ArrayList<Pion>();
        for (Pion p: code) {pions.add(p);}

        ArrayList<Pion> goks = new ArrayList<Pion>();
        goks.add(a);
        goks.add(b);
        goks.add(c);
        goks.add(d);
        spelData.getGokken().add(new Code(a,b,c,d));
        ArrayList<Pion> result = new ArrayList<Pion>();

        for (int gokindex = 0; gokindex < goks.size(); gokindex++){
            Pion gokPion = goks.get(gokindex);
            Pion codePion = pions.get(gokindex);
            if (gokPion.equals(codePion)){
                result.add(juistKleurEnPositie);
                goks.remove(gokPion);
                pions.remove(gokPion);
                gokindex--;
            }
        }
        for (int gokindex = 0; gokindex < goks.size(); gokindex++){
            Pion gokPion = goks.get(gokindex);
             if (pions.contains(gokPion)){
                result.add(juistKleurFoutePositie);
                pions.remove(gokPion);
            }
        }

        spelData.getResultaten().add(new Code(result));
        rondeTeller++;
        spelData.setBeurtTeller(rondeTeller);
        return  result;

    }
    public void maakCodeUniekeKleuren(int aantal){
        List<Pion> pions = new ArrayList<>(4);
        Random random = new Random();
        int willekeurigGetal ;
        List<Integer> gebruikteRandoms = new ArrayList<>();
        for (int i=0; i< aantal; i++){
            do {
                willekeurigGetal =  maakWillekeurigGetal(random, 8);
            } while(gebruikteRandoms.contains(willekeurigGetal));
            gebruikteRandoms.add(willekeurigGetal);

            Pion pion = maakPion(willekeurigGetal);
            pions.add(pion);
        }
        code = new Code(pions);
    }
    public void maakCode(int aantal){
        List<Pion> pions = new ArrayList<>(4);
        Random random = new Random();
        for (int i=0; i< aantal; i++){
            int willekeurigGetal = maakWillekeurigGetal(random, 8);
            Pion pion = maakPion(willekeurigGetal);
            pions.add(pion);
        }
        code = new Code(pions);
    }

    public int maakWillekeurigGetal(Random random, int aantal) {
        return random.nextInt(aantal);
    }

    public void maakCode6UniekeKleuren(int aantal) {
        List<Pion> pions = new ArrayList<>(4);
        Random random = new Random();

        int willekeurigGetal ;
        List<Integer> gebruikteRandoms = new ArrayList<>();

        for (int i=0; i< aantal; i++){
            do {
                willekeurigGetal =  maakWillekeurigGetal(random, 6);
            } while(gebruikteRandoms.contains(willekeurigGetal));
            gebruikteRandoms.add(willekeurigGetal);

            Pion pion = maakPion(willekeurigGetal);
            pions.add(pion);
        }
        code = new Code(pions);
    }

    public Pion maakPion(int willekeurigGetal) {
        Pion pion = new Pion();

        switch (willekeurigGetal){
            case 0: pion.setKleur(Kleur.zwart);
                break;
            case 1: pion.setKleur(Kleur.wit);
                break;
            case 2: pion.setKleur(Kleur.rood);
                break;
            case 3: pion.setKleur(Kleur.geel);
                break;
            case 4: pion.setKleur(Kleur.blauw);
                break;
            case 5: pion.setKleur(Kleur.groen);
                break;
            case 6: pion.setKleur(Kleur.bruin);
                break;
            default: pion.setKleur(Kleur.oranje);
                break;
        }
        return pion;
    }
}

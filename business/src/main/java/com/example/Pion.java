package com.example;

/**
 * Created by ief on 28/09/2017.
 */
public class Pion implements Comparable<Pion> {

    protected Kleur kleur ;


    public void setKleur(Kleur kleur){this.kleur = kleur;}
    public Kleur getKleur(){return kleur;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pion)) return false;

        Pion pion = (Pion) o;

        return getKleur() == pion.getKleur();

    }
    @Override
    public int hashCode() {
        return getKleur().hashCode();
    }

    @Override
    public String toString() {
        return this.getKleur().toString();
    }

    @Override
    public int compareTo(Pion pion) {
        return this.getKleur().compareTo(pion.getKleur());
    }
}

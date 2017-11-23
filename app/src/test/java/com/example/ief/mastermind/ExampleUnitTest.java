package com.example.ief.mastermind;

import com.example.Kleur;
import com.example.Pion;
import com.example.Spel;
import com.example.Code;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Pion zwartePion= new Pion(){{setKleur(Kleur.zwart);}};
    private Pion oranjePion = new Pion(){{setKleur(Kleur.oranje);}};
    private Pion foutePion = new Pion(){{setKleur(Kleur.fout);}};
    private Pion wittePion = new Pion(){{setKleur(Kleur.wit);}};

    private int evaluuerResultaat(ArrayList<Pion> pionnen){
        int resultaat = 0;
        for (Pion p : pionnen){
            if (p.getKleur().equals(Kleur.zwart)) resultaat += 10;
            if (p.getKleur().equals(Kleur.wit)) resultaat +=1;
        }
        return resultaat;
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void logischeGelijkheidPionnen() throws Exception {
        Pion b = new Pion(){{setKleur(Kleur.zwart);}};
        boolean test = zwartePion.equals(b);
        assertTrue(test);

    }

    @Test
    public void werktWillekeurigGetalGenerator() throws Exception {
        Spel s = new Spel();
        Random r = new Random();
        List<Integer> lijst = new ArrayList<>(100);
        for (int i = 0; i< 100; i++){
            lijst.add(s.maakWillekeurigGetal(r, 8));
        }
        for (int getal : lijst ){
            System.out.println(getal);
        }
        assertTrue(lijst.contains(7));
    }
    @Test
    public void wordDeCodeGemaakt() throws Exception {
        Spel spel = new Spel();
        spel.maakCode(4);
        Code code = spel.getCode();
        System.out.println(code);
//        Assert.assertEquals(4,code.GeefPionnen().length);
        Assert.assertEquals(4,code.size());
    }

    @Test
    public void doeEenPerfecteGok() throws Exception {



        Spel spel = new Spel();
        Code code = new Code(oranjePion, oranjePion, oranjePion, oranjePion);
        spel.setCode(code);

        ArrayList<Pion> resultaat = spel.doeGok(oranjePion, oranjePion, oranjePion, oranjePion);
        Pion [] verwachting = new Pion[] {zwartePion, zwartePion, zwartePion, zwartePion};

        assertEquals(40, evaluuerResultaat(resultaat));
    }

    @Test
    public void doeEenRedelijkeGok() throws Exception {
        Spel s = new Spel();
        s.setCode(new Code(oranjePion, oranjePion, oranjePion,oranjePion));

        ArrayList<Pion> resultaat = s.doeGok(oranjePion, zwartePion, zwartePion, oranjePion);
        Pion [] verwachting = new Pion[] {zwartePion, foutePion, foutePion, zwartePion};

        assertEquals(20, evaluuerResultaat(resultaat));
    }

    @Test
    public void doeEenNogBetereGok() throws Exception {
        Spel s = new Spel();
        s.setCode(new Code(oranjePion, zwartePion, oranjePion, zwartePion));

        ArrayList<Pion> resultaat = s.doeGok(zwartePion, oranjePion, zwartePion, oranjePion);
        Pion [] verwachting = new Pion[] {wittePion, wittePion, wittePion, wittePion};

        assertEquals(4,  evaluuerResultaat(resultaat));
    }

    @Test
    public void doeEenFouteGok() throws Exception {
        Spel s = new Spel();
        s.setCode(new Code(oranjePion, oranjePion, oranjePion,oranjePion));

        ArrayList<Pion> resultaat = s.doeGok(wittePion, zwartePion, wittePion, zwartePion);
        Pion [] verwachting = new Pion[]{foutePion, foutePion, foutePion, foutePion};

        assertEquals(0, evaluuerResultaat(resultaat));
    }

    @Test
    public void meerWitDanInDeCode() throws Exception {
        Spel s = new Spel();
        s.setCode(new Code(wittePion, wittePion, zwartePion, zwartePion));

        ArrayList<Pion> resultaat = s.doeGok(wittePion, wittePion, wittePion, zwartePion);
        Pion [] verwachting = new Pion[]{zwartePion, zwartePion, foutePion, zwartePion};
        System.out.println(evaluuerResultaat(resultaat));

        assertEquals(30,  evaluuerResultaat(resultaat));
    }

    @Test
    public void GokMetPuurZwartCodeHeeftErMaarEen() throws Exception {
        Spel s = new Spel();
        s.setCode(new Code(wittePion, wittePion, wittePion, zwartePion));

        ArrayList<Pion>  resultaat = s.doeGok(zwartePion, zwartePion, zwartePion, zwartePion);
        Pion [] verwachting = new Pion[]{foutePion, foutePion, foutePion, zwartePion};
        System.out.println(evaluuerResultaat(resultaat));

        assertEquals(10, evaluuerResultaat(resultaat));

    }

    @Test
    public void MaakCodeMetUniekeKleuren() throws Exception {
        Spel s = new Spel();
        s.maakCodeUniekeKleuren(4);
//        Pion[] pions = s.getTeZoekencode().GeefPionnen();
        List<Pion> pions = s.getCode();

        List<Pion> pionsList = new ArrayList<>();
        for (Pion p: pions) {
            System.out.println(p);
            pionsList.add(p);
        }
        Pion pion1 = /*pions[0]*/ pions.get(0);
        Pion pion2 = /*pions[1]*/ pions.get(1);
        Pion pion3 = /*pions[2]*/ pions.get(2);
        Pion pion4 = /*pions[3]*/ pions.get(3);

        pionsList.remove(pion1);
        assertFalse(pionsList.contains(pion1));
        pionsList.remove(pion2);
        assertFalse(pionsList.contains(pion2));
        pionsList.remove(pion3);
        assertFalse(pionsList.contains(pion3));
        pionsList.remove(pion4);
        assertFalse(pionsList.contains(pion4));

    }

    @Test
    public void maakCodeMetSlechts6UniekeKleuren() throws Exception {
        Spel s = new Spel();
        s.maakCode6UniekeKleuren(4);
        Pion bruin = new Pion(){{setKleur(Kleur.bruin);}};
        Pion oranje = new Pion(){{setKleur(Kleur.oranje);}};

//        Pion[] pions = s.getTeZoekencode().GeefPionnen();
       List<Pion> pions = s.getCode();
        List<Pion> pionsList = new ArrayList<>();
        for (Pion p: pions) {
            System.out.println(p);
            pionsList.add(p);
        }
        assertFalse(pionsList.contains(bruin));
        assertFalse(pionsList.contains(oranje));

    }

    @Test
    public void ControleerSpelData() throws Exception {
        Spel s = new Spel();
        s.maakCode(4);
        s.doeGok(zwartePion, zwartePion, zwartePion, zwartePion);
        s.doeGok(wittePion, oranjePion, wittePion, wittePion);
        assertEquals(2 , s.getSpelData().getBeurtTeller());
        assertEquals(oranjePion, s.getSpelData().getGokken().get(1).get(1));
    }
}
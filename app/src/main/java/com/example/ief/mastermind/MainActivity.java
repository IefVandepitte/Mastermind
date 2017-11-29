package com.example.ief.mastermind;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Code;
import com.example.Kleur;
import com.example.Pion;
import com.example.Spel;
import com.example.SpelData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private int aantal;
    private Code c;
    private int gokSequentie = 1;
    private boolean hersteld = false;

    private ImageView codeImageView1 ;
    private ImageView codeImageView2 ;
    private ImageView codeImageView3 ;
    private ImageView codeImageView4 ;
    private ParcelableSpelData spelData;
    private ArrayList<Code> gokken = new ArrayList<Code>();
    private ArrayList<Code> resultaten = new ArrayList<Code>();
    private Spel spel;

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mDoeGokButton;
    private ImageButton mGok1ImageButton1;
    private ImageButton mGok1ImageButton2;
    private ImageButton mGok1ImageButton3;
    private ImageButton mGok1ImageButton4;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spelData = new ParcelableSpelData(spel.getRondeTeller(), spel.getCode(), gokken, resultaten);
        String spelDataString = spelData.toString();
        outState.putParcelable(ParcelableSpelData.SPEL_DATA, spelData);
        Log.i(TAG, "onSaveInstanceState triggered");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            spelData = savedInstanceState.getParcelable(ParcelableSpelData.SPEL_DATA);

        }
        Log.i(TAG, "onRestoreInstanceState triggered");
    }
    @Override
    protected void onResume() {
        Log.i(TAG, "code:" + spel.getCode().toString());
        Log.i(TAG, "onResume triggered");
        super.onResume();
//        if (spelData != null) {
//            super.onDestroy();
//            Bundle opgeslagenSpelData = new Bundle();
//            opgeslagenSpelData.putParcelable(ParcelableSpelData.SPEL_DATA, spelData);
//            onCreate(opgeslagenSpelData);
//        }
        if (spelData != null){ int rondeTeller = spelData.getBeurtTeller();
            visualiseerTeZoekenCode();

                spel.setCode(spelData.getParcelableTeZoekenCode());
                c= spel.getCode();
            Log.i(TAG, "code:" + spel.getCode().toString());
                visualiseerTeZoekenCode();
            for (int i = 0 ; i < rondeTeller; i++){
                ParcelableCode gok =  (ParcelableCode)spelData.getParcelableGokken()[i];
                mGok1ImageButton1.setImageResource(vertaalPionNaarGroteAfbeelding(gok.get(0)));
                mGok1ImageButton2.setImageResource(vertaalPionNaarGroteAfbeelding(gok.get(1)));
                mGok1ImageButton3.setImageResource(vertaalPionNaarGroteAfbeelding(gok.get(2)));
                mGok1ImageButton4.setImageResource(vertaalPionNaarGroteAfbeelding(gok.get(3)));
                vertaalKleurNameNaarTagEnContentDescription(mGok1ImageButton1 ,gok.get(0).getKleur());
                vertaalKleurNameNaarTagEnContentDescription(mGok1ImageButton2 ,gok.get(1).getKleur());
                vertaalKleurNameNaarTagEnContentDescription(mGok1ImageButton3 ,gok.get(2).getKleur());
                vertaalKleurNameNaarTagEnContentDescription(mGok1ImageButton4 ,gok.get(3).getKleur());
                hersteld = true;
//                        doeGokButton.setText("Guess");
                mDoeGokButton.performClick();
            }}

    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spel spel = initSpel(4);
        mGok1ImageButton1 = (ImageButton) findViewById(R.id.gok1ImageButton1);
        mGok1ImageButton2 = (ImageButton) findViewById(R.id.gok1ImageButton2);
        mGok1ImageButton3 = (ImageButton) findViewById(R.id.gok1ImageButton3);
        mGok1ImageButton4 = (ImageButton) findViewById(R.id.gok1ImageButton4);
        final ImageButton zwartImageButton = (ImageButton) findViewById(R.id.zwartImageButton);
        final ImageButton witImageButton = (ImageButton) findViewById(R.id.witImageButton);
        final ImageButton roodImageButton = (ImageButton) findViewById(R.id.roodImageButton);
        final ImageButton geelImageButton = (ImageButton) findViewById(R.id.geelImageButton);
        final ImageButton blauwImageButton = (ImageButton) findViewById(R.id.blauwImageButton);
        final ImageButton groenImageButton = (ImageButton) findViewById(R.id.groenImageButton);
        final ImageButton bruinImageButton = (ImageButton) findViewById(R.id.bruinImageButton);
        final ImageButton oranjeImageButton = (ImageButton) findViewById(R.id.oranjeImageButton);
        codeImageView1 = (ImageView) findViewById(R.id.code_imageView1);
        codeImageView2 = (ImageView) findViewById(R.id.code_imageView2);
        codeImageView3 = (ImageView) findViewById(R.id.code_imageView3);
        codeImageView4 = (ImageView) findViewById(R.id.code_imageView4);
        mDoeGokButton = (Button) findViewById(R.id.doeGokButton);
        List<ImageButton> kleurButtons = verzamelKleurbuttons(zwartImageButton, witImageButton, roodImageButton, geelImageButton, blauwImageButton, groenImageButton, bruinImageButton, oranjeImageButton);
        kleurbuttonClick(mGok1ImageButton1, mGok1ImageButton2, mGok1ImageButton3, mGok1ImageButton4, kleurButtons);
        visualiseerTeZoekenCode();
        final List<List<ImageButton>> resultButtons = verzamelResultButtons();
        final List<List<ImageButton>> resultScoreButtons = verzamelResultaatScoreButtons();
//        maakCodeKnop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                maakCode(spel, codeTekstueel);  // TODO: 9/10/2017 aanpassen voor rc1, code mag niet zichtbaar zijn
//            }
//        });
        veranderSequentieDoorklikOpGokImageButton(mGok1ImageButton1, mGok1ImageButton2, mGok1ImageButton3, mGok1ImageButton4);

        if (savedInstanceState == null){
            Log.i(TAG, "onCreate triggered zonder bundle");
            Log.i(TAG, "code:" + spel.getCode().toString());
        }

        mDoeGokButton.setOnClickListener(new View.OnClickListener() {
           int rondeTeller = 0;
            @Override

            public void onClick(View view) {

               final String kleur1 = mGok1ImageButton1.getTag().toString()
                        .substring(2,geefIndexVan_(mGok1ImageButton1.getTag().toString()));
                final String kleur2 = mGok1ImageButton2.getTag().toString()
                        .substring(2,geefIndexVan_(mGok1ImageButton2.getTag().toString()));
                final String kleur3 = mGok1ImageButton3.getTag().toString()
                        .substring(2,geefIndexVan_(mGok1ImageButton3.getTag().toString()));
                final String kleur4 = mGok1ImageButton4.getTag().toString()
                        .substring(2,geefIndexVan_(mGok1ImageButton4.getTag().toString()));
                final  Pion pion1 = new Pion(){{setKleur(VertaalStringNaarPionKleur(kleur1));}};
                final  Pion pion2 = new Pion(){{setKleur(VertaalStringNaarPionKleur(kleur2));}};
                final Pion pion3 = new Pion(){{setKleur(VertaalStringNaarPionKleur(kleur3));}};
                final Pion pion4 = new Pion(){{setKleur(VertaalStringNaarPionKleur(kleur4));}};
                ArrayList<Pion> resultaat = spel.doeGok(pion1,pion2,pion3, pion4);

                for (int i = 0; i < resultaat.size(); i++){
                    resultScoreButtons.get(rondeTeller).get(i)
                            .setImageResource(vertaalPionNaarKleineAfbeelding(resultaat.get(i)));
                    resultScoreButtons.get(rondeTeller).get(i).setVisibility(View.VISIBLE);
                }
                winsituatie(resultaat);
                toonResultEnResultScoreButtonsVoorDezeRonde(pion1, pion2, pion3, pion4);

// TODO: 20/10/2017 deze blok is toegevoegd om een intent te maken parcelablespeldata creator is corrupt
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//
                final Pion temp = new Pion(){{setKleur(Kleur.geel);}};
                final Code codeVoorresultaat = new Code(new Pion(){{setKleur(Kleur.geel);}},new Pion(){{setKleur(Kleur.geel);}}
                        ,new Pion(){{setKleur(Kleur.geel);}},new Pion(){{setKleur(Kleur.geel);}});
                if  (resultaat.size()!=0){
                        for (int i = 0; i < resultaat.size(); i++){
                            Pion p = resultaat.get(i);
                        codeVoorresultaat.get(i).setKleur(p.getKleur());
                    }
                }
                for (int i = codeVoorresultaat.size()-1; i >= 0; i--){
                    if (codeVoorresultaat.get(i).equals(temp)){
                        codeVoorresultaat.remove(temp);
                    }
                }
                gokken.add(new Code(pion1, pion2, pion3, pion4));
                resultaten.add(codeVoorresultaat);

// TODO: 20/10/2017 tot bovenstaande regel
                rondeTeller++;
                if (verliesSituatie()) return;
            }
            private boolean verliesSituatie() {
                if(rondeTeller >9) {
                    maakCodeZichtbaar();
                    TextView gameOverBoodschap = (TextView) findViewById(R.id.gameOverTextView);
                    gameOverBoodschap.setVisibility(View.VISIBLE);
                    rondeTeller = activeerReset(mDoeGokButton);
                    return true;
                }
                return false;
            }
            private void toonResultEnResultScoreButtonsVoorDezeRonde(Pion pion1, Pion pion2, Pion pion3, Pion pion4) {
                resultButtons.get(rondeTeller).get(0)
                        .setImageResource(vertaalPionNaarMediumAfbeelding(pion1));
                resultButtons.get(rondeTeller).get(1)
                        .setImageResource(vertaalPionNaarMediumAfbeelding(pion2));
                resultButtons.get(rondeTeller).get(2)
                        .setImageResource(vertaalPionNaarMediumAfbeelding(pion3));
                ;
                resultButtons.get(rondeTeller).get(3)
                        .setImageResource(vertaalPionNaarMediumAfbeelding(pion4));
                resultButtons.get(rondeTeller).get(0)
                        .setVisibility(View.VISIBLE);
                resultButtons.get(rondeTeller).get(1)
                        .setVisibility(View.VISIBLE);
                resultButtons.get(rondeTeller).get(2)
                        .setVisibility(View.VISIBLE);
                resultButtons.get(rondeTeller).get(3)
                        .setVisibility(View.VISIBLE);
            }
            private void winsituatie(ArrayList<Pion> resultaat) {
                if (evalueerResultaat(resultaat) == 40){
                   TextView victoryBoodschap = (TextView) findViewById(R.id.gewonnenTextView);
                    maakCodeZichtbaar();
                   rondeTeller = activeerReset(mDoeGokButton);
                    victoryBoodschap.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void veranderSequentieDoorklikOpGokImageButton(ImageButton gok1ImageButton1, ImageButton gok1ImageButton2, ImageButton gok1ImageButton3, ImageButton gok1ImageButton4) {
        gok1ImageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view) {
                gokSequentie = 1;
        }
        });
        gok1ImageButton2.setOnClickListener(new View.OnClickListener(){
            @Override            public void onClick(View view) {
                gokSequentie = 2;
            }
        });
        gok1ImageButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gokSequentie = 3;
            }
        });
        gok1ImageButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gokSequentie =4;
            }
        });
    }
    @NonNull
    private List<List<ImageButton>> verzamelResultaatScoreButtons() {
        final List<List<ImageButton>> resultScoreButtons = new ArrayList<>();
        for (int aantalGokken = 0 ; aantalGokken < 10; aantalGokken++) {
            resultScoreButtons.add(new ArrayList<ImageButton>());
            for (int knoppenTeller = 1; knoppenTeller <=4; knoppenTeller++){
                int knop = getResources().getIdentifier("result"+(aantalGokken+1)+
                        "ScoreImageButton" +knoppenTeller, "id", getPackageName());
                resultScoreButtons.get(aantalGokken).add((ImageButton) findViewById(knop) );}
        }
        return resultScoreButtons;
    }
    @NonNull
    private List<List<ImageButton>> verzamelResultButtons() {
        final List<List<ImageButton>> resultButtons = new ArrayList<>();
        for (int aantalGokken = 0 ; aantalGokken < 10; aantalGokken++) {
            resultButtons.add(new ArrayList<ImageButton>());
            for (int knoppenTeller = 1; knoppenTeller <=4; knoppenTeller++){
                int knop = getResources().getIdentifier("result"+(aantalGokken+1)+
                        "ImageButton" +knoppenTeller, "id", getPackageName());
                resultButtons.get(aantalGokken).add((ImageButton) findViewById(knop) );}
        }
        return resultButtons;
    }
    private void visualiseerTeZoekenCode() {
        List<Pion> pionnen = c;
        codeImageView1.setImageResource(vertaalPionNaarGroteAfbeelding(/*pionnen[0]*/pionnen.get(0)));
        codeImageView2.setImageResource(vertaalPionNaarGroteAfbeelding(/*pionnen[1]*/pionnen.get(1)));
        codeImageView3.setImageResource(vertaalPionNaarGroteAfbeelding(/*pionnen[2]*/pionnen.get(2)));
        codeImageView4.setImageResource(vertaalPionNaarGroteAfbeelding(/*pionnen[3]*/pionnen.get(3)));
    }
    @NonNull
    private List<ImageButton> verzamelKleurbuttons(ImageButton zwartImageButton, ImageButton witImageButton, ImageButton roodImageButton, ImageButton geelImageButton, ImageButton blauwImageButton, ImageButton groenImageButton, ImageButton bruinImageButton, ImageButton oranjeImageButton) {
        List<ImageButton> kleurButtons = new ArrayList<>();
        kleurButtons.add(zwartImageButton);
        kleurButtons.add(witImageButton);
        kleurButtons.add(roodImageButton);
        kleurButtons.add(geelImageButton);
        kleurButtons.add(blauwImageButton);
        kleurButtons.add(groenImageButton);
        kleurButtons.add(bruinImageButton);
        kleurButtons.add(oranjeImageButton);
        return kleurButtons;
    }
    private void kleurbuttonClick(final ImageButton gok1ImageButton1, final ImageButton gok1ImageButton2, final ImageButton gok1ImageButton3, final ImageButton gok1ImageButton4, List<ImageButton> kleurButtons) {
        for (ImageButton b : kleurButtons){
            final ImageButton button = b;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(gokSequentie){
                        case 1:
                            gok1ImageButton1.setImageResource(vertaalImageButtonNaarGroteAfbeelding(button));
                            gok1ImageButton1.setTag(button.getTag().toString());
                            break;
                        case 2:
                            gok1ImageButton2.setImageResource(vertaalImageButtonNaarGroteAfbeelding(button));
                            gok1ImageButton2.setTag(button.getTag().toString());
                            break;
                        case 3:
                            gok1ImageButton3.setImageResource(vertaalImageButtonNaarGroteAfbeelding(button));
                            gok1ImageButton3.setTag(button.getTag().toString());
                            break;
                        case 4:
                            gok1ImageButton4.setImageResource(vertaalImageButtonNaarGroteAfbeelding(button));
                            gok1ImageButton4.setTag(button.getTag().toString());
                            break;
                    }
                    if (gokSequentie == 4 ){
                        gokSequentie = 1;
                    }
                    else    gokSequentie++;
                }
            });
        }
    }
    private void vertaalKleurNameNaarTagEnContentDescription(ImageButton button, Kleur kleur) {
        switch (kleur.name()){
            case "zwart":
                button.setContentDescription("@drawable/a0zwart_48");
                button.setTag("a0zwart_48");
                break;
            case "wit":
                button.setContentDescription("@drawable/a1wit_48");
                button.setTag("a1wit_48");
                break;
            case "rood":
                button.setContentDescription("@drawable/a2rood_48");
                button.setTag("a2rood_48");
                break;
            case "geel":
                button.setContentDescription("@drawable/a3geel_48");
                button.setTag("a3geel_48");
                break;
            case "blauw":
                button.setContentDescription("@drawable/a4blauw_48");
                button.setTag("a4blauw_48");
                break;
            case "groen":
                button.setContentDescription("@drawable/a5groen_48");
                button.setTag("a5groen_48");
                break;
            case "bruin":
                button.setContentDescription("@drawable/a6bruin_48");
                button.setTag("a6bruin_48");
                break;
            case "oranje":
                button.setContentDescription("@drawable/a7oranje_48");
                button.setTag("a7oranje_48");
                break;
        }
    }
    @NonNull
    private Spel initSpel(int aantal) {
        spel = new Spel();
        spel.maakCodeUniekeKleuren(aantal);
        c = spel.getCode();
        gokken.clear();
        resultaten.clear();
        return spel;
    }
    public int activeerReset(Button doeGokButton) {
        doeGokButton.setText("Reset");
        doeGokButton.setOnClickListener(null);
        doeGokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onDestroy();
                onCreate(null);
            }
        });
        int rondeTeller =0;
        return rondeTeller;
    }
    public void maakCodeZichtbaar() {
        codeImageView1.setVisibility(View.VISIBLE);
        codeImageView2.setVisibility(View.VISIBLE);
        codeImageView3.setVisibility(View.VISIBLE);
        codeImageView4.setVisibility(View.VISIBLE);
    }
    private void maakCode(Spel spel) {
        spel.maakCode(aantal);
    }
    private void maakCode(Spel spel, EditText codeTekstueel) {
        spel.maakCode(aantal);
        Code c = spel.getCode();
        List<Pion> code = c;

        Pion codePion0 =code.get(0);
        Pion codePion1 =code.get(1);
        Pion codePion2 =code.get(2);
        Pion codePion3 =code.get(3);

        codeImageView1 = (ImageView) findViewById(R.id.code_imageView1);
        codeImageView2 = (ImageView) findViewById(R.id.code_imageView2);
        codeImageView3 = (ImageView) findViewById(R.id.code_imageView3);
        codeImageView4 = (ImageView) findViewById(R.id.code_imageView4);


        codeImageView1.setImageResource(vertaalPionNaarGroteAfbeelding(codePion0));
        codeImageView2.setImageResource(vertaalPionNaarGroteAfbeelding(codePion1));
        codeImageView3.setImageResource(vertaalPionNaarGroteAfbeelding(codePion2));
        codeImageView4.setImageResource(vertaalPionNaarGroteAfbeelding(codePion3));


        codeTekstueel.setText(c.toString());
    }
    private int evalueerResultaat(ArrayList<Pion> pionnen){
        int resultaat = 0;
        for (Pion p : pionnen){
            if (p.getKleur().equals(Kleur.zwart)) resultaat += 10;
            if (p.getKleur().equals(Kleur.wit)) resultaat +=1;
        }
        return resultaat;
    }
    private Kleur VertaalStringNaarPionKleur(String kleur) {
        Kleur k = Kleur.fout;
        switch (kleur){
            case "zwart":
                k = Kleur.zwart;
                break;
            case "wit":
                k = Kleur.wit;
                break;
            case "rood":
                k = Kleur.rood;
                break;
            case "geel":
                k = Kleur.geel;
                break;
            case "blauw":
                k = Kleur.blauw;
                break;
            case "groen":
                k = Kleur.groen;
                break;
            case "bruin":
                k = Kleur.bruin;
                break;
            case "oranje":
                k = Kleur.oranje;
                break;
            default:
                k = Kleur.fout;
        }
        return k;
    }
    private int geefIndexVan_(String s) {
        return s.indexOf('_');    }
    private void veranderKleur(ImageButton button) {
        String tag = button.getTag().toString();
        if (!tag.isEmpty()){
            int _index = tag.indexOf("_");
            String kleur = tag.substring(0,_index);
            String grootte = tag.substring(_index);
            String volgendKleur = "";
            switch(kleur){
                case "a0zwart":
                    volgendKleur = "a1wit";
                    break;
                case "a1wit":
                    volgendKleur = "a2rood";
                    break;
                case "a2rood":
                    volgendKleur = "a3geel";
                    break;
                case "a3geel":
                    volgendKleur = "a4blauw";
                    break;
                case "a4blauw":
                    volgendKleur = "a5groen";
                    break;
                case "a5groen":
                    volgendKleur = "a6bruin";
                    break;
                case "a6bruin":
                    volgendKleur = "a7oranje";
                    break;
                case "a7oranje":
                    volgendKleur = "a0zwart";
                    break;
                default:volgendKleur = kleur;
            }
            String bron = volgendKleur+grootte;
            int afbeeldingVertaaldNaarInt = getResources().getIdentifier(bron, "drawable", getPackageName());
            button.setTag(bron);
            button.setImageResource(afbeeldingVertaaldNaarInt);
        }
    }
    private int vertaalPionNaarGroteAfbeelding(Pion pion) {
    int bron = 0;
    switch (pion.getKleur()){
        case zwart:
            bron = R.drawable.a0zwart_48;
            break;
        case wit:
            bron = R.drawable.a1wit_48;
            break;
        case rood:
            bron = R.drawable.a2rood_48;
            break;
        case geel:
            bron = R.drawable.a3geel_48;
            break;
        case blauw:
            bron = R.drawable.a4blauw_48;
            break;
        case groen:
            bron = R.drawable.a5groen_48;
            break;
        case bruin:
            bron = R.drawable.a6bruin_48;
            break;
        case oranje:
            bron = R.drawable.a7oranje_48;
            break;
    }
    return bron;
}
    private int vertaalPionNaarMediumAfbeelding(Pion pion) {
        int bron = 0;
            switch (pion.getKleur()){
                case zwart:
                    bron = R.drawable.a0zwart_32;
                    break;
                case wit:
                    bron = R.drawable.a1wit_32;
                    break;
                case rood:
                    bron = R.drawable.a2rood_32;
                    break;
                case geel:
                    bron = R.drawable.a3geel_32;
                    break;
                case blauw:
                    bron = R.drawable.a4blauw_32;
                    break;
                case groen:
                    bron = R.drawable.a5groen_32;
                    break;
                case bruin:
                    bron = R.drawable.a6bruin_32;
                    break;
                case oranje:
                    bron = R.drawable.a7oranje_32;
                    break;
            }
        return bron;
    }
    private int vertaalPionNaarKleineAfbeelding(Pion pion) {
        int bron = 0;
        switch (pion.getKleur()){
            case zwart:
                bron = R.drawable.a0zwart_24;
                break;
            case wit:
                bron = R.drawable.a1wit_24;
                break;
            case rood:
                bron = R.drawable.a2rood_24;
                break;
            case geel:
                bron = R.drawable.a3geel_24;
                break;
            case blauw:
                bron = R.drawable.a4blauw_24;
                break;
            case groen:
                bron = R.drawable.a5groen_24;
                break;
            case bruin:
                bron = R.drawable.a6bruin_24;
                break;
            case oranje:
                bron = R.drawable.a7oranje_24;
                break;
        }

        return bron;
    }
    private int vertaalImageButtonNaarGroteAfbeelding(ImageButton button) {
        int bron = 0;

        switch (button.getTag().toString()){
            case "a0zwart_48":
                bron = R.drawable.a0zwart_48;
                break;
            case "a1wit_48":
                bron = R.drawable.a1wit_48;
                break;
            case "a2rood_48":
                bron = R.drawable.a2rood_48;
                break;
            case "a3geel_48":
                bron = R.drawable.a3geel_48;
                break;
            case "a4blauw_48":
                bron = R.drawable.a4blauw_48;
                break;
            case "a5groen_48":
                bron = R.drawable.a5groen_48;
                break;
            case "a6bruin_48":
                bron = R.drawable.a6bruin_48;
                break;
            case "a7oranje_48":
                bron = R.drawable.a7oranje_48;
                break;
        }
        return bron;
    }
}
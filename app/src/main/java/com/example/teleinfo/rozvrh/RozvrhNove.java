package com.example.teleinfo.rozvrh;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.teleinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RozvrhNove extends AppCompatActivity {

    int velColumn = 190; //šířa slpoupce
/*
    int velHeaderu = 150;
    int velKlasickeHod = 225;
    int velSesteHod = (velKlasickeHod*70)/45; // klasicka hod 45min, velka hodina 70min

    int velMalePrestavky = 75;
    int velStredniPrestavky = 100;
    int velVelkePrestavky = 150;


    int velHeaderu = 130;
    int velKlasickeHod = 190;
    int velSesteHod = (velKlasickeHod*70)/45; // klasicka hod 45min, velka hodina 70min
    int velVrat = (velKlasickeHod*25)/45; // klasicka hod 45min, velka hodina 70min
    int velMorningSupervision = (velKlasickeHod*10)/45;



    int velMalePrestavky = 60;
    int velStredniPrestavky = 90;
    int velVelkePrestavky = 120;
*/


    int velHeaderu = 150;
    int velKlasickeHod = 190;
    int velSesteHod = (velKlasickeHod*70)/45; // klasicka hod 45min, velka hodina 70min
    int velVrat = (velKlasickeHod*25)/45; // klasicka hod 45min, velka hodina 70min
    int velMorningSupervision = (velKlasickeHod*10)/45;



    int velMalePrestavky = 60;
    int velStredniPrestavky = 90;
    int velVelkePrestavky = 120;

    int velSupervision6ab = (velSesteHod*25)/70 + velMalePrestavky;



    int marginMainCard = 12;
    int lineBorderHeight = 3;

    int lineBorderColor = Color.parseColor("#ECEFF1");




    String Hour0Title = "0. HOD";
    String Hour0From = "7:25";
    String Hour0To = "8:10";

    String Hour1Title = "1. HOD";
    String Hour1From = "8:15";
    String Hour1To = "9:00";

    String Hour2Title = "2. HOD";
    String Hour2From = "9:10";
    String Hour2To = "9:55";

    String Hour3Title = "3. HOD";
    String Hour3From = "10:15";
    String Hour3To = "11:00";

    String Hour4Title = "4. HOD";
    String Hour4From = "11:05";
    String Hour4To = "11:50";

    String Hour5Title = "5. HOD";
    String Hour5From = "11:55";
    String Hour5To = "12:40";

    String Hour6Title = "6. HOD";
    String Hour6From = "12:45";
    String Hour6To = "13:55";

    String Hour7Title = "7. HOD";
    String Hour7From = "14:00";
    String Hour7To = "14:45";

    String Hour8Title = "8. HOD";
    String Hour8From = "14:50";
    String Hour8To = "15:35";










    int marginHour0 = velHeaderu + 0;
    int marginHour1 = velHeaderu + 1*velKlasickeHod + 1*velMalePrestavky;
    int marginHour2 = velHeaderu + 2*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky;
    int marginHour3 = velHeaderu + 3*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginHour4 = velHeaderu + 4*velKlasickeHod + 2*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginHour5 = velHeaderu + 5*velKlasickeHod + 3*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginHour6 = velHeaderu + 6*velKlasickeHod + 4*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginHour7 = velHeaderu + 6*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginHour8 = velHeaderu + 7*velKlasickeHod + 1*velSesteHod + 6*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;

    int marginBreak0 = velHeaderu + 1*velKlasickeHod;
    int marginBreak1 = velHeaderu + 2*velKlasickeHod + 1*velMalePrestavky;
    int marginBreak2 = velHeaderu + 3*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky;
    int marginBreak3 = velHeaderu + 4*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginBreak4 = velHeaderu + 5*velKlasickeHod + 2*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginBreak5 = velHeaderu + 6*velKlasickeHod + 3*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginBreak6 = velHeaderu + 6*velKlasickeHod  + 1*velSesteHod + 4*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginBreak7 = velHeaderu + 7*velKlasickeHod  + 1*velSesteHod + 5*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    int marginBreak8 = velHeaderu + 8*velKlasickeHod  + 1*velSesteHod + 6*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;

    int marginBreak2a = marginBreak2;
    int marginBreak2b = marginBreak2+velVelkePrestavky/2;

    int marginLunch6a = marginHour6;
    int marginLunch6b = marginHour6+velKlasickeHod;
    int marginLunch6middle = marginHour6 + (velSesteHod - (velSesteHod-velKlasickeHod))/2;



    int size;

    LinearLayout linearLayoutMain;

    LinearLayout linearLayoutsColumn[];
    RelativeLayout relativeLayoutsColumn[];

    LinearLayout linearLayoutsColumnCellHeader[];
    CardView cardViewHeader[];
    LinearLayout linearLayoutsInnerCardHeader[];
    TextView textViewsRowHeader[];

    LinearLayout linearLayoutsColumnCellHour0[];
    LinearLayout linearLayoutsColumnCellHour1[];
    LinearLayout linearLayoutsColumnCellHour2[];
    LinearLayout linearLayoutsColumnCellHour3[];
    LinearLayout linearLayoutsColumnCellHour4[];
    LinearLayout linearLayoutsColumnCellHour5[];
    LinearLayout linearLayoutsColumnCellHour6[];
    LinearLayout linearLayoutsColumnCellHour7[];
    LinearLayout linearLayoutsColumnCellHour8[];

    LinearLayout linearLayoutsColumnCellBreak0[];
    LinearLayout linearLayoutsColumnCellBreak1[];
    LinearLayout linearLayoutsColumnCellBreak2[];
    LinearLayout linearLayoutsColumnCellBreak3[];
    LinearLayout linearLayoutsColumnCellBreak4[];
    LinearLayout linearLayoutsColumnCellBreak5[];
    LinearLayout linearLayoutsColumnCellBreak6[];
    LinearLayout linearLayoutsColumnCellBreak7[];
    LinearLayout linearLayoutsColumnCellBreak8[];

    CardView cardViewHour0[];
    CardView cardViewHour1[];
    CardView cardViewHour2[];
    CardView cardViewHour3[];
    CardView cardViewHour4[];
    CardView cardViewHour5[];
    CardView cardViewHour6[];
    CardView cardViewHour7[];
    CardView cardViewHour8[];

    CardView cardViewBreak0[];
    CardView cardViewBreak1[];
    CardView cardViewBreak2[];
    CardView cardViewBreak3[];
    CardView cardViewBreak4[];
    CardView cardViewBreak5[];
    CardView cardViewBreak6[];
    CardView cardViewBreak7[];
    CardView cardViewBreak8[];

    LinearLayout linearLayoutsInnerCardHour0[];
    LinearLayout linearLayoutsInnerCardHour1[];
    LinearLayout linearLayoutsInnerCardHour2[];
    LinearLayout linearLayoutsInnerCardHour3[];
    LinearLayout linearLayoutsInnerCardHour4[];
    LinearLayout linearLayoutsInnerCardHour5[];
    LinearLayout linearLayoutsInnerCardHour6[];
    LinearLayout linearLayoutsInnerCardHour7[];
    LinearLayout linearLayoutsInnerCardHour8[];

    LinearLayout linearLayoutsInnerCardBreak0[];
    LinearLayout linearLayoutsInnerCardBreak1[];
    LinearLayout linearLayoutsInnerCardBreak2[];
    LinearLayout linearLayoutsInnerCardBreak3[];
    LinearLayout linearLayoutsInnerCardBreak4[];
    LinearLayout linearLayoutsInnerCardBreak5[];
    LinearLayout linearLayoutsInnerCardBreak6[];
    LinearLayout linearLayoutsInnerCardBreak7[];
    LinearLayout linearLayoutsInnerCardBreak8[];


    TextView textViewsRow1Hour0[];
    TextView textViewsRow2Hour0[];
    TextView textViewsRow3Hour0[];
    TextView textViewsRow1Hour1[];
    TextView textViewsRow2Hour1[];
    TextView textViewsRow3Hour1[];
    TextView textViewsRow1Hour2[];
    TextView textViewsRow2Hour2[];
    TextView textViewsRow3Hour2[];
    TextView textViewsRow1Hour3[];
    TextView textViewsRow2Hour3[];
    TextView textViewsRow3Hour3[];
    TextView textViewsRow1Hour4[];
    TextView textViewsRow2Hour4[];
    TextView textViewsRow3Hour4[];
    TextView textViewsRow1Hour5[];
    TextView textViewsRow2Hour5[];
    TextView textViewsRow3Hour5[];
    TextView textViewsRow1Hour6[];
    TextView textViewsRow2Hour6[];
    TextView textViewsRow3Hour6[];
    TextView textViewsRow1Hour7[];
    TextView textViewsRow2Hour7[];
    TextView textViewsRow3Hour7[];
    TextView textViewsRow1Hour8[];
    TextView textViewsRow2Hour8[];
    TextView textViewsRow3Hour8[];

    TextView textViewsRowBreak0[];
    TextView textViewsRowBreak1[];
    TextView textViewsRowBreak2[];
    TextView textViewsRowBreak3[];
    TextView textViewsRowBreak4[];
    TextView textViewsRowBreak5[];
    TextView textViewsRowBreak6[];
    TextView textViewsRowBreak7[];
    TextView textViewsRowBreak8[];

    LinearLayout linearLayoutsLineBorderHour0[];
    LinearLayout linearLayoutsLineBorderHour1[];
    LinearLayout linearLayoutsLineBorderHour2[];
    LinearLayout linearLayoutsLineBorderHour3[];
    LinearLayout linearLayoutsLineBorderHour4[];
    LinearLayout linearLayoutsLineBorderHour5[];
    LinearLayout linearLayoutsLineBorderHour6[];
    LinearLayout linearLayoutsLineBorderHour7[];
    LinearLayout linearLayoutsLineBorderHour8[];

    LinearLayout linearLayoutsLineBorderBreak0[];
    LinearLayout linearLayoutsLineBorderBreak1[];
    LinearLayout linearLayoutsLineBorderBreak2[];
    LinearLayout linearLayoutsLineBorderBreak3[];
    LinearLayout linearLayoutsLineBorderBreak4[];
    LinearLayout linearLayoutsLineBorderBreak5[];
    LinearLayout linearLayoutsLineBorderBreak6[];
    LinearLayout linearLayoutsLineBorderBreak7[];
    LinearLayout linearLayoutsLineBorderBreak8[];







    LinearLayout linearLayoutsColumnCellHour6a[];
    CardView cardViewHour6a[];
    LinearLayout linearLayoutsInnerCardHour6a[];
    TextView textViewsRow1Hour6a[];
    TextView textViewsRow2Hour6a[];
    TextView textViewsRow3Hour6a[];


    LinearLayout linearLayoutsColumnCellHour6b[];
    CardView cardViewHour6b[];
    LinearLayout linearLayoutsInnerCardHour6b[];
    TextView textViewsRow1Hour6b[];
    TextView textViewsRow2Hour6b[];
    TextView textViewsRow3Hour6b[];

    LinearLayout linearLayoutsColumnCellLunch[];
    CardView cardViewLunch[];
    LinearLayout linearLayoutsInnerCardLunch[];
    TextView textViewsRowLunch[];

    LinearLayout linearLayoutsColumnCellVrat[];
    CardView cardViewVrat[];
    LinearLayout linearLayoutsInnerCardVrat[];
    TextView textViewsRowVrat[];

    LinearLayout linearLayoutsColumnCellPPP[];
    CardView cardViewPPP[];
    LinearLayout linearLayoutsInnerCardPPP[];
    TextView textViewsRowPPP[];

    LinearLayout linearLayoutsColumnCellMorningSupervision[];
    CardView cardViewMorningSupervision[];
    LinearLayout linearLayoutsInnerCardMorningSupervision[];
    TextView textViewsRowMorningSupervision[];

    LinearLayout linearLayoutsColumnCellSupervision6a[];
    CardView cardViewSupervision6a[];
    LinearLayout linearLayoutsInnerCardSupervision6a[];
    TextView textViewsRowSupervision6a[];

    LinearLayout linearLayoutsColumnCellSupervision6b[];
    CardView cardViewSupervision6b[];
    LinearLayout linearLayoutsInnerCardSupervision6b[];
    TextView textViewsRowSupervision6b[];

    LinearLayout linearLayoutsColumnCellOther[];
    CardView cardViewOther[];
    LinearLayout linearLayoutsInnerCardOther[];
    TextView textViewsRowOther[];


    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DataSnapshot dataSnapshot_global;

    List<String> names = new ArrayList<>();

    LinearLayout.LayoutParams lp3;
    LinearLayout.LayoutParams lp4;
    LinearLayout.LayoutParams lp_mainCard;
    LinearLayout.LayoutParams lp_match_match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rozvrh_activity_rozvrh_nove);

        lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("rozvrh/20_9");

        names = new ArrayList<>();

        names.add("Bardaševská_Marcela_Bam");
        names.add("Bidláková_Svatava_Bd");
        names.add("Bureš_Miroslav_Bur");
        names.add("Cinner_Radovan_Cin");
        names.add("Dědičík_Pavel_Dě");
        names.add("Ďurian_Čeněk_Ďu");
        names.add("Dušková_Barbora_Du");
        names.add("Frömmelová_Svatava_Fro");
        names.add("Glacová_Lenka_Gla");
        names.add("Gocalová_Daniela_Go");
        names.add("Halouzka_Pavel_Ha");
        names.add("Jedlička_Václav_Jed");
        names.add("Johnová_Petra_Joh");
        names.add("Justová_Emília_Ju");
        names.add("Knápek_František_Kná");
        names.add("Koníček_Michal_Kon");
        names.add("Kopřivová_Michaela_Kop");
        names.add("Koždoň_Pavel_Kož");
        names.add("Krupová _Lenka_Kru");
        names.add("Kubala_Jiří_Kub");
        names.add("Lašková_Kateřina_Laš");
        names.add("Lauterbach_Filip_Lau");
        names.add("Mrázková Miková_Iveta_Mk");
        names.add("Němec_Milan_Ně");
        names.add("Nerudová_Silvana_Ner");
        names.add("Pivoňková_Vladimíra_Pká");
        names.add("Prokop_Radomír_Pro");
        names.add("Radová_Vladimíra_unknown");
        names.add("Siláková_Veronika_Sil");
        names.add("Smolka_Aleš_Smo");
        names.add("Sulír_Martin_Sul");
        names.add("Svobodová_Andrea_Svo");
        names.add("Szpandrzyková_Dagmar_Szp");
        names.add("Šestáková_Ivana_unknown");
        names.add("Šmiřáková_Radka_Šmi");
        names.add("Štěpánová_Ivana_Ště");
        names.add("Vašíček_David_Vaš");
        names.add("Vavříček_Pavel_Vav");
        names.add("Weintrittová_Kamila_Wt");
        names.add("Wiesnerová_Božena_Wie");
        names.add("Zubek_Pavel_Zu");

        size = names.size() + 1;

        initViewElement();

        createColumn(0);
        createBorder(0);
/*
        addHeader(0, "");
        addHour0(0, true, null);
        addHour1(0, true, null);
        addHour2(0, true, null);
        addHour3(0, true, null);
        addHour4(0, true, null);
        addHour5(0, true, null);
        addHour6(0, true, null);
        addHour7(0, true, null);
        addHour8(0, true, null);

        addBreak0(0,true, null);
        addBreak1(0,true, null);
        addBreak2(0,true, null, marginBreak2, velVelkePrestavky);
        addBreak3(0,true, null);
        addBreak4(0,true, null);
        addBreak5(0,true, null);
        addBreak6(0,true, null);
        addBreak7(0,true, null);
        addBreak8(0,true, null);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot_global = dataSnapshot;





                for(int i = 1; i < size ; i++){

                    String teacher = names.get(i-1);

                    createColumn(i);
                    createBorder(i);



                    String name = teacher.split("_")[0];
                    String surname = teacher.split("_")[1];

                    addHeader(i, name+ "\n" +surname);


                    try{
                        String txt1 = dataSnapshot.child("0").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("0").child(teacher).getValue(HourObject.class);
                        addHour0(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("1").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("1").child(teacher).getValue(HourObject.class);
                        addHour1(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("2").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("2").child(teacher).getValue(HourObject.class);
                        addHour2(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("3").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("3").child(teacher).getValue(HourObject.class);
                        addHour3(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("4").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("4").child(teacher).getValue(HourObject.class);
                        addHour4(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("5").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("5").child(teacher).getValue(HourObject.class);
                        addHour5(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("6a").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("6a").child(teacher).getValue(HourObject.class);
                        addHour6a(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("6b").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("6b").child(teacher).getValue(HourObject.class);
                        addHour6b(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("7").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("7").child(teacher).getValue(HourObject.class);
                        addHour7(i,false, hourObject);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("8").child(teacher).child("Predmet").getValue(String.class).split(" ")[0];
                        HourObject hourObject = dataSnapshot.child("8").child(teacher).getValue(HourObject.class);
                        addHour8(i,false, hourObject);

                    }catch (NullPointerException e){}


                   // Log.e(TAG, "decka: " + dataSnapshot.getChildren().forEach(); );


                    try{
                        String txt1 = dataSnapshot.child("d_0p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_0p").child(teacher).getValue(SupervisionObject.class);
                        addBreak0(i, false, hourObject);
                    }catch (NullPointerException e){}

                   try{
                       String txt1 = dataSnapshot.child("d_1p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_1p").child(teacher).getValue(SupervisionObject.class);
                        addBreak1(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_2p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_2p").child(teacher).getValue(SupervisionObject.class);
                        addBreak2(i, false, hourObject, marginBreak2, velVelkePrestavky);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_2pa").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_2pa").child(teacher).getValue(SupervisionObject.class);
                        addBreak2(i, false, hourObject, marginBreak2a,velVelkePrestavky/2);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_2pb").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_2pb").child(teacher).getValue(SupervisionObject.class);
                        addBreak2(i, false, hourObject, marginBreak2b, velVelkePrestavky/2);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_3p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_3p").child(teacher).getValue(SupervisionObject.class);
                        addBreak3(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_4p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_4p").child(teacher).getValue(SupervisionObject.class);
                        addBreak4(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_5p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_5p").child(teacher).getValue(SupervisionObject.class);
                        addBreak5(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_6p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_6p").child(teacher).getValue(SupervisionObject.class);
                        addBreak6(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_7p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_7p").child(teacher).getValue(SupervisionObject.class);
                        addBreak7(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_8p").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        SupervisionObject hourObject = dataSnapshot.child("d_8p").child(teacher).getValue(SupervisionObject.class);
                        addBreak8(i, false, hourObject);
                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_MorningSupervision").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        addMorningSupervision(i, txt1);

                    }catch (NullPointerException e){}

                    try{
                          String txt1 = dataSnapshot.child("d_PPP").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        addPPP(i);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_Vrat").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        addVrat(i);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_6pa").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        addSupervision6a(i, txt1);

                    }catch (NullPointerException e){}

                    try{
                        String txt1 = dataSnapshot.child("d_6pb").child(teacher).child("Location").getValue(String.class).split(" - ")[0];
                        addSupervision6b(i, txt1);

                    }catch (NullPointerException e){}



                    if(i==8){

                        addOther(i, "Elektrotechnická výstava");

                    }


                    try{
                        textViewsRow1Hour0[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour0[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour0[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour0[i].setVisibility(View.GONE);
                    }

                }

















                // --------------------------------------------------------------------------------- hodina 8




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                // TODO
                //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

            }
        });


*/

























/*

        createColumn(1);
        createBorder(1);

        addHeader(1, "David\nVašíček");

        addHour0(1, "PJ","3.A","17", false);
        addHour1(1, "PRA","3.B","18", false);
        addHour2(1, "PJ","3.A","17", false);
        addHour3(1, "PRA","3.B","18", false);
        addHour4(1, "PJ","3.A","17", false);
        addHour5(1, "PRA","3.B","18", false);
        //addHour6(1, "PJ","3.A","17", false);
        addHour7(1, "PRA","3.B","18", false);
        addHour8(1, "PJ","3.A","17", false);
        addHour6a(1, "PJ","3.A","17", false);

        addBreak0(1, "1. patro", false);
        addBreak1(1, "1. patro", false);
        addBreak2(1, "1. patro", false, marginBreak2, velVelkePrestavky/2);
        addBreak3(1, "1. patro", false);
        addBreak4(1, "1. patro", false);
        addBreak5(1, "1. patro", false);
        addBreak6(1, "1. patro", false);
        addBreak7(1, "1. patro", false);
        addBreak8(1, "1. patro", false);



        // -------- VYTVOŘ HODINY -----------

        createColumn(2);
        createBorder(2);

        addHeader(2, "Veronika\nNováková");

        //addHour0(2, "PJ","3.A","17", false);
        addHour1(2, "PRA","3.B","18", false);
        addHour2(2, "PJ","3.A","17", false);
        addHour3(2, "PRA","3.B","18", false);
        addHour4(2, "PJ","3.A","17", false);
        addHour5(2, "PRA","3.B","18", false);
        //addHour6(1, "PJ","3.A","17", false);
        addHour7(2, "PRA","3.B","18", false);
        addHour8(2, "PJ","3.A","17", false);
        //addHour6b(2, "PJ","3.A","17", false);


        //addBreak0(2, "1. patro", false);
        addBreak1(2, "1. patro", false);
        addBreak2(2, "1. patro", false, marginBreak2+velVelkePrestavky/2, velVelkePrestavky/2);
        addBreak3(2, "1. patro", false);
        addBreak4(2, "1. patro", false);
        addBreak5(2, "1. patro", false);
        addBreak6(2, "1. patro", false);
        addBreak7(2, "1. patro", false);
        addBreak8(2, "1. patro", false);

        addMorningSupervision(2, "1. patro");
        addSupervision6a(2, "1. patro");
        addSupervision6b(2, "Sut");

        createColumn(3);
        createBorder(3);

        addHeader(3, "Veronika\nNováková");

        //addHour0(3, "PJ","3.A","17", false);
        //addHour1(3, "PRA","3.B","18", false);
        addHour2(3, "PJ","3.A","17", false);
        addHour3(3, "PRA","3.B","18", false);
        addHour4(3, "PJ","3.A","17", false);
        addHour5(3, "PRA","3.B","18", false);
        //addHour6(1, "PJ","3.A","17", false);
        addHour7(3, "PRA","3.B","18", false);
        addHour8(3, "PJ","3.A","17", false);
        //addHour6b(3, "PJ","3.A","17", false);
       // addLunch(3, marginHour6);
        addLunch(3, marginLunch6middle);

        //addBreak0(3, "1. patro", false);
        addBreak1(3, "1. patro", false);
        addBreak2(3, "1. patro", false, marginBreak2, velVelkePrestavky);
        addBreak3(3, "1. patro", false);
        addBreak4(3, "1. patro", false);
        addBreak5(3, "1. patro", false);
        addBreak6(3, "1. patro", false);
        addBreak7(3, "1. patro", false);
        addBreak8(3, "1. patro", false);

        addVrat(3);
        addPPP(3);





*/





    }


    public void initViewElement(){

        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);



        linearLayoutsColumn = new LinearLayout[size];
        relativeLayoutsColumn = new RelativeLayout[size];


        linearLayoutsColumnCellHeader = new LinearLayout[size];
        cardViewHeader = new CardView[size];
        linearLayoutsInnerCardHeader = new LinearLayout[size];
        textViewsRowHeader = new TextView[size];

        linearLayoutsColumnCellHour0 = new LinearLayout[size];
        linearLayoutsColumnCellHour1 = new LinearLayout[size];
        linearLayoutsColumnCellHour2 = new LinearLayout[size];
        linearLayoutsColumnCellHour3 = new LinearLayout[size];
        linearLayoutsColumnCellHour4 = new LinearLayout[size];
        linearLayoutsColumnCellHour5 = new LinearLayout[size];
        linearLayoutsColumnCellHour6 = new LinearLayout[size];
        linearLayoutsColumnCellHour7 = new LinearLayout[size];
        linearLayoutsColumnCellHour8 = new LinearLayout[size];

        linearLayoutsColumnCellBreak0 = new LinearLayout[size];
        linearLayoutsColumnCellBreak1 = new LinearLayout[size];
        linearLayoutsColumnCellBreak2 = new LinearLayout[size];
        linearLayoutsColumnCellBreak3 = new LinearLayout[size];
        linearLayoutsColumnCellBreak4 = new LinearLayout[size];
        linearLayoutsColumnCellBreak5 = new LinearLayout[size];
        linearLayoutsColumnCellBreak6 = new LinearLayout[size];
        linearLayoutsColumnCellBreak7 = new LinearLayout[size];
        linearLayoutsColumnCellBreak8 = new LinearLayout[size];

        cardViewHour0 = new CardView[size];
        cardViewHour1 = new CardView[size];
        cardViewHour2 = new CardView[size];
        cardViewHour3 = new CardView[size];
        cardViewHour4 = new CardView[size];
        cardViewHour5 = new CardView[size];
        cardViewHour6 = new CardView[size];
        cardViewHour7 = new CardView[size];
        cardViewHour8 = new CardView[size];

        cardViewBreak0 = new CardView[size];
        cardViewBreak1 = new CardView[size];
        cardViewBreak2 = new CardView[size];
        cardViewBreak3 = new CardView[size];
        cardViewBreak4 = new CardView[size];
        cardViewBreak5 = new CardView[size];
        cardViewBreak6 = new CardView[size];
        cardViewBreak7 = new CardView[size];
        cardViewBreak8 = new CardView[size];

        linearLayoutsInnerCardHour0 = new LinearLayout[size];
        linearLayoutsInnerCardHour1 = new LinearLayout[size];
        linearLayoutsInnerCardHour2 = new LinearLayout[size];
        linearLayoutsInnerCardHour3 = new LinearLayout[size];
        linearLayoutsInnerCardHour4 = new LinearLayout[size];
        linearLayoutsInnerCardHour5 = new LinearLayout[size];
        linearLayoutsInnerCardHour6 = new LinearLayout[size];
        linearLayoutsInnerCardHour7 = new LinearLayout[size];
        linearLayoutsInnerCardHour8 = new LinearLayout[size];

        linearLayoutsInnerCardBreak0 = new LinearLayout[size];
        linearLayoutsInnerCardBreak1 = new LinearLayout[size];
        linearLayoutsInnerCardBreak2 = new LinearLayout[size];
        linearLayoutsInnerCardBreak3 = new LinearLayout[size];
        linearLayoutsInnerCardBreak4 = new LinearLayout[size];
        linearLayoutsInnerCardBreak5 = new LinearLayout[size];
        linearLayoutsInnerCardBreak6 = new LinearLayout[size];
        linearLayoutsInnerCardBreak7 = new LinearLayout[size];
        linearLayoutsInnerCardBreak8 = new LinearLayout[size];

        textViewsRow1Hour0 = new TextView[size];
        textViewsRow2Hour0 = new TextView[size];
        textViewsRow3Hour0 = new TextView[size];
        textViewsRow1Hour1 = new TextView[size];
        textViewsRow2Hour1 = new TextView[size];
        textViewsRow3Hour1 = new TextView[size];
        textViewsRow1Hour2 = new TextView[size];
        textViewsRow2Hour2 = new TextView[size];
        textViewsRow3Hour2 = new TextView[size];
        textViewsRow1Hour3 = new TextView[size];
        textViewsRow2Hour3 = new TextView[size];
        textViewsRow3Hour3 = new TextView[size];
        textViewsRow1Hour4 = new TextView[size];
        textViewsRow2Hour4 = new TextView[size];
        textViewsRow3Hour4 = new TextView[size];
        textViewsRow1Hour5 = new TextView[size];
        textViewsRow2Hour5 = new TextView[size];
        textViewsRow3Hour5 = new TextView[size];
        textViewsRow1Hour6 = new TextView[size];
        textViewsRow2Hour6 = new TextView[size];
        textViewsRow3Hour6 = new TextView[size];
        textViewsRow1Hour7 = new TextView[size];
        textViewsRow2Hour7 = new TextView[size];
        textViewsRow3Hour7 = new TextView[size];
        textViewsRow1Hour8 = new TextView[size];
        textViewsRow2Hour8 = new TextView[size];
        textViewsRow3Hour8 = new TextView[size];

        textViewsRowBreak0 = new TextView[size];
        textViewsRowBreak1 = new TextView[size];
        textViewsRowBreak2 = new TextView[size];
        textViewsRowBreak3 = new TextView[size];
        textViewsRowBreak4 = new TextView[size];
        textViewsRowBreak5 = new TextView[size];
        textViewsRowBreak6 = new TextView[size];
        textViewsRowBreak7 = new TextView[size];
        textViewsRowBreak8 = new TextView[size];

        linearLayoutsLineBorderHour0 = new LinearLayout[size];
        linearLayoutsLineBorderHour1 = new LinearLayout[size];
        linearLayoutsLineBorderHour2 = new LinearLayout[size];
        linearLayoutsLineBorderHour3 = new LinearLayout[size];
        linearLayoutsLineBorderHour4 = new LinearLayout[size];
        linearLayoutsLineBorderHour5 = new LinearLayout[size];
        linearLayoutsLineBorderHour6 = new LinearLayout[size];
        linearLayoutsLineBorderHour7 = new LinearLayout[size];
        linearLayoutsLineBorderHour8 = new LinearLayout[size];

        linearLayoutsLineBorderBreak0 = new LinearLayout[size];
        linearLayoutsLineBorderBreak1 = new LinearLayout[size];
        linearLayoutsLineBorderBreak2 = new LinearLayout[size];
        linearLayoutsLineBorderBreak3 = new LinearLayout[size];
        linearLayoutsLineBorderBreak4 = new LinearLayout[size];
        linearLayoutsLineBorderBreak5 = new LinearLayout[size];
        linearLayoutsLineBorderBreak6 = new LinearLayout[size];
        linearLayoutsLineBorderBreak7 = new LinearLayout[size];
        linearLayoutsLineBorderBreak8 = new LinearLayout[size];

        linearLayoutsColumnCellHour6a = new LinearLayout[size];
        cardViewHour6a = new CardView[size];
        linearLayoutsInnerCardHour6a = new LinearLayout[size];
        textViewsRow1Hour6a = new TextView[size];
        textViewsRow2Hour6a = new TextView[size];
        textViewsRow3Hour6a = new TextView[size];

        linearLayoutsColumnCellHour6b = new LinearLayout[size];
        cardViewHour6b = new CardView[size];
        linearLayoutsInnerCardHour6b = new LinearLayout[size];
        textViewsRow1Hour6b = new TextView[size];
        textViewsRow2Hour6b = new TextView[size];
        textViewsRow3Hour6b = new TextView[size];

        linearLayoutsColumnCellLunch = new LinearLayout[size];
        cardViewLunch = new CardView[size];
        linearLayoutsInnerCardLunch = new LinearLayout[size];
        textViewsRowLunch = new TextView[size];



        linearLayoutsColumnCellVrat = new LinearLayout[size];
        cardViewVrat = new CardView[size];
        linearLayoutsInnerCardVrat = new LinearLayout[size];
        textViewsRowVrat = new TextView[size];

        linearLayoutsColumnCellPPP = new LinearLayout[size];
        cardViewPPP = new CardView[size];
        linearLayoutsInnerCardPPP = new LinearLayout[size];
        textViewsRowPPP = new TextView[size];

        linearLayoutsColumnCellMorningSupervision = new LinearLayout[size];
        cardViewMorningSupervision = new CardView[size];
        linearLayoutsInnerCardMorningSupervision = new LinearLayout[size];
        textViewsRowMorningSupervision = new TextView[size];

        linearLayoutsColumnCellSupervision6a = new LinearLayout[size];
        cardViewSupervision6a = new CardView[size];
        linearLayoutsInnerCardSupervision6a = new LinearLayout[size];
        textViewsRowSupervision6a = new TextView[size];

        linearLayoutsColumnCellSupervision6b = new LinearLayout[size];
        cardViewSupervision6b = new CardView[size];
        linearLayoutsInnerCardSupervision6b = new LinearLayout[size];
        textViewsRowSupervision6b = new TextView[size];

        linearLayoutsColumnCellOther = new LinearLayout[size];
        cardViewOther = new CardView[size];
        linearLayoutsInnerCardOther = new LinearLayout[size];
        textViewsRowOther = new TextView[size];

    }


    public void createColumn(int columnID){

        LinearLayout.LayoutParams lp_columns = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams lp_rl_columns = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayoutsColumn[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsColumn[columnID].setLayoutParams(lp_columns);
        linearLayoutsColumn[columnID].setOrientation(LinearLayout.VERTICAL);

        if(columnID%2 == 0){

            linearLayoutsColumn[columnID].setBackgroundColor(Color.parseColor("#FAFAFA"));

        }

        relativeLayoutsColumn[columnID] = new RelativeLayout(getApplicationContext());
        relativeLayoutsColumn[columnID].setLayoutParams(lp_rl_columns);


        linearLayoutsColumn[columnID].addView(relativeLayoutsColumn[columnID]);
        linearLayoutMain.addView(linearLayoutsColumn[columnID]);

    }

    public void createBorder(int columnID){

        linearLayoutsLineBorderHour0[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour0.setMargins(0,marginHour0,0,0);
        linearLayoutsLineBorderHour0[columnID].setLayoutParams(lp_columnLineBorderHour0);
        linearLayoutsLineBorderHour0[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour0[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour1[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour1.setMargins(0,marginHour1,0,0);
        linearLayoutsLineBorderHour1[columnID].setLayoutParams(lp_columnLineBorderHour1);
        linearLayoutsLineBorderHour1[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour1[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour2[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour2.setMargins(0,marginHour2,0,0);
        linearLayoutsLineBorderHour2[columnID].setLayoutParams(lp_columnLineBorderHour2);
        linearLayoutsLineBorderHour2[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour2[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour3[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour3.setMargins(0,marginHour3,0,0);
        linearLayoutsLineBorderHour3[columnID].setLayoutParams(lp_columnLineBorderHour3);
        linearLayoutsLineBorderHour3[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour3[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour4[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour4.setMargins(0,marginHour4,0,0);
        linearLayoutsLineBorderHour4[columnID].setLayoutParams(lp_columnLineBorderHour4);
        linearLayoutsLineBorderHour4[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour4[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour5[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour5.setMargins(0,marginHour5,0,0);
        linearLayoutsLineBorderHour5[columnID].setLayoutParams(lp_columnLineBorderHour5);
        linearLayoutsLineBorderHour5[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour5[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour6[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour6 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour6.setMargins(0,marginHour6,0,0);
        linearLayoutsLineBorderHour6[columnID].setLayoutParams(lp_columnLineBorderHour6);
        linearLayoutsLineBorderHour6[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour6[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour7[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour7 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour7.setMargins(0,marginHour7,0,0);
        linearLayoutsLineBorderHour7[columnID].setLayoutParams(lp_columnLineBorderHour7);
        linearLayoutsLineBorderHour7[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour7[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderHour8[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour8 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour8.setMargins(0,marginHour8,0,0);
        linearLayoutsLineBorderHour8[columnID].setLayoutParams(lp_columnLineBorderHour8);
        linearLayoutsLineBorderHour8[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour8[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak0[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak0.setMargins(0,marginBreak0,0,0);
        linearLayoutsLineBorderBreak0[columnID].setLayoutParams(lp_columnLineBorderBreak0);
        linearLayoutsLineBorderBreak0[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak0[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak1[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak1.setMargins(0,marginBreak1,0,0);
        linearLayoutsLineBorderBreak1[columnID].setLayoutParams(lp_columnLineBorderBreak1);
        linearLayoutsLineBorderBreak1[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak1[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak2[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak2.setMargins(0,marginBreak2,0,0);
        linearLayoutsLineBorderBreak2[columnID].setLayoutParams(lp_columnLineBorderBreak2);
        linearLayoutsLineBorderBreak2[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak2[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak3[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak3.setMargins(0,marginBreak3,0,0);
        linearLayoutsLineBorderBreak3[columnID].setLayoutParams(lp_columnLineBorderBreak3);
        linearLayoutsLineBorderBreak3[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak3[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak4[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak4.setMargins(0,marginBreak4,0,0);
        linearLayoutsLineBorderBreak4[columnID].setLayoutParams(lp_columnLineBorderBreak4);
        linearLayoutsLineBorderBreak4[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak4[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak5[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak5.setMargins(0,marginBreak5,0,0);
        linearLayoutsLineBorderBreak5[columnID].setLayoutParams(lp_columnLineBorderBreak5);
        linearLayoutsLineBorderBreak5[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak5[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak6[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak6 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak6.setMargins(0,marginBreak6,0,0);
        linearLayoutsLineBorderBreak6[columnID].setLayoutParams(lp_columnLineBorderBreak6);
        linearLayoutsLineBorderBreak6[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak6[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak7[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak7 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak7.setMargins(0,marginBreak7,0,0);
        linearLayoutsLineBorderBreak7[columnID].setLayoutParams(lp_columnLineBorderBreak7);
        linearLayoutsLineBorderBreak7[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak7[columnID].setBackgroundColor(lineBorderColor);

        linearLayoutsLineBorderBreak8[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak8 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak8.setMargins(0,marginBreak8,0,0);
        linearLayoutsLineBorderBreak8[columnID].setLayoutParams(lp_columnLineBorderBreak8);
        linearLayoutsLineBorderBreak8[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak8[columnID].setBackgroundColor(lineBorderColor);

        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour0[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour1[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour2[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour3[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour4[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour5[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour6[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour7[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour8[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak0[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak1[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak2[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak3[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak4[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak5[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak6[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak7[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak8[columnID]);



    }

    public void addHeader(int columnID, String headerString){

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellHeader[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHeader = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velHeaderu);
        lp_column1CellHeader.setMargins(0,0,0,0);
        linearLayoutsColumnCellHeader[columnID].setLayoutParams(lp_column1CellHeader);
        linearLayoutsColumnCellHeader[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellHeader[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewHeader[columnID] = new CardView(getApplicationContext());
        cardViewHeader[columnID].setLayoutParams(lp_mainCard);
        cardViewHeader[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        cardViewHeader[columnID].setClickable(true);

        linearLayoutsInnerCardHeader[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHeader[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHeader[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowHeader[columnID] = new TextView(getApplicationContext());
        textViewsRowHeader[columnID].setLayoutParams(lp_match_match);
        textViewsRowHeader[columnID].setGravity(Gravity.CENTER);
        //textViewsRowHeader[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowHeader[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowHeader[columnID].setAllCaps(true);
        textViewsRowHeader[columnID].setTextSize(12);
        textViewsRowHeader[columnID].setText(headerString);

        linearLayoutsInnerCardHeader[columnID].addView(textViewsRowHeader[columnID]);
        cardViewHeader[columnID].addView(linearLayoutsInnerCardHeader[columnID]);
        linearLayoutsColumnCellHeader[columnID].addView(cardViewHeader[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHeader[columnID]);
    }

    public void addHour0(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour0[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour0.setMargins(0,marginHour0,0,0);
        linearLayoutsColumnCellHour0[columnID].setLayoutParams(lp_column1CellHour0);
        linearLayoutsColumnCellHour0[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour0[columnID] = new CardView(getApplicationContext());
        cardViewHour0[columnID].setLayoutParams(lp_mainCard);
        cardViewHour0[columnID].setClickable(true);

        linearLayoutsInnerCardHour0[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour0[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour0[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour0[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour0[columnID].setLayoutParams(lp3);
        textViewsRow1Hour0[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour0[columnID].setTextSize(18);
        textViewsRow1Hour0[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour0[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour0[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour0[columnID].setLayoutParams(lp4);
        textViewsRow2Hour0[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour0[columnID].setTextSize(13);
        textViewsRow2Hour0[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour0[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour0[columnID].setLayoutParams(lp4);
        textViewsRow3Hour0[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour0[columnID].setTextSize(12);
        textViewsRow3Hour0[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour0[columnID].addView(textViewsRow1Hour0[columnID]);
        linearLayoutsInnerCardHour0[columnID].addView(textViewsRow2Hour0[columnID]);
        linearLayoutsInnerCardHour0[columnID].addView(textViewsRow3Hour0[columnID]);
        cardViewHour0[columnID].addView(linearLayoutsInnerCardHour0[columnID]);
        linearLayoutsColumnCellHour0[columnID].addView(cardViewHour0[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour0[columnID]);

        if(isHeader){

            textViewsRow1Hour0[columnID].setText(Hour0Title);
            textViewsRow2Hour0[columnID].setText(Hour0From);
            textViewsRow3Hour0[columnID].setText(Hour0To);
            cardViewHour0[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour0[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour0[columnID].setText(hourObject.udalostPopis.split(" ")[0]);
            textViewsRow2Hour0[columnID].setText(hourObject.Trida);
            textViewsRow3Hour0[columnID].setText(hourObject.Ucebna);
/*
            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour0[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour0[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour0[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour0[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }*/

            cardViewHour0[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                 //   activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    /*

    public void addHour1(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour1[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour1.setMargins(0,marginHour1,0,0);
        linearLayoutsColumnCellHour1[columnID].setLayoutParams(lp_column1CellHour1);
        linearLayoutsColumnCellHour1[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour1[columnID] = new CardView(getApplicationContext());
        cardViewHour1[columnID].setLayoutParams(lp_mainCard);
        cardViewHour1[columnID].setClickable(true);

        linearLayoutsInnerCardHour1[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour1[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour1[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour1[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour1[columnID].setLayoutParams(lp3);
        textViewsRow1Hour1[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour1[columnID].setTextSize(18);
        textViewsRow1Hour1[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour1[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour1[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour1[columnID].setLayoutParams(lp4);
        textViewsRow2Hour1[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour1[columnID].setTextSize(13);
        textViewsRow2Hour1[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour1[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour1[columnID].setLayoutParams(lp4);
        textViewsRow3Hour1[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour1[columnID].setTextSize(12);
        textViewsRow3Hour1[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour1[columnID].addView(textViewsRow1Hour1[columnID]);
        linearLayoutsInnerCardHour1[columnID].addView(textViewsRow2Hour1[columnID]);
        linearLayoutsInnerCardHour1[columnID].addView(textViewsRow3Hour1[columnID]);
        cardViewHour1[columnID].addView(linearLayoutsInnerCardHour1[columnID]);
        linearLayoutsColumnCellHour1[columnID].addView(cardViewHour1[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour1[columnID]);

        if(isHeader){

            textViewsRow1Hour1[columnID].setText(Hour1Title);
            textViewsRow2Hour1[columnID].setText(Hour1From);
            textViewsRow3Hour1[columnID].setText(Hour1To);
            cardViewHour1[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour1[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour1[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour1[columnID].setText(hourObject.Trida);
            textViewsRow3Hour1[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour1[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour1[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour1[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour1[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour1[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null,"lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour2(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour2[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour2.setMargins(0,marginHour2,0,0);
        linearLayoutsColumnCellHour2[columnID].setLayoutParams(lp_column1CellHour2);
        linearLayoutsColumnCellHour2[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour2[columnID] = new CardView(getApplicationContext());
        cardViewHour2[columnID].setLayoutParams(lp_mainCard);
        cardViewHour2[columnID].setClickable(true);

        linearLayoutsInnerCardHour2[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour2[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour2[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour2[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour2[columnID].setLayoutParams(lp3);
        textViewsRow1Hour2[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour2[columnID].setTextSize(18);
        textViewsRow1Hour2[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour2[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour2[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour2[columnID].setLayoutParams(lp4);
        textViewsRow2Hour2[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour2[columnID].setTextSize(13);
        textViewsRow2Hour2[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour2[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour2[columnID].setLayoutParams(lp4);
        textViewsRow3Hour2[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour2[columnID].setTextSize(12);
        textViewsRow3Hour2[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour2[columnID].addView(textViewsRow1Hour2[columnID]);
        linearLayoutsInnerCardHour2[columnID].addView(textViewsRow2Hour2[columnID]);
        linearLayoutsInnerCardHour2[columnID].addView(textViewsRow3Hour2[columnID]);
        cardViewHour2[columnID].addView(linearLayoutsInnerCardHour2[columnID]);
        linearLayoutsColumnCellHour2[columnID].addView(cardViewHour2[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour2[columnID]);

        if(isHeader){

            textViewsRow1Hour2[columnID].setText(Hour2Title);
            textViewsRow2Hour2[columnID].setText(Hour2From);
            textViewsRow3Hour2[columnID].setText(Hour2To);
            cardViewHour2[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour2[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour2[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour2[columnID].setText(hourObject.Trida);
            textViewsRow3Hour2[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour2[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour2[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour2[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour2[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour2[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour3(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour3[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour3.setMargins(0,marginHour3,0,0);
        linearLayoutsColumnCellHour3[columnID].setLayoutParams(lp_column1CellHour3);
        linearLayoutsColumnCellHour3[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour3[columnID] = new CardView(getApplicationContext());
        cardViewHour3[columnID].setLayoutParams(lp_mainCard);
        cardViewHour3[columnID].setClickable(true);

        linearLayoutsInnerCardHour3[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour3[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour3[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour3[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour3[columnID].setLayoutParams(lp3);
        textViewsRow1Hour3[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour3[columnID].setTextSize(18);
        textViewsRow1Hour3[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour3[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour3[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour3[columnID].setLayoutParams(lp4);
        textViewsRow2Hour3[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour3[columnID].setTextSize(13);
        textViewsRow2Hour3[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour3[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour3[columnID].setLayoutParams(lp4);
        textViewsRow3Hour3[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour3[columnID].setTextSize(12);
        textViewsRow3Hour3[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour3[columnID].addView(textViewsRow1Hour3[columnID]);
        linearLayoutsInnerCardHour3[columnID].addView(textViewsRow2Hour3[columnID]);
        linearLayoutsInnerCardHour3[columnID].addView(textViewsRow3Hour3[columnID]);
        cardViewHour3[columnID].addView(linearLayoutsInnerCardHour3[columnID]);
        linearLayoutsColumnCellHour3[columnID].addView(cardViewHour3[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour3[columnID]);

        if(isHeader){

            textViewsRow1Hour3[columnID].setText(Hour3Title);
            textViewsRow2Hour3[columnID].setText(Hour3From);
            textViewsRow3Hour3[columnID].setText(Hour3To);
            cardViewHour3[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour3[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour3[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour3[columnID].setText(hourObject.Trida);
            textViewsRow3Hour3[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour3[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour3[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour3[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour3[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour3[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour4(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour4[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour4.setMargins(0,marginHour4,0,0);
        linearLayoutsColumnCellHour4[columnID].setLayoutParams(lp_column1CellHour4);
        linearLayoutsColumnCellHour4[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour4[columnID] = new CardView(getApplicationContext());
        cardViewHour4[columnID].setLayoutParams(lp_mainCard);
        cardViewHour4[columnID].setClickable(true);

        linearLayoutsInnerCardHour4[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour4[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour4[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour4[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour4[columnID].setLayoutParams(lp3);
        textViewsRow1Hour4[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour4[columnID].setTextSize(18);
        textViewsRow1Hour4[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour4[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour4[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour4[columnID].setLayoutParams(lp4);
        textViewsRow2Hour4[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour4[columnID].setTextSize(13);
        textViewsRow2Hour4[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour4[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour4[columnID].setLayoutParams(lp4);
        textViewsRow3Hour4[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour4[columnID].setTextSize(12);
        textViewsRow3Hour4[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour4[columnID].addView(textViewsRow1Hour4[columnID]);
        linearLayoutsInnerCardHour4[columnID].addView(textViewsRow2Hour4[columnID]);
        linearLayoutsInnerCardHour4[columnID].addView(textViewsRow3Hour4[columnID]);
        cardViewHour4[columnID].addView(linearLayoutsInnerCardHour4[columnID]);
        linearLayoutsColumnCellHour4[columnID].addView(cardViewHour4[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour4[columnID]);

        if(isHeader){

            textViewsRow1Hour4[columnID].setText(Hour4Title);
            textViewsRow2Hour4[columnID].setText(Hour4From);
            textViewsRow3Hour4[columnID].setText(Hour4To);
            cardViewHour4[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour4[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour4[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour4[columnID].setText(hourObject.Trida);
            textViewsRow3Hour4[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour4[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour4[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour4[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour4[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour4[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour5(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour5[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour5.setMargins(0,marginHour5,0,0);
        linearLayoutsColumnCellHour5[columnID].setLayoutParams(lp_column1CellHour5);
        linearLayoutsColumnCellHour5[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour5[columnID] = new CardView(getApplicationContext());
        cardViewHour5[columnID].setLayoutParams(lp_mainCard);
        cardViewHour5[columnID].setClickable(true);

        linearLayoutsInnerCardHour5[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour5[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour5[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour5[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour5[columnID].setLayoutParams(lp3);
        textViewsRow1Hour5[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour5[columnID].setTextSize(18);
        textViewsRow1Hour5[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour5[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour5[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour5[columnID].setLayoutParams(lp4);
        textViewsRow2Hour5[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour5[columnID].setTextSize(13);
        textViewsRow2Hour5[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour5[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour5[columnID].setLayoutParams(lp4);
        textViewsRow3Hour5[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour5[columnID].setTextSize(12);
        textViewsRow3Hour5[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour5[columnID].addView(textViewsRow1Hour5[columnID]);
        linearLayoutsInnerCardHour5[columnID].addView(textViewsRow2Hour5[columnID]);
        linearLayoutsInnerCardHour5[columnID].addView(textViewsRow3Hour5[columnID]);
        cardViewHour5[columnID].addView(linearLayoutsInnerCardHour5[columnID]);
        linearLayoutsColumnCellHour5[columnID].addView(cardViewHour5[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour5[columnID]);

        if(isHeader){

            textViewsRow1Hour5[columnID].setText(Hour5Title);
            textViewsRow2Hour5[columnID].setText(Hour5From);
            textViewsRow3Hour5[columnID].setText(Hour5To);
            cardViewHour5[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour5[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour5[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour5[columnID].setText(hourObject.Trida);
            textViewsRow3Hour5[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour5[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour5[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour5[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour5[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour5[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour6(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour6[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour6 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velSesteHod);
        lp_column1CellHour6.setMargins(0,marginHour6,0,0);
        linearLayoutsColumnCellHour6[columnID].setLayoutParams(lp_column1CellHour6);
        linearLayoutsColumnCellHour6[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour6[columnID] = new CardView(getApplicationContext());
        cardViewHour6[columnID].setLayoutParams(lp_mainCard);
        cardViewHour6[columnID].setClickable(true);

        linearLayoutsInnerCardHour6[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour6[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour6[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour6[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour6[columnID].setLayoutParams(lp3);
        textViewsRow1Hour6[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour6[columnID].setTextSize(18);
        textViewsRow1Hour6[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour6[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour6[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour6[columnID].setLayoutParams(lp4);
        textViewsRow2Hour6[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour6[columnID].setTextSize(13);
        textViewsRow2Hour6[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour6[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour6[columnID].setLayoutParams(lp4);
        textViewsRow3Hour6[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour6[columnID].setTextSize(12);
        textViewsRow3Hour6[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour6[columnID].addView(textViewsRow1Hour6[columnID]);
        linearLayoutsInnerCardHour6[columnID].addView(textViewsRow2Hour6[columnID]);
        linearLayoutsInnerCardHour6[columnID].addView(textViewsRow3Hour6[columnID]);
        cardViewHour6[columnID].addView(linearLayoutsInnerCardHour6[columnID]);
        linearLayoutsColumnCellHour6[columnID].addView(cardViewHour6[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour6[columnID]);

        if(isHeader){

            textViewsRow1Hour6[columnID].setText(Hour6Title);
            textViewsRow2Hour6[columnID].setText(Hour6From);
            textViewsRow3Hour6[columnID].setText(Hour6To);
            cardViewHour6[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour6[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour6[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour6[columnID].setText(hourObject.Trida);
            textViewsRow3Hour6[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour6[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour6[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour6[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour6[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour6[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour7(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour7[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour7 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour7.setMargins(0,marginHour7,0,0);
        linearLayoutsColumnCellHour7[columnID].setLayoutParams(lp_column1CellHour7);
        linearLayoutsColumnCellHour7[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour7[columnID] = new CardView(getApplicationContext());
        cardViewHour7[columnID].setLayoutParams(lp_mainCard);
        cardViewHour7[columnID].setClickable(true);

        linearLayoutsInnerCardHour7[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour7[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour7[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour7[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour7[columnID].setLayoutParams(lp3);
        textViewsRow1Hour7[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour7[columnID].setTextSize(18);
        textViewsRow1Hour7[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour7[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour7[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour7[columnID].setLayoutParams(lp4);
        textViewsRow2Hour7[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour7[columnID].setTextSize(13);
        textViewsRow2Hour7[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour7[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour7[columnID].setLayoutParams(lp4);
        textViewsRow3Hour7[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour7[columnID].setTextSize(12);
        textViewsRow3Hour7[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour7[columnID].addView(textViewsRow1Hour7[columnID]);
        linearLayoutsInnerCardHour7[columnID].addView(textViewsRow2Hour7[columnID]);
        linearLayoutsInnerCardHour7[columnID].addView(textViewsRow3Hour7[columnID]);
        cardViewHour7[columnID].addView(linearLayoutsInnerCardHour7[columnID]);
        linearLayoutsColumnCellHour7[columnID].addView(cardViewHour7[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour7[columnID]);

        if(isHeader){

            textViewsRow1Hour7[columnID].setText(Hour7Title);
            textViewsRow2Hour7[columnID].setText(Hour7From);
            textViewsRow3Hour7[columnID].setText(Hour7To);
            cardViewHour7[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour7[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour7[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour7[columnID].setText(hourObject.Trida);
            textViewsRow3Hour7[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour7[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour7[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour7[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour7[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour7[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour8(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour8[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour8 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour8.setMargins(0,marginHour8,0,0);
        linearLayoutsColumnCellHour8[columnID].setLayoutParams(lp_column1CellHour8);
        linearLayoutsColumnCellHour8[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour8[columnID] = new CardView(getApplicationContext());
        cardViewHour8[columnID].setLayoutParams(lp_mainCard);
        cardViewHour8[columnID].setClickable(true);

        linearLayoutsInnerCardHour8[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour8[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour8[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour8[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour8[columnID].setLayoutParams(lp3);
        textViewsRow1Hour8[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour8[columnID].setTextSize(18);
        textViewsRow1Hour8[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour8[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour8[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour8[columnID].setLayoutParams(lp4);
        textViewsRow2Hour8[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour8[columnID].setTextSize(13);
        textViewsRow2Hour8[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour8[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour8[columnID].setLayoutParams(lp4);
        textViewsRow3Hour8[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour8[columnID].setTextSize(12);
        textViewsRow3Hour8[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour8[columnID].addView(textViewsRow1Hour8[columnID]);
        linearLayoutsInnerCardHour8[columnID].addView(textViewsRow2Hour8[columnID]);
        linearLayoutsInnerCardHour8[columnID].addView(textViewsRow3Hour8[columnID]);
        cardViewHour8[columnID].addView(linearLayoutsInnerCardHour8[columnID]);
        linearLayoutsColumnCellHour8[columnID].addView(cardViewHour8[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour8[columnID]);

        if(isHeader){

            textViewsRow1Hour8[columnID].setText(Hour8Title);
            textViewsRow2Hour8[columnID].setText(Hour8From);
            textViewsRow3Hour8[columnID].setText(Hour8To);
            cardViewHour8[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour8[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour8[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour8[columnID].setText(hourObject.Trida);
            textViewsRow3Hour8[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour8[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour8[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour8[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour8[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour8[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour6a(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour6a[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour6a = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour6a.setMargins(0,marginHour6,0,0);
        linearLayoutsColumnCellHour6a[columnID].setLayoutParams(lp_column1CellHour6a);
        linearLayoutsColumnCellHour6a[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour6a[columnID] = new CardView(getApplicationContext());
        cardViewHour6a[columnID].setLayoutParams(lp_mainCard);
        cardViewHour6a[columnID].setClickable(true);

        linearLayoutsInnerCardHour6a[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour6a[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour6a[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour6a[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour6a[columnID].setLayoutParams(lp3);
        textViewsRow1Hour6a[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour6a[columnID].setTextSize(18);
        textViewsRow1Hour6a[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour6a[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour6a[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour6a[columnID].setLayoutParams(lp4);
        textViewsRow2Hour6a[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour6a[columnID].setTextSize(13);
        textViewsRow2Hour6a[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour6a[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour6a[columnID].setLayoutParams(lp4);
        textViewsRow3Hour6a[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour6a[columnID].setTextSize(12);
        textViewsRow3Hour6a[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour6a[columnID].addView(textViewsRow1Hour6a[columnID]);
        linearLayoutsInnerCardHour6a[columnID].addView(textViewsRow2Hour6a[columnID]);
        linearLayoutsInnerCardHour6a[columnID].addView(textViewsRow3Hour6a[columnID]);
        cardViewHour6a[columnID].addView(linearLayoutsInnerCardHour6a[columnID]);
        linearLayoutsColumnCellHour6a[columnID].addView(cardViewHour6a[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour6a[columnID]);

        if(isHeader){

            textViewsRow1Hour6a[columnID].setText(Hour6Title);
            textViewsRow2Hour6a[columnID].setText(Hour6From);
            textViewsRow3Hour6a[columnID].setText(Hour6To);
            cardViewHour6a[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour6a[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour6a[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour6a[columnID].setText(hourObject.Trida);
            textViewsRow3Hour6a[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour6a[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour6a[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour6a[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour6a[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour6a[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addHour6b(int columnID, boolean isHeader, HourObject hourObject){

        linearLayoutsColumnCellHour6b[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour6b = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod);
        lp_column1CellHour6b.setMargins(0,marginHour6+(velSesteHod-velKlasickeHod),0,0);
        linearLayoutsColumnCellHour6b[columnID].setLayoutParams(lp_column1CellHour6b);
        linearLayoutsColumnCellHour6b[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewHour6b[columnID] = new CardView(getApplicationContext());
        cardViewHour6b[columnID].setLayoutParams(lp_mainCard);
        cardViewHour6b[columnID].setClickable(true);

        linearLayoutsInnerCardHour6b[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour6b[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour6b[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRow1Hour6b[columnID] = new TextView(getApplicationContext());
        textViewsRow1Hour6b[columnID].setLayoutParams(lp3);
        textViewsRow1Hour6b[columnID].setGravity(Gravity.CENTER);
        textViewsRow1Hour6b[columnID].setTextSize(18);
        textViewsRow1Hour6b[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour6b[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour6b[columnID] = new TextView(getApplicationContext());
        textViewsRow2Hour6b[columnID].setLayoutParams(lp4);
        textViewsRow2Hour6b[columnID].setGravity(Gravity.CENTER);
        textViewsRow2Hour6b[columnID].setTextSize(13);
        textViewsRow2Hour6b[columnID].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour6b[columnID] = new TextView(getApplicationContext());
        textViewsRow3Hour6b[columnID].setLayoutParams(lp4);
        textViewsRow3Hour6b[columnID].setGravity(Gravity.CENTER);
        textViewsRow3Hour6b[columnID].setTextSize(12);
        textViewsRow3Hour6b[columnID].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardHour6b[columnID].addView(textViewsRow1Hour6b[columnID]);
        linearLayoutsInnerCardHour6b[columnID].addView(textViewsRow2Hour6b[columnID]);
        linearLayoutsInnerCardHour6b[columnID].addView(textViewsRow3Hour6b[columnID]);
        cardViewHour6b[columnID].addView(linearLayoutsInnerCardHour6b[columnID]);
        linearLayoutsColumnCellHour6b[columnID].addView(cardViewHour6b[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour6b[columnID]);

        if(isHeader){

            textViewsRow1Hour6b[columnID].setText(Hour6Title);
            textViewsRow2Hour6b[columnID].setText(Hour6From);
            textViewsRow3Hour6b[columnID].setText(Hour6To);
            cardViewHour6b[columnID].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            textViewsRow1Hour6b[columnID].setTextSize(16);

        }else{

            textViewsRow1Hour6b[columnID].setText(hourObject.Predmet.split(" ")[0]);
            textViewsRow2Hour6b[columnID].setText(hourObject.Trida);
            textViewsRow3Hour6b[columnID].setText(hourObject.Ucebna);

            if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewHour6b[columnID].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewHour6b[columnID].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewHour6b[columnID].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewHour6b[columnID].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }

            cardViewHour6b[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }










    public void addBreak0(int columnID, boolean isHeader, SupervisionObject supervisionObject){
        
        linearLayoutsColumnCellBreak0[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak0.setMargins(0,marginBreak0,0,0);
        linearLayoutsColumnCellBreak0[columnID].setLayoutParams(lp_column1CellBreak0);
        linearLayoutsColumnCellBreak0[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak0[columnID] = new CardView(getApplicationContext());
        cardViewBreak0[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak0[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak0[columnID].setClickable(true);

        linearLayoutsInnerCardBreak0[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak0[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak0[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak0[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak0[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak0[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak0[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak0[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak0[columnID].setAllCaps(true);
        textViewsRowBreak0[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak0[columnID].addView(textViewsRowBreak0[columnID]);
        cardViewBreak0[columnID].addView(linearLayoutsInnerCardBreak0[columnID]);
        linearLayoutsColumnCellBreak0[columnID].addView(cardViewBreak0[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak0[columnID]);

        if(isHeader){

            textViewsRowBreak0[columnID].setText("5 MIN");
            cardViewBreak0[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak0[columnID].setText(supervisionObject.Location.split(" - ")[0]);
            
            cardViewBreak0[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak1(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak1[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velStredniPrestavky);
        lp_column1CellBreak1.setMargins(0,marginBreak1,0,0);
        linearLayoutsColumnCellBreak1[columnID].setLayoutParams(lp_column1CellBreak1);
        linearLayoutsColumnCellBreak1[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak1[columnID] = new CardView(getApplicationContext());
        cardViewBreak1[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak1[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak1[columnID].setClickable(true);

        linearLayoutsInnerCardBreak1[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak1[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak1[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak1[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak1[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak1[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak1[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak1[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak1[columnID].setAllCaps(true);
        textViewsRowBreak1[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak1[columnID].addView(textViewsRowBreak1[columnID]);
        cardViewBreak1[columnID].addView(linearLayoutsInnerCardBreak1[columnID]);
        linearLayoutsColumnCellBreak1[columnID].addView(cardViewBreak1[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak1[columnID]);

        if(isHeader){

            textViewsRowBreak1[columnID].setText("10 MIN");
            cardViewBreak1[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak1[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak1[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak2(int columnID, boolean isHeader, SupervisionObject supervisionObject, int position, int height){

        linearLayoutsColumnCellBreak2[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                height);
        lp_column1CellBreak2.setMargins(0,position,0,0);
        linearLayoutsColumnCellBreak2[columnID].setLayoutParams(lp_column1CellBreak2);
        linearLayoutsColumnCellBreak2[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak2[columnID] = new CardView(getApplicationContext());
        cardViewBreak2[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak2[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak2[columnID].setClickable(true);

        linearLayoutsInnerCardBreak2[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak2[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak2[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak2[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak2[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak2[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak2[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak2[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak2[columnID].setAllCaps(true);
        textViewsRowBreak2[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak2[columnID].addView(textViewsRowBreak2[columnID]);
        cardViewBreak2[columnID].addView(linearLayoutsInnerCardBreak2[columnID]);
        linearLayoutsColumnCellBreak2[columnID].addView(cardViewBreak2[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak2[columnID]);

        if(isHeader){

            textViewsRowBreak2[columnID].setText("20 MIN");
            cardViewBreak2[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak2[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak2[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak3(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak3[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak3.setMargins(0,marginBreak3,0,0);
        linearLayoutsColumnCellBreak3[columnID].setLayoutParams(lp_column1CellBreak3);
        linearLayoutsColumnCellBreak3[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak3[columnID] = new CardView(getApplicationContext());
        cardViewBreak3[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak3[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak3[columnID].setClickable(true);

        linearLayoutsInnerCardBreak3[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak3[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak3[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak3[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak3[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak3[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak3[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak3[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak3[columnID].setAllCaps(true);
        textViewsRowBreak3[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak3[columnID].addView(textViewsRowBreak3[columnID]);
        cardViewBreak3[columnID].addView(linearLayoutsInnerCardBreak3[columnID]);
        linearLayoutsColumnCellBreak3[columnID].addView(cardViewBreak3[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak3[columnID]);

        if(isHeader){

            textViewsRowBreak3[columnID].setText("5 MIN");
            cardViewBreak3[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak3[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak3[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak4(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak4[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak4.setMargins(0,marginBreak4,0,0);
        linearLayoutsColumnCellBreak4[columnID].setLayoutParams(lp_column1CellBreak4);
        linearLayoutsColumnCellBreak4[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak4[columnID] = new CardView(getApplicationContext());
        cardViewBreak4[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak4[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak4[columnID].setClickable(true);

        linearLayoutsInnerCardBreak4[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak4[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak4[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak4[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak4[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak4[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak4[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak4[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak4[columnID].setAllCaps(true);
        textViewsRowBreak4[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak4[columnID].addView(textViewsRowBreak4[columnID]);
        cardViewBreak4[columnID].addView(linearLayoutsInnerCardBreak4[columnID]);
        linearLayoutsColumnCellBreak4[columnID].addView(cardViewBreak4[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak4[columnID]);

        if(isHeader){

            textViewsRowBreak4[columnID].setText("5 MIN");
            cardViewBreak4[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak4[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak4[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak5(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak5[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak5.setMargins(0,marginBreak5,0,0);
        linearLayoutsColumnCellBreak5[columnID].setLayoutParams(lp_column1CellBreak5);
        linearLayoutsColumnCellBreak5[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak5[columnID] = new CardView(getApplicationContext());
        cardViewBreak5[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak5[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak5[columnID].setClickable(true);

        linearLayoutsInnerCardBreak5[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak5[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak5[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak5[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak5[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak5[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak5[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak5[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak5[columnID].setAllCaps(true);
        textViewsRowBreak5[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak5[columnID].addView(textViewsRowBreak5[columnID]);
        cardViewBreak5[columnID].addView(linearLayoutsInnerCardBreak5[columnID]);
        linearLayoutsColumnCellBreak5[columnID].addView(cardViewBreak5[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak5[columnID]);

        if(isHeader){

            textViewsRowBreak5[columnID].setText("5 MIN");
            cardViewBreak5[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak5[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak5[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak6(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak6[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak6 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak6.setMargins(0,marginBreak6,0,0);
        linearLayoutsColumnCellBreak6[columnID].setLayoutParams(lp_column1CellBreak6);
        linearLayoutsColumnCellBreak6[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak6[columnID] = new CardView(getApplicationContext());
        cardViewBreak6[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak6[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak6[columnID].setClickable(true);

        linearLayoutsInnerCardBreak6[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak6[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak6[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak6[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak6[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak6[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak6[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak6[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak6[columnID].setAllCaps(true);
        textViewsRowBreak6[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak6[columnID].addView(textViewsRowBreak6[columnID]);
        cardViewBreak6[columnID].addView(linearLayoutsInnerCardBreak6[columnID]);
        linearLayoutsColumnCellBreak6[columnID].addView(cardViewBreak6[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak6[columnID]);

        if(isHeader){

            textViewsRowBreak6[columnID].setText("5 MIN");
            cardViewBreak6[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak6[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak6[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak7(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak7[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak7 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak7.setMargins(0,marginBreak7,0,0);
        linearLayoutsColumnCellBreak7[columnID].setLayoutParams(lp_column1CellBreak7);
        linearLayoutsColumnCellBreak7[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak7[columnID] = new CardView(getApplicationContext());
        cardViewBreak7[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak7[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak7[columnID].setClickable(true);

        linearLayoutsInnerCardBreak7[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak7[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak7[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak7[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak7[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak7[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak7[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak7[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak7[columnID].setAllCaps(true);
        textViewsRowBreak7[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak7[columnID].addView(textViewsRowBreak7[columnID]);
        cardViewBreak7[columnID].addView(linearLayoutsInnerCardBreak7[columnID]);
        linearLayoutsColumnCellBreak7[columnID].addView(cardViewBreak7[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak7[columnID]);

        if(isHeader){

            textViewsRowBreak7[columnID].setText("5 MIN");
            cardViewBreak7[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak7[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak7[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }

    public void addBreak8(int columnID, boolean isHeader, SupervisionObject supervisionObject){

        linearLayoutsColumnCellBreak8[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak8 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMalePrestavky);
        lp_column1CellBreak8.setMargins(0,marginBreak8,0,0);
        linearLayoutsColumnCellBreak8[columnID].setLayoutParams(lp_column1CellBreak8);
        linearLayoutsColumnCellBreak8[columnID].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak8[columnID] = new CardView(getApplicationContext());
        cardViewBreak8[columnID].setLayoutParams(lp_mainCard);
        cardViewBreak8[columnID].setCardBackgroundColor(Color.parseColor("#FBE9E7"));
        cardViewBreak8[columnID].setClickable(true);

        linearLayoutsInnerCardBreak8[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak8[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak8[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak8[columnID] = new TextView(getApplicationContext());
        textViewsRowBreak8[columnID].setLayoutParams(lp_match_match);
        textViewsRowBreak8[columnID].setGravity(Gravity.CENTER);
        //textViewsRowBreak8[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak8[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak8[columnID].setAllCaps(true);
        textViewsRowBreak8[columnID].setTextSize(12);

        linearLayoutsInnerCardBreak8[columnID].addView(textViewsRowBreak8[columnID]);
        cardViewBreak8[columnID].addView(linearLayoutsInnerCardBreak8[columnID]);
        linearLayoutsColumnCellBreak8[columnID].addView(cardViewBreak8[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak8[columnID]);

        if(isHeader){

            textViewsRowBreak8[columnID].setText("5 MIN");
            cardViewBreak8[columnID].setCardBackgroundColor(Color.parseColor("#CFD8DC"));

        }else{

            textViewsRowBreak8[columnID].setText(supervisionObject.Location.split(" - ")[0]);

            cardViewBreak8[columnID].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, "supervision");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });
        }
    }























    public void addLunch(int columnID, int position){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellLunch[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellLunch = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velSesteHod-velKlasickeHod);
        lp_column1CellLunch.setMargins(0,position,0,0);
        linearLayoutsColumnCellLunch[columnID].setLayoutParams(lp_column1CellLunch);
        linearLayoutsColumnCellLunch[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellLunch[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewLunch[columnID] = new CardView(getApplicationContext());
        cardViewLunch[columnID].setLayoutParams(lp_mainCard);
        cardViewLunch[columnID].setCardBackgroundColor(Color.parseColor("#E6EE9C"));
        cardViewLunch[columnID].setClickable(true);
        cardViewLunch[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardLunch[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardLunch[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardLunch[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowLunch[columnID] = new TextView(getApplicationContext());
        textViewsRowLunch[columnID].setLayoutParams(lp_match_match);
        textViewsRowLunch[columnID].setGravity(Gravity.CENTER);
        //textViewsRowLunch[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowLunch[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowLunch[columnID].setAllCaps(true);
        textViewsRowLunch[columnID].setTextSize(12);
        textViewsRowLunch[columnID].setText("OBĚD");

        linearLayoutsInnerCardLunch[columnID].addView(textViewsRowLunch[columnID]);
        cardViewLunch[columnID].addView(linearLayoutsInnerCardLunch[columnID]);
        linearLayoutsColumnCellLunch[columnID].addView(cardViewLunch[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellLunch[columnID]);
    }

    public void addVrat(int columnID){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellVrat[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellVrat = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velVrat);
        lp_column1CellVrat.setMargins(0,marginHour0 + velKlasickeHod-velVrat,0,0);
        linearLayoutsColumnCellVrat[columnID].setLayoutParams(lp_column1CellVrat);
        linearLayoutsColumnCellVrat[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellVrat[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewVrat[columnID] = new CardView(getApplicationContext());
        cardViewVrat[columnID].setLayoutParams(lp_mainCard);
        cardViewVrat[columnID].setCardBackgroundColor(Color.parseColor("#B2DFDB"));
        cardViewVrat[columnID].setClickable(true);
        cardViewVrat[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardVrat[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardVrat[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardVrat[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowVrat[columnID] = new TextView(getApplicationContext());
        textViewsRowVrat[columnID].setLayoutParams(lp_match_match);
        textViewsRowVrat[columnID].setGravity(Gravity.CENTER);
        //textViewsRowVrat[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowVrat[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowVrat[columnID].setAllCaps(true);
        textViewsRowVrat[columnID].setTextSize(12);
        textViewsRowVrat[columnID].setText("VRÁT");

        linearLayoutsInnerCardVrat[columnID].addView(textViewsRowVrat[columnID]);
        cardViewVrat[columnID].addView(linearLayoutsInnerCardVrat[columnID]);
        linearLayoutsColumnCellVrat[columnID].addView(cardViewVrat[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellVrat[columnID]);
    }

    public void addPPP(int columnID){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellPPP[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellPPP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velKlasickeHod+velMalePrestavky);
        lp_column1CellPPP.setMargins(0,marginBreak0,0,0);
        linearLayoutsColumnCellPPP[columnID].setLayoutParams(lp_column1CellPPP);
        linearLayoutsColumnCellPPP[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellPPP[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewPPP[columnID] = new CardView(getApplicationContext());
        cardViewPPP[columnID].setLayoutParams(lp_mainCard);
        cardViewPPP[columnID].setCardBackgroundColor(Color.parseColor("#F8BBD0"));
        cardViewPPP[columnID].setClickable(true);
        cardViewPPP[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardPPP[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardPPP[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardPPP[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowPPP[columnID] = new TextView(getApplicationContext());
        textViewsRowPPP[columnID].setLayoutParams(lp_match_match);
        textViewsRowPPP[columnID].setGravity(Gravity.CENTER);
        //textViewsRowPPP[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowPPP[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowPPP[columnID].setAllCaps(true);
        textViewsRowPPP[columnID].setTextSize(12);
        textViewsRowPPP[columnID].setText("PPP");

        linearLayoutsInnerCardPPP[columnID].addView(textViewsRowPPP[columnID]);
        cardViewPPP[columnID].addView(linearLayoutsInnerCardPPP[columnID]);
        linearLayoutsColumnCellPPP[columnID].addView(cardViewPPP[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellPPP[columnID]);
    }

    public void addMorningSupervision(int columnID, String textRow){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellMorningSupervision[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellMorningSupervision = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velMorningSupervision+velMalePrestavky);
        lp_column1CellMorningSupervision.setMargins(0,marginHour0 + velKlasickeHod-velMorningSupervision,0,0);
        linearLayoutsColumnCellMorningSupervision[columnID].setLayoutParams(lp_column1CellMorningSupervision);
        linearLayoutsColumnCellMorningSupervision[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellMorningSupervision[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewMorningSupervision[columnID] = new CardView(getApplicationContext());
        cardViewMorningSupervision[columnID].setLayoutParams(lp_mainCard);
        cardViewMorningSupervision[columnID].setCardBackgroundColor(Color.parseColor("#FFF9C4"));
        cardViewMorningSupervision[columnID].setClickable(true);
        cardViewMorningSupervision[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardMorningSupervision[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardMorningSupervision[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardMorningSupervision[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowMorningSupervision[columnID] = new TextView(getApplicationContext());
        textViewsRowMorningSupervision[columnID].setLayoutParams(lp_match_match);
        textViewsRowMorningSupervision[columnID].setGravity(Gravity.CENTER);
        //textViewsRowMorningSupervision[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowMorningSupervision[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowMorningSupervision[columnID].setAllCaps(true);
        textViewsRowMorningSupervision[columnID].setTextSize(12);
        textViewsRowMorningSupervision[columnID].setText(textRow);

        linearLayoutsInnerCardMorningSupervision[columnID].addView(textViewsRowMorningSupervision[columnID]);
        cardViewMorningSupervision[columnID].addView(linearLayoutsInnerCardMorningSupervision[columnID]);
        linearLayoutsColumnCellMorningSupervision[columnID].addView(cardViewMorningSupervision[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellMorningSupervision[columnID]);
    }




 int getHeight(int hour, int colspan){


        if(hour == 0){

            if(colspan == 2){

                return 2*velKlasickeHod + velMalePrestavky;

            }else if(colspan == 3){

                return 3*velKlasickeHod + velMalePrestavky + velStredniPrestavky;

            }else if(colspan == 4){

                return 4*velKlasickeHod + velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

            }else if(colspan == 5){

                return 5*velKlasickeHod + 2*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

            }else if(colspan == 6){

                return 6*velKlasickeHod + 3*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

            }else if(colspan == 7){

                return 6*velKlasickeHod + 1*velSesteHod + 4*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

            }else if(colspan == 8){

                return 7*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

            }else if(colspan == 9){

                return 8*velKlasickeHod + 1*velSesteHod + 6*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;
            }


        }


        return 0;
 }






    public void addSupervision6a(int columnID, String textRow){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellSupervision6a[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellSupervision6a = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velSupervision6ab);
        lp_column1CellSupervision6a.setMargins(0,marginBreak5,0,0);
        linearLayoutsColumnCellSupervision6a[columnID].setLayoutParams(lp_column1CellSupervision6a);
        linearLayoutsColumnCellSupervision6a[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellSupervision6a[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewSupervision6a[columnID] = new CardView(getApplicationContext());
        cardViewSupervision6a[columnID].setLayoutParams(lp_mainCard);
        cardViewSupervision6a[columnID].setCardBackgroundColor(Color.parseColor("#FFF9C4"));
        cardViewSupervision6a[columnID].setClickable(true);
        cardViewSupervision6a[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardSupervision6a[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardSupervision6a[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardSupervision6a[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowSupervision6a[columnID] = new TextView(getApplicationContext());
        textViewsRowSupervision6a[columnID].setLayoutParams(lp_match_match);
        textViewsRowSupervision6a[columnID].setGravity(Gravity.CENTER);
        //textViewsRowSupervision6a[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowSupervision6a[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowSupervision6a[columnID].setAllCaps(true);
        textViewsRowSupervision6a[columnID].setTextSize(12);
        textViewsRowSupervision6a[columnID].setText(textRow);

        linearLayoutsInnerCardSupervision6a[columnID].addView(textViewsRowSupervision6a[columnID]);
        cardViewSupervision6a[columnID].addView(linearLayoutsInnerCardSupervision6a[columnID]);
        linearLayoutsColumnCellSupervision6a[columnID].addView(cardViewSupervision6a[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellSupervision6a[columnID]);
    }

    public void addSupervision6b(int columnID, String textRow){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellSupervision6b[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellSupervision6b = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                velSupervision6ab);
        lp_column1CellSupervision6b.setMargins(0,marginHour7-velSupervision6ab,0,0);
        linearLayoutsColumnCellSupervision6b[columnID].setLayoutParams(lp_column1CellSupervision6b);
        linearLayoutsColumnCellSupervision6b[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellSupervision6b[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewSupervision6b[columnID] = new CardView(getApplicationContext());
        cardViewSupervision6b[columnID].setLayoutParams(lp_mainCard);
        cardViewSupervision6b[columnID].setCardBackgroundColor(Color.parseColor("#FFF9C4"));
        cardViewSupervision6b[columnID].setClickable(true);
        cardViewSupervision6b[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardSupervision6b[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardSupervision6b[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardSupervision6b[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowSupervision6b[columnID] = new TextView(getApplicationContext());
        textViewsRowSupervision6b[columnID].setLayoutParams(lp_match_match);
        textViewsRowSupervision6b[columnID].setGravity(Gravity.CENTER);
        //textViewsRowSupervision6b[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowSupervision6b[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowSupervision6b[columnID].setAllCaps(true);
        textViewsRowSupervision6b[columnID].setTextSize(12);
        textViewsRowSupervision6b[columnID].setText(textRow);

        linearLayoutsInnerCardSupervision6b[columnID].addView(textViewsRowSupervision6b[columnID]);
        cardViewSupervision6b[columnID].addView(linearLayoutsInnerCardSupervision6b[columnID]);
        linearLayoutsColumnCellSupervision6b[columnID].addView(cardViewSupervision6b[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellSupervision6b[columnID]);
    }


    public void addOther(int columnID, String textRow){

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        linearLayoutsColumnCellOther[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellOther = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                getHeight(0,9));
        lp_column1CellOther.setMargins(0,marginHour0,0,0);
        linearLayoutsColumnCellOther[columnID].setLayoutParams(lp_column1CellOther);
        linearLayoutsColumnCellOther[columnID].setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsColumnCellOther[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewOther[columnID] = new CardView(getApplicationContext());
        cardViewOther[columnID].setLayoutParams(lp_mainCard);
        cardViewOther[columnID].setCardBackgroundColor(Color.parseColor("#F4F9CF"));
        cardViewOther[columnID].setClickable(true);
        cardViewOther[columnID].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("kokot3","Hour8 " +  names.get(finalI-1));

                //String lolo = names.get(columnID);

                //String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                //String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                //String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                //String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                //String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                //String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog("a", "a", "a", "a", "a", "a" );
                //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutsInnerCardOther[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardOther[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardOther[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowOther[columnID] = new TextView(getApplicationContext());
        textViewsRowOther[columnID].setLayoutParams(lp_match_match);
        textViewsRowOther[columnID].setGravity(Gravity.CENTER);
        //textViewsRowOther[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowOther[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowOther[columnID].setAllCaps(true);
        textViewsRowOther[columnID].setTextSize(12);
        textViewsRowOther[columnID].setText(textRow);

        linearLayoutsInnerCardOther[columnID].addView(textViewsRowOther[columnID]);
        cardViewOther[columnID].addView(linearLayoutsInnerCardOther[columnID]);
        linearLayoutsColumnCellOther[columnID].addView(cardViewOther[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellOther[columnID]);
    }
*/

}
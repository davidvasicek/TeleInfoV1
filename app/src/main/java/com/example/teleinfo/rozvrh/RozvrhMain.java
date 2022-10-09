package com.example.teleinfo.rozvrh;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.teleinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RozvrhMain extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DataSnapshot dataSnapshot_global;

    CardView rozvrh_card_hour1_teacher1;
    CardView rozvrh_card_hour2_teacher1;
    CardView rozvrh_card_hour3_teacher1;
    CardView rozvrh_card_hour4_teacher1;
    CardView rozvrh_card_hour5_teacher1;
    CardView rozvrh_card_hour6_teacher1;
    CardView rozvrh_card_hour7_teacher1;
    CardView rozvrh_card_hour8_teacher1;
    CardView rozvrh_card_hour1_teacher2;
    CardView rozvrh_card_hour2_teacher2;
    CardView rozvrh_card_hour3_teacher2;
    CardView rozvrh_card_hour4_teacher2;
    CardView rozvrh_card_hour5_teacher2;
    CardView rozvrh_card_hour6_teacher2;
    CardView rozvrh_card_hour7_teacher2;
    CardView rozvrh_card_hour8_teacher2;

    TextView rozvrh_subject_hour1_teacher1;
    TextView rozvrh_subject_hour2_teacher1;
    TextView rozvrh_subject_hour3_teacher1;
    TextView rozvrh_subject_hour4_teacher1;
    TextView rozvrh_subject_hour5_teacher1;
    TextView rozvrh_subject_hour6_teacher1;
    TextView rozvrh_subject_hour7_teacher1;
    TextView rozvrh_subject_hour8_teacher1;
    TextView rozvrh_subject_hour1_teacher2;
    TextView rozvrh_subject_hour2_teacher2;
    TextView rozvrh_subject_hour3_teacher2;
    TextView rozvrh_subject_hour4_teacher2;
    TextView rozvrh_subject_hour5_teacher2;
    TextView rozvrh_subject_hour6_teacher2;
    TextView rozvrh_subject_hour7_teacher2;
    TextView rozvrh_subject_hour8_teacher2;

    TextView rozvrh_class_hour1_teacher1;
    TextView rozvrh_class_hour2_teacher1;
    TextView rozvrh_class_hour3_teacher1;
    TextView rozvrh_class_hour4_teacher1;
    TextView rozvrh_class_hour5_teacher1;
    TextView rozvrh_class_hour6_teacher1;
    TextView rozvrh_class_hour7_teacher1;
    TextView rozvrh_class_hour8_teacher1;
    TextView rozvrh_class_hour1_teacher2;
    TextView rozvrh_class_hour2_teacher2;
    TextView rozvrh_class_hour3_teacher2;
    TextView rozvrh_class_hour4_teacher2;
    TextView rozvrh_class_hour5_teacher2;
    TextView rozvrh_class_hour6_teacher2;
    TextView rozvrh_class_hour7_teacher2;
    TextView rozvrh_class_hour8_teacher2;

    TextView rozvrh_room_hour1_teacher1;
    TextView rozvrh_room_hour2_teacher1;
    TextView rozvrh_room_hour3_teacher1;
    TextView rozvrh_room_hour4_teacher1;
    TextView rozvrh_room_hour5_teacher1;
    TextView rozvrh_room_hour6_teacher1;
    TextView rozvrh_room_hour7_teacher1;
    TextView rozvrh_room_hour8_teacher1;
    TextView rozvrh_room_hour1_teacher2;
    TextView rozvrh_room_hour2_teacher2;
    TextView rozvrh_room_hour3_teacher2;
    TextView rozvrh_room_hour4_teacher2;
    TextView rozvrh_room_hour5_teacher2;
    TextView rozvrh_room_hour6_teacher2;
    TextView rozvrh_room_hour7_teacher2;
    TextView rozvrh_room_hour8_teacher2;

    TextView rozvrh_freeroom_hour1;
    TextView rozvrh_freeroom_hour2;
    TextView rozvrh_freeroom_hour3;
    TextView rozvrh_freeroom_hour4;
    TextView rozvrh_freeroom_hour5;
    TextView rozvrh_freeroom_hour6;
    TextView rozvrh_freeroom_hour7;
    TextView rozvrh_freeroom_hour8;

    LinearLayout LinearLayoutRowHeader;
    LinearLayout linearLayoutRowHour0;
    LinearLayout linearLayoutRowBreak0;
    LinearLayout linearLayoutRowHour1;
    LinearLayout linearLayoutRowBreak1;
    LinearLayout linearLayoutRowHour2;
    LinearLayout linearLayoutRowBreak2;
    LinearLayout linearLayoutRowHour3;
    LinearLayout linearLayoutRowBreak3;
    LinearLayout linearLayoutRowHour4;
    LinearLayout linearLayoutRowBreak4;
    LinearLayout linearLayoutRowHour5;
    LinearLayout linearLayoutRowBreak5;
    LinearLayout linearLayoutRowHour6;
    LinearLayout linearLayoutRowBreak6;
    LinearLayout linearLayoutRowHour7;
    LinearLayout linearLayoutRowBreak7;
    LinearLayout linearLayoutRowHour8;
    LinearLayout linearLayoutRowBreak8;
    LinearLayout linearLayoutRowHour9;
    LinearLayout linearLayoutRowBreak9;

    LinearLayout linearLayouts[];
    LinearLayout linearLayoutsInnerCard[];
    CardView cardView[];
    TextView textViewsRow1[];
    TextView textViewsRow2[];



    LinearLayout linearLayoutsColumnHour0[];
    LinearLayout linearLayoutsInnerCardHour0[];
    CardView cardViewHour0[];
    TextView textViewsRow1Hour0[];
    TextView textViewsRow2Hour0[];
    TextView textViewsRow3Hour0[];

    LinearLayout linearLayoutsColumnBreak0[];
    LinearLayout linearLayoutsInnerCardBreak0[];
    CardView cardViewBreak0[];
    TextView textViewsRow1Break0[];
    TextView textViewsRow2Break0[];
    TextView textViewsRow3Break0[];

    LinearLayout linearLayoutsColumnHour1[];
    LinearLayout linearLayoutsInnerCardHour1[];
    CardView cardViewHour1[];
    TextView textViewsRow1Hour1[];
    TextView textViewsRow2Hour1[];
    TextView textViewsRow3Hour1[];

    LinearLayout linearLayoutsColumnBreak1[];
    LinearLayout linearLayoutsInnerCardBreak1[];
    CardView cardViewBreak1[];
    TextView textViewsRow1Break1[];
    TextView textViewsRow2Break1[];
    TextView textViewsRow3Break1[];

    LinearLayout linearLayoutsColumnHour2[];
    LinearLayout linearLayoutsInnerCardHour2[];
    CardView cardViewHour2[];
    TextView textViewsRow1Hour2[];
    TextView textViewsRow2Hour2[];
    TextView textViewsRow3Hour2[];

    LinearLayout linearLayoutsColumnBreak2[];
    LinearLayout linearLayoutsInnerCardBreak2[];
    CardView cardViewBreak2[];
    TextView textViewsRow1Break2[];
    TextView textViewsRow2Break2[];
    TextView textViewsRow3Break2[];

    LinearLayout linearLayoutsColumnHour3[];
    LinearLayout linearLayoutsInnerCardHour3[];
    CardView cardViewHour3[];
    TextView textViewsRow1Hour3[];
    TextView textViewsRow2Hour3[];
    TextView textViewsRow3Hour3[];

    LinearLayout linearLayoutsColumnBreak3[];
    LinearLayout linearLayoutsInnerCardBreak3[];
    CardView cardViewBreak3[];
    TextView textViewsRow1Break3[];
    TextView textViewsRow2Break3[];
    TextView textViewsRow3Break3[];

    LinearLayout linearLayoutsColumnHour4[];
    LinearLayout linearLayoutsInnerCardHour4[];
    CardView cardViewHour4[];
    TextView textViewsRow1Hour4[];
    TextView textViewsRow2Hour4[];
    TextView textViewsRow3Hour4[];

    LinearLayout linearLayoutsColumnBreak4[];
    LinearLayout linearLayoutsInnerCardBreak4[];
    CardView cardViewBreak4[];
    TextView textViewsRow1Break4[];
    TextView textViewsRow2Break4[];
    TextView textViewsRow3Break4[];

    LinearLayout linearLayoutsColumnHour5[];
    LinearLayout linearLayoutsInnerCardHour5[];
    CardView cardViewHour5[];
    TextView textViewsRow1Hour5[];
    TextView textViewsRow2Hour5[];
    TextView textViewsRow3Hour5[];

    LinearLayout linearLayoutsColumnBreak5[];
    LinearLayout linearLayoutsInnerCardBreak5[];
    CardView cardViewBreak5[];
    TextView textViewsRow1Break5[];
    TextView textViewsRow2Break5[];
    TextView textViewsRow3Break5[];

    LinearLayout linearLayoutsColumnHour6[];
    LinearLayout linearLayoutsInnerCardHour6[];
    CardView cardViewHour6[];
    TextView textViewsRow1Hour6[];
    TextView textViewsRow2Hour6[];
    TextView textViewsRow3Hour6[];

    LinearLayout linearLayoutsColumnBreak6[];
    LinearLayout linearLayoutsInnerCardBreak6[];
    CardView cardViewBreak6[];
    TextView textViewsRow1Break6[];
    TextView textViewsRow2Break6[];
    TextView textViewsRow3Break6[];

    LinearLayout linearLayoutsColumnHour7[];
    LinearLayout linearLayoutsInnerCardHour7[];
    CardView cardViewHour7[];
    TextView textViewsRow1Hour7[];
    TextView textViewsRow2Hour7[];
    TextView textViewsRow3Hour7[];

    LinearLayout linearLayoutsColumnBreak7[];
    LinearLayout linearLayoutsInnerCardBreak7[];
    CardView cardViewBreak7[];
    TextView textViewsRow1Break7[];
    TextView textViewsRow2Break7[];
    TextView textViewsRow3Break7[];

    LinearLayout linearLayoutsColumnHour8[];
    LinearLayout linearLayoutsInnerCardHour8[];
    CardView cardViewHour8[];
    TextView textViewsRow1Hour8[];
    TextView textViewsRow2Hour8[];
    TextView textViewsRow3Hour8[];

    LinearLayout linearLayoutsColumnBreak8[];
    LinearLayout linearLayoutsInnerCardBreak8[];
    CardView cardViewBreak8[];
    TextView textViewsRow1Break8[];
    TextView textViewsRow2Break8[];
    TextView textViewsRow3Break8[];

    LinearLayout linearLayoutsColumnHour9[];
    LinearLayout linearLayoutsInnerCardHour9[];
    CardView cardViewHour9[];
    TextView textViewsRow1Hour9[];
    TextView textViewsRow2Hour9[];
    TextView textViewsRow3Hour9[];

    LinearLayout linearLayoutsColumnBreak9[];
    LinearLayout linearLayoutsInnerCardBreak9[];
    CardView cardViewBreak9[];
    TextView textViewsRow1Break9[];
    TextView textViewsRow2Break9[];
    TextView textViewsRow3Break9[];


    int size = 50;

    List<String> names = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rozvrh_activity_rozvrh_main);

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



        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("rozvrh");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot_global = dataSnapshot;

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour0[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour0[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour0[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour0[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                       // Log.e(TAG, "koko: " + lolo );

                        textViewsRow1Break0[i].setText(dataSnapshot.child("16_9").child("d_Op").child(lolo).child("Location").getValue(String.class).split(" - ")[0]);
                        Log.e(TAG, "koko: " + lolo );
                    }catch (NullPointerException e){
                        cardViewBreak0[i].setVisibility(View.GONE);
                        //Log.e(TAG, "koko: spadl jsem na " + lolo );
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour1[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour1[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour1[i].setText(dataSnapshot.child("16_9").child("1").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour1[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Break1[i].setText(dataSnapshot.child("16_9").child("d_1p").child(lolo).child("Location").getValue(String.class).split(" - ")[0]);
                    }catch (NullPointerException e){
                        cardViewBreak1[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour2[i].setText(dataSnapshot.child("16_9").child("2").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour2[i].setText(dataSnapshot.child("16_9").child("2").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour2[i].setText(dataSnapshot.child("16_9").child("2").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour2[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Break2[i].setText(dataSnapshot.child("16_9").child("d_2p").child(lolo).child("Location").getValue(String.class).split(" - ")[0]);
                    }catch (NullPointerException e){
                        cardViewBreak2[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour3[i].setText(dataSnapshot.child("16_9").child("3").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour3[i].setText(dataSnapshot.child("16_9").child("3").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour3[i].setText(dataSnapshot.child("16_9").child("3").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour3[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Break3[i].setText(dataSnapshot.child("16_9").child("d_3p").child(lolo).child("Location").getValue(String.class).split(" - ")[0]);
                    }catch (NullPointerException e){
                        cardViewBreak3[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour4[i].setText(dataSnapshot.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour4[i].setText(dataSnapshot.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour4[i].setText(dataSnapshot.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour4[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Break4[i].setText(dataSnapshot.child("16_9").child("d_4p").child(lolo).child("Location").getValue(String.class).split(" - ")[0]);
                    }catch (NullPointerException e){
                        cardViewBreak4[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour5[i].setText(dataSnapshot.child("16_9").child("5").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour5[i].setText(dataSnapshot.child("16_9").child("5").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour5[i].setText(dataSnapshot.child("16_9").child("5").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour5[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Break5[i].setText(dataSnapshot.child("16_9").child("d_5p").child(lolo).child("Location").getValue(String.class).split(" - ")[0]);
                    }catch (NullPointerException e){
                        cardViewBreak5[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour6[i].setText(dataSnapshot.child("16_9").child("6").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour6[i].setText(dataSnapshot.child("16_9").child("6").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour6[i].setText(dataSnapshot.child("16_9").child("6").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour6[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour7[i].setText(dataSnapshot.child("16_9").child("7").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour7[i].setText(dataSnapshot.child("16_9").child("7").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour7[i].setText(dataSnapshot.child("16_9").child("7").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour7[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour8[i].setText(dataSnapshot.child("16_9").child("8").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour8[i].setText(dataSnapshot.child("16_9").child("8").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour8[i].setText(dataSnapshot.child("16_9").child("8").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour8[i].setVisibility(View.GONE);
                    }

                }

                for(int i = 1; i < size ; i++){

                    String lolo = names.get(i-1);

                    try{
                        textViewsRow1Hour9[i].setText(dataSnapshot.child("16_9").child("9").child(lolo).child("Predmet").getValue(String.class).split(" ")[0]);
                        textViewsRow2Hour9[i].setText(dataSnapshot.child("16_9").child("9").child(lolo).child("Trida").getValue(String.class));
                        textViewsRow3Hour9[i].setText(dataSnapshot.child("16_9").child("9").child(lolo).child("Ucebna").getValue(String.class));
                    }catch (NullPointerException e){
                        cardViewHour9[i].setVisibility(View.GONE);
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

        LinearLayoutRowHeader = (LinearLayout)findViewById(R.id.LinearLayoutRowHeader);
        linearLayoutRowHour0 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour0);
        linearLayoutRowBreak0 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak0);
        linearLayoutRowHour1 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour1);
        linearLayoutRowBreak1 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak1);
        linearLayoutRowHour2 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour2);
        linearLayoutRowBreak2 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak2);
        linearLayoutRowHour3 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour3);
        linearLayoutRowBreak3 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak3);
        linearLayoutRowHour4 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour4);
        linearLayoutRowBreak4 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak4);
        linearLayoutRowHour5 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour5);
        linearLayoutRowBreak5 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak5);
        linearLayoutRowHour6 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour6);
        linearLayoutRowBreak6 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak6);
        linearLayoutRowHour7 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour7);
        linearLayoutRowBreak7 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak7);
        linearLayoutRowHour8 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour8);
        linearLayoutRowBreak8 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak8);
        linearLayoutRowHour9 = (LinearLayout)findViewById(R.id.LinearLayoutRowHour9);
        linearLayoutRowBreak9 = (LinearLayout)findViewById(R.id.LinearLayoutRowBreak9);


        linearLayouts = new LinearLayout[size];
        linearLayoutsInnerCard = new LinearLayout[size];
        cardView = new CardView[size];
        textViewsRow1 = new TextView[size];
        textViewsRow2 = new TextView[size];


        linearLayoutsColumnHour0 = new LinearLayout[size];
        linearLayoutsInnerCardHour0 = new LinearLayout[size];
        cardViewHour0 = new CardView[size];;
        textViewsRow1Hour0 = new TextView[size];
        textViewsRow2Hour0 = new TextView[size];
        textViewsRow3Hour0 = new TextView[size];

        linearLayoutsColumnBreak0 = new LinearLayout[size];
        linearLayoutsInnerCardBreak0 = new LinearLayout[size];
        cardViewBreak0 = new CardView[size];;
        textViewsRow1Break0 = new TextView[size];
        textViewsRow2Break0 = new TextView[size];
        textViewsRow3Break0 = new TextView[size];

        linearLayoutsColumnHour1 = new LinearLayout[size];
        linearLayoutsInnerCardHour1 = new LinearLayout[size];
        cardViewHour1 = new CardView[size];;
        textViewsRow1Hour1 = new TextView[size];
        textViewsRow2Hour1 = new TextView[size];
        textViewsRow3Hour1 = new TextView[size];

        linearLayoutsColumnBreak1 = new LinearLayout[size];
        linearLayoutsInnerCardBreak1 = new LinearLayout[size];
        cardViewBreak1 = new CardView[size];
        textViewsRow1Break1 = new TextView[size];
        textViewsRow2Break1 = new TextView[size];
        textViewsRow3Break1 = new TextView[size];

        linearLayoutsColumnHour2 = new LinearLayout[size];
        linearLayoutsInnerCardHour2 = new LinearLayout[size];
        cardViewHour2 = new CardView[size];
        textViewsRow1Hour2 = new TextView[size];
        textViewsRow2Hour2 = new TextView[size];
        textViewsRow3Hour2 = new TextView[size];

        linearLayoutsColumnBreak2 = new LinearLayout[size];
        linearLayoutsInnerCardBreak2 = new LinearLayout[size];
        cardViewBreak2 = new CardView[size];
        textViewsRow1Break2 = new TextView[size];
        textViewsRow2Break2 = new TextView[size];
        textViewsRow3Break2 = new TextView[size];

        linearLayoutsColumnHour3 = new LinearLayout[size];
        linearLayoutsInnerCardHour3 = new LinearLayout[size];
        cardViewHour3 = new CardView[size];
        textViewsRow1Hour3 = new TextView[size];
        textViewsRow2Hour3 = new TextView[size];
        textViewsRow3Hour3 = new TextView[size];

        linearLayoutsColumnBreak3 = new LinearLayout[size];
        linearLayoutsInnerCardBreak3 = new LinearLayout[size];
        cardViewBreak3 = new CardView[size];
        textViewsRow1Break3 = new TextView[size];
        textViewsRow2Break3 = new TextView[size];
        textViewsRow3Break3 = new TextView[size];

        linearLayoutsColumnHour4 = new LinearLayout[size];
        linearLayoutsInnerCardHour4 = new LinearLayout[size];
        cardViewHour4 = new CardView[size];
        textViewsRow1Hour4 = new TextView[size];
        textViewsRow2Hour4 = new TextView[size];
        textViewsRow3Hour4 = new TextView[size];

        linearLayoutsColumnBreak4 = new LinearLayout[size];
        linearLayoutsInnerCardBreak4 = new LinearLayout[size];
        cardViewBreak4 = new CardView[size];
        textViewsRow1Break4 = new TextView[size];
        textViewsRow2Break4 = new TextView[size];
        textViewsRow3Break4 = new TextView[size];

        linearLayoutsColumnHour5 = new LinearLayout[size];
        linearLayoutsInnerCardHour5 = new LinearLayout[size];
        cardViewHour5 = new CardView[size];
        textViewsRow1Hour5 = new TextView[size];
        textViewsRow2Hour5 = new TextView[size];
        textViewsRow3Hour5 = new TextView[size];

        linearLayoutsColumnBreak5 = new LinearLayout[size];
        linearLayoutsInnerCardBreak5 = new LinearLayout[size];
        cardViewBreak5 = new CardView[size];
        textViewsRow1Break5 = new TextView[size];
        textViewsRow2Break5 = new TextView[size];
        textViewsRow3Break5 = new TextView[size];

        linearLayoutsColumnHour6 = new LinearLayout[size];
        linearLayoutsInnerCardHour6 = new LinearLayout[size];
        cardViewHour6 = new CardView[size];
        textViewsRow1Hour6 = new TextView[size];
        textViewsRow2Hour6 = new TextView[size];
        textViewsRow3Hour6 = new TextView[size];

        linearLayoutsColumnBreak6 = new LinearLayout[size];
        linearLayoutsInnerCardBreak6 = new LinearLayout[size];
        cardViewBreak6 = new CardView[size];
        textViewsRow1Break6 = new TextView[size];
        textViewsRow2Break6 = new TextView[size];
        textViewsRow3Break6 = new TextView[size];

        linearLayoutsColumnHour7 = new LinearLayout[size];
        linearLayoutsInnerCardHour7 = new LinearLayout[size];
        cardViewHour7 = new CardView[size];
        textViewsRow1Hour7 = new TextView[size];
        textViewsRow2Hour7 = new TextView[size];
        textViewsRow3Hour7 = new TextView[size];

        linearLayoutsColumnBreak7 = new LinearLayout[size];
        linearLayoutsInnerCardBreak7 = new LinearLayout[size];
        cardViewBreak7 = new CardView[size];
        textViewsRow1Break7 = new TextView[size];
        textViewsRow2Break7 = new TextView[size];
        textViewsRow3Break7 = new TextView[size];

        linearLayoutsColumnHour8 = new LinearLayout[size];
        linearLayoutsInnerCardHour8 = new LinearLayout[size];
        cardViewHour8 = new CardView[size];
        textViewsRow1Hour8 = new TextView[size];
        textViewsRow2Hour8 = new TextView[size];
        textViewsRow3Hour8 = new TextView[size];

        linearLayoutsColumnBreak8 = new LinearLayout[size];
        linearLayoutsInnerCardBreak8 = new LinearLayout[size];
        cardViewBreak8 = new CardView[size];
        textViewsRow1Break8 = new TextView[size];
        textViewsRow2Break8 = new TextView[size];
        textViewsRow3Break8 = new TextView[size];

        linearLayoutsColumnHour9 = new LinearLayout[size];
        linearLayoutsInnerCardHour9 = new LinearLayout[size];
        cardViewHour9 = new CardView[size];
        textViewsRow1Hour9 = new TextView[size];
        textViewsRow2Hour9 = new TextView[size];
        textViewsRow3Hour9 = new TextView[size];

        linearLayoutsColumnBreak9 = new LinearLayout[size];
        linearLayoutsInnerCardBreak9 = new LinearLayout[size];
        cardViewBreak9 = new CardView[size];
        textViewsRow1Break9 = new TextView[size];
        textViewsRow2Break9 = new TextView[size];
        textViewsRow3Break9 = new TextView[size];





       LinearLayout.LayoutParams lp_mainCell = new LinearLayout.LayoutParams(
                225, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(12,12,12,12);
        
        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView





        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        for(int i = 0; i < size; i++){

            linearLayouts[i] = new LinearLayout(getApplicationContext());
            linearLayouts[i].setLayoutParams(lp_mainCell);
            linearLayouts[i].setOrientation(LinearLayout.VERTICAL);

            cardView[i] = new CardView(getApplicationContext());
            cardView[i].setLayoutParams(lp_mainCard);
            cardView[i].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
            
            linearLayoutsInnerCard[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCard[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCard[i].setOrientation(LinearLayout.VERTICAL);



            textViewsRow1[i] = new TextView(getApplicationContext());
            textViewsRow1[i].setLayoutParams(lp3);
            textViewsRow1[i].setGravity(Gravity.CENTER);
            textViewsRow1[i].setTextSize(13);
            textViewsRow1[i].setText("David\nVašíček");
            textViewsRow1[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1[i].setAllCaps(true);

            textViewsRow2[i] = new TextView(getApplicationContext());
            textViewsRow2[i].setLayoutParams(lp4);
            textViewsRow2[i].setGravity(Gravity.CENTER);
            textViewsRow2[i].setTextSize(14);
            textViewsRow2[i].setText("Vašíček");
            textViewsRow2[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow2[i].setTextColor(Color.parseColor("#212121"));

            linearLayoutsInnerCard[i].addView(textViewsRow1[i]);
            //linearLayoutsInnerCard[i].addView(textViewsRow2[i]);

            cardView[i].addView(linearLayoutsInnerCard[i]);

            linearLayouts[i].addView(cardView[i]);

            LinearLayoutRowHeader.addView(linearLayouts[i]);
        }

        for(int i = 1; i < size ; i++){

            String lolo = names.get(i-1);

            String name = lolo.split("_")[0];
            String surname = lolo.split("_")[1];
            textViewsRow1[i].setText(name+ "\n" +surname);

        }



        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour0[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour0[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour0[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour0[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour0[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour0[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour0[i] = new CardView(getApplicationContext());
            cardViewHour0[i].setLayoutParams(lp_mainCard);
            cardViewHour0[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour0[i].setClickable(true);

            if(i != 0){

                cardViewHour0[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour0[i] = new TextView(getApplicationContext());
            textViewsRow1Hour0[i].setLayoutParams(lp3);
            textViewsRow1Hour0[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour0[i].setTextSize(18);
            textViewsRow1Hour0[i].setText("PRA");
            textViewsRow1Hour0[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour0[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour0[i].setAllCaps(true);

            textViewsRow2Hour0[i] = new TextView(getApplicationContext());
            textViewsRow2Hour0[i].setLayoutParams(lp4);
            textViewsRow2Hour0[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour0[i].setTextSize(13);
            textViewsRow2Hour0[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour0[i].setText("3.D (c)");

            textViewsRow3Hour0[i] = new TextView(getApplicationContext());
            textViewsRow3Hour0[i].setLayoutParams(lp4);
            textViewsRow3Hour0[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour0[i].setTextSize(12);
            textViewsRow3Hour0[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour0[i].setText("17");

            linearLayoutsInnerCardHour0[i].addView(textViewsRow1Hour0[i]);
            linearLayoutsInnerCardHour0[i].addView(textViewsRow2Hour0[i]);
            linearLayoutsInnerCardHour0[i].addView(textViewsRow3Hour0[i]);

            cardViewHour0[i].addView(linearLayoutsInnerCardHour0[i]);

            linearLayoutsColumnHour0[i].addView(cardViewHour0[i]);

            linearLayoutRowHour0.addView(linearLayoutsColumnHour0[i]);
        }


        cardViewHour0[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour0[0].setTextSize(16);
        textViewsRow1Hour0[0].setText("0. HOD");
        textViewsRow2Hour0[0].setText("7:25");
        textViewsRow3Hour0[0].setText("8:10");


        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak0[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak0[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak0[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak0[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak0[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak0[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak0[i] = new CardView(getApplicationContext());
            cardViewBreak0[i].setLayoutParams(lp_mainCard);
            cardViewBreak0[i].setCardBackgroundColor(Color.parseColor("#FFCCBC"));

            textViewsRow1Break0[i] = new TextView(getApplicationContext());
            textViewsRow1Break0[i].setLayoutParams(lp_match_match);
            textViewsRow1Break0[i].setGravity(Gravity.CENTER);
            textViewsRow1Break0[i].setTextSize(12);
            textViewsRow1Break0[i].setText("5 MIN");
            textViewsRow1Break0[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break0[i].setAllCaps(true);

            linearLayoutsInnerCardBreak0[i].addView(textViewsRow1Break0[i]);

            cardViewBreak0[i].addView(linearLayoutsInnerCardBreak0[i]);

            linearLayoutsColumnBreak0[i].addView(cardViewBreak0[i]);

            linearLayoutRowBreak0.addView(linearLayoutsColumnBreak0[i]);
        }
        cardViewBreak0[0].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        textViewsRow1Break0[0].setText("5 MIN");

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour1[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour1[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour1[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour1[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour1[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour1[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour1[i] = new CardView(getApplicationContext());
            cardViewHour1[i].setLayoutParams(lp_mainCard);
            cardViewHour1[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour1[i].setClickable(true);

            if(i != 0){

                cardViewHour1[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("1").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour1[i] = new TextView(getApplicationContext());
            textViewsRow1Hour1[i].setLayoutParams(lp3);
            textViewsRow1Hour1[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour1[i].setTextSize(18);
            textViewsRow1Hour1[i].setText("PRA");
            textViewsRow1Hour1[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour1[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour1[i].setAllCaps(true);

            textViewsRow2Hour1[i] = new TextView(getApplicationContext());
            textViewsRow2Hour1[i].setLayoutParams(lp4);
            textViewsRow2Hour1[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour1[i].setTextSize(13);
            textViewsRow2Hour1[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour1[i].setText("3.D (c)");

            textViewsRow3Hour1[i] = new TextView(getApplicationContext());
            textViewsRow3Hour1[i].setLayoutParams(lp4);
            textViewsRow3Hour1[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour1[i].setTextSize(12);
            textViewsRow3Hour1[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour1[i].setText("17");

            linearLayoutsInnerCardHour1[i].addView(textViewsRow1Hour1[i]);
            linearLayoutsInnerCardHour1[i].addView(textViewsRow2Hour1[i]);
            linearLayoutsInnerCardHour1[i].addView(textViewsRow3Hour1[i]);

            cardViewHour1[i].addView(linearLayoutsInnerCardHour1[i]);

            linearLayoutsColumnHour1[i].addView(cardViewHour1[i]);

            linearLayoutRowHour1.addView(linearLayoutsColumnHour1[i]);
        }

        cardViewHour1[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour1[0].setTextSize(16);
        textViewsRow1Hour1[0].setText("1. HOD");
        textViewsRow2Hour1[0].setText("8:15");
        textViewsRow3Hour1[0].setText("9:00");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak1[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak1[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak1[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak1[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak1[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak1[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak1[i] = new CardView(getApplicationContext());
            cardViewBreak1[i].setLayoutParams(lp_mainCard);
            cardViewBreak1[i].setCardBackgroundColor(Color.parseColor("#FFCCBC"));

            textViewsRow1Break1[i] = new TextView(getApplicationContext());
            textViewsRow1Break1[i].setLayoutParams(lp_match_match);
            textViewsRow1Break1[i].setGravity(Gravity.CENTER);
            textViewsRow1Break1[i].setTextSize(12);
            textViewsRow1Break1[i].setText("5 MIN");
            textViewsRow1Break1[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break1[i].setAllCaps(true);

            linearLayoutsInnerCardBreak1[i].addView(textViewsRow1Break1[i]);

            cardViewBreak1[i].addView(linearLayoutsInnerCardBreak1[i]);

            linearLayoutsColumnBreak1[i].addView(cardViewBreak1[i]);

            linearLayoutRowBreak1.addView(linearLayoutsColumnBreak1[i]);
        }

        cardViewBreak1[0].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        textViewsRow1Break1[0].setText("10 MIN");


        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour2[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour2[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour2[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour2[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour2[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour2[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour2[i] = new CardView(getApplicationContext());
            cardViewHour2[i].setLayoutParams(lp_mainCard);
            cardViewHour2[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour2[i].setClickable(true);

            if(i != 0){

                cardViewHour2[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("2").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("2").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("2").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("2").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("2").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("2").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour2[i] = new TextView(getApplicationContext());
            textViewsRow1Hour2[i].setLayoutParams(lp3);
            textViewsRow1Hour2[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour2[i].setTextSize(18);
            textViewsRow1Hour2[i].setText("PRA");
            textViewsRow1Hour2[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour2[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour2[i].setAllCaps(true);

            textViewsRow2Hour2[i] = new TextView(getApplicationContext());
            textViewsRow2Hour2[i].setLayoutParams(lp4);
            textViewsRow2Hour2[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour2[i].setTextSize(13);
            textViewsRow2Hour2[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour2[i].setText("3.D (c)");

            textViewsRow3Hour2[i] = new TextView(getApplicationContext());
            textViewsRow3Hour2[i].setLayoutParams(lp4);
            textViewsRow3Hour2[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour2[i].setTextSize(12);
            textViewsRow3Hour2[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour2[i].setText("17");

            linearLayoutsInnerCardHour2[i].addView(textViewsRow1Hour2[i]);
            linearLayoutsInnerCardHour2[i].addView(textViewsRow2Hour2[i]);
            linearLayoutsInnerCardHour2[i].addView(textViewsRow3Hour2[i]);

            cardViewHour2[i].addView(linearLayoutsInnerCardHour2[i]);

            linearLayoutsColumnHour2[i].addView(cardViewHour2[i]);

            linearLayoutRowHour2.addView(linearLayoutsColumnHour2[i]);
        }

        cardViewHour2[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour2[0].setTextSize(16);
        textViewsRow1Hour2[0].setText("2. HOD");
        textViewsRow2Hour2[0].setText("9:10");
        textViewsRow3Hour2[0].setText("9:55");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak2[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak2[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak2[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak2[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak2[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak2[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak2[i] = new CardView(getApplicationContext());
            cardViewBreak2[i].setLayoutParams(lp_mainCard);
            cardViewBreak2[i].setCardBackgroundColor(Color.parseColor("#FFCCBC"));

            textViewsRow1Break2[i] = new TextView(getApplicationContext());
            textViewsRow1Break2[i].setLayoutParams(lp_match_match);
            textViewsRow1Break2[i].setGravity(Gravity.CENTER);
            textViewsRow1Break2[i].setTextSize(12);
            textViewsRow1Break2[i].setText("5 MIN");
            textViewsRow1Break2[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break2[i].setAllCaps(true);

            linearLayoutsInnerCardBreak2[i].addView(textViewsRow1Break2[i]);

            cardViewBreak2[i].addView(linearLayoutsInnerCardBreak2[i]);

            linearLayoutsColumnBreak2[i].addView(cardViewBreak2[i]);

            linearLayoutRowBreak2.addView(linearLayoutsColumnBreak2[i]);
        }

        cardViewBreak2[0].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        textViewsRow1Break2[0].setText("20 MIN");

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour3[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour3[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour3[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour3[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour3[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour3[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour3[i] = new CardView(getApplicationContext());
            cardViewHour3[i].setLayoutParams(lp_mainCard);
            cardViewHour3[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour3[i].setClickable(true);

            if(i != 0){

                cardViewHour3[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("3").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("3").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("3").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("3").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("3").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("3").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour3[i] = new TextView(getApplicationContext());
            textViewsRow1Hour3[i].setLayoutParams(lp3);
            textViewsRow1Hour3[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour3[i].setTextSize(18);
            textViewsRow1Hour3[i].setText("PRA");
            textViewsRow1Hour3[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour3[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour3[i].setAllCaps(true);

            textViewsRow2Hour3[i] = new TextView(getApplicationContext());
            textViewsRow2Hour3[i].setLayoutParams(lp4);
            textViewsRow2Hour3[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour3[i].setTextSize(13);
            textViewsRow2Hour3[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour3[i].setText("3.D (c)");

            textViewsRow3Hour3[i] = new TextView(getApplicationContext());
            textViewsRow3Hour3[i].setLayoutParams(lp4);
            textViewsRow3Hour3[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour3[i].setTextSize(12);
            textViewsRow3Hour3[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour3[i].setText("17");

            linearLayoutsInnerCardHour3[i].addView(textViewsRow1Hour3[i]);
            linearLayoutsInnerCardHour3[i].addView(textViewsRow2Hour3[i]);
            linearLayoutsInnerCardHour3[i].addView(textViewsRow3Hour3[i]);

            cardViewHour3[i].addView(linearLayoutsInnerCardHour3[i]);

            linearLayoutsColumnHour3[i].addView(cardViewHour3[i]);

            linearLayoutRowHour3.addView(linearLayoutsColumnHour3[i]);
        }

        cardViewHour3[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour3[0].setTextSize(16);
        textViewsRow1Hour3[0].setText("3. HOD");
        textViewsRow2Hour3[0].setText("10:15");
        textViewsRow3Hour3[0].setText("11:00");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak3[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak3[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak3[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak3[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak3[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak3[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak3[i] = new CardView(getApplicationContext());
            cardViewBreak3[i].setLayoutParams(lp_mainCard);
            cardViewBreak3[i].setCardBackgroundColor(Color.parseColor("#FFCCBC"));

            textViewsRow1Break3[i] = new TextView(getApplicationContext());
            textViewsRow1Break3[i].setLayoutParams(lp_match_match);
            textViewsRow1Break3[i].setGravity(Gravity.CENTER);
            textViewsRow1Break3[i].setTextSize(12);
            textViewsRow1Break3[i].setText("5 MIN");
            textViewsRow1Break3[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break3[i].setAllCaps(true);

            linearLayoutsInnerCardBreak3[i].addView(textViewsRow1Break3[i]);

            cardViewBreak3[i].addView(linearLayoutsInnerCardBreak3[i]);

            linearLayoutsColumnBreak3[i].addView(cardViewBreak3[i]);

            linearLayoutRowBreak3.addView(linearLayoutsColumnBreak3[i]);
        }

        cardViewBreak3[0].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        textViewsRow1Break3[0].setText("5 MIN");

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour4[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour4[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour4[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour4[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour4[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour4[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour4[i] = new CardView(getApplicationContext());
            cardViewHour4[i].setLayoutParams(lp_mainCard);
            cardViewHour4[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour4[i].setClickable(true);

            if(i != 0){

                cardViewHour4[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("4").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour4[i] = new TextView(getApplicationContext());
            textViewsRow1Hour4[i].setLayoutParams(lp3);
            textViewsRow1Hour4[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour4[i].setTextSize(18);
            textViewsRow1Hour4[i].setText("PRA");
            textViewsRow1Hour4[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour4[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour4[i].setAllCaps(true);

            textViewsRow2Hour4[i] = new TextView(getApplicationContext());
            textViewsRow2Hour4[i].setLayoutParams(lp4);
            textViewsRow2Hour4[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour4[i].setTextSize(13);
            textViewsRow2Hour4[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour4[i].setText("3.D (c)");

            textViewsRow3Hour4[i] = new TextView(getApplicationContext());
            textViewsRow3Hour4[i].setLayoutParams(lp4);
            textViewsRow3Hour4[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour4[i].setTextSize(12);
            textViewsRow3Hour4[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour4[i].setText("17");

            linearLayoutsInnerCardHour4[i].addView(textViewsRow1Hour4[i]);
            linearLayoutsInnerCardHour4[i].addView(textViewsRow2Hour4[i]);
            linearLayoutsInnerCardHour4[i].addView(textViewsRow3Hour4[i]);

            cardViewHour4[i].addView(linearLayoutsInnerCardHour4[i]);

            linearLayoutsColumnHour4[i].addView(cardViewHour4[i]);

            linearLayoutRowHour4.addView(linearLayoutsColumnHour4[i]);
        }

        cardViewHour4[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour4[0].setTextSize(16);
        textViewsRow1Hour4[0].setText("4. HOD");
        textViewsRow2Hour4[0].setText("11:05");
        textViewsRow3Hour4[0].setText("11:50");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak4[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak4[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak4[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak4[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak4[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak4[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak4[i] = new CardView(getApplicationContext());
            cardViewBreak4[i].setLayoutParams(lp_mainCard);
            cardViewBreak4[i].setCardBackgroundColor(Color.parseColor("#FFCCBC"));

            textViewsRow1Break4[i] = new TextView(getApplicationContext());
            textViewsRow1Break4[i].setLayoutParams(lp_match_match);
            textViewsRow1Break4[i].setGravity(Gravity.CENTER);
            textViewsRow1Break4[i].setTextSize(12);
            textViewsRow1Break4[i].setText("5 MIN");
            textViewsRow1Break4[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break4[i].setAllCaps(true);

            linearLayoutsInnerCardBreak4[i].addView(textViewsRow1Break4[i]);

            cardViewBreak4[i].addView(linearLayoutsInnerCardBreak4[i]);

            linearLayoutsColumnBreak4[i].addView(cardViewBreak4[i]);

            linearLayoutRowBreak4.addView(linearLayoutsColumnBreak4[i]);
        }

        cardViewBreak4[0].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        textViewsRow1Break4[0].setText("5 MIN");

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour5[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour5[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour5[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour5[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour5[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour5[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour5[i] = new CardView(getApplicationContext());
            cardViewHour5[i].setLayoutParams(lp_mainCard);
            cardViewHour5[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour5[i].setClickable(true);

            if(i != 0){

                cardViewHour5[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("5").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("5").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("5").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("5").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("5").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("5").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour5[i] = new TextView(getApplicationContext());
            textViewsRow1Hour5[i].setLayoutParams(lp3);
            textViewsRow1Hour5[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour5[i].setTextSize(18);
            textViewsRow1Hour5[i].setText("PRA");
            textViewsRow1Hour5[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour5[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour5[i].setAllCaps(true);

            textViewsRow2Hour5[i] = new TextView(getApplicationContext());
            textViewsRow2Hour5[i].setLayoutParams(lp4);
            textViewsRow2Hour5[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour5[i].setTextSize(13);
            textViewsRow2Hour5[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour5[i].setText("3.D (c)");

            textViewsRow3Hour5[i] = new TextView(getApplicationContext());
            textViewsRow3Hour5[i].setLayoutParams(lp4);
            textViewsRow3Hour5[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour5[i].setTextSize(12);
            textViewsRow3Hour5[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour5[i].setText("17");

            linearLayoutsInnerCardHour5[i].addView(textViewsRow1Hour5[i]);
            linearLayoutsInnerCardHour5[i].addView(textViewsRow2Hour5[i]);
            linearLayoutsInnerCardHour5[i].addView(textViewsRow3Hour5[i]);

            cardViewHour5[i].addView(linearLayoutsInnerCardHour5[i]);

            linearLayoutsColumnHour5[i].addView(cardViewHour5[i]);

            linearLayoutRowHour5.addView(linearLayoutsColumnHour5[i]);
        }

        cardViewHour5[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour5[0].setTextSize(16);
        textViewsRow1Hour5[0].setText("5. HOD");
        textViewsRow2Hour5[0].setText("11:55");
        textViewsRow3Hour5[0].setText("12:40");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak5[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak5[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak5[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak5[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak5[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak5[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak5[i] = new CardView(getApplicationContext());
            cardViewBreak5[i].setLayoutParams(lp_mainCard);
            cardViewBreak5[i].setCardBackgroundColor(Color.parseColor("#FFCCBC"));

            textViewsRow1Break5[i] = new TextView(getApplicationContext());
            textViewsRow1Break5[i].setLayoutParams(lp_match_match);
            textViewsRow1Break5[i].setGravity(Gravity.CENTER);
            textViewsRow1Break5[i].setTextSize(12);
            textViewsRow1Break5[i].setText("5 MIN");
            textViewsRow1Break5[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break5[i].setAllCaps(true);

            linearLayoutsInnerCardBreak5[i].addView(textViewsRow1Break5[i]);

            cardViewBreak5[i].addView(linearLayoutsInnerCardBreak5[i]);

            linearLayoutsColumnBreak5[i].addView(cardViewBreak5[i]);

            linearLayoutRowBreak5.addView(linearLayoutsColumnBreak5[i]);
        }

        cardViewBreak5[0].setCardBackgroundColor(Color.parseColor("#CFD8DC"));
        textViewsRow1Break5[0].setText("5 MIN");

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour6[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour6[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour6[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour6[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour6[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour6[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour6[i] = new CardView(getApplicationContext());
            cardViewHour6[i].setLayoutParams(lp_mainCard);
            cardViewHour6[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour6[i].setClickable(true);

            if(i != 0){

                cardViewHour6[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("6").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("6").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("6").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("6").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("6").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("6").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour6[i] = new TextView(getApplicationContext());
            textViewsRow1Hour6[i].setLayoutParams(lp3);
            textViewsRow1Hour6[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour6[i].setTextSize(18);
            textViewsRow1Hour6[i].setText("PRA");
            textViewsRow1Hour6[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour6[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour6[i].setAllCaps(true);

            textViewsRow2Hour6[i] = new TextView(getApplicationContext());
            textViewsRow2Hour6[i].setLayoutParams(lp4);
            textViewsRow2Hour6[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour6[i].setTextSize(13);
            textViewsRow2Hour6[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour6[i].setText("3.D (c)");

            textViewsRow3Hour6[i] = new TextView(getApplicationContext());
            textViewsRow3Hour6[i].setLayoutParams(lp4);
            textViewsRow3Hour6[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour6[i].setTextSize(12);
            textViewsRow3Hour6[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour6[i].setText("17");

            linearLayoutsInnerCardHour6[i].addView(textViewsRow1Hour6[i]);
            linearLayoutsInnerCardHour6[i].addView(textViewsRow2Hour6[i]);
            linearLayoutsInnerCardHour6[i].addView(textViewsRow3Hour6[i]);

            cardViewHour6[i].addView(linearLayoutsInnerCardHour6[i]);

            linearLayoutsColumnHour6[i].addView(cardViewHour6[i]);

            linearLayoutRowHour6.addView(linearLayoutsColumnHour6[i]);
        }

        cardViewHour6[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour6[0].setTextSize(16);
        textViewsRow1Hour6[0].setText("6. HOD");
        textViewsRow2Hour6[0].setText("10:15");
        textViewsRow3Hour6[0].setText("11:00");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak6[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak6[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak6[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak6[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak6[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak6[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak6[i] = new CardView(getApplicationContext());
            cardViewBreak6[i].setLayoutParams(lp_mainCard);
            cardViewBreak6[i].setCardBackgroundColor(Color.parseColor("#B0BEC5"));

            textViewsRow1Break6[i] = new TextView(getApplicationContext());
            textViewsRow1Break6[i].setLayoutParams(lp3);
            textViewsRow1Break6[i].setGravity(Gravity.CENTER);
            textViewsRow1Break6[i].setTextSize(13);
            textViewsRow1Break6[i].setText("David");
            textViewsRow1Break6[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Break6[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break6[i].setAllCaps(true);

            linearLayoutsInnerCardBreak6[i].addView(textViewsRow1Break6[i]);

            cardViewBreak6[i].addView(linearLayoutsInnerCardBreak6[i]);

            linearLayoutsColumnBreak6[i].addView(cardViewBreak6[i]);

            linearLayoutRowBreak6.addView(linearLayoutsColumnBreak6[i]);
        }

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour7[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour7[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour7[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour7[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour7[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour7[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour7[i] = new CardView(getApplicationContext());
            cardViewHour7[i].setLayoutParams(lp_mainCard);
            cardViewHour7[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour7[i].setClickable(true);

            if(i != 0){

                cardViewHour7[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("7").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("7").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("7").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("7").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("7").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("7").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour7[i] = new TextView(getApplicationContext());
            textViewsRow1Hour7[i].setLayoutParams(lp3);
            textViewsRow1Hour7[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour7[i].setTextSize(18);
            textViewsRow1Hour7[i].setText("PRA");
            textViewsRow1Hour7[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour7[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour7[i].setAllCaps(true);

            textViewsRow2Hour7[i] = new TextView(getApplicationContext());
            textViewsRow2Hour7[i].setLayoutParams(lp4);
            textViewsRow2Hour7[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour7[i].setTextSize(13);
            textViewsRow2Hour7[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour7[i].setText("3.D (c)");

            textViewsRow3Hour7[i] = new TextView(getApplicationContext());
            textViewsRow3Hour7[i].setLayoutParams(lp4);
            textViewsRow3Hour7[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour7[i].setTextSize(12);
            textViewsRow3Hour7[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour7[i].setText("17");

            linearLayoutsInnerCardHour7[i].addView(textViewsRow1Hour7[i]);
            linearLayoutsInnerCardHour7[i].addView(textViewsRow2Hour7[i]);
            linearLayoutsInnerCardHour7[i].addView(textViewsRow3Hour7[i]);

            cardViewHour7[i].addView(linearLayoutsInnerCardHour7[i]);

            linearLayoutsColumnHour7[i].addView(cardViewHour7[i]);

            linearLayoutRowHour7.addView(linearLayoutsColumnHour7[i]);
        }

        cardViewHour7[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour7[0].setTextSize(16);
        textViewsRow1Hour7[0].setText("7. HOD");
        textViewsRow2Hour7[0].setText("10:15");
        textViewsRow3Hour7[0].setText("11:00");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak7[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak7[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak7[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak7[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak7[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak7[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak7[i] = new CardView(getApplicationContext());
            cardViewBreak7[i].setLayoutParams(lp_mainCard);
            cardViewBreak7[i].setCardBackgroundColor(Color.parseColor("#B0BEC5"));

            textViewsRow1Break7[i] = new TextView(getApplicationContext());
            textViewsRow1Break7[i].setLayoutParams(lp3);
            textViewsRow1Break7[i].setGravity(Gravity.CENTER);
            textViewsRow1Break7[i].setTextSize(13);
            textViewsRow1Break7[i].setText("David");
            textViewsRow1Break7[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Break7[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break7[i].setAllCaps(true);

            linearLayoutsInnerCardBreak7[i].addView(textViewsRow1Break7[i]);

            cardViewBreak7[i].addView(linearLayoutsInnerCardBreak7[i]);

            linearLayoutsColumnBreak7[i].addView(cardViewBreak7[i]);

            linearLayoutRowBreak7.addView(linearLayoutsColumnBreak7[i]);
        }

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour8[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour8[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour8[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour8[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour8[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour8[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour8[i] = new CardView(getApplicationContext());
            cardViewHour8[i].setLayoutParams(lp_mainCard);
            cardViewHour8[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour8[i].setClickable(true);

            if(i != 0){

                cardViewHour8[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("8").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("8").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("8").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("8").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("8").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("8").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour8[i] = new TextView(getApplicationContext());
            textViewsRow1Hour8[i].setLayoutParams(lp3);
            textViewsRow1Hour8[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour8[i].setTextSize(18);
            textViewsRow1Hour8[i].setText("PRA");
            textViewsRow1Hour8[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour8[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour8[i].setAllCaps(true);

            textViewsRow2Hour8[i] = new TextView(getApplicationContext());
            textViewsRow2Hour8[i].setLayoutParams(lp4);
            textViewsRow2Hour8[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour8[i].setTextSize(13);
            textViewsRow2Hour8[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour8[i].setText("3.D (c)");

            textViewsRow3Hour8[i] = new TextView(getApplicationContext());
            textViewsRow3Hour8[i].setLayoutParams(lp4);
            textViewsRow3Hour8[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour8[i].setTextSize(12);
            textViewsRow3Hour8[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour8[i].setText("17");

            linearLayoutsInnerCardHour8[i].addView(textViewsRow1Hour8[i]);
            linearLayoutsInnerCardHour8[i].addView(textViewsRow2Hour8[i]);
            linearLayoutsInnerCardHour8[i].addView(textViewsRow3Hour8[i]);

            cardViewHour8[i].addView(linearLayoutsInnerCardHour8[i]);

            linearLayoutsColumnHour8[i].addView(cardViewHour8[i]);

            linearLayoutRowHour8.addView(linearLayoutsColumnHour8[i]);
        }

        cardViewHour8[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour8[0].setTextSize(16);
        textViewsRow1Hour8[0].setText("8. HOD");
        textViewsRow2Hour8[0].setText("10:15");
        textViewsRow3Hour8[0].setText("11:00");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak8[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak8[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak8[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak8[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak8[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak8[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak8[i] = new CardView(getApplicationContext());
            cardViewBreak8[i].setLayoutParams(lp_mainCard);
            cardViewBreak8[i].setCardBackgroundColor(Color.parseColor("#B0BEC5"));

            textViewsRow1Break8[i] = new TextView(getApplicationContext());
            textViewsRow1Break8[i].setLayoutParams(lp3);
            textViewsRow1Break8[i].setGravity(Gravity.CENTER);
            textViewsRow1Break8[i].setTextSize(13);
            textViewsRow1Break8[i].setText("David");
            textViewsRow1Break8[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Break8[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break8[i].setAllCaps(true);

            linearLayoutsInnerCardBreak8[i].addView(textViewsRow1Break8[i]);

            cardViewBreak8[i].addView(linearLayoutsInnerCardBreak8[i]);

            linearLayoutsColumnBreak8[i].addView(cardViewBreak8[i]);

            linearLayoutRowBreak8.addView(linearLayoutsColumnBreak8[i]);
        }

        for(int i = 0; i < size; i++){

            final int finalI = i;

            linearLayoutsColumnHour9[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnHour9[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnHour9[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardHour9[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardHour9[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardHour9[i].setOrientation(LinearLayout.VERTICAL);

            cardViewHour9[i] = new CardView(getApplicationContext());
            cardViewHour9[i].setLayoutParams(lp_mainCard);
            cardViewHour9[i].setCardBackgroundColor(Color.parseColor("#EEEEEE"));
            cardViewHour9[i].setClickable(true);

            if(i != 0){

                cardViewHour9[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("kokot3","hour0 " +  names.get(finalI-1));

                        String lolo = names.get(finalI-1);

                        String classRoom = dataSnapshot_global.child("16_9").child("9").child(lolo).child("Ucebna").getValue(String.class);
                        String subjectTitle = dataSnapshot_global.child("16_9").child("9").child(lolo).child("Predmet").getValue(String.class);
                        String nameSurname = dataSnapshot_global.child("16_9").child("9").child(lolo).child("Ucitel").getValue(String.class);
                        String classclass = dataSnapshot_global.child("16_9").child("9").child(lolo).child("Trida").getValue(String.class);
                        String classStudents = dataSnapshot_global.child("16_9").child("9").child(lolo).child("Skupina").getValue(String.class);
                        String cycle = dataSnapshot_global.child("16_9").child("9").child(lolo).child("Cyklus").getValue(String.class);

                        //ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(classRoom, subjectTitle, nameSurname, classclass, classStudents, cycle );
                        //activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                    }
                });
            }

            textViewsRow1Hour9[i] = new TextView(getApplicationContext());
            textViewsRow1Hour9[i].setLayoutParams(lp3);
            textViewsRow1Hour9[i].setGravity(Gravity.CENTER);
            textViewsRow1Hour9[i].setTextSize(18);
            textViewsRow1Hour9[i].setText("PRA");
            textViewsRow1Hour9[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Hour9[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Hour9[i].setAllCaps(true);

            textViewsRow2Hour9[i] = new TextView(getApplicationContext());
            textViewsRow2Hour9[i].setLayoutParams(lp4);
            textViewsRow2Hour9[i].setGravity(Gravity.CENTER);
            textViewsRow2Hour9[i].setTextSize(13);
            textViewsRow2Hour9[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow2Hour9[i].setText("3.D (c)");

            textViewsRow3Hour9[i] = new TextView(getApplicationContext());
            textViewsRow3Hour9[i].setLayoutParams(lp4);
            textViewsRow3Hour9[i].setGravity(Gravity.CENTER);
            textViewsRow3Hour9[i].setTextSize(12);
            textViewsRow3Hour9[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow3Hour9[i].setText("17");

            linearLayoutsInnerCardHour9[i].addView(textViewsRow1Hour9[i]);
            linearLayoutsInnerCardHour9[i].addView(textViewsRow2Hour9[i]);
            linearLayoutsInnerCardHour9[i].addView(textViewsRow3Hour9[i]);

            cardViewHour9[i].addView(linearLayoutsInnerCardHour9[i]);

            linearLayoutsColumnHour9[i].addView(cardViewHour9[i]);

            linearLayoutRowHour9.addView(linearLayoutsColumnHour9[i]);
        }

        cardViewHour9[0].setCardBackgroundColor(Color.parseColor("#B0BEC5"));
        textViewsRow1Hour9[0].setTextSize(16);
        textViewsRow1Hour9[0].setText("9. HOD");
        textViewsRow2Hour9[0].setText("10:15");
        textViewsRow3Hour9[0].setText("11:00");

        for(int i = 0; i < size; i++){

            linearLayoutsColumnBreak9[i] = new LinearLayout(getApplicationContext());
            linearLayoutsColumnBreak9[i].setLayoutParams(lp_mainCell);
            linearLayoutsColumnBreak9[i].setOrientation(LinearLayout.VERTICAL);

            linearLayoutsInnerCardBreak9[i] = new LinearLayout(getApplicationContext());
            linearLayoutsInnerCardBreak9[i].setLayoutParams(lp_match_match);
            linearLayoutsInnerCardBreak9[i].setOrientation(LinearLayout.VERTICAL);

            cardViewBreak9[i] = new CardView(getApplicationContext());
            cardViewBreak9[i].setLayoutParams(lp_mainCard);
            cardViewBreak9[i].setCardBackgroundColor(Color.parseColor("#B0BEC5"));

            textViewsRow1Break9[i] = new TextView(getApplicationContext());
            textViewsRow1Break9[i].setLayoutParams(lp3);
            textViewsRow1Break9[i].setGravity(Gravity.CENTER);
            textViewsRow1Break9[i].setTextSize(13);
            textViewsRow1Break9[i].setText("David");
            textViewsRow1Break9[i].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Break9[i].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Break9[i].setAllCaps(true);

            linearLayoutsInnerCardBreak9[i].addView(textViewsRow1Break9[i]);

            cardViewBreak9[i].addView(linearLayoutsInnerCardBreak9[i]);

            linearLayoutsColumnBreak9[i].addView(cardViewBreak9[i]);

            linearLayoutRowBreak9.addView(linearLayoutsColumnBreak9[i]);
        }






       // cardViewHour0[0].setCardBackgroundColor(Color.parseColor("#EEEEEE"));


















/*


        rozvrh_card_hour1_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour1_teacher1);
        rozvrh_card_hour2_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour2_teacher1);
        rozvrh_card_hour3_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour3_teacher1);
        rozvrh_card_hour4_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour4_teacher1);
        rozvrh_card_hour5_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour5_teacher1);
        //rozvrh_card_hour6_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour6_teacher1);
        rozvrh_card_hour7_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour7_teacher1);
        rozvrh_card_hour8_teacher1 = (CardView)findViewById(R.id.rozvrh_card_hour8_teacher1);
        rozvrh_card_hour1_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour1_teacher2);
        rozvrh_card_hour2_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour2_teacher2);
        rozvrh_card_hour3_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour3_teacher2);
        rozvrh_card_hour4_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour4_teacher2);
        rozvrh_card_hour5_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour5_teacher2);
        //rozvrh_card_hour6_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour6_teacher2);
        rozvrh_card_hour7_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour7_teacher2);
        rozvrh_card_hour8_teacher2 = (CardView)findViewById(R.id.rozvrh_card_hour8_teacher2);

        rozvrh_subject_hour1_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour1_teacher1);
        rozvrh_subject_hour2_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour2_teacher1);
        rozvrh_subject_hour3_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour3_teacher1);
        rozvrh_subject_hour4_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour4_teacher1);
        rozvrh_subject_hour5_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour5_teacher1);
        //rozvrh_subject_hour6_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour6_teacher1);
        rozvrh_subject_hour7_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour7_teacher1);
        rozvrh_subject_hour8_teacher1 = (TextView)findViewById(R.id.rozvrh_subject_hour8_teacher1);
        rozvrh_subject_hour1_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour1_teacher2);
        rozvrh_subject_hour2_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour2_teacher2);
        rozvrh_subject_hour3_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour3_teacher2);
        rozvrh_subject_hour4_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour4_teacher2);
        rozvrh_subject_hour5_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour5_teacher2);
        //rozvrh_subject_hour6_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour6_teacher2);
        rozvrh_subject_hour7_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour7_teacher2);
        rozvrh_subject_hour8_teacher2 = (TextView)findViewById(R.id.rozvrh_subject_hour8_teacher2);

        rozvrh_class_hour1_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour1_teacher1);
        rozvrh_class_hour2_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour2_teacher1);
        rozvrh_class_hour3_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour3_teacher1);
        rozvrh_class_hour4_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour4_teacher1);
        rozvrh_class_hour5_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour5_teacher1);
        //rozvrh_class_hour6_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour6_teacher1);
        rozvrh_class_hour7_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour7_teacher1);
        rozvrh_class_hour8_teacher1 = (TextView)findViewById(R.id.rozvrh_class_hour8_teacher1);
        rozvrh_class_hour1_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour1_teacher2);
        rozvrh_class_hour2_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour2_teacher2);
        rozvrh_class_hour3_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour3_teacher2);
        rozvrh_class_hour4_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour4_teacher2);
        rozvrh_class_hour5_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour5_teacher2);
        //rozvrh_class_hour6_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour6_teacher2);
        rozvrh_class_hour7_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour7_teacher2);
        rozvrh_class_hour8_teacher2 = (TextView)findViewById(R.id.rozvrh_class_hour8_teacher2);

        rozvrh_room_hour1_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour1_teacher1);
        rozvrh_room_hour2_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour2_teacher1);
        rozvrh_room_hour3_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour3_teacher1);
        rozvrh_room_hour4_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour4_teacher1);
        rozvrh_room_hour5_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour5_teacher1);
        //rozvrh_room_hour6_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour6_teacher1);
        rozvrh_room_hour7_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour7_teacher1);
        rozvrh_room_hour8_teacher1 = (TextView)findViewById(R.id.rozvrh_room_hour8_teacher1);
        rozvrh_room_hour1_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour1_teacher2);
        rozvrh_room_hour2_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour2_teacher2);
        rozvrh_room_hour3_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour3_teacher2);
        rozvrh_room_hour4_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour4_teacher2);
        rozvrh_room_hour5_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour5_teacher2);
        //rozvrh_room_hour6_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour6_teacher2);
        rozvrh_room_hour7_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour7_teacher2);
        rozvrh_room_hour8_teacher2 = (TextView)findViewById(R.id.rozvrh_room_hour8_teacher2);

        rozvrh_freeroom_hour1 = (TextView)findViewById(R.id.rozvrh_freeroom_hour1);
        rozvrh_freeroom_hour2 = (TextView)findViewById(R.id.rozvrh_freeroom_hour2);
        rozvrh_freeroom_hour3 = (TextView)findViewById(R.id.rozvrh_freeroom_hour3);
        rozvrh_freeroom_hour4 = (TextView)findViewById(R.id.rozvrh_freeroom_hour4);
        rozvrh_freeroom_hour5 = (TextView)findViewById(R.id.rozvrh_freeroom_hour5);
        //rozvrh_freeroom_hour6 = (TextView)findViewById(R.id.rozvrh_freeroom_hour6);
        rozvrh_freeroom_hour7 = (TextView)findViewById(R.id.rozvrh_freeroom_hour7);
        rozvrh_freeroom_hour8 = (TextView)findViewById(R.id.rozvrh_freeroom_hour8);

















        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e(TAG, "color ___________________ " + dataSnapshot.child("16_9").child("1") );



                // --------------------------------------------------------------------------------- hodina 1
                try{
                    rozvrh_subject_hour1_teacher1.setText(dataSnapshot.child("16_9").child("1").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour1_teacher1.setText(dataSnapshot.child("16_9").child("1").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour1_teacher1.setText(dataSnapshot.child("16_9").child("1").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour1_teacher1.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 2
                try{
                    rozvrh_subject_hour2_teacher1.setText(dataSnapshot.child("16_9").child("2").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour2_teacher1.setText(dataSnapshot.child("16_9").child("2").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour2_teacher1.setText(dataSnapshot.child("16_9").child("2").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour2_teacher1.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 3
                try{
                    rozvrh_subject_hour3_teacher1.setText(dataSnapshot.child("16_9").child("3").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour3_teacher1.setText(dataSnapshot.child("16_9").child("3").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour3_teacher1.setText(dataSnapshot.child("16_9").child("3").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour3_teacher1.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 4
                try{
                    rozvrh_subject_hour4_teacher1.setText(dataSnapshot.child("16_9").child("4").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour4_teacher1.setText(dataSnapshot.child("16_9").child("4").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour4_teacher1.setText(dataSnapshot.child("16_9").child("4").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour4_teacher1.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 5
                try{
                    rozvrh_subject_hour5_teacher1.setText(dataSnapshot.child("16_9").child("5").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour5_teacher1.setText(dataSnapshot.child("16_9").child("5").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour5_teacher1.setText(dataSnapshot.child("16_9").child("5").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour5_teacher1.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 6

                // --------------------------------------------------------------------------------- hodina 7
                try{
                    rozvrh_subject_hour7_teacher1.setText(dataSnapshot.child("16_9").child("7").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour7_teacher1.setText(dataSnapshot.child("16_9").child("7").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour7_teacher1.setText(dataSnapshot.child("16_9").child("7").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour7_teacher1.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 8
                try{
                    rozvrh_subject_hour8_teacher1.setText(dataSnapshot.child("16_9").child("8").child("Vašíček_David_Vaš").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour8_teacher1.setText(dataSnapshot.child("16_9").child("8").child("Vašíček_David_Vaš").child("Trida").getValue(String.class));
                    rozvrh_room_hour8_teacher1.setText(dataSnapshot.child("16_9").child("8").child("Vašíček_David_Vaš").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour8_teacher1.setVisibility(View.GONE);
                }


                // ---------------------------------------------------------------------------------------------------------------------------------------------------------

                // --------------------------------------------------------------------------------- hodina 1
                try{
                    rozvrh_subject_hour1_teacher2.setText(dataSnapshot.child("16_9").child("1").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour1_teacher2.setText(dataSnapshot.child("16_9").child("1").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour1_teacher2.setText(dataSnapshot.child("16_9").child("1").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour1_teacher2.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 2
                try{
                    rozvrh_subject_hour2_teacher2.setText(dataSnapshot.child("16_9").child("2").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour2_teacher2.setText(dataSnapshot.child("16_9").child("2").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour2_teacher2.setText(dataSnapshot.child("16_9").child("2").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour2_teacher2.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 3
                try{
                    rozvrh_subject_hour3_teacher2.setText(dataSnapshot.child("16_9").child("3").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour3_teacher2.setText(dataSnapshot.child("16_9").child("3").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour3_teacher2.setText(dataSnapshot.child("16_9").child("3").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour3_teacher2.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 4
                try{
                    rozvrh_subject_hour4_teacher2.setText(dataSnapshot.child("16_9").child("4").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour4_teacher2.setText(dataSnapshot.child("16_9").child("4").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour4_teacher2.setText(dataSnapshot.child("16_9").child("4").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour4_teacher2.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 5
                try{
                    rozvrh_subject_hour5_teacher2.setText(dataSnapshot.child("16_9").child("5").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour5_teacher2.setText(dataSnapshot.child("16_9").child("5").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour5_teacher2.setText(dataSnapshot.child("16_9").child("5").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour5_teacher2.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 6

                // --------------------------------------------------------------------------------- hodina 7
                try{
                    rozvrh_subject_hour7_teacher2.setText(dataSnapshot.child("16_9").child("7").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour7_teacher2.setText(dataSnapshot.child("16_9").child("7").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour7_teacher2.setText(dataSnapshot.child("16_9").child("7").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour7_teacher2.setVisibility(View.GONE);
                }

                // --------------------------------------------------------------------------------- hodina 8
                try{
                    rozvrh_subject_hour8_teacher2.setText(dataSnapshot.child("16_9").child("8").child("Glacová_Lenka_Gla").child("Predmet").getValue(String.class).split(" ")[0]);
                    rozvrh_class_hour8_teacher2.setText(dataSnapshot.child("16_9").child("8").child("Glacová_Lenka_Gla").child("Trida").getValue(String.class));
                    rozvrh_room_hour8_teacher2.setText(dataSnapshot.child("16_9").child("8").child("Glacová_Lenka_Gla").child("Ucebna").getValue(String.class));
                }catch (NullPointerException e){
                    rozvrh_card_hour8_teacher2.setVisibility(View.GONE);
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                // TODO
                //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

            }
        });

*/

    }
}
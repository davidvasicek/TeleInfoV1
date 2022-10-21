package com.example.teleinfo.rozvrh;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.CLASSIC_HOUR_IN_MINUTES;
import static com.example.teleinfo.parameters.MainParameters.COLOR_CLASSIC_HOUR;
import static com.example.teleinfo.parameters.MainParameters.COLOR_CLASSIC_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_COLUMN_HEADER;
import static com.example.teleinfo.parameters.MainParameters.COLOR_DINNINGROOM_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_MIDDAY_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_MORNING_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_PORTER_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_PPP_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_BREAK;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_HOUR;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DATA;
import static com.example.teleinfo.parameters.MainParameters.INIT;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;
import static com.example.teleinfo.parameters.MainParameters.NO_DATA;
import static com.example.teleinfo.parameters.MainParameters.NO_INTERNET;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SHOW_BREAKS;
import static com.example.teleinfo.parameters.MainParameters.SHOW_TIME_LINE;
import static com.example.teleinfo.parameters.MainParameters.SIXTH_HOUR_IN_MINUTES;
import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.example.teleinfo.parameters.IsOnline;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RozvrhNove1 extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{


    int velColumn = (int)(190*1); //šířa slpoupce

    int velHeaderu = 150;
    int velKlasickeHod = (int)(190*1);
    int velSesteHod = (velKlasickeHod*SIXTH_HOUR_IN_MINUTES)/CLASSIC_HOUR_IN_MINUTES; // klasicka hod 45min, velka hodina 70min
    int velVrat = (velKlasickeHod*25)/CLASSIC_HOUR_IN_MINUTES; // klasicka hod 45min, velka hodina 70min
    int velMorningSupervision = (velKlasickeHod*10)/CLASSIC_HOUR_IN_MINUTES;

    //int velMalePrestavky = 0;
    //int velStredniPrestavky = 0;
    //int velVelkePrestavky = 0;

    int velMalePrestavky = 60;
    int velStredniPrestavky = 90;
    int velVelkePrestavky = 120;

    int velSupervision6ab = (velSesteHod*25)/SIXTH_HOUR_IN_MINUTES + velMalePrestavky;

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

    String Hour9Title = "9. HOD";
    String Hour9From = "15:40";
    String Hour9To = "16:25";

    String Break0Title = "5 MIN";
    String Break0From = "8:10";
    String Break0To = "8:15";

    String Break1Title = "10 MIN";
    String Break1From = "9:00";
    String Break1To = "9:15";

    String Break2Title = "20 MIN";
    String Break2From = "9:55";
    String Break2To = "10:15";

    String Break3Title = "5 MIN";
    String Break3From = "11:00";
    String Break3To = "11:05";

    String Break4Title = "5 MIN";
    String Break4From = "11:50";
    String Break4To = "11:55";

    String Break5Title = "5 MIN";
    String Break5From = "12:40";
    String Break5To = "12:45";

    String Break6Title = "5 MIN";
    String Break6From = "13:55";
    String Break6To = "14:00";

    String Break7Title = "5 MIN";
    String Break7From = "14:45";
    String Break7To = "14:50";

    String Break8Title = "5 MIN";
    String Break8From = "15:35";
    String Break8To = "15:40";

    String Break9sTitle = "5 MIN";
    String Break9From = "16:25";
    String Break9To = "16:30";


    int marginHour0;
    int marginHour1;
    int marginHour2;
    int marginHour3;
    int marginHour4;
    int marginHour5;
    int marginHour6;
    int marginHour7;
    int marginHour8;
    int marginHour9;

    int marginBreak0;
    int marginBreak1;
    int marginBreak2;
    int marginBreak3;
    int marginBreak4;
    int marginBreak5;
    int marginBreak6;
    int marginBreak7;
    int marginBreak8;
    int marginBreak9;

    int marginBreak2a;
    int marginBreak2b;

    int marginLunch6a;
    int marginLunch6b;
    int marginLunch6middle;


    int columnSize;

    LinearLayout linearLayoutMain;
    LinearLayout llllllllll;
    RelativeLayout kkkkkkkkkk;


    LinearLayout linearLayoutsColumn[];
    RelativeLayout relativeLayoutsColumn[];

    LinearLayout linearLayoutsColumnCellHeader[];
    CardView cardViewHeader[];
    LinearLayout linearLayoutsInnerCardHeader[];
    TextView textViewsRowHeader[];

    LinearLayout linearLayoutsCornerHeader;
    CardView cardViewCornerHeader;

    LinearLayout linearLayoutsColumnCellHour[];
    CardView cardViewHour[];
    LinearLayout linearLayoutsInnerCardHour[];
    RelativeLayout relativeLayoutInnerCardHour[];
    TextView textViewsRow1Hour[];
    TextView textViewsRow2Hour[];
    TextView textViewsRow3Hour[];

    LinearLayout linearLayoutsColumnCellOthers[];
    CardView cardViewOthers[];
    LinearLayout linearLayoutsInnerCardOthers[];
    RelativeLayout relativeLayoutInnerCardOthers[];
    TextView textViewsRow1Others[];
    TextView textViewsRow2Others[];
    TextView textViewsRow3Others[];

    LinearLayout linearLayoutsColumnCellBreak[];
    CardView cardViewBreak[];
    LinearLayout linearLayoutsInnerCardBreak[];
    TextView textViewsRowBreak[];




    LinearLayout linearLayoutsColumnCellHourHeader[];
    CardView cardViewHourHeader[];
    RelativeLayout relativeLayoutsInnerCardHourHeader[];
    TextView textViewsRow1HourHeader[];
    TextView textViewsRow2HourHeader[];
    TextView textViewsRow3HourHeader[];

    LinearLayout linearLayoutsColumnCellBreakHeader[];
    CardView cardViewBreakHeader[];
    RelativeLayout relativeLayoutInnerCardBreak[];
    LinearLayout linearLayoutsInnerCardBreakHeader[];
    TextView textViewsRowBreakHeader[];

    LinearLayout linearLayoutsLineBorderHeader[];

    LinearLayout linearLayoutsLineBorderHour0[];
    LinearLayout linearLayoutsLineBorderHour1[];
    LinearLayout linearLayoutsLineBorderHour2[];
    LinearLayout linearLayoutsLineBorderHour3[];
    LinearLayout linearLayoutsLineBorderHour4[];
    LinearLayout linearLayoutsLineBorderHour5[];
    LinearLayout linearLayoutsLineBorderHour6[];
    LinearLayout linearLayoutsLineBorderHour7[];
    LinearLayout linearLayoutsLineBorderHour8[];
    LinearLayout linearLayoutsLineBorderHour9[];

    LinearLayout linearLayoutsLineBorderBreak0[];
    LinearLayout linearLayoutsLineBorderBreak1[];
    LinearLayout linearLayoutsLineBorderBreak2[];
    LinearLayout linearLayoutsLineBorderBreak3[];
    LinearLayout linearLayoutsLineBorderBreak4[];
    LinearLayout linearLayoutsLineBorderBreak5[];
    LinearLayout linearLayoutsLineBorderBreak6[];
    LinearLayout linearLayoutsLineBorderBreak7[];
    LinearLayout linearLayoutsLineBorderBreak8[];
    LinearLayout linearLayoutsLineBorderBreak9[];

    LinearLayout linearLayoutsLineBorderTest[];
    ImageView imageViewsLineBorderTest[];

    LinearLayout linearLayoutsColumnCellOther[];
    CardView cardViewOther[];
    LinearLayout linearLayoutsInnerCardOther[];
    TextView textViewsRowOther[];


    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DataSnapshot dataSnapshot_global;

    List<String> teachers = new ArrayList<>();
    List<String> Schoolroom = new ArrayList<>();
    List<String> tridy = new ArrayList<>();
    List<String> dates = new ArrayList<>();
    List<Date> datesToShow = new ArrayList<>();

    List<String> datumyNasmazani = new ArrayList<>();

    LinearLayout.LayoutParams lp3;
    LinearLayout.LayoutParams lp4;
    LinearLayout.LayoutParams lp5;
    LinearLayout.LayoutParams lp_mainCard;
    LinearLayout.LayoutParams lp_match_match;


    List<String> hodiny = new ArrayList<>();

    Intent intent;
    String action;
    String databaseStringReference;

    int actualIndexOfHour = 0;
    int actualIndexOfBreak = 0;
    int actualIndexOfOthers = 0;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    LinearLayout rozvrh_rozvrh;
    LinearLayout rozvrh_nacitaniDat;
    AVLoadingIndicatorView rozvrh_loadingIndicator;
    Button rozvrh_buttonRefresh;
    TextView rozvrh_text2;
    TextView rozvrh_text1;


    Handler handler;



    int lolo = 0;


    Menu mMenu;

String child="";
    int listingDates = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(mSharPref.getString(CURRENT_THEME, "#212121")));;
        setContentView(R.layout.rozvrh_activity_rozvrh_nove1);

        mSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);


        rozvrh_rozvrh = (LinearLayout) findViewById(R.id.rozvrh_rozvrh);
        rozvrh_nacitaniDat = (LinearLayout) findViewById(R.id.rozvrh_nacitaniDat);
        rozvrh_loadingIndicator = (AVLoadingIndicatorView) findViewById(R.id.rozvrh_loadingIndicator);
        rozvrh_buttonRefresh = (Button) findViewById(R.id.rozvrh_buttonRefresh);
        rozvrh_text2 = (TextView) findViewById(R.id.rozvrh_text2);
        rozvrh_text1 = (TextView) findViewById(R.id.rozvrh_text1);

        intent = getIntent();
        action = intent.getStringExtra("action");

        datumyNasmazani = new ArrayList<>();


        datumyNasmazani.add("21_10_2022");
        datumyNasmazani.add("20_10_2022");
        datumyNasmazani.add("19_10_2022");
        datumyNasmazani.add("18_10_2022");
        datumyNasmazani.add("17_10_2022");

        datumyNasmazani.add("14_10_2022");
        datumyNasmazani.add("13_10_2022");
        datumyNasmazani.add("12_10_2022");
        datumyNasmazani.add("11_10_2022");
        datumyNasmazani.add("10_10_2022");

        datumyNasmazani.add("7_10_2022");
        datumyNasmazani.add("6_10_2022");
        datumyNasmazani.add("5_10_2022");
        datumyNasmazani.add("4_10_2022");
        datumyNasmazani.add("3_10_2022");

        datumyNasmazani.add("30_9_2022");
        datumyNasmazani.add("29_9_2022");
        datumyNasmazani.add("28_9_2022");
        datumyNasmazani.add("27_9_2022");
        datumyNasmazani.add("26_9_2022");

        datumyNasmazani.add("23_9_2022");
        datumyNasmazani.add("22_9_2022");
        datumyNasmazani.add("21_9_2022");
        datumyNasmazani.add("20_9_2022");
        datumyNasmazani.add("19_9_2022");

        datumyNasmazani.add("16_9_2022");
        datumyNasmazani.add("15_9_2022");
        datumyNasmazani.add("14_9_2022");
        datumyNasmazani.add("13_9_2022");
        datumyNasmazani.add("12_9_2022");

        datumyNasmazani.add("9_9_2022");
        datumyNasmazani.add("8_9_2022");
        datumyNasmazani.add("7_9_2022");
        datumyNasmazani.add("6_9_2022");
        datumyNasmazani.add("5_9_2022");

        Toolbar toolbar = (Toolbar) findViewById(R.id.abcdtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp3.setMargins(0,10,0,10);

        lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp4.setMargins(0,5,0,5);

        lp5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp5.setMargins(0,0,0,0);

        lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView
        
        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();


        if(action.compareTo("dailyByClass") == 0){

            child = "rozvrh/denniTrid";

        }else if(action.compareTo("dailyByschoolroom") == 0){

            child = "rozvrh/denniUceben";

        }else if(action.compareTo("dailyByTeachers") == 0){

            child = "rozvrh/denniUcitelu";

            Log.e(TAG, "color ___________________ kkkk" );
            getSupportActionBar().setTitle(Html.fromHtml("<small>" + datumyNasmazani.get(listingDates) + "</small>"));

        }else if(action.compareTo("weeklyBySchoolroom") == 0){

            child = "rozvrh/denniUceben";
            getSupportActionBar().setTitle(Html.fromHtml("<small>" + datumyNasmazani.get(listingDates) + "</small>"));


        }else if(action.compareTo("weeklyByTeacher") == 0){

            child = "rozvrh/denniUcitelu";
            getSupportActionBar().setTitle(Html.fromHtml("<small>" + datumyNasmazani.get(listingDates) + "</small>"));


        }else if(action.compareTo("weeklyBySchoolClass") == 0){

            child = "rozvrh/denniTrid";

        }

        Loaddata();

    }




    private void CheckIsListEmpty(int action){

        if(action == INIT){

            rozvrh_rozvrh.setVisibility(View.GONE);
            rozvrh_nacitaniDat.setVisibility(View.VISIBLE);
            rozvrh_loadingIndicator.setVisibility(View.VISIBLE);
            rozvrh_buttonRefresh.setVisibility(View.GONE);
            rozvrh_text1.setVisibility(View.VISIBLE);
            rozvrh_text2.setVisibility(View.GONE);

            rozvrh_text1.setText("Načítání rozvrhu");

        }else if(action == NO_INTERNET){

            rozvrh_rozvrh.setVisibility(View.GONE);
            rozvrh_nacitaniDat.setVisibility(View.VISIBLE);
            rozvrh_loadingIndicator.setVisibility(View.GONE);
            rozvrh_buttonRefresh.setVisibility(View.VISIBLE);
            rozvrh_text1.setVisibility(View.VISIBLE);
            rozvrh_text2.setVisibility(View.VISIBLE);

            rozvrh_text1.setText("Nepodařilo se načíst aktuální rozvrh");
            rozvrh_text2.setText("Zkontrolujte připojení k internetu");

        }else if(action == NO_DATA){

            rozvrh_rozvrh.setVisibility(View.GONE);
            rozvrh_nacitaniDat.setVisibility(View.VISIBLE);
            rozvrh_loadingIndicator.setVisibility(View.GONE);
            rozvrh_buttonRefresh.setVisibility(View.GONE);
            rozvrh_text1.setVisibility(View.VISIBLE);
            rozvrh_text2.setVisibility(View.VISIBLE);

            rozvrh_text1.setText("Nepodařilo se načíst data z databáze");
            rozvrh_text2.setText("Databáze je prázdná");

        }

        else if(action == DATA){

            rozvrh_rozvrh.setVisibility(View.VISIBLE);
            rozvrh_nacitaniDat.setVisibility(View.GONE);
            rozvrh_loadingIndicator.setVisibility(View.GONE);
            rozvrh_buttonRefresh.setVisibility(View.GONE);
            rozvrh_text1.setVisibility(View.GONE);
            rozvrh_text2.setVisibility(View.GONE);

            rozvrh_text1.setText("Nepodařilo se načíst data z databáze");
            rozvrh_text2.setText("Databáze je prázdná");
        }
    }

    private void Loaddata(){

        if(new IsOnline(getApplicationContext()).isOnline() == IS_ONLINE){

            CheckIsListEmpty(INIT);

            Log.e(TAG, "color ___________________ kgggggggggggkkk" );

            mDatabaseReference = mFirebaseDatabaseDatabase.getReference(child);
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.e(TAG, "color ___________________ ffffffffffff" );
                    dataSnapshot_global = dataSnapshot;

                    if(dataSnapshot_global != null){

                        CheckIsListEmpty(DATA);
                        createTimeTable(dataSnapshot_global);

                    }else{

                        CheckIsListEmpty(NO_DATA);
                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // TODO
                    //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                    //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

                    Log.e(TAG, "color ___________________ " + databaseError );

                }
            });

        }else{

            CheckIsListEmpty(NO_INTERNET);

        }

    }











































    public void createTimeTable(DataSnapshot dataSnapshot){

    actualIndexOfHour = 0;
    actualIndexOfBreak = 0;
    actualIndexOfOthers = 0;

    if(action.compareTo("dailyByClass") == 0){    // ------------------------------------------------------------------------- Denní podle tříd

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Denní dle tříd" + "</small>"));

        if(!mSharedPreferences.getBoolean(SHOW_BREAKS, true)){

            velMalePrestavky = 0;
            velStredniPrestavky = 0;
            velVelkePrestavky = 0;
        }

        initMargins();

        tridy = new ArrayList<>();

        tridy.add("1.A");
        tridy.add("1.B");
        tridy.add("1.C");
        tridy.add("1.D");
        tridy.add("2.A");
        tridy.add("2.B");
        tridy.add("2.C");
        tridy.add("2.D");
        tridy.add("3.A");
        tridy.add("3.B");
        tridy.add("3.C");
        tridy.add("3.D");
        tridy.add("4.A");
        tridy.add("4.B");
        tridy.add("4.C");
        tridy.add("4.D");

        columnSize = tridy.size();
        initViewElement();
        createLeftHeader();

        rozvrh_rozvrh.setVisibility(View.VISIBLE);
        rozvrh_nacitaniDat.setVisibility(View.GONE);
        rozvrh_loadingIndicator.setVisibility(View.GONE);
        rozvrh_buttonRefresh.setVisibility(View.GONE);
        rozvrh_text2.setVisibility(View.GONE);
        rozvrh_text1.setVisibility(View.GONE);

        // --------------- zjisti, kolik budes vytvaret jednotlivych LinearLayoutu ------------------------

        int viewCardSizeHour = 0; // počet jednotlivých hodin k zobrazení
        int viewCardSizeOther = 0; // počet jednotlivých jiných událostí k zobrazení

        for (DataSnapshot dataSnapshott: dataSnapshot.getChildren()) {

            for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").getChildren()) {

                for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeHour++;
                }
            }

            for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").getChildren()) {

                for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                    viewCardSizeOther++;
                }
            }
        }

                Log.e(TAG, "color ___________________ " + viewCardSizeHour );
                Log.e(TAG, "coloroth ___________________ " + viewCardSizeOther );

                // inicializuj potřebný počet všech prvků, které se budou zobrazovat
                linearLayoutsColumnCellHour = new LinearLayout[viewCardSizeHour];
                cardViewHour = new CardView[viewCardSizeHour];
                linearLayoutsInnerCardHour = new LinearLayout[viewCardSizeHour];
                relativeLayoutInnerCardHour = new RelativeLayout[viewCardSizeHour];
                textViewsRow1Hour = new TextView[viewCardSizeHour];
                textViewsRow2Hour = new TextView[viewCardSizeHour];
                textViewsRow3Hour = new TextView[viewCardSizeHour];

                linearLayoutsColumnCellOthers = new LinearLayout[viewCardSizeOther];
                cardViewOthers = new CardView[viewCardSizeOther];
                linearLayoutsInnerCardOthers = new LinearLayout[viewCardSizeOther];
                relativeLayoutInnerCardOthers = new RelativeLayout[viewCardSizeOther];
                textViewsRow1Others = new TextView[viewCardSizeOther];
                textViewsRow2Others = new TextView[viewCardSizeOther];
                textViewsRow3Others = new TextView[viewCardSizeOther];

                // Vytvoř jednotlivé sloupce podle počtu zobrazovaných tříd
                for(int i = 0; i < columnSize ; i++){

                    String room = tridy.get(i);

                    createColumn(i);
                    createBorder(i);

                    addColumnHeader(i, room);

                }

                for (DataSnapshot dataSnapshott: dataSnapshot.getChildren()) {

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;
                            String novaTrida;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                novaTrida = dataSnapshott.getKey().replace('_', '.');

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(getIndexOfClass(novaTrida), hourObject, hourObject.Ucebna, count, "");
                                count++;
                            }

                        }else{

                            String novaTrida;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                novaTrida = dataSnapshott.getKey().replace('_', '.');

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(getIndexOfClass(novaTrida), hourObject, hourObject.Ucebna, 0, "");
                            }
                        }
                    }

                    // Zobraz všechny potřebné prvky
                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;
                            String novaTrida;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                novaTrida = dataSnapshott.getKey().replace('_', '.');

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(getIndexOfClass(novaTrida), othersObject, othersObject.Ucebna, count, "");
                                count++;
                            }

                        }else{

                            String novaTrida;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                novaTrida = dataSnapshott.getKey().replace('_', '.');

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(getIndexOfClass(novaTrida), othersObject, othersObject.Ucebna, 0, "");
                            }
                        }
                    }
                }
    }

    else if(action.compareTo("dailyByschoolroom") == 0){    // ------------------------------------------------------------------------- Denní podle učeben

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Denní dle učeben" + "</small>"));

        if(!mSharedPreferences.getBoolean(SHOW_BREAKS, true)){

            velMalePrestavky = 0;
            velStredniPrestavky = 0;
            velVelkePrestavky = 0;
        }

        initMargins();


        Schoolroom = new ArrayList<>();

        Schoolroom.add("1");
        Schoolroom.add("3");
        Schoolroom.add("4");
        Schoolroom.add("5");
        Schoolroom.add("6");
        Schoolroom.add("7");
        Schoolroom.add("8");
        Schoolroom.add("9");
        Schoolroom.add("10");
        Schoolroom.add("11");
        Schoolroom.add("12");
        Schoolroom.add("13");
        Schoolroom.add("14");
        Schoolroom.add("15");
        Schoolroom.add("16");
        Schoolroom.add("17");
        Schoolroom.add("18");
        Schoolroom.add("25");
        Schoolroom.add("26");
        Schoolroom.add("27");
        Schoolroom.add("28");
        Schoolroom.add("30");
        Schoolroom.add("31");
        Schoolroom.add("32");
        Schoolroom.add("33");
        Schoolroom.add("34");
        Schoolroom.add("35");
        Schoolroom.add("T");
        Schoolroom.add("POS");
        Schoolroom.add("ZAS");
        Schoolroom.add("Knih");






        columnSize = Schoolroom.size();



        initViewElement();
                createLeftHeader();

                rozvrh_rozvrh.setVisibility(View.VISIBLE);
                rozvrh_nacitaniDat.setVisibility(View.GONE);
                rozvrh_loadingIndicator.setVisibility(View.GONE);
                rozvrh_buttonRefresh.setVisibility(View.GONE);
                rozvrh_text2.setVisibility(View.GONE);
                rozvrh_text1.setVisibility(View.GONE);


                // --------------- zjisti, kolik budes vytvaret jednotlivych LinearLayoutu ------------------------

                int viewCardSizeHour = 0; // počet jednotlivých hodin k zobrazení
                int viewCardSizeOther = 0; // počet jednotlivých jiných událostí k zobrazení

                for (DataSnapshot dataSnapshott: dataSnapshot.getChildren()) {

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeHour++;

                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeOther++;

                        }
                    }
                }

                // inicializuj potřebný počet všech prvků, které se budou zobrazovat
                linearLayoutsColumnCellHour = new LinearLayout[viewCardSizeHour];
                cardViewHour = new CardView[viewCardSizeHour];
                linearLayoutsInnerCardHour = new LinearLayout[viewCardSizeHour];
                relativeLayoutInnerCardHour = new RelativeLayout[viewCardSizeHour];
                textViewsRow1Hour = new TextView[viewCardSizeHour];
                textViewsRow2Hour = new TextView[viewCardSizeHour];
                textViewsRow3Hour = new TextView[viewCardSizeHour];

                linearLayoutsColumnCellOthers = new LinearLayout[viewCardSizeOther];
                cardViewOthers = new CardView[viewCardSizeOther];
                linearLayoutsInnerCardOthers = new LinearLayout[viewCardSizeOther];
                relativeLayoutInnerCardOthers = new RelativeLayout[viewCardSizeOther];
                textViewsRow1Others = new TextView[viewCardSizeOther];
                textViewsRow2Others = new TextView[viewCardSizeOther];
                textViewsRow3Others = new TextView[viewCardSizeOther];

                // Vytvoř jednotlivé sloupce podle počtu zobrazovaných tříd
                for(int i = 0; i < columnSize ; i++){

                    String room = Schoolroom.get(i);

                    createColumn(i);
                    createBorder(i);

                    addColumnHeader(i, room);

                }

                // ---------------------------------- ucebny ---------------------------------
                for (DataSnapshot dataSnapshott: dataSnapshot.getChildren()) {

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(getIndexOfRoom(hourObject.Ucebna), hourObject, hourObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(getIndexOfRoom(hourObject.Ucebna), hourObject, hourObject.Ucebna, 0, "");
                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(getIndexOfRoom(othersObject.Ucebna), othersObject, othersObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(getIndexOfRoom(othersObject.Ucebna), othersObject, othersObject.Ucebna, 0, "");
                            }
                        }
                    }
                }



    }

    else if(action.compareTo("dailyByTeachers") == 0){    // ------------------------------------------------------------------------- Denní podle učeben

        initMargins();

        //getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Dnes (Čt)" + "</small>"));

        hodiny = new ArrayList<>();
        teachers = new ArrayList<>();

        teachers.add("Bardaševská_Marcela_Bam");
        teachers.add("Bidláková_Svatava_Bd");
        teachers.add("Bureš_Miroslav_Bur");
        teachers.add("Cinner_Radovan_Cin");
        teachers.add("Dědičík_Pavel_Dě");
        teachers.add("Ďurian_Čeněk_Ďu");
        teachers.add("Dušková_Barbora_Du");
        teachers.add("Frömmelová_Svatava_Fro");
        teachers.add("Glacová_Lenka_Gla");
        teachers.add("Gocalová_Daniela_Go");
        teachers.add("Halouzka_Pavel_Ha");
        teachers.add("Jedlička_Václav_Jed");
        teachers.add("Johnová_Petra_Joh");
        teachers.add("Justová_Emília_Ju");
        teachers.add("Knápek_František_Kná");
        teachers.add("Koníček_Michal_Kon");
        teachers.add("Kopřivová_Michaela_Kop");
        teachers.add("Koždoň_Pavel_Kož");
        teachers.add("Krupová _Lenka_Kru");
        teachers.add("Kubala_Jiří_Kub");
        teachers.add("Lašková_Kateřina_Laš");
        teachers.add("Lauterbach_Filip_Lau");
        teachers.add("Mrázková Miková_Iveta_Mk");
        teachers.add("Němec_Milan_Ně");
        teachers.add("Nerudová_Silvana_Ner");
        teachers.add("Pivoňková_Vladimíra_Pká");
        teachers.add("Prokop_Radomír_Pro");
        teachers.add("Radová_Vladimíra_unknown");
        teachers.add("Siláková_Veronika_Sil");
        teachers.add("Smolka_Aleš_Smo");
        teachers.add("Sulír_Martin_Sul");
        teachers.add("Svobodová_Andrea_Svo");
        teachers.add("Szpandrzyková_Dagmar_Szp");
        teachers.add("Šestáková_Ivana_unknown");
        teachers.add("Šmiřáková_Radka_Šmi");
        teachers.add("Štěpánová_Ivana_Ště");
        teachers.add("Vašíček_David_Vaš");
        teachers.add("Vavříček_Pavel_Vav");
        teachers.add("Weintrittová_Kamila_Wt");
        teachers.add("Wiesnerová_Božena_Wie");
        teachers.add("Zubek_Pavel_Zu");

        columnSize = teachers.size();

        initViewElement();
        //getDayNumberOfWeek();
        createLeftHeader();

        //rozvrh_rozvrh.setVisibility(View.VISIBLE);
        //rozvrh_nacitaniDat.setVisibility(View.GONE);
        //rozvrh_loadingIndicator.setVisibility(View.GONE);
        // rozvrh_buttonRefresh.setVisibility(View.GONE);
        // rozvrh_text2.setVisibility(View.GONE);
        // rozvrh_text1.setVisibility(View.GONE);

        // --------------- zjisti, kolik budes vytvaret jednotlivych LinearLayoutu ------------------------

        int viewCardSizeHour = 0; // počet jednotlivých hodin k zobrazení
        int viewCardSizeOther = 0; // počet jednotlivých jiných událostí k zobrazení
        int viewCardSizeSupervision = 0; // počet jednotlivých dozorů k zobrazení

        for (DataSnapshot dataSnapshott: dataSnapshot.getChildren()) {

            for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").getChildren()) {

                for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                    viewCardSizeHour++;

                }
            }

            for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").getChildren()) {

                for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                    viewCardSizeOther++;

                }
            }

            for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("Dozor").getChildren()) {

                for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("Dozor").child(dataSnapshoth.getKey()).getChildren()) {

                    viewCardSizeSupervision++;

                }
            }
        }

        // inicializuj potřebný počet všech prvků, které se budou zobrazovat
        linearLayoutsColumnCellHour = new LinearLayout[viewCardSizeHour];
        cardViewHour = new CardView[viewCardSizeHour];
        linearLayoutsInnerCardHour = new LinearLayout[viewCardSizeHour];
        relativeLayoutInnerCardHour = new RelativeLayout[viewCardSizeHour];
        textViewsRow1Hour = new TextView[viewCardSizeHour];
        textViewsRow2Hour = new TextView[viewCardSizeHour];
        textViewsRow3Hour = new TextView[viewCardSizeHour];

        linearLayoutsColumnCellOthers = new LinearLayout[viewCardSizeOther];
        cardViewOthers = new CardView[viewCardSizeOther];
        linearLayoutsInnerCardOthers = new LinearLayout[viewCardSizeOther];
        relativeLayoutInnerCardOthers = new RelativeLayout[viewCardSizeOther];
        textViewsRow1Others = new TextView[viewCardSizeOther];
        textViewsRow2Others = new TextView[viewCardSizeOther];
        textViewsRow3Others = new TextView[viewCardSizeOther];

        linearLayoutsColumnCellBreak = new LinearLayout[viewCardSizeSupervision];
        cardViewBreak = new CardView[viewCardSizeSupervision];
        relativeLayoutInnerCardBreak = new RelativeLayout[viewCardSizeSupervision];
        linearLayoutsInnerCardBreak = new LinearLayout[viewCardSizeSupervision];
        textViewsRowBreak = new TextView[viewCardSizeSupervision];

        // Vytvoř jednotlivé sloupce podle počtu zobrazovaných tříd
        for(int i = 0; i < columnSize ; i++){

            String teacher = teachers.get(i);

            createColumn(i);
            createBorder(i);

            String name = teacher.split("_")[0];
            String surname = teacher.split("_")[1];

            addColumnHeader(i, name+ "\n" +surname);

        }

                // ---------------------------------- učitelé ---------------------------------
                for (DataSnapshot dataSnapshott: dataSnapshot.getChildren()) {

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(getIndexOfTeacher(dataSnapshott.getKey()), hourObject, hourObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(getIndexOfTeacher(dataSnapshott.getKey()), hourObject, hourObject.Ucebna, 0, "");


                                //  Log.e(TAG, "color ___________________ " + getIndexOfTeacher(dataSnapshott.getKey()) );

                                //Log.e(TAG, "color ___________________ " + hourObject.Ucitel );


                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(getIndexOfTeacher(dataSnapshott.getKey()), othersObject, othersObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(getIndexOfTeacher(dataSnapshott.getKey()), othersObject, othersObject.Ucebna, 0, "");
                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("Dozor").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("Dozor").child(dataSnapshoth.getKey()).getChildren()) {

                                SupervisionObject supervisionObject = dataSnapshotu.getValue(SupervisionObject.class);
                                supervisionObject.key = dataSnapshoth.getKey();

                                addBreak(getIndexOfTeacher(dataSnapshott.getKey()), supervisionObject, supervisionObject.key, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(dataSnapshott.getKey()).child(datumyNasmazani.get(listingDates)).child("Dozor").child(dataSnapshoth.getKey()).getChildren()) {

                                SupervisionObject supervisionObject = dataSnapshotu.getValue(SupervisionObject.class);
                                supervisionObject.key = dataSnapshoth.getKey();

                                addBreak(getIndexOfTeacher(dataSnapshott.getKey()), supervisionObject, supervisionObject.key, 0, "");
                            }
                        }
                    }



                }







                if(mSharedPreferences.getBoolean(SHOW_TIME_LINE, true)){
                    handler = new Handler() ;
                    handler.postDelayed(runnable, 0);


                }







    }

    else if(action.compareTo("weeklyBySchoolroom") == 0){    // ------------------------------------------------------------------------- Denní podle učeben


        if(!mSharedPreferences.getBoolean(SHOW_BREAKS, true)){

            velMalePrestavky = 0;
            velStredniPrestavky = 0;
            velVelkePrestavky = 0;
        }

        initMargins();


        String schoolroom = intent.getStringExtra("schoolroom");

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + schoolroom + "</small>"));



        dates = new ArrayList<>();

        dates.add("17_10_2022");
        dates.add("18_10_2022");
        dates.add("19_10_2022");
        dates.add("20_10_2022");
        dates.add("21_10_2022");

        columnSize = dates.size();

        initViewElement();
        createLeftHeader();

                // blink();



                //rozvrh_rozvrh.setVisibility(View.VISIBLE);
                //rozvrh_nacitaniDat.setVisibility(View.GONE);
                //rozvrh_loadingIndicator.setVisibility(View.GONE);
                // rozvrh_buttonRefresh.setVisibility(View.GONE);
                // rozvrh_text2.setVisibility(View.GONE);
                // rozvrh_text1.setVisibility(View.GONE);



                // --------------- zjisti, kolik budes vytvaret jednotlivych LinearLayoutu ------------------------

                int viewCardSizeHour = 0; // počet jednotlivých hodin k zobrazení
                int viewCardSizeOther = 0; // počet jednotlivých jiných událostí k zobrazení


                for(int i = 0; i<dates.size();i++){

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolroom).child(dates.get(i)).child("hodina").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolroom).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeHour++;

                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolroom).child(dates.get(i)).child("others").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolroom).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeOther++;

                        }
                    }
                }

                Log.e(TAG, "color ___________________ " + viewCardSizeHour );
                Log.e(TAG, "color ___________________ " + viewCardSizeOther );



                // inicializuj potřebný počet všech prvků, které se budou zobrazovat
                linearLayoutsColumnCellHour = new LinearLayout[viewCardSizeHour];
                cardViewHour = new CardView[viewCardSizeHour];
                linearLayoutsInnerCardHour = new LinearLayout[viewCardSizeHour];
                relativeLayoutInnerCardHour = new RelativeLayout[viewCardSizeHour];
                textViewsRow1Hour = new TextView[viewCardSizeHour];
                textViewsRow2Hour = new TextView[viewCardSizeHour];
                textViewsRow3Hour = new TextView[viewCardSizeHour];

                linearLayoutsColumnCellOthers = new LinearLayout[viewCardSizeOther];
                cardViewOthers = new CardView[viewCardSizeOther];
                linearLayoutsInnerCardOthers = new LinearLayout[viewCardSizeOther];
                relativeLayoutInnerCardOthers = new RelativeLayout[viewCardSizeOther];
                textViewsRow1Others = new TextView[viewCardSizeOther];
                textViewsRow2Others = new TextView[viewCardSizeOther];
                textViewsRow3Others = new TextView[viewCardSizeOther];


                // Vytvoř jednotlivé sloupce podle počtu zobrazovaných tříd
                for(int i = 0; i < columnSize ; i++){

                    List<String> dny = new ArrayList<>();

                    dny.add("Po");
                    dny.add("ÚT");
                    dny.add("ST");
                    dny.add("ČT");
                    dny.add("PA");


                    String date = dates.get(i);

                    createColumn(i);
                    createBorder(i);




                    addColumnHeader(i, dny.get(i) +"\n"+date);

                }

                // ---------------------------------- učitelé ---------------------------------


                for(int i = 0; i<dates.size();i++){

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolroom).child(dates.get(i)).child("hodina").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolroom).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(i, hourObject, hourObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolroom).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(i, hourObject, hourObject.Ucebna, 0, "");
                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolroom).child(dates.get(i)).child("others").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolroom).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(i, othersObject, othersObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolroom).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(i, othersObject, othersObject.Ucebna, 0, "");
                            }
                        }
                    }

                }




    }

    else if(action.compareTo("weeklyByTeacher") == 0){    // ------------------------------------------------------------------------- Denní podle učeben



        initMargins();

        String nameOfTeacher = intent.getStringExtra("name");

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + nameOfTeacher.split("_")[0] + " " + nameOfTeacher.split("_")[1] + "</small>"));


        dates = new ArrayList<>();

        dates.add("17_10_2022");
        dates.add("18_10_2022");
        dates.add("19_10_2022");
        dates.add("20_10_2022");
        dates.add("21_10_2022");

        columnSize = dates.size();



        initViewElement();
                createLeftHeader();

                //rozvrh_rozvrh.setVisibility(View.VISIBLE);
                //rozvrh_nacitaniDat.setVisibility(View.GONE);
                //rozvrh_loadingIndicator.setVisibility(View.GONE);
                // rozvrh_buttonRefresh.setVisibility(View.GONE);
                // rozvrh_text2.setVisibility(View.GONE);
                // rozvrh_text1.setVisibility(View.GONE);



                // --------------- zjisti, kolik budes vytvaret jednotlivych LinearLayoutu ------------------------

                int viewCardSizeHour = 0; // počet jednotlivých hodin k zobrazení
                int viewCardSizeOther = 0; // počet jednotlivých jiných událostí k zobrazení
                int viewCardSizeSupervision = 0; // počet jednotlivých dozorů k zobrazení


                for(int i = 0; i<dates.size();i++){

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("hodina").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeHour++;

                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("others").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeOther++;

                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("Dozor").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("Dozor").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeSupervision++;

                        }
                    }
                }


                Log.e(TAG, "color ___________________ " + viewCardSizeHour );
                Log.e(TAG, "color ___________________ " + viewCardSizeOther );
                Log.e(TAG, "color ___________________ " + viewCardSizeSupervision );


                // inicializuj potřebný počet všech prvků, které se budou zobrazovat
                linearLayoutsColumnCellHour = new LinearLayout[viewCardSizeHour];
                cardViewHour = new CardView[viewCardSizeHour];
                linearLayoutsInnerCardHour = new LinearLayout[viewCardSizeHour];
                relativeLayoutInnerCardHour = new RelativeLayout[viewCardSizeHour];
                textViewsRow1Hour = new TextView[viewCardSizeHour];
                textViewsRow2Hour = new TextView[viewCardSizeHour];
                textViewsRow3Hour = new TextView[viewCardSizeHour];

                linearLayoutsColumnCellOthers = new LinearLayout[viewCardSizeOther];
                cardViewOthers = new CardView[viewCardSizeOther];
                linearLayoutsInnerCardOthers = new LinearLayout[viewCardSizeOther];
                relativeLayoutInnerCardOthers = new RelativeLayout[viewCardSizeOther];
                textViewsRow1Others = new TextView[viewCardSizeOther];
                textViewsRow2Others = new TextView[viewCardSizeOther];
                textViewsRow3Others = new TextView[viewCardSizeOther];

                linearLayoutsColumnCellBreak = new LinearLayout[viewCardSizeSupervision];
                cardViewBreak = new CardView[viewCardSizeSupervision];
                relativeLayoutInnerCardBreak = new RelativeLayout[viewCardSizeSupervision];
                linearLayoutsInnerCardBreak = new LinearLayout[viewCardSizeSupervision];
                textViewsRowBreak = new TextView[viewCardSizeSupervision];


                // Vytvoř jednotlivé sloupce podle počtu zobrazovaných tříd
                for(int i = 0; i < columnSize ; i++){

                    List<String> dny = new ArrayList<>();

                    dny.add("Po");
                    dny.add("ÚT");
                    dny.add("ST");
                    dny.add("ČT");
                    dny.add("PA");


                    String date = dates.get(i);

                    createColumn(i);
                    createBorder(i);




                    addColumnHeader(i, dny.get(i) +"\n"+date);

                }

                // ---------------------------------- učitelé ---------------------------------


                for(int i = 0; i<dates.size();i++){

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("hodina").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(i, hourObject, hourObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(i, hourObject, hourObject.Ucebna, 0, "");
                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("others").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(i, othersObject, othersObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(i, othersObject, othersObject.Ucebna, 0, "");
                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("Dozor").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("Dozor").child(dataSnapshoth.getKey()).getChildren()) {

                                SupervisionObject supervisionObject = dataSnapshotu.getValue(SupervisionObject.class);
                                supervisionObject.key = dataSnapshoth.getKey();

                                addBreak(i, supervisionObject, supervisionObject.key, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(nameOfTeacher).child(dates.get(i)).child("Dozor").child(dataSnapshoth.getKey()).getChildren()) {

                                SupervisionObject supervisionObject = dataSnapshotu.getValue(SupervisionObject.class);
                                supervisionObject.key = dataSnapshoth.getKey();

                                addBreak(i, supervisionObject, supervisionObject.key, 0, "");
                            }
                        }
                    }




                }



        if(mSharedPreferences.getBoolean(SHOW_TIME_LINE, true)){

            handler = new Handler() ;
            handler.postDelayed(runnable, 0);

        }

    }




    else if(action.compareTo("weeklyBySchoolClass") == 0){    // ------------------------------------------------------------------------- Denní podle učeben

        if(!mSharedPreferences.getBoolean(SHOW_BREAKS, true)){

            velMalePrestavky = 0;
            velStredniPrestavky = 0;
            velVelkePrestavky = 0;
        }

        initMargins();

        String schoolClass = intent.getStringExtra("schoolClass").replace(".", "_");

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Učebna: " + schoolClass + "</small>"));



        dates = new ArrayList<>();

        dates.add("17_10_2022");
        dates.add("18_10_2022");
        dates.add("19_10_2022");
        dates.add("20_10_2022");
        dates.add("21_10_2022");

        columnSize = dates.size();



        initViewElement();
                createLeftHeader();

                //rozvrh_rozvrh.setVisibility(View.VISIBLE);
                //rozvrh_nacitaniDat.setVisibility(View.GONE);
                //rozvrh_loadingIndicator.setVisibility(View.GONE);
                // rozvrh_buttonRefresh.setVisibility(View.GONE);
                // rozvrh_text2.setVisibility(View.GONE);
                // rozvrh_text1.setVisibility(View.GONE);


                // --------------- zjisti, kolik budes vytvaret jednotlivych LinearLayoutu ------------------------

                int viewCardSizeHour = 0; // počet jednotlivých hodin k zobrazení
                int viewCardSizeOther = 0; // počet jednotlivých jiných událostí k zobrazení


                for(int i = 0; i<dates.size();i++){

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolClass).child(dates.get(i)).child("hodina").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolClass).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeHour++;

                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolClass).child(dates.get(i)).child("others").getChildren()) {

                        for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolClass).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                            viewCardSizeOther++;

                        }
                    }
                }

                Log.e(TAG, "color ___________________ " + viewCardSizeHour );
                Log.e(TAG, "color ___________________ " + viewCardSizeOther );


                // inicializuj potřebný počet všech prvků, které se budou zobrazovat
                linearLayoutsColumnCellHour = new LinearLayout[viewCardSizeHour];
                cardViewHour = new CardView[viewCardSizeHour];
                linearLayoutsInnerCardHour = new LinearLayout[viewCardSizeHour];
                relativeLayoutInnerCardHour = new RelativeLayout[viewCardSizeHour];
                textViewsRow1Hour = new TextView[viewCardSizeHour];
                textViewsRow2Hour = new TextView[viewCardSizeHour];
                textViewsRow3Hour = new TextView[viewCardSizeHour];

                linearLayoutsColumnCellOthers = new LinearLayout[viewCardSizeOther];
                cardViewOthers = new CardView[viewCardSizeOther];
                linearLayoutsInnerCardOthers = new LinearLayout[viewCardSizeOther];
                relativeLayoutInnerCardOthers = new RelativeLayout[viewCardSizeOther];
                textViewsRow1Others = new TextView[viewCardSizeOther];
                textViewsRow2Others = new TextView[viewCardSizeOther];
                textViewsRow3Others = new TextView[viewCardSizeOther];

                // Vytvoř jednotlivé sloupce podle počtu zobrazovaných tříd
                for(int i = 0; i < columnSize ; i++){

                    List<String> dny = new ArrayList<>();

                    dny.add("Po");
                    dny.add("ÚT");
                    dny.add("ST");
                    dny.add("ČT");
                    dny.add("PA");


                    String date = dates.get(i);

                    createColumn(i);
                    createBorder(i);




                    addColumnHeader(i, dny.get(i) +"\n"+date);

                }

                // ---------------------------------- učitelé ---------------------------------


                for(int i = 0; i<dates.size();i++){

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolClass).child(dates.get(i)).child("hodina").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolClass).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(i, hourObject, hourObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolClass).child(dates.get(i)).child("hodina").child(dataSnapshoth.getKey()).getChildren()) {

                                HourObject hourObject = dataSnapshotu.getValue(HourObject.class);

                                addHour(i, hourObject, hourObject.Ucebna, 0, "");
                            }
                        }
                    }

                    for (DataSnapshot dataSnapshoth: dataSnapshot.child(schoolClass).child(dates.get(i)).child("others").getChildren()) {

                        if(dataSnapshoth.getChildrenCount() > 1){

                            int count = 0;

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolClass).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(i, othersObject, othersObject.Ucebna, count, "K");
                                count++;
                            }

                        }else{

                            for (DataSnapshot dataSnapshotu: dataSnapshot.child(schoolClass).child(dates.get(i)).child("others").child(dataSnapshoth.getKey()).getChildren()) {

                                OthersObject othersObject = dataSnapshotu.getValue(OthersObject.class);

                                addOthers(i, othersObject, othersObject.Ucebna, 0, "");
                            }
                        }
                    }
                }


    }

}





int getDayNumberOfWeek(){


  //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a EEEE ");

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");

    Date actualDt = new Date(System.currentTimeMillis());

  //  7-5

//    5-1+7

    for(int i = 11; i>=0;i--){

        Date dt = new Date(System.currentTimeMillis()-i*24*60*60*1000);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        dow--;

        if(dow != 6 && dow != 0){

            datesToShow.add(dt);

        }


    }

    for(int i = 1; i<=9;i++){

        Date dt = new Date(System.currentTimeMillis()+i*24*60*60*1000);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        dow--;

        if(dow != 6 && dow != 0){

            datesToShow.add(dt);
        }
    }




    for(int i = 0; i<datesToShow.size();i++){



        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datesToShow.get(i));
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        dow--;

        Log.e(TAG, "color ___________________ " + dateFormat.format(datesToShow.get(i)));
        Log.e(TAG, "color ___________________ " + dow );


        String dateActual = dateFormat.format(actualDt);
        String datFromList = dateFormat.format(datesToShow.get(i));

        if( dateActual.compareTo(datFromList) == 0){

            Log.e(TAG, "color ___________________ dnes" + i );
            listingDates = i;

        }




    }






    return 0;
}



public int getIntFromTime(String time){

    return Integer.parseInt(time.replace(":", ""));
}

public int getDifferenceTimeInMinutes(String actualTime, String startTime){


    // Creating a SimpleDateFormat object
    // to parse time in the format HH:MM:SS
    SimpleDateFormat simpleDateFormat
            = new SimpleDateFormat("HH:mm");

    // Parsing the Time Period
    Date date1 = null;
    try {
        date1 = simpleDateFormat.parse(actualTime);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Date date2 = null;
    try {
        date2 = simpleDateFormat.parse(startTime);
    } catch (ParseException e) {
        e.printStackTrace();
    }

    // Calculating the difference in milliseconds
    long differenceInMilliSeconds
            = Math.abs(date2.getTime() - date1.getTime());

    // Calculating the difference in Hours
    long differenceInHours
            = (differenceInMilliSeconds / (60 * 60 * 1000))
            % 24;

    // Calculating the difference in Minutes
    long differenceInMinutes
            = (differenceInMilliSeconds / (60 * 1000)) % 60;

    // Calculating the difference in Seconds
    long differenceInSeconds
            = (differenceInMilliSeconds / 1000) % 60;



    Log.e(TAG, "color ___________________ " + "Difference is " + differenceInHours + " hours "
            + differenceInMinutes + " minutes "
            + differenceInSeconds + " Seconds. " );




        return (int)differenceInHours*60 + (int)differenceInMinutes;
    }



    public int displayTimeLine(){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String actualTime =  sdf.format(new Date()); //"15:00"; //dates1.get(lolo);
        int actualTimeInInt = getIntFromTime(actualTime);

        if(actualTimeInInt >= getIntFromTime(Hour0From) && actualTimeInInt <= getIntFromTime(Hour0To)-1){

            return marginHour0 + getDifferenceTimeInMinutes(actualTime, Hour0From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break0From) && actualTimeInInt <= getIntFromTime(Break0To)-1){

            return marginBreak0 + getDifferenceTimeInMinutes(actualTime, Break0From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour1From) && actualTimeInInt <= getIntFromTime(Hour1To)-1){

            return marginHour1 + getDifferenceTimeInMinutes(actualTime, Hour1From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break1From) && actualTimeInInt <= getIntFromTime(Break1To)-1){

            return marginBreak1 + getDifferenceTimeInMinutes(actualTime, Break1From) * velStredniPrestavky/10;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour2From) && actualTimeInInt <= getIntFromTime(Hour2To)-1){

            return marginHour2 + getDifferenceTimeInMinutes(actualTime, Hour2From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break2From) && actualTimeInInt <= getIntFromTime(Break2To)-1){

            return marginBreak2 + getDifferenceTimeInMinutes(actualTime, Break2From) * velVelkePrestavky/20;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour3From) && actualTimeInInt <= getIntFromTime(Hour3To)-1){

            return marginHour3 + getDifferenceTimeInMinutes(actualTime, Hour3From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break3From) && actualTimeInInt <= getIntFromTime(Break3To)-1){

            return marginBreak3 + getDifferenceTimeInMinutes(actualTime, Break3From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour4From) && actualTimeInInt <= getIntFromTime(Hour4To)-1){

            return marginHour4 + getDifferenceTimeInMinutes(actualTime, Hour4From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break4From) && actualTimeInInt <= getIntFromTime(Break4To)-1){

            return marginBreak4 + getDifferenceTimeInMinutes(actualTime, Break4From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour5From) && actualTimeInInt <= getIntFromTime(Hour5To)-1){

            return marginHour5 + getDifferenceTimeInMinutes(actualTime, Hour5From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break5From) && actualTimeInInt <= getIntFromTime(Break5To)-1){

            return marginBreak5 + getDifferenceTimeInMinutes(actualTime, Break5From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour6From) && actualTimeInInt <= getIntFromTime(Hour6To)-1){

            return marginHour6 + getDifferenceTimeInMinutes(actualTime, Hour6From) * velSesteHod/SIXTH_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break6From) && actualTimeInInt <= getIntFromTime(Break6To)-1){

            return marginBreak6 + getDifferenceTimeInMinutes(actualTime, Break6From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour7From) && actualTimeInInt <= getIntFromTime(Hour7To)-1){

            return marginHour7 + getDifferenceTimeInMinutes(actualTime, Hour7From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break7From) && actualTimeInInt <= getIntFromTime(Break7To)-1){

            return marginBreak7 + getDifferenceTimeInMinutes(actualTime, Break7From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour8From) && actualTimeInInt <= getIntFromTime(Hour8To)-1){

            return marginHour8 + getDifferenceTimeInMinutes(actualTime, Hour8From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break8From) && actualTimeInInt <= getIntFromTime(Break8To)-1){

            return marginBreak8 + getDifferenceTimeInMinutes(actualTime, Break8From) * velMalePrestavky/5;

        }

        else if(actualTimeInInt >= getIntFromTime(Hour9From) && actualTimeInInt <= getIntFromTime(Hour9To)-1){

            return marginHour9 + getDifferenceTimeInMinutes(actualTime, Hour9From) * velKlasickeHod/CLASSIC_HOUR_IN_MINUTES;

        }else if(actualTimeInInt >= getIntFromTime(Break9From) && actualTimeInInt <= getIntFromTime(Break9To)-1){

            return marginBreak9 + getDifferenceTimeInMinutes(actualTime, Break9From) * velMalePrestavky/5;

        }






        return -1;

    }















    public Runnable runnable = new Runnable() {

        public void run() {



           //Log.e(TAG, "color ___________________ "  + currentDateandTime);

            lolo++;

            int margin = displayTimeLine();

            if(margin != -1){

                for(int i = 0; i<columnSize; i++){

                    relativeLayoutsColumn[i].removeView(linearLayoutsLineBorderTest[i]);

                    linearLayoutsLineBorderTest[i] = new LinearLayout(getApplicationContext());
                    LinearLayout.LayoutParams lp_columnLineBorderHour13 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                            2);
                    lp_columnLineBorderHour13.setMargins(0,margin,0,0);
                    linearLayoutsLineBorderTest[i].setLayoutParams(lp_columnLineBorderHour13);
                    linearLayoutsLineBorderTest[i].setBackgroundColor(Color.parseColor("#E91E63"));

                    relativeLayoutsColumn[i].addView(linearLayoutsLineBorderTest[i]);

                }

            }





            handler.postDelayed(this, 30000);
        }

    };






    public void initMargins(){

    marginHour0 = velHeaderu + 0;
    marginHour1 = velHeaderu + 1*velKlasickeHod + 1*velMalePrestavky;
    marginHour2 = velHeaderu + 2*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky;
    marginHour3 = velHeaderu + 3*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginHour4 = velHeaderu + 4*velKlasickeHod + 2*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginHour5 = velHeaderu + 5*velKlasickeHod + 3*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginHour6 = velHeaderu + 6*velKlasickeHod + 4*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginHour7 = velHeaderu + 6*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginHour8 = velHeaderu + 7*velKlasickeHod + 1*velSesteHod + 6*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginHour9 = velHeaderu + 8*velKlasickeHod + 1*velSesteHod + 7*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;




    marginBreak0 = velHeaderu + 1*velKlasickeHod;
    marginBreak1 = velHeaderu + 2*velKlasickeHod + 1*velMalePrestavky;
    marginBreak2 = velHeaderu + 3*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky;
    marginBreak3 = velHeaderu + 4*velKlasickeHod + 1*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginBreak4 = velHeaderu + 5*velKlasickeHod + 2*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginBreak5 = velHeaderu + 6*velKlasickeHod + 3*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginBreak6 = velHeaderu + 6*velKlasickeHod  + 1*velSesteHod + 4*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginBreak7 = velHeaderu + 7*velKlasickeHod  + 1*velSesteHod + 5*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginBreak8 = velHeaderu + 8*velKlasickeHod  + 1*velSesteHod + 6*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;
    marginBreak9 = velHeaderu + 9*velKlasickeHod  + 1*velSesteHod + 7*velMalePrestavky + 1*velStredniPrestavky + 1*velVelkePrestavky;


    marginBreak2a = marginBreak2;
    marginBreak2b = marginBreak2+velVelkePrestavky/2;

    marginLunch6a = marginHour6;
    marginLunch6b = marginHour6+velKlasickeHod;
    marginLunch6middle = marginHour6 + (velSesteHod - (velSesteHod-velKlasickeHod))/2;



}





































    public void createLeftHeader(){

        addCorner();
        addHourHeader(0, Hour0Title, Hour0From, Hour0To);
        addHourHeader(1, Hour1Title, Hour1From, Hour1To);
        addHourHeader(2, Hour2Title, Hour2From, Hour2To);
        addHourHeader(3, Hour3Title, Hour3From, Hour3To);
        addHourHeader(4, Hour4Title, Hour4From, Hour4To);
        addHourHeader(5, Hour5Title, Hour5From, Hour5To);
        addHourHeader(6, Hour6Title, Hour6From + "\n13:30" , "13:10\n" + Hour6To);
        addHourHeader(7, Hour7Title, Hour7From, Hour7To);
        addHourHeader(8, Hour8Title, Hour8From, Hour8To);
        addHourHeader(9, Hour9Title, Hour9From, Hour9To);


        String Hour6From = "12:45\n13:30";
        String Hour6To = "13:10\n13:55";

        addBreakHeader(0, "d_0p","5 MIN");
        addBreakHeader(1, "d_1p","10 MIN");
        addBreakHeader(2, "d_2p","20 MIN");
        addBreakHeader(3, "d_3p","5 MIN");
        addBreakHeader(4, "d_4p","5 MIN");
        addBreakHeader(5, "d_5p","5 MIN");
        addBreakHeader(6, "d_6p","5 MIN");
        addBreakHeader(7, "d_7p","5 MIN");
        addBreakHeader(8, "d_8p","5 MIN");
        addBreakHeader(9, "d_9p","5 MIN");

        createBorderHeader(0, getMarginHour(String.valueOf(0)));
        createBorderHeader(1, getMarginHour(String.valueOf(1)));
        createBorderHeader(2, getMarginHour(String.valueOf(2)));
        createBorderHeader(3, getMarginHour(String.valueOf(3)));
        createBorderHeader(4, getMarginHour(String.valueOf(4)));
        createBorderHeader(5, getMarginHour(String.valueOf(5)));
        createBorderHeader(6, getMarginHour(String.valueOf(6)));
        createBorderHeader(7, getMarginHour(String.valueOf(7)));
        createBorderHeader(8, getMarginHour(String.valueOf(8)));
        createBorderHeader(9, getMarginHour(String.valueOf(9)));

        createBorderHeader(10,  getMarginBreak("d_0p"));
        createBorderHeader(11, getMarginBreak("d_1p"));
        createBorderHeader(12, getMarginBreak("d_2p"));
        createBorderHeader(13, getMarginBreak("d_3p"));
        createBorderHeader(14, getMarginBreak("d_4p"));
        createBorderHeader(15, getMarginBreak("d_5p"));
        createBorderHeader(16, getMarginBreak("d_6p"));
        createBorderHeader(17, getMarginBreak("d_7p"));
        createBorderHeader(18, getMarginBreak("d_8p"));
        createBorderHeader(19, getMarginBreak("d_9p"));

    }





    public int getIndexOfRoom(String ucebna){


        for(int i = 0; i<Schoolroom.size();i++){

            if( ucebna.compareTo(Schoolroom.get(i)) == 0){

                return i;

            }

        }

        return -1;

}

    public int getIndexOfClass(String trida){


        for(int i = 0; i<tridy.size();i++){

            if( trida.compareTo(tridy.get(i)) == 0){

                return i;

            }

        }

        return -1;

    }

    public int getIndexOfTeacher(String trida){

        for(int i = 0; i<teachers.size();i++){

            if( trida.compareTo(teachers.get(i)) == 0){

                return i;

            }
        }

        return -1;

    }

    public int getIndexOfDate(String date){

        for(int i = 0; i<dates.size();i++){

            if( date.compareTo(dates.get(i)) == 0){

                return i;

            }
        }

        return -1;

    }


    public void initViewElement(){

        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
        llllllllll = (LinearLayout) findViewById(R.id.llllllllll);
        kkkkkkkkkk = (RelativeLayout) findViewById(R.id.kkkkkkkkkk);




        linearLayoutsColumn = new LinearLayout[columnSize];
        relativeLayoutsColumn = new RelativeLayout[columnSize];

        linearLayoutsColumnCellHeader = new LinearLayout[columnSize];
        cardViewHeader = new CardView[columnSize];
        linearLayoutsInnerCardHeader = new LinearLayout[columnSize];
        textViewsRowHeader = new TextView[columnSize];




        linearLayoutsColumnCellHourHeader = new LinearLayout[15];
        cardViewHourHeader = new CardView[15];
        relativeLayoutsInnerCardHourHeader = new RelativeLayout[15];
        textViewsRow1HourHeader = new TextView[15];
        textViewsRow2HourHeader = new TextView[15];
        textViewsRow3HourHeader = new TextView[15];

        linearLayoutsColumnCellBreakHeader = new LinearLayout[15];
        cardViewBreakHeader = new CardView[15];
        linearLayoutsInnerCardBreakHeader = new LinearLayout[15];
        textViewsRowBreakHeader = new TextView[15];

        linearLayoutsLineBorderHeader = new LinearLayout[30];


        linearLayoutsLineBorderHour0 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour1 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour2 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour3 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour4 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour5 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour6 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour7 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour8 = new LinearLayout[columnSize];
        linearLayoutsLineBorderHour9 = new LinearLayout[columnSize];


        linearLayoutsLineBorderBreak0 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak1 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak2 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak3 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak4 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak5 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak6 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak7 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak8 = new LinearLayout[columnSize];
        linearLayoutsLineBorderBreak9 = new LinearLayout[columnSize];

        linearLayoutsLineBorderTest = new LinearLayout[columnSize];

        imageViewsLineBorderTest = new ImageView[columnSize];

        linearLayoutsColumnCellOther = new LinearLayout[columnSize];
        cardViewOther = new CardView[columnSize];
        linearLayoutsInnerCardOther = new LinearLayout[columnSize];
        textViewsRowOther = new TextView[columnSize];

    }


    public void createColumn(int columnID){

        LinearLayout.LayoutParams lp_columns = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams lp_rl_columns = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayoutsColumn[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsColumn[columnID].setLayoutParams(lp_columns);
        linearLayoutsColumn[columnID].setOrientation(LinearLayout.VERTICAL);

        if(columnID%2 == 1){

            linearLayoutsColumn[columnID].setBackgroundColor(Color.parseColor("#F5F5F5"));

        }

        if(columnID == 2){

            linearLayoutsColumn[columnID].setBackgroundColor(Color.parseColor("#EFEBE9"));

        }

        relativeLayoutsColumn[columnID] = new RelativeLayout(getApplicationContext());
        relativeLayoutsColumn[columnID].setLayoutParams(lp_rl_columns);


        linearLayoutsColumn[columnID].addView(relativeLayoutsColumn[columnID]);
        linearLayoutMain.addView(linearLayoutsColumn[columnID]);

    }

    public void createBorderHeader(int columnID, int margin){

        linearLayoutsLineBorderHeader[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHeader = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHeader.setMargins(0,margin,0,0);
        linearLayoutsLineBorderHeader[columnID].setLayoutParams(lp_columnLineBorderHeader);
        linearLayoutsLineBorderHeader[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHeader[columnID].setBackgroundColor(lineBorderColor);


        kkkkkkkkkk.addView(linearLayoutsLineBorderHeader[columnID]);



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

        linearLayoutsLineBorderHour9[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderHour9 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderHour9.setMargins(0,marginHour9,0,0);
        linearLayoutsLineBorderHour9[columnID].setLayoutParams(lp_columnLineBorderHour9);
        linearLayoutsLineBorderHour9[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderHour9[columnID].setBackgroundColor(lineBorderColor);





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


        linearLayoutsLineBorderBreak9[columnID] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_columnLineBorderBreak9 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                lineBorderHeight);
        lp_columnLineBorderBreak9.setMargins(0,marginBreak9,0,0);
        linearLayoutsLineBorderBreak9[columnID].setLayoutParams(lp_columnLineBorderBreak9);
        linearLayoutsLineBorderBreak9[columnID].setOrientation(LinearLayout.VERTICAL);
        linearLayoutsLineBorderBreak9[columnID].setBackgroundColor(lineBorderColor);




        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour0[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour1[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour2[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour3[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour4[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour5[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour6[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour7[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour8[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderHour9[columnID]);


        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak0[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak1[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak2[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak3[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak4[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak5[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak6[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak7[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak8[columnID]);
        relativeLayoutsColumn[columnID].addView(linearLayoutsLineBorderBreak9[columnID]);
    }

    public void addColumnHeader(int columnID, String headerString){

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        LinearLayout.LayoutParams lp_match_match = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT); // Height of TextView

        LinearLayout.LayoutParams lp_match_widthColumn = new LinearLayout.LayoutParams(
                velColumn-30, // Width of TextView
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
        cardViewHeader[columnID].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_COLUMN_HEADER, "#B0BEC5")));
        cardViewHeader[columnID].setClickable(true);

        linearLayoutsInnerCardHeader[columnID] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHeader[columnID].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHeader[columnID].setOrientation(LinearLayout.VERTICAL);

        textViewsRowHeader[columnID] = new TextView(getApplicationContext());
        textViewsRowHeader[columnID].setLayoutParams(lp_match_widthColumn);
        textViewsRowHeader[columnID].setGravity(Gravity.CENTER);
        //textViewsRowHeader[columnID].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowHeader[columnID].setTextColor(Color.parseColor("#212121"));
        textViewsRowHeader[columnID].setAllCaps(true);
        textViewsRowHeader[columnID].setTextSize(12);
        textViewsRowHeader[columnID].setText(headerString);
        //setMoveText(textViewsRowHeader[columnID]);

        linearLayoutsInnerCardHeader[columnID].addView(textViewsRowHeader[columnID]);
        cardViewHeader[columnID].addView(linearLayoutsInnerCardHeader[columnID]);
        linearLayoutsColumnCellHeader[columnID].addView(cardViewHeader[columnID]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHeader[columnID]);




        if(columnID == 2){

            cardViewHeader[columnID].setCardBackgroundColor(Color.parseColor("#D7CCC8"));


        }



    }

    public void addCorner(){

        LinearLayout.LayoutParams lp_mainCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_mainCard.setMargins(marginMainCard,marginMainCard,marginMainCard,marginMainCard);

        linearLayoutsCornerHeader = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHeader = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                velHeaderu);
        lp_column1CellHeader.setMargins(0,0,0,0);
        linearLayoutsCornerHeader.setLayoutParams(lp_column1CellHeader);
        linearLayoutsCornerHeader.setOrientation(LinearLayout.VERTICAL);
        //linearLayoutsCornerHeader[columnID].setBackgroundColor(Color.parseColor("#EEEEEE"));

        cardViewCornerHeader = new CardView(getApplicationContext());
        cardViewCornerHeader.setLayoutParams(lp_mainCard);
        cardViewCornerHeader.setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_COLUMN_HEADER, "#B0BEC5")));
        cardViewCornerHeader.setClickable(true);

        linearLayoutsCornerHeader.addView(cardViewCornerHeader);

        kkkkkkkkkk.addView(linearLayoutsCornerHeader);
    }


    public void addHourHeader(int hodina, String text1, String text2, String text3){

        int hourSize = velKlasickeHod;
        if(hodina == 6){

            hourSize = velSesteHod;
        }

        linearLayoutsColumnCellHourHeader[hodina] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHourHeader = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                hourSize);
        lp_column1CellHourHeader.setMargins(0,getMarginHour(String.valueOf(hodina)),0,0);
        linearLayoutsColumnCellHourHeader[hodina].setLayoutParams(lp_column1CellHourHeader);
        linearLayoutsColumnCellHourHeader[hodina].setOrientation(LinearLayout.VERTICAL);

        cardViewHourHeader[hodina] = new CardView(getApplicationContext());
        cardViewHourHeader[hodina].setLayoutParams(lp_mainCard);
        cardViewHourHeader[hodina].setClickable(true);

        relativeLayoutsInnerCardHourHeader[hodina] = new RelativeLayout(getApplicationContext());
        relativeLayoutsInnerCardHourHeader[hodina].setLayoutParams(lp_match_match);

        textViewsRow1HourHeader[hodina] = new TextView(getApplicationContext());
        LinearLayout.LayoutParams lp_textTop = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_textTop.setMargins(0,10,0,0);
        textViewsRow1HourHeader[hodina].setLayoutParams(lp_textTop);
        textViewsRow1HourHeader[hodina].setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
        textViewsRow1HourHeader[hodina].setTextSize(14);
        textViewsRow1HourHeader[hodina].setTextColor(Color.parseColor("#212121"));

        textViewsRow2HourHeader[hodina] = new TextView(getApplicationContext());
        textViewsRow2HourHeader[hodina].setLayoutParams(lp_match_match);
        textViewsRow2HourHeader[hodina].setGravity(Gravity.CENTER);
        textViewsRow2HourHeader[hodina].setTextSize(16);
        textViewsRow2HourHeader[hodina].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow2HourHeader[hodina].setTextColor(Color.parseColor("#212121"));

        textViewsRow3HourHeader[hodina] = new TextView(getApplicationContext());
        LinearLayout.LayoutParams lp_textBottom = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp_textBottom.setMargins(0,0,0,10);
        textViewsRow3HourHeader[hodina].setLayoutParams(lp_textBottom);
        textViewsRow3HourHeader[hodina].setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        textViewsRow3HourHeader[hodina].setTextSize(14);
        textViewsRow3HourHeader[hodina].setTextColor(Color.parseColor("#212121"));

        relativeLayoutsInnerCardHourHeader[hodina].addView(textViewsRow1HourHeader[hodina]);
        relativeLayoutsInnerCardHourHeader[hodina].addView(textViewsRow2HourHeader[hodina]);
        relativeLayoutsInnerCardHourHeader[hodina].addView(textViewsRow3HourHeader[hodina]);
        cardViewHourHeader[hodina].addView(relativeLayoutsInnerCardHourHeader[hodina]);
        linearLayoutsColumnCellHourHeader[hodina].addView(cardViewHourHeader[hodina]);

        kkkkkkkkkk.addView(linearLayoutsColumnCellHourHeader[hodina]);

        textViewsRow1HourHeader[hodina].setText(text2);
        textViewsRow2HourHeader[hodina].setText(text1);
        textViewsRow3HourHeader[hodina].setText(text3);




        cardViewHourHeader[hodina].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_ROW_HEADER_HOUR, "#78909C")));
    }

    public void addBreakHeader(int prestavka, String prestavkaKey, String text){

        linearLayoutsColumnCellBreakHeader[prestavka] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreakHeader = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                getHeightBreak(prestavkaKey));
        lp_column1CellBreakHeader.setMargins(0,getMarginBreak(prestavkaKey),0,0);
        linearLayoutsColumnCellBreakHeader[prestavka].setLayoutParams(lp_column1CellBreakHeader);
        linearLayoutsColumnCellBreakHeader[prestavka].setOrientation(LinearLayout.VERTICAL);

        cardViewBreakHeader[prestavka] = new CardView(getApplicationContext());
        cardViewBreakHeader[prestavka].setLayoutParams(lp_mainCard);
        cardViewBreakHeader[prestavka].setClickable(true);

        linearLayoutsInnerCardBreakHeader[prestavka] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreakHeader[prestavka].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreakHeader[prestavka].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreakHeader[prestavka] = new TextView(getApplicationContext());
        textViewsRowBreakHeader[prestavka].setLayoutParams(lp_match_match);
        textViewsRowBreakHeader[prestavka].setGravity(Gravity.CENTER);
        //textViewsRowBreakHeader[prestavka].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreakHeader[prestavka].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreakHeader[prestavka].setAllCaps(true);
        textViewsRowBreakHeader[prestavka].setTextSize(12);

        linearLayoutsInnerCardBreakHeader[prestavka].addView(textViewsRowBreakHeader[prestavka]);
        cardViewBreakHeader[prestavka].addView(linearLayoutsInnerCardBreakHeader[prestavka]);
        linearLayoutsColumnCellBreakHeader[prestavka].addView(cardViewBreakHeader[prestavka]);

        kkkkkkkkkk.addView(linearLayoutsColumnCellBreakHeader[prestavka]);

        textViewsRowBreakHeader[prestavka].setText(text);

        cardViewBreakHeader[prestavka].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_ROW_HEADER_BREAK, "#90A4AE")));
    }

    public void addHour(int columnID, HourObject hourObject, String text, int leftMargin, String mimoradneUdalosti){

        Log.e(TAG, "koko: -----------------------------"  );
        Log.e(TAG, "koko: " + hourObject.Ucebna );
        Log.e(TAG, "koko: " + hourObject.VyucHod );
        Log.e(TAG, "koko: " + hourObject.Datum );
        Log.e(TAG, "koko: " + hourObject.Den );
        Log.e(TAG, "koko: " + hourObject.neocekavanaUdalost );
        Log.e(TAG, "koko: " + hourObject.Trida );


        int hodnota = parseInt(hourObject.VyucHod.replaceAll("[\\D]",""));

        linearLayoutsColumnCellHour[actualIndexOfHour] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellHour = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                getHeightHour(hodnota, hourObject.colspan));
        lp_column1CellHour.setMargins(leftMargin*velColumn,getMarginHour(hourObject.VyucHod),0,0);
        linearLayoutsColumnCellHour[actualIndexOfHour].setLayoutParams(lp_column1CellHour);
        linearLayoutsColumnCellHour[actualIndexOfHour].setOrientation(LinearLayout.VERTICAL);

        cardViewHour[actualIndexOfHour] = new CardView(getApplicationContext());
        cardViewHour[actualIndexOfHour].setLayoutParams(lp_mainCard);
        cardViewHour[actualIndexOfHour].setClickable(true);

        relativeLayoutInnerCardHour[actualIndexOfHour] = new RelativeLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_relativeLayoutInnerCardHour = new LinearLayout.LayoutParams(
                10, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        relativeLayoutInnerCardHour[actualIndexOfHour].setLayoutParams(lp_relativeLayoutInnerCardHour);

        linearLayoutsInnerCardHour[actualIndexOfHour] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardHour[actualIndexOfHour].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardHour[actualIndexOfHour].setOrientation(LinearLayout.VERTICAL);





        textViewsRow1Hour[actualIndexOfHour] = new TextView(getApplicationContext());
        textViewsRow1Hour[actualIndexOfHour].setLayoutParams(lp3);
        textViewsRow1Hour[actualIndexOfHour].setGravity(Gravity.CENTER);
        textViewsRow1Hour[actualIndexOfHour].setTextSize(18);
        textViewsRow1Hour[actualIndexOfHour].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRow1Hour[actualIndexOfHour].setTextColor(Color.parseColor("#212121"));

        textViewsRow2Hour[actualIndexOfHour] = new TextView(getApplicationContext());
        textViewsRow2Hour[actualIndexOfHour].setLayoutParams(lp4);
        textViewsRow2Hour[actualIndexOfHour].setGravity(Gravity.CENTER);
        textViewsRow2Hour[actualIndexOfHour].setTextSize(13);
        textViewsRow2Hour[actualIndexOfHour].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Hour[actualIndexOfHour] = new TextView(getApplicationContext());
        textViewsRow3Hour[actualIndexOfHour].setLayoutParams(lp4);
        textViewsRow3Hour[actualIndexOfHour].setGravity(Gravity.CENTER);
        textViewsRow3Hour[actualIndexOfHour].setTextSize(12);
        textViewsRow3Hour[actualIndexOfHour].setTextColor(Color.parseColor("#212121"));


        linearLayoutsInnerCardHour[actualIndexOfHour].addView(textViewsRow1Hour[actualIndexOfHour]);
        linearLayoutsInnerCardHour[actualIndexOfHour].addView(textViewsRow2Hour[actualIndexOfHour]);
        linearLayoutsInnerCardHour[actualIndexOfHour].addView(textViewsRow3Hour[actualIndexOfHour]);
        cardViewHour[actualIndexOfHour].addView(relativeLayoutInnerCardHour[actualIndexOfHour]);
        cardViewHour[actualIndexOfHour].addView(linearLayoutsInnerCardHour[actualIndexOfHour]);
        linearLayoutsColumnCellHour[actualIndexOfHour].addView(cardViewHour[actualIndexOfHour]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellHour[actualIndexOfHour]);

            textViewsRow1Hour[actualIndexOfHour].setText(hourObject.udalostPopis.split(" ")[0]);
            textViewsRow2Hour[actualIndexOfHour].setText(hourObject.Trida);



            if(hourObject.Ucebna.contains("class=AbsZdroj")){

                String textHTML  = hourObject.Ucebna.replace("_",": ").replace("<span class=AbsZdroj>", "<font color='" + getResources().getColor(R.color.timetableColorRedTeacher) + "'>").replace("</span>","</font>");
                textViewsRow3Hour[actualIndexOfHour].setText( Html.fromHtml(textHTML), TextView.BufferType.SPANNABLE);

            }else{

                textViewsRow3Hour[actualIndexOfHour].setText(hourObject.Ucebna);
            }




        if(hourObject.Status.compareTo("Spojena hodina") == 0){

            relativeLayoutInnerCardHour[actualIndexOfHour].setBackgroundColor(getResources().getColor(R.color.timetableColorHourSpojeni));

        }else if(hourObject.Status.compareTo("Naduvazkova hodina") == 0){

            relativeLayoutInnerCardHour[actualIndexOfHour].setBackgroundColor(getResources().getColor(R.color.timetableColorHourNaduvazek));

        }else if(hourObject.Status.compareTo("Presunuta hodina") == 0){

            relativeLayoutInnerCardHour[actualIndexOfHour].setBackgroundColor(getResources().getColor(R.color.timetableColorHourPresunuto));

        }else if(hourObject.Status.compareTo("Hodina v PPP") == 0){

            relativeLayoutInnerCardHour[actualIndexOfHour].setBackgroundColor(getResources().getColor(R.color.timetableColorHourPPP));

        }else{

            relativeLayoutInnerCardHour[actualIndexOfHour].setBackgroundColor(Color.parseColor("#EEEEEE"));
        }




        cardViewHour[actualIndexOfHour].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_CLASSIC_HOUR, "#EEEEEE")));


       // if(mimoradneUdalosti.compareTo("K") == 0){

         //   cardViewHour[actualIndexOfHour].setCardBackgroundColor(Color.parseColor("#FB8C00"));

        //}

            cardViewHour[actualIndexOfHour].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(hourObject, null, null, "lesson");
                    activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                }
            });


        actualIndexOfHour++;

    }

    // Přidej přestávku
    public void addBreak(int columnID, SupervisionObject supervisionObject, String text, int leftMargin, String mimoradneUdalosti){

        linearLayoutsColumnCellBreak[actualIndexOfBreak] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellBreak = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                getHeightBreak(supervisionObject.key));
        lp_column1CellBreak.setMargins(leftMargin*velColumn,getMarginBreak(supervisionObject.key),0,0);
        linearLayoutsColumnCellBreak[actualIndexOfBreak].setLayoutParams(lp_column1CellBreak);
        linearLayoutsColumnCellBreak[actualIndexOfBreak].setOrientation(LinearLayout.VERTICAL);

        cardViewBreak[actualIndexOfBreak] = new CardView(getApplicationContext());
        cardViewBreak[actualIndexOfBreak].setLayoutParams(lp_mainCard);
        cardViewBreak[actualIndexOfBreak].setLayoutParams(lp_mainCard);
        cardViewBreak[actualIndexOfBreak].setClickable(true);

        relativeLayoutInnerCardBreak[actualIndexOfBreak] = new RelativeLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_relativeLayoutInnerCardHour = new LinearLayout.LayoutParams(
                10, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT);
        relativeLayoutInnerCardBreak[actualIndexOfBreak].setLayoutParams(lp_relativeLayoutInnerCardHour);

        linearLayoutsInnerCardBreak[actualIndexOfBreak] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardBreak[actualIndexOfBreak].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardBreak[actualIndexOfBreak].setOrientation(LinearLayout.VERTICAL);

        textViewsRowBreak[actualIndexOfBreak] = new TextView(getApplicationContext());
        textViewsRowBreak[actualIndexOfBreak].setLayoutParams(lp_match_match);
        textViewsRowBreak[actualIndexOfBreak].setGravity(Gravity.CENTER);
        //textViewsRowBreak[actualIndexOfBreak].setTypeface(Typeface.DEFAULT_BOLD);
        textViewsRowBreak[actualIndexOfBreak].setTextColor(Color.parseColor("#212121"));
        textViewsRowBreak[actualIndexOfBreak].setAllCaps(true);
        textViewsRowBreak[actualIndexOfBreak].setTextSize(12);

        linearLayoutsInnerCardBreak[actualIndexOfBreak].addView(textViewsRowBreak[actualIndexOfBreak]);
        cardViewBreak[actualIndexOfBreak].addView(relativeLayoutInnerCardBreak[actualIndexOfBreak]);
        cardViewBreak[actualIndexOfBreak].addView(linearLayoutsInnerCardBreak[actualIndexOfBreak]);
        linearLayoutsColumnCellBreak[actualIndexOfBreak].addView(cardViewBreak[actualIndexOfBreak]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellBreak[actualIndexOfBreak]);

        textViewsRowBreak[actualIndexOfBreak].setText(supervisionObject.dozorMisto.replace(" - ", "-").replace("- ", "-").replace(" -", "-").split("-")[0]);


        if(supervisionObject.key.compareTo("d_MorningSupervision") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_MORNING_SUPERVISION, "#C5E1A5")));

        }else if(supervisionObject.key.compareTo("d_Vrat") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_PORTER_SUPERVISION, "#B2DFDB")));

        }else if(supervisionObject.key.compareTo("d_PPP") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_PPP_SUPERVISION, "#B2DFDB")));

        }else if(supervisionObject.key.compareTo("d_6pa") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_MIDDAY_SUPERVISION, "#9FA8DA")));

        }else if(supervisionObject.key.compareTo("d_6pb") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_MIDDAY_SUPERVISION, "#9FA8DA")));

        }else if(supervisionObject.key.compareTo("d_SupervisionLunch6a_1") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

        }else if(supervisionObject.key.compareTo("d_SupervisionLunch6a_2") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

        }else if(supervisionObject.key.compareTo("d_SupervisionLunch6b_1") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

        }else if(supervisionObject.key.compareTo("d_SupervisionLunch6b_2") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

        }else{

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_CLASSIC_SUPERVISION, "#BCAAA4")));

        }


        if(supervisionObject.Status.compareTo("Odpadly dozor") == 0){

            cardViewBreak[actualIndexOfBreak].setCardBackgroundColor(Color.parseColor("#f5f5f5"));
            textViewsRowBreak[actualIndexOfBreak].setTextColor(Color.parseColor("#bdbdbd"));

        }else if(supervisionObject.Status.compareTo("Suplovany dozor") == 0){

            relativeLayoutInnerCardBreak[actualIndexOfBreak].setBackgroundColor(Color.parseColor("#EF9A9A"));

        }











        cardViewBreak[actualIndexOfBreak].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, supervisionObject, null, "supervision");
                activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

        actualIndexOfBreak++;
    }

    public void addOthers(int columnID, OthersObject othersObject, String text, int leftMargin, String mimoradneUdalosti){

        int hodnota = parseInt(othersObject.VyucHod.replaceAll("[\\D]",""));


        Log.e(TAG, "uuuuuuuuuuuuuuuuuuu: " + othersObject.colspan);


        linearLayoutsColumnCellOthers[actualIndexOfOthers] = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp_column1CellOthers = new LinearLayout.LayoutParams(
                velColumn, // Width of TextView
                getHeightHour(hodnota, othersObject.colspan));
        lp_column1CellOthers.setMargins(leftMargin*velColumn,getMarginHour(othersObject.VyucHod),0,0);
        linearLayoutsColumnCellOthers[actualIndexOfOthers].setLayoutParams(lp_column1CellOthers);
        linearLayoutsColumnCellOthers[actualIndexOfOthers].setOrientation(LinearLayout.VERTICAL);

        cardViewOthers[actualIndexOfOthers] = new CardView(getApplicationContext());
        cardViewOthers[actualIndexOfOthers].setLayoutParams(lp_mainCard);
        cardViewOthers[actualIndexOfOthers].setClickable(true);

        linearLayoutsInnerCardOthers[actualIndexOfOthers] = new LinearLayout(getApplicationContext());
        linearLayoutsInnerCardOthers[actualIndexOfOthers].setLayoutParams(lp_match_match);
        linearLayoutsInnerCardOthers[actualIndexOfOthers].setOrientation(LinearLayout.VERTICAL);

       // textViewsRow1Others[actualIndexOfOthers] = new TextView(getApplicationContext());
       // textViewsRow1Others[actualIndexOfOthers].setLayoutParams(lp5);
        //textViewsRow1Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
       // textViewsRow1Others[actualIndexOfOthers].setTextSize(18);
       // textViewsRow1Others[actualIndexOfOthers].setTypeface(Typeface.DEFAULT_BOLD);
       // textViewsRow1Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));

        if(othersObject.colspan == -1){

            textViewsRow1Others[actualIndexOfOthers] = new TextView(getApplicationContext());
            textViewsRow1Others[actualIndexOfOthers].setLayoutParams(lp3);
            textViewsRow1Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
            textViewsRow1Others[actualIndexOfOthers].setTextSize(15);
            textViewsRow1Others[actualIndexOfOthers].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));
            setMoveText(textViewsRow1Others[actualIndexOfOthers]);
            textViewsRow1Others[actualIndexOfOthers].setText(othersObject.udalostPopis);


        }else if(othersObject.colspan == 2){

            LinearLayout.LayoutParams lp_lolo = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                    getHeightHour(hodnota, othersObject.colspan)-velKlasickeHod);

            textViewsRow1Others[actualIndexOfOthers] = new TextView(getApplicationContext());
            textViewsRow1Others[actualIndexOfOthers].setLayoutParams(lp_lolo);
            textViewsRow1Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
            textViewsRow1Others[actualIndexOfOthers].setTextSize(15);
            textViewsRow1Others[actualIndexOfOthers].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Others[actualIndexOfOthers].setText(othersObject.udalostPopis);

        }else if(othersObject.colspan > 2){

            LinearLayout.LayoutParams lp_lolo = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                    getHeightHour(hodnota, othersObject.colspan)-velKlasickeHod );

          // LinearLayout.LayoutParams lp_lolo = new LinearLayout.LayoutParams(
            //        getHeightHour(hodnota, othersObject.colspan)-velKlasickeHod, // Width of TextView
            //        LinearLayout.LayoutParams.WRAP_CONTENT);

            textViewsRow1Others[actualIndexOfOthers] = new TextView(getApplicationContext());
            textViewsRow1Others[actualIndexOfOthers].setLayoutParams(lp_lolo);
            textViewsRow1Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
            textViewsRow1Others[actualIndexOfOthers].setTextSize(15);
            textViewsRow1Others[actualIndexOfOthers].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));
          //  textViewsRow1Others[actualIndexOfOthers].setText("Třídnická hodina abcdeugughgyugugfgfghfghhgfdsgfjhsdjhfgjhsdgfjgsdfgsdhgfjhsdgfjhsgdjhfgsjhdgfjhsdfjhsgdjhfgsdgfjhsdgfjhsdgfjhdfjhgdfjhgdfgdghghghghcghcghfcfcgf cgf");
              textViewsRow1Others[actualIndexOfOthers].setText(othersObject.udalostPopis);


           //textViewsRow1Others[actualIndexOfOthers].setRotation(90);

        }else{


            textViewsRow1Others[actualIndexOfOthers] = new TextView(getApplicationContext());
            textViewsRow1Others[actualIndexOfOthers].setLayoutParams(lp5);
            textViewsRow1Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
            textViewsRow1Others[actualIndexOfOthers].setTextSize(15);
            //textViewsRow1Others[actualIndexOfOthers].setTypeface(Typeface.DEFAULT_BOLD);
            textViewsRow1Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));
            textViewsRow1Others[actualIndexOfOthers].setRotation(90);


        }








        //textViewsRow1Others[actualIndexOfOthers].setRotation(90);
        //setMoveText(textViewsRow1Others[actualIndexOfOthers]);

        textViewsRow2Others[actualIndexOfOthers] = new TextView(getApplicationContext());
        textViewsRow2Others[actualIndexOfOthers].setLayoutParams(lp4);
        textViewsRow2Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
        textViewsRow2Others[actualIndexOfOthers].setTextSize(13);
        textViewsRow2Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));

        textViewsRow3Others[actualIndexOfOthers] = new TextView(getApplicationContext());
        textViewsRow3Others[actualIndexOfOthers].setLayoutParams(lp4);
        textViewsRow3Others[actualIndexOfOthers].setGravity(Gravity.CENTER);
        textViewsRow3Others[actualIndexOfOthers].setTextSize(12);
        textViewsRow3Others[actualIndexOfOthers].setTextColor(Color.parseColor("#212121"));

        linearLayoutsInnerCardOthers[actualIndexOfOthers].addView(textViewsRow1Others[actualIndexOfOthers]);
        linearLayoutsInnerCardOthers[actualIndexOfOthers].addView(textViewsRow2Others[actualIndexOfOthers]);
        linearLayoutsInnerCardOthers[actualIndexOfOthers].addView(textViewsRow3Others[actualIndexOfOthers]);
        cardViewOthers[actualIndexOfOthers].addView(linearLayoutsInnerCardOthers[actualIndexOfOthers]);
        linearLayoutsColumnCellOthers[actualIndexOfOthers].addView(cardViewOthers[actualIndexOfOthers]);

        relativeLayoutsColumn[columnID].addView(linearLayoutsColumnCellOthers[actualIndexOfOthers]);

        //textViewsRow1Others[actualIndexOfOthers].setText("1234567890abcdefghijklmnopqrstuvwxyz1234567890");
        //textViewsRow1Others[actualIndexOfOthers].setText("Třídnická hodina");
        //textViewsRow1Others[actualIndexOfOthers].setText("Třídnická hodina abcdeugughgyugugfgfghfghhgfdsgfjhsdjhfgjhsdgfjgsdfgsdhgfjhsdgfjhsgdjhfgsjhdgfjhsdfjhsgdjhfgsdgfjhsdgfjhsdgfjhdfjhgdfjhgdfgdghghghghcghcghfcfcgf cgf");
        //textViewsRow1Others[actualIndexOfOthers].setText(othersObject.udalostPopis);

        textViewsRow2Others[actualIndexOfOthers].setText(othersObject.Trida);
        textViewsRow3Others[actualIndexOfOthers].setText(othersObject.Ucebna);


            cardViewOthers[actualIndexOfOthers].setCardBackgroundColor(Color.parseColor("#FFCC80"));


           /* if(hourObject.backgroundColor.compareTo("212121") == 0){

                cardViewOthers[actualIndexOfOthers].setCardBackgroundColor(Color.parseColor("#EEEEEE"));

            }else if(hourObject.backgroundColor.compareTo("7fff00") == 0){

                cardViewOthers[actualIndexOfOthers].setCardBackgroundColor(Color.parseColor("#C5E1A5"));

            }else if(hourObject.backgroundColor.compareTo("FF0404") == 0){

                cardViewOthers[actualIndexOfOthers].setCardBackgroundColor(Color.parseColor("#EF9A9A"));

            }else if(hourObject.backgroundColor.compareTo("d4aaff") == 0){

                cardViewOthers[actualIndexOfOthers].setCardBackgroundColor(Color.parseColor("#B39DDB"));
            }*/

        cardViewOthers[actualIndexOfOthers].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityViewBottomSheetDialog activityViewBottomSheetDialog = new ActivityViewBottomSheetDialog(null, null, othersObject, "other");
                activityViewBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

        actualIndexOfOthers++;

    }

    private void setMoveText(TextView moveText) {
        moveText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        moveText.setSingleLine(true);
        moveText.setAllCaps(true);
        moveText.setMarqueeRepeatLimit(200);
        moveText.setSelected(true);
    }

    int getWidthHour(String text,  String vyucHod){

            if(!hodiny.contains(text + "_" + vyucHod)){

                hodiny.add(text + "_" + vyucHod);
                return 0;

            }else{

                return velColumn;
            }

        }

    int getHeightBreak(String breakKey){


            if(breakKey.compareTo("d_0p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_1p") == 0){

                return velStredniPrestavky;
            }

            if(breakKey.compareTo("d_2p") == 0){

                return velVelkePrestavky;
            }

            if(breakKey.compareTo("d_2pa") == 0){

                return velVelkePrestavky/2;
            }

            if(breakKey.compareTo("d_2pb") == 0){

                return velVelkePrestavky/2;
            }

            if(breakKey.compareTo("d_3p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_4p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_5p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_6p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_7p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_8p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_9p") == 0){

                return velMalePrestavky;
            }

            if(breakKey.compareTo("d_MorningSupervision") == 0){

                return velMorningSupervision+velMalePrestavky;
            }

            if(breakKey.compareTo("d_PPP") == 0){

                return velKlasickeHod+velMalePrestavky;
            }

            if(breakKey.compareTo("d_Vrat") == 0){

                return velVrat;
            }

            if(breakKey.compareTo("d_6pa") == 0){

                return velSupervision6ab;
            }

            if(breakKey.compareTo("d_6pb") == 0){

                return velSupervision6ab;
            }

            if(breakKey.compareTo("d_SupervisionLunch6a_1") == 0){

                return velKlasickeHod*5/CLASSIC_HOUR_IN_MINUTES + velMalePrestavky;
            }

            if(breakKey.compareTo("d_SupervisionLunch6a_2") == 0){

                return velSesteHod-velKlasickeHod;
            }

        if(breakKey.compareTo("d_SupervisionLunch6b_1") == 0){

            return velKlasickeHod*5/CLASSIC_HOUR_IN_MINUTES + velMalePrestavky;
        }

        if(breakKey.compareTo("d_SupervisionLunch6b_2") == 0){

            return velSesteHod-velKlasickeHod;
        }

        if(breakKey.compareTo("d_Supervision1330_1335") == 0){

            return velMalePrestavky;
        }

/*

s("12:35-12:45")){
        child = "d_SupervisionLunch6a_1";

    }else if(dozorCas.includes("12:45-13:10")){

        child = "d_SupervisionLunch6a_2";

    }else if(dozorCas.includes("13:25-13:35")){

        child = "d_SupervisionLunch6b_1";

    }else if(dozorCas.includes("13:35-14:00")){

        child = "d_SupervisionLunch6b_2";

*/


            return 0;
        }

    int getHeightHour(int hour, int colspan){

            if(hour == 0){

                if(colspan == -1){

                    return velKlasickeHod;

                }else if(colspan == 2){

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

                }else if(colspan == 10){

                    return 9*velKlasickeHod + 1*velSesteHod + 7*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;
                }


            }

         if(hour == 1){

             if(colspan == -1){

                 return velKlasickeHod;

             }else if(colspan == 2){

                 return 2*velKlasickeHod + velStredniPrestavky;

             }else if(colspan == 3){

                 return 3*velKlasickeHod + velStredniPrestavky + velVelkePrestavky;

             }else if(colspan == 4){

                 return 4*velKlasickeHod + velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

             }else if(colspan == 5){

                 return 5*velKlasickeHod + 2*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

             }else if(colspan == 6){

                 return 5*velKlasickeHod + 1*velSesteHod + 3*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

             }else if(colspan == 7){

                 return 6*velKlasickeHod + 1*velSesteHod + 4*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

             }else if(colspan == 8){

                 return 7*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;

             }else if(colspan == 9){

                 return 8*velKlasickeHod + 1*velSesteHod + 6*velMalePrestavky + velStredniPrestavky + velVelkePrestavky;
             }
         }

             if(hour == 2){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 2*velKlasickeHod + velVelkePrestavky;

                 }else if(colspan == 3){

                     return 3*velKlasickeHod + velMalePrestavky + velVelkePrestavky;

                 }else if(colspan == 4){

                     return 4*velKlasickeHod + 2*velMalePrestavky + velVelkePrestavky;

                 }else if(colspan == 5){

                     return 4*velKlasickeHod + 1*velSesteHod + 3*velMalePrestavky + velVelkePrestavky;

                 }else if(colspan == 6){

                     return 5*velKlasickeHod + 1*velSesteHod + 4*velMalePrestavky + velVelkePrestavky;

                 }else if(colspan == 7){

                     return 6*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky + velVelkePrestavky;

                 }else if(colspan == 8){

                     return 7*velKlasickeHod + 1*velSesteHod + 6*velMalePrestavky + velVelkePrestavky;
                 }
            }

             if(hour == 3){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 2*velKlasickeHod + velMalePrestavky;

                 }else if(colspan == 3){

                     return 3*velKlasickeHod + 2*velMalePrestavky;

                 }else if(colspan == 4){

                     return 3*velKlasickeHod + 1*velSesteHod  + 3*velMalePrestavky;

                 }else if(colspan == 5){

                     return 4*velKlasickeHod + 1*velSesteHod + 4*velMalePrestavky;

                 }else if(colspan == 6){

                     return 5*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky;

                 }else if(colspan == 7){

                     return 6*velKlasickeHod + 1*velSesteHod + 6*velMalePrestavky;

                 }
             }

             if(hour == 4){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 2*velKlasickeHod + velMalePrestavky;

                 }else if(colspan == 3){

                     return 2*velKlasickeHod + 1*velSesteHod + 2*velMalePrestavky;

                 }else if(colspan == 4){

                     return 3*velKlasickeHod + 1*velSesteHod  + 3*velMalePrestavky;

                 }else if(colspan == 5){

                     return 4*velKlasickeHod + 1*velSesteHod + 4*velMalePrestavky;

                 }else if(colspan == 6){

                     return 5*velKlasickeHod + 1*velSesteHod + 5*velMalePrestavky;

                 }
             }

             if(hour == 5){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 1*velKlasickeHod + 1*velSesteHod + velMalePrestavky;

                 }else if(colspan == 3){

                     return 2*velKlasickeHod + 1*velSesteHod + 2*velMalePrestavky;

                 }else if(colspan == 4){

                     return 3*velKlasickeHod + 1*velSesteHod  + 3*velMalePrestavky;

                 }else if(colspan == 5){

                     return 4*velKlasickeHod + 1*velSesteHod + 4*velMalePrestavky;

                 }
             }

             if(hour == 6){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 1*velKlasickeHod + 1*velSesteHod + velMalePrestavky;

                 }else if(colspan == 3){

                     return 2*velKlasickeHod + 1*velSesteHod + 2*velMalePrestavky;

                 }else if(colspan == 4){

                     return 3*velKlasickeHod + 1*velSesteHod  + 3*velMalePrestavky;

                 }
             }

             if(hour == 7){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 2*velKlasickeHod + velMalePrestavky;

                 }else if(colspan == 3){

                     return 3*velKlasickeHod + 2*velMalePrestavky;

                 }
             }

             if(hour == 8){

                 if(colspan == -1){

                     return velKlasickeHod;

                 }else if(colspan == 2){

                     return 2*velKlasickeHod + velMalePrestavky;

                 }
             }

             if(hour == 9){

                 if(colspan == -1){

                     return velKlasickeHod;
                 }
             }

         return 0;
     }

    int getMarginHour(String hour){

            if(hour.compareTo("0") == 0){

                return marginHour0;
            }

            if(hour.compareTo("1") == 0){

                return marginHour1;
            }

            if(hour.compareTo("2") == 0){

                return marginHour2;
            }

            if(hour.compareTo("3") == 0){

                return marginHour3;
            }

            if(hour.compareTo("4") == 0){

                return marginHour4;
            }

            if(hour.compareTo("5") == 0){

                return marginHour5;
            }

            if(hour.compareTo("6") == 0){

                return marginHour6;
            }

            if(hour.compareTo("6a") == 0){

                return marginHour6;
            }

            if(hour.compareTo("6b") == 0){

                return marginHour6+(velSesteHod-velKlasickeHod);
            }

            if(hour.compareTo("7") == 0){

                return marginHour7;
            }

            if(hour.compareTo("8") == 0){

                return marginHour8;
            }

            if(hour.compareTo("9") == 0){

                return marginHour9;
            }

    return 0;


        }

    int getMarginBreak(String breakKey){

            if(breakKey.compareTo("d_0p") == 0){

                return marginBreak0;
            }

            if(breakKey.compareTo("d_1p") == 0){

                return marginBreak1;
            }

            if(breakKey.compareTo("d_2p") == 0){

                return marginBreak2;
            }

            if(breakKey.compareTo("d_2pa") == 0){

                return marginBreak2a;
            }

            if(breakKey.compareTo("d_2pb") == 0){

                return marginBreak2b;
            }

            if(breakKey.compareTo("d_3p") == 0){

                return marginBreak3;
            }

            if(breakKey.compareTo("d_4p") == 0){

                return marginBreak4;
            }

            if(breakKey.compareTo("d_5p") == 0){

                return marginBreak5;
            }

            if(breakKey.compareTo("d_6p") == 0){

                return marginBreak6;
            }

            if(breakKey.compareTo("d_7p") == 0){

                return marginBreak7;
            }

            if(breakKey.compareTo("d_8p") == 0){

                return marginBreak8;
            }

            if(breakKey.compareTo("d_9p") == 0){

                return marginBreak9;
            }

            if(breakKey.compareTo("d_MorningSupervision") == 0){

                return marginHour0 + velKlasickeHod-velMorningSupervision;
            }

            if(breakKey.compareTo("d_PPP") == 0){

                return marginBreak0;
            }

            if(breakKey.compareTo("d_Vrat") == 0){

                return marginHour0 + velKlasickeHod-velVrat;
            }

            if(breakKey.compareTo("d_6pa") == 0){

                return marginBreak5;
            }

            if(breakKey.compareTo("d_6pb") == 0){

                return marginHour7-velSupervision6ab;
            }

        if(breakKey.compareTo("d_SupervisionLunch6a_1") == 0){

            return marginHour6 - velKlasickeHod*5/CLASSIC_HOUR_IN_MINUTES-velMalePrestavky;
        }

        if(breakKey.compareTo("d_SupervisionLunch6a_2") == 0){

            return marginHour6;
        }

        if(breakKey.compareTo("d_SupervisionLunch6b_1") == 0){

            return marginHour6+velKlasickeHod +velMalePrestavky- velKlasickeHod*5/CLASSIC_HOUR_IN_MINUTES-velMalePrestavky;
        }

        if(breakKey.compareTo("d_SupervisionLunch6b_2") == 0){

            return marginHour6+velKlasickeHod+velMalePrestavky;
        }

        if(breakKey.compareTo("d_Supervision1330_1335") == 0){

            return marginHour6+velKlasickeHod;





        }




           /* if(breakKey.compareTo("d_SupervisionLunch6a_2") == 0){

                return marginBreak6;
            }

            if(breakKey.compareTo("d_SupervisionLunch6b_2") == 0){

                return marginHour7-velSupervision6ab;
            }
*/



            return 0;
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_timetable, menu);

        this.mMenu = menu;

        mMenu.getItem(0).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);
        mMenu.getItem(1).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);
        mMenu.getItem(2).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);

        mMenu.getItem(0).setVisible(false);
        mMenu.getItem(1).setVisible(false);
        mMenu.getItem(2).setVisible(false);

        // if(menuVisibility){

        //mMenu.getItem(0).setVisible(true);
        mMenu.getItem(0).setVisible(true);
        mMenu.getItem(1).setVisible(true);
        mMenu.getItem(2).setVisible(true);
        // }



        return true;
    }


    String getDayOfMonth(int numberOfDay){


        if(numberOfDay == 1){

            return "Po";

        }else if(numberOfDay == 2) {

            return  "Út";

        }else if(numberOfDay == 3) {

            return "St";

        }else if(numberOfDay == 4) {

            return "Čt";

        }else if(numberOfDay == 5) {

            return "Pá";
        }

        return "";

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.dateLeft:


                /*

                if(listingDates >= 0){

                    listingDates--;

                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(datesToShow.get(listingDates));
                int dow = calendar.get(Calendar.DAY_OF_WEEK);
                dow--;

                getSupportActionBar().setTitle(Html.fromHtml("<small>" + dateFormat.format(datesToShow.get(listingDates)) + " ("+ getDayOfMonth(dow) + ")"+ "</small>"));

*/
                listingDates++;
                getSupportActionBar().setTitle(Html.fromHtml("<small>" + datumyNasmazani.get(listingDates) + "</small>"));
                linearLayoutMain.removeAllViews();
                kkkkkkkkkk.removeAllViews();
                createTimeTable(dataSnapshot_global);

                return(true);

            case R.id.dateRight:


                listingDates--;
                getSupportActionBar().setTitle(Html.fromHtml("<small>" + datumyNasmazani.get(listingDates) + "</small>"));
                linearLayoutMain.removeAllViews();
                kkkkkkkkkk.removeAllViews();
                createTimeTable(dataSnapshot_global);

                return(true);

            case R.id.menu:

                TimetableMenuBottomSheetDialog timetableMenuBottomSheetDialog = new TimetableMenuBottomSheetDialog();
                timetableMenuBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");

                return(true);

        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {



       // String COLOR_COLUMN_HEADER = "ColorColumnHeader";
       // String COLOR_ROW_HEADER_HOUR = "ColorRowHeaderHour";
       // String COLOR_ROW_HEADER_BREAK = "ColorRowHeaderBreak";
       // String COLOR_CLASSIC_HOUR = "ColorClassicHour";
       // String COLOR_CLASSIC_SUPERVISION = "ColorClassicSupervision";
       // String COLOR_MORNING_SUPERVISION = "ColorMorningSupervision";
       // String COLOR_PORTER_SUPERVISION = "ColorPorterSupervision";
       // String COLOR_PPP_SUPERVISION = "ColorPPPSupervision";
       // String COLOR_DINNINGROOM_SUPERVISION = "ColorDinningroomSupervision";
       // String COLOR_MIDDAY_SUPERVISION = "ColorMiddaySupervision";
       // String COLOR_CLASS_LESSON = "ColorClassLesson";
       // String COLOR_CONSULTATION_HOUR = "ColorConsultationHour";
       // String COLOR_SCHOOL_ACTIONS = "ColorSchoolActions";
       // String COLOR_LUNCH = "ColorLunch";


        recreate();

    }
}
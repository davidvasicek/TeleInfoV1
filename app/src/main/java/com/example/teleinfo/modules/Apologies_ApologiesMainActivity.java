package com.example.teleinfo.modules;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.example.teleinfo.parameters.IsOnline;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Apologies_ApologiesMainActivity extends AppCompatActivity {

    private List<Apologies_ApologiesObject> mApologies_ApologiesObject;
    private MainStudentsAdapter mMainStudentsAdapter;

    RecyclerView recyclerView;
    TextView textViewMessageRow1;
    TextView textViewMessageRow2;
    Button buttonRefrest;
    AVLoadingIndicatorView aVLoadingIndicatorViewIndicator;
    LinearLayout LinearLAyoutInfoBlock;

    TextView textLastSynchronization;

    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener listener;

    Toolbar toolbar;

    Handler handler;

    long lastSyncData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shrPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.apologies_apologies_main_activity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        toolbar = (Toolbar) findViewById(R.id.toolbardd);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Omluvenky 4.C" + "</small>"));

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("Omluvenky").child("4_C") ;







        recyclerView = (RecyclerView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_RecyclerView);

        textViewMessageRow1 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow1);
        textViewMessageRow2 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow2);
        buttonRefrest = (Button)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_ButtonRefrest);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_AVLoadingIndicatorViewIndicator);
        LinearLAyoutInfoBlock = (LinearLayout) findViewById(R.id.LinearLAyoutInfoBlock);



        mApologies_ApologiesObject = new ArrayList<>();

        hideAllViews();
        textViewMessageRow1.setVisibility(View.VISIBLE);
        textViewMessageRow1.setText("Načítání dat");
        aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

        LinearLAyoutInfoBlock.setVisibility(View.GONE);

        textLastSynchronization = (TextView)findViewById(R.id.textLastSynchronization);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mMainStudentsAdapter = new MainStudentsAdapter(mApologies_ApologiesObject, getApplicationContext(), getSupportFragmentManager());
        recyclerView.setAdapter(mMainStudentsAdapter);

        Loaddata();

        // Práce s daty
        listener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Apologies_ApologiesObject mainLightsObject = dataSnapshot.getValue(Apologies_ApologiesObject.class);
                mainLightsObject.key = dataSnapshot.getKey();

                //String studenName = "";

                //String[] keySplit = dataSnapshot.getKey().split("_");

                //for(int i = 2; i<keySplit.length; i++){

                //    studenName+= keySplit[i] + " ";
               // }

              //  mainLightsObject.Id = Integer.parseInt(keySplit[0]);
               // mainLightsObject.Name = studenName;

                mApologies_ApologiesObject.add(mainLightsObject);

                Collections.sort(mApologies_ApologiesObject, new Comparator<Apologies_ApologiesObject>(){
                    public int compare(Apologies_ApologiesObject obj1, Apologies_ApologiesObject obj2) {
                        return (obj1.Id < obj2.Id) ? -1: (obj1.Id > obj2.Id) ? 1:0 ;
                    }});

                mMainStudentsAdapter.notifyDataSetChanged();

                //linearLayout_synchronization.setVisibility(View.VISIBLE);
                hideAllViews();
                CheckIsListEmpty(null,null);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Apologies_ApologiesObject mainLightsObject = dataSnapshot.getValue(Apologies_ApologiesObject.class);
                mainLightsObject.key = dataSnapshot.getKey();

                //String studenName = "";

                //String[] keySplit = dataSnapshot.getKey().split("_");

                //for(int i = 2; i<keySplit.length; i++){

                //    studenName+= keySplit[i] + " ";
               // }

                //mainLightsObject.Id = Integer.parseInt(keySplit[0]);
                //mainLightsObject.Name = studenName;

                int index = GetItemIndex(mainLightsObject,mApologies_ApologiesObject);
                mApologies_ApologiesObject.set(index, mainLightsObject);

                mMainStudentsAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Apologies_ApologiesObject mainLightsObject = dataSnapshot.getValue(Apologies_ApologiesObject.class);
                mainLightsObject.key = dataSnapshot.getKey();

                int index = GetItemIndex(mainLightsObject,mApologies_ApologiesObject);
                mApologies_ApologiesObject.set(index, mainLightsObject);
                mMainStudentsAdapter.notifyItemRemoved(index);

                CheckIsListEmpty(null, null);
                CheckIsListEmpty("Žádná data k zobrazení", "Databáze je prázdná");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        buttonRefrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Loaddata();

            }
        });




    }



    private int GetItemIndex(Apologies_ApologiesObject mainLightsObject, List<Apologies_ApologiesObject> list){

        int index = -1;

        for(int i = 0; i<list.size(); i++){

            if(list.get(i).key.equals(mainLightsObject.key)){
                index = i;
                break;
            }
        }

        return index;
    }



    private void hideAllViews(){

        textViewMessageRow1.setVisibility(View.GONE);
        textViewMessageRow2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        buttonRefrest.setVisibility(View.GONE);
        aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);

    }

    private void CheckIsListEmpty(String messageRow1, String messageRow2){

        if(mApologies_ApologiesObject.size() == 0){

            if(messageRow1 == null){

                textViewMessageRow1.setVisibility(View.GONE);

            }else{

                textViewMessageRow1.setVisibility(View.VISIBLE);
                textViewMessageRow1.setText(messageRow1);
            }

            if(messageRow2 == null){

                textViewMessageRow2.setVisibility(View.GONE);

            }else{

                textViewMessageRow2.setVisibility(View.VISIBLE);
                textViewMessageRow2.setText(messageRow2);
            }

            recyclerView.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.GONE);



        }else{

            textViewMessageRow1.setVisibility(View.GONE);
            textViewMessageRow2.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            buttonRefrest.setVisibility(View.GONE);


        }
    }

    private void Loaddata(){

        if(new IsOnline(getApplicationContext()).isOnline() == IS_ONLINE){

            CheckIsListEmpty("Načítání dat", null);
            aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.i("kokot", dataSnapshot.getChildrenCount() + "");


                    long epochtime = dataSnapshot.child("Info").child("lastSynchronizationTime").getValue(long.class);

                    lastSyncData = epochtime;

                    Date date = new Date(epochtime);
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    format.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String formatted = format.format(date);

                    long time = epochtime;
                    Date date1 = new Date(time);
                    //SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy a");
                    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    format1.setTimeZone(TimeZone.getTimeZone("UTC"));

                    handler = new Handler() ;
                    handler.postDelayed(runnable, 0);

                    //textLastSynchronization.setText("Synchronizováno před " + format1.format(date1));
                    LinearLAyoutInfoBlock.setVisibility(View.VISIBLE);

                    mDatabaseReference.child("Apologies").addChildEventListener(listener);

                    mDatabaseReference.child("Info").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            lastSyncData = dataSnapshot.child("lastSynchronizationTime").getValue(long.class);;

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // TODO
                        //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                        //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

                    }
                });



                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // TODO
                    //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                    //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

                }
            });

        }else{

            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.VISIBLE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);

            textViewMessageRow1.setText("Nepodařilo se načíst data");
            textViewMessageRow2.setText("Zkontrolujte připojení k internetu");
        }

    }


    public Runnable runnable = new Runnable() {

        public void run() {



            StringBuilder s = new StringBuilder(100);


            long lastSyncDiff = (System.currentTimeMillis() - lastSyncData);

            long differenceInDays = (lastSyncDiff) / 86400000;
            long differenceInHours = (lastSyncDiff / (60 * 60 * 1000)) % 24;
            long differenceInMinutes = (lastSyncDiff / (60 * 1000)) % 60;
            long differenceInSeconds = (lastSyncDiff / 1000) % 60;






            if(differenceInDays > 0){

                s.append(differenceInDays + " dny, ");
            }

            if(differenceInHours > 0){

                s.append(differenceInHours + " hod, ");
            }

            if(differenceInMinutes > 0){

                s.append(differenceInMinutes + " min, ");
            }

            s.append(differenceInSeconds + " sec");


            // TODO - když bude 2hod 0min a 1sec tak se minuty nezobrazí

            textLastSynchronization.setText("Synchronizováno před " +  s);

          //  1675188695483
            //  1675191211518
            Log.i("Lojza", "" + System.currentTimeMillis());

            handler.postDelayed(this, 1000);
        }

    };










    // ------------------------------------------------------------------------------------ Adapter ------------------------------------------------------------------------------------



    public static class MainStudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<Apologies_ApologiesObject> mMainLightsList;
        private Context mContext;

        FragmentManager fragmentManager;

        public MainStudentsAdapter(List<Apologies_ApologiesObject> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.students_list_adapter_item,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final Apologies_ApologiesObject mainLightsObject = mMainLightsList.get(position);

            MainLightsViewHolder mainLightsViewHolder = (MainLightsViewHolder)holder;



mainLightsViewHolder.statisticOfStudentPritomen.setText(mainLightsObject.Pritomen + "");
mainLightsViewHolder.statisticOfStudentNepritomen.setText(mainLightsObject.Neritomen + "");
mainLightsViewHolder.statisticOfStudentNezapocitavana.setText(mainLightsObject.Nezapocitavana + "");
mainLightsViewHolder.statisticOfStudentNeomluveno.setText(mainLightsObject.Neomluveno + "");
mainLightsViewHolder.statisticOfStudentPotvrzeno.setText(mainLightsObject.Potvrzeno + "");
mainLightsViewHolder.statisticOfStudentNepotvrzeno.setText((mainLightsObject.Omluveno-mainLightsObject.Potvrzeno) + "");
mainLightsViewHolder.statisticOfStudentCelkem.setText("(" + mainLightsObject.CelkPocHod + " hod)");
mainLightsViewHolder.statisticOfStudentJmeno.setText(mainLightsObject.Name + "");
mainLightsViewHolder.statisticOfStudentId.setText(mainLightsObject.Id + "");

           // mainLightsViewHolder.textId.setText(mainLightsObject.id + "");
         //   mainLightsViewHolder.textName.setText(mainLightsObject.studentName);


            if((mainLightsObject.Omluveno-mainLightsObject.Potvrzeno) > 35){

                mainLightsViewHolder.statisticOfStudentNepotvrzeno.setTextColor(Color.parseColor("#E53935"));
                mainLightsViewHolder.statisticOfStudentNepotvrzeno.setText((mainLightsObject.Omluveno-mainLightsObject.Potvrzeno) + "");

            }else{

                mainLightsViewHolder.statisticOfStudentNepotvrzeno.setTextColor(Color.parseColor("#212121"));
                mainLightsViewHolder.statisticOfStudentNepotvrzeno.setText((mainLightsObject.Omluveno-mainLightsObject.Potvrzeno) + "");
            }




        }










        @Override
        public int getItemCount() {
            return mMainLightsList.size();
        }

        @Override
        public long getItemId(int position) {

            return position;
        }



        class MainLightsViewHolder extends RecyclerView.ViewHolder{


            TextView statisticOfStudentPritomen;
            TextView statisticOfStudentNepritomen;
            TextView statisticOfStudentNezapocitavana;
            TextView statisticOfStudentNeomluveno;
            TextView statisticOfStudentPotvrzeno;
            TextView statisticOfStudentNepotvrzeno;
            TextView statisticOfStudentCelkem;
            TextView statisticOfStudentJmeno;
            TextView statisticOfStudentId;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                statisticOfStudentPritomen = itemView.findViewById(R.id.statisticOfStudentPritomen);
                statisticOfStudentNepritomen = itemView.findViewById(R.id.statisticOfStudentNepritomen);
                statisticOfStudentNezapocitavana = itemView.findViewById(R.id.statisticOfStudentNezapocitavana);
                statisticOfStudentNeomluveno = itemView.findViewById(R.id.statisticOfStudentNeomluveno);
                statisticOfStudentPotvrzeno = itemView.findViewById(R.id.statisticOfStudentPotvrzeno);
                statisticOfStudentNepotvrzeno = itemView.findViewById(R.id.statisticOfStudentNepotvrzeno);
                statisticOfStudentCelkem = itemView.findViewById(R.id.statisticOfStudentCelkem);
                statisticOfStudentJmeno = itemView.findViewById(R.id.statisticOfStudentJmeno);
                statisticOfStudentId = itemView.findViewById(R.id.statisticOfStudentId);

            }
        }

    }



}












/*
red_050_#ffebee
red_100_#ffcdd2
red_200_#ef9a9a
red_300_#e57373
red_400_#ef5350
red_500_#f44336
red_600_#e53935
red_700_#d32f2f
red_800_#c62828
red_900_#b71c1c
red_A100_#ff8a80
red_A200_#ff5252
red_A400_#ff1744
red_A700_#d50000
pink_050_#fce4ec
pink_100_#f8bbd0
pink_200_#f48fb1
pink_300_#f06292
pink_400_#ec407a
pink_500_#e91e63
pink_600_#d81b60
pink_700_#c2185b
pink_800_#ad1457
pink_900_#880e4f
pink_A100_#ff80ab
pink_A200_#ff4081
pink_A400_#f50057
pink_A700_#c51162
purple_050_#f3e5f5
purple_100_#e1bee7
purple_200_#ce93d8
purple_300_#ba68c8
purple_400_#ab47bc
purple_500_#9c27b0
purple_600_#8e24aa
purple_700_#7b1fa2
purple_800_#6a1b9a
purple_900_#4a148c
purple_A100_#ea80fc
purple_A200_#e040fb
purple_A400_#d500f9
purple_A700_#aa00ff
deepPurple_050_#ede7f6
deepPurple_100_#d1c4e9
deepPurple_200_#b39ddb
deepPurple_300_#9575cd
deepPurple_400_#7e57c2
deepPurple_500_#673ab7
deepPurple_600_#5e35b1
deepPurple_700_#512da8
deepPurple_800_#4527a0
deepPurple_900_#311b92
deepPurple_A100_#b388ff
deepPurple_A200_#7c4dff
deepPurple_A400_#651fff
deepPurple_A700_#6200ea
indigo_050_#e8eaf6
indigo_100_#c5cae9
indigo_200_#9fa8da
indigo_300_#7986cb
indigo_400_#5c6bc0
indigo_500_#3f51b5
indigo_600_#3949ab
indigo_700_#303f9f
indigo_800_#283593
indigo_900_#1a237e
indigo_A100_#8c9eff
indigo_A200_#536dfe
indigo_A400_#3d5afe
indigo_A700_#304ffe
blue_050_#e3f2fd
blue_100_#bbdefb
blue_200_#90caf9
blue_300_#64b5f6
blue_400_#42a5f5
blue_500_#2196f3
blue_600_#1e88e5
blue_700_#1976d2
blue_800_#1565c0
blue_900_#0d47a1
blue_A100_#82b1ff
blue_A200_#448aff
blue_A400_#2979ff
blue_A700_#2962ff
lightBlue_050_#e1f5fe
lightBlue_100_#b3e5fc
lightBlue_200_#81d4fa
lightBlue_300_#4fc3f7
lightBlue_400_#29b6f6
lightBlue_500_#03a9f4
lightBlue_600_#039be5
lightBlue_700_#0288d1
lightBlue_800_#0277bd
lightBlue_900_#01579b
lightBlue_A100_#80d8ff
lightBlue_A200_#40c4ff
lightBlue_A400_#00b0ff
lightBlue_A700_#0091ea
cyan_050_#e0f7fa
cyan_100_#b2ebf2
cyan_200_#80deea
cyan_300_#4dd0e1
cyan_400_#26c6da
cyan_500_#00bcd4
cyan_600_#00acc1
cyan_700_#0097a7
cyan_800_#00838f
cyan_900_#006064
cyan_A100_#84ffff
cyan_A200_#18ffff
cyan_A400_#00e5ff
cyan_A700_#00b8d4
teal_050_#e0f2f1
teal_100_#b2dfdb
teal_200_#80cbc4
teal_300_#4db6ac
teal_400_#26a69a
teal_500_#009688
teal_600_#00897b
teal_700_#00796b
teal_800_#00695c
teal_900_#004d40
teal_A100_#a7ffeb
teal_A200_#64ffda
teal_A400_#1de9b6
teal_A700_#00bfa5
green_050_#e8f5e9
green_100_#c8e6c9
green_200_#a5d6a7
green_300_#81c784
green_400_#66bb6a
green_500_#4caf50
green_600_#43a047
green_700_#388e3c
green_800_#2e7d32
green_900_#1b5e20
green_A100_#b9f6ca
green_A200_#69f0ae
green_A400_#00e676
green_A700_#00c853
lightGreen_050_#f1f8e9
lightGreen_100_#dcedc8
lightGreen_200_#c5e1a5
lightGreen_300_#aed581
lightGreen_400_#9ccc65
lightGreen_500_#8bc34a
lightGreen_600_#7cb342
lightGreen_700_#689f38
lightGreen_800_#558b2f
lightGreen_900_#33691e
lightGreen_A100_#ccff90
lightGreen_A200_#b2ff59
lightGreen_A400_#76ff03
lightGreen_A700_#64dd17
lime_050_#f9fbe7
lime_100_#f0f4c3
lime_200_#e6ee9c
lime_300_#dce775
lime_400_#d4e157
lime_500_#cddc39
lime_600_#c0ca33
lime_700_#afb42b
lime_800_#9e9d24
lime_900_#827717
lime_A100_#f4ff81
lime_A200_#eeff41
lime_A400_#c6ff00
lime_A700_#aeea00
yellow_050_#fffde7
yellow_100_#fff9c4
yellow_200_#fff59d
yellow_300_#fff176
yellow_400_#ffee58
yellow_500_#ffeb3b
yellow_600_#fdd835
yellow_700_#fbc02d
yellow_800_#f9a825
yellow_900_#f57f17
yellow_A100_#ffff8d
yellow_A200_#ffff00
yellow_A400_#ffea00
yellow_A700_#ffd600
amber_050_#fff8e1
amber_100_#ffecb3
amber_200_#ffe082
amber_300_#ffd54f
amber_400_#ffca28
amber_500_#ffc107
amber_600_#ffb300
amber_700_#ffa000
amber_800_#ff8f00
amber_900_#ff6f00
amber_A100_#ffe57f
amber_A200_#ffd740
amber_A400_#ffc400
amber_A700_#ffab00
orange_050_#fff3e0
orange_100_#ffe0b2
orange_200_#ffcc80
orange_300_#ffb74d
orange_400_#ffa726
orange_500_#ff9800
orange_600_#fb8c00
orange_700_#f57c00
orange_800_#ef6c00
orange_900_#e65100
orange_A100_#ffd180
orange_A200_#ffab40
orange_A400_#ff9100
orange_A700_#ff6d00
deepOrange_050_#fbe9e7
deepOrange_100_#ffccbc
deepOrange_200_#ffab91
deepOrange_300_#ff8a65
deepOrange_400_#ff7043
deepOrange_500_#ff5722
deepOrange_600_#f4511e
deepOrange_700_#e64a19
deepOrange_800_#d84315
deepOrange_900_#bf360c
deepOrange_A100_#ff9e80
deepOrange_A200_#ff6e40
deepOrange_A400_#ff3d00
deepOrange_A700_#dd2c00
brown_050_#efebe9
brown_100_#d7ccc8
brown_200_#bcaaa4
brown_300_#a1887f
brown_400_#8d6e63
brown_500_#795548
brown_600_#6d4c41
brown_700_#5d4037
brown_800_#4e342e
brown_900_#3e2723
brown_A100_#d7ccc8
brown_A200_#bcaaa4
brown_A400_#8d6e63
brown_A700_#5d4037
grey_050_#fafafa
grey_100_#f5f5f5
grey_200_#eeeeee
grey_300_#e0e0e0
grey_400_#bdbdbd
grey_500_#9e9e9e
grey_600_#757575
grey_700_#616161
grey_800_#424242
grey_900_#212121
grey_A100_#f5f5f5
grey_A200_#eeeeee
grey_A400_#bdbdbd
grey_A700_#616161
blueGrey_050_#eceff1
blueGrey_100_#cfd8dc
blueGrey_200_#b0bec5
blueGrey_300_#90a4ae
blueGrey_400_#78909c
blueGrey_500_#607d8b
blueGrey_600_#546e7a
blueGrey_700_#455a64
blueGrey_800_#37474f
blueGrey_900_#263238
blueGrey_A100_#cfd8dc
blueGrey_A200_#b0bec5
blueGrey_A400_#78909c
blueGrey_A700_#455a64*/






/*
red_050_#FFEBEE
red_100_#FFCDD2
red_200_#EF9A9A
red_300_#E57373
red_400_#EF5350
red_500_#F44336
red_600_#E53935
red_700_#D32F2F
red_800_#C62828
red_900_#B71C1C
red_A100_#FF8A80
red_A200_#FF5252
red_A400_#FF1744
red_A700_#D50000
pink_050_#FCE4EC
pink_100_#F8BBD0
pink_200_#F48FB1
pink_300_#F06292
pink_400_#EC407A
pink_500_#E91E63
pink_600_#D81B60
pink_700_#C2185B
pink_800_#AD1457
pink_900_#880E4F
pink_A100_#FF80AB
pink_A200_#FF4081
pink_A400_#F50057
pink_A700_#C51162
purple_050_#F3E5F5
purple_100_#E1BEE7
purple_200_#CE93D8
purple_300_#BA68C8
purple_400_#AB47BC
purple_500_#9C27B0
purple_600_#8E24AA
purple_700_#7B1FA2
purple_800_#6A1B9A
purple_900_#4A148C
purple_A100_#EA80FC
purple_A200_#E040FB
purple_A400_#D500F9
purple_A700_#AA00FF
deepPurple_050_#EDE7F6
deepPurple_100_#D1C4E9
deepPurple_200_#B39DDB
deepPurple_300_#9575CD
deepPurple_400_#7E57C2
deepPurple_500_#673AB7
deepPurple_600_#5E35B1
deepPurple_700_#512DA8
deepPurple_800_#4527A0
deepPurple_900_#311B92
deepPurple_A100_#B388FF
deepPurple_A200_#7C4DFF
deepPurple_A400_#651FFF
deepPurple_A700_#6200EA
indigo_050_#E8EAF6
indigo_100_#C5CAE9
indigo_200_#9FA8DA
indigo_300_#7986CB
indigo_400_#5C6BC0
indigo_500_#3F51B5
indigo_600_#3949AB
indigo_700_#303F9F
indigo_800_#283593
indigo_900_#1A237E
indigo_A100_#8C9EFF
indigo_A200_#536DFE
indigo_A400_#3D5AFE
indigo_A700_#304FFE
blue_050_#E3F2FD
blue_100_#BBDEFB
blue_200_#90CAF9
blue_300_#64B5F6
blue_400_#42A5F5
blue_500_#2196F3
blue_600_#1E88E5
blue_700_#1976D2
blue_800_#1565C0
blue_900_#0D47A1
blue_A100_#82B1FF
blue_A200_#448AFF
blue_A400_#2979FF
blue_A700_#2962FF
lightBlue_050_#E1F5FE
lightBlue_100_#B3E5FC
lightBlue_200_#81D4FA
lightBlue_300_#4FC3F7
lightBlue_400_#29B6F6
lightBlue_500_#03A9F4
lightBlue_600_#039BE5
lightBlue_700_#0288D1
lightBlue_800_#0277BD
lightBlue_900_#01579B
lightBlue_A100_#80D8FF
lightBlue_A200_#40C4FF
lightBlue_A400_#00B0FF
lightBlue_A700_#0091EA
cyan_050_#E0F7FA
cyan_100_#B2EBF2
cyan_200_#80DEEA
cyan_300_#4DD0E1
cyan_400_#26C6DA
cyan_500_#00BCD4
cyan_600_#00ACC1
cyan_700_#0097A7
cyan_800_#00838F
cyan_900_#006064
cyan_A100_#84FFFF
cyan_A200_#18FFFF
cyan_A400_#00E5FF
cyan_A700_#00B8D4
teal_050_#E0F2F1
teal_100_#B2DFDB
teal_200_#80CBC4
teal_300_#4DB6AC
teal_400_#26A69A
teal_500_#009688
teal_600_#00897B
teal_700_#00796B
teal_800_#00695C
teal_900_#004D40
teal_A100_#A7FFEB
teal_A200_#64FFDA
teal_A400_#1DE9B6
teal_A700_#00BFA5
green_050_#E8F5E9
green_100_#C8E6C9
green_200_#A5D6A7
green_300_#81C784
green_400_#66BB6A
green_500_#4CAF50
green_600_#43A047
green_700_#388E3C
green_800_#2E7D32
green_900_#1B5E20
green_A100_#B9F6CA
green_A200_#69F0AE
green_A400_#00E676
green_A700_#00C853
lightGreen_050_#F1F8E9
lightGreen_100_#DCEDC8
lightGreen_200_#C5E1A5
lightGreen_300_#AED581
lightGreen_400_#9CCC65
lightGreen_500_#8BC34A
lightGreen_600_#7CB342
lightGreen_700_#689F38
lightGreen_800_#558B2F
lightGreen_900_#33691E
lightGreen_A100_#CCFF90
lightGreen_A200_#B2FF59
lightGreen_A400_#76FF03
lightGreen_A700_#64DD17
lime_050_#F9FBE7
lime_100_#F0F4C3
lime_200_#E6EE9C
lime_300_#DCE775
lime_400_#D4E157
lime_500_#CDDC39
lime_600_#C0CA33
lime_700_#AFB42B
lime_800_#9E9D24
lime_900_#827717
lime_A100_#F4FF81
lime_A200_#EEFF41
lime_A400_#C6FF00
lime_A700_#AEEA00
yellow_050_#FFFDE7
yellow_100_#FFF9C4
yellow_200_#FFF59D
yellow_300_#FFF176
yellow_400_#FFEE58
yellow_500_#FFEB3B
yellow_600_#FDD835
yellow_700_#FBC02D
yellow_800_#F9A825
yellow_900_#F57F17
yellow_A100_#FFFF8D
yellow_A200_#FFFF00
yellow_A400_#FFEA00
yellow_A700_#FFD600
amber_050_#FFF8E1
amber_100_#FFECB3
amber_200_#FFE082
amber_300_#FFD54F
amber_400_#FFCA28
amber_500_#FFC107
amber_600_#FFB300
amber_700_#FFA000
amber_800_#FF8F00
amber_900_#FF6F00
amber_A100_#FFE57F
amber_A200_#FFD740
amber_A400_#FFC400
amber_A700_#FFAB00
orange_050_#FFF3E0
orange_100_#FFE0B2
orange_200_#FFCC80
orange_300_#FFB74D
orange_400_#FFA726
orange_500_#FF9800
orange_600_#FB8C00
orange_700_#F57C00
orange_800_#EF6C00
orange_900_#E65100
orange_A100_#FFD180
orange_A200_#FFAB40
orange_A400_#FF9100
orange_A700_#FF6D00
deepOrange_050_#FBE9E7
deepOrange_100_#FFCCBC
deepOrange_200_#FFAB91
deepOrange_300_#FF8A65
deepOrange_400_#FF7043
deepOrange_500_#FF5722
deepOrange_600_#F4511E
deepOrange_700_#E64A19
deepOrange_800_#D84315
deepOrange_900_#BF360C
deepOrange_A100_#FF9E80
deepOrange_A200_#FF6E40
deepOrange_A400_#FF3D00
deepOrange_A700_#DD2C00
brown_050_#EFEBE9
brown_100_#D7CCC8
brown_200_#BCAAA4
brown_300_#A1887F
brown_400_#8D6E63
brown_500_#795548
brown_600_#6D4C41
brown_700_#5D4037
brown_800_#4E342E
brown_900_#3E2723
brown_A100_#D7CCC8
brown_A200_#BCAAA4
brown_A400_#8D6E63
brown_A700_#5D4037
grey_050_#FAFAFA
grey_100_#F5F5F5
grey_200_#EEEEEE
grey_300_#E0E0E0
grey_400_#BDBDBD
grey_500_#9E9E9E
grey_600_#757575
grey_700_#616161
grey_800_#424242
grey_900_#212121
grey_A100_#F5F5F5
grey_A200_#EEEEEE
grey_A400_#BDBDBD
grey_A700_#616161
blueGrey_050_#ECEFF1
blueGrey_100_#CFD8DC
blueGrey_200_#B0BEC5
blueGrey_300_#90A4AE
blueGrey_400_#78909C
blueGrey_500_#607D8B
blueGrey_600_#546E7A
blueGrey_700_#455A64
blueGrey_800_#37474F
blueGrey_900_#263238
blueGrey_A100_#CFD8DC
blueGrey_A200_#B0BEC5
blueGrey_A400_#78909C
blueGrey_A700_#455A64



 */
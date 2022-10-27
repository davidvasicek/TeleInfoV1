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

    TextView textLastSynchronization;
    LinearLayout linearLayout_synchronization;

    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener listener;

    Toolbar toolbar;

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
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("omluvenkyLG") ;

        recyclerView = (RecyclerView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_RecyclerView);

        textViewMessageRow1 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow1);
        textViewMessageRow2 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow2);
        buttonRefrest = (Button)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_ButtonRefrest);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_AVLoadingIndicatorViewIndicator);

        mApologies_ApologiesObject = new ArrayList<>();

        hideAllViews();
        textViewMessageRow1.setVisibility(View.VISIBLE);
        textViewMessageRow1.setText("Načítání dat");
        aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

        textLastSynchronization = (TextView)findViewById(R.id.textLastSynchronization);
        linearLayout_synchronization = (LinearLayout)findViewById(R.id.linearLayout_synchronization);

        linearLayout_synchronization.setVisibility(View.GONE);

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

                String studenName = "";

                String[] keySplit = dataSnapshot.getKey().split("_");

                for(int i = 2; i<keySplit.length; i++){

                    studenName+= keySplit[i] + " ";
                }

                mainLightsObject.id = Integer.parseInt(keySplit[0]);
                mainLightsObject.studentName = studenName;

                mApologies_ApologiesObject.add(mainLightsObject);

                Collections.sort(mApologies_ApologiesObject, new Comparator<Apologies_ApologiesObject>(){
                    public int compare(Apologies_ApologiesObject obj1, Apologies_ApologiesObject obj2) {
                        return (obj1.id < obj2.id) ? -1: (obj1.id > obj2.id) ? 1:0 ;
                    }});

                mMainStudentsAdapter.notifyDataSetChanged();

                linearLayout_synchronization.setVisibility(View.VISIBLE);
                hideAllViews();
                CheckIsListEmpty(null,null);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Apologies_ApologiesObject mainLightsObject = dataSnapshot.getValue(Apologies_ApologiesObject.class);
                mainLightsObject.key = dataSnapshot.getKey();

                String studenName = "";

                String[] keySplit = dataSnapshot.getKey().split("_");

                for(int i = 2; i<keySplit.length; i++){

                    studenName+= keySplit[i] + " ";
                }

                mainLightsObject.id = Integer.parseInt(keySplit[0]);
                mainLightsObject.studentName = studenName;

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


                    Date date = new Date(epochtime);
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    format.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String formatted = format.format(date);

                    long time = epochtime;
                    Date date1 = new Date(time);
                    //SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy a");
                    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    format1.setTimeZone(TimeZone.getTimeZone("UTC"));

                    textLastSynchronization.setText(format1.format(date1));


                    mDatabaseReference.child("omluvenky").addChildEventListener(listener);

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

            mainLightsViewHolder.textId.setText(mainLightsObject.id + "");
            mainLightsViewHolder.textName.setText(mainLightsObject.studentName);


            if(mainLightsObject.pocNepotvrzeno > 35){

                mainLightsViewHolder.textHours.setTextColor(Color.parseColor("#E53935"));
                mainLightsViewHolder.textHours.setText(mainLightsObject.pocNepotvrzeno + "");

            }else{

                mainLightsViewHolder.textHours.setTextColor(Color.parseColor("#212121"));
                mainLightsViewHolder.textHours.setText(mainLightsObject.pocNepotvrzeno + "");
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


            TextView textId;
            TextView textName;
            TextView textHours;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                textId = itemView.findViewById(R.id.textId);
                textName = itemView.findViewById(R.id.textName);
                textHours = itemView.findViewById(R.id.textHours);

            }
        }

    }


}
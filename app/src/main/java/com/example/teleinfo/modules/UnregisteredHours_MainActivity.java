package com.example.teleinfo.modules;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DATA;
import static com.example.teleinfo.parameters.MainParameters.INIT;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;
import static com.example.teleinfo.parameters.MainParameters.NO_DATA;
import static com.example.teleinfo.parameters.MainParameters.NO_INTERNET;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.USER_TIME_TABLE;

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

public class UnregisteredHours_MainActivity extends AppCompatActivity {

    private List<UnregisteredHours_Object> mUnregisteredHours_ObjectList;
    private MainUnregisteredHoursAdapter mMainUnregisteredHoursAdapter;

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


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shrPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.unregistered_hours_main_activity);



        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        toolbar = (Toolbar) findViewById(R.id.toolbardd);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Nezapsané hodiny" + "</small>"));

        mSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();





        Log.e(TAG, "aaaaaaaaaaaaaaa: " + mSharedPreferences.getString(USER_TIME_TABLE, "") );


        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("/TeleInfo/Administration/UnregisteredHours/Vašíček_David_Vaš") ;

        recyclerView = (RecyclerView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_RecyclerView);

        textViewMessageRow1 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow1);
        textViewMessageRow2 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow2);
        buttonRefrest = (Button)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_ButtonRefrest);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_AVLoadingIndicatorViewIndicator);

        mUnregisteredHours_ObjectList = new ArrayList<>();

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
        mMainUnregisteredHoursAdapter = new MainUnregisteredHoursAdapter(mUnregisteredHours_ObjectList, getApplicationContext(), getSupportFragmentManager());
        recyclerView.setAdapter(mMainUnregisteredHoursAdapter);

        Loaddata();

        // Práce s daty
        listener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                UnregisteredHours_Object unregisteredHours_object = dataSnapshot.getValue(UnregisteredHours_Object.class);
                unregisteredHours_object.Key = dataSnapshot.getKey();

                mUnregisteredHours_ObjectList.add(unregisteredHours_object);

              //  Collections.sort(mUnregisteredHours_ObjectList, new Comparator<UnregisteredHours_Object>(){
                 //   public int compare(UnregisteredHours_Object obj1, UnregisteredHours_Object obj2) {
                //        return (obj1.id < obj2.id) ? -1: (obj1.id > obj2.id) ? 1:0 ;
               //     }});

                mMainUnregisteredHoursAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                UnregisteredHours_Object unregisteredHours_object = dataSnapshot.getValue(UnregisteredHours_Object.class);
                unregisteredHours_object.Key = dataSnapshot.getKey();

                int index = GetItemIndex(unregisteredHours_object, mUnregisteredHours_ObjectList);
                mUnregisteredHours_ObjectList.set(index, unregisteredHours_object);

                mMainUnregisteredHoursAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                UnregisteredHours_Object unregisteredHours_object = dataSnapshot.getValue(UnregisteredHours_Object.class);
                unregisteredHours_object.Key = dataSnapshot.getKey();

                int index = GetItemIndex(unregisteredHours_object, mUnregisteredHours_ObjectList);
                mUnregisteredHours_ObjectList.set(index, unregisteredHours_object);
                mMainUnregisteredHoursAdapter.notifyItemRemoved(index);

                // TODO  otestuj jestli není list prázdný a v tom případě vypiš že nejsou žádná data k zobrazení
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


    private int GetItemIndex(UnregisteredHours_Object unregisteredHours_object, List<UnregisteredHours_Object> list){

        int index = -1;

        for(int i = 0; i<list.size(); i++){

            if(list.get(i).Key.equals(unregisteredHours_object.Key)){
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

    private void CheckIsListEmpty(int action){

        if(action == INIT){

            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.GONE);

            textViewMessageRow1.setText("Načítání dat");

        }else if(action == NO_INTERNET){

            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.VISIBLE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);

            textViewMessageRow1.setText("Nepodařilo se načíst adata");
            textViewMessageRow2.setText("Zkontrolujte připojení k internetu");

        }else if(action == NO_DATA){

            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);

            textViewMessageRow1.setText("Žádná data k zobrazení");
            textViewMessageRow2.setTextColor(getApplicationContext().getResources().getColor(R.color.green500colorAccent));
            textViewMessageRow2.setText("K datu synchronizace nemáte žádné nezapsané hodiny");

        }

        else if(action == DATA){

            recyclerView.setVisibility(View.VISIBLE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.GONE);
            textViewMessageRow2.setVisibility(View.GONE);


        }
    }

    private void Loaddata(){

        if(new IsOnline(getApplicationContext()).isOnline() == IS_ONLINE){

            CheckIsListEmpty(INIT);

            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.i("kokot", dataSnapshot.getChildrenCount() + "");

                    if(dataSnapshot.getChildrenCount() == 0){

                        CheckIsListEmpty(NO_DATA);

                    }else{

                        CheckIsListEmpty(DATA);
                        mDatabaseReference.addChildEventListener(listener);
                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // TODO
                    //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                    //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

                }
            });

        }else{

            CheckIsListEmpty(NO_INTERNET);
        }

    }












    // ------------------------------------------------------------------------------------ Adapter ------------------------------------------------------------------------------------



    public static class MainUnregisteredHoursAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<UnregisteredHours_Object> mMainLightsList;
        private Context mContext;

        FragmentManager fragmentManager;

        public MainUnregisteredHoursAdapter(List<UnregisteredHours_Object> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainUnregisteredHoursAdapter.MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.unregistered_hours_main_activity_adapter_item,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final UnregisteredHours_Object unregisteredHours_object = mMainLightsList.get(position);

            MainUnregisteredHoursAdapter.MainLightsViewHolder mainLightsViewHolder = (MainUnregisteredHoursAdapter.MainLightsViewHolder)holder;

         //   mainLightsViewHolder.textName.setText(unregisteredHours_object.Subject);


            String[] day = unregisteredHours_object.Day.split("\\.");




            mainLightsViewHolder.textViewDay.setText(day[0]);
            mainLightsViewHolder.textViewMonth.setText(day[1]);
            mainLightsViewHolder.textViewYear.setText(day[2]);
            mainLightsViewHolder.textViewLearnedHour.setText(unregisteredHours_object.Hour);
            mainLightsViewHolder.textViewClass.setText(unregisteredHours_object.Class);
            mainLightsViewHolder.textViewSubject.setText(unregisteredHours_object.Subject);



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


            TextView textViewDay;
            TextView textViewMonth;
            TextView textViewYear;
            TextView textViewLearnedHour;
            TextView textViewClass;
            TextView textViewSubject;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                textViewDay = itemView.findViewById(R.id.unregisteredHoursMainActivityAdapterItemTextViewDay);
                textViewMonth = itemView.findViewById(R.id.unregisteredHoursMainActivityAdapterItemTextViewMonth);
                textViewYear = itemView.findViewById(R.id.unregisteredHoursMainActivityAdapterItemTextViewYear);
                textViewLearnedHour = itemView.findViewById(R.id.unregisteredHoursMainActivityAdapterItemTextViewLearnedHour);
                textViewClass = itemView.findViewById(R.id.unregisteredHoursMainActivityAdapterItemTextViewClass);
                textViewSubject = itemView.findViewById(R.id.unregisteredHoursMainActivityAdapterItemTextViewSubject);

            }
        }

    }



}

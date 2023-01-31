package com.example.teleinfo.modules;

import static com.example.teleinfo.parameters.MainParameters.BREAKFAST_SHOW;
import static com.example.teleinfo.parameters.MainParameters.BRUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DATA;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SECOND_SHOW;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SHOW;
import static com.example.teleinfo.parameters.MainParameters.INIT;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;
import static com.example.teleinfo.parameters.MainParameters.LUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.MY_ALERGENS;
import static com.example.teleinfo.parameters.MainParameters.NO_DATA;
import static com.example.teleinfo.parameters.MainParameters.NO_INTERNET;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SNACK_SHOW;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Date;
import java.util.List;

public class ScheduleOfTheYear_MainActivity extends AppCompatActivity {

    private List<ScheduleOfTheYear_Object> mScheduleOfTheYear_ObjectList;
    private ScheduleOfTheYearAdapter mScheduleOfTheYearAdapter;

    RecyclerView recyclerView;
    TextView textViewMessageRow1;
    TextView textViewMessageRow2;
    Button buttonRefrest;
    AVLoadingIndicatorView aVLoadingIndicatorViewIndicator;

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
        setContentView(R.layout.schedule_of_the_year_main_activity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        toolbar = (Toolbar) findViewById(R.id.toolbardd);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Harmonogram roku" + "</small>"));

        mSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();



        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("harmonogramRoku") ;

        recyclerView = (RecyclerView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_RecyclerView);

        textViewMessageRow1 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow1);
        textViewMessageRow2 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow2);
        buttonRefrest = (Button)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_ButtonRefrest);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_AVLoadingIndicatorViewIndicator);

        mScheduleOfTheYear_ObjectList = new ArrayList<>();

        hideAllViews();
        textViewMessageRow1.setVisibility(View.VISIBLE);
        textViewMessageRow1.setText("Načítání dat");
        aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mScheduleOfTheYearAdapter = new ScheduleOfTheYearAdapter(mScheduleOfTheYear_ObjectList, getApplicationContext(), getSupportFragmentManager());
        recyclerView.setAdapter(mScheduleOfTheYearAdapter);

        Loaddata();

        // Práce s daty
        listener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ScheduleOfTheYear_Object ScheduleOfTheYear_Object = dataSnapshot.getValue(ScheduleOfTheYear_Object.class);
                ScheduleOfTheYear_Object.Key = dataSnapshot.getKey();


                if(ScheduleOfTheYear_Object.From >= System.currentTimeMillis()){

                    mScheduleOfTheYear_ObjectList.add(ScheduleOfTheYear_Object);


                }



                //  Collections.sort(mScheduleOfTheYear_ObjectList, new Comparator<ScheduleOfTheYear_Object>(){
                //   public int compare(ScheduleOfTheYear_Object obj1, ScheduleOfTheYear_Object obj2) {
                //        return (obj1.id < obj2.id) ? -1: (obj1.id > obj2.id) ? 1:0 ;
                //     }});

                mScheduleOfTheYearAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ScheduleOfTheYear_Object ScheduleOfTheYear_Object = dataSnapshot.getValue(ScheduleOfTheYear_Object.class);
                ScheduleOfTheYear_Object.Key = dataSnapshot.getKey();

                int index = GetItemIndex(ScheduleOfTheYear_Object, mScheduleOfTheYear_ObjectList);
                mScheduleOfTheYear_ObjectList.set(index, ScheduleOfTheYear_Object);

                mScheduleOfTheYearAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                ScheduleOfTheYear_Object ScheduleOfTheYear_Object = dataSnapshot.getValue(ScheduleOfTheYear_Object.class);
                ScheduleOfTheYear_Object.Key = dataSnapshot.getKey();

                int index = GetItemIndex(ScheduleOfTheYear_Object, mScheduleOfTheYear_ObjectList);
                mScheduleOfTheYear_ObjectList.set(index, ScheduleOfTheYear_Object);
                mScheduleOfTheYearAdapter.notifyItemRemoved(index);

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


    private int GetItemIndex(ScheduleOfTheYear_Object ScheduleOfTheYear_Object, List<ScheduleOfTheYear_Object> list){

        int index = -1;

        for(int i = 0; i<list.size(); i++){

            if(list.get(i).Key.equals(ScheduleOfTheYear_Object.Key)){
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
            // TODO
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



    public static class ScheduleOfTheYearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<ScheduleOfTheYear_Object> mMainLightsList;
        private Context mContext;

        FragmentManager fragmentManager;

        SharedPreferences mSharedPreferences;

        public ScheduleOfTheYearAdapter(List<ScheduleOfTheYear_Object> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_of_the_year_main_activity_adapter_item,parent,false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            MainLightsViewHolder mainLightsViewHolder = (MainLightsViewHolder)holder;

            final ScheduleOfTheYear_Object dinningRoomScheduleOfTheYearObject = mMainLightsList.get(position);

            //mainLightsViewHolder.menuDate.setText(dinningRoomScheduleOfTheYearObject.Date);
           // mainLightsViewHolder.menuDateDay.setText(dinningRoomScheduleOfTheYearObject.DayOfWeek);



            if(dinningRoomScheduleOfTheYearObject.From == dinningRoomScheduleOfTheYearObject.To){

                mainLightsViewHolder.textViewDateSeparator.setVisibility(View.INVISIBLE);
                mainLightsViewHolder.textViewDateToDay.setVisibility(View.INVISIBLE);
                mainLightsViewHolder.textViewDateToMonth.setVisibility(View.INVISIBLE);
                mainLightsViewHolder.textViewDateToYear.setVisibility(View.INVISIBLE);
                mainLightsViewHolder.textViewDateToDayOfTheWeek.setVisibility(View.INVISIBLE);

            }

            try {

                DateFormat sdfDay;
                DateFormat sdfMonth;
                DateFormat sdfYear;
                DateFormat sdfDayOfTheWeek;


                sdfDay = new SimpleDateFormat("dd");
                sdfMonth = new SimpleDateFormat("MM");
                sdfYear = new SimpleDateFormat("yyyy");
                sdfDayOfTheWeek = new SimpleDateFormat("EEEE");


                Date newDate = (new Date(dinningRoomScheduleOfTheYearObject.From));

                mainLightsViewHolder.textViewDateFromDay.setText(sdfDay.format(newDate) + "");
                mainLightsViewHolder.textViewDateFromMonth.setText(sdfMonth.format(newDate) + "");
                mainLightsViewHolder.textViewDateFromYear.setText(sdfYear.format(newDate) + "");
                mainLightsViewHolder.textViewDateFromDayOfTheWeek.setText(sdfDayOfTheWeek.format(newDate) + "");


            } catch (Exception ex) {

            }

            try {

                DateFormat sdfDay;
                DateFormat sdfMonth;
                DateFormat sdfYear;
                DateFormat sdfDayOfTheWeek;


                sdfDay = new SimpleDateFormat("dd");
                sdfMonth = new SimpleDateFormat("MM");
                sdfYear = new SimpleDateFormat("yyyy");
                sdfDayOfTheWeek = new SimpleDateFormat("EEEE");

                Date newDate = (new Date(dinningRoomScheduleOfTheYearObject.To));

                mainLightsViewHolder.textViewDateToDay.setText(sdfDay.format(newDate) + "");
                mainLightsViewHolder.textViewDateToMonth.setText(sdfMonth.format(newDate) + "");
                mainLightsViewHolder.textViewDateToYear.setText(sdfYear.format(newDate) + "");
                mainLightsViewHolder.textViewDateToDayOfTheWeek.setText(sdfDayOfTheWeek.format(newDate) + "");


            } catch (Exception ex) {

            }


            long zbyva = (int)((dinningRoomScheduleOfTheYearObject.From - System.currentTimeMillis()) / 86400000);

            mainLightsViewHolder.textViewNumberOfDayeTo.setText("za " + zbyva + " dní");
            mainLightsViewHolder.textViewEvent.setText(dinningRoomScheduleOfTheYearObject.Event);

            if(dinningRoomScheduleOfTheYearObject.State.compareTo("Volno") == 0){

                mainLightsViewHolder.cardViewStatus.setCardBackgroundColor(mContext.getResources().getColor(R.color.green500colorAccent));
            }

            if(dinningRoomScheduleOfTheYearObject.State.compareTo("Událost") == 0){

                mainLightsViewHolder.cardViewStatus.setCardBackgroundColor(mContext.getResources().getColor(R.color.blue500colorAccent));
            }

            Log.e("mylog", "ttttttttt " + dinningRoomScheduleOfTheYearObject.From);
            Log.e("mylog", "ttttttttt111 " + System.currentTimeMillis());

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

            TextView textViewDateFromDay;
            TextView textViewDateFromMonth;
            TextView textViewDateFromYear;
            TextView textViewDateFromDayOfTheWeek;
            TextView textViewDateSeparator;
            TextView textViewDateToDay;
            TextView textViewDateToMonth;
            TextView textViewDateToYear;
            TextView textViewDateToDayOfTheWeek;
            TextView textViewEvent;
            TextView textViewNumberOfDayeTo;
            CardView cardViewStatus;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                textViewDateFromDay = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateFromDay);
                textViewDateFromMonth = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateFromMonth);
                textViewDateFromYear = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateFromYear);
                textViewDateFromDayOfTheWeek = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateFromDayOfTheWeek);
                textViewDateSeparator = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateSeparator);
                textViewDateToDay = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateToDay);
                textViewDateToMonth = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateToMonth);
                textViewDateToYear = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateToYear);
                textViewDateToDayOfTheWeek = itemView.findViewById(R.id.scheduleOfTheYearTextViewDateToDayOfTheWeek);
                textViewEvent = itemView.findViewById(R.id.scheduleOfTheYearTextViewEvent);
                textViewNumberOfDayeTo = itemView.findViewById(R.id.scheduleOfTheYearTextViewNumberOfDayeTo);
                cardViewStatus = itemView.findViewById(R.id.scheduleOfTheYearCardViewStatus);





            }
        }
    }
}

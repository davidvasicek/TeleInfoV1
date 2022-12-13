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
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

public class Menu_MainActivity extends AppCompatActivity {

    private List<Menu_Object> mMenu_ObjectList;
    private MenuAdapter mMenuAdapter;

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
        setContentView(R.layout.menu_main_activity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        toolbar = (Toolbar) findViewById(R.id.toolbardd);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Jídelníček" + "</small>"));

        mSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();


        Log.e("mylog", "ttttttttt " + mSharedPreferences.getString(MY_ALERGENS, ""));

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("jidelnicek") ;

        recyclerView = (RecyclerView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_RecyclerView);

        textViewMessageRow1 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow1);
        textViewMessageRow2 = (TextView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow2);
        buttonRefrest = (Button)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_ButtonRefrest);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_AVLoadingIndicatorViewIndicator);

        mMenu_ObjectList = new ArrayList<>();

        hideAllViews();
        textViewMessageRow1.setVisibility(View.VISIBLE);
        textViewMessageRow1.setText("Načítání dat");
        aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mMenuAdapter = new MenuAdapter(mMenu_ObjectList, getApplicationContext(), getSupportFragmentManager());
        recyclerView.setAdapter(mMenuAdapter);

        Loaddata();

        // Práce s daty
        listener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Menu_Object Menu_Object = dataSnapshot.getValue(Menu_Object.class);
                Menu_Object.Key = dataSnapshot.getKey();

                mMenu_ObjectList.add(Menu_Object);

                //  Collections.sort(mMenu_ObjectList, new Comparator<Menu_Object>(){
                //   public int compare(Menu_Object obj1, Menu_Object obj2) {
                //        return (obj1.id < obj2.id) ? -1: (obj1.id > obj2.id) ? 1:0 ;
                //     }});

                mMenuAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Menu_Object Menu_Object = dataSnapshot.getValue(Menu_Object.class);
                Menu_Object.Key = dataSnapshot.getKey();

                int index = GetItemIndex(Menu_Object, mMenu_ObjectList);
                mMenu_ObjectList.set(index, Menu_Object);

                mMenuAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Menu_Object Menu_Object = dataSnapshot.getValue(Menu_Object.class);
                Menu_Object.Key = dataSnapshot.getKey();

                int index = GetItemIndex(Menu_Object, mMenu_ObjectList);
                mMenu_ObjectList.set(index, Menu_Object);
                mMenuAdapter.notifyItemRemoved(index);

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


    private int GetItemIndex(Menu_Object Menu_Object, List<Menu_Object> list){

        int index = -1;

        for(int i = 0; i<list.size(); i++){

            if(list.get(i).Key.equals(Menu_Object.Key)){
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



    public static class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<Menu_Object> mMainLightsList;
        private Context mContext;

        FragmentManager fragmentManager;

        SharedPreferences mSharedPreferences;

        public MenuAdapter(List<Menu_Object> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MenuAdapter.MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_main_activity_adapter_item,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            MenuAdapter.MainLightsViewHolder mainLightsViewHolder = (MenuAdapter.MainLightsViewHolder)holder;

            final Menu_Object dinningRoomMenuObject = mMainLightsList.get(position);



            mainLightsViewHolder.textViewDay.setText(dinningRoomMenuObject.Date.split("\\.")[0]);
            mainLightsViewHolder.textViewMonth.setText(dinningRoomMenuObject.Date.split("\\.")[1]);
            mainLightsViewHolder.textViewYear.setText(dinningRoomMenuObject.Date.split("\\.")[2]);
            mainLightsViewHolder.textViewDayOfTheWeek.setText(dinningRoomMenuObject.DayOfWeek);


            if(dinningRoomMenuObject.Status == 1){

                mainLightsViewHolder.Status.setVisibility(View.VISIBLE);
                mainLightsViewHolder.Status.setText("Státní svátek");

                mainLightsViewHolder.BreakfastTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Breakfast.setVisibility(View.GONE);
                mainLightsViewHolder.BreakfastAllergens.setVisibility(View.GONE);
                mainLightsViewHolder.BrunchTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Brunch.setVisibility(View.GONE);
                mainLightsViewHolder.BrunchAllergens.setVisibility(View.GONE);
                mainLightsViewHolder.LunchTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Lunch.setVisibility(View.GONE);
                mainLightsViewHolder.LunchAllergens.setVisibility(View.GONE);
                mainLightsViewHolder.SnackTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Snack.setVisibility(View.GONE);
                mainLightsViewHolder.SnackAllergens.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Dinner.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerAllergens.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerSecondTitle.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerSecond.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerSecondAllergens.setVisibility(View.GONE);

            }else{

                mainLightsViewHolder.Status.setVisibility(View.GONE);

                if(mSharedPreferences.getBoolean(BREAKFAST_SHOW, false)){

                    mainLightsViewHolder.BreakfastTitle.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.Breakfast.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.BreakfastAllergens.setVisibility(View.VISIBLE);

                }else{

                    mainLightsViewHolder.BreakfastTitle.setVisibility(View.GONE);
                    mainLightsViewHolder.Breakfast.setVisibility(View.GONE);
                    mainLightsViewHolder.BreakfastAllergens.setVisibility(View.GONE);
                }

                if(mSharedPreferences.getBoolean(BRUNCH_SHOW, false)){

                    mainLightsViewHolder.BrunchTitle.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.Brunch.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.BrunchAllergens.setVisibility(View.VISIBLE);

                }else{

                    mainLightsViewHolder.BrunchTitle.setVisibility(View.GONE);
                    mainLightsViewHolder.Brunch.setVisibility(View.GONE);
                    mainLightsViewHolder.BrunchAllergens.setVisibility(View.GONE);
                }

                if(mSharedPreferences.getBoolean(LUNCH_SHOW, true)){

                    mainLightsViewHolder.LunchTitle.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.Lunch.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.LunchAllergens.setVisibility(View.VISIBLE);

                }else{

                    mainLightsViewHolder.LunchTitle.setVisibility(View.GONE);
                    mainLightsViewHolder.Lunch.setVisibility(View.GONE);
                    mainLightsViewHolder.LunchAllergens.setVisibility(View.GONE);
                }

                if(mSharedPreferences.getBoolean(SNACK_SHOW, false)){

                    mainLightsViewHolder.SnackTitle.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.Snack.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.SnackAllergens.setVisibility(View.VISIBLE);

                }else{

                    mainLightsViewHolder.SnackTitle.setVisibility(View.GONE);
                    mainLightsViewHolder.Snack.setVisibility(View.GONE);
                    mainLightsViewHolder.SnackAllergens.setVisibility(View.GONE);
                }

                if(mSharedPreferences.getBoolean(DINNER_SHOW, false)){

                    mainLightsViewHolder.DinnerTitle.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.Dinner.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.DinnerAllergens.setVisibility(View.VISIBLE);

                }else{

                    mainLightsViewHolder.DinnerTitle.setVisibility(View.GONE);
                    mainLightsViewHolder.Dinner.setVisibility(View.GONE);
                    mainLightsViewHolder.DinnerAllergens.setVisibility(View.GONE);
                }

                if(mSharedPreferences.getBoolean(DINNER_SECOND_SHOW, false)){

                    mainLightsViewHolder.DinnerSecondTitle.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.DinnerSecond.setVisibility(View.VISIBLE);
                    mainLightsViewHolder.DinnerSecondAllergens.setVisibility(View.VISIBLE);

                }else{

                    mainLightsViewHolder.DinnerSecondTitle.setVisibility(View.GONE);
                    mainLightsViewHolder.DinnerSecond.setVisibility(View.GONE);
                    mainLightsViewHolder.DinnerSecondAllergens.setVisibility(View.GONE);
                }

                String[] myAlergensStringSplit = mSharedPreferences.getString(MY_ALERGENS, "").split("_");

                Log.e("mylog", "bbbbbbbb " + myAlergensStringSplit);
                Log.e("mylog", "bbbbbbbb " + myAlergensStringSplit.length);



                mainLightsViewHolder.BreakfastTitle.setText("Snídaně");
                if(dinningRoomMenuObject.Breakfast.compareTo("") != 0){
                    mainLightsViewHolder.Breakfast.setText(dinningRoomMenuObject.Breakfast);
                }else{
                    mainLightsViewHolder.Breakfast.setText("-");
                }
                if(dinningRoomMenuObject.BreakfastAllergens.compareTo("") != 0){

                    String[] foodAlergensStringSplit = dinningRoomMenuObject.BreakfastAllergens.split(",");
                    String finalHTMLString = "";

                    for(int i = 0; i<foodAlergensStringSplit.length;i++){

                        String foodAlergen = foodAlergensStringSplit[i].replace(" ", "");
                        boolean find = false;
                        Log.e("mylog", "ttttttttt " + foodAlergen);

                        for(int j = 0; j<myAlergensStringSplit.length;j++){

                            if(myAlergensStringSplit[j].compareTo(foodAlergen) == 0){

                                Log.e("mylog", "ttttttttt " + "ano");
                                find = true;
                                break;
                            }
                        }

                        if(find){
                            Log.e("mylog", "ttttttttt " + "anoooo");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.red700colorAccent) + "'>" + foodAlergen + "</font>, ";

                        }else{
                            Log.e("mylog", "ttttttttt " + "ne");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.text_secondary) + "'>" + foodAlergen + "</font>, ";
                        }
                    }
                    finalHTMLString = finalHTMLString.substring(0, (finalHTMLString.length()-2));
                    mainLightsViewHolder.BreakfastAllergens.setText(Html.fromHtml(finalHTMLString), TextView.BufferType.SPANNABLE);

                }else{
                    mainLightsViewHolder.BreakfastAllergens.setVisibility(View.GONE);
                }

                mainLightsViewHolder.BrunchTitle.setText("Přesnidávka");
                if(dinningRoomMenuObject.Brunch.compareTo("") != 0){
                    mainLightsViewHolder.Brunch.setText(dinningRoomMenuObject.Brunch);
                }else{
                    mainLightsViewHolder.Brunch.setText("-");
                }
                if(dinningRoomMenuObject.BrunchAllergens.compareTo("") != 0){

                    String[] foodAlergensStringSplit = dinningRoomMenuObject.BrunchAllergens.split(",");
                    String finalHTMLString = "";

                    for(int i = 0; i<foodAlergensStringSplit.length;i++){

                        String foodAlergen = foodAlergensStringSplit[i].replace(" ", "");
                        boolean find = false;
                        Log.e("mylog", "ttttttttt " + foodAlergen);

                        for(int j = 0; j<myAlergensStringSplit.length;j++){

                            if(myAlergensStringSplit[j].compareTo(foodAlergen) == 0){

                                Log.e("mylog", "ttttttttt " + "ano");
                                find = true;
                                break;
                            }
                        }

                        if(find){
                            Log.e("mylog", "ttttttttt " + "anoooo");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.red700colorAccent) + "'>" + foodAlergen + "</font>, ";

                        }else{
                            Log.e("mylog", "ttttttttt " + "ne");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.text_secondary) + "'>" + foodAlergen + "</font>, ";
                        }
                    }
                    finalHTMLString = finalHTMLString.substring(0, (finalHTMLString.length()-2));
                    mainLightsViewHolder.BrunchAllergens.setText(Html.fromHtml(finalHTMLString), TextView.BufferType.SPANNABLE);

                }else{
                    mainLightsViewHolder.BrunchAllergens.setVisibility(View.GONE);
                }

                mainLightsViewHolder.LunchTitle.setText("Oběd");
                if(dinningRoomMenuObject.Lunch.compareTo("") != 0){
                    mainLightsViewHolder.Lunch.setText(dinningRoomMenuObject.Lunch);
                }else{
                    mainLightsViewHolder.Lunch.setText("-");
                }
                if(dinningRoomMenuObject.LunchAllergens.compareTo("") != 0){

                    String[] foodAlergensStringSplit = dinningRoomMenuObject.LunchAllergens.split(",");
                    String finalHTMLString = "";

                    for(int i = 0; i<foodAlergensStringSplit.length;i++){

                        String foodAlergen = foodAlergensStringSplit[i].replace(" ", "");
                        boolean find = false;
                        Log.e("mylog", "ttttttttt " + foodAlergen);

                        for(int j = 0; j<myAlergensStringSplit.length;j++){

                            if(myAlergensStringSplit[j].compareTo(foodAlergen) == 0){

                                Log.e("mylog", "ttttttttt " + "ano");
                                find = true;
                                break;
                            }
                        }

                        if(find){
                            Log.e("mylog", "ttttttttt " + "anoooo");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.red700colorAccent) + "'>" + foodAlergen + "</font>, ";

                        }else{
                            Log.e("mylog", "ttttttttt " + "ne");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.text_secondary) + "'>" + foodAlergen + "</font>, ";
                        }
                    }
                    finalHTMLString = finalHTMLString.substring(0, (finalHTMLString.length()-2));
                    mainLightsViewHolder.LunchAllergens.setText(Html.fromHtml(finalHTMLString), TextView.BufferType.SPANNABLE);

                }else{
                    mainLightsViewHolder.LunchAllergens.setVisibility(View.GONE);
                }

                mainLightsViewHolder.SnackTitle.setText("Svačina");
                if(dinningRoomMenuObject.Snack.compareTo("") != 0){
                    mainLightsViewHolder.Snack.setText(dinningRoomMenuObject.Snack);
                }else{
                    mainLightsViewHolder.Snack.setText("-");
                }
                if(dinningRoomMenuObject.SnackAllergens.compareTo("") != 0){

                    String[] foodAlergensStringSplit = dinningRoomMenuObject.SnackAllergens.split(",");
                    String finalHTMLString = "";

                    for(int i = 0; i<foodAlergensStringSplit.length;i++){

                        String foodAlergen = foodAlergensStringSplit[i].replace(" ", "");
                        boolean find = false;
                        Log.e("mylog", "ttttttttt " + foodAlergen);

                        for(int j = 0; j<myAlergensStringSplit.length;j++){

                            if(myAlergensStringSplit[j].compareTo(foodAlergen) == 0){

                                Log.e("mylog", "ttttttttt " + "ano");
                                find = true;
                                break;
                            }
                        }

                        if(find){
                            Log.e("mylog", "ttttttttt " + "anoooo");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.red700colorAccent) + "'>" + foodAlergen + "</font>, ";

                        }else{
                            Log.e("mylog", "ttttttttt " + "ne");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.text_secondary) + "'>" + foodAlergen + "</font>, ";
                        }
                    }
                    finalHTMLString = finalHTMLString.substring(0, (finalHTMLString.length()-2));
                    mainLightsViewHolder.SnackAllergens.setText(Html.fromHtml(finalHTMLString), TextView.BufferType.SPANNABLE);

                }else{
                    mainLightsViewHolder.SnackAllergens.setVisibility(View.GONE);
                }

                mainLightsViewHolder.DinnerTitle.setText("Večeře");
                if(dinningRoomMenuObject.Dinner.compareTo("") != 0){
                    mainLightsViewHolder.Dinner.setText(dinningRoomMenuObject.Dinner);
                }else{
                    mainLightsViewHolder.Dinner.setText("-");
                }
                if(dinningRoomMenuObject.DinnerAllergens.compareTo("") != 0){

                    String[] foodAlergensStringSplit = dinningRoomMenuObject.DinnerAllergens.split(",");
                    String finalHTMLString = "";

                    for(int i = 0; i<foodAlergensStringSplit.length;i++){

                        String foodAlergen = foodAlergensStringSplit[i].replace(" ", "");
                        boolean find = false;
                        Log.e("mylog", "ttttttttt " + foodAlergen);

                        for(int j = 0; j<myAlergensStringSplit.length;j++){

                            if(myAlergensStringSplit[j].compareTo(foodAlergen) == 0){

                                Log.e("mylog", "ttttttttt " + "ano");
                                find = true;
                                break;
                            }
                        }

                        if(find){
                            Log.e("mylog", "ttttttttt " + "anoooo");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.red700colorAccent) + "'>" + foodAlergen + "</font>, ";

                        }else{
                            Log.e("mylog", "ttttttttt " + "ne");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.text_secondary) + "'>" + foodAlergen + "</font>, ";
                        }
                    }
                    finalHTMLString = finalHTMLString.substring(0, (finalHTMLString.length()-2));
                    mainLightsViewHolder.DinnerAllergens.setText(Html.fromHtml(finalHTMLString), TextView.BufferType.SPANNABLE);

                }else{
                    mainLightsViewHolder.DinnerAllergens.setVisibility(View.GONE);
                }

                mainLightsViewHolder.DinnerSecondTitle.setText("Večeře II.");
                if(dinningRoomMenuObject.DinnerSecond.compareTo("") != 0){
                    mainLightsViewHolder.DinnerSecond.setText(dinningRoomMenuObject.DinnerSecond);
                }else{
                    mainLightsViewHolder.DinnerSecond.setText("-");
                }
                if(dinningRoomMenuObject.DinnerSecondAllergens.compareTo("") != 0){

                    String[] foodAlergensStringSplit = dinningRoomMenuObject.DinnerSecondAllergens.split(",");
                    String finalHTMLString = "";

                    for(int i = 0; i<foodAlergensStringSplit.length;i++){

                        String foodAlergen = foodAlergensStringSplit[i].replace(" ", "");
                        boolean find = false;
                        Log.e("mylog", "ttttttttt " + foodAlergen);

                        for(int j = 0; j<myAlergensStringSplit.length;j++){

                            if(myAlergensStringSplit[j].compareTo(foodAlergen) == 0){

                                Log.e("mylog", "ttttttttt " + "ano");
                                find = true;
                                break;
                            }
                        }

                        if(find){
                            Log.e("mylog", "ttttttttt " + "anoooo");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.red700colorAccent) + "'>" + foodAlergen + "</font>, ";

                        }else{
                            Log.e("mylog", "ttttttttt " + "ne");
                            finalHTMLString += "<font color='" + mContext.getResources().getColor(R.color.text_secondary) + "'>" + foodAlergen + "</font>, ";
                        }
                    }
                    finalHTMLString = finalHTMLString.substring(0, (finalHTMLString.length()-2));
                    mainLightsViewHolder.DinnerSecondAllergens.setText(Html.fromHtml(finalHTMLString), TextView.BufferType.SPANNABLE);

                }else{
                    mainLightsViewHolder.DinnerSecondAllergens.setVisibility(View.GONE);
                }




                mainLightsViewHolder.BreakfastAllergens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Menu_AlergensDialog menu_alergensDialog = new Menu_AlergensDialog(mSharedPreferences.getString(MY_ALERGENS, ""), dinningRoomMenuObject.BreakfastAllergens);
                        menu_alergensDialog.show(fragmentManager, "exampleBottomSheet");

                    }
                });

                mainLightsViewHolder.BrunchAllergens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Menu_AlergensDialog menu_alergensDialog = new Menu_AlergensDialog(mSharedPreferences.getString(MY_ALERGENS, ""), dinningRoomMenuObject.BrunchAllergens);
                        menu_alergensDialog.show(fragmentManager, "exampleBottomSheet");

                    }
                });

                mainLightsViewHolder.LunchAllergens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Menu_AlergensDialog menu_alergensDialog = new Menu_AlergensDialog(mSharedPreferences.getString(MY_ALERGENS, ""), dinningRoomMenuObject.LunchAllergens);
                        menu_alergensDialog.show(fragmentManager, "exampleBottomSheet");

                    }
                });

                mainLightsViewHolder.SnackAllergens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Menu_AlergensDialog menu_alergensDialog = new Menu_AlergensDialog(mSharedPreferences.getString(MY_ALERGENS, ""), dinningRoomMenuObject.SnackAllergens);
                        menu_alergensDialog.show(fragmentManager, "exampleBottomSheet");

                    }
                });

                mainLightsViewHolder.DinnerAllergens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Menu_AlergensDialog menu_alergensDialog = new Menu_AlergensDialog(mSharedPreferences.getString(MY_ALERGENS, ""), dinningRoomMenuObject.DinnerAllergens);
                        menu_alergensDialog.show(fragmentManager, "exampleBottomSheet");

                    }
                });

                mainLightsViewHolder.DinnerSecondAllergens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Menu_AlergensDialog menu_alergensDialog = new Menu_AlergensDialog(mSharedPreferences.getString(MY_ALERGENS, ""), dinningRoomMenuObject.DinnerSecondAllergens);
                        menu_alergensDialog.show(fragmentManager, "exampleBottomSheet");

                    }
                });








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

            TextView textViewDay;
            TextView textViewMonth;
            TextView textViewYear;
            TextView textViewDayOfTheWeek;
            TextView Status;
            TextView BreakfastTitle;
            TextView Breakfast;
            TextView BreakfastAllergens;
            TextView BrunchTitle;
            TextView Brunch;
            TextView BrunchAllergens;
            TextView LunchTitle;
            TextView Lunch;
            TextView LunchAllergens;
            TextView SnackTitle;
            TextView Snack;
            TextView SnackAllergens;
            TextView DinnerTitle;
            TextView Dinner;
            TextView DinnerAllergens;
            TextView DinnerSecondTitle;
            TextView DinnerSecond;
            TextView DinnerSecondAllergens;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                textViewDay = itemView.findViewById(R.id.menuMainActivityAdapterItemTextViewDay);
                textViewMonth = itemView.findViewById(R.id.menuMainActivityAdapterItemTextViewMonth);
                textViewYear = itemView.findViewById(R.id.menuMainActivityAdapterItemTextViewYear);
                textViewDayOfTheWeek = itemView.findViewById(R.id.menuMainActivityAdapterItemTextViewDayOfTheWeek);

                Status = itemView.findViewById(R.id.Status);
                BreakfastTitle = itemView.findViewById(R.id.BreakfastTitle1);
                Breakfast = itemView.findViewById(R.id.Breakfast1);
                BreakfastAllergens = itemView.findViewById(R.id.BreakfastAllergens1);
                BrunchTitle = itemView.findViewById(R.id.BrunchTitle1);
                Brunch = itemView.findViewById(R.id.Brunch1);
                BrunchAllergens = itemView.findViewById(R.id.BrunchAllergens1);
                LunchTitle = itemView.findViewById(R.id.LunchTitle1);
                Lunch = itemView.findViewById(R.id.Lunch1);
                LunchAllergens = itemView.findViewById(R.id.LunchAllergens1);
                SnackTitle = itemView.findViewById(R.id.SnackTitle1);
                Snack = itemView.findViewById(R.id.Snack1);
                SnackAllergens = itemView.findViewById(R.id.SnackAllergens1);
                DinnerTitle = itemView.findViewById(R.id.DinnerTitle1);
                Dinner = itemView.findViewById(R.id.Dinner1);
                DinnerAllergens = itemView.findViewById(R.id.DinnerAllergens1);
                DinnerSecondTitle = itemView.findViewById(R.id.DinnerSecondTitle1);
                DinnerSecond = itemView.findViewById(R.id.DinnerSecond1);
                DinnerSecondAllergens = itemView.findViewById(R.id.DinnerSecondAllergens1);
            }
        }

    }



}

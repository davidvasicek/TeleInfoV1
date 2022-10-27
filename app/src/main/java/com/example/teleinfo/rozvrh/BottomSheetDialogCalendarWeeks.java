package com.example.teleinfo.rozvrh;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogCalendarWeeks extends BottomSheetDialogFragment {

    ArrayList<ObjectDates> source;

    private DayDateListenerWeeks dayDateListenerWeeks;





    int selectedDay;
    int selectedMonth;
    int selectedYear;

    int selectedPosition;



    TextView textViewPreviousWeekTitle;
    TextView textViewPreviousWeekDateFromTheNameOfTheDayOfTheWeek;
    TextView textViewPreviousWeekDateFromDay;
    TextView textViewPreviousWeekDateFromMonth;
    TextView textViewPreviousWeekDateFromYear;
    TextView textViewPreviousWeekDateToTheNameOfTheDayOfTheWeek;
    TextView textViewPreviousWeekDateToDay;
    TextView textViewPreviousWeekDateToMonth;
    TextView textViewPreviousWeekDateToYear;
    TextView textViewActualWeekTitle;
    TextView textViewActualWeekDateFromTheNameOfTheDayOfTheWeek;
    TextView textViewActualWeekDateFromDay;
    TextView textViewActualWeekDateFromMonth;
    TextView textViewActualWeekDateFromYear;
    TextView textViewActualWeekDateToTheNameOfTheDayOfTheWeek;
    TextView textViewActualWeekDateToDay;
    TextView textViewActualWeekDateToMonth;
    TextView textViewActualWeekDateToYear;
    TextView textViewNextWeekTitle;
    TextView textViewNextWeekDateFromTheNameOfTheDayOfTheWeek;
    TextView textViewNextWeekDateFromDay;
    TextView textViewNextWeekDateFromMonth;
    TextView textViewNextWeekDateFromYear;
    TextView textViewNextWeekDateToTheNameOfTheDayOfTheWeek;
    TextView textViewNextWeekDateToDay;
    TextView textViewNextWeekDateToMonth;
    TextView textViewNextWeekDateToYear;
    LinearLayout linearLayoutPreviousWeek;
    LinearLayout linearLayoutActualWeek;
    LinearLayout linearLayoutNextWeek;


    
    
    
    

    public BottomSheetDialogCalendarWeeks() {



    }


    @Override
    public void onAttach(Context context) {

        Log.e(TAG, "onAttach: ClassCastException aaaa: " + getChildFragmentManager() + "   -   " +  getParentFragmentManager() + "   -   " + getParentFragment() + "   -   " );

        if(getChildFragmentManager() == null){

            try{
                Log.e(TAG, "onAttach: ClassCastException aaaa: "  );

                dayDateListenerWeeks = (DayDateListenerWeeks) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException 11: " + e.getMessage() );
            }
        }else{
            try{
                Log.e(TAG, "onAttach: ClassCastException oooo: "  );

                dayDateListenerWeeks = (DayDateListenerWeeks) context;
                Log.e(TAG, "onAttach: ClassCastException oooo1: " );
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException 22: " + e.getMessage() );
            }
        }
        super.onAttach(context);
    }

    public interface DayDateListenerWeeks {
        void applyDayDateListenerWeeks(ObjectDatesWeek objectDatesWeek);
    }








    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.test_activity_test_recycler_view_horizontal_weeks, null);
        dialog.setContentView(contentView);

        textViewPreviousWeekTitle = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekTitle);
        textViewPreviousWeekDateFromTheNameOfTheDayOfTheWeek = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateFromTheNameOfTheDayOfTheWeek);
        textViewPreviousWeekDateFromDay = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateFromDay);
        textViewPreviousWeekDateFromMonth = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateFromMonth);
        textViewPreviousWeekDateFromYear = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateFromYear);
        textViewPreviousWeekDateToTheNameOfTheDayOfTheWeek = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateToTheNameOfTheDayOfTheWeek);
        textViewPreviousWeekDateToDay = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateToDay);
        textViewPreviousWeekDateToMonth = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateToMonth);
        textViewPreviousWeekDateToYear = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewPreviousWeekDateToYear);
        textViewActualWeekTitle = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekTitle);
        textViewActualWeekDateFromTheNameOfTheDayOfTheWeek = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateFromTheNameOfTheDayOfTheWeek);
        textViewActualWeekDateFromDay = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateFromDay);
        textViewActualWeekDateFromMonth = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateFromMonth);
        textViewActualWeekDateFromYear = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateFromYear);
        textViewActualWeekDateToTheNameOfTheDayOfTheWeek = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateToTheNameOfTheDayOfTheWeek);
        textViewActualWeekDateToDay = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateToDay);
        textViewActualWeekDateToMonth = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateToMonth);
        textViewActualWeekDateToYear = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewActualWeekDateToYear);
        textViewNextWeekTitle = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekTitle);
        textViewNextWeekDateFromTheNameOfTheDayOfTheWeek = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateFromTheNameOfTheDayOfTheWeek);
        textViewNextWeekDateFromDay = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateFromDay);
        textViewNextWeekDateFromMonth = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateFromMonth);
        textViewNextWeekDateFromYear = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateFromYear);
        textViewNextWeekDateToTheNameOfTheDayOfTheWeek = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateToTheNameOfTheDayOfTheWeek);
        textViewNextWeekDateToDay = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateToDay);
        textViewNextWeekDateToMonth = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateToMonth);
        textViewNextWeekDateToYear = (TextView)contentView.findViewById(R.id.aaaaaaaaaaTextViewNextWeekDateToYear);
        linearLayoutPreviousWeek = (LinearLayout)contentView.findViewById(R.id.aaaaaaaaaaLinearLayoutPreviousWeek);
        linearLayoutActualWeek = (LinearLayout)contentView.findViewById(R.id.aaaaaaaaaaLinearLayoutActualWeek);
        linearLayoutNextWeek = (LinearLayout)contentView.findViewById(R.id.aaaaaaaaaaLinearLayoutNextWeek);


        AddItemsToRecyclerViewArrayList();


        ObjectDatesWeek objectDatesWeek1 = new ObjectDatesWeek();
        objectDatesWeek1.day1 = 17;
        objectDatesWeek1.month1 = 10;
        objectDatesWeek1.year1 = 2022;
        objectDatesWeek1.dayOfTheWeek1 = 1;
        objectDatesWeek1.day2 = 18;
        objectDatesWeek1.month2 = 10;
        objectDatesWeek1.year2 = 2022;
        objectDatesWeek1.dayOfTheWeek2 = 2;
        objectDatesWeek1.day3 = 19;
        objectDatesWeek1.month3 = 10;
        objectDatesWeek1.year3 = 2022;
        objectDatesWeek1.dayOfTheWeek3 = 3;
        objectDatesWeek1.day4 = 20;
        objectDatesWeek1.month4 = 10;
        objectDatesWeek1.year4 = 2022;
        objectDatesWeek1.dayOfTheWeek4 = 4;
        objectDatesWeek1.day5 = 21;
        objectDatesWeek1.month5 = 10;
        objectDatesWeek1.year5 = 2022;
        objectDatesWeek1.dayOfTheWeek5 = 5;
        objectDatesWeek1.theNameOfTheDayOfTheWeek1 = "Pondělí";
        objectDatesWeek1.shortTheNameOfTheDayOfTheWeek1 = "Po";
        objectDatesWeek1.theNameOfTheDayOfTheWeek2 = "Úterý";
        objectDatesWeek1.shortTheNameOfTheDayOfTheWeek2 = "Út";
        objectDatesWeek1.theNameOfTheDayOfTheWeek3 = "Středa";
        objectDatesWeek1.shortTheNameOfTheDayOfTheWeek3 = "St";
        objectDatesWeek1.theNameOfTheDayOfTheWeek4 = "Čtvrtek";
        objectDatesWeek1.shortTheNameOfTheDayOfTheWeek4 = "Čt";
        objectDatesWeek1.theNameOfTheDayOfTheWeek5 = "Pátek";
        objectDatesWeek1.shortTheNameOfTheDayOfTheWeek5 = "Pá";
        objectDatesWeek1.weekTitle= "Předchozí týden";




        ObjectDatesWeek objectDatesWeek2 = new ObjectDatesWeek();
        objectDatesWeek2.day1 = 24;
        objectDatesWeek2.month1 = 10;
        objectDatesWeek2.year1 = 2022;
        objectDatesWeek2.dayOfTheWeek1 = 1;
        objectDatesWeek2.day2 = 25;
        objectDatesWeek2.month2 = 10;
        objectDatesWeek2.year2 = 2022;
        objectDatesWeek2.dayOfTheWeek2 = 2;
        objectDatesWeek2.day3 = 26;
        objectDatesWeek2.month3 = 10;
        objectDatesWeek2.year3 = 2022;
        objectDatesWeek2.dayOfTheWeek3 = 3;
        objectDatesWeek2.day4 = 27;
        objectDatesWeek2.month4 = 10;
        objectDatesWeek2.year4 = 2022;
        objectDatesWeek2.dayOfTheWeek4 = 4;
        objectDatesWeek2.day5 = 28;
        objectDatesWeek2.month5 = 10;
        objectDatesWeek2.year5 = 2022;
        objectDatesWeek2.dayOfTheWeek5 = 5;
        objectDatesWeek2.theNameOfTheDayOfTheWeek1 = "Pondělí";
        objectDatesWeek2.shortTheNameOfTheDayOfTheWeek1 = "Po";
        objectDatesWeek2.theNameOfTheDayOfTheWeek2 = "Úterý";
        objectDatesWeek2.shortTheNameOfTheDayOfTheWeek2 = "Út";
        objectDatesWeek2.theNameOfTheDayOfTheWeek3 = "Středa";
        objectDatesWeek2.shortTheNameOfTheDayOfTheWeek3 = "St";
        objectDatesWeek2.theNameOfTheDayOfTheWeek4 = "Čtvrtek";
        objectDatesWeek2.shortTheNameOfTheDayOfTheWeek4 = "Čt";
        objectDatesWeek2.theNameOfTheDayOfTheWeek5 = "Pátek";
        objectDatesWeek2.shortTheNameOfTheDayOfTheWeek5 = "Pá";
        objectDatesWeek2.weekTitle= "Aktuální týden";



        ObjectDatesWeek objectDatesWeek3 = new ObjectDatesWeek();
        objectDatesWeek3.day1 = 31;
        objectDatesWeek3.month1 = 10;
        objectDatesWeek3.year1 = 2022;
        objectDatesWeek3.dayOfTheWeek1 = 1;
        objectDatesWeek3.day2 = 1;
        objectDatesWeek3.month2 = 11;
        objectDatesWeek3.year2 = 2022;
        objectDatesWeek3.dayOfTheWeek2 = 2;
        objectDatesWeek3.day3 = 2;
        objectDatesWeek3.month3 = 11;
        objectDatesWeek3.year3 = 2022;
        objectDatesWeek3.dayOfTheWeek3 = 3;
        objectDatesWeek3.day4 = 3;
        objectDatesWeek3.month4 = 11;
        objectDatesWeek3.year4 = 2022;
        objectDatesWeek3.dayOfTheWeek4 = 4;
        objectDatesWeek3.day5 = 4;
        objectDatesWeek3.month5 = 11;
        objectDatesWeek3.year5 = 2022;
        objectDatesWeek3.dayOfTheWeek5 = 5;
        objectDatesWeek3.theNameOfTheDayOfTheWeek1 = "Pondělí";
        objectDatesWeek3.shortTheNameOfTheDayOfTheWeek1 = "Po";
        objectDatesWeek3.theNameOfTheDayOfTheWeek2 = "Úterý";
        objectDatesWeek3.shortTheNameOfTheDayOfTheWeek2 = "Út";
        objectDatesWeek3.theNameOfTheDayOfTheWeek3 = "Středa";
        objectDatesWeek3.shortTheNameOfTheDayOfTheWeek3 = "St";
        objectDatesWeek3.theNameOfTheDayOfTheWeek4 = "Čtvrtek";
        objectDatesWeek3.shortTheNameOfTheDayOfTheWeek4 = "Čt";
        objectDatesWeek3.theNameOfTheDayOfTheWeek5 = "Pátek";
        objectDatesWeek3.shortTheNameOfTheDayOfTheWeek5 = "Pá";
        objectDatesWeek3.weekTitle= "Příští týden";




        textViewPreviousWeekTitle.setText(objectDatesWeek1.weekTitle);
        textViewPreviousWeekDateFromTheNameOfTheDayOfTheWeek.setText(objectDatesWeek1.theNameOfTheDayOfTheWeek5);
        textViewPreviousWeekDateFromDay.setText(objectDatesWeek1.day1 + "");
        textViewPreviousWeekDateFromMonth.setText(objectDatesWeek1.month1 + "");
        textViewPreviousWeekDateFromYear.setText(objectDatesWeek1.year1 + "");
        textViewPreviousWeekDateToTheNameOfTheDayOfTheWeek.setText(objectDatesWeek1.theNameOfTheDayOfTheWeek1);
        textViewPreviousWeekDateToDay.setText(objectDatesWeek1.day5 + "");
        textViewPreviousWeekDateToMonth.setText(objectDatesWeek1.month5 + "");
        textViewPreviousWeekDateToYear.setText(objectDatesWeek1.year5 + "");


        textViewActualWeekTitle.setText(objectDatesWeek2.weekTitle);
        textViewActualWeekDateFromTheNameOfTheDayOfTheWeek.setText(objectDatesWeek2.theNameOfTheDayOfTheWeek5);
        textViewActualWeekDateFromDay.setText(objectDatesWeek2.day1 + "");
        textViewActualWeekDateFromMonth.setText(objectDatesWeek2.month1 + "");
        textViewActualWeekDateFromYear.setText(objectDatesWeek2.year1 + "");
        textViewActualWeekDateToTheNameOfTheDayOfTheWeek.setText(objectDatesWeek2.theNameOfTheDayOfTheWeek1);
        textViewActualWeekDateToDay.setText(objectDatesWeek2.day5 + "");
        textViewActualWeekDateToMonth.setText(objectDatesWeek2.month5 + "");
        textViewActualWeekDateToYear.setText(objectDatesWeek2.year5 + "");



        textViewNextWeekTitle.setText(objectDatesWeek3.weekTitle);
        textViewNextWeekDateFromTheNameOfTheDayOfTheWeek.setText(objectDatesWeek3.theNameOfTheDayOfTheWeek5);
        textViewNextWeekDateFromDay.setText(objectDatesWeek3.day1 + "");
        textViewNextWeekDateFromMonth.setText(objectDatesWeek3.month1 + "");
        textViewNextWeekDateFromYear.setText(objectDatesWeek3.year1 + "");
        textViewNextWeekDateToTheNameOfTheDayOfTheWeek.setText(objectDatesWeek3.theNameOfTheDayOfTheWeek1);
        textViewNextWeekDateToDay.setText(objectDatesWeek3.day5 + "");
        textViewNextWeekDateToMonth.setText(objectDatesWeek3.month5 + "");
        textViewNextWeekDateToYear.setText(objectDatesWeek3.year5 + "");






        for(int i = 0; i<source.size(); i++){

            ObjectDates objectDates = source.get(i);

            if(selectedDay == objectDates.day && selectedMonth == objectDates.month && selectedYear == objectDates.year){

                selectedPosition = i;
                Log.e(TAG, "pozice " + i);

                break;

            }
        }



        linearLayoutPreviousWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutPreviousWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));
                linearLayoutActualWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));
                linearLayoutNextWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));

                linearLayoutPreviousWeek.setBackground(getContext().getResources().getDrawable(R.drawable.selected_date));
                linearLayoutPreviousWeek.getBackground().setColorFilter(Color.parseColor("#42A5F5"), PorterDuff.Mode.SRC_ATOP);


                dayDateListenerWeeks.applyDayDateListenerWeeks(objectDatesWeek1);
            }
        });

        linearLayoutActualWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutPreviousWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));
                linearLayoutActualWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));
                linearLayoutNextWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));

                linearLayoutActualWeek.setBackground(getContext().getResources().getDrawable(R.drawable.selected_date));
                linearLayoutActualWeek.getBackground().setColorFilter(Color.parseColor("#42A5F5"), PorterDuff.Mode.SRC_ATOP);

                dayDateListenerWeeks.applyDayDateListenerWeeks(objectDatesWeek2);
            }
        });

        linearLayoutNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutPreviousWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));
                linearLayoutActualWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));
                linearLayoutNextWeek.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));

                linearLayoutNextWeek.setBackground(getContext().getResources().getDrawable(R.drawable.selected_date));
                linearLayoutNextWeek.getBackground().setColorFilter(Color.parseColor("#42A5F5"), PorterDuff.Mode.SRC_ATOP);

                dayDateListenerWeeks.applyDayDateListenerWeeks(objectDatesWeek3);

            }
        });




    }

    public void AddItemsToRecyclerViewArrayList()
    {
        source = new ArrayList<>();

        ObjectDates objectDates = new ObjectDates(); objectDates.day = 1; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 2; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 3; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 4; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 5; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 6; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 7; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 8; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 9; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 10; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 11; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 12; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 13; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 14; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 15; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 16; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 17; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 18; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 19; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 20; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 21; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 22; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 23; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 24; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 25; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 26; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 27; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 28; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 29; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 30; objectDates.month = 9; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 1; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 2; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 3; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 4; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 5; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 6; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 7; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 8; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 9; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 10; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 11; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 12; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 13; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 14; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 15; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 16; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 17; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 18; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 19; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 20; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 21; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 22; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 23; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 24; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 25; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 2; objectDates.theNameOfTheDayOfTheWeek = "Úterý"; objectDates.shortTheNameOfTheDayOfTheWeek = "Út"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 26; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 3; objectDates.theNameOfTheDayOfTheWeek = "Středa"; objectDates.shortTheNameOfTheDayOfTheWeek = "St"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 27; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 4; objectDates.theNameOfTheDayOfTheWeek = "Čtvrtek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Čt"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 28; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 5; objectDates.theNameOfTheDayOfTheWeek = "Pátek"; objectDates.shortTheNameOfTheDayOfTheWeek = "Pá"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 29; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 6; objectDates.theNameOfTheDayOfTheWeek = "Sobota"; objectDates.shortTheNameOfTheDayOfTheWeek = "So"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 30; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 7; objectDates.theNameOfTheDayOfTheWeek = "Neděle"; objectDates.shortTheNameOfTheDayOfTheWeek = "Ne"; source.add(objectDates);
        objectDates = new ObjectDates(); objectDates.day = 31; objectDates.month = 10; objectDates.year = 2022; objectDates.dayOfTheWeek = 1; objectDates.theNameOfTheDayOfTheWeek = "Pondělí"; objectDates.shortTheNameOfTheDayOfTheWeek = "Po"; source.add(objectDates);

        source.add(objectDates);
    }









//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }





}

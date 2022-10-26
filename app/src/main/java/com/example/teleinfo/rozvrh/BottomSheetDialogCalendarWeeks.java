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
        void applyDayDateListener(int day, int month, int year);
    }








    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.test_activity_test_recycler_view_horizontal_weeks, null);
        dialog.setContentView(contentView);


        AddItemsToRecyclerViewArrayList();

        for(int i = 0; i<source.size(); i++){

            ObjectDates objectDates = source.get(i);

            if(selectedDay == objectDates.day && selectedMonth == objectDates.month && selectedYear == objectDates.year){

                selectedPosition = i;
                Log.e(TAG, "pozice " + i);

                break;

            }
        }




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

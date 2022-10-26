package com.example.teleinfo.rozvrh;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_HOUR;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.login.BottomSheetDialogCredentialsLogin;
import com.example.teleinfo.login.BottomSheetDialogQRCodeReading;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogCalendar extends BottomSheetDialogFragment {

    RecyclerView recyclerView;
    ArrayList<ObjectDates> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    Adapter adapter;
    LinearLayoutManager HorizontalLayout;

    private DayDateListener dayDateListener;


    View ChildView;
    int RecyclerViewItemPosition;

    int selectedDay;
    int selectedMonth;
    int selectedYear;

    int selectedPosition;

    public BottomSheetDialogCalendar(int selectedDay, int selectedMonth, int selectedYear) {

        this.selectedDay = selectedDay;
        this.selectedMonth = selectedMonth;
        this.selectedYear = selectedYear;

    }


    @Override
    public void onAttach(Context context) {

        Log.e(TAG, "onAttach: ClassCastException aaaa: " + getChildFragmentManager() + "   -   " +  getParentFragmentManager() + "   -   " + getParentFragment() + "   -   " );

        if(getChildFragmentManager() == null){

            try{
                Log.e(TAG, "onAttach: ClassCastException aaaa: "  );

                dayDateListener = (DayDateListener) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException 11: " + e.getMessage() );
            }
        }else{
            try{
                Log.e(TAG, "onAttach: ClassCastException oooo: "  );

                dayDateListener = (DayDateListener) context;
                Log.e(TAG, "onAttach: ClassCastException oooo1: " );
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException 22: " + e.getMessage() );
            }
        }
        super.onAttach(context);
    }









    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.test_activity_test_recycler_view_horizontal, null);
        dialog.setContentView(contentView);

        recyclerView = (RecyclerView)contentView.findViewById(R.id.recyclerview);
        RecyclerViewLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        AddItemsToRecyclerViewArrayList();

        for(int i = 0; i<source.size(); i++){

            ObjectDates objectDates = source.get(i);

            if(selectedDay == objectDates.day && selectedMonth == objectDates.month && selectedYear == objectDates.year){

                selectedPosition = i;
                Log.e(TAG, "pozice " + i);

                break;

            }
        }


        adapter = new Adapter(source);

        HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.scrollToPosition(selectedPosition-2);

        recyclerView.setAdapter(adapter);

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

    public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

        private List<ObjectDates> list;

        public Adapter(List<ObjectDates> horizontalList) {

            this.list = horizontalList;
        }

        @Override
        public Adapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_activity_test_recycler_view_horizontal_item, parent, false);

            return new Adapter.MyView(itemView);
        }

        @Override
        public void onBindViewHolder(final MyView holder, int position) {

            ObjectDates objectDates = list.get(position);

            // Set the text of each item of
            // Recycler view with the list items
            holder.textviewlll.setText(objectDates.theNameOfTheDayOfTheWeek);
            holder.textView.setText(objectDates.day + "");
            holder.textviehwaa.setText(objectDates.month + "");
            holder.textviewaa.setText(objectDates.year + "");

            if(objectDates.dayOfTheWeek == 6 || objectDates.dayOfTheWeek == 7){

                holder.textviewlll.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                holder.textView.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                holder.textviehwaa.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                holder.textviewaa.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

            }else{

                holder.textviewlll.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                holder.textView.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                holder.textviehwaa.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                holder.textviewaa.setTextColor(getContext().getResources().getColor(R.color.text_primary));

                if(position == selectedPosition){

                    holder.date1.setBackground(getContext().getResources().getDrawable(R.drawable.selected_date));
                    holder.date1.getBackground().setColorFilter(Color.parseColor("#42A5F5"), PorterDuff.Mode.SRC_ATOP);

                }else{

                    holder.date1.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));

                }
            }


            if(position == selectedPosition){

                holder.date1.setBackground(getContext().getResources().getDrawable(R.drawable.selected_date));

                holder.date1.getBackground().setColorFilter(Color.parseColor("#42A5F5"), PorterDuff.Mode.SRC_ATOP);
                holder.textviewlll.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                holder.textView.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                holder.textviehwaa.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                holder.textviewaa.setTextColor(getContext().getResources().getColor(R.color.text_primary));

            }else{

                holder.date1.setBackground(getContext().getResources().getDrawable(R.drawable.unselected_date));

                if(objectDates.dayOfTheWeek == 6 || objectDates.dayOfTheWeek == 7){

                    holder.textviewlll.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                    holder.textView.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                    holder.textviehwaa.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
                    holder.textviewaa.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

                }

            }



            if(objectDates.day == 26){

                holder.weekTitle.setVisibility(View.VISIBLE);

            }else{

                holder.weekTitle.setVisibility(View.INVISIBLE);

            }

            holder.date2.setVisibility(View.GONE);
            holder.dateSeparator.setVisibility(View.GONE);
            //holder.weekTitle.setVisibility(View.GONE);


            holder.date1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedPosition = position;
                    notifyDataSetChanged();


                    dayDateListener.applyDayDateListener(objectDates.day, objectDates.month, objectDates.year);

                }
            });


        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }

        public class MyView extends RecyclerView.ViewHolder {

            // Text View
            TextView textView;
            TextView textviewlll;
            TextView textviehwaa;
            TextView textviewaa;

            LinearLayout date1;
            LinearLayout date2;
            LinearLayout dateSeparator;
            LinearLayout weekTitle;



            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            public MyView(View view){
                super(view);

                // initialise TextView with id
                textView = (TextView)view.findViewById(R.id.textview);
                textviewlll = (TextView)view.findViewById(R.id.textviewlll);
                textviehwaa = (TextView)view.findViewById(R.id.textviehwaa);
                textviewaa = (TextView)view.findViewById(R.id.textviewaa);

                date1 = (LinearLayout)view.findViewById(R.id.date1);
                date2 = (LinearLayout)view.findViewById(R.id.date2);
                dateSeparator = (LinearLayout)view.findViewById(R.id.dateSeparator);
                weekTitle = (LinearLayout)view.findViewById(R.id.weekTitle);



            }
        }
    }








//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }




    public interface DayDateListener {
        void applyDayDateListener(int day, int month, int year);
    }

}

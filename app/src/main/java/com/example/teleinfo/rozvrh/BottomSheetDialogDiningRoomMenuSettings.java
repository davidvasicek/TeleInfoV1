package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.BREAKFAST_SHOW;
import static com.example.teleinfo.parameters.MainParameters.BRUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.COLOR_COLUMN_HEADER;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_BREAK;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SECOND_SHOW;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SHOW;
import static com.example.teleinfo.parameters.MainParameters.FINGERPRINT_HARDWARE_IS_DETECTED;
import static com.example.teleinfo.parameters.MainParameters.LUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.MY_ALERGENS;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SNACK_SHOW;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialogDiningRoomMenuSettings extends BottomSheetDialogFragment {

    CheckBox Breakfast;
    CheckBox Brunch;
    CheckBox Lunch;
    CheckBox Snack;
    CheckBox Dinner;
    CheckBox DinnerSecond;


    CheckBox checkBox_Breakfast;
    CheckBox checkBox_Brunch;
    CheckBox checkBox_Lunch;
    CheckBox checkBox_Snack;
    CheckBox checkBox_Dinner;
    CheckBox checkBox_DinnerSecond;
    CheckBox checkBox_Alergens01a;
    CheckBox checkBox_Alergens01b;
    CheckBox checkBox_Alergens01c;
    CheckBox checkBox_Alergens01d;
    CheckBox checkBox_Alergens01e;
    CheckBox checkBox_Alergens01f;
    CheckBox checkBox_Alergens01g;
    CheckBox checkBox_Alergens02;
    CheckBox checkBox_Alergens03;
    CheckBox checkBox_Alergens04;
    CheckBox checkBox_Alergens05;
    CheckBox checkBox_Alergens06;
    CheckBox checkBox_Alergens07;
    CheckBox checkBox_Alergens08a;
    CheckBox checkBox_Alergens08b;
    CheckBox checkBox_Alergens08c;
    CheckBox checkBox_Alergens08d;
    CheckBox checkBox_Alergens08e;
    CheckBox checkBox_Alergens08f;
    CheckBox checkBox_Alergens08g;
    CheckBox checkBox_Alergens08h;
    CheckBox checkBox_Alergens09;
    CheckBox checkBox_Alergens10;
    CheckBox checkBox_Alergens11;
    CheckBox checkBox_Alergens12;
    CheckBox checkBox_Alergens13;
    CheckBox checkBox_Alergens14;



    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    public BottomSheetDialogDiningRoomMenuSettings() {

    }
    
   
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.rozvrh_bottom_sheet_dialog_dinning_room_menu_settings, null);
        dialog.setContentView(contentView);

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();




        checkBox_Breakfast = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Breakfast );
        checkBox_Brunch = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Brunch );
        checkBox_Lunch = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Lunch );
        checkBox_Snack = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Snack );
        checkBox_Dinner = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Dinner );
        checkBox_DinnerSecond = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_DinnerSecond);
        checkBox_Alergens01a = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01a);
        checkBox_Alergens01b = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01b);
        checkBox_Alergens01c = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01c);
        checkBox_Alergens01d = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01d);
        checkBox_Alergens01e = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01e);
        checkBox_Alergens01f = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01f);
        checkBox_Alergens01g = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens01g);
        checkBox_Alergens02 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens02);
        checkBox_Alergens03 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens03);
        checkBox_Alergens04 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens04);
        checkBox_Alergens05 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens05);
        checkBox_Alergens06 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens06);
        checkBox_Alergens07 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens07);
        checkBox_Alergens08a = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08a);
        checkBox_Alergens08b = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08b);
        checkBox_Alergens08c = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08c);
        checkBox_Alergens08d = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08d);
        checkBox_Alergens08e = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08e);
        checkBox_Alergens08f = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08f);
        checkBox_Alergens08g = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08g);
        checkBox_Alergens08h = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens08h);
        checkBox_Alergens09 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens09);
        checkBox_Alergens10 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens10);
        checkBox_Alergens11 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens11);
        checkBox_Alergens12 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens12);
        checkBox_Alergens13 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens13);
        checkBox_Alergens14 = (CheckBox)contentView.findViewById(R.id.aaaaaaaaaaaaaaCheckBox_Alergens14);


        initAlergensCheckBoxes();




        checkBox_Breakfast.setChecked(mSharedPreferences.getBoolean(BREAKFAST_SHOW, false));
        checkBox_Breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(BREAKFAST_SHOW, checkBox_Breakfast.isChecked());
                mEditor.commit();

            }
        });

        checkBox_Brunch.setChecked(mSharedPreferences.getBoolean(BRUNCH_SHOW, false));
        checkBox_Brunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(BRUNCH_SHOW, checkBox_Brunch.isChecked());
                mEditor.commit();

            }
        });

        checkBox_Lunch.setChecked(mSharedPreferences.getBoolean(LUNCH_SHOW, true));
        checkBox_Lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(LUNCH_SHOW, checkBox_Lunch.isChecked());
                mEditor.commit();

            }
        });

        checkBox_Snack.setChecked(mSharedPreferences.getBoolean(SNACK_SHOW, false));
        checkBox_Snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(SNACK_SHOW, checkBox_Snack.isChecked());
                mEditor.commit();

            }
        });

        checkBox_Dinner.setChecked(mSharedPreferences.getBoolean(DINNER_SHOW, false));
        checkBox_Dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(DINNER_SHOW, checkBox_Dinner.isChecked());
                mEditor.commit();

            }
        });

        checkBox_DinnerSecond.setChecked(mSharedPreferences.getBoolean(DINNER_SECOND_SHOW, false));
        checkBox_DinnerSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(DINNER_SECOND_SHOW, checkBox_DinnerSecond.isChecked());
                mEditor.commit();

            }
        });






        checkBox_Alergens01a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens01b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens01c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens01d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens01e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens01f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens01g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens08h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });

        checkBox_Alergens14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlergensString();

            }
        });






/*




checkBox_Alergens01a
checkBox_Alergens01b
checkBox_Alergens01c
checkBox_Alergens01d
checkBox_Alergens01e
checkBox_Alergens01f
checkBox_Alergens01g
checkBox_Alergens02
checkBox_Alergens03
checkBox_Alergens04
checkBox_Alergens05
checkBox_Alergens06
checkBox_Alergens07
checkBox_Alergens08a
checkBox_Alergens08b
checkBox_Alergens08c
checkBox_Alergens08d
checkBox_Alergens08e
checkBox_Alergens08f
checkBox_Alergens08g
checkBox_Alergens08h
checkBox_Alergens09
checkBox_Alergens10
checkBox_Alergens11
checkBox_Alergens12
checkBox_Alergens13
checkBox_Alergens14








        checkBox_Alergens01a



                checkBox_Alergens01b
        checkBox_Alergens01c
                checkBox_Alergens01d
        checkBox_Alergens01e
                checkBox_Alergens01f
        checkBox_Alergens01g
                checkBox_Alergens02
        checkBox_Alergens03
                checkBox_Alergens04
        checkBox_Alergens05
                checkBox_Alergens06
        checkBox_Alergens07
                checkBox_Alergens08a
        checkBox_Alergens08b
                checkBox_Alergens08c
        checkBox_Alergens08d
                checkBox_Alergens08e
        checkBox_Alergens08f
                checkBox_Alergens08g
        checkBox_Alergens08h
                checkBox_Alergens09
        checkBox_Alergens10
                checkBox_Alergens11
        checkBox_Alergens12
                checkBox_Alergens13
        checkBox_Alergens14

*/
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }



    public void createAlergensString(){

        String alergensString = "";


        if(checkBox_Alergens01a.isChecked()){

            alergensString += "01a_";
        }

        if(checkBox_Alergens01b.isChecked()){

            alergensString += "01b_";
        }

        if(checkBox_Alergens01c.isChecked()){

            alergensString += "01c_";
        }

        if(checkBox_Alergens01d.isChecked()){

            alergensString += "01d_";
        }

        if(checkBox_Alergens01e.isChecked()){

            alergensString += "01e_";
        }

        if(checkBox_Alergens01f.isChecked()){

            alergensString += "01f_";
        }

        if(checkBox_Alergens01g.isChecked()){

            alergensString += "01g_";
        }

        if(checkBox_Alergens02.isChecked()){

            alergensString += "02_";
        }

        if(checkBox_Alergens03.isChecked()){

            alergensString += "03_";
        }

        if(checkBox_Alergens04.isChecked()){

            alergensString += "04_";
        }

        if(checkBox_Alergens05.isChecked()){

            alergensString += "05_";
        }

        if(checkBox_Alergens06.isChecked()){

            alergensString += "06_";
        }

        if(checkBox_Alergens07.isChecked()){

            alergensString += "07_";
        }

        if(checkBox_Alergens08a.isChecked()){

            alergensString += "08a_";
        }

        if(checkBox_Alergens08b.isChecked()){

            alergensString += "08b_";
        }

        if(checkBox_Alergens08c.isChecked()){

            alergensString += "08c_";
        }

        if(checkBox_Alergens08d.isChecked()){

            alergensString += "08d_";
        }

        if(checkBox_Alergens08e.isChecked()){

            alergensString += "08e_";
        }

        if(checkBox_Alergens08f.isChecked()){

            alergensString += "08f_";
        }

        if(checkBox_Alergens08g.isChecked()){

            alergensString += "08g_";
        }

        if(checkBox_Alergens08h.isChecked()){

            alergensString += "08h_";
        }

        if(checkBox_Alergens09.isChecked()){

            alergensString += "09_";
        }

        if(checkBox_Alergens10.isChecked()){

            alergensString += "10_";
        }

        if(checkBox_Alergens11.isChecked()){

            alergensString += "11_";
        }

        if(checkBox_Alergens12.isChecked()){

            alergensString += "12_";
        }

        if(checkBox_Alergens13.isChecked()){

            alergensString += "13_";
        }

        if(checkBox_Alergens14.isChecked()){

            alergensString += "14_";
        }

        mEditor.putString(MY_ALERGENS, alergensString);
        mEditor.commit();

    }


    public void initAlergensCheckBoxes(){

        String[] alergensStringSplit = mSharedPreferences.getString(MY_ALERGENS, "").split("_");

        Log.e("mylog", "bbbbbbbb " + mSharedPreferences.getString(MY_ALERGENS, ""));
        Log.e("mylog", "bbbbbbbb " + alergensStringSplit);
        Log.e("mylog", "bbbbbbbb " + alergensStringSplit.length);



        for(int i = 0;  i < alergensStringSplit.length; i++){

            Log.e("mylog", "bbbbbbbb " + alergensStringSplit[i]);

           if(alergensStringSplit[i].compareTo("01a") == 0){
                checkBox_Alergens01a.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("01b") == 0){
                checkBox_Alergens01b.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("01c") == 0){
                checkBox_Alergens01c.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("01d") == 0){
                checkBox_Alergens01d.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("01e") == 0){
                checkBox_Alergens01e.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("01f") == 0){
                checkBox_Alergens01f.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("01g") == 0){
                checkBox_Alergens01g.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("02") == 0){
                checkBox_Alergens02.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("03") == 0){
                checkBox_Alergens03.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("04") == 0){
                checkBox_Alergens04.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("05") == 0){
                checkBox_Alergens05.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("06") == 0){
                checkBox_Alergens06.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("07") == 0){
                checkBox_Alergens07.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08a") == 0){
                checkBox_Alergens08a.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08b") == 0){
                checkBox_Alergens08b.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08c") == 0){
                checkBox_Alergens08c.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08d") == 0){
                checkBox_Alergens08d.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08e") == 0){
                checkBox_Alergens08e.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08f") == 0){
                checkBox_Alergens08f.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08g") == 0){
                checkBox_Alergens08g.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("08h") == 0){
                checkBox_Alergens08h.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("09") == 0){
                checkBox_Alergens09.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("10") == 0){
                checkBox_Alergens10.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("11") == 0){
                checkBox_Alergens11.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("12") == 0){
                checkBox_Alergens12.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("13") == 0){
                checkBox_Alergens13.setChecked(true);
            }

            if(alergensStringSplit[i].compareTo("14") == 0){
                checkBox_Alergens14.setChecked(true);
            }/**/

        }


    }

}

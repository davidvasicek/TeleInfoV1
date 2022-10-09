package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.BREAKFAST_SHOW;
import static com.example.teleinfo.parameters.MainParameters.BRUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.COLOR_COLUMN_HEADER;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_BREAK;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SECOND_SHOW;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SHOW;
import static com.example.teleinfo.parameters.MainParameters.LUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SNACK_SHOW;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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





        Breakfast = (CheckBox)contentView.findViewById(R.id.checkbox_Breakfast);
        Brunch = (CheckBox)contentView.findViewById(R.id.checkbox_Brunch);
        Lunch = (CheckBox)contentView.findViewById(R.id.checkbox_Lunch);
        Snack = (CheckBox)contentView.findViewById(R.id.checkbox_Snack);
        Dinner = (CheckBox)contentView.findViewById(R.id.checkbox_Dinner);
        DinnerSecond = (CheckBox)contentView.findViewById(R.id.checkbox_DinnerSecond);

        Breakfast.setChecked(mSharedPreferences.getBoolean(BREAKFAST_SHOW, false));
        Breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(BREAKFAST_SHOW, Breakfast.isChecked());
                mEditor.commit();

            }
        });

        Brunch.setChecked(mSharedPreferences.getBoolean(BRUNCH_SHOW, false));
        Brunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(BRUNCH_SHOW, Brunch.isChecked());
                mEditor.commit();

            }
        });

        Lunch.setChecked(mSharedPreferences.getBoolean(LUNCH_SHOW, true));
        Lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(LUNCH_SHOW, Lunch.isChecked());
                mEditor.commit();

            }
        });

        Snack.setChecked(mSharedPreferences.getBoolean(SNACK_SHOW, false));
        Snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(SNACK_SHOW, Snack.isChecked());
                mEditor.commit();

            }
        });

        Dinner.setChecked(mSharedPreferences.getBoolean(DINNER_SHOW, false));
        Dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(DINNER_SHOW, Dinner.isChecked());
                mEditor.commit();

            }
        });

        DinnerSecond.setChecked(mSharedPreferences.getBoolean(DINNER_SECOND_SHOW, false));
        DinnerSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(DINNER_SECOND_SHOW, DinnerSecond.isChecked());
                mEditor.commit();

            }
        });






    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }


}

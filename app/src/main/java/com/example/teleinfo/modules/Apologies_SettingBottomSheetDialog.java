package com.example.teleinfo.modules;

import static com.example.teleinfo.parameters.MainParameters.COLOR_CLASSIC_HOUR;
import static com.example.teleinfo.parameters.MainParameters.COLOR_CLASSIC_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_CLASS_LESSON;
import static com.example.teleinfo.parameters.MainParameters.COLOR_COLUMN_HEADER;
import static com.example.teleinfo.parameters.MainParameters.COLOR_CONSULTATION_HOUR;
import static com.example.teleinfo.parameters.MainParameters.COLOR_DINNINGROOM_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_LUNCH;
import static com.example.teleinfo.parameters.MainParameters.COLOR_MIDDAY_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_MORNING_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_PORTER_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_PPP_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_BREAK;
import static com.example.teleinfo.parameters.MainParameters.COLOR_ROW_HEADER_HOUR;
import static com.example.teleinfo.parameters.MainParameters.COLOR_SCHOOL_ACTIONS;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.HIDE_LEARNED_DAYS;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SHOW_BREAKS;
import static com.example.teleinfo.parameters.MainParameters.SHOW_CANCELED_CLASSED;
import static com.example.teleinfo.parameters.MainParameters.SHOW_CONSULTATION_HOUR;
import static com.example.teleinfo.parameters.MainParameters.SHOW_CURRENT_DAY_HIGHLIGHTED;
import static com.example.teleinfo.parameters.MainParameters.SHOW_EVEN_AND_ODD_COLUMNS;
import static com.example.teleinfo.parameters.MainParameters.SHOW_LUNCH_IN_SCHEDULE;
import static com.example.teleinfo.parameters.MainParameters.SHOW_TIME_LINE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Switch;

import androidx.cardview.widget.CardView;

import com.example.teleinfo.R;
import com.example.teleinfo.dialogs.ColorSelectBottomSheetDialog;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.example.teleinfo.rozvrh.TimetableMenuBottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class Apologies_SettingBottomSheetDialog extends BottomSheetDialogFragment{

    String colorPressedButton;

    CardView card_viewColumnHeader;
    CardView card_viewRowHeaderHour;
    CardView card_viewRowHeaderBreak;
    CardView card_viewClassicHour;
    CardView card_viewClassicSupervision;
    CardView card_viewMorningSupervision;
    CardView card_viewPorterSupervision;
    CardView card_viewPPPSupervision;
    CardView card_viewDinningRoomSupervision;
    CardView card_viewMiddaySupervision;
    CardView card_viewClassLesson;
    CardView card_viewConsultationHour;
    CardView card_viewSchoolActions;
    CardView card_viewLunch;

    Switch switchShowBreaks;
    Switch switchShowTimeLine;
    Switch switchShowEvenAndOddColumns;
    Switch switchShowCanceledClassed;
    Switch switchHideLearnedDays;
    Switch switchShowConsultationHour;
    Switch switchShowCurrentDayHighlighted;
    Switch switchShowLunch;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public Apologies_SettingBottomSheetDialog() {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.apologies_bottom_sheet_dialog_settings, null);
        dialog.setContentView(contentView);


        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();



    }


//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }


}

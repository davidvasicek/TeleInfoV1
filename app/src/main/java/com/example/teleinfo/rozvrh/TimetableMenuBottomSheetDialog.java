package com.example.teleinfo.rozvrh;

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
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.example.teleinfo.R;
import com.example.teleinfo.dialogs.ColorSelectBottomSheetDialog;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class TimetableMenuBottomSheetDialog extends BottomSheetDialogFragment implements ColorSelectBottomSheetDialog.ColorSelectListener{

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

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public TimetableMenuBottomSheetDialog() {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.rozvrh_dialog_bottom_sheet_dialog_timetable_menu, null);
        dialog.setContentView(contentView);

/*
        Date dt1 = new Date(System.currentTimeMillis()-3*24*60*60*1000);
        Date dt2 = new Date(System.currentTimeMillis()+3*24*60*60*1000);
        Date dt3 = new Date(System.currentTimeMillis());

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dt1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(dt2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(dt3);

        //https://github.com/Mulham-Raee/Horizontal-Calendar

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(contentView, R.id.calendarView)
                .range(calendar1, calendar2)
                .datesNumberOnScreen(5)   // Number of Dates cells shown on screen (default to 5).
                .configure()    // starts configuration.
               // .formatTopText("MMM")       // default to "MMM".
               // .formatMiddleText("dd")    // default to "dd".
             //   .formatBottomText("EEE")    // default to "EEE".

                .showTopText(true)              // show or hide TopText (default to true).
                    .showBottomText(true)           // show or hide BottomText (default to true).
                    .textColor(Color.LTGRAY, Color.LTGRAY)    // default to (Color.LTGRAY, Color.WHITE).
                    //.selectedDateBackground(Color.parseColor("#FF0000"))      // set selected date cell background.
                .selectorColor(Color.parseColor("#00FF00"))               // set selection indicator bar's color (default to colorAccent).
                .end()          // ends configuration.
                .defaultSelectedDate(calendar3)    // Date to be selected at start (default to current day `Calendar.getInstance()`).
                .build();



        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });


*/
        DatePickerTimeline datePickerTimeline = contentView.findViewById(R.id.datePickerTimeline);

        // Set a Start date (Default, 1 Jan 1970)
        datePickerTimeline.setInitialDate(2022, 8, 21);
        //datePickerTimeline.
        //datePickerTimeline.set

        //datePickerTimeline.setDat(2022, 10, 16);

        // Set a date Selected Listener
        datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                //Do Something
            }
        });

        card_viewColumnHeader = (CardView)contentView.findViewById(R.id.card_viewColumnHeader);
        card_viewRowHeaderHour = (CardView)contentView.findViewById(R.id.card_viewRowHeaderHour);
        card_viewRowHeaderBreak = (CardView)contentView.findViewById(R.id.card_viewRowHeaderBreak);
        card_viewClassicHour = (CardView)contentView.findViewById(R.id.card_viewClassicHour);
        card_viewClassicSupervision = (CardView)contentView.findViewById(R.id.card_viewClassicSupervision);
        card_viewMorningSupervision = (CardView)contentView.findViewById(R.id.card_viewMorningSupervision);
        card_viewPorterSupervision = (CardView)contentView.findViewById(R.id.card_viewPorterSupervision);
        card_viewPPPSupervision = (CardView)contentView.findViewById(R.id.card_viewPPPSupervision);
        card_viewDinningRoomSupervision = (CardView)contentView.findViewById(R.id.card_viewDinningRoomSupervision);
        card_viewMiddaySupervision = (CardView)contentView.findViewById(R.id.card_viewMiddaySupervision);
        card_viewClassLesson = (CardView)contentView.findViewById(R.id.card_viewClassLesson);
        card_viewConsultationHour = (CardView)contentView.findViewById(R.id.card_viewConsultationHour);
        card_viewSchoolActions = (CardView)contentView.findViewById(R.id.card_viewSchoolActions);
        card_viewLunch = (CardView)contentView.findViewById(R.id.card_viewLunch);

        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        List<String> colors = new ArrayList<>();

        colors.add("#ffebee");
        colors.add("#ffcdd2");
        colors.add("#ef9a9a");
        colors.add("#e57373");
        colors.add("#ef5350");
        colors.add("#f44336");
        colors.add("#e53935");
        colors.add("#d32f2f");
        colors.add("#c62828");
        colors.add("#b71c1c");
        colors.add("#fce4ec");
        colors.add("#f8bbd0");
        colors.add("#f48fb1");
        colors.add("#f06292");
        colors.add("#ec407a");
        colors.add("#e91e63");
        colors.add("#d81b60");
        colors.add("#c2185b");
        colors.add("#ad1457");
        colors.add("#880e4f");
        colors.add("#f3e5f5");
        colors.add("#e1bee7");
        colors.add("#ce93d8");
        colors.add("#ba68c8");
        colors.add("#ab47bc");
        colors.add("#9c27b0");
        colors.add("#8e24aa");
        colors.add("#7b1fa2");
        colors.add("#6a1b9a");
        colors.add("#4a148c");
        colors.add("#ede7f6");
        colors.add("#d1c4e9");
        colors.add("#b39ddb");
        colors.add("#9575cd");
        colors.add("#7e57c2");
        colors.add("#673ab7");
        colors.add("#5e35b1");
        colors.add("#512da8");
        colors.add("#4527a0");
        colors.add("#311b92");
        colors.add("#e8eaf6");
        colors.add("#c5cae9");
        colors.add("#9fa8da");
        colors.add("#7986cb");
        colors.add("#5c6bc0");
        colors.add("#3f51b5");
        colors.add("#3949ab");
        colors.add("#303f9f");
        colors.add("#283593");
        colors.add("#1a237e");
        colors.add("#e3f2fd");
        colors.add("#bbdefb");
        colors.add("#90caf9");
        colors.add("#64b5f6");
        colors.add("#42a5f5");
        colors.add("#2196f3");
        colors.add("#1e88e5");
        colors.add("#1976d2");
        colors.add("#1565c0");
        colors.add("#0d47a1");
        colors.add("#e1f5fe");
        colors.add("#b3e5fc");
        colors.add("#81d4fa");
        colors.add("#4fc3f7");
        colors.add("#29b6f6");
        colors.add("#03a9f4");
        colors.add("#039be5");
        colors.add("#0288d1");
        colors.add("#0277bd");
        colors.add("#01579b");
        colors.add("#e0f7fa");
        colors.add("#b2ebf2");
        colors.add("#80deea");
        colors.add("#4dd0e1");
        colors.add("#26c6da");
        colors.add("#00bcd4");
        colors.add("#00acc1");
        colors.add("#0097a7");
        colors.add("#00838f");
        colors.add("#006064");
        colors.add("#e0f2f1");
        colors.add("#b2dfdb");
        colors.add("#80cbc4");
        colors.add("#4db6ac");
        colors.add("#26a69a");
        colors.add("#009688");
        colors.add("#00897b");
        colors.add("#00796b");
        colors.add("#00695c");
        colors.add("#004d40");
        colors.add("#e8f5e9");
        colors.add("#c8e6c9");
        colors.add("#a5d6a7");
        colors.add("#81c784");
        colors.add("#66bb6a");
        colors.add("#4caf50");
        colors.add("#43a047");
        colors.add("#388e3c");
        colors.add("#2e7d32");
        colors.add("#1b5e20");
        colors.add("#f1f8e9");
        colors.add("#dcedc8");
        colors.add("#c5e1a5");
        colors.add("#aed581");
        colors.add("#9ccc65");
        colors.add("#8bc34a");
        colors.add("#7cb342");
        colors.add("#689f38");
        colors.add("#558b2f");
        colors.add("#33691e");
        colors.add("#f9fbe7");
        colors.add("#f0f4c3");
        colors.add("#e6ee9c");
        colors.add("#dce775");
        colors.add("#d4e157");
        colors.add("#cddc39");
        colors.add("#c0ca33");
        colors.add("#afb42b");
        colors.add("#9e9d24");
        colors.add("#827717");
        colors.add("#fffde7");
        colors.add("#fff9c4");
        colors.add("#fff59d");
        colors.add("#fff176");
        colors.add("#ffee58");
        colors.add("#ffeb3b");
        colors.add("#fdd835");
        colors.add("#fbc02d");
        colors.add("#f9a825");
        colors.add("#f57f17");
        colors.add("#fff8e1");
        colors.add("#ffecb3");
        colors.add("#ffe082");
        colors.add("#ffd54f");
        colors.add("#ffca28");
        colors.add("#ffc107");
        colors.add("#ffb300");
        colors.add("#ffa000");
        colors.add("#ff8f00");
        colors.add("#ff6f00");
        colors.add("#fff3e0");
        colors.add("#ffe0b2");
        colors.add("#ffcc80");
        colors.add("#ffb74d");
        colors.add("#ffa726");
        colors.add("#ff9800");
        colors.add("#fb8c00");
        colors.add("#f57c00");
        colors.add("#ef6c00");
        colors.add("#e65100");
        colors.add("#fbe9e7");
        colors.add("#ffccbc");
        colors.add("#ffab91");
        colors.add("#ff8a65");
        colors.add("#ff7043");
        colors.add("#ff5722");
        colors.add("#f4511e");
        colors.add("#e64a19");
        colors.add("#d84315");
        colors.add("#bf360c");
        colors.add("#efebe9");
        colors.add("#d7ccc8");
        colors.add("#bcaaa4");
        colors.add("#a1887f");
        colors.add("#8d6e63");
        colors.add("#795548");
        colors.add("#6d4c41");
        colors.add("#5d4037");
        colors.add("#4e342e");
        colors.add("#3e2723");
        colors.add("#fafafa");
        colors.add("#f5f5f5");
        colors.add("#eeeeee");
        colors.add("#e0e0e0");
        colors.add("#bdbdbd");
        colors.add("#9e9e9e");
        colors.add("#757575");
        colors.add("#616161");
        colors.add("#424242");
        colors.add("#212121");
        colors.add("#eceff1");
        colors.add("#cfd8dc");
        colors.add("#b0bec5");
        colors.add("#90a4ae");
        colors.add("#78909c");
        colors.add("#607d8b");
        colors.add("#546e7a");
        colors.add("#455a64");
        colors.add("#37474f");
        colors.add("#263238");

        card_viewColumnHeader.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_COLUMN_HEADER, "#B0BEC5")));
        card_viewColumnHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorColumnHeader";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_COLUMN_HEADER, "#B0BEC5"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewRowHeaderHour.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_ROW_HEADER_HOUR, "#78909C")));
        card_viewRowHeaderHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorRowHeaderHour";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_ROW_HEADER_HOUR, "#78909C"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewRowHeaderBreak.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_ROW_HEADER_BREAK, "#90A4AE")));
        card_viewRowHeaderBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorRowHeaderBreak";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_ROW_HEADER_BREAK, "#90A4AE"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewClassicHour.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_CLASSIC_HOUR, "#EEEEEE")));
        card_viewClassicHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorClassicHour";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_CLASSIC_HOUR, "#EEEEEE"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewClassicSupervision.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_CLASSIC_SUPERVISION, "#BCAAA4")));
        card_viewClassicSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorClassicSupervision";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_CLASSIC_SUPERVISION, "#BCAAA4"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewMorningSupervision.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_MORNING_SUPERVISION, "#C5E1A5")));
        card_viewMorningSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorMorningSupervision";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_MORNING_SUPERVISION, "#C5E1A5"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewPorterSupervision.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_PORTER_SUPERVISION, "#B2DFDB")));
        card_viewPorterSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorPorterSupervision";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_PORTER_SUPERVISION, "#B2DFDB"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewPPPSupervision.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_PPP_SUPERVISION, "#B2DFDB")));
        card_viewPPPSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorPPPSupervision";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_PPP_SUPERVISION, "#B2DFDB"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewDinningRoomSupervision.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));
        card_viewDinningRoomSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorDinningRoomSupervision";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewMiddaySupervision.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_MIDDAY_SUPERVISION, "#9FA8DA")));
        card_viewMiddaySupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorMiddaySupervision";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_MIDDAY_SUPERVISION, "#9FA8DA"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewClassLesson.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_CLASS_LESSON, "#CE93D8")));
        card_viewClassLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorClassLesson";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_CLASS_LESSON, "#CE93D8"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewConsultationHour.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_CONSULTATION_HOUR, "#F8BBD0")));
        card_viewConsultationHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorConsultationHour";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_CONSULTATION_HOUR, "#F8BBD0"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewSchoolActions.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_SCHOOL_ACTIONS, "#A5D6A7")));
        card_viewSchoolActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorSchoolActions";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_SCHOOL_ACTIONS, "#A5D6A7"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });

        card_viewLunch.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(COLOR_LUNCH, "#212121")));
        card_viewLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorPressedButton = "colorLunch";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(COLOR_LUNCH, "#212121"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });


        

    }

    @Override
    public void applyColor(String color) {

        if(colorPressedButton.compareTo("colorColumnHeader") == 0){

            mEditor.putString(COLOR_COLUMN_HEADER, "#" + color);
            mEditor.commit();

            card_viewColumnHeader.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorRowHeaderHour") == 0){

            mEditor.putString(COLOR_ROW_HEADER_HOUR, "#" + color);
            mEditor.commit();

            card_viewRowHeaderHour.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorRowHeaderBreak") == 0){

            mEditor.putString(COLOR_ROW_HEADER_BREAK, "#" + color);
            mEditor.commit();

            card_viewRowHeaderBreak.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorClassicHour") == 0){

            mEditor.putString(COLOR_CLASSIC_HOUR, "#" + color);
            mEditor.commit();

            card_viewClassicHour.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorClassicSupervision") == 0){

            mEditor.putString(COLOR_CLASSIC_SUPERVISION, "#" + color);
            mEditor.commit();

            card_viewClassicSupervision.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorMorningSupervision") == 0){

            mEditor.putString(COLOR_MORNING_SUPERVISION, "#" + color);
            mEditor.commit();

            card_viewMorningSupervision.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorPorterSupervision") == 0){

            mEditor.putString(COLOR_PORTER_SUPERVISION, "#" + color);
            mEditor.commit();

            card_viewPorterSupervision.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorPPPSupervision") == 0){

            mEditor.putString(COLOR_PPP_SUPERVISION, "#" + color);
            mEditor.commit();

            card_viewPPPSupervision.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorDinningRoomSupervision") == 0){

            mEditor.putString(COLOR_DINNINGROOM_SUPERVISION, "#" + color);
            mEditor.commit();

            card_viewDinningRoomSupervision.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorMiddaySupervision") == 0){

            mEditor.putString(COLOR_MIDDAY_SUPERVISION, "#" + color);
            mEditor.commit();

            card_viewMiddaySupervision.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorClassLesson") == 0){

            mEditor.putString(COLOR_CLASS_LESSON, "#" + color);
            mEditor.commit();

            card_viewClassLesson.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorConsultationHour") == 0){

            mEditor.putString(COLOR_CONSULTATION_HOUR, "#" + color);
            mEditor.commit();

            card_viewConsultationHour.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorSchoolActions") == 0){

            mEditor.putString(COLOR_SCHOOL_ACTIONS, "#" + color);
            mEditor.commit();

            card_viewSchoolActions.setBackgroundColor(Color.parseColor("#" + color));

        }else if(colorPressedButton.compareTo("colorLunch") == 0){

            mEditor.putString(COLOR_LUNCH, "#" + color);
            mEditor.commit();

            card_viewLunch.setBackgroundColor(Color.parseColor("#" + color));

        }





    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }


}

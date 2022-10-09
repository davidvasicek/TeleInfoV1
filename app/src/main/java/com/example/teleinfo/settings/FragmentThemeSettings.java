package com.example.teleinfo.settings;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_COLOR_APP_BAR;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;
import com.example.teleinfo.dialogs.ColorSelectBottomSheetDialog;
import com.example.teleinfo.dialogs.EasyRadioGroupListDialog;

import java.util.ArrayList;
import java.util.List;

public class FragmentThemeSettings extends Fragment implements ColorSelectBottomSheetDialog.ColorSelectListener, EasyRadioGroupListDialog.ItemSelectedListener {


    TextView textViewColorTheme;
    TextView textViewColorTabNonSelectedText;
    TextView textViewColorThemeAppBar;


    String colorPressedButton; // rozlišuje, čemu chci vlastbně měnit barvu

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    List<String> appBarThemes;
    int idOfSelectedAppBarTheme;

    public FragmentThemeSettings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.settings_fragment_theme, container, false);

        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();


        textViewColorTheme = (TextView)rootView.findViewById(R.id.settingsThemeActivityTextViewColorTheme);
        textViewColorTabNonSelectedText = (TextView)rootView.findViewById(R.id.settingsThemeActivityTextViewColorTabNonSelectedText);
        textViewColorThemeAppBar = (TextView)rootView.findViewById(R.id.settingsThemeActivityTextViewAppBarColor);






        textViewColorTheme.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(CURRENT_THEME, "#212121")));
        textViewColorTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> colors = new ArrayList<>();
                colors.add("#F44336");
                colors.add("#D32F2F");
                colors.add("#B71C1C");
                colors.add("#E91E63");
                colors.add("#C2185B");
                colors.add("#880E4F");
                colors.add("#9C27B0");
                colors.add("#7B1FA2");
                colors.add("#4A148C");
                colors.add("#673AB7");
                colors.add("#512DA8");
                colors.add("#311B92");
                colors.add("#3F51B5");
                colors.add("#303F9F");
                colors.add("#1A237E");
                colors.add("#2196F3");
                colors.add("#1976D2");
                colors.add("#0D47A1");
                colors.add("#03A9F4");
                colors.add("#0288D1");
                colors.add("#01579B");
                colors.add("#00BCD4");
                colors.add("#0097A7");
                colors.add("#006064");
                colors.add("#009688");
                colors.add("#00796B");
                colors.add("#004D40");
                colors.add("#4CAF50");
                colors.add("#388E3C");
                colors.add("#1B5E20");
                colors.add("#8BC34A");
                colors.add("#689F38");
                colors.add("#33691E");
                colors.add("#CDDC39");
                colors.add("#AFB42B");
                colors.add("#827717");
                colors.add("#FFEB3B");
                colors.add("#FBC02D");
                colors.add("#F57F17");
                colors.add("#FFC107");
                colors.add("#FFA000");
                colors.add("#FF6F00");
                colors.add("#FF9800");
                colors.add("#F57C00");
                colors.add("#E65100");
                colors.add("#FF5722");
                colors.add("#E64A19");
                colors.add("#BF360C");
                colors.add("#795548");
                colors.add("#5D4037");
                colors.add("#3E2723");
                colors.add("#9E9E9E");
                colors.add("#616161");
                colors.add("#212121");
                colors.add("#607D8B");
                colors.add("#455A64");
                colors.add("#263238");




                colorPressedButton = "ColorTheme";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 3, mSharedPreferences.getString(CURRENT_THEME, "#212121"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");




            }
        });


        textViewColorThemeAppBar.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(CURRENT_COLOR_APP_BAR, "#FAFAFA")));
        textViewColorThemeAppBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> colors = new ArrayList<>();
                colors.add("#FAFAFA");
                colors.add("#F5F5F5");
                colors.add("#EEEEEE");
                colors.add("#E0E0E0");
                colors.add("#BDBDBD");
                colors.add("#9E9E9E");
                colors.add("#757575");
                colors.add("#616161");
                colors.add("#424242");
                colors.add("#212121");
                colors.add("#ECEFF1");
                colors.add("#CFD8DC");
                colors.add("#B0BEC5");
                colors.add("#90A4AE");
                colors.add("#78909C");
                colors.add("#607D8B");
                colors.add("#546E7A");
                colors.add("#455A64");
                colors.add("#37474F");
                colors.add("#263238");

                colorPressedButton = "AppBarColor";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(CURRENT_COLOR_APP_BAR, "#FAFAFA"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });


        textViewColorTabNonSelectedText.setBackgroundColor(Color.parseColor(mSharedPreferences.getString(CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT, "#616161")));
        textViewColorTabNonSelectedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> colors = new ArrayList<>();
                colors.add("#FAFAFA");
                colors.add("#F5F5F5");
                colors.add("#EEEEEE");
                colors.add("#E0E0E0");
                colors.add("#BDBDBD");
                colors.add("#9E9E9E");
                colors.add("#757575");
                colors.add("#616161");
                colors.add("#424242");
                colors.add("#212121");
                colors.add("#ECEFF1");
                colors.add("#CFD8DC");
                colors.add("#B0BEC5");
                colors.add("#90A4AE");
                colors.add("#78909C");
                colors.add("#607D8B");
                colors.add("#546E7A");
                colors.add("#455A64");
                colors.add("#37474F");
                colors.add("#263238");

                colorPressedButton = "ColorTabLayoutNonSelectedText";

                ColorSelectBottomSheetDialog colorSelectBottomSheetDialog = new ColorSelectBottomSheetDialog(colors, 5, mSharedPreferences.getString(CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT, "#616161"));
                colorSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");
            }
        });




        return rootView;
    }

    public String convertAppBarTheme(String theme){

        if(theme.compareTo("Light") == 0){

            return "světlé";//getApplicationContext().getResources().getString(R.string.settingsThemeActivity_TextView_ThemeLight)"";
        }

        if(theme.compareTo("Dark") == 0){

            return "tmavé";//getApplicationContext().getResources().getString(R.string.settingsThemeActivity_TextView_ThemeDark);
        }

        return "";

    }


    @Override
    public void applyColor(String color) {

        if(colorPressedButton.compareTo("ColorTheme") == 0){

            mEditor.putString(CURRENT_THEME, "#" + color);
            mEditor.commit();

            textViewColorTheme.setBackgroundColor(Color.parseColor("#" + color));
        }

        if(colorPressedButton.compareTo("ColorTabLayoutNonSelectedText") == 0){

            mEditor.putString(CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT, "#" + color);
            mEditor.commit();

            textViewColorTabNonSelectedText.setBackgroundColor(Color.parseColor("#" + color));
        }

        if(colorPressedButton.compareTo("AppBarColor") == 0){

            mEditor.putString(CURRENT_COLOR_APP_BAR, "#" + color);
            mEditor.commit();

            textViewColorThemeAppBar.setBackgroundColor(Color.parseColor("#" + color));
        }





    }

    @Override
    public void applyID(int id) {

    }
}













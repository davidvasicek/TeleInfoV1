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
                colors.add("#FFEBEE");
                colors.add("#FFCDD2");
                colors.add("#EF9A9A");
                colors.add("#E57373");
                colors.add("#EF5350");
                colors.add("#F44336");
                colors.add("#E53935");
                colors.add("#D32F2F");
                colors.add("#C62828");
                colors.add("#B71C1C");
                colors.add("#FF8A80");
                colors.add("#FF5252");
                colors.add("#FF1744");
                colors.add("#D50000");
                colors.add("#FCE4EC");
                colors.add("#F8BBD0");
                colors.add("#F48FB1");
                colors.add("#F06292");
                colors.add("#EC407A");
                colors.add("#E91E63");
                colors.add("#D81B60");
                colors.add("#C2185B");
                colors.add("#AD1457");
                colors.add("#880E4F");
                colors.add("#FF80AB");
                colors.add("#FF4081");
                colors.add("#F50057");
                colors.add("#C51162");
                colors.add("#F3E5F5");
                colors.add("#E1BEE7");
                colors.add("#CE93D8");
                colors.add("#BA68C8");
                colors.add("#AB47BC");
                colors.add("#9C27B0");
                colors.add("#8E24AA");
                colors.add("#7B1FA2");
                colors.add("#6A1B9A");
                colors.add("#4A148C");
                colors.add("#EA80FC");
                colors.add("#E040FB");
                colors.add("#D500F9");
                colors.add("#AA00FF");
                colors.add("#EDE7F6");
                colors.add("#D1C4E9");
                colors.add("#B39DDB");
                colors.add("#9575CD");
                colors.add("#7E57C2");
                colors.add("#673AB7");
                colors.add("#5E35B1");
                colors.add("#512DA8");
                colors.add("#4527A0");
                colors.add("#311B92");
                colors.add("#B388FF");
                colors.add("#7C4DFF");
                colors.add("#651FFF");
                colors.add("#6200EA");
                colors.add("#E8EAF6");
                colors.add("#C5CAE9");
                colors.add("#9FA8DA");
                colors.add("#7986CB");
                colors.add("#5C6BC0");
                colors.add("#3F51B5");
                colors.add("#3949AB");
                colors.add("#303F9F");
                colors.add("#283593");
                colors.add("#1A237E");
                colors.add("#8C9EFF");
                colors.add("#536DFE");
                colors.add("#3D5AFE");
                colors.add("#304FFE");
                colors.add("#E3F2FD");
                colors.add("#BBDEFB");
                colors.add("#90CAF9");
                colors.add("#64B5F6");
                colors.add("#42A5F5");
                colors.add("#2196F3");
                colors.add("#1E88E5");
                colors.add("#1976D2");
                colors.add("#1565C0");
                colors.add("#0D47A1");
                colors.add("#82B1FF");
                colors.add("#448AFF");
                colors.add("#2979FF");
                colors.add("#2962FF");
                colors.add("#E1F5FE");
                colors.add("#B3E5FC");
                colors.add("#81D4FA");
                colors.add("#4FC3F7");
                colors.add("#29B6F6");
                colors.add("#03A9F4");
                colors.add("#039BE5");
                colors.add("#0288D1");
                colors.add("#0277BD");
                colors.add("#01579B");
                colors.add("#80D8FF");
                colors.add("#40C4FF");
                colors.add("#00B0FF");
                colors.add("#0091EA");
                colors.add("#E0F7FA");
                colors.add("#B2EBF2");
                colors.add("#80DEEA");
                colors.add("#4DD0E1");
                colors.add("#26C6DA");
                colors.add("#00BCD4");
                colors.add("#00ACC1");
                colors.add("#0097A7");
                colors.add("#00838F");
                colors.add("#006064");
                colors.add("#84FFFF");
                colors.add("#18FFFF");
                colors.add("#00E5FF");
                colors.add("#00B8D4");
                colors.add("#E0F2F1");
                colors.add("#B2DFDB");
                colors.add("#80CBC4");
                colors.add("#4DB6AC");
                colors.add("#26A69A");
                colors.add("#009688");
                colors.add("#00897B");
                colors.add("#00796B");
                colors.add("#00695C");
                colors.add("#004D40");
                colors.add("#A7FFEB");
                colors.add("#64FFDA");
                colors.add("#1DE9B6");
                colors.add("#00BFA5");
                colors.add("#E8F5E9");
                colors.add("#C8E6C9");
                colors.add("#A5D6A7");
                colors.add("#81C784");
                colors.add("#66BB6A");
                colors.add("#4CAF50");
                colors.add("#43A047");
                colors.add("#388E3C");
                colors.add("#2E7D32");
                colors.add("#1B5E20");
                colors.add("#B9F6CA");
                colors.add("#69F0AE");
                colors.add("#00E676");
                colors.add("#00C853");
                colors.add("#F1F8E9");
                colors.add("#DCEDC8");
                colors.add("#C5E1A5");
                colors.add("#AED581");
                colors.add("#9CCC65");
                colors.add("#8BC34A");
                colors.add("#7CB342");
                colors.add("#689F38");
                colors.add("#558B2F");
                colors.add("#33691E");
                colors.add("#CCFF90");
                colors.add("#B2FF59");
                colors.add("#76FF03");
                colors.add("#64DD17");
                colors.add("#F9FBE7");
                colors.add("#F0F4C3");
                colors.add("#E6EE9C");
                colors.add("#DCE775");
                colors.add("#D4E157");
                colors.add("#CDDC39");
                colors.add("#C0CA33");
                colors.add("#AFB42B");
                colors.add("#9E9D24");
                colors.add("#827717");
                colors.add("#F4FF81");
                colors.add("#EEFF41");
                colors.add("#C6FF00");
                colors.add("#AEEA00");
                colors.add("#FFFDE7");
                colors.add("#FFF9C4");
                colors.add("#FFF59D");
                colors.add("#FFF176");
                colors.add("#FFEE58");
                colors.add("#FFEB3B");
                colors.add("#FDD835");
                colors.add("#FBC02D");
                colors.add("#F9A825");
                colors.add("#F57F17");
                colors.add("#FFFF8D");
                colors.add("#FFFF00");
                colors.add("#FFEA00");
                colors.add("#FFD600");
                colors.add("#FFF8E1");
                colors.add("#FFECB3");
                colors.add("#FFE082");
                colors.add("#FFD54F");
                colors.add("#FFCA28");
                colors.add("#FFC107");
                colors.add("#FFB300");
                colors.add("#FFA000");
                colors.add("#FF8F00");
                colors.add("#FF6F00");
                colors.add("#FFE57F");
                colors.add("#FFD740");
                colors.add("#FFC400");
                colors.add("#FFAB00");
                colors.add("#FFF3E0");
                colors.add("#FFE0B2");
                colors.add("#FFCC80");
                colors.add("#FFB74D");
                colors.add("#FFA726");
                colors.add("#FF9800");
                colors.add("#FB8C00");
                colors.add("#F57C00");
                colors.add("#EF6C00");
                colors.add("#E65100");
                colors.add("#FFD180");
                colors.add("#FFAB40");
                colors.add("#FF9100");
                colors.add("#FF6D00");
                colors.add("#FBE9E7");
                colors.add("#FFCCBC");
                colors.add("#FFAB91");
                colors.add("#FF8A65");
                colors.add("#FF7043");
                colors.add("#FF5722");
                colors.add("#F4511E");
                colors.add("#E64A19");
                colors.add("#D84315");
                colors.add("#BF360C");
                colors.add("#FF9E80");
                colors.add("#FF6E40");
                colors.add("#FF3D00");
                colors.add("#DD2C00");
                colors.add("#EFEBE9");
                colors.add("#D7CCC8");
                colors.add("#BCAAA4");
                colors.add("#A1887F");
                colors.add("#8D6E63");
                colors.add("#795548");
                colors.add("#6D4C41");
                colors.add("#5D4037");
                colors.add("#4E342E");
                colors.add("#3E2723");
                colors.add("#D7CCC8");
                colors.add("#BCAAA4");
                colors.add("#8D6E63");
                colors.add("#5D4037");
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
                colors.add("#F5F5F5");
                colors.add("#EEEEEE");
                colors.add("#BDBDBD");
                colors.add("#616161");
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
                colors.add("#CFD8DC");
                colors.add("#B0BEC5");
                colors.add("#78909C");
                colors.add("#455A64");




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













package com.example.teleinfo.settings;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;

public class _ActivityMainSettings extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private boolean isLargeDisplay;

    LinearLayout linearLayoutReset;
    LinearLayout linearLayoutNotification;
    LinearLayout linearLayoutTheme;
    LinearLayout linearLayoutLightsGenerals;
    LinearLayout linearLayoutLightsBtn;
    LinearLayout linearLayoutPlants;
    LinearLayout linearLayoutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(mSharPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.settings_main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Nastavení" + "</small>"));

        linearLayoutReset = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutReset);
        linearLayoutNotification = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutNotification);
        linearLayoutTheme = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutTheme);
        linearLayoutLightsGenerals = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutLightsGenerals);
        linearLayoutLightsBtn = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutLightsBtn);
        linearLayoutPlants = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutPlants);
        linearLayoutInfo = (LinearLayout)findViewById(R.id.settingsMenuLinearLayoutInfo);


        linearLayoutReset.setOnClickListener(mOnKeyClickListener);
        linearLayoutNotification.setOnClickListener(mOnKeyClickListener);
        linearLayoutTheme.setOnClickListener(mOnKeyClickListener);
        linearLayoutLightsGenerals.setOnClickListener(mOnKeyClickListener);
        linearLayoutLightsBtn.setOnClickListener(mOnKeyClickListener);
        linearLayoutPlants.setOnClickListener(mOnKeyClickListener);
        linearLayoutInfo.setOnClickListener(mOnKeyClickListener);

        fragmentManager = getSupportFragmentManager();

        /*if (findViewById(R.id.settingsContainer) != null) {
            isLargeDisplay = true;
            Toast.makeText(getApplicationContext(),"Tablet",Toast.LENGTH_SHORT).show();
        } else {
            isLargeDisplay = false;
            Toast.makeText(getApplicationContext(),"Mobil",Toast.LENGTH_SHORT).show();
        }

        if (isLargeDisplay) {
            fragmentManager.beginTransaction()
                    .replace(R.id.settingsContainer, new FragmentThemeSettings())
                    .commit();
        }*/

/*
        linearLayoutReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isLargeDisplay) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.settingsContainer, new FragmentResetSettings())
                            .commit();
                }else{

                    Intent intent = new Intent(getApplicationContext(), ActivityFragmentView.class);
                    //intent.putExtra(ActivityFragmentView.EXTRA_SETTING_OPTION, option);
                    startActivity(intent);

                }

            }
        });*/



    }

    private View.OnClickListener mOnKeyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof LinearLayout) {

                String stringTag = ((LinearLayout) v).getTag().toString(); // Získej číslo tlačítka z jeho popisu

              /*  if (isLargeDisplay) {

                    switch (stringTag) {

                        case "settingsMenuLinearLayoutReset": {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.settingsContainer, new FragmentResetSettings())
                                    .commit();
                            break;
                        }

                        case "settingsMenuLinearLayoutNotification": {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.settingsContainer, new FragmentNotificationsSetting())
                                    .commit();
                            break;
                        }

                        case "settingsMenuLinearLayoutTheme": {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.settingsContainer, new FragmentThemeSettings())
                                    .commit();
                            break;
                        }


                    }

                }else{*/

                    Intent intent = new Intent(getApplicationContext(), ActivityFragmentView.class);
                    intent.putExtra("stringTag", stringTag);
                    startActivity(intent);
               /* }*/

            }
        }
    };


    public void onOptionSelected(String option) {

        // jestliže je velký displej (je tablet) vše otevírej v pravé části obrazovky přepisovaním fragmentu
        if (isLargeDisplay) {

            switch (option) {
                case "network": {
                    //   fragmentManager.beginTransaction()
                    //          .replace(R.id.detailContainer, new RoomLegendDialog())
                    //          .commit();
                    break;
                }
                case "network1": {
                    ///   fragmentManager.beginTransaction()
                    ///           .replace(R.id.detailContainer, new RoomLegendDialog1())
                    //          .commit();
                    break;
                }
              /*    case "storage": {
                    fragmentManager.beginTransaction()
                            .replace(R.id.detailContainer, new StorageSettingsFragment())
                            .commit();
                    break;
                }*/
            }
        } else { // jestliže je malý displej (není tablet) otevírej vše jako novou aktivitu s fragmentem, ve které se bude fragment přepisovat
            //Intent intent = new Intent(this, SettingDetailsActivity.class);
            // intent.putExtra(SettingDetailsActivity.EXTRA_SETTING_OPTION, option);
            // startActivity(intent);
        }
    }



    public void sensorsOnOptionSelected(String option) {

        // jestliže je velký displej (je tablet) vše otevírej v pravé části obrazovky přepisovaním fragmentu
        if (isLargeDisplay) {

            switch (option) {
                case "plnt_001": {
                    // fragmentManager.beginTransaction()
                    //         .replace(R.id.detailContainer, new PlantsFragment())
                    //         .commit();
                    break;
                }


            }
        } else { // jestliže je malý displej (není tablet) otevírej vše jako novou aktivitu s fragmentem, ve které se bude fragment přepisovat
            //Intent intent = new Intent(this, SettingDetailsActivity.class);
            //intent.putExtra(SettingDetailsActivity.EXTRA_SETTING_OPTION, option);
            //startActivity(intent);
        }

    }
}
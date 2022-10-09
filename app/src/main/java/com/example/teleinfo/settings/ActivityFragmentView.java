package com.example.teleinfo.settings;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_COLOR_APP_BAR;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;

public class ActivityFragmentView extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    FragmentManager fragmentManager;

    FrameLayout frameLayout;

    Intent intent;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shrPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.settings_activity_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable drawable = toolbar.getNavigationIcon();

        mSharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        String appBarColor = mSharedPreferences.getString(CURRENT_COLOR_APP_BAR, "#FAFAFA");

        drawable.setColorFilter(Color.parseColor(appBarColor), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setTitle(Html.fromHtml("<small><font color='" +appBarColor+ "'>" + "Téma aplikace" + "</font></small>"));



        intent = getIntent();



        fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.settingsContainer11);

        if(intent.getStringExtra("stringTag") != null){

            if (frameLayout != null) {

                switch (intent.getStringExtra("stringTag")) {

                    case "settingsMenuLinearLayoutReset": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer11, new FragmentResetSettings())
                                .commit();
                        break;
                    }

                    case "settingsMenuLinearLayoutNotification": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer11, new FragmentNotificationsSetting())
                                .commit();
                        break;
                    }

                    case "settingsMenuLinearLayoutTheme": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer11, new FragmentThemeSettings())
                                .commit();
                        break;
                    }


                }

            }




        }

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if(s.equals("CurrentTheme")){
            recreate();
        }

        if(s.equals("CurentColorAppBar")){

            Drawable drawable = toolbar.getNavigationIcon();

            mSharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
            mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
            String appBarColor = mSharedPreferences.getString(CURRENT_COLOR_APP_BAR, "#FAFAFA");

            drawable.setColorFilter(Color.parseColor(appBarColor), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setTitle(Html.fromHtml("<small><font color='" +appBarColor+ "'>" + "Téma aplikace" + "</font></small>"));


        }

    }

}
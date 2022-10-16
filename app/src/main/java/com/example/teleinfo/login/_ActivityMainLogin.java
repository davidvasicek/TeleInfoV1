package com.example.teleinfo.login;

import static com.example.teleinfo.parameters.MainParameters.AUTH_PRIORITY;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_COLOR_APP_BAR;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DEVICE_IS_PAIRED;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_FINGERPRINT;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_PIN;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.teleinfo.R;
import com.example.teleinfo.administration.FragmentAdministrationTeachers;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class _ActivityMainLogin extends AppCompatActivity {

    //test

    FragmentManager fragmentManager;

    FrameLayout frameLayout;

    Intent intent;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(mSharPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.login_activity_main);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        mSharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        //mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        String appBarColor = mSharedPreferences.getString(CURRENT_COLOR_APP_BAR, "#FAFAFA");



        intent = getIntent();



        fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.settingsContainer1111);


        if(mSharedPreferences.getBoolean(DEVICE_IS_PAIRED, false)){

            if(mSharedPreferences.getInt(AUTH_PRIORITY, -1) == LOGIN_BY_FINGERPRINT){

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer1111, new FragmentLoginWithFingerprint())
                        .commit();

            }

            if(mSharedPreferences.getInt(AUTH_PRIORITY, -1) == LOGIN_BY_PIN){

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer1111, new FragmentLoginWithPin())
                        .commit();

            }





        }else{

            fragmentManager.beginTransaction()
                    .replace(R.id.settingsContainer1111, new FragmentFirstLoginQR())
                    .commit();

        }




        if(intent.getStringExtra("stringTag") != null){

            if (frameLayout != null) {

                switch (intent.getStringExtra("stringTag")) {

                    case "administrationLinearLayoutTeachers": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer1111, new FragmentAdministrationTeachers())
                                .commit();
                        break;
                    }

                   /* case "settingsMenuLinearLayoutNotification": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer111, new FragmentNotificationsSetting())
                                .commit();
                        break;
                    }*/

                   /* case "settingsMenuLinearLayoutTheme": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer111, new FragmentThemeSettings())
                                .commit();
                        break;
                    }*/


                }

            }




        }


    }
}
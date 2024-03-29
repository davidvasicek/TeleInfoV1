package com.example.teleinfo.administration;

import static com.example.teleinfo.parameters.MainParameters.ADMINISTRATION_NEW;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_COLOR_APP_BAR;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.example.teleinfo.rozvrh.TimetableMenuBottomSheetDialog;
import com.example.teleinfo.settings.FragmentNotificationsSetting;
import com.example.teleinfo.settings.FragmentResetSettings;
import com.example.teleinfo.settings.FragmentThemeSettings;

public class _ActivityFragmentViewAdministration extends AppCompatActivity {

    FragmentManager fragmentManager;

    FrameLayout frameLayout;

    Intent intent;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    Toolbar toolbar;

    Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shrPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.administration_activity_fragment_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar111);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable drawable = toolbar.getNavigationIcon();

        mSharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        //mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        String appBarColor = mSharedPreferences.getString(CURRENT_COLOR_APP_BAR, "#FAFAFA");

        drawable.setColorFilter(Color.parseColor(appBarColor), PorterDuff.Mode.SRC_ATOP);



        intent = getIntent();



        fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.settingsContainer111);

        if(intent.getStringExtra("stringTag") != null){

            if (frameLayout != null) {

                switch (intent.getStringExtra("stringTag")) {

                    case "administrationLinearLayoutTeachers": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer111, new FragmentAdministrationTeachers())
                                .commit();

                        getSupportActionBar().setTitle(Html.fromHtml("<small><font color='" +appBarColor+ "'>" + "Seznam učetelů" + "</font></small>"));



                        break;
                    }

                    case "administrationLinearLayoutSubjects": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer111, new FragmentAdministrationSubjects())
                                .commit();

                        getSupportActionBar().setTitle(Html.fromHtml("<small><font color='" +appBarColor+ "'>" + "Seznam předmětů" + "</font></small>"));


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_plus, menu);

        this.mMenu = menu;

        mMenu.getItem(0).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);

        mMenu.getItem(0).setVisible(true);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.plus:

                switch (intent.getStringExtra("stringTag")) {

                    case "administrationLinearLayoutTeachers": {

                        BottomSheetDialogTeacherEditNew bottomSheetDialogTeacherEditNew = new BottomSheetDialogTeacherEditNew();
                        bottomSheetDialogTeacherEditNew.show(getSupportFragmentManager(), "exampleBottomSheet");

                        break;
                    }

                    case "administrationLinearLayoutSubjects": {
                        fragmentManager.beginTransaction()
                                .replace(R.id.settingsContainer111, new FragmentAdministrationSubjects())
                                .commit();

                        BottomSheetDialogSubjectsEditNew bottomSheetDialogSubjectsEditNew = new BottomSheetDialogSubjectsEditNew(ADMINISTRATION_NEW, null);
                        bottomSheetDialogSubjectsEditNew.show(getSupportFragmentManager(), "exampleBottomSheet");

                        break;
                    }





                }



                return(true);



        }

        return(super.onOptionsItemSelected(item));
    }



}
package com.example.teleinfo;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.example.teleinfo.administration._ActivityFragmentViewAdministration;
import com.example.teleinfo.krmitko.KrmitkoMainActivity;
import com.example.teleinfo.login._ActivityMainLogin;
import com.example.teleinfo.modules.ClassRooms_BottomSheetDialogClassRoomsList;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.example.teleinfo.rozvrh.BottomSheetDialogCalendar;
import com.example.teleinfo.rozvrh.BottomSheetDialogCalendarWeeks;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.teleinfo.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mSharPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(mSharPref.getString(CURRENT_THEME, "#212121")));
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar111x);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Nastavení" + "</small>"));

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //BottomSheetDialogCalendarWeeks bottomSheetDialogCalendarWeeks = new BottomSheetDialogCalendarWeeks();
       // bottomSheetDialogCalendarWeeks.show(getSupportFragmentManager(), "exampleBottomSheet");

        // Intent intent = new Intent(getApplicationContext(), _ActivityMainLogin.class);
       // startActivity(intent);

       //  Intent intent = new Intent(getApplicationContext(), testRecyclerViewHorizontal.class);
        // startActivity(intent);


        //   Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        //   //    startActivity(intent);


      //  mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
      //  mDatabaseReference = mFirebaseDatabaseDatabase.getReference("TeleInfo/Administration/");



        Intent intent = new Intent(getApplicationContext(), KrmitkoMainActivity.class);
        startActivity(intent);







        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_channel", "notification_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed Successfully";
                        if (!task.isSuccessful()) {
                            msg = "Subscription failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });




    }

}


// https://www.geeksforgeeks.org/android-horizontal-recyclerview-with-examples/ reczcler view calendar
// https://viblo.asia/p/lam-viec-voi-bottom-sheet-Do754jjXZM6
// expand bottom sheet dialog


// filter https://github.com/Krupen/FabulousFilter

// https://github.com/Hamadakram/AlertView


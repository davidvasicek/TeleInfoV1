package com.example.teleinfo;

import android.content.Intent;
import android.os.Bundle;

import com.example.teleinfo.administration.BottomSheetDialogTeacherEdit;
import com.example.teleinfo.administration._ActivityFragmentViewAdministration;
import com.example.teleinfo.login.ChangePasswordActivity;
import com.example.teleinfo.login._ActivityMainLogin;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.teleinfo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //BottomSheetDialogTeacherEdit bottomSheetDialogTeacherEdit = new BottomSheetDialogTeacherEdit();
        //bottomSheetDialogTeacherEdit.show(getSupportFragmentManager(), "exampleBottomSheet");

      //  Intent intent = new Intent(getApplicationContext(), _ActivityMainLogin.class);
      //  startActivity(intent);

        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        startActivity(intent);

    }

}
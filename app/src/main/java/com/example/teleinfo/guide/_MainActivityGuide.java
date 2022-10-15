package com.example.teleinfo.guide;

import static com.example.teleinfo.parameters.MainParameters.AUTH_PIN_OR_PASSWORD;
import static com.example.teleinfo.parameters.MainParameters.AUTH_PRIORITY;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DEVICE_IS_PAIRED;
import static com.example.teleinfo.parameters.MainParameters.FINGERPRINT_HARDWARE_IS_DETECTED;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_CREDENTIALS;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.USER_EMAIL_LOGGED;
import static com.example.teleinfo.parameters.MainParameters.USER_PASSWORD_LOGGED;
import static com.example.teleinfo.parameters.MainParameters.USER_ROLE;
import static com.example.teleinfo.parameters.MainParameters.USER_TIME_TABLE;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.teleinfo.MainActivity;
import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class _MainActivityGuide extends AppCompatActivity implements a_RunGuideFragment.OnGuideOptionClickListener, c_b_GuideSecurityPinFragment.OnGuideOptionClickListener, c_a_GuideSecurityFingerprintFragment.OnGuideOptionClickListener, c_c_GuideSecurityCredentialsFragment.OnGuideOptionClickListener{

    FragmentManager fragmentManager;
    String keyOption;

    FrameLayout frameLayout;

    int counter = 0;
    int totalPageCount = 3;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    boolean isHardwareDetected;

    int authMethod;
    String pinOrPassword;
    String token = null;

    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(mSharPref.getString(CURRENT_THEME, "#212121")));
        setContentView(R.layout.activity_main_guide);

        mSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.settingsContainer19);

        if (frameLayout != null) {

            fragmentManager.beginTransaction()
                    .add(R.id.settingsContainer19, new a_RunGuideFragment())
                    .commit();
        }

        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        isHardwareDetected = fingerprintManager.isHardwareDetected();

        Log.e("Inmetry", "isHardwareDetected: " + isHardwareDetected);

        mEditor.putBoolean(FINGERPRINT_HARDWARE_IS_DETECTED, isHardwareDetected );
        mEditor.commit();

        //if(!isHardwareDetected){

        //     totalPageCount--;
        //}

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Lojza", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                       // FirebaseDatabase database = FirebaseDatabase.getInstance();
                      //  DatabaseReference myRef = database.getReference("TeleInfo/FCM/DeviceInfo");

                        //AndroidDevicesObject androidDevicesObject = new AndroidDevicesObject();

                        //androidDevicesObject.Device_ID = Device_ID;
                        //androidDevicesObject.Device_Name = android.os.Build.BRAND + " " + android.os.Build.MODEL;
                        //androidDevicesObject.Token = token;

                       // myRef.setValue(token);

                        // Log and toast

                        Log.d("lojza", "aaaaaaaaaa");
                       // Toast.makeText(MainActivity.this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
                    }
                });




    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putString("fragment", keyOption);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onGuideOptionClickListener(String keyOption, int keyOptions, String valueOptions ) {

        switch (keyOption) {
            case "changeToPinFragment": {

                authMethod = keyOptions;

                c_b_GuideSecurityPinFragment fragment = new c_b_GuideSecurityPinFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;

            }

            case "changeToFingerprintFragment": {

                authMethod = keyOptions;

                c_a_GuideSecurityFingerprintFragment fragment = new c_a_GuideSecurityFingerprintFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToCredentialsFragment": {

                authMethod = keyOptions;

                c_c_GuideSecurityCredentialsFragment fragment = new c_c_GuideSecurityCredentialsFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToAuthPriorityFragment": {

                b_GuideSecurityAuthPriorityFragment fragment = new b_GuideSecurityAuthPriorityFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToNotificationsFragment": {

                pinOrPassword = valueOptions;

                d_GuideNotificationsFragment fragment = new d_GuideNotificationsFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToFinish": {

                boolean status;

                if(token != null){

                    status = true;

                }else{

                    status = false;

                }

                GuideFinishFragment fragment = GuideFinishFragment.newInstance(status);

                intent = getIntent();

                mEditor.putInt(AUTH_PRIORITY, authMethod );
                mEditor.putString(AUTH_PIN_OR_PASSWORD, pinOrPassword);
                mEditor.putString(USER_EMAIL_LOGGED, intent.getStringExtra("Email"));
                mEditor.putString(USER_PASSWORD_LOGGED, intent.getStringExtra("Password"));
                mEditor.putString(USER_TIME_TABLE, intent.getStringExtra("PairTimeTable"));
                mEditor.putString(USER_ROLE, intent.getStringExtra("Role"));







                 // uložit jméno
                // uložit příjmení
                // uložit přezdívku
                // uložit rozvh
                // uložit máOmluvenky modul
                // uložit email
                // uložit
                // uložit
                // uložit
                // uložit autentizační metodu
                // uložit heslo, pokud je to nutné dle metody
                // uložit heslo přístupové do celé aplikace pro budoucí přihlašování
                // uložit je třídní truefalse
                // uložit jaká je jeho třída?
                // uložit
                // uložit zařízení je spárováno


                mEditor.putBoolean(DEVICE_IS_PAIRED, true );
                mEditor.commit();

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

        }
    }

    private Boolean checkBiometricSupport() {

        KeyguardManager keyguardManager =
                (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        PackageManager packageManager = this.getPackageManager();

        if (!keyguardManager.isKeyguardSecure()) {
            //notifyUser("Lock screen security not enabled in Settings");
            return false;
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.USE_BIOMETRIC) !=
                PackageManager.PERMISSION_GRANTED) {

            //notifyUser("Fingerprint authentication permission not enabled");
            return false;
        }

        if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT))
        {
            return true;
        }

        return true;
    }


}

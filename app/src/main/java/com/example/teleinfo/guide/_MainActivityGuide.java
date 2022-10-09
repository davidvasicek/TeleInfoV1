package com.example.teleinfo.guide;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.FINGERPRINT_HARDWARE_IS_DETECTED;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;

public class _MainActivityGuide extends AppCompatActivity implements RunGuideFragment.OnGuideOptionClickListener,GuideSecurityPinFragment.OnGuideOptionClickListener, GuideSecurityFingerprintFragment.OnGuideOptionClickListener{

    FragmentManager fragmentManager;
    String keyOption;

    FrameLayout frameLayout;

    int counter = 0;
    int totalPageCount = 3;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    boolean isHardwareDetected;

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
                    .add(R.id.settingsContainer19, new RunGuideFragment())
                    .commit();
        }

        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        isHardwareDetected = fingerprintManager.isHardwareDetected();

        Log.i("Inmetry", "isHardwareDetected: " + isHardwareDetected);

        mEditor.putBoolean(FINGERPRINT_HARDWARE_IS_DETECTED, isHardwareDetected );
        mEditor.commit();

        //if(!isHardwareDetected){

        //     totalPageCount--;
        //}





    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putString("fragment", keyOption);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onGuideOptionClickListener(String keyOption, int counter) {

        this.counter += counter;


        switch (keyOption) {
            case "changeToPinFragment": {

                GuideSecurityPinFragment fragment = GuideSecurityPinFragment.newInstance(this.counter, totalPageCount);

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;

            }

            case "changeToFingerprintFragment": {

                GuideSecurityFingerprintFragment fragment = GuideSecurityFingerprintFragment.newInstance(this.counter, totalPageCount);

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToAuthPrioritFragment": {

                GuideSecurityAuthPriorityFragment fragment = GuideSecurityAuthPriorityFragment.newInstance(this.counter, totalPageCount);

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToPermissionsFragment": {

                GuidePermissions fragment = GuidePermissions.newInstance(this.counter, totalPageCount);

                fragmentManager.beginTransaction()
                        .replace(R.id.settingsContainer19, fragment)
                        .commit();
                break;
            }

            case "changeToFinish": {

                GuideFinishFragment fragment = GuideFinishFragment.newInstance(this.counter, totalPageCount);

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

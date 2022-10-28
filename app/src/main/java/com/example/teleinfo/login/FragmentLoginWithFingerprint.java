package com.example.teleinfo.login;

import static android.content.ContentValues.TAG;
import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.TIME_OF_LAST_BLOCKED_READER;
import static com.example.teleinfo.parameters.MainParameters.USER_EMAIL_LOGGED;
import static com.example.teleinfo.parameters.MainParameters.USER_PASSWORD_LOGGED;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.MainActivity;
import com.example.teleinfo.R;
import com.example.teleinfo.guide._MainActivityGuide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FragmentLoginWithFingerprint extends Fragment {

    TextView textViewFingerprintTextStatus;
    TextView textViewLoginStatus;
    TextView textViewNameOfUser;
    TextView textViewLoginWithCredentials;
    ImageView imageViewFingerprintIcon;
    ImageView imageViewFingerprintStatus;
    AVLoadingIndicatorView aVLoadingIndicatorViewLogging;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    boolean isVerify = false;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;

    private FingerprintHandler fingerprintHandler;

    private static final String FINGERPRINT_KEY = "key_name";

    private static final int REQUEST_USE_FINGERPRINT = 300;
    
    private int onAuthenticationFailedCount = 0;

    private static final long START_TIME_IN_MILLIS = 30000;


    private CountDownTimer mCountDownTimer;


    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;



    public FragmentLoginWithFingerprint() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.login_fragment_fingerprint, container, false);

        textViewFingerprintTextStatus = (TextView)rootView.findViewById(R.id.loginFragmentFingerprintTextViewFingerprintTextStatus);
        textViewLoginStatus = (TextView)rootView.findViewById(R.id.loginFragmentFingerprintTextViewLoginStatus);
        textViewNameOfUser = (TextView)rootView.findViewById(R.id.loginFragmentFingerprintTextViewNameOfUser);
        textViewLoginWithCredentials = (TextView)rootView.findViewById(R.id.loginFragmentFingerprintTextViewLoginWithCredentials);
        imageViewFingerprintIcon = (ImageView)rootView.findViewById(R.id.loginFragmentFingerprintImageViewFingerprintIcon);
        imageViewFingerprintStatus = (ImageView)rootView.findViewById(R.id.loginFragmentFingerprintImageViewFingerprintStatus);
        aVLoadingIndicatorViewLogging = (AVLoadingIndicatorView)rootView.findViewById(R.id.loginFragmentFingerprintAVLoadingIndicatorViewLogging);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/");

        textViewLoginStatus.setVisibility(View.GONE);
        textViewNameOfUser.setVisibility(View.GONE);
        aVLoadingIndicatorViewLogging.setVisibility(View.GONE);
        imageViewFingerprintStatus.setVisibility(View.GONE);
        textViewFingerprintTextStatus.setVisibility(View.GONE);

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();


        Long lastTime = mSharedPreferences.getLong(TIME_OF_LAST_BLOCKED_READER, 0);

        if(System.currentTimeMillis()-lastTime >30000){

            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            initFingerprint();

        }else{

            mTimeLeftInMillis = 30000-((System.currentTimeMillis()-lastTime));


            startTimer();
        }


















        return rootView;
    }






    boolean login() {

        String username = mSharedPreferences.getString(USER_EMAIL_LOGGED, "");
        String password = mSharedPreferences.getString(USER_PASSWORD_LOGGED, "");

        textViewNameOfUser.setVisibility(View.VISIBLE);
        textViewLoginStatus.setVisibility(View.VISIBLE);
        aVLoadingIndicatorViewLogging.setVisibility(View.VISIBLE);

        textViewLoginStatus.setText("Ověřuji uživatele");
        textViewLoginStatus.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

        textViewNameOfUser.setText(username);
        textViewLoginWithCredentials.setVisibility(View.GONE);


        String email = username.replace("@","_").replace(".","_");
        Log.e(TAG, "___________________" + email);


        mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/" + email + "/adminstračníInfo/AccessKey");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue(String.class).compareTo(password) == 0){

                    Log.e(TAG, "___________________" + " anooooooooo");
                    // otevři průvodce nastavením android aplikací (zabezpečení, vzhled, notifikace - notifikace stavu baterií, notifikace offlinosti senzorů, notifikace událostí
                    // notifikace narozenin a svátků.......)

                    mDatabaseReference = mFirebaseDatabase.getReference("TeleInfo/Administration/Users/" + email + "/adminstračníInfo");

                    mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            Log.e(TAG, "___________________" + dataSnapshot.child("PairTimeTable").getValue(String.class) );
                            Log.e(TAG, "___________________" + dataSnapshot.child("role").getValue(int.class) );


                            Intent myIntent = new Intent(getContext(), MainActivity.class);
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(myIntent);
                            getActivity().finish();


                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }else{

                    Log.e(TAG, "___________________" + "neeeeeeee");
                    Toast.makeText(getContext(),"klíč nenení správný",Toast.LENGTH_LONG).show();

                    textViewNameOfUser.setVisibility(View.GONE);
                    textViewLoginStatus.setVisibility(View.VISIBLE);
                    aVLoadingIndicatorViewLogging.setVisibility(View.GONE);

                    textViewLoginStatus.setText("Ověření se nazdařilo\n\nEmail nebo heslo není správné");
                    textViewLoginStatus.setTextColor(getContext().getResources().getColor(R.color.red700colorAccent));
                    textViewNameOfUser.setText("");
                    textViewLoginWithCredentials.setVisibility(View.VISIBLE);

                }




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return true;
    }



    public void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                mTimeLeftInMillis = millisUntilFinished;
                textViewFingerprintTextStatus.setVisibility(View.VISIBLE);
                textViewFingerprintTextStatus.setText("kokot");
                updateCountDownText();

               //textViewFingerprintTextStatus.setVisibility(View.GONE);
               //imageViewFingerprintStatus.setVisibility(View.GONE);




            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFinish() {

                Log.e("mylog", "??????????????: oooooooooooo");

                initFingerprint();
                onAuthenticationFailedCount=0;
                textViewFingerprintTextStatus.setVisibility(View.GONE);

                textViewFingerprintTextStatus.setText("");

                textViewFingerprintTextStatus.setVisibility(View.VISIBLE);


                Log.e("klmmmmmmmmmmmmmm", "konec textViewFingerprintTextStatusu");
                //mTimerRunning = false;
                //mButtonStartPause.setText("Start");
                //mButtonStartPause.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        //mTimerRunning = true;
        //mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);

        textViewFingerprintTextStatus.setText("Čtečka otisku prstů je zablokována. K obnovení dojde za \n" + timeLeftFormatted + " sekund");
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initFingerprint(){

        fingerprintHandler = new FingerprintHandler(getContext());
        fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);

        // check support for android fingerprint on device
        checkDeviceFingerprintSupport();
        //generate fingerprint keystore
        generateFingerprintKeyStore();
        //instantiate Cipher class
        Cipher mCipher = instantiateCipher();
        if(mCipher != null){
            cryptoObject = new FingerprintManager.CryptoObject(mCipher);
        }

        fingerprintHandler.completeFingerAuthentication(fingerprintManager, cryptoObject);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkDeviceFingerprintSupport() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.USE_FINGERPRINT}, REQUEST_USE_FINGERPRINT);

        } else {
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(getContext(), "Fingerprint is not supported in this device", Toast.LENGTH_LONG).show();
            }
            if (!fingerprintManager.hasEnrolledFingerprints()) {

                textViewFingerprintTextStatus.setText("Otisk prstu ještěnení nakonfigurován");

                textViewFingerprintTextStatus.setVisibility(View.GONE);
                imageViewFingerprintStatus.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Fingerprint not yet configured", Toast.LENGTH_LONG).show();
            }
            if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(getContext(), "Screen lock is not secure and enable", Toast.LENGTH_LONG).show();
            }
            return;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generateFingerprintKeyStore(){
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            keyGenerator.init(new KeyGenParameterSpec.Builder(FINGERPRINT_KEY, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        keyGenerator.generateKey();
    }

    private Cipher instantiateCipher(){
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            keyStore.load(null);
            SecretKey secretKey = (SecretKey)keyStore.getKey(FINGERPRINT_KEY, null);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnrecoverableKeyException |
                CertificateException | IOException | KeyStoreException | InvalidKeyException e) {
            throw new RuntimeException("Failed to instantiate Cipher class");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_USE_FINGERPRINT){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // check support for android fingerprint on device
                checkDeviceFingerprintSupport();
                //generate fingerprint keystore
                generateFingerprintKeyStore();
                //instantiate Cipher class
                Cipher mCipher = instantiateCipher();
                if(mCipher != null){
                    cryptoObject = new FingerprintManager.CryptoObject(mCipher);
                }
            }
            else{
                //Toast.makeText(this, R.string.permission_refused, Toast.LENGTH_LONG).show();
            }
        }else{
            // Toast.makeText(this, getString(R.string.Unknown_permission_request), Toast.LENGTH_LONG).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{

        private final String TAG = FingerprintHandler.class.getSimpleName();

        private Context context;

        public FingerprintHandler(Context context){
            this.context = context;
        }

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Log.d(TAG, "Error message " + errorCode + ": " + errString);
            //Toast.makeText(context, context.getString(R.string.authenticate_fingerprint), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
            //Toast.makeText(context, R.string.auth_successful, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);

            login();

            Vibrator vibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate(200);
            }

            //textViewReply.setText(getResources().getString(R.string.loginWithFingerprint_TextView_ReplySuccess));
            imageViewFingerprintStatus.setImageResource(R.drawable.success);
            imageViewFingerprintStatus.setVisibility(View.VISIBLE);
            textViewFingerprintTextStatus.setAllCaps(true);
            textViewFingerprintTextStatus.setTextColor(getActivity().getResources().getColor(R.color.green500colorPrimary));
            textViewFingerprintTextStatus.setText("Otisk prstu ověřen");

            isVerify = true;
            
            // TODO ... 
            
            //finish();
            //startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();

            Log.e("mylog", "??????????????:oppppppppppppppp");


            onAuthenticationFailedCount++;

            if(onAuthenticationFailedCount >= 4){



                mEditor.putLong(TIME_OF_LAST_BLOCKED_READER, System.currentTimeMillis());
                mEditor.commit();

                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                startTimer();
                Log.e("mylog", "??????????????: aaaaaaaaaaaaa");
            }

            textViewFingerprintTextStatus.setText("Počet zbývajících pokusů: " + (4-onAuthenticationFailedCount));
            textViewFingerprintTextStatus.setVisibility(View.VISIBLE);
            textViewFingerprintTextStatus.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewFingerprintTextStatus.setVisibility(View.GONE);
                }
            }, 1500);


            /*textViewLoginByCredentials.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginByCredentials.class));

                }
            });*/

            // textViewReply.setText(getResources().getString(R.string.loginWithFingerprint_TextView_ReplyNegative));

            imageViewFingerprintStatus.setImageResource(R.drawable.failure);
            // final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_pf);
            // imageViewFingerprintStatus.startAnimation(animShake);
            imageViewFingerprintStatus.setVisibility(View.VISIBLE);
            imageViewFingerprintStatus.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // imageViewFingerprintStatus.setImageResource(R.drawable.ic_fingerprint);
                    // imageViewFingerprintStatus.getDrawable().setColorFilter(getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP );
                    //textViewReply.setText("");
                    imageViewFingerprintStatus.setVisibility(View.GONE);


                }
            }, 1500);


        }



        public void completeFingerAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            try{
                fingerprintManager.authenticate(cryptoObject, new CancellationSignal(), 0, this, null);
            }catch (SecurityException ex) {
                Log.d(TAG, "An error occurred:\n" + ex.getMessage());
            } catch (Exception ex) {
                Log.d(TAG, "An error occurred\n" + ex.getMessage());
            }
        }
    }

}





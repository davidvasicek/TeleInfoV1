package com.example.teleinfo.guide;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;
import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_FINGERPRINT;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.TIME_OF_LAST_BLOCKED_READER;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;

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


public class c_a_GuideSecurityFingerprintFragment extends Fragment {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    TextView textViewInstructions;
    TextView textViewFingerprintStatus;
    TextView textViewFingerprintStatus1;
    ImageView imageViewFingerprintIcon;
    ImageView imageViewFingertprintStatus;
    Button buttonPrevious;
    Button buttonNext;




    String numberOfPage = "";

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

    private static final long START_TIME_IN_MILLIS = 90000;


    private CountDownTimer mCountDowntextViewFingerprintStatus;


    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;











    public interface OnGuideOptionClickListener {
        void onGuideOptionClickListener(String keyOption, int keyOptions, String valueOptions);
    }

    private OnGuideOptionClickListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnGuideOptionClickListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement SettingOptionsFragment.OnOptionClickListener");
        }
    }

    public c_a_GuideSecurityFingerprintFragment(){

    }

    public static c_a_GuideSecurityFingerprintFragment newInstance(int counter, int totalCount) {
        c_a_GuideSecurityFingerprintFragment myFragment = new c_a_GuideSecurityFingerprintFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide_fragment_auth_by_fingerprint, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

            numberOfPage = counter + " / " + totalCount;

        }

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        
        textViewInstructions = (TextView)rootView.findViewById(R.id.guideFragmentAuthByFingerprintTextViewInstructions);
        textViewFingerprintStatus = (TextView)rootView.findViewById(R.id.guideFragmentAuthByFingerprintTextViewFingerprintStatus);
        textViewFingerprintStatus1 = (TextView)rootView.findViewById(R.id.guideFragmentAuthByFingerprintTextViewFingerprintStatus1);
        imageViewFingerprintIcon = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByFingerprintImageViewFingerprintIcon);
        imageViewFingertprintStatus = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByFingerprintImageViewFingertprintStatus);
        buttonPrevious = (Button)rootView.findViewById(R.id.guideFragmentAuthByFingerprintButtonPrevious);
        buttonNext = (Button)rootView.findViewById(R.id.guideFragmentAuthByFingerprintButtonNext);
        
        imageViewFingertprintStatus.setVisibility(View.GONE);
        textViewFingerprintStatus.setVisibility(View.GONE);
        textViewFingerprintStatus1.setVisibility(View.GONE);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToNotificationsFragment",  LOGIN_BY_FINGERPRINT, "");
            }
        });
        buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
        buttonNext.setEnabled(false);

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToAuthPriorityFragment", -1, null);
            }
        });
        
        Long lastTime = mSharedPreferences.getLong(TIME_OF_LAST_BLOCKED_READER, 0);

        if(System.currentTimeMillis()-lastTime >90000){

            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            initFingerprint();

        }else{

            mTimeLeftInMillis = 90000-((System.currentTimeMillis()-lastTime));


            starttextViewFingerprintStatus();
        }


        return rootView;
    }
    
    public void starttextViewFingerprintStatus() {

        mCountDowntextViewFingerprintStatus = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                textViewFingerprintStatus.setVisibility(View.VISIBLE);
                updateCountDownText();

                textViewFingerprintStatus1.setVisibility(View.GONE);
                //sensorViewMainFragmentChartImageViewGraphLinesf.setVisibility(View.GONE);
                imageViewFingertprintStatus.setVisibility(View.GONE);




            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFinish() {

                initFingerprint();
                onAuthenticationFailedCount=0;
                textViewFingerprintStatus.setVisibility(View.GONE);



                textViewFingerprintStatus1.setVisibility(View.VISIBLE);
                //sensorViewMainFragmentChartImageViewGraphLinesf.setVisibility(View.VISIBLE);


                Log.e("klmmmmmmmmmmmmmm", "konec textViewFingerprintStatusu");
                //mtextViewFingerprintStatusRunning = false;
                //mButtonStartPause.setText("Start");
                //mButtonStartPause.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        //mtextViewFingerprintStatusRunning = true;
        //mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);

        textViewFingerprintStatus.setText("Čtečka otisku prstů je zablokována. K obnovení dojde za \n" + timeLeftFormatted + " sekund");
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

                textViewFingerprintStatus.setText("Otisk prstu ještěnení nakonfigurován");

                textViewFingerprintStatus1.setVisibility(View.GONE);
                //sensorViewMainFragmentChartImageViewGraphLinesf.setVisibility(View.GONE);
                imageViewFingertprintStatus.setVisibility(View.GONE);

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

            Vibrator vibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate(200);
            }

            //textViewReply.setText(getResources().getString(R.string.loginWithFingerprint_TextView_ReplySuccess));
            imageViewFingertprintStatus.setImageResource(R.drawable.success);
            imageViewFingertprintStatus.setVisibility(View.VISIBLE);
            textViewFingerprintStatus1.setAllCaps(true);
            textViewFingerprintStatus1.setTextColor(getActivity().getResources().getColor(R.color.green500text_primary));
            textViewFingerprintStatus1.setText("Otisk prstu ověřen");

            isVerify = true;

            buttonNext.setEnabled(true);
            buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_primary));


            //finish();
            //startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();

            onAuthenticationFailedCount++;

            if(onAuthenticationFailedCount >= 4){



                        mEditor.putLong(TIME_OF_LAST_BLOCKED_READER, System.currentTimeMillis());
                        mEditor.commit();

                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                starttextViewFingerprintStatus();
            }

            textViewFingerprintStatus.setText("Počet zbývajících pokusů: " + (5-onAuthenticationFailedCount));
            textViewFingerprintStatus.setVisibility(View.VISIBLE);
            textViewFingerprintStatus.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewFingerprintStatus.setVisibility(View.GONE);
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

            imageViewFingertprintStatus.setImageResource(R.drawable.failure);
           // final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_pf);
           // imageViewFingertprintStatus.startAnimation(animShake);
            imageViewFingertprintStatus.setVisibility(View.VISIBLE);
            imageViewFingertprintStatus.postDelayed(new Runnable() {
                @Override
                public void run() {
                   // imageViewFingertprintStatus.setImageResource(R.drawable.ic_fingerprint);
                   // imageViewFingertprintStatus.getDrawable().setColorFilter(getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP );
                    //textViewReply.setText("");
                    imageViewFingertprintStatus.setVisibility(View.GONE);


                }
            }, 600);


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
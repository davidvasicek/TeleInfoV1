package com.example.teleinfo.login;

import static com.example.teleinfo.parameters.MainParameters.LOGGED_USER;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teleinfo.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewSubtitle;
    TextView textViewPasswordStrengthIndicatorText;
    TextView textViewPasswordText;
    TextView textViewPasswordStrengthMatchesText;
    TextInputEditText textInputEditTextPasswordInput;
    TextInputEditText textInputEditTextPasswordInput1;
    Button buttonNextToPasswordRepeat;
    Button buttonShow;
    Button buttonBack;
    Button buttonLogin;
    LinearLayout linearLayoutNewPassword;
    LinearLayout linearLayoutPasswordRequirements;
    LinearLayout linearLayoutPasswordStrengthIndicator;
    ImageView imageViewPasswordRequirementsEightCharacters;
    ImageView imageViewPasswordRequirementsLowerCaseLetter;
    ImageView imageViewPasswordRequirementsCapitalLetter;
    ImageView imageViewPasswordRequirementsNumber;
    ImageView imageViewPasswordRequirementsSpecialCharacter;
    ImageView imageViewPasswordRequirementsNoSpace;
    LinearLayout linearLayoutRepeatPassword;
    LinearLayout linearLayoutPasswordMatches;
    LinearLayout linearLayoutPasswordMatchesIndicator;

    String email;
    boolean passwordIsGeneretedByApp;
    String password;

    String passwordEditText;
    String passwordRepeatEditText;

    private LinearLayout linearLayoutsCharacters[];

    boolean passwordRequirementsEightCharacters = false;
    boolean passwordRequirementsLowerCaseLetter = false;
    boolean passwordRequirementsCapitalLetter = false;
    boolean passwordRequirementsNumber = false;
    boolean passwordRequirementsSpecialCharacter = false;
    boolean passwordRequirementsNoSpace = false;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_change_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textViewTitle = (TextView)findViewById(R.id.loginWithCredentialsActivityTextViewTitle);
        textViewSubtitle = (TextView)findViewById(R.id.loginWithCredentialsActivityTextViewSubtitle);
        textViewPasswordStrengthIndicatorText = (TextView)findViewById(R.id.login_fragmentAuthenticationPasswordTextViewPasswordStrengthIndicatorText);
        textViewPasswordText = (TextView)findViewById(R.id.login_fragmentAuthenticationPasswordTextViewPasswordText);
        textViewPasswordStrengthMatchesText = (TextView)findViewById(R.id.login_fragmentAuthenticationPasswordTextViewPasswordStrengthMatchesText);
        textInputEditTextPasswordInput = (TextInputEditText)findViewById(R.id.login_fragmentAuthenticationPasswordTextInputEditTextPasswordInput);
        textInputEditTextPasswordInput1 = (TextInputEditText)findViewById(R.id.login_fragmentAuthenticationPasswordTextInputEditTextPasswordInput1);
        buttonNextToPasswordRepeat = (Button)findViewById(R.id.login_fragmentAuthenticationPasswordButtonNextToPasswordRepeat);
        buttonShow = (Button)findViewById(R.id.login_fragmentAuthenticationPasswordButtonShow);
        buttonBack = (Button)findViewById(R.id.login_fragmentAuthenticationPasswordButtonBack);
        buttonLogin = (Button)findViewById(R.id.login_fragmentAuthenticationPasswordButtonLogin);
        linearLayoutNewPassword = (LinearLayout)findViewById(R.id.login_fragmentAuthenticationPasswordLinearLayoutNewPassword);
        linearLayoutPasswordRequirements = (LinearLayout)findViewById(R.id.login_fragmentAuthenticationPasswordLinearLayoutPasswordRequirements);
        linearLayoutPasswordStrengthIndicator = (LinearLayout)findViewById(R.id.login_fragmentAuthenticationPasswordLinearLayoutPasswordStrengthIndicator);
        imageViewPasswordRequirementsEightCharacters = (ImageView)findViewById(R.id.login_fragmentAuthenticationPasswordImageViewPasswordRequirementsEightCharacters);
        imageViewPasswordRequirementsLowerCaseLetter = (ImageView)findViewById(R.id.login_fragmentAuthenticationPasswordImageViewPasswordRequirementsLowerCaseLetter);
        imageViewPasswordRequirementsCapitalLetter = (ImageView)findViewById(R.id.login_fragmentAuthenticationPasswordImageViewPasswordRequirementsCapitalLetter);
        imageViewPasswordRequirementsNumber = (ImageView)findViewById(R.id.login_fragmentAuthenticationPasswordImageViewPasswordRequirementsNumber);
        imageViewPasswordRequirementsSpecialCharacter = (ImageView)findViewById(R.id.login_fragmentAuthenticationPasswordImageViewPasswordRequirementsSpecialCharacter);
        imageViewPasswordRequirementsNoSpace = (ImageView)findViewById(R.id.login_fragmentAuthenticationPasswordImageViewPasswordRequirementsNoSpace);
        linearLayoutRepeatPassword = (LinearLayout)findViewById(R.id.login_fragmentAuthenticationPasswordLinearLayoutRepeatPassword);
        linearLayoutPasswordMatches = (LinearLayout)findViewById(R.id.login_fragmentAuthenticationPasswordLinearLayoutPasswordMatches);
        linearLayoutPasswordMatchesIndicator = (LinearLayout)findViewById(R.id.login_fragmentAuthenticationPasswordLinearLayoutPasswordMatchesIndicator);

        textViewPasswordStrengthIndicatorText.setText("Žádné");
        linearLayoutPasswordStrengthIndicator.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.text_secondary));

        imageViewPasswordRequirementsEightCharacters.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsLowerCaseLetter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsCapitalLetter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsNumber.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsSpecialCharacter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsNoSpace.setImageResource(R.drawable.failure);

        buttonNextToPasswordRepeat.setVisibility(View.GONE);

        linearLayoutNewPassword.setVisibility(View.VISIBLE);
        linearLayoutRepeatPassword.setVisibility(View.GONE);

        textInputEditTextPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Calculate password strength
                calculateStrength(editable.toString());

                int passwordLength = editable.toString().length();

                calculateMatches(passwordLength);

                passwordEditText = editable.toString();

                textInputEditTextPasswordInput1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(passwordLength) });
            }
        });


        textInputEditTextPasswordInput1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int passwordRepeatLength = editable.toString().length();

                passwordRepeatEditText = editable.toString();
                char p, rp;
                int countOfCorrectCharacter = 0;


                for (int i = 0; i < passwordEditText.length(); i++) {


                    if (i < passwordRepeatLength) {

                        p = passwordEditText.charAt(i);
                        rp = passwordRepeatEditText.charAt(i);


                        if (p == rp) {

                            linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.green500text_primary));
                            countOfCorrectCharacter++;


                        } else {

                            linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.red700colorAccent));


                        }


                    } else {

                        linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.text_secondary));
                    }

                }


                textViewPasswordStrengthMatchesText.setText((countOfCorrectCharacter * 100) / passwordEditText.length() + "%");

                if(passwordEditText.compareTo(passwordRepeatEditText) == 0){


                    buttonLogin.setVisibility(View.VISIBLE);
                }
                else{

                    buttonLogin.setVisibility(View.GONE);

                }


            }
        });



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();

                mSharedPreferences.getString(LOGGED_USER, "");





            }
        });



        buttonShow.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN ) {

                    StringBuilder mCode;
                    mCode = new StringBuilder(100);

                    for(int i = 0; i< passwordEditText.length(); i++){

                        char c = passwordEditText.charAt(i);
                        mCode.append(c);
                        mCode.append(" ");
                    }

                    textViewPasswordText.setText(mCode);

                } else

                if(event.getAction() == MotionEvent.ACTION_UP){

                    StringBuilder mCode;
                    mCode = new StringBuilder(100);

                    for(int i = 0; i< passwordEditText.length(); i++){

                        mCode.append("* ");
                    }

                    textViewPasswordText.setText(mCode);
                }
                return false;
            }
        });

        buttonNextToPasswordRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //textInputEditTextPasswordInput.clearFocus();
                //InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(getApplicationContext().getWindowToken(), 0);

                textViewSubtitle.setText("Zopakujte heslo, kterým se budete do aplikace autentizovat");

                linearLayoutNewPassword.setVisibility(View.GONE);
                linearLayoutRepeatPassword.setVisibility(View.VISIBLE);

                StringBuilder mCode;
                mCode = new StringBuilder(100);

                for(int i = 0; i< passwordEditText.length(); i++){

                    mCode.append("* ");
                }

                textViewPasswordText.setText(mCode);

                char p, rp;
                int countOfCorrectCharacter = 0;


                if(passwordRepeatEditText != null){

                    for (int i = 0; i < passwordEditText.length(); i++) {


                        if (i < passwordRepeatEditText.length()) {

                            p = passwordEditText.charAt(i);
                            rp = passwordRepeatEditText.charAt(i);


                            if (p == rp) {

                                linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.green500text_primary));
                                countOfCorrectCharacter++;

                            } else {

                                linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.red700colorAccent));

                            }


                        } else {

                            linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.text_secondary));
                        }
                    }


                }


            }
        });



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //textInputEditTextPasswordInput1.clearFocus();
                //InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);

                textViewSubtitle.setText("Nastavte Vaše nové heslo, kterým se budete do aplikace autentizovat");

                linearLayoutNewPassword.setVisibility(View.VISIBLE);
                linearLayoutRepeatPassword.setVisibility(View.GONE);


            }
        });

    }


    private void calculateMatches(int numberOfCharacters) {

        linearLayoutPasswordMatchesIndicator.removeAllViews();
        linearLayoutsCharacters = new LinearLayout[numberOfCharacters];


        LinearLayout.LayoutParams lpSections = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f); // Height of TextView
        lpSections.setMargins(15, 0, 15, 0);



        for(int i = 0; i<numberOfCharacters; i++){

            linearLayoutsCharacters[i] = new LinearLayout(getApplicationContext());

            linearLayoutsCharacters[i].setLayoutParams(lpSections);
            linearLayoutsCharacters[i].setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutsCharacters[i].setBackgroundColor(getApplicationContext().getResources().getColor(R.color.text_secondary));
            linearLayoutPasswordMatchesIndicator.addView(linearLayoutsCharacters[i]);
        }

    }

    private void calculateStrength(String passwordText) {
        int upperChars = 0, lowerChars = 0, numbers = 0,
                specialChars = 0, otherChars = 0, strengthPoints = 0;
        char c;

        int passwordLength = passwordText.length();


        if (passwordLength == 0)
        {
            textViewPasswordStrengthIndicatorText.setText("Žádné");
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.text_secondary));

            imageViewPasswordRequirementsCapitalLetter.setImageResource(R.drawable.failure);
            imageViewPasswordRequirementsLowerCaseLetter.setImageResource(R.drawable.failure);
            imageViewPasswordRequirementsNumber.setImageResource(R.drawable.failure);
            imageViewPasswordRequirementsSpecialCharacter.setImageResource(R.drawable.failure);
            imageViewPasswordRequirementsNoSpace.setImageResource(R.drawable.failure);

            return;
        }

        //If password length is <= 5 set strengthPoints=1
        if (passwordLength <= 5) {
            strengthPoints =1;
        }
        //If password length is >5 and <= 10 set strengthPoints=2
        else if (passwordLength <= 10) {
            strengthPoints = 2;
        }
        //If password length is >10 set strengthPoints=3
        else
            strengthPoints = 3;


        if (passwordLength >= 8) {
            imageViewPasswordRequirementsEightCharacters.setImageResource(R.drawable.success);
            passwordRequirementsEightCharacters = true;
        }else{
            imageViewPasswordRequirementsEightCharacters.setImageResource(R.drawable.failure);
            passwordRequirementsEightCharacters = false;
        }



        imageViewPasswordRequirementsCapitalLetter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsLowerCaseLetter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsNumber.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsSpecialCharacter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsNoSpace.setImageResource(R.drawable.failure);

        passwordRequirementsLowerCaseLetter  = false;
        passwordRequirementsCapitalLetter = false;
        passwordRequirementsNumber = false;
        passwordRequirementsSpecialCharacter = false;
        passwordRequirementsNoSpace  = false;

        int numOfSpace = 0;

        // Loop through the characters of the password
        for (int i = 0; i < passwordLength; i++) {
            c = passwordText.charAt(i);



            if(c == ' '){

                numOfSpace++;
            }

            if(numOfSpace == 0){

                imageViewPasswordRequirementsNoSpace.setImageResource(R.drawable.success);
                passwordRequirementsNoSpace = true;

            }else{

                imageViewPasswordRequirementsNoSpace.setImageResource(R.drawable.failure);
                passwordRequirementsNoSpace = false;

            }


            // If password contains lowercase letters
            // then increase strengthPoints by 1
            if (c >= 'a' && c <= 'z') {
                if (lowerChars == 0) strengthPoints++;
                lowerChars = 1;
                imageViewPasswordRequirementsLowerCaseLetter.setImageResource(R.drawable.success);
                passwordRequirementsLowerCaseLetter = true;
            }
            // If password contains uppercase letters
            // then increase strengthPoints by 1
            else if (c >= 'A' && c <= 'Z') {
                if (upperChars == 0) strengthPoints++;
                upperChars = 1;
                imageViewPasswordRequirementsCapitalLetter.setImageResource(R.drawable.success);
                passwordRequirementsCapitalLetter = true;
            }
            // If password contains numbers
            // then increase strengthPoints by 1
            else if (c >= '0' && c <= '9') {
                if (numbers == 0) strengthPoints++;
                numbers = 1;
                imageViewPasswordRequirementsNumber.setImageResource(R.drawable.success);
                passwordRequirementsNumber = true;
            }
            // If password contains _ or @
            // then increase strengthPoints by 1
            else if (c == '_' || c == '@') {
                if (specialChars == 0) strengthPoints += 1;
                specialChars = 1;
                imageViewPasswordRequirementsSpecialCharacter.setImageResource(R.drawable.success);
                passwordRequirementsSpecialCharacter = true;
            }


            // If password contains any other special chars
            // then increase strengthPoints by 1
            else {

                if(c != ' '){

                    if (otherChars == 0) strengthPoints += 2;
                    otherChars = 1;
                    imageViewPasswordRequirementsSpecialCharacter.setImageResource(R.drawable.success);
                    passwordRequirementsSpecialCharacter = true;

                }
            }



        }

        if (strengthPoints <= 3)
        {
            textViewPasswordStrengthIndicatorText.setText("Nízké");
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.red700colorAccent));
        }
        else if (strengthPoints <= 6) {
            textViewPasswordStrengthIndicatorText.setText("Střední");
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.amber500colorAccent));
        }
        else if (strengthPoints <= 9){
            textViewPasswordStrengthIndicatorText.setText("Vysoké");
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.green500text_primary));
        }


        if(passwordRequirementsEightCharacters && passwordRequirementsLowerCaseLetter && passwordRequirementsCapitalLetter && passwordRequirementsNumber && passwordRequirementsSpecialCharacter && passwordRequirementsNoSpace){

            buttonNextToPasswordRepeat.setVisibility(View.VISIBLE);

        }else{

            buttonNextToPasswordRepeat.setVisibility(View.GONE);
        }

    }



}
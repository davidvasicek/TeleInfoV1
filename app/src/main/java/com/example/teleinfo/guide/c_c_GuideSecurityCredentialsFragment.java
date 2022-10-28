package com.example.teleinfo.guide;



import static com.example.teleinfo.parameters.MainParameters.LOGIN_BY_CREDENTIALS;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;
import com.google.android.material.textfield.TextInputEditText;

public class c_c_GuideSecurityCredentialsFragment extends Fragment {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    TextView textViewPasswordStrengthIndicatorText;
    TextView textViewPasswordStrengthMatchesText;
    ImageView imageViewPasswordRequirementsEightCharacters;
    ImageView imageViewPasswordRequirementsLowerCaseLetter;
    ImageView imageViewPasswordRequirementsCapitalLetter;
    ImageView imageViewPasswordRequirementsNumber;
    ImageView imageViewPasswordRequirementsSpecialCharacter;
    ImageView imageViewPasswordRequirementsNoSpace;
    TextInputEditText textInputEditTextPasswordInput;
    TextInputEditText textInputEditTextPasswordInputRepeat;
    LinearLayout linearLayoutPasswordRequirements;
    LinearLayout linearLayoutPasswordStrengthIndicator;
    LinearLayout linearLayoutPasswordRequirementsEightCharacters;
    LinearLayout linearLayoutPasswordRequirementsLowerCaseLetter;
    LinearLayout linearLayoutPasswordRequirementsCapitalLetter;
    LinearLayout linearLayoutPasswordRequirementsNumber;
    LinearLayout linearLayoutPasswordRequirementsSpecialCharacter;
    LinearLayout linearLayoutPasswordRequirementsNoSpace;
    LinearLayout linearLayoutPasswordMatches;
    LinearLayout linearLayoutPasswordMatchesIndicator;
    Button buttonPrevious;
    Button buttonNext;




    String passwordEditText;
    String passwordRepeatEditText;

    private LinearLayout linearLayoutsCharacters[];

    boolean passwordRequirementsEightCharacters = false;
    boolean passwordRequirementsLowerCaseLetter = false;
    boolean passwordRequirementsCapitalLetter = false;
    boolean passwordRequirementsNumber = false;
    boolean passwordRequirementsSpecialCharacter = false;
    boolean passwordRequirementsNoSpace = false;



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

    public c_c_GuideSecurityCredentialsFragment(){

    }

    public static c_c_GuideSecurityCredentialsFragment newInstance(int counter, int totalCount) {
        c_c_GuideSecurityCredentialsFragment myFragment = new c_c_GuideSecurityCredentialsFragment();

        Bundle args = new Bundle();
        args.putInt("count", counter);
        args.putInt("totalCount", totalCount);
        myFragment.setArguments(args);

        return myFragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.guide_fragment_auth_by_credentials, container, false);

        if (getArguments() != null) {

            int counter = getArguments().getInt("count");
            int totalCount = getArguments().getInt("totalCount");

        }

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        textViewPasswordStrengthIndicatorText = (TextView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsTextViewPasswordStrengthIndicatorText);
        textViewPasswordStrengthMatchesText = (TextView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsTextViewPasswordStrengthMatchesText);
        imageViewPasswordRequirementsEightCharacters = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsImageViewPasswordRequirementsEightCharacters);
        imageViewPasswordRequirementsLowerCaseLetter = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsImageViewPasswordRequirementsLowerCaseLetter);
        imageViewPasswordRequirementsCapitalLetter = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsImageViewPasswordRequirementsCapitalLetter);
        imageViewPasswordRequirementsNumber = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsImageViewPasswordRequirementsNumber);
        imageViewPasswordRequirementsSpecialCharacter = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsImageViewPasswordRequirementsSpecialCharacter);
        imageViewPasswordRequirementsNoSpace = (ImageView)rootView.findViewById(R.id.guideFragmentAuthByCredentialsImageViewPasswordRequirementsNoSpace);
        textInputEditTextPasswordInput = (TextInputEditText)rootView.findViewById(R.id.guideFragmentAuthByCredentialsTextInputEditTextPasswordInput);
        textInputEditTextPasswordInputRepeat = (TextInputEditText)rootView.findViewById(R.id.guideFragmentAuthByCredentialsTextInputEditTextPasswordInputRepeat);
        linearLayoutPasswordRequirements = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirements);
        linearLayoutPasswordStrengthIndicator = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordStrengthIndicator);
        linearLayoutPasswordRequirementsEightCharacters = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirementsEightCharacters);
        linearLayoutPasswordRequirementsLowerCaseLetter = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirementsLowerCaseLetter);
        linearLayoutPasswordRequirementsCapitalLetter = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirementsCapitalLetter);
        linearLayoutPasswordRequirementsNumber = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirementsNumber);
        linearLayoutPasswordRequirementsSpecialCharacter = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirementsSpecialCharacter);
        linearLayoutPasswordRequirementsNoSpace = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordRequirementsNoSpace);
        linearLayoutPasswordMatches = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordMatches);
        linearLayoutPasswordMatchesIndicator = (LinearLayout)rootView.findViewById(R.id.guideFragmentAuthByCredentialsLinearLayoutPasswordMatchesIndicator);
        buttonPrevious = (Button)rootView.findViewById(R.id.guideFragmentAuthByCredentialsButtonPrevious);
        buttonNext = (Button)rootView.findViewById(R.id.guideFragmentAuthByCredentialsButtonNext);

        textInputEditTextPasswordInputRepeat.setEnabled(false);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onGuideOptionClickListener("changeToNotificationsFragment", LOGIN_BY_CREDENTIALS, passwordEditText);
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


        textViewPasswordStrengthIndicatorText.setText("Žádné");
        linearLayoutPasswordStrengthIndicator.setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));

        imageViewPasswordRequirementsEightCharacters.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsLowerCaseLetter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsCapitalLetter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsNumber.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsSpecialCharacter.setImageResource(R.drawable.failure);
        imageViewPasswordRequirementsNoSpace.setImageResource(R.drawable.failure);
        
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

                textInputEditTextPasswordInputRepeat.setFilters(new InputFilter[] { new InputFilter.LengthFilter(passwordLength) });
            }
        });


        textInputEditTextPasswordInputRepeat.addTextChangedListener(new TextWatcher() {
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

                            linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.green500colorPrimary));
                            countOfCorrectCharacter++;


                        } else {

                            linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.red700colorAccent));


                        }


                    } else {

                        linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
                    }

                }


                textViewPasswordStrengthMatchesText.setText((countOfCorrectCharacter * 100) / passwordEditText.length() + "%");

                if(passwordEditText.compareTo(passwordRepeatEditText) == 0){

                    buttonNext.setEnabled(true);
                    buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_primary));
                }
                else{

                    buttonNext.setEnabled(false);
                    buttonNext.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

                }


            }
        });






/*
        buttonNextToPasswordRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //textInputEditTextPasswordInput.clearFocus();
                //InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(getContext().getWindowToken(), 0);

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

                                linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.green500colorPrimary));
                                countOfCorrectCharacter++;

                            } else {

                                linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.red700colorAccent));

                            }


                        } else {

                            linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
                        }
                    }


                }


            }
        });
*/



        

        return rootView;
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

            linearLayoutsCharacters[i] = new LinearLayout(getContext());

            linearLayoutsCharacters[i].setLayoutParams(lpSections);
            linearLayoutsCharacters[i].setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutsCharacters[i].setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));
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
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getContext().getResources().getColor(R.color.text_secondary));

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
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getContext().getResources().getColor(R.color.red700colorAccent));
        }
        else if (strengthPoints <= 6) {
            textViewPasswordStrengthIndicatorText.setText("Střední");
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getContext().getResources().getColor(R.color.amber500colorAccent));
        }
        else if (strengthPoints <= 9){
            textViewPasswordStrengthIndicatorText.setText("Vysoké");
            linearLayoutPasswordStrengthIndicator.setBackgroundColor(getContext().getResources().getColor(R.color.green500colorPrimary));
        }


        if(passwordRequirementsEightCharacters && passwordRequirementsLowerCaseLetter && passwordRequirementsCapitalLetter && passwordRequirementsNumber && passwordRequirementsSpecialCharacter && passwordRequirementsNoSpace){

            textInputEditTextPasswordInputRepeat.setEnabled(true);

        }else{

            textInputEditTextPasswordInputRepeat.setEnabled(false);
        }

    }



}
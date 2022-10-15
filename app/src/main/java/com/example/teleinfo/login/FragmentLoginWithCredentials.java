package com.example.teleinfo.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.teleinfo.R;

public class FragmentLoginWithCredentials extends Fragment {

    TextView textViewTitle;
    TextView textViewSubtitle;
    EditText editTextEmailInput;
    EditText editTextPasswordInput;
    TextView textViewResetChangePassword;
    Button buttonLogin;
    LinearLayout linearLayoutMainLinearLayout;

    private String password;
    private String email;

    public FragmentLoginWithCredentials() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.login_with_credentials_activity, container, false);

        textViewTitle = (TextView)rootView.findViewById(R.id.loginWithCredentialsActivityTextViewTitle);
        textViewSubtitle = (TextView)rootView.findViewById(R.id.loginWithCredentialsActivityTextViewSubtitle);
        editTextEmailInput = (EditText)rootView.findViewById(R.id.loginWithCredentialsActivityEditTextEmailInput);
        editTextPasswordInput = (EditText)rootView.findViewById(R.id.loginWithCredentialsActivityEditTextPasswordInput);
        textViewResetChangePassword = (TextView)rootView.findViewById(R.id.loginWithCredentialsActivityTextViewResetChangePassword);
        buttonLogin = (Button)rootView.findViewById(R.id.loginWithCredentialsActivityButtonLogin);
        linearLayoutMainLinearLayout = (LinearLayout)rootView.findViewById(R.id.loginWithCredentialsActivityLinearLayoutMainLinearLayout);








        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password = editTextPasswordInput.getText().toString().trim();
                email = editTextEmailInput.getText().toString().trim();

                //checking if email and passwords are empty

                if(TextUtils.isEmpty(email)){
                    //Toast.makeText(getContext(),getResources().getString(R.string.loginWithCredentialsActivity_Warning_EmailInputEmpty),Toast.LENGTH_LONG).show();

                    return;
                }

                if(!email.contains("@") || !email.contains(".") || email.contains(" ")){
                   // Toast.makeText(getContext(),getResources().getString(R.string.loginWithCredentialsActivity_Warning_EmailInputNoEmailFormat),Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                   // Toast.makeText(getContext(),getResources().getString(R.string.loginWithCredentialsActivity_Warning_PasswordInputEmpty),Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        textViewResetChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editTextEmailInput.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                   // Toast.makeText(getContext(),getResources().getString(R.string.loginWithCredentialsActivity_Warning_EmailInputEmpty),Toast.LENGTH_LONG).show();

                    return;
                }

                if(!email.contains("@") || !email.contains(".") || email.contains(" ")){
                  //  Toast.makeText(getContext(),getResources().getString(R.string.loginWithCredentialsActivity_Warning_EmailInputNoEmailFormat),Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });

        return rootView;
    }






    boolean login(String username, String password) {


/*
        String email = username.replace("@","_").replace(".","_");
        Log.e(TAG, "___________________" + email);

        textViewNameOfUser.setVisibility(View.VISIBLE);
        textViewStatus.setVisibility(View.VISIBLE);
        aVLoadingIndicatorViewLogging.setVisibility(View.VISIBLE);

        textViewStatus.setText("Ověřuji uživatele");
        textViewStatus.setTextColor(getContext().getResources().getColor(R.color.text_secondary));

        textViewNameOfUser.setText(username);
        buttonQRScan.setVisibility(View.GONE);
        textViewLoginWithCredentials.setVisibility(View.GONE);
        textViewInfoText.setVisibility(View.GONE);

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


                            Intent myIntent = new Intent(getContext(), _MainActivityGuide.class);
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(myIntent);


                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }else{

                    Log.e(TAG, "___________________" + "neeeeeeee");
                    Toast.makeText(getContext(),"klíč nenení správný",Toast.LENGTH_LONG).show();

                    textViewNameOfUser.setVisibility(View.GONE);
                    textViewStatus.setVisibility(View.VISIBLE);
                    aVLoadingIndicatorViewLogging.setVisibility(View.GONE);

                    textViewStatus.setText("Ověření se nazdařilo\n\nEmail nebo heslo není správné");
                    textViewStatus.setTextColor(getContext().getResources().getColor(R.color.red700colorAccent));
                    textViewNameOfUser.setText("");
                    buttonQRScan.setVisibility(View.VISIBLE);
                    textViewLoginWithCredentials.setVisibility(View.VISIBLE);
                    textViewInfoText.setVisibility(View.VISIBLE);

                }




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

*/
        return true;
    }




}










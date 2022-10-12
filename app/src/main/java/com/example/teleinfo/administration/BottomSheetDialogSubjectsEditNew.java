package com.example.teleinfo.administration;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.ADMINISTRATION_EDIT;
import static com.example.teleinfo.parameters.MainParameters.ADMINISTRATION_NEW;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teleinfo.R;
import com.example.teleinfo.login.BottomSheetDialogCredentialsLogin;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class BottomSheetDialogSubjectsEditNew extends BottomSheetDialogFragment {

    TextView textViewTitle;
    TextInputEditText textInputEditTextShortcut;
    TextInputEditText textInputEditTextFullName;
    Button buttonSave;
    ThemedToggleButtonGroup themedToggleButtonGroupFieldOfStudy;
    ThemedButton themedButtonTag1;
    ThemedButton themedButtonTag2;
    ThemedButton themedButtonTag3;

    int action;
    ObjectSubjects objectSubjects;

    private BottomSheetDialogSubjectsEditNewListener bottomSheetDialogSubjectsEditNewListener;

    @Override
    public void onAttach(Context context) {

        if(getChildFragmentManager() == null){

            try{
                bottomSheetDialogSubjectsEditNewListener = (BottomSheetDialogSubjectsEditNewListener) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }else{
            try{
                bottomSheetDialogSubjectsEditNewListener = (BottomSheetDialogSubjectsEditNewListener) getChildFragmentManager();
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }
        super.onAttach(context);
    }



    public interface BottomSheetDialogSubjectsEditNewListener {
        void applyBottomSheetDialogSubjectsEditNewListener(ObjectSubjects objectSubjects, int action);
    }

    public BottomSheetDialogSubjectsEditNew(int action, ObjectSubjects objectSubjects) {

        this.action = action;
        this.objectSubjects = objectSubjects;

    }
    
   
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.administration_bottom_sheet_dialog_subjects_edit_new, null);
        dialog.setContentView(contentView);


        textViewTitle = (TextView)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewTextViewTitle);
        textInputEditTextShortcut = (TextInputEditText)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewTextInputEditTextShortcut);
        textInputEditTextFullName = (TextInputEditText)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewTextInputEditTextFullName);
        buttonSave = (Button)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewButtonSave);
        themedToggleButtonGroupFieldOfStudy = (ThemedToggleButtonGroup)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewThemedToggleButtonGroupFieldOfStudy);
        themedButtonTag1 = (ThemedButton)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewThemedButtonTag1);
        themedButtonTag2 = (ThemedButton)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewThemedButtonTag2);
        themedButtonTag3 = (ThemedButton)contentView.findViewById(R.id.administrationBottomSheetDialogSubjectEditNewThemedButtonTag3);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(textInputEditTextShortcut.getText().toString().trim())){

                    Toast.makeText(getContext(),"Zkrácený název předmětu je prázdný",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(textInputEditTextFullName.getText().toString().trim())){

                    Toast.makeText(getContext(),"Úpný název předmětu je prázdný",Toast.LENGTH_LONG).show();
                    return;
                }


                List<ThemedButton> selectedButtons = themedToggleButtonGroupFieldOfStudy.getSelectedButtons();


                if(selectedButtons.size() == 0){

                    Toast.makeText(getContext(),"Není vybrán žádný obor",Toast.LENGTH_LONG).show();
                    return;
                }


                ObjectSubjects objectSubjectsNew = new ObjectSubjects();
                objectSubjectsNew.Shortcut = textInputEditTextShortcut.getText().toString().trim();
                objectSubjectsNew.FullName = textInputEditTextFullName.getText().toString().trim();
                objectSubjectsNew.FieldOfStudy = selectedButtons.get(0).toString();

                if(action == ADMINISTRATION_EDIT){

                    objectSubjectsNew.Key = objectSubjects.Key;

                }

                bottomSheetDialogSubjectsEditNewListener.applyBottomSheetDialogSubjectsEditNewListener(objectSubjectsNew, action);


            }
        });


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }



}

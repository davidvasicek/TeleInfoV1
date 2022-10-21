package com.example.teleinfo.modules;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DATA;
import static com.example.teleinfo.parameters.MainParameters.INIT;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;
import static com.example.teleinfo.parameters.MainParameters.NO_DATA;
import static com.example.teleinfo.parameters.MainParameters.NO_INTERNET;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.example.teleinfo.parameters.IsOnline;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class ClassRooms_BottomSheetDialogClassRoomsList extends BottomSheetDialogFragment {


    private List<ObjectClassrooms> mObjectClassroomsList_original;
    private List<ObjectClassrooms> mObjectClassroomsList_edited;
    private MainSubjectsAdapter mMainClassroomsAdapter;

    TextView textViewMessageRow1;
    TextView textViewMessageRow2;
    RecyclerView recyclerView;
    AVLoadingIndicatorView aVLoadingIndicatorViewIndicator;
    LinearLayout linearLayoutFilterRow;
    ThemedToggleButtonGroup themedToggleButtonGroupFilter;
    Button buttonRefrest;




    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener listener;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    int selectedIndexOfSorting;
    String selectedLocationOfRoom;


    String selectedButton = "MainLights";

  

    public ClassRooms_BottomSheetDialogClassRoomsList() {

      

    }
    
   
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.classrooms_bottom_sheet_dialog_classrooms_list, null);
        dialog.setContentView(contentView);

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("TeleInfo/Administration/Ucebny");





        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        //mSharedPreferences.registerOnSharedPreferenceChangeListener(this);



        textViewMessageRow1 = (TextView)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListTextViewMessageRow1);
        textViewMessageRow2 = (TextView)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListTextViewMessageRow2);
        recyclerView = (RecyclerView)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListRecyclerView);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListAVLoadingIndicatorViewIndicator);
        linearLayoutFilterRow = (LinearLayout)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListLinearLayoutFilterRow);
        themedToggleButtonGroupFilter = (ThemedToggleButtonGroup)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListThemedToggleButtonGroupFilter);
        buttonRefrest = (Button)contentView.findViewById(R.id.classroomsBottomSheetDialogClassroomsListButtonRefrest);


        

/*

                mObjectSubjectsList_edited.removeAll(mObjectSubjectsList_edited);

                for(int i = 0; i< mObjectSubjectsList_original.size(); i++){

                    if(mObjectSubjectsList_original.get(i).Category.compareTo(selectedButton) == 0){

                        mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));
                    }

                }

                selectedLocationOfRoom = "none";

                applySorting(selectedIndexOfSorting);
                mMainSubjectsAdapter.notifyDataSetChanged();

                linearLayoutRoomFilterRow.setVisibility(View.GONE);

*/

        mObjectClassroomsList_original = new ArrayList<>();
        mObjectClassroomsList_edited = new ArrayList<>();

        hideAllViews();
        textViewMessageRow1.setVisibility(View.VISIBLE);
        textViewMessageRow1.setText("Načítání dat");
        aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getChildFragmentManager();
        mMainClassroomsAdapter = new MainSubjectsAdapter(mObjectClassroomsList_edited, getContext(), fragmentManager);
        recyclerView.setAdapter(mMainClassroomsAdapter);

        themedToggleButtonGroupFilter.setOnSelectListener((ThemedButton btn) -> {

            Log.e(TAG, "___________________ " + btn.getId() + "________ " + btn.getText()  + "________ " + btn.getSelectedText()  + "________ " + btn.getTag()  + "________ " + btn.getText() );

            selectedButton = btn.getTag().toString();

            applyFilter(selectedButton);
            //applySorting(selectedIndexOfSorting);
            mMainClassroomsAdapter.notifyDataSetChanged();
            return kotlin.Unit.INSTANCE;
        });


        List<ThemedButton> allButtons = themedToggleButtonGroupFilter.getButtons();



        for(int i = 0; i<allButtons.size();i++){

            allButtons.get(i).setSelectedBgColor(Color.parseColor(mSharedPreferences.getString(CURRENT_THEME, "#212121")));
            allButtons.get(i).setBgColor(Color.parseColor("#EEEEEE"));
            // allButtons.get(i).setBackgroundColor(Color.parseColor("#E91E63"));
        }


        Log.e("mylog", "??????????????: " + allButtons.get(0).getId());
        //  allButtons.get(0).setEnabled(true);
        // allButtons.get(0).setSelectedBgColor(Color.parseColor(mSharedPreferences.getString(CURRENT_THEME, "#212121")));
        // themedButtonGroup.selectButton(allButtons.get(0).getId());






        // get the selected buttons:
        //   List<ThemedButton> selectedButtons = themedButtonGroup.getSelectedButtons();
// get all buttons
        // List<ThemedButton> allButtons = themedButtonGroup.getButtons();
// get all unselected buttons
        //  List<ThemedButton> unselectedButtons = allButtons.stream().filter(btn -> !btn.isSelected()).collect(Collectors.toList());



        Loaddata();

        // Práce s daty
        listener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ObjectClassrooms objectSubjects = dataSnapshot.getValue(ObjectClassrooms.class);
                objectSubjects.Key = dataSnapshot.getKey();

                mObjectClassroomsList_original.add(objectSubjects);
                mObjectClassroomsList_edited.add(objectSubjects);
                mMainClassroomsAdapter.notifyDataSetChanged();

                //applySorting(selectedIndexOfSorting);
                //hideAllViews();
                //CheckIsListEmpty(null,null);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ObjectClassrooms objectSubjects = dataSnapshot.getValue(ObjectClassrooms.class);
                objectSubjects.Key = dataSnapshot.getKey();

                int index = GetItemIndex(objectSubjects, mObjectClassroomsList_original);
                mObjectClassroomsList_original.set(index, objectSubjects);
                index = GetItemIndex(objectSubjects, mObjectClassroomsList_edited);
                mObjectClassroomsList_edited.set(index, objectSubjects);

                //applySorting(selectedIndexOfSorting);
                mMainClassroomsAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                ObjectClassrooms objectSubjects = dataSnapshot.getValue(ObjectClassrooms.class);
                objectSubjects.Key = dataSnapshot.getKey();

                int index = GetItemIndex(objectSubjects, mObjectClassroomsList_original);
                mObjectClassroomsList_original.set(index, objectSubjects);
                index = GetItemIndex(objectSubjects, mObjectClassroomsList_edited);
                mObjectClassroomsList_edited.set(index, objectSubjects);

                mMainClassroomsAdapter.notifyItemRemoved(index);

                // TODo žádná data po filtrování

                //applySorting(selectedIndexOfSorting);
                //CheckIsListEmpty(null, null);
                //CheckIsListEmpty("Žádná data k zobrazení", "Databáze je prázdná");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        buttonRefrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Loaddata();

            }
        });








    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }

    private void applyFilter(String filter){
/*
        mObjectClassroomsList_edited.clear();
        Log.e("mylog", "??????????????: " + mObjectClassroomsList_edited);

        switch (filter) {

            case "all": {
                Log.e("mylog", "??????????????: all");

                for(int i = 0; i< mObjectClassroomsList_original.size(); i++){

                    mObjectClassroomsList_edited.add(mObjectClassroomsList_original.get(i));

                }
                break;
            }

            case "IKT": {
                Log.e("mylog", "??????????????: ikt");
                for(int i = 0; i< mObjectClassroomsList_original.size(); i++){

                    if(mObjectClassroomsList_original.get(i).FieldOfStudy.compareTo("IKT") == 0){

                        mObjectClassroomsList_edited.add(mObjectClassroomsList_original.get(i));
                    }
                }
                break;
            }

            case "IT": {
                Log.e("mylog", "??????????????: it");
                for(int i = 0; i< mObjectClassroomsList_original.size(); i++){

                    if(mObjectClassroomsList_original.get(i).FieldOfStudy.compareTo("IT") == 0){

                        mObjectClassroomsList_edited.add(mObjectClassroomsList_original.get(i));
                    }
                }

                break;
            }

            case "IKT_IT": {
                Log.e("mylog", "??????????????: ikt a it");
                for(int i = 0; i< mObjectClassroomsList_original.size(); i++){

                    if(mObjectClassroomsList_original.get(i).FieldOfStudy.compareTo("IKT_IT") == 0){

                        mObjectClassroomsList_edited.add(mObjectClassroomsList_original.get(i));
                    }
                }
                break;
            }
        }

        Log.e("mylog", "??????????????: hhhhhhhhhh" );
        Log.e("mylog", "??????????????: " + mObjectClassroomsList_edited);
*/
    }



    private void hideAllViews(){

        textViewMessageRow1.setVisibility(View.GONE);
        textViewMessageRow2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        buttonRefrest.setVisibility(View.GONE);
        aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);

    }
    private void CheckIsListEmpty(String messageRow1, String messageRow2){

        if(mObjectClassroomsList_original.size() == 0){

            if(messageRow1 == null){

                textViewMessageRow1.setVisibility(View.GONE);

            }else{

                textViewMessageRow1.setVisibility(View.VISIBLE);
                textViewMessageRow1.setText(messageRow1);
            }

            if(messageRow2 == null){

                textViewMessageRow2.setVisibility(View.GONE);

            }else{

                textViewMessageRow2.setVisibility(View.VISIBLE);
                textViewMessageRow2.setText(messageRow2);
            }

            recyclerView.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.GONE);

       

        }else{

            textViewMessageRow1.setVisibility(View.GONE);
            textViewMessageRow2.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            buttonRefrest.setVisibility(View.GONE);

     
        }
    }

    private void Loaddata(){

        if(new IsOnline(getContext()).isOnline() == IS_ONLINE){

            CheckIsListEmpty(INIT);

            //CheckIsListEmpty("Načítání dat", null);
            //aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.i("kokot", dataSnapshot.getChildrenCount() + "");

                    if(dataSnapshot.getChildrenCount() == 0){

                        CheckIsListEmpty(NO_DATA);

                    }else{

                        CheckIsListEmpty(DATA);
                        mDatabaseReference.addChildEventListener(listener);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // TODO
                    //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                    //errorDialog.show(getFragmentManager(), "exampleBottomSheet");

                }
            });

        }else{

            CheckIsListEmpty(NO_INTERNET);


        }

    }

    private int GetItemIndex(ObjectClassrooms objectSubjects, List<ObjectClassrooms> list){

        int index = -1;

        for(int i = 0; i<list.size(); i++){

            if(list.get(i).Key.equals(objectSubjects.Key)){
                index = i;
                break;
            }
        }

        return index;
    }

    private void CheckIsListEmpty(int action){

        if(action == INIT){

            linearLayoutFilterRow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.GONE);

            textViewMessageRow1.setText("Načítání dat");

        }else if(action == NO_INTERNET){

            linearLayoutFilterRow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.VISIBLE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);

            textViewMessageRow1.setText("Nepodařilo se načíst adata");
            textViewMessageRow2.setText("Zkontrolujte připojení k internetu");

        }else if(action == NO_DATA){

            linearLayoutFilterRow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.VISIBLE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);

            textViewMessageRow1.setText("Žádná data k zobrazení");
            textViewMessageRow2.setText("Databáze je prázdná");

        }

        else if(action == DATA){

            linearLayoutFilterRow.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.GONE);
            textViewMessageRow2.setVisibility(View.GONE);


        }
    }


    public static class MainSubjectsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<ObjectClassrooms> mMainLightsList;
        private Context mContext;
        SharedPreferences mSharedPreferences;

        FragmentManager fragmentManager;

        public MainSubjectsAdapter(List<ObjectClassrooms> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainSubjectsAdapter.MainSubjectsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.classrooms_bottom_sheet_dialog_classrooms_list_adapter_item,parent,false));

        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final ObjectClassrooms objectSubjects = mMainLightsList.get(position);

            MainSubjectsAdapter.MainSubjectsViewHolder mainSubjectsViewHolder = (MainSubjectsAdapter.MainSubjectsViewHolder)holder;

            mainSubjectsViewHolder.teacherNamedssssf.setText(objectSubjects.SchoolRoomNumber);
            mainSubjectsViewHolder.teacherNamedssssfss.setText(objectSubjects.SchoolRoomCharakter);

        }

        @Override
        public int getItemCount() {
            return mMainLightsList.size();
        }

        @Override
        public long getItemId(int position) {

            return position;
        }



        class MainSubjectsViewHolder extends RecyclerView.ViewHolder{

            TextView teacherNamedssssf;
            TextView teacherNamedssssfss;


            public MainSubjectsViewHolder(View itemView){
                super(itemView);

                teacherNamedssssf = itemView.findViewById(R.id.teacherNamedsssvcsssssssvsfs);
                teacherNamedssssfss = itemView.findViewById(R.id.teacherNamedsssvcsssddssssvsfs);

            }
        }


        private void setMoveText(TextView moveText) {
            moveText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            moveText.setSingleLine(true);
            moveText.setAllCaps(true);
            moveText.setMarqueeRepeatLimit(200);
            moveText.setSelected(true);
        }


    }


}

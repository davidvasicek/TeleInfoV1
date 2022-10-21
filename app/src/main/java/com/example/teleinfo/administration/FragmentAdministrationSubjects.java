package com.example.teleinfo.administration;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.ADMINISTRATION_EDIT;
import static com.example.teleinfo.parameters.MainParameters.ADMINISTRATION_NEW;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DATA;
import static com.example.teleinfo.parameters.MainParameters.INIT;
import static com.example.teleinfo.parameters.MainParameters.IS_ONLINE;
import static com.example.teleinfo.parameters.MainParameters.NO_DATA;
import static com.example.teleinfo.parameters.MainParameters.NO_INTERNET;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.IsOnline;
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

public class FragmentAdministrationSubjects extends Fragment implements BottomSheetDialogSubjectsEditNew.BottomSheetDialogSubjectsEditNewListener {

    private List<ObjectSubjects> mObjectSubjectsList_original;
    private List<ObjectSubjects> mObjectSubjectsList_edited;
    private MainSubjectsAdapter mMainSubjectsAdapter;

    RecyclerView recyclerView;
    TextView textViewMessageRow1;
    TextView textViewMessageRow2;
    Button buttonRefrest;
    AVLoadingIndicatorView aVLoadingIndicatorViewIndicator;


    LinearLayout LinearLayoutFilterRow;
    
    ThemedToggleButtonGroup themedButtonGroup;
    

    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener listener;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    int selectedIndexOfSorting;
    String selectedLocationOfRoom;

    Menu mMenu;

    String selectedButton = "MainLights";

    public FragmentAdministrationSubjects() {
        // Required empty public constructor
    }

    //@Override
    //public void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.administration_fragment_subjects, container, false);

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        //mDatabaseReference = mFirebaseDatabaseDatabase.getReference("TeleInfo/Administration/Teachers");
        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("TeleInfo/Administration/");




        /*




        ObjectSubjects objectSubjects = new ObjectSubjects();
        objectSubjects.Shortcut = "AJ";
        objectSubjects.FullName = "Anglický jazyk";
        objectSubjects.FieldOfStudy = "IKT";
        String key = mDatabaseReference.child("AJ_anglicky").push().getKey();
        mDatabaseReference.child(key).setValue(objectSubjects);

        objectSubjects = new ObjectSubjects();
        objectSubjects.Shortcut = "PJ";
        objectSubjects.FullName = "Programovací jazyky";
        objectSubjects.FieldOfStudy = "IKT";
         key = mDatabaseReference.child("PJ_prograqmko").push().getKey();
        mDatabaseReference.child(key).setValue(objectSubjects);

        objectSubjects = new ObjectSubjects();
        objectSubjects.Shortcut = "PRA";
        objectSubjects.FullName = "Praxe";
        objectSubjects.FieldOfStudy = "IKT_IT";
         key = mDatabaseReference.child("PRA_praxe").push().getKey();
        mDatabaseReference.child(key).setValue(objectSubjects);

        objectSubjects = new ObjectSubjects();
        objectSubjects.Shortcut = "PW";
        objectSubjects.FullName = "Programování WWW";
        objectSubjects.FieldOfStudy= "IT";
         key = mDatabaseReference.child("PW_webovky").push().getKey();
        mDatabaseReference.child(key).setValue(objectSubjects);

 */

        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        //mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        
        
        themedButtonGroup = (ThemedToggleButtonGroup)rootView.findViewById(R.id.subjectsThemedToggleButtonGroupFilter);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_RecyclerView);

        textViewMessageRow1 = (TextView)rootView.findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow1);
        textViewMessageRow2 = (TextView)rootView.findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_TextViewMessageRow2);
        buttonRefrest = (Button)rootView.findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_ButtonRefrest);
        aVLoadingIndicatorViewIndicator = (AVLoadingIndicatorView)rootView.findViewById(R.id.administrationsOrganizationsOrganizationsListActivity_AVLoadingIndicatorViewIndicator);

        LinearLayoutFilterRow = (LinearLayout) rootView.findViewById(R.id.LinearLayoutFilterRow);


        

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

        mObjectSubjectsList_original = new ArrayList<>();
        mObjectSubjectsList_edited = new ArrayList<>();

        hideAllViews();
        textViewMessageRow1.setVisibility(View.VISIBLE);
        textViewMessageRow1.setText("Načítání dat");
        aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getChildFragmentManager();
        mMainSubjectsAdapter = new MainSubjectsAdapter(mObjectSubjectsList_edited, getContext(), fragmentManager);
        recyclerView.setAdapter(mMainSubjectsAdapter);

        themedButtonGroup.setOnSelectListener((ThemedButton btn) -> {

            Log.e(TAG, "___________________ " + btn.getId() + "________ " + btn.getText()  + "________ " + btn.getSelectedText()  + "________ " + btn.getTag()  + "________ " + btn.getText() );

            selectedButton = btn.getTag().toString();

            applyFilter(selectedButton);
            applySorting(selectedIndexOfSorting);
            mMainSubjectsAdapter.notifyDataSetChanged();
            return kotlin.Unit.INSTANCE;
        });


        List<ThemedButton> allButtons = themedButtonGroup.getButtons();



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

                ObjectSubjects objectSubjects = dataSnapshot.getValue(ObjectSubjects.class);
                objectSubjects.Key = dataSnapshot.getKey();

                mObjectSubjectsList_original.add(objectSubjects);
                mObjectSubjectsList_edited.add(objectSubjects);
                mMainSubjectsAdapter.notifyDataSetChanged();

                //applySorting(selectedIndexOfSorting);
                //hideAllViews();
                //CheckIsListEmpty(null,null);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ObjectSubjects objectSubjects = dataSnapshot.getValue(ObjectSubjects.class);
                objectSubjects.Key = dataSnapshot.getKey();

                int index = GetItemIndex(objectSubjects, mObjectSubjectsList_original);
                mObjectSubjectsList_original.set(index, objectSubjects);
                index = GetItemIndex(objectSubjects, mObjectSubjectsList_edited);
                mObjectSubjectsList_edited.set(index, objectSubjects);

                //applySorting(selectedIndexOfSorting);
                mMainSubjectsAdapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                ObjectSubjects objectSubjects = dataSnapshot.getValue(ObjectSubjects.class);
                objectSubjects.Key = dataSnapshot.getKey();

                int index = GetItemIndex(objectSubjects, mObjectSubjectsList_original);
                mObjectSubjectsList_original.set(index, objectSubjects);
                index = GetItemIndex(objectSubjects, mObjectSubjectsList_edited);
                mObjectSubjectsList_edited.set(index, objectSubjects);

                mMainSubjectsAdapter.notifyItemRemoved(index);

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



    return rootView;

    }

    private void applyFilter(String filter){

        mObjectSubjectsList_edited.clear();
        Log.e("mylog", "??????????????: " + mObjectSubjectsList_edited);

        switch (filter) {

            case "all": {
                Log.e("mylog", "??????????????: all");

                for(int i = 0;i<mObjectSubjectsList_original.size();i++){

                    mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));

                }
                break;
            }

            case "IKT": {
                Log.e("mylog", "??????????????: ikt");
                for(int i = 0;i<mObjectSubjectsList_original.size();i++){

                    if(mObjectSubjectsList_original.get(i).FieldOfStudy.compareTo("IKT") == 0){

                        mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));
                    }
                }
                break;
            }

            case "IT": {
                Log.e("mylog", "??????????????: it");
                for(int i = 0;i<mObjectSubjectsList_original.size();i++){

                    if(mObjectSubjectsList_original.get(i).FieldOfStudy.compareTo("IT") == 0){

                        mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));
                    }
                }

                break;
            }

            case "IKT_IT": {
                Log.e("mylog", "??????????????: ikt a it");
                for(int i = 0;i<mObjectSubjectsList_original.size();i++){

                    if(mObjectSubjectsList_original.get(i).FieldOfStudy.compareTo("IKT_IT") == 0){

                        mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));
                    }
                }
                break;
            }
        }

        Log.e("mylog", "??????????????: hhhhhhhhhh" );
        Log.e("mylog", "??????????????: " + mObjectSubjectsList_edited);

    }




/*
    @Override
    public void applySortingListener(int selectedIndex) {

        selectedIndexOfSorting = selectedIndex;
        applySorting(selectedIndexOfSorting);

        mEditor.putInt(ON_SAVE_SORTING_VALUE_MAIN_LIGHTS, selectedIndexOfSorting);
        mEditor.commit();

    }


 */
 /*
    @Override
    public void applyRoomFilter(String room) {

        Log.i("kokot", room + "");

        selectedLocationOfRoom = room;

        linearLayoutRoomFilterRow.setVisibility(View.VISIBLE);

        textViewRoomFilterName.setText(getApplicationContext().getResources().getString(new GetIcon(getApplicationContext()).getStringLocationResource(selectedLocationOfRoom)));
        imageViewRoomFilterIcon.setImageResource(new GetIcon(getApplicationContext()).getImageLocationResource(selectedLocationOfRoom));

        mObjectSubjectsList_edited.removeAll(mObjectSubjectsList_edited);

        for(int i = 0; i < mObjectSubjectsList_original.size(); i++){

            if(mObjectSubjectsList_original.get(i).Location.compareTo(selectedLocationOfRoom) == 0){


                if(mObjectSubjectsList_original.get(i).Category.compareTo(selectedButton) == 0){

                    mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));

                }

            }
        }

        mMainSubjectsAdapter.notifyDataSetChanged();


    }
 */
    private void hideAllViews(){

        textViewMessageRow1.setVisibility(View.GONE);
        textViewMessageRow2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        buttonRefrest.setVisibility(View.GONE);
        aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);

    }

    private void CheckIsListEmpty(String messageRow1, String messageRow2){

        if(mObjectSubjectsList_original.size() == 0){

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

            if(mMenu != null){

                mMenu.getItem(0).setVisible(false);
                mMenu.getItem(1).setVisible(false);
                mMenu.getItem(2).setVisible(false);
            }

        }else{

            textViewMessageRow1.setVisibility(View.GONE);
            textViewMessageRow2.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            buttonRefrest.setVisibility(View.GONE);

            if(mMenu != null){

                mMenu.getItem(0).setVisible(true);
                mMenu.getItem(1).setVisible(true);
                mMenu.getItem(2).setVisible(true);
            }
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

    private int GetItemIndex(ObjectSubjects objectSubjects, List<ObjectSubjects> list){

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

            LinearLayoutFilterRow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.VISIBLE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.GONE);

            textViewMessageRow1.setText("Načítání dat");

        }else if(action == NO_INTERNET){

            LinearLayoutFilterRow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.VISIBLE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);

            textViewMessageRow1.setText("Nepodařilo se načíst adata");
            textViewMessageRow2.setText("Zkontrolujte připojení k internetu");

        }else if(action == NO_DATA){

            LinearLayoutFilterRow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.VISIBLE);
            textViewMessageRow1.setVisibility(View.VISIBLE);
            textViewMessageRow2.setVisibility(View.VISIBLE);

            textViewMessageRow1.setText("Žádná data k zobrazení");
            textViewMessageRow2.setText("Databáze je prázdná");

        }

        else if(action == DATA){

            LinearLayoutFilterRow.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            aVLoadingIndicatorViewIndicator.setVisibility(View.GONE);
            buttonRefrest.setVisibility(View.GONE);
            textViewMessageRow1.setVisibility(View.GONE);
            textViewMessageRow2.setVisibility(View.GONE);


        }
    }


    private void applySorting(int selectedIndexOfSorting){

/*

        mObjectSubjectsList_edited.removeAll(mObjectSubjectsList_edited);

        for(int i = 0; i< mObjectSubjectsList_original.size(); i++){

            if(mObjectSubjectsList_original.get(i).Category.compareTo(selectedButton) == 0){

                mObjectSubjectsList_edited.add(mObjectSubjectsList_original.get(i));
            }


        }

        switch(selectedIndexOfSorting) {
            case 0:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return obj1.Name.compareToIgnoreCase(obj2.Name) ;
                    }});
                break;
            case 1:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return obj2.Name.compareToIgnoreCase(obj1.Name) ;
                    }});
                break;
            case 2:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return obj1.Location.compareToIgnoreCase(obj2.Location) ;
                    }});
                break;
            case 3:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return obj2.Location.compareToIgnoreCase(obj1.Location) ;
                    }});
                break;
            case 4:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return (obj1.UsageCounter > obj2.UsageCounter) ? -1: (obj1.UsageCounter < obj2.UsageCounter) ? 1:0 ;
                    }});
                break;
            case 5:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return (obj1.UsageCounter < obj2.UsageCounter) ? -1: (obj1.UsageCounter > obj2.UsageCounter) ? 1:0 ;
                    }});
                break;
            case 6:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return Boolean.compare(obj2.on, obj1.on) ;
                    }});
                break;
            case 7:
                Collections.sort(mObjectSubjectsList_edited, new Comparator<ObjectSubjects>(){
                    public int compare(ObjectSubjects obj1, ObjectSubjects obj2) {
                        return Boolean.compare(obj1.on, obj2.on) ;
                    }});
                break;

        }





        mMainSubjectsAdapter.notifyDataSetChanged();
*/
    }

    @Override
    public void applyBottomSheetDialogSubjectsEditNewListener(ObjectSubjects objectSubjects,int action) {


        if(action == ADMINISTRATION_NEW){


            objectSubjects.Key = mDatabaseReference.push().getKey();

        }

        mDatabaseReference.child(objectSubjects.Key ).setValue(objectSubjects);




    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_sort_switch, menu);

        this.mMenu = menu;

        mMenu.getItem(0).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);
        mMenu.getItem(1).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);
        mMenu.getItem(2).getIcon().setColorFilter(getApplicationContext().getResources().getColor(R.color.line), PorterDuff.Mode.SRC_ATOP);

        mMenu.getItem(0).setVisible(false);
        mMenu.getItem(1).setVisible(false);
        mMenu.getItem(2).setVisible(false);

        // if(menuVisibility){

        //mMenu.getItem(0).setVisible(true);
        mMenu.getItem(0).setVisible(true);
        mMenu.getItem(1).setVisible(true);
        mMenu.getItem(2).setVisible(true);
        // }



        return true;
    }
*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.sort:

                List <String> mElements = new ArrayList<>();
                mElements.add("jmeno asc");
                mElements.add("jmeno desc");
                mElements.add("lokace asc");
                mElements.add("lokace desc");
                mElements.add("pouzití asc");
                mElements.add("použití desc");
                mElements.add("zapnuté první");
                mElements.add("vypnuté první");

                SensorListActivitySortingDialog sensorListActivitySortingDialog = new SensorListActivitySortingDialog(mElements, selectedIndexOfSorting);
                sensorListActivitySortingDialog.show(getSupportFragmentManager(), "");
                return(true);

            case R.id.settings:

                SensorListActivitySettingsBottomSheetDialog sensorListActivitySettingsBottomSheetDialog = new SensorListActivitySettingsBottomSheetDialog("MainLights");
                sensorListActivitySettingsBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");
                return(true);

            case R.id.roomCategory:

                List <String> mLocationList = new ArrayList<>();


                // vytáhnu si jednotlivé umístění a uložím je do nového listu
                for(int i = 0; i < mObjectSubjectsList_original.size(); i++){

                    if (!mLocationList.contains(mObjectSubjectsList_original.get(i).Location)) {
                        mLocationList.add(mObjectSubjectsList_original.get(i).Location);
                    }
                }

                SensorListActivityRoomsCategoryBottomSheetDialog sensorListActivityRoomsCategoryBottomSheetDialog = new SensorListActivityRoomsCategoryBottomSheetDialog(mLocationList, selectedLocationOfRoom);
                sensorListActivityRoomsCategoryBottomSheetDialog.show(getSupportFragmentManager(), "exampleBottomSheet");



                return(true);

        }

        return(super.onOptionsItemSelected(item));
    }

*/


    // ------------------------------------------------------------------------------------ Adapter ------------------------------------------------------------------------------------





    public static class MainSubjectsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<ObjectSubjects> mMainLightsList;
        private Context mContext;
        SharedPreferences mSharedPreferences;

        FragmentManager fragmentManager;

        public MainSubjectsAdapter(List<ObjectSubjects> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainSubjectsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.administration_fragment_subjects_adapter_item,parent,false));

        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final ObjectSubjects objectSubjects = mMainLightsList.get(position);

            MainSubjectsViewHolder mainSubjectsViewHolder = (MainSubjectsViewHolder)holder;

            mainSubjectsViewHolder.teacherNamedssssf.setText(objectSubjects.Shortcut + " (" + objectSubjects.FullName + ")");
            mainSubjectsViewHolder.teacherNamedssssfss.setText(objectSubjects.FieldOfStudy);

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

                teacherNamedssssf = itemView.findViewById(R.id.teacherNamedssssf);
                teacherNamedssssfss = itemView.findViewById(R.id.teacherNamedssssfss);

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










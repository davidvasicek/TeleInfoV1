package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindSchoolroomBottomSheetDialog extends BottomSheetDialogFragment {

    private GridLayoutManager gridLayoutManager;

    List<String> colors;
    int columns;
    String colorSelected;
    int idOfSelectedColor;

    TextView vysledkyHledani;

    private FirebaseDatabase mFirebaseDatabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DataSnapshot dataSnapshot_global;

    public FindSchoolroomBottomSheetDialog(List<String> colors, int columns, String colorSelected) {

        this.colors = colors;
        this.columns = columns;
        this.colorSelected = colorSelected;
    }
    
   
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.rozvrh_dialog_bottom_sheet_dialog_find_schoolroom, null);
        dialog.setContentView(contentView);

        vysledkyHledani = (TextView)contentView.findViewById(R.id.vysledkyHledani);

        mFirebaseDatabaseDatabase = FirebaseDatabase.getInstance();
        
        gridLayoutManager = new GridLayoutManager(getContext(), columns);

        RecyclerView rView = (RecyclerView)contentView.findViewById(R.id.color1SelectBottomSheetDialogFragmentRecyclerView);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(gridLayoutManager);

        FragmentManager fragmentManager = getChildFragmentManager();
        ClassroomSelectAdapter rcAdapter = new ClassroomSelectAdapter(colors,getActivity(), fragmentManager);
        rView.setAdapter(rcAdapter);



        mDatabaseReference = mFirebaseDatabaseDatabase.getReference("rozvrh/denniUceben");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                dataSnapshot_global = dataSnapshot;


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                // TODO
                //ErrorDialog errorDialog = new ErrorDialog(ERROR_CODE_UNKNOWN, databaseError + "");
                //errorDialog.show(getFragmentManager(), "exampleBottomSheet");
            }
        });


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }


    public class ClassroomSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> mColorList;
        private Context mContext;
        private FragmentManager mFragmentManager;

        public ClassroomSelectAdapter(List<String> list, Context context, FragmentManager fragmentManager) {

            this.mColorList = list;
            this.mContext = context;
            this.mFragmentManager = fragmentManager;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ColorViewItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.rozvrh_dialog_bottom_sheet_dialog_find_schoolroom_adapter_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final String color = mColorList.get(position);

            ColorViewItem colorViewItem = (ColorViewItem)holder;
            



                colorViewItem.imageViewColorSelected.setText(color);

        

            colorViewItem.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    List<String> Schoolroom = new ArrayList<>();

                    Schoolroom.add("1");
                    Schoolroom.add("3");
                    Schoolroom.add("4");
                    Schoolroom.add("5");
                    Schoolroom.add("6");
                    Schoolroom.add("7");
                    Schoolroom.add("8");
                    Schoolroom.add("9");
                    Schoolroom.add("10");
                    Schoolroom.add("11");
                    Schoolroom.add("12");
                    Schoolroom.add("13");
                    Schoolroom.add("14");
                    Schoolroom.add("15");
                    Schoolroom.add("16");
                    Schoolroom.add("17");
                    Schoolroom.add("18");
                    Schoolroom.add("25");
                    Schoolroom.add("26");
                    Schoolroom.add("27");
                    Schoolroom.add("28");
                    Schoolroom.add("30");
                    Schoolroom.add("31");
                    Schoolroom.add("32");
                    Schoolroom.add("33");
                    Schoolroom.add("34");
                    Schoolroom.add("35");
                    Schoolroom.add("T");
                    Schoolroom.add("POS");
                    Schoolroom.add("ZAS");
                    Schoolroom.add("Knih");


                    //for()


                    String ucebny = "";
for(int i = 0; i<Schoolroom.size(); i++){

    int count = 0;

    for (DataSnapshot dataSnapshotu: dataSnapshot_global.child(Schoolroom.get(i)).child("19_9_2022").child("hodina").child(color).getChildren()) {

        count++;

    }

    if(count == 0){

        ucebny+= Schoolroom.get(i) + ", ";
    }

    count = 0;

}

                    vysledkyHledani.setText(ucebny);





                    //Log.e(TAG, "color ___________________ " + count );


                    //  dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {

            return mColorList.size();
        }

        public class ColorViewItem extends RecyclerView.ViewHolder{

            TextView imageViewColorSelected;

            public ColorViewItem(View itemView){
                super(itemView);

                imageViewColorSelected = itemView.findViewById(R.id.dialogBottomSheetDialogSelectColorAdapterItemImageViewColorSelected);
            }
        }
    }
}

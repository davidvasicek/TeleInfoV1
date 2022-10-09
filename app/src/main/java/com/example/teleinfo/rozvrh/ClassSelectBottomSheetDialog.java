package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class ClassSelectBottomSheetDialog extends BottomSheetDialogFragment {

    private GridLayoutManager gridLayoutManager;

    List<String> colors;
    int columns;
    String colorSelected;
    int idOfSelectedColor;

    public ClassSelectBottomSheetDialog(List<String> colors, int columns, String colorSelected) {

        this.colors = colors;
        this.columns = columns;
        this.colorSelected = colorSelected;
    }
    
   
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.rozvrh_dialog_bottom_sheet_dialog_select_class, null);
        dialog.setContentView(contentView);
        
        gridLayoutManager = new GridLayoutManager(getContext(), columns);

        RecyclerView rView = (RecyclerView)contentView.findViewById(R.id.color1SelectBottomSheetDialogFragmentRecyclerView);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(gridLayoutManager);

        FragmentManager fragmentManager = getChildFragmentManager();
        ClassroomSelectAdapter rcAdapter = new ClassroomSelectAdapter(colors,getActivity(), fragmentManager);
        rView.setAdapter(rcAdapter);
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

            return new ColorViewItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.rozvrh_dialog_bottom_sheet_dialog_select_class_adapter_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final String color = mColorList.get(position);

            ColorViewItem colorViewItem = (ColorViewItem)holder;
            



                colorViewItem.imageViewColorSelected.setText(color);

        

            colorViewItem.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(getContext(), RozvrhNove1.class);
                    intent.putExtra("action", "weeklyBySchoolClass");
                     intent.putExtra("schoolClass", color);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(intent);



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

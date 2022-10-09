package com.example.teleinfo.dialogs;

import static android.content.ContentValues.TAG;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ColorSelectBottomSheetDialog extends BottomSheetDialogFragment {

    private GridLayoutManager gridLayoutManager;
    private ColorSelectListener colorSelectListener;

    List<String> colors;
    int columns;
    String colorSelected;
    int idOfSelectedColor;
    
    public ColorSelectBottomSheetDialog(List<String> colors, int columns, String colorSelected) {

        this.colors = colors;
        this.columns = columns;
        this.colorSelected = colorSelected;
    }
    
    @Override
    public void onAttach(Context context) {

        if(getParentFragmentManager() == null){

            try{
                colorSelectListener = (ColorSelectListener) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }else{
            try{
                colorSelectListener = (ColorSelectListener) getParentFragment();
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }
        super.onAttach(context);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.dialog_bottom_sheet_dialog_select_color, null);
        dialog.setContentView(contentView);

        for(int i = 0; i < colors.size(); i++){

            if(colors.get(i).compareTo(colorSelected) == 0){

                idOfSelectedColor = i;
                break;
            }
        }

        gridLayoutManager = new GridLayoutManager(getContext(), columns);

        RecyclerView rView = (RecyclerView)contentView.findViewById(R.id.color1SelectBottomSheetDialogFragmentRecyclerView);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(gridLayoutManager);

        FragmentManager fragmentManager = getChildFragmentManager();
        ColorSelectAdapter rcAdapter = new ColorSelectAdapter(colors,getActivity(), fragmentManager);
        rView.setAdapter(rcAdapter);
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }

    public interface ColorSelectListener {
        void applyColor(String color);
    }
    
    public class ColorSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> mColorList;
        private Context mContext;
        private FragmentManager mFragmentManager;

        public ColorSelectAdapter(List<String> list, Context context, FragmentManager fragmentManager) {

            this.mColorList = list;
            this.mContext = context;
            this.mFragmentManager = fragmentManager;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ColorViewItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_bottom_sheet_dialog_select_color_adapter_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final String color = mColorList.get(position);

            ColorViewItem colorViewItem = (ColorViewItem)holder;

            colorViewItem.linearLayoutColorRow.setBackgroundColor(Color.parseColor(color));

            if(idOfSelectedColor == position){

                colorViewItem.imageViewColorSelected.setVisibility(View.VISIBLE);

            }else{

                colorViewItem.imageViewColorSelected.setVisibility(View.GONE);
            }

            colorViewItem.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    if (v != null) {
                        vibrator.vibrate(200);
                    }

                    colorSelectListener.applyColor(color.substring(1, color.length()));

                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {

            return mColorList.size();
        }

        public class ColorViewItem extends RecyclerView.ViewHolder{

            ImageView imageViewColorSelected;
            LinearLayout linearLayoutColorRow;

            public ColorViewItem(View itemView){
                super(itemView);

                imageViewColorSelected = itemView.findViewById(R.id.dialogBottomSheetDialogSelectColorAdapterItemImageViewColorSelected);
                linearLayoutColorRow = itemView.findViewById(R.id.dialogBottomSheetDialogSelectColorAdapterItemLinearLayoutColorRow);
            }
        }
    }
}

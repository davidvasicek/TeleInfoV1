package com.example.teleinfo.dialogs;

import static android.content.ContentValues.TAG;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class EasyRadioGroupListDialog extends BottomSheetDialogFragment {

    private EasyRadioGroupAdapter mEasyRadioGroupAdapter;
    ItemSelectedListener itemSelectedListener;

    int checkIndex;
    private List<String> list;

    RecyclerView recyclerView;
    Button buttonOk;
    TextView textViewTitle;

    String title;

    public EasyRadioGroupListDialog(String title, List<String> list, int checkIndex) {

        this.list = list;
        this.checkIndex = checkIndex;
        this.title = title;

    }

    @Override
    public void onAttach(Context context) {

        if(getParentFragmentManager() == null){

            try{
                itemSelectedListener = (ItemSelectedListener) context;
            }catch (ClassCastException e){
                Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
            }
        }else{
            try{
                itemSelectedListener = (ItemSelectedListener) getParentFragment();
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
        View contentView = View.inflate(getContext(), R.layout.dialog_easy_radio_group_list, null);
        dialog.setContentView(contentView);

        recyclerView = (RecyclerView)contentView.findViewById(R.id.dialogEasyRadioGroupListRecyclerView);
        textViewTitle = (TextView)contentView.findViewById(R.id.dialogEasyRadioGroupListTextViewTitle);

        buttonOk = (Button) contentView.findViewById(R.id.dialogEasyRadioGroupListButtonOk);
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        buttonOk.setTextColor(Color.parseColor(mSharedPreferences.getString(CURRENT_THEME, "#212121")));

        textViewTitle.setText(title);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getChildFragmentManager();
        mEasyRadioGroupAdapter = new EasyRadioGroupAdapter(list, getContext() , fragmentManager);
        recyclerView.setAdapter(mEasyRadioGroupAdapter);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.dialog_easy_radio_group_list, container,false);
//        return rootView;
//    }

    public interface ItemSelectedListener {
        void applyID(int id);
    }

    public class EasyRadioGroupAdapter extends RecyclerView.Adapter<EasyRadioGroupAdapter.RoomViewHolder> {

        private List<String> list;
        private Context mContext;

        public int mSelectedItem = checkIndex;

        public EasyRadioGroupAdapter(List<String> list, Context context, FragmentManager fragmentManager) {

            this.list = list;
            this.mContext = context;
        }

        @Override
        public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RoomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_sensor_list_activity_sorting_dialog_adapter_row,parent,false));
        }

        @Override
        public void onBindViewHolder(final RoomViewHolder roomViewHolder, final int position) {

            String element = list.get(position);

            roomViewHolder.mRadio.setChecked(position == mSelectedItem);

            roomViewHolder.textViewSortBy.setText(element);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class RoomViewHolder extends RecyclerView.ViewHolder{

            TextView textViewSortBy;
            public RadioButton mRadio;


            public RoomViewHolder(View itemView){
                super(itemView);

                textViewSortBy = itemView.findViewById(R.id.sensorListActivitySortingDialogRowTextViewSortBy);

                mRadio = (RadioButton) itemView.findViewById(R.id.sensorListActivitySortingDialogRowRadioButtonSortBy);
                View.OnClickListener l = v -> {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, list.size());
                    itemSelectedListener.applyID(getAdapterPosition());

                };
                itemView.setOnClickListener(l);
                mRadio.setOnClickListener(l);

            }
        }
    }




}


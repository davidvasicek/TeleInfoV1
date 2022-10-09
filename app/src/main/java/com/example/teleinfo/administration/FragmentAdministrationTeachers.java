package com.example.teleinfo.administration;

import static com.example.teleinfo.parameters.MainParameters.BREAKFAST_SHOW;
import static com.example.teleinfo.parameters.MainParameters.BRUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SECOND_SHOW;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SHOW;
import static com.example.teleinfo.parameters.MainParameters.LUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SNACK_SHOW;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.rozvrh.DinningRoomMenuObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdministrationTeachers extends Fragment {
    
    private List<DinningRoomMenuObject> mTeachersAdministrationList;
    private TeachersAdministrationAdapter mTeachersAdministrationAdapter;

    RecyclerView recyclerView;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public FragmentAdministrationTeachers() {
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

        View rootView = inflater.inflate(R.layout.administration_fragment_teachers, container, false);

        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();


        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerAdministrationTeachers);


        mTeachersAdministrationList = new ArrayList<>();




        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getChildFragmentManager();
        mTeachersAdministrationAdapter = new TeachersAdministrationAdapter(mTeachersAdministrationList, getContext(), getChildFragmentManager());
        recyclerView.setAdapter(mTeachersAdministrationAdapter);


        return rootView;
    }

    public class TeachersAdministrationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<DinningRoomMenuObject> mMainLightsList;
        private Context mContext;
        SharedPreferences mSharedPreferences;

        FragmentManager fragmentManager;

        public TeachersAdministrationAdapter(List<DinningRoomMenuObject> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new TeachersAdministrationAdapter.MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rozvrh_bottom_sheet_dialog_dinning_room_menu_adapter_item,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final DinningRoomMenuObject dinningRoomMenuObject = mMainLightsList.get(position);

            TeachersAdministrationAdapter.MainLightsViewHolder mainLightsViewHolder = (TeachersAdministrationAdapter.MainLightsViewHolder)holder;






        }

        @Override
        public int getItemCount() {
            return mMainLightsList.size();
        }

        @Override
        public long getItemId(int position) {

            return position;
        }


        class MainLightsViewHolder extends RecyclerView.ViewHolder{

            TextView menuDate;
            TextView BreakfastTitle;
            TextView Breakfast;
            TextView BreakfastAllergens;
            TextView BrunchTitle;
            TextView Brunch;
            TextView BrunchAllergens;
            TextView LunchTitle;
            TextView Lunch;
            TextView LunchAllergens;
            TextView SnackTitle;
            TextView Snack;
            TextView SnackAllergens;
            TextView DinnerTitle;
            TextView Dinner;
            TextView DinnerAllergens;
            TextView DinnerSecondTitle;
            TextView DinnerSecond;
            TextView DinnerSecondAllergens;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                menuDate = itemView.findViewById(R.id.menuDate);
                BreakfastTitle = itemView.findViewById(R.id.BreakfastTitle);
                Breakfast = itemView.findViewById(R.id.Breakfast);
                BreakfastAllergens = itemView.findViewById(R.id.BreakfastAllergens);
                BrunchTitle = itemView.findViewById(R.id.BrunchTitle);
                Brunch = itemView.findViewById(R.id.Brunch);
                BrunchAllergens = itemView.findViewById(R.id.BrunchAllergens);
                LunchTitle = itemView.findViewById(R.id.LunchTitle);
                Lunch = itemView.findViewById(R.id.Lunch);
                LunchAllergens = itemView.findViewById(R.id.LunchAllergens);
                SnackTitle = itemView.findViewById(R.id.SnackTitle);
                Snack = itemView.findViewById(R.id.Snack);
                SnackAllergens = itemView.findViewById(R.id.SnackAllergens);
                DinnerTitle = itemView.findViewById(R.id.DinnerTitle);
                Dinner = itemView.findViewById(R.id.Dinner);
                DinnerAllergens = itemView.findViewById(R.id.DinnerAllergens);
                DinnerSecondTitle = itemView.findViewById(R.id.DinnerSecondTitle);
                DinnerSecond = itemView.findViewById(R.id.DinnerSecond);
                DinnerSecondAllergens = itemView.findViewById(R.id.DinnerSecondAllergens);

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










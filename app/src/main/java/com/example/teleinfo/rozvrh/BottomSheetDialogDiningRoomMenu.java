package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.BREAKFAST_SHOW;
import static com.example.teleinfo.parameters.MainParameters.BRUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SECOND_SHOW;
import static com.example.teleinfo.parameters.MainParameters.DINNER_SHOW;
import static com.example.teleinfo.parameters.MainParameters.LUNCH_SHOW;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SNACK_SHOW;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BottomSheetDialogDiningRoomMenu extends BottomSheetDialogFragment {

    private List<DinningRoomMenuObject> mDinningRoomMenuList;
    private DinningRoomMenuAdapter mDinningRoomMenuAdapter;

    RecyclerView recyclerView;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    public BottomSheetDialogDiningRoomMenu() {

    }
    
   
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.rozvrh_bottom_sheet_dialog_dinning_room_menu, null);
        dialog.setContentView(contentView);

        recyclerView = (RecyclerView)contentView.findViewById(R.id.recyclerTeachers);


        mDinningRoomMenuList = new ArrayList<>();

        DinningRoomMenuObject dinningRoomMenuObject = new DinningRoomMenuObject();
        dinningRoomMenuObject.Date = new Date();
        dinningRoomMenuObject.DayOfWeek = "Pondělí";
        dinningRoomMenuObject.Breakfast = "FRGÁL, KAKAO GRANKO, OVOCE";
        dinningRoomMenuObject.BreakfastAllergens = "A: 01a,01c,03,07";
        dinningRoomMenuObject.Brunch = "TAVENÝ SÝR, PLETÝNKA, ČERSTVÁ ZELENINA, ČAJ ";
        dinningRoomMenuObject.BrunchAllergens = "A: 01a,01b,01c,06,07,11";
        dinningRoomMenuObject.Lunch = "BULHARSKÁ POLÉVKA, PIZZA SE SÝREM A ŠUNKOU, OVOCE, DŽUS ";
        dinningRoomMenuObject.LunchAllergens = "A: 01,01a,03,07,09";
        dinningRoomMenuObject.Snack = "CROISSANT, ČAJ ";
        dinningRoomMenuObject.SnackAllergens = "A: 01a,03,06,07";
        dinningRoomMenuObject.Dinner = "KUŘECÍ RAŽNIČÍ, BRAMBORY, ĎÁBELSKÝ DIP, ZELENINOVÁ OBLOHA, ČAJ";
        dinningRoomMenuObject.DinnerAllergens = "A: 03,10,12";
        dinningRoomMenuObject.DinnerSecond = "MLÉČNÝ DEZERT ";
        dinningRoomMenuObject.DinnerSecondAllergens = "A: 07,08b";
        mDinningRoomMenuList.add(dinningRoomMenuObject);

        dinningRoomMenuObject = new DinningRoomMenuObject();
        dinningRoomMenuObject.Date = new Date();
        dinningRoomMenuObject.DayOfWeek = "Pondělí";
        dinningRoomMenuObject.Breakfast = "FRGÁL, KAKAO GRANKO, OVOCE";
        dinningRoomMenuObject.BreakfastAllergens = "A: 01a,01c,03,07";
        dinningRoomMenuObject.Brunch = "TAVENÝ SÝR, PLETÝNKA, ČERSTVÁ ZELENINA, ČAJ ";
        dinningRoomMenuObject.BrunchAllergens = "A: 01a,01b,01c,06,07,11";
        dinningRoomMenuObject.Lunch = "BULHARSKÁ POLÉVKA, PIZZA SE SÝREM A ŠUNKOU, OVOCE, DŽUS ";
        dinningRoomMenuObject.LunchAllergens = "A: 01,01a,03,07,09";
        dinningRoomMenuObject.Snack = "CROISSANT, ČAJ ";
        dinningRoomMenuObject.SnackAllergens = "A: 01a,03,06,07";
        dinningRoomMenuObject.Dinner = "KUŘECÍ RAŽNIČÍ, BRAMBORY, ĎÁBELSKÝ DIP, ZELENINOVÁ OBLOHA, ČAJ";
        dinningRoomMenuObject.DinnerAllergens = "A: 03,10,12";
        dinningRoomMenuObject.DinnerSecond = "MLÉČNÝ DEZERT ";
        dinningRoomMenuObject.DinnerSecondAllergens = "A: 07,08b";
        mDinningRoomMenuList.add(dinningRoomMenuObject);

        dinningRoomMenuObject = new DinningRoomMenuObject();
        dinningRoomMenuObject.Date = new Date();
        dinningRoomMenuObject.DayOfWeek = "Pondělí";
        dinningRoomMenuObject.Breakfast = "FRGÁL, KAKAO GRANKO, OVOCE";
        dinningRoomMenuObject.BreakfastAllergens = "A: 01a,01c,03,07";
        dinningRoomMenuObject.Brunch = "TAVENÝ SÝR, PLETÝNKA, ČERSTVÁ ZELENINA, ČAJ ";
        dinningRoomMenuObject.BrunchAllergens = "A: 01a,01b,01c,06,07,11";
        dinningRoomMenuObject.Lunch = "BULHARSKÁ POLÉVKA, PIZZA SE SÝREM A ŠUNKOU, OVOCE, DŽUS ";
        dinningRoomMenuObject.LunchAllergens = "A: 01,01a,03,07,09";
        dinningRoomMenuObject.Snack = "CROISSANT, ČAJ ";
        dinningRoomMenuObject.SnackAllergens = "A: 01a,03,06,07";
        dinningRoomMenuObject.Dinner = "KUŘECÍ RAŽNIČÍ, BRAMBORY, ĎÁBELSKÝ DIP, ZELENINOVÁ OBLOHA, ČAJ";
        dinningRoomMenuObject.DinnerAllergens = "A: 03,10,12";
        dinningRoomMenuObject.DinnerSecond = "MLÉČNÝ DEZERT ";
        dinningRoomMenuObject.DinnerSecondAllergens = "A: 07,08b";
        mDinningRoomMenuList.add(dinningRoomMenuObject);

        dinningRoomMenuObject = new DinningRoomMenuObject();
        dinningRoomMenuObject.Date = new Date();
        dinningRoomMenuObject.DayOfWeek = "Pondělí";
        dinningRoomMenuObject.Breakfast = "FRGÁL, KAKAO GRANKO, OVOCE";
        dinningRoomMenuObject.BreakfastAllergens = "A: 01a,01c,03,07";
        dinningRoomMenuObject.Brunch = "TAVENÝ SÝR, PLETÝNKA, ČERSTVÁ ZELENINA, ČAJ ";
        dinningRoomMenuObject.BrunchAllergens = "A: 01a,01b,01c,06,07,11";
        dinningRoomMenuObject.Lunch = "BULHARSKÁ POLÉVKA, PIZZA SE SÝREM A ŠUNKOU, OVOCE, DŽUS ";
        dinningRoomMenuObject.LunchAllergens = "A: 01,01a,03,07,09";
        dinningRoomMenuObject.Snack = "CROISSANT, ČAJ ";
        dinningRoomMenuObject.SnackAllergens = "A: 01a,03,06,07";
        dinningRoomMenuObject.Dinner = "KUŘECÍ RAŽNIČÍ, BRAMBORY, ĎÁBELSKÝ DIP, ZELENINOVÁ OBLOHA, ČAJ";
        dinningRoomMenuObject.DinnerAllergens = "A: 03,10,12";
        dinningRoomMenuObject.DinnerSecond = "MLÉČNÝ DEZERT ";
        dinningRoomMenuObject.DinnerSecondAllergens = "A: 07,08b";
        mDinningRoomMenuList.add(dinningRoomMenuObject);

        dinningRoomMenuObject = new DinningRoomMenuObject();
        dinningRoomMenuObject.Date = new Date();
        dinningRoomMenuObject.DayOfWeek = "Pondělí";
        dinningRoomMenuObject.Breakfast = "FRGÁL, KAKAO GRANKO, OVOCE";
        dinningRoomMenuObject.BreakfastAllergens = "A: 01a,01c,03,07";
        dinningRoomMenuObject.Brunch = "TAVENÝ SÝR, PLETÝNKA, ČERSTVÁ ZELENINA, ČAJ ";
        dinningRoomMenuObject.BrunchAllergens = "A: 01a,01b,01c,06,07,11";
        dinningRoomMenuObject.Lunch = "BULHARSKÁ POLÉVKA, PIZZA SE SÝREM A ŠUNKOU, OVOCE, DŽUS ";
        dinningRoomMenuObject.LunchAllergens = "A: 01,01a,03,07,09";
        dinningRoomMenuObject.Snack = "CROISSANT, ČAJ ";
        dinningRoomMenuObject.SnackAllergens = "A: 01a,03,06,07";
        dinningRoomMenuObject.Dinner = "KUŘECÍ RAŽNIČÍ, BRAMBORY, ĎÁBELSKÝ DIP, ZELENINOVÁ OBLOHA, ČAJ";
        dinningRoomMenuObject.DinnerAllergens = "A: 03,10,12";
        dinningRoomMenuObject.DinnerSecond = "MLÉČNÝ DEZERT ";
        dinningRoomMenuObject.DinnerSecondAllergens = "A: 07,08b";
        mDinningRoomMenuList.add(dinningRoomMenuObject);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getChildFragmentManager();
        mDinningRoomMenuAdapter = new DinningRoomMenuAdapter(mDinningRoomMenuList, getContext(), getChildFragmentManager());
        recyclerView.setAdapter(mDinningRoomMenuAdapter);


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.color1_select_bottom_sheet_dialog, container, false);
//        return v;
//    }

    public class DinningRoomMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<DinningRoomMenuObject> mMainLightsList;
        private Context mContext;
        SharedPreferences mSharedPreferences;

        FragmentManager fragmentManager;

        public DinningRoomMenuAdapter(List<DinningRoomMenuObject> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new DinningRoomMenuAdapter.MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rozvrh_bottom_sheet_dialog_dinning_room_menu_adapter_item,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final DinningRoomMenuObject dinningRoomMenuObject = mMainLightsList.get(position);

            MainLightsViewHolder mainLightsViewHolder = (MainLightsViewHolder)holder;

            if(mSharedPreferences.getBoolean(BREAKFAST_SHOW, false)){

                mainLightsViewHolder.BreakfastTitle.setVisibility(View.VISIBLE);
                mainLightsViewHolder.Breakfast.setVisibility(View.VISIBLE);
                mainLightsViewHolder.BreakfastAllergens.setVisibility(View.VISIBLE);

            }else{

                mainLightsViewHolder.BreakfastTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Breakfast.setVisibility(View.GONE);
                mainLightsViewHolder.BreakfastAllergens.setVisibility(View.GONE);
            }

            if(mSharedPreferences.getBoolean(BRUNCH_SHOW, false)){

                mainLightsViewHolder.BrunchTitle.setVisibility(View.VISIBLE);
                mainLightsViewHolder.Brunch.setVisibility(View.VISIBLE);
                mainLightsViewHolder.BrunchAllergens.setVisibility(View.VISIBLE);

            }else{

                mainLightsViewHolder.BrunchTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Brunch.setVisibility(View.GONE);
                mainLightsViewHolder.BrunchAllergens.setVisibility(View.GONE);
            }

            if(mSharedPreferences.getBoolean(LUNCH_SHOW, true)){

                mainLightsViewHolder.LunchTitle.setVisibility(View.VISIBLE);
                mainLightsViewHolder.Lunch.setVisibility(View.VISIBLE);
                mainLightsViewHolder.LunchAllergens.setVisibility(View.VISIBLE);

            }else{

                mainLightsViewHolder.LunchTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Lunch.setVisibility(View.GONE);
                mainLightsViewHolder.LunchAllergens.setVisibility(View.GONE);
            }

            if(mSharedPreferences.getBoolean(SNACK_SHOW, false)){

                mainLightsViewHolder.SnackTitle.setVisibility(View.VISIBLE);
                mainLightsViewHolder.Snack.setVisibility(View.VISIBLE);
                mainLightsViewHolder.SnackAllergens.setVisibility(View.VISIBLE);

            }else{

                mainLightsViewHolder.SnackTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Snack.setVisibility(View.GONE);
                mainLightsViewHolder.SnackAllergens.setVisibility(View.GONE);
            }

            if(mSharedPreferences.getBoolean(DINNER_SHOW, false)){

                mainLightsViewHolder.DinnerTitle.setVisibility(View.VISIBLE);
                mainLightsViewHolder.Dinner.setVisibility(View.VISIBLE);
                mainLightsViewHolder.DinnerAllergens.setVisibility(View.VISIBLE);

            }else{

                mainLightsViewHolder.DinnerTitle.setVisibility(View.GONE);
                mainLightsViewHolder.Dinner.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerAllergens.setVisibility(View.GONE);
            }

            if(mSharedPreferences.getBoolean(DINNER_SECOND_SHOW, false)){

                mainLightsViewHolder.DinnerSecondTitle.setVisibility(View.VISIBLE);
                mainLightsViewHolder.DinnerSecond.setVisibility(View.VISIBLE);
                mainLightsViewHolder.DinnerSecondAllergens.setVisibility(View.VISIBLE);

            }else{

                mainLightsViewHolder.DinnerSecondTitle.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerSecond.setVisibility(View.GONE);
                mainLightsViewHolder.DinnerSecondAllergens.setVisibility(View.GONE);
            }

        mainLightsViewHolder.menuDate.setText("5.10.2022 (Pondělí)");
            mainLightsViewHolder.BreakfastTitle.setText("Snídaně");
            mainLightsViewHolder.Breakfast.setText(dinningRoomMenuObject.Breakfast);
            mainLightsViewHolder.BreakfastAllergens.setText(dinningRoomMenuObject.BreakfastAllergens.replace(",",", "));
            mainLightsViewHolder.BrunchTitle.setText("Přesnidávka");
            mainLightsViewHolder.Brunch.setText(dinningRoomMenuObject.Brunch );
            mainLightsViewHolder.BrunchAllergens.setText(dinningRoomMenuObject.BrunchAllergens.replace(",",", "));
            mainLightsViewHolder.LunchTitle.setText("Oběd");
            mainLightsViewHolder.Lunch.setText(dinningRoomMenuObject.Lunch  );
            mainLightsViewHolder.LunchAllergens.setText(dinningRoomMenuObject.LunchAllergens.replace(",",", "));
            mainLightsViewHolder.SnackTitle.setText("Svačina");
            mainLightsViewHolder.Snack.setText(dinningRoomMenuObject.Snack);
            mainLightsViewHolder.SnackAllergens.setText(dinningRoomMenuObject.SnackAllergens.replace(",",", "));
            mainLightsViewHolder.DinnerTitle.setText("Večeře");
            mainLightsViewHolder.Dinner.setText(dinningRoomMenuObject.Dinner);
            mainLightsViewHolder.DinnerAllergens.setText(dinningRoomMenuObject.DinnerAllergens.replace(",",", "));
            mainLightsViewHolder.DinnerSecondTitle.setText("Večeře II.");
            mainLightsViewHolder.DinnerSecond.setText(dinningRoomMenuObject.DinnerSecond);
            mainLightsViewHolder.DinnerSecondAllergens.setText(dinningRoomMenuObject.DinnerSecondAllergens.replace(",",", "));

   /*
            mainLightsViewHolder.menuDate.setText("5.10.2022 (Pondělí)");
            mainLightsViewHolder.BreakfastTitle.setText("");
            mainLightsViewHolder.Breakfast.setText("");
            mainLightsViewHolder.BreakfastAllergens.setText("");
            mainLightsViewHolder.BrunchTitle.setText("");
            mainLightsViewHolder.Brunch.setText("");
            mainLightsViewHolder.BrunchAllergens.setText("");
            mainLightsViewHolder.LunchTitle.setText("");
            mainLightsViewHolder.Lunch.setText("");
            mainLightsViewHolder.LunchAllergens.setText("");
            mainLightsViewHolder.SnackTitle.setText("");
            mainLightsViewHolder.Snack.setText("");
            mainLightsViewHolder.SnackAllergens.setText("");
            mainLightsViewHolder.DinnerTitle.setText("");
            mainLightsViewHolder.Dinner.setText("");
            mainLightsViewHolder.DinnerAllergens.setText("");
            mainLightsViewHolder.DinnerSecondTitle.setText("");
            mainLightsViewHolder.DinnerSecond.setText("");
            mainLightsViewHolder.DinnerSecondAllergens.setText("");*/
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

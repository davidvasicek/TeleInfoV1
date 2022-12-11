package com.example.teleinfo.modules;

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

public class Menu_AlergensDialog extends BottomSheetDialogFragment {

    private String foodAlergens;
    private String myAlergens;

    TextView textViewTextNumber01;
    TextView textViewText01;
    TextView textViewTextNumber01a;
    TextView textViewText01a;
    TextView textViewTextNumber01b;
    TextView textViewText01b;
    TextView textViewTextNumber01c;
    TextView textViewText01c;
    TextView textViewTextNumber01d;
    TextView textViewText01d;
    TextView textViewTextNumber01e;
    TextView textViewText01e;
    TextView textViewTextNumber01f;
    TextView textViewText01f;
    TextView textViewTextNumber01g;
    TextView textViewText01g;
    TextView textViewTextNumber02;
    TextView textViewText02;
    TextView textViewTextNumber03;
    TextView textViewText03;
    TextView textViewTextNumber04;
    TextView textViewText04;
    TextView textViewTextNumber05;
    TextView textViewText05;
    TextView textViewTextNumber06;
    TextView textViewText06;
    TextView textViewTextNumber07;
    TextView textViewText07;
    TextView textViewTextNumber08;
    TextView textViewText08;
    TextView textViewTextNumber08a;
    TextView textViewText08a;
    TextView textViewTextNumber08b;
    TextView textViewText08b;
    TextView textViewTextNumber08c;
    TextView textViewText08c;
    TextView textViewTextNumber08d;
    TextView textViewText08d;
    TextView textViewTextNumber08e;
    TextView textViewText08e;
    TextView textViewTextNumber08f;
    TextView textViewText08f;
    TextView textViewTextNumber08g;
    TextView textViewText08g;
    TextView textViewTextNumber08h;
    TextView textViewText08h;
    TextView textViewTextNumber09;
    TextView textViewText09;
    TextView textViewTextNumber10;
    TextView textViewText10;
    TextView textViewTextNumber11;
    TextView textViewText11;
    TextView textViewTextNumber12;
    TextView textViewText12;
    TextView textViewTextNumber13;
    TextView textViewText13;
    TextView textViewTextNumber14;
    TextView textViewText14;

    public Menu_AlergensDialog(String myAlergens, String foodAlergens) {

        this.foodAlergens = foodAlergens;
        this.myAlergens = myAlergens;

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.menu_alergens_dialog, null);
        dialog.setContentView(contentView);

        textViewTextNumber01 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01);
        textViewText01 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01);
        textViewTextNumber01a = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01a);
        textViewText01a = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01a);
        textViewTextNumber01b = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01b);
        textViewText01b = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01b);
        textViewTextNumber01c = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01c);
        textViewText01c = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01c);
        textViewTextNumber01d = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01d);
        textViewText01d = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01d);
        textViewTextNumber01e = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01e);
        textViewText01e = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01e);
        textViewTextNumber01f = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01f);
        textViewText01f = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01f);
        textViewTextNumber01g = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber01g);
        textViewText01g = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText01g);
        textViewTextNumber02 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber02);
        textViewText02 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText02);
        textViewTextNumber03 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber03);
        textViewText03 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText03);
        textViewTextNumber04 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber04);
        textViewText04 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText04);
        textViewTextNumber05 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber05);
        textViewText05 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText05);
        textViewTextNumber06 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber06);
        textViewText06 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText06);
        textViewTextNumber07 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber07);
        textViewText07 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText07);
        textViewTextNumber08 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08);
        textViewText08 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08);
        textViewTextNumber08a = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08a);
        textViewText08a = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08a);
        textViewTextNumber08b = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08b);
        textViewText08b = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08b);
        textViewTextNumber08c = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08c);
        textViewText08c = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08c);
        textViewTextNumber08d = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08d);
        textViewText08d = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08d);
        textViewTextNumber08e = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08e);
        textViewText08e = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08e);
        textViewTextNumber08f = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08f);
        textViewText08f = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08f);
        textViewTextNumber08g = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08g);
        textViewText08g = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08g);
        textViewTextNumber08h = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber08h);
        textViewText08h = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText08h);
        textViewTextNumber09 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber09);
        textViewText09 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText09);
        textViewTextNumber10 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber10);
        textViewText10 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText10);
        textViewTextNumber11 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber11);
        textViewText11 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText11);
        textViewTextNumber12 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber12);
        textViewText12 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText12);
        textViewTextNumber13 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber13);
        textViewText13 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText13);
        textViewTextNumber14 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewTextNumber14);
        textViewText14 = (TextView)contentView.findViewById(R.id.menuAlergensDialogTextViewText14);


        //if(foodAlergens.contains("01")){

            //textViewTextNumber01.setTextColor(getResources().getColor(R.color.red700colorAccent));
            //textViewText01.setTextColor(getResources().getColor(R.color.red700colorAccent));
        //}


        String[] foodAlergensStringSplit = foodAlergens.split(",");

        Log.e(TAG, "koko: " + foodAlergensStringSplit);


        if(foodAlergens.contains("01a")){

            textViewTextNumber01a.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01a.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("01b")){

            textViewTextNumber01b.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01b.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("01c")){

            textViewTextNumber01c.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01c.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("01d")){

            textViewTextNumber01d.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01d.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("01e")){

            textViewTextNumber01e.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01e.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("01f")){

            textViewTextNumber01f.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01f.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("01g")){

            textViewTextNumber01g.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText01g.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("02")){

            textViewTextNumber02.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText02.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("03")){

            textViewTextNumber03.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText03.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("04")){

            textViewTextNumber04.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText04.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("05")){

            textViewTextNumber05.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText05.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("06")){

            textViewTextNumber06.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText06.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("07")){

            textViewTextNumber07.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText07.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        //if(foodAlergens.contains("08")){

            //textViewTextNumber08.setTextColor(getResources().getColor(R.color.red700colorAccent));
            //textViewText08.setTextColor(getResources().getColor(R.color.red700colorAccent));
        //}

        if(foodAlergens.contains("08a")){

            textViewTextNumber08a.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08a.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("08b")){

            textViewTextNumber08b.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08b.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("08c")){

            textViewTextNumber08c.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08c.setTextColor(getResources().getColor(R.color.red700colorAccent));
         }

        if(foodAlergens.contains("08d")){

            textViewTextNumber08d.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08d.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("08e")){

            textViewTextNumber08e.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08e.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("08f")){

            textViewTextNumber08f.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08f.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("08g")){

            textViewTextNumber08g.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08g.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("08h")){

            textViewTextNumber08h.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText08h.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("09")){

            textViewTextNumber09 .setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText09.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("10")){

            textViewTextNumber10.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText10.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("11")){

            textViewTextNumber11.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText11.setTextColor(getResources().getColor(R.color.red700colorAccent));
         }

        if(foodAlergens.contains("12")){

            textViewTextNumber12.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText12.setTextColor(getResources().getColor(R.color.red700colorAccent));
         }

        if(foodAlergens.contains("13")){

            textViewTextNumber13.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText13.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }

        if(foodAlergens.contains("14")){

            textViewTextNumber14.setTextColor(getResources().getColor(R.color.red700colorAccent));
            textViewText14.setTextColor(getResources().getColor(R.color.red700colorAccent));
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View contentView = inflater.inflate(R.layout.dialog_easy_radio_group_list, container,false);
//        return contentView;
//    }

}


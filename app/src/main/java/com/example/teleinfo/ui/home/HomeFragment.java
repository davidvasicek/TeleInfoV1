package com.example.teleinfo.ui.home;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT;
import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.DEVICE_IS_PAIRED;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;
import static com.example.teleinfo.parameters.MainParameters.SHOW_BREAKS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleinfo.R;
import com.example.teleinfo.databinding.FragmentHomeBinding;
import com.example.teleinfo.rozvrh.ClassSelectBottomSheetDialog;
import com.example.teleinfo.rozvrh.ClassroomSelectBottomSheetDialog;
import com.example.teleinfo.rozvrh.FindSchoolroomBottomSheetDialog;
import com.example.teleinfo.rozvrh.RozvrhNove1;
import com.example.teleinfo.rozvrh.TeacherSelectBottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    LinearLayout linearLayoutDailyTimetableTeachers;
    LinearLayout linearLayoutDailyTimetableSchoolRooms;
    LinearLayout linearLayoutDailyTimetableClasses;
    LinearLayout linearLayoutWeeklyTimetableTeachers;
    LinearLayout linearLayoutWeeklyTimetableSchoolRooms;
    LinearLayout linearLayoutWeeklyTimetableClasses;




    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();



             //   TextView iiiiiiiiiii = (TextView) root.findViewById(R.id.iiiiiiiiiii);
       // makeRoundCorner(getContext().getResources().getColor(R.color.red700colorAccent),50,iiiiiiiiiii,0,0);





        linearLayoutDailyTimetableTeachers = (LinearLayout)root.findViewById(R.id.dddddddddddddddLinearLayoutDailyTimetableTeachers);
        linearLayoutDailyTimetableSchoolRooms = (LinearLayout)root.findViewById(R.id.dddddddddddddddLinearLayoutDailyTimetableSchoolRooms);
        linearLayoutDailyTimetableClasses = (LinearLayout)root.findViewById(R.id.dddddddddddddddLinearLayoutDailyTimetableClasses);
        linearLayoutWeeklyTimetableTeachers = (LinearLayout)root.findViewById(R.id.dddddddddddddddLinearLayoutWeeklyTimetableTeachers);
        linearLayoutWeeklyTimetableSchoolRooms = (LinearLayout)root.findViewById(R.id.dddddddddddddddLinearLayoutWeeklyTimetableSchoolRooms);
        linearLayoutWeeklyTimetableClasses = (LinearLayout)root.findViewById(R.id.dddddddddddddddLinearLayoutWeeklyTimetableClasses);




        linearLayoutDailyTimetableTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), RozvrhNove1.class);
                intent.putExtra("action", "dailyByTeachers");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        linearLayoutDailyTimetableSchoolRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), RozvrhNove1.class);
                intent.putExtra("action", "dailyByschoolroom");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        linearLayoutDailyTimetableClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), RozvrhNove1.class);
                intent.putExtra("action", "dailyByClass");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        linearLayoutWeeklyTimetableTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(getContext(), RozvrhNove1.class);
                //intent.putExtra("action", "weeklyByTeacher");
                //intent.putExtra("name", "Vašíček_David_Vaš");
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(intent);

                // Intent intent = new Intent(getContext(), RozvrhNove1.class);
                // intent.putExtra("action", "weeklyByTeacher");
                // intent.putExtra("name", "Vašíček_David_Vaš");
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // startActivity(intent);

                //Intent intent = new Intent(getContext(), ListTeachersActivity.class);
                //startActivity(intent);

                TeacherSelectBottomSheetDialog teacherSelectBottomSheetDialog = new TeacherSelectBottomSheetDialog();
                teacherSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutWeeklyTimetableSchoolRooms.setOnClickListener(new View.OnClickListener() {
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


                ClassroomSelectBottomSheetDialog classroomSelectBottomSheetDialog = new ClassroomSelectBottomSheetDialog(Schoolroom, 5, mSharedPreferences.getString(CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT, "#616161"));
                classroomSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");

                // Intent intent = new Intent(getContext(), ListTeachersActivity.class);
                // startActivity(intent);

            }
        });

        linearLayoutWeeklyTimetableClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> tridy = new ArrayList<>();

                tridy.add("1.A");
                tridy.add("1.B");
                tridy.add("1.C");
                tridy.add("1.D");
                tridy.add("2.A");
                tridy.add("2.B");
                tridy.add("2.C");
                tridy.add("2.D");
                tridy.add("3.A");
                tridy.add("3.B");
                tridy.add("3.C");
                tridy.add("3.D");
                tridy.add("4.A");
                tridy.add("4.B");
                tridy.add("4.C");
                tridy.add("4.D");



                ClassSelectBottomSheetDialog classSelectBottomSheetDialog = new ClassSelectBottomSheetDialog(tridy, 4, mSharedPreferences.getString(CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT, "#616161"));
                classSelectBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");

                // Intent intent = new Intent(getContext(), ListTeachersActivity.class);
                // startActivity(intent);

            }
        });





        //card_view_rozvrh_my.setCardBackgroundColor(Color.parseColor(mSharedPreferences.getString(CURRENT_THEME, "#212121")));



       // GradientDrawable gd = new GradientDrawable(
         //       GradientDrawable.Orientation.BL_TR,
         //       new int[] {0xFFF48FB1,0xFFEC407A,0xFFD81B60});
        //gd.setCornerRadius(20f);

       // card_view_rozvrh_dle_ucitelu.setBackground(gd);



      /*

        card_view_najdi_ucebnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> Schoolhour = new ArrayList<>();

                Schoolhour.add("0");
                Schoolhour.add("1");
                Schoolhour.add("2");
                Schoolhour.add("3");
                Schoolhour.add("4");
                Schoolhour.add("5");
                Schoolhour.add("6a");
                Schoolhour.add("6b");
                Schoolhour.add("7");
                Schoolhour.add("8");
                Schoolhour.add("9");

                FindSchoolroomBottomSheetDialog findSchoolroomBottomSheetDialog = new FindSchoolroomBottomSheetDialog(Schoolhour, 5, mSharedPreferences.getString(CURRENT_COLOR_TAB_LAYOUT_NON_SELECTED_TEXT, "#616161"));
                findSchoolroomBottomSheetDialog.show(getChildFragmentManager(), "exampleBottomSheet");

                // Intent intent = new Intent(getContext(), ListTeachersActivity.class);
                // startActivity(intent);

            }
        });
*/




      //  final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    public static void makeRoundCorner(int bgcolor,int radius,View v,int strokeWidth,int strokeColor)
    {
        GradientDrawable gdDefault = new GradientDrawable();
        gdDefault.setColor(bgcolor);
        gdDefault.setCornerRadius(radius);
        gdDefault.setStroke(strokeWidth, strokeColor);
        v.setBackground(gdDefault);
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
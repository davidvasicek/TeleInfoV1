package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.COLOR_CLASSIC_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_DINNINGROOM_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_MIDDAY_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_MORNING_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_PORTER_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.COLOR_PPP_SUPERVISION;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.teleinfo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActivityViewBottomSheetDialog extends BottomSheetDialogFragment {

    LinearLayout eventLesson;
    TextView classRoom;
    TextView subjectTitle;
    TextView nameSurname;
    TextView classclass;
    TextView classStudents;
    TextView cycle;
    TextView specialniUdaloslt;
    RelativeLayout relativePruhNecekanaUdalost;
    LinearLayout royvrhUcebna;

    HourObject hourObject;
    SupervisionObject supervisionObject;
    OthersObject othersObject;
    String event;

    LinearLayout eventSupervision;
    TextView subjectTitle1;
    TextView subjectTime;
    TextView textUvod;
    RelativeLayout relativePruhNecekanaUdaslostjj;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public ActivityViewBottomSheetDialog(HourObject hourObject, SupervisionObject supervisionObject, OthersObject othersObject, String event) {

        this.hourObject = hourObject;
        this.supervisionObject = supervisionObject;
        this.othersObject = othersObject;

        this.event = event;


    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        SharedPreferences shrPref = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        //super.setupDialog(dialog, new GetThemeStyle().getThemeStyle(shrPref.getString(CURRENT_THEME, "#212121")));
        View contentView = View.inflate(getContext(), R.layout.rozvrh_activity_view_bottom_sheet_dialog, null);
        dialog.setContentView(contentView);


        eventLesson= (LinearLayout)contentView.findViewById(R.id.eventLesson);
        eventSupervision= (LinearLayout)contentView.findViewById(R.id.eventSupervision);

        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        if(event.compareTo("lesson") == 0){

            eventLesson.setVisibility(View.VISIBLE);
            eventSupervision.setVisibility(View.GONE);

            classRoom = (TextView)contentView.findViewById(R.id.classRoom);
            subjectTitle = (TextView)contentView.findViewById(R.id.subjectTitle);
            nameSurname = (TextView)contentView.findViewById(R.id.nameSurname);
            classclass = (TextView)contentView.findViewById(R.id.classclass);
            classStudents = (TextView)contentView.findViewById(R.id.classStudents);
            cycle = (TextView)contentView.findViewById(R.id.cycle);
            specialniUdaloslt= (TextView)contentView.findViewById(R.id.specialniUdaloslt);
            relativePruhNecekanaUdalost= (RelativeLayout)contentView.findViewById(R.id.relativePruhNecekanaUdalost);
            royvrhUcebna= (LinearLayout)contentView.findViewById(R.id.royvrhUcebna);

            if(hourObject.Ucebna.compareTo("unknown") == 0){

                royvrhUcebna.setVisibility(View.GONE);

            }


            classRoom.setText(hourObject.Ucebna);
            subjectTitle.setText(hourObject.udalostPopis);
            nameSurname.setText(hourObject.Ucitel);
            classclass.setText(hourObject.Trida);
            classStudents.setText(hourObject.Zaci);
            cycle.setText(hourObject.Cyklus);

            if(hourObject.neocekavanaUdalost.compareTo("unknown") == 0){

                specialniUdaloslt.setVisibility(View.GONE);
                relativePruhNecekanaUdalost.setVisibility(View.INVISIBLE);

            }else{

                specialniUdaloslt.setVisibility(View.VISIBLE);
                relativePruhNecekanaUdalost.setVisibility(View.VISIBLE);

                String text  = hourObject.neocekavanaUdalost.replace("_",": ").replace("<span class=AbsZdroj>", "<font color='" + getResources().getColor(R.color.timetableColorRedTeacher) + "'>").replace("</span>","</font>");
                specialniUdaloslt.setText( Html.fromHtml(text), TextView.BufferType.SPANNABLE);

                if(hourObject.Status.compareTo("Spojena hodina") == 0){

                    relativePruhNecekanaUdalost.setBackgroundColor(getResources().getColor(R.color.timetableColorHourSpojeni));

                }else if(hourObject.Status.compareTo("Naduvazkova hodina") == 0){

                    relativePruhNecekanaUdalost.setBackgroundColor(getResources().getColor(R.color.timetableColorHourNaduvazek));

                }else if(hourObject.Status.compareTo("Presunuta hodina") == 0){

                    relativePruhNecekanaUdalost.setBackgroundColor(getResources().getColor(R.color.timetableColorHourPresunuto));

                }else if(hourObject.Status.compareTo("Hodina v PPP") == 0){

                    relativePruhNecekanaUdalost.setBackgroundColor(getResources().getColor(R.color.timetableColorHourPPP));

                }
            }


        }else if(event.compareTo("supervision") == 0){

            eventLesson.setVisibility(View.GONE);
            eventSupervision.setVisibility(View.VISIBLE);

            subjectTitle1 = (TextView)contentView.findViewById(R.id.subjectTitle1);
            subjectTime = (TextView)contentView.findViewById(R.id.subjectTime);
            relativePruhNecekanaUdaslostjj = (RelativeLayout)contentView.findViewById(R.id.relativePruhNecekanaUdaslostjj);
            textUvod = (TextView)contentView.findViewById(R.id.textUvod);


            subjectTitle1.setText(supervisionObject.dozorMisto);
            subjectTime.setText(supervisionObject.dozorCas);

            if(supervisionObject.key.compareTo("d_MorningSupervision") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_MORNING_SUPERVISION, "#C5E1A5")));

            }else if(supervisionObject.key.compareTo("d_Vrat") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_PORTER_SUPERVISION, "#B2DFDB")));

            }else if(supervisionObject.key.compareTo("d_PPP") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_PPP_SUPERVISION, "#B2DFDB")));

            }else if(supervisionObject.key.compareTo("d_6pa") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_MIDDAY_SUPERVISION, "#9FA8DA")));

            }else if(supervisionObject.key.compareTo("d_6pb") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_MIDDAY_SUPERVISION, "#9FA8DA")));

            }else if(supervisionObject.key.compareTo("d_SupervisionLunch6a_1") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

            }else if(supervisionObject.key.compareTo("d_SupervisionLunch6a_2") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

            }else if(supervisionObject.key.compareTo("d_SupervisionLunch6b_1") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

            }else if(supervisionObject.key.compareTo("d_SupervisionLunch6b_2") == 0){

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_DINNINGROOM_SUPERVISION, "#B3E5FC")));

            }else{

                subjectTime.setTextColor(Color.parseColor(mSharedPreferences.getString(COLOR_CLASSIC_SUPERVISION, "#BCAAA4")));

            }







            if(supervisionObject.Status.compareTo("Suplovany dozor") == 0){

                relativePruhNecekanaUdaslostjj.setVisibility(View.VISIBLE);
                relativePruhNecekanaUdaslostjj.setBackgroundColor(getResources().getColor(R.color.timetableColorHourSpojeni));

            }else if(supervisionObject.Status.compareTo("Odpadly dozor") == 0){


                relativePruhNecekanaUdaslostjj.setVisibility(View.INVISIBLE);
                relativePruhNecekanaUdaslostjj.setBackgroundColor(getResources().getColor(R.color.timetableColorHourSpojeni));

                subjectTitle1.setTextColor(Color.parseColor("#bdbdbd"));
                subjectTime.setTextColor(Color.parseColor("#bdbdbd"));
                textUvod.setTextColor(Color.parseColor("#bdbdbd"));


            }else{

                relativePruhNecekanaUdaslostjj.setVisibility(View.INVISIBLE);

            }



        }else if(event.compareTo("other") == 0){

            eventLesson.setVisibility(View.VISIBLE);
            eventSupervision.setVisibility(View.GONE);

            classRoom = (TextView)contentView.findViewById(R.id.classRoom);
            subjectTitle = (TextView)contentView.findViewById(R.id.subjectTitle);
            nameSurname = (TextView)contentView.findViewById(R.id.nameSurname);
            classclass = (TextView)contentView.findViewById(R.id.classclass);
            classStudents = (TextView)contentView.findViewById(R.id.classStudents);
            cycle = (TextView)contentView.findViewById(R.id.cycle);
            specialniUdaloslt= (TextView)contentView.findViewById(R.id.specialniUdaloslt);
            relativePruhNecekanaUdalost= (RelativeLayout)contentView.findViewById(R.id.relativePruhNecekanaUdalost);
            royvrhUcebna= (LinearLayout)contentView.findViewById(R.id.royvrhUcebna);

            if(othersObject.Ucebna.compareTo("unknown") == 0){

                royvrhUcebna.setVisibility(View.GONE);

            }


            classRoom.setText(othersObject.Ucebna);
            subjectTitle.setText(othersObject.udalostPopis);
            nameSurname.setText(othersObject.Ucitel);
            classclass.setText(othersObject.Trida);
            classStudents.setText(othersObject.Zaci);
            cycle.setText(othersObject.Cyklus);

                specialniUdaloslt.setVisibility(View.GONE);
                relativePruhNecekanaUdalost.setVisibility(View.INVISIBLE);




        }




 /*

            eventSupervision = (LinearLayout)contentView.findViewById(R.id.eventSupervision);
        eventLesson = (LinearLayout)contentView.findViewById(R.id.eventLesson);
        subjectTitle1 = (TextView)contentView.findViewById(R.id.subjectTitle1);
        subjectTime = (TextView)contentView.findViewById(R.id.subjectTime);



if(event.compareTo("supervision") == 0){

                    //subjectTitle1.setText(supervisionObject.Location);
                    //subjectTime.setText(supervisionObject.Time.replace("-", " - "));

                    eventSupervision.setVisibility(View.VISIBLE);
                    eventLesson.setVisibility(View.GONE);

                }else{

                    eventSupervision.setVisibility(View.GONE);
                    eventLesson.setVisibility(View.VISIBLE);

                    classRoom.setText(hourObject.Ucebna);
                    subjectTitle.setText(hourObject.udalostPopis);
                   // nameSurname.setText(hourObject.s);
                    classclass.setText(hourObject.Trida);
                   // classStudents.setText(hourObject.Skupina);
                    cycle.setText(hourObject.Cyklus);

                }


*/


    }


//    Original onCreate před nastavením schématu
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View contentView = inflater.inflate(R.layout.sensor_list_activity_setting_bottom_sheet_dialog, container, false);
//
//
//
//        return contentView;
//    }



}
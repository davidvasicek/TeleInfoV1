package com.example.teleinfo.ui.notifications;

import static com.example.teleinfo.parameters.MainParameters.DEVICE_IS_PAIRED;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleinfo.R;
import com.example.teleinfo.administration.__ActivityMainAdministration;
import com.example.teleinfo.databinding.FragmentNotificationsBinding;
import com.example.teleinfo.modules.Apologies_ApologiesMainActivity;
import com.example.teleinfo.modules.Apologies_ApologiesObject;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenu;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenuSettings;
import com.example.teleinfo.rozvrh.BottomSheetDialogTeacherCard;
import com.example.teleinfo.settings._ActivityMainSettings;

public class NotificationsFragment extends Fragment {

    CardView card_view_settingsLunch;
    CardView card_view_teacher_card;
    CardView card_view_teacher_list;
    CardView card_view_telephone_contact;
    CardView card_view_schooll_class_list;
    CardView card_view_schedule_of_the_year;
    CardView card_view_school_plan;
    CardView card_view_supervision;
    CardView card_view_administration;
    CardView card_view_erasepaire;
    CardView card_view_apologies;

    LinearLayout linearLayoutSchoolCanteenMenu;
    LinearLayout linearLayoutApologiesModule;
    LinearLayout linearLayoutUnregisteredHours;
    LinearLayout linearLayoutSchoolFloorPlan;
    LinearLayout linearLayoutTeacherList;
    LinearLayout linearLayoutSchoolRoomsList;
    LinearLayout linearLayoutTelephoneContact;
    LinearLayout linearLayoutAppSettings;
    LinearLayout linearLayoutAdministration;





    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        card_view_settingsLunch = (CardView)root.findViewById(R.id.card_view_settingsLunch);
        card_view_teacher_card = (CardView)root.findViewById(R.id.card_view_teacher_card);
        card_view_teacher_list = (CardView)root.findViewById(R.id.card_view_teacher_list);
        card_view_telephone_contact = (CardView)root.findViewById(R.id.card_view_telephone_contact);
        card_view_schooll_class_list = (CardView)root.findViewById(R.id.card_view_schooll_class_list);
        card_view_schedule_of_the_year = (CardView)root.findViewById(R.id.card_view_schedule_of_the_year);
        card_view_school_plan = (CardView)root.findViewById(R.id.card_view_school_plan);
        card_view_supervision = (CardView)root.findViewById(R.id.card_view_supervision);
        card_view_administration = (CardView)root.findViewById(R.id.card_view_administration);
        card_view_erasepaire = (CardView)root.findViewById(R.id.card_view_erasepaire);
        card_view_apologies = (CardView)root.findViewById(R.id.card_view_apologies);

        linearLayoutSchoolCanteenMenu = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutSchoolCanteenMenu);
        linearLayoutApologiesModule = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutApologiesModule);
        linearLayoutUnregisteredHours = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutUnregisteredHours);
        linearLayoutSchoolFloorPlan = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutSchoolFloorPlan);
        linearLayoutTeacherList = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutTeacherList);
        linearLayoutSchoolRoomsList = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutSchoolRoomsList);
        linearLayoutTelephoneContact = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutTelephoneContact);
        linearLayoutAppSettings = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutAppSettings);
        linearLayoutAdministration = (LinearLayout)root.findViewById(R.id.ttttttttttttttLinearLayoutAdministration);


        mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();


        linearLayoutSchoolCanteenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogDiningRoomMenu bottomSheetDialogDiningRoomMenu = new BottomSheetDialogDiningRoomMenu();
                bottomSheetDialogDiningRoomMenu.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        linearLayoutApologiesModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        linearLayoutUnregisteredHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        linearLayoutSchoolFloorPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        linearLayoutTeacherList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        linearLayoutSchoolRoomsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");


            }
        });

        linearLayoutTelephoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        linearLayoutAppSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), _ActivityMainSettings.class);
                startActivity(intent);

            }
        });


        linearLayoutAdministration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        card_view_settingsLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogDiningRoomMenuSettings bottomSheetDialogDiningRoomMenuSettings = new BottomSheetDialogDiningRoomMenuSettings();
                bottomSheetDialogDiningRoomMenuSettings.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });


        card_view_teacher_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });



        card_view_teacher_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });


        card_view_telephone_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });


        card_view_schooll_class_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });


        card_view_schedule_of_the_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });


        card_view_school_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });


        card_view_supervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        card_view_administration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), __ActivityMainAdministration.class);
                startActivity(intent);

            }
        });

        card_view_erasepaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putBoolean(DEVICE_IS_PAIRED, false );
                mEditor.commit();

            }
        });

        card_view_apologies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Apologies_ApologiesMainActivity.class);
                startActivity(intent);

            }
        });












        // final TextView textView = binding.textNotifications;
       // notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
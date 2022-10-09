package com.example.teleinfo.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleinfo.R;
import com.example.teleinfo.administration.__ActivityMainAdministration;
import com.example.teleinfo.databinding.FragmentNotificationsBinding;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenu;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenuSettings;
import com.example.teleinfo.rozvrh.BottomSheetDialogTeacherCard;
import com.example.teleinfo.settings._ActivityMainSettings;

public class NotificationsFragment extends Fragment {

    CardView card_view_settings;
    CardView card_view_settingsLunch;
    CardView card_view_menu;
    CardView card_view_teacher_card;
    CardView card_view_school_room_list;
    CardView card_view_teacher_list;
    CardView card_view_telephone_contact;
    CardView card_view_schooll_class_list;
    CardView card_view_schedule_of_the_year;
    CardView card_view_school_plan;
    CardView card_view_supervision;
    CardView card_view_administration;



    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        card_view_settings = (CardView)root.findViewById(R.id.card_view_settings);
        card_view_settingsLunch = (CardView)root.findViewById(R.id.card_view_settingsLunch);
        card_view_menu = (CardView)root.findViewById(R.id.card_view_menu);
        card_view_teacher_card = (CardView)root.findViewById(R.id.card_view_teacher_card);
        card_view_school_room_list = (CardView)root.findViewById(R.id.card_view_school_room_list);
        card_view_teacher_list = (CardView)root.findViewById(R.id.card_view_teacher_list);
        card_view_telephone_contact = (CardView)root.findViewById(R.id.card_view_telephone_contact);
        card_view_schooll_class_list = (CardView)root.findViewById(R.id.card_view_schooll_class_list);
        card_view_schedule_of_the_year = (CardView)root.findViewById(R.id.card_view_schedule_of_the_year);
        card_view_school_plan = (CardView)root.findViewById(R.id.card_view_school_plan);
        card_view_supervision = (CardView)root.findViewById(R.id.card_view_supervision);
        card_view_administration = (CardView)root.findViewById(R.id.card_view_administration);



        card_view_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), _ActivityMainSettings.class);
                startActivity(intent);

            }
        });

        card_view_settingsLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogDiningRoomMenuSettings bottomSheetDialogDiningRoomMenuSettings = new BottomSheetDialogDiningRoomMenuSettings();
                bottomSheetDialogDiningRoomMenuSettings.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        card_view_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogDiningRoomMenu bottomSheetDialogDiningRoomMenu = new BottomSheetDialogDiningRoomMenu();
                bottomSheetDialogDiningRoomMenu.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        card_view_teacher_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

            }
        });

        card_view_school_room_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                //bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

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
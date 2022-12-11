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
import com.example.teleinfo.modules.ClassRooms_BottomSheetDialogClassRoomsList;
import com.example.teleinfo.modules.FloorPlan_MainActivity;
import com.example.teleinfo.modules.Menu_MainActivity;
import com.example.teleinfo.modules.UnregisteredHours_MainActivity;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenu;
import com.example.teleinfo.rozvrh.BottomSheetDialogDiningRoomMenuSettings;
import com.example.teleinfo.rozvrh.BottomSheetDialogTeacherCard;
import com.example.teleinfo.settings._ActivityMainSettings;

public class NotificationsFragment extends Fragment {


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

                //BottomSheetDialogDiningRoomMenu bottomSheetDialogDiningRoomMenu = new BottomSheetDialogDiningRoomMenu();
                //bottomSheetDialogDiningRoomMenu.show(getChildFragmentManager(), "exampleBottomSheet");
                Intent intent = new Intent(getContext(), Menu_MainActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutApologiesModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Apologies_ApologiesMainActivity.class);
                startActivity(intent);

            }
        });

        linearLayoutUnregisteredHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), UnregisteredHours_MainActivity.class);
                startActivity(intent);

            }
        });

        linearLayoutSchoolFloorPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), FloorPlan_MainActivity.class);
                startActivity(intent);

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


                ClassRooms_BottomSheetDialogClassRoomsList classRooms_bottomSheetDialogClassRoomsList = new ClassRooms_BottomSheetDialogClassRoomsList();
                classRooms_bottomSheetDialogClassRoomsList.show(getChildFragmentManager(), "exampleBottomSheet");


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
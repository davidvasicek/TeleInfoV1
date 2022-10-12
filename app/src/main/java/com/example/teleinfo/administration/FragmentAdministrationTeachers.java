package com.example.teleinfo.administration;

import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

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
import com.example.teleinfo.rozvrh.BottomSheetDialogTeacherCard;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdministrationTeachers extends Fragment {
    
    private List<String> mTeachersAdministrationList;
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
        mTeachersAdministrationList.add("Ing. David Vašíček");





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

        private List<String> mMainLightsList;
        private Context mContext;
        SharedPreferences mSharedPreferences;

        FragmentManager fragmentManager;

        public TeachersAdministrationAdapter(List<String> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new TeachersAdministrationAdapter.MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.administration_fragment_teachers_adapter_item,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final String dinningRoomMenuObject = mMainLightsList.get(position);

            MainLightsViewHolder mainLightsViewHolder = (MainLightsViewHolder)holder;


            mainLightsViewHolder.teacherNamedsf.setText(dinningRoomMenuObject);

            //TODO p5ed2lat na klik na linear LAzout
            mainLightsViewHolder.teacherNamedsf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BottomSheetDialogTeacherCard bottomSheetDialogTeacherCard = new BottomSheetDialogTeacherCard();
                    bottomSheetDialogTeacherCard.show(getChildFragmentManager(), "exampleBottomSheet");

                }
            });

            mainLightsViewHolder.teacherNamedsf.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    BottomSheetDialogTeacherEditNew bottomSheetDialogTeacherEditNew = new BottomSheetDialogTeacherEditNew();
                    bottomSheetDialogTeacherEditNew.show(getChildFragmentManager(), "exampleBottomSheet");


                    return false;
                }
            });



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

            TextView teacherNamedsf;


            public MainLightsViewHolder(View itemView){
                super(itemView);

                teacherNamedsf = itemView.findViewById(R.id.teacherNamedsf);


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










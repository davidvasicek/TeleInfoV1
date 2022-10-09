package com.example.teleinfo.rozvrh;

import static com.example.teleinfo.parameters.MainParameters.CURRENT_THEME;
import static com.example.teleinfo.parameters.MainParameters.SHARED_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teleinfo.R;
import com.example.teleinfo.parameters.GetThemeStyle;

import java.util.ArrayList;
import java.util.List;

public class ListTeachersActivity extends AppCompatActivity {

    private List<String> mMainLightsList_original;
    private MainTeachersAdapter mMainTeachersAdapter;

    RecyclerView recyclerView;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharPref = getApplication().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setTheme(new GetThemeStyle().getThemeStyle(mSharPref.getString(CURRENT_THEME, "#212121")));;
        setContentView(R.layout.rozvrh_rozvrh_smazat);


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "seznam učitelů" + "</small>"));


        recyclerView = (RecyclerView)findViewById(R.id.recyclerTeachers);


        mMainLightsList_original = new ArrayList<>();
        
        mMainLightsList_original.add("Bardaševská_Marcela_Bam");
        mMainLightsList_original.add("Bidláková_Svatava_Bd");
        mMainLightsList_original.add("Bureš_Miroslav_Bur");
        mMainLightsList_original.add("Cinner_Radovan_Cin");
        mMainLightsList_original.add("Dědičík_Pavel_Dě");
        mMainLightsList_original.add("Ďurian_Čeněk_Ďu");
        mMainLightsList_original.add("Dušková_Barbora_Du");
        mMainLightsList_original.add("Frömmelová_Svatava_Fro");
        mMainLightsList_original.add("Glacová_Lenka_Gla");
        mMainLightsList_original.add("Gocalová_Daniela_Go");
        mMainLightsList_original.add("Halouzka_Pavel_Ha");
        mMainLightsList_original.add("Jedlička_Václav_Jed");
        mMainLightsList_original.add("Johnová_Petra_Joh");
        mMainLightsList_original.add("Justová_Emília_Ju");
        mMainLightsList_original.add("Knápek_František_Kná");
        mMainLightsList_original.add("Koníček_Michal_Kon");
        mMainLightsList_original.add("Kopřivová_Michaela_Kop");
        mMainLightsList_original.add("Koždoň_Pavel_Kož");
        mMainLightsList_original.add("Krupová _Lenka_Kru");
        mMainLightsList_original.add("Kubala_Jiří_Kub");
        mMainLightsList_original.add("Lašková_Kateřina_Laš");
        mMainLightsList_original.add("Lauterbach_Filip_Lau");
        mMainLightsList_original.add("Mrázková Miková_Iveta_Mk");
        mMainLightsList_original.add("Němec_Milan_Ně");
        mMainLightsList_original.add("Nerudová_Silvana_Ner");
        mMainLightsList_original.add("Pivoňková_Vladimíra_Pká");
        mMainLightsList_original.add("Prokop_Radomír_Pro");
        mMainLightsList_original.add("Radová_Vladimíra_unknown");
        mMainLightsList_original.add("Siláková_Veronika_Sil");
        mMainLightsList_original.add("Smolka_Aleš_Smo");
        mMainLightsList_original.add("Sulír_Martin_Sul");
        mMainLightsList_original.add("Svobodová_Andrea_Svo");
        mMainLightsList_original.add("Szpandrzyková_Dagmar_Szp");
        mMainLightsList_original.add("Šestáková_Ivana_unknown");
        mMainLightsList_original.add("Šmiřáková_Radka_Šmi");
        mMainLightsList_original.add("Štěpánová_Ivana_Ště");
        mMainLightsList_original.add("Vašíček_David_Vaš");
        mMainLightsList_original.add("Vavříček_Pavel_Vav");
        mMainLightsList_original.add("Weintrittová_Kamila_Wt");
        mMainLightsList_original.add("Wiesnerová_Božena_Wie");
        mMainLightsList_original.add("Zubek_Pavel_Zu");


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mMainTeachersAdapter = new MainTeachersAdapter(mMainLightsList_original, getApplicationContext(), getSupportFragmentManager());
        recyclerView.setAdapter(mMainTeachersAdapter);



    }





















    // ------------------------------------------------------------------------------------ Adapter ------------------------------------------------------------------------------------







    public class MainTeachersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<String> mMainLightsList;
        private Context mContext;
        SharedPreferences mSharedPreferences;

        FragmentManager fragmentManager;

        public MainTeachersAdapter(List<String> list, Context context, FragmentManager fragmentManager) {

            this.mMainLightsList = list;
            this.mContext = context;
            this.fragmentManager = fragmentManager;

            mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainLightsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rozvrh_teachers_list,parent,false));

        }



        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final String mainLightsObject = mMainLightsList.get(position);

            MainLightsViewHolder mainLightsViewHolder = (MainLightsViewHolder)holder;



                mainLightsViewHolder.teacherName.setText(mainLightsObject.split("_")[0] + " " + mainLightsObject.split("_")[1]);



                mainLightsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                         Intent intent = new Intent(mContext, RozvrhNove1.class);
                         intent.putExtra("action", "weeklyByTeacher");
                         intent.putExtra("name", mainLightsObject);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(intent);

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

            TextView teacherName;
            LinearLayout teachersLinearClick;

            public MainLightsViewHolder(View itemView){
                super(itemView);

                teacherName = itemView.findViewById(R.id.teacherName);
                teachersLinearClick = itemView.findViewById(R.id.teachersLinearClick);

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
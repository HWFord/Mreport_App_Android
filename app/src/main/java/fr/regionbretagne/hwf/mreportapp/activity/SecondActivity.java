package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import fr.regionbretagne.hwf.mreportapp.R;
import fr.regionbretagne.hwf.mreportapp.adapter.RapportListAdapter;
import fr.regionbretagne.hwf.mreportapp.model.Rapport;

public class SecondActivity extends Activity {

    private ListView lvSecondReportList;
    private Button btnSecondConnexion;
    private Button btnSecondRapports;
    private TextView tvSecondUrl;

    private String url ="";

    private ArrayList<Rapport> rapportsArray = new ArrayList<>();

    private Intent intent;
    public static ArrayAdapter<Rapport> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvSecondReportList = findViewById(R.id.lvSecondReportList);
        btnSecondConnexion = findViewById(R.id.btnSecondConnexion);
        btnSecondRapports = findViewById(R.id.btnSecondRapports);
        tvSecondUrl = findViewById(R.id.tvSecondUrl);


        adapter = new RapportListAdapter(this, rapportsArray);
        lvSecondReportList.setAdapter(adapter);
//
        SharedPreferences preferences = getSharedPreferences("url",0);
        url = preferences.getString("url", "");

        tvSecondUrl.setText(url);
//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
//            String url = bundle.getString("url");
//        }

        intent = new Intent( SecondActivity.this, ThirdActivity.class);

        btnSecondConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
                //finish();
            }
        });
    }

    public void openThirdActivity(){
        startActivity(intent);
    }
}

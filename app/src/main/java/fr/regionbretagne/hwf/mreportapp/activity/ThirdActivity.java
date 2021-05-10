package fr.regionbretagne.hwf.mreportapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import fr.regionbretagne.hwf.mreportapp.R;

public class ThirdActivity extends Activity {

    private Button btnThirdStartMain;
    private Button btnThirdRapports;
    private TextView tvThirdUrl;
    private TextView tvThirdStateCo;

    private ArrayList reports;

    private Intent intentMain;
    private Intent intentSecond;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnThirdStartMain = findViewById(R.id.btnThirdStartMain);
        btnThirdRapports = findViewById(R.id.btnThirdRapports);
        tvThirdUrl = findViewById(R.id.tvThirdUrl);
        tvThirdStateCo = findViewById(R.id.tvThirdStateCo);

        SharedPreferences preferencesJson = getSharedPreferences("response",0);

        String jsonStr = preferencesJson.getString("response", "");
        if(jsonStr!= null) {
            tvThirdStateCo.setText("Connexion réussi");
            tvThirdStateCo.setTextColor(Color.parseColor("#9FB935"));
        }else{
            tvThirdStateCo.setText("Echec de connexion, vérifier l'url ");
            tvThirdStateCo.setTextColor(Color.parseColor("#B0252E"));
        }

        btnThirdStartMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentMain);
                finish();
            }
        });

        intentSecond = new Intent( ThirdActivity.this, SecondActivity.class);
        btnThirdRapports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentSecond);
            }
        });
    }
}
